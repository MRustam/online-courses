$(document).ready(function () {

    var path = window.location.href.substring(window.location.href.lastIndexOf('/') + 1).toString();

    switch (path) {

        case 'login':
            $('#auth-form').append('<form th:action="@{/login}" method="post">\n' +
                '                <div class="form-group">\n' +
                '                    <input type="text"\n' +
                '                           id="username"' +
                '                           name="username"\n' +
                '                           class="form-control"\n' +
                '                           autofocus="autofocus"\n' +
                '                           placeholder="Username" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="password"\n' +
                '                           id="password"' +
                '                           name="password"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Password" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <div class="row">\n' +
                '                        <div class="col-sm-6 col-sm-offset-3">\n' +
                '                            <input type="submit"\n' +
                '                                   name="login-submit"\n' +
                '                                   id="login-submit"\n' +
                '                                   class="form-control btn btn-primary"\n' +
                '                                   value="Log In">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </form>');
            break;

        case 'registration':

            $('#auth-form').append('<form th:action="@{/registration}" method="post">\n' +
                '                <div class="form-group">\n' +
                '                    <input type="text"\n' +
                '                           name="username"\n' +
                '                           class="form-control"\n' +
                '                           autofocus="autofocus"\n' +
                '                           placeholder="Username" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="password"\n' +
                '                           name="password"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Password" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="password"\n' +
                '                           name="confirm-password"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Confirm password" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="text"\n' +
                '                           name="firstName"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="First name" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="text"\n' +
                '                           name="lastName"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Last name" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="email"\n' +
                '                           name="email"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Email" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="email"\n' +
                '                           name="skype"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Skype">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="text"\n' +
                '                           name="phone"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Phone number" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <input type="number"\n' +
                '                           name="age"\n' +
                '                           class="form-control"\n' +
                '                           placeholder="Age" required>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <div class="row">\n' +
                '                        <div class="col-sm-6 col-sm-offset-3">\n' +
                '                            <input type="submit"\n' +
                '                                   name="login-submit"\n' +
                '                                   id="login-submit"\n' +
                '                                   class="form-control btn btn-primary"\n' +
                '                                   value="Register">\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </form>');
            break;

    }



    // var form = $('#auth-form');
    //
    // form.submit(function (event) {
    //
    //     event.preventDefault();
    //
    //     var user = {
    //         username: $('#username').val(),
    //         password: $('#password').val()
    //     };
    //
    //     console.log(user);
    //     $.ajax({
    //         method: "POST",
    //         url: "/api/instructors/save",
    //         contentType: "application/json",
    //         data: JSON.stringify(user),
    //         success: function () {
    //             window.location.replace("/home");
    //         },
    //         error: function (err) {
    //             // window.location.replace("/error");
    //         }
    //     });
    //
    // });


});

function submitUser() {

}