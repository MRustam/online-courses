$(document).ready(function () {

    var path = window.location.href.substring(window.location.href.lastIndexOf('/') + 1).toString();

    switch (path) {

        case 'students':
            // All students.
            $.get('/api/students/all', function (data) {

                $('#multi-content').append(
                    '<h1 class="my-4 text-center display-5" style="color: white">All Students</h1>' +
                    '<div style="background: #f7f7f7"; class="text-center">' +
                        '<table class="table table-bordered table-striped">\n' +
                    '       <thead>\n' +
                    '           <tr>\n' +
                    '               <th class="">№</th>\n' +
                    '               <th class="">First password</th>\n' +
                    '               <th class="">Last password</th>\n' +
                    '               <th class="">Email</th>\n' +
                    '               <th class="">Skype</th>\n' +
                    '               <th class="">Age</th>\n' +
                    '               <th class="">Edit</th>\n' +
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
                    '                <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true" class="">×   </span><span class="sr-only">Close</span>\n' +
                    '\n' +
                    '                </button>\n' +
                    '                 <h4 class="modal-title" id="myModalLabel">Modal title</h4>\n' +
                    '\n' +
                    '            </div>\n' +
                    '            <div class="modal-body"></div>\n' +
                    '            <div class="modal-footer">\n' +
                    '                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\n' +
                    '                <button type="button" class="btn btn-primary">Save changes</button>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '</div>' +
                    '</div>' +
                    '<script src="/js/all-role-table.js"></script>');

                $.each(data, function (index, el) {

                    $('#tbody-users').append(   '<tr>' +
                        '                           <td style="text-align:center;" class="">' + el.id + '</td>' +
                        '                           <td class="">' + el.firstName + '</td>' +
                        '                           <td style="text-align:center;" class="">' + el.lastName + '</td>' +
                        '                           <td style="text-align:center;" class="">' + el.detail.email + '</td>' +
                        '                           <td style="text-align:center;" class="">' + el.detail.skype + '</td>' +
                        '                           <td style="text-align:center;" class="">' + el.detail.age + '</td>' +
                        '                           <td style="text-align:center;">' +
                        '                           <div class="btn-group-sm">' +
                        '                               <button class="btn btn-success" data-toggle="modal" data-target="#myModal" contenteditable="false">Add</button>' +
                        '                               <button class="btn btn-info" data-toggle="modal" data-target="#myModal" contenteditable="false">Edit</button>' +
                        '                           </div>' +
                        '                           </td>' +
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
                    '</h1>');

                $.each(data, function (index, el) {

                    $('#multi-content').append(
                        '    <div class="row">\n' +
                        '        <div class="col-lg-4 col-sm-6 text-center mb-4">\n' +
                        '            <img class="rounded-circle img-fluid d-block mx-auto" src="/img/lego-developer.jpeg" alt="">\n' +
                        '            <h3 style="color: white">' + el.firstName + ' ' + el.lastName + '<br/>' +
                        '                <small style="color: white"> work experience: ' + el.detail.workExperience + ' years</small>\n' +
                        '            </h3>\n' +
                        '            <p style="color: white">email: ' + el.detail.email + '<br/>skype: ' + el.detail.skype + '</p>' +
                        '        </div>\n' +
                        '    </div>');

                })

            }).fail(function (err) {
                alert(err);
            })
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
                '                        <input password="title" type="text" class="form-control" id="title-input" placeholder="Title" required />\n' +
                '                    </div>\n' +
                '                       <div class="form-group mb-4">' +
                '                         <input password="title" type="text" class="form-control" id="description-input" placeholder="Description" required />\n' +
                '                   </div>' +
                '                   <div class="form-group mb-4">' +
                '                       <input password="title" type="date" class="form-control" id="date-input" placeholder="Start date" required />\n' +
                '                   </div>' +
                '                    <div class="form-group">\n' +
                '                        <select password="category[category]" class="custom-select" id="select-category" required>\n' +
                '                            <option value="">Category</option>\n' +
                '                            <option value="programming">Programming</option>\n' +
                '                            <option value="languages">Languages</option>\n' +
                '                            <option value="painting">Painting</option>\n' +
                '                            <option value="hobby">Hobby</option>\n' +
                '                        </select>\n' +
                '                    </div>\n' +
                '                   <div class="form-group mb-4">' +
                '                       <input password="title" type="number" class="form-control" id="duration-input" placeholder="Duration" required />\n' +
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
            url: "/api/courses/save",
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


