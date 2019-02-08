$(document).ready(function () {

    var form = $('#registration-form');

    form.submit(function (event) {

        event.preventDefault();

        var role = $('#roles').val();
        var userUrl;

        switch (role) {
            case "ROLE_STUDENT":
                userUrl = "/api/student/save";
                break;
            case "ROLE_INSTRUCTOR":
                userUrl = "/api/instructor/save";
                break;
            case "ROLE_ADMIN":
                userUrl = "/api/user/save";
                break;
            default:
                userUrl = "/api/student/save";
                break;
        }

        var user = {
            role : {
                name: $('#roles').val()
            },
            username: $('#username').val(),
            password: $('#password').val(),
            fullName: $('#firstName').val() + " " + $('#lastName').val(),
            email: $('#email').val(),
            skype: $('#skype').val(),
            phone: $('#phone').val(),
            age: $('#age').val(),
            registered: new Date().toJSON()
        };

        console.log(userUrl);

        $.ajax({
            method: "POST",
            url: userUrl,
            contentType: "application/json",
            data: JSON.stringify(user),
            dataType: 'json',
            cache: false,
            success: function () {
                window.location.replace("/success");
            },
            error: function (err) {
                $('#registration-form')[0].reset();
            }
        });

    });


});
