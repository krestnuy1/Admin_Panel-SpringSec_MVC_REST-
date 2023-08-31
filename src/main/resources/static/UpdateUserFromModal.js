document.addEventListener("DOMContentLoaded", function () {
    const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
    const saveButton = document.getElementById("saveButton");
    const userForm = document.querySelector(".form"); // Находим форму внутри модального окна

    saveButton.addEventListener("click", function () {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const firstname = document.getElementById("firstname").value;
        const lastname = document.getElementById("secondname").value;
        const age = document.getElementById("age").value;
        const email = document.getElementById("email").value;
        const selectedRoles = Array.from(document.getElementById("selectedRoles").selectedOptions).map(option => option.value);

        const user = {
            username: username,
            password: password,
            firstname: firstname,
            lastname: lastname,
            age: age,
            email: email,
            roles: selectedRoles.map(roleId => ({id: roleId})) // Преобразование ID ролей в объекты
        };

        const headers = new Headers();
        headers.append(csrfHeader, csrfToken);
        headers.append("Content-Type", "application/json"); // Указываем тип медиа как JSON

        fetch("/admin/update", {
            method: "POST",
            headers: headers,
            body: JSON.stringify(user), // Преобразуем объект пользователя в JSON-строку
        })
            .then(data => {
                // Обработка успешного ответа от сервера
                console.log("Пользователь сохранен:", data);
                $('#userForm').modal('hide'); // Закрытие модального окна
                $(document).ready(function () {
                    // Получение CSRF токена из мета-тегов
                    var csrfToken = $('meta[name="_csrf"]').attr('content');
                    var csrfHeader = $('meta[name="_csrf_header"]').attr('content');

                    // Настройка CSRF токена для всех запросов
                    $.ajaxSetup({
                        headers: {
                            'X-CSRF-TOKEN': csrfToken
                        }
                    });

                    // Функция для заполнения таблицы данными
                    function populateTable(data) {
                        var tableBody = $('#table-body');
                        tableBody.empty();

                        data.forEach(function (item) {
                            var row = '<tr>' +
                                '<td>' + item.id + '</td>' +
                                '<td>' + item.firstname + '</td>' +
                                '<td>' + item.lastname + '</td>' +
                                '<td>' + item.age + '</td>' +
                                '<td>' + item.email + '</td>' +
                                '<td>' + item.rolesasstring + '</td>' +
                                '<td>' +
                                '<div>' +
                                '<button id="edit-user" type="button" class="btn btn-info"' +
                                'data-toggle="modal" data-target="#userForm"' +
                                'data-userId="' + item.id + '">' +
                                'Редактировать' +
                                '</button>' +
                                '</div>' +
                                '</td>' +
                                '<td>' +
                                '<div>' +
                                '<form class="delete-user-form">' +
                                '<input type="hidden" name="userID" value="' + item.id + '"/>' +
                                '<button type="submit" class="btn btn-danger delete-user-button">Удалить</button>' +
                                '</form>' +
                                '</div>' +
                                '</td>' +
                                '</tr>';
                            tableBody.append(row);
                        });
                    }

                    $(document).on("submit", ".delete-user-form", function (e) {
                        e.preventDefault();

                        var form = $(this);
                        var userId = form.find('input[name="userID"]').val();

                        $.ajax({
                            url: `/admin/deleteUser/${userId}`,
                            method: 'DELETE',
                            success: function (response) {
                                $.ajax({
                                    url: '/admin/getAll',
                                    method: 'GET',
                                    success: function (response) {
                                        populateTable(response);
                                    }
                                });
                            },
                            error: function (error) {
                                console.error("Ошибка при удалении пользователя:", error);
                            }
                        });
                    });

                    $.ajax({
                        url: '/admin/getAll',
                        method: 'GET',
                        success: function (response) {
                            populateTable(response);
                        },
                        error: function (error) {
                            console.error("Ошибка при получении данных:", error);
                        }
                    });
                });
                // Дополнительные действия при успешном сохранении

            })
            .catch(error => {
                // Обработка ошибок при выполнении запроса
                console.error("Ошибка при сохранении пользователя:", error);
            });
    });
});