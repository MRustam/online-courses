$(document).ready(function () {


    var form = $('#registration-form');

    form.submit(function (event) {

        event.preventDefault();

        var user = {
            roles: [{role : $('#roles').val()}],
            username: $('#username').val(),
            password: $('#password').val(),
            fullName: $('#firstName').val() + " " + $('#lastName').val(),
            email: $('#email').val(),
            skype: $('#skype').val(),
            phone: $('#phone').val(),
            age: $('#age').val()
        };

        $.ajax({
            method: "POST",
            url: "/api/user/save",
            contentType: "application/json",
            data: JSON.stringify(user),
            dataType: 'json',
            cache: false,
            success: function () {
                // window.location.replace("/login");
            },
            error: function (err) {
                // alert("Failed to register" + err);
                // window.location.replace("/registration");
            }
        });

    });


});
