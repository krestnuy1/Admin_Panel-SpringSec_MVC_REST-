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