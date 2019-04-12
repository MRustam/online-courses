$(document).ready(function () {

    var url;
    var id = new URL(window.location.href).searchParams.get("id");
    var cId = new URL(window.location.href).searchParams.get("cId");



    if (cId > 0) {
        url = '/api/student/all/by-course-id/' + cId;
    } else if (id > 0 ) {
        url = '/api/student/' + id;
    } else {
        url = '/api/student/all';
    }

    console.log(cId);
    console.log(url);

    if (id > 0) {
        $.get(url, function (data) {

            $('#students').append(
                '<h1 class="my-4 text-center display-5" style="color: white">hi: ' + data.username + '</h1>' +
                '<div class="row" style="background: aliceblue;">' +
                '        <div class="col-lg-12 col-sm-6 mb-4">\n' +
                '            <div>' +
                '               <h3> Full name: ' + data.fullname + '</h3>' +
                '               <p>username: ' + data.username + '</p>' +
                '               <p>age: ' + data.age + '</p>' +
                '               <p>email: ' + data.email + '<br/>skype: ' + data.skype + '</p>' +
                '               <p>enrolledCourses on: ' + data.courses + ' course/s</p>' +
                '               <p>performance: ' + data.performance + ' </p>' +
                '               <p>registered: ' + data.registered + '</p>' +
                '               <p><a href="/home?studentId=' + data.id + '">Your ru.rmamedov.createdCourses link</a></p>' +
                '           </div>' +
                '        </div>' +
                '</div>');

        }).fail(function (err) {
            alert(err)
        });

    } else {

        // All students.
        $.get(url, function (data) {

            $('#students').append(
                '<h1 class="my-4 text-center display-5" style="color: white">All Students</h1>' +
                '<div style="background: #f7f7f7"; class="text-center">' +
                '<table class="table table-bordered table-striped">\n' +
                '       <thead>\n' +
                '           <tr class="small font-italic">\n' +
                '               <th class="">ID</th>\n' +
                '               <th class="">Full Name</th>\n' +
                '               <th class="">Performance</th>\n' +
                '               <th class="">Enrolled on</th>\n' +
                '               <th class="">Phone</th>\n' +
                '               <th class="">Email</th>\n' +
                '               <th class="">Skype</th>\n' +
                '               <th class="">Age</th>\n' +
                '               <th class="">Registered</th>\n' +
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

                $('#tbody-users').append('<tr class="small">' +
                    '                           <td style="text-align:center;" class="">' + el.id + '</td>' +
                    '                           <td><a href="?id=' + el.id + '">' + el.fullname + '</a></td>' +
                    '                           <td>' + el.performance + '</td>' +
                    '                           <td>' + el.courses + '</td>' +
                    '                           <td style="text-align:center;">' + el.phone + '</td>' +
                    '                           <td style="text-align:center;">' + el.email + '</td>' +
                    '                           <td style="text-align:center;">' + el.skype + '</td>' +
                    '                           <td style="text-align:center;">' + el.age + '</td>' +
                    '                           <td style="text-align:center;">' + el.registered + '</td>' +
                    '                       </tr>');

            })

        }).fail(function (err) {
            alert(err);
        });
    }
});