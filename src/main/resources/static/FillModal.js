$('#userForm').on('shown.bs.modal', function (event) {
    let button = $(event.relatedTarget)
    let userId = button.data('userid')

    if (userId) {
        $.get({
            url: 'admin/user/' + userId,
            success: (data) => {
                let modal = $(this)
                modal.find('#username').val(data.username)
                modal.find('#firstname').val(data.firstname)
                modal.find('#secondname').val(data.lastname)
                modal.find('#age').val(data.age)
                modal.find('#email').val(data.email)
                modal.find('#selectedRoles').val(data.roles)
            },
            error: err => {
                alert(err);
            }
        });

    }
})