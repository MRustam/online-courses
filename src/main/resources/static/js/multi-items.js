$(document).ready(function () {

    var path = window.location.href.substring(window.location.href.lastIndexOf('/') + 1).toString();

    console.log(path);

    switch (path) {

        case 'users':
            $.get('/api/user/all', function (data) {

                $('#multi-content').append(
                    '<h1 class="my-4 text-center display-5" style="color: white">Users</h1>' +
                    '<div style="background: #f7f7f7"; class="text-center">' +
                    '<table class="table table-bordered table-striped">\n' +
                    '       <thead>\n' +
                    '           <tr>\n' +
                    '               <th>ID</th>\n' +
                    '               <th>Full Name</th>\n' +
                    '               <th>Phone</th>\n' +
                    '               <th>Email</th>\n' +
                    '               <th>Skype</th>\n' +
                    '               <th>Age</th>\n' +
                    '               <th>Registered</th>\n' +
                    '               <th>Role</th>\n' +
                    '           </tr>\n' +
                    '       </thead>\n' +
                    '       <tbody id="tbody-users">\n' +

                    '       </tbody>\n' +
                    '    </table>\n' +
                    '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\n' +
                    '    <div class="modal-dialog">\n' +
                    '        <div class="modal-content"></div>\n' +
                    '    </div>\n' +
                    '    <div class="modal-dialog">\n' +
                    '        <div class="modal-content"></div>\n' +
                    '    </div>\n' +
                    '    <div class="modal-dialog">\n' +
                    '        <div class="modal-content">\n' +
                    '            <div class="modal-header">\n' +
                    '                <button type="button" class="close" persist-dismiss="modal"> <span aria-hidden="true" class="">×   </span><span class="sr-only">Close</span>\n' +
                    '\n' +
                    '                </button>\n' +
                    '                 <h4 class="modal-title" id="myModalLabel">Modal title</h4>\n' +
                    '\n' +
                    '            </div>\n' +
                    '            <div class="modal-body"></div>\n' +
                    '            <div class="modal-footer">\n' +
                    '                <button type="button" class="btn btn-default" persist-dismiss="modal">Close</button>\n' +
                    '                <button type="button" class="btn btn-primary">Save changes</button>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '</div>' +
                    '</div>');

                $.each(data, function (index, el) {

                    $('#tbody-users').append(   '<tr>' +
                        '                           <td style="text-align:center;" class="">' + el.id + '</td>' +
                        '                           <td class="small font-italic w-25">' + el.fullName + '</td>' +
                        '                           <td style="text-align:center;" class="small font-italic">' + el.phone + '</td>' +
                        '                           <td style="text-align:center;" class="small font-italic">' + el.email + '</td>' +
                        '                           <td style="text-align:center;" class="small font-italic">' + el.skype + '</td>' +
                        '                           <td style="text-align:center;" class="small font-italic">' + el.age + '</td>' +
                        '                           <td style="text-align:center;" class="small font-italic">' + el.registered + '</td>' +
                        '                           <td style="text-align:center;" class="small font-italic">' + el.role.name + '</td>' +
                        '                       </tr>');
                })

            }).fail(function (err) {
                alert(err);
            });
            break;

        case 'instructors':
            //All instructors.
            $.get('/api/instructor/all', function (data) {

                $('#multi-content').append(
                    '<h1 class="my-4 text-center display-5" style="color: white">All Instructors' +
                    '</h1>' +
                    '<div class="row">' +

                    '</div>');

                $.each(data, function (index, el) {

                    $('.row').append(
                        '        <div class="col-lg-4 col-sm-6 mb-4">\n' +
                        '            <img class="img-fluid d-block mx-auto" src="/img/lego-developer.jpeg" alt="">\n' +
                        '            <div class="text-center">' +
                        '               <h3>' + el.user.fullName + '</h3>' +
                        '               <p> work experience: ' + el.workExperience + ' years</p>\n' +
                        '               <p>' + el.user.phone + '</p>' +
                        '               <p style="color: white">email: ' + el.user.email + '<br/>skype: ' + el.user.skype + '</p>' +
                        '           </div>' +
                        '        </div>');

                })

            }).fail(function (err) {
                alert(err);
            });
            break;


        case 'access-denied':

            $('#multi-content').append('<div class="col-lg-12 col-sm-12 text-center" style="margin-top: 200px">' +
                '<h1 class="display-1">403</h1>' +
                '<h1 style="color: brown">ACCESS DENIED</h1>' +
                '<a class="btn btn-info" href="/home" style="margin-top: 30px">Home page</a>' +
                '</div> ');
            break;


        case 'new-course':
            $('#multi-content').append('<div class="col-xs-12 col-sm-9" id="newsGrid" style="margin: 0 auto; width: 600px; background: aliceblue; border-radius: 7px; margin-top: 5%">\n' +
                '\n' +
                '            <div class="mb-4" id="news-form">\n' +
                '\n' +
                '                <h2 class="text-center mb-3">Register course</h2>\n' +
                '\n' +
                '                <form id="course-form" method="POST" >\n' +
                '                    <div class="form-group">\n' +
                '                        <input name="title" type="text" class="form-control" id="title-input" placeholder="Title" required />\n' +
                '                    </div>\n' +
                '                       <div class="form-group mb-4">' +
                '                         <textarea name="title" class="form-control" id="description-input" placeholder="Description" required />\n' +
                '                   </div>' +
                '                   <div class="form-group mb-4">' +
                '                       <label for="date-input" class="small font-weight-light font-italic">Start date</label>' +
                '                       <input name="title" type="date" class="form-control" id="date-input" required />\n' +
                '                   </div>' +
                '                    <div class="form-group">\n' +
                '                        <select name="category[category]" class="custom-select" id="select-category" required>\n' +
                '                            <option value="">Category</option>\n' +
                '                            <option value="programming">Programming</option>\n' +
                '                            <option value="languages">Languages</option>\n' +
                '                            <option value="painting">Painting</option>\n' +
                '                            <option value="hobby">Hobby</option>\n' +
                '                        </select>\n' +
                '                    </div>\n' +
                '                   <div class="form-group mb-4">' +
                '                       <input name="title" type="number" class="form-control" id="duration-input" placeholder="Duration" required />\n' +
                '                   </div>' +
                '\n' +
                '                    <button type="submit" class="btn btn-primary mb-4">Submit</button>\n' +
                '                </form>\n' +
                '            </div>\n' +
                '\n' +
                '\n' +
                '        </div><!--/.col-xs-12.col-sm-9-->');

            break;


        case 'success':

            $('#multi-content').append('<div class="col-lg-12 col-sm-12 text-center" style="margin-top: 200px">' +
                '<h1 style="color: white">SUCCESSFUL</h1>' +
                '<a class="btn btn-info" href="/home" style="margin-top: 30px">Home page</a>' +
                '</div> ');
            break;


        default:

            $('#multi-content').append('<div class="col-lg-12 col-sm-12 text-center" style="margin-top: 200px">' +
                '<h1 class="display-1">404</h1>' +
                '<h1 style="color: brown">PAGE NOT FOUND</h1>' +
                '<a class="btn btn-info" href="/home" style="margin-top: 30px">Home page</a>' +
                '</div> ');
            break;
    }



    // // Delete student.
    // var deleteStudent = $('#delete-student');
    //
    // var studentID = url.searchParams.get("id");
    //
    // deleteStudent.submit(function(event) {
    //
    //     // event.preventDefault();
    //
    //     $.ajax({
    //
    //         type: "DELETE",
    //         url: "/api/students/delete/" + studentID,
    //         success: function() {
    //             window.location.replace("/success");
    //         },
    //         error: function () {
    //             // window.location.replace("/error");
    //         }
    //     })
    // });





    // Add new course POST.
    var addCourseForm = $('#course-form');
    addCourseForm.submit(function (event) {
        event.preventDefault();
        //PREPARE FORM DATA
        var dataInput = {
            title: $("#title-input").val(),
            description: $("#description-input").val(),
            duration: $("#duration-input").val(),
            date: $("#date-input").val(),
            category: $("#select-category").val()
        };
        $.ajax({
            method: "POST",
            url: "/api/course/save",
            contentType: "application/json",
            data: JSON.stringify(dataInput),
            success: function () {
                window.location.replace("/home");
            },
            error: function (err) {
                window.location.replace("/error");
            }
        })
    });


});


