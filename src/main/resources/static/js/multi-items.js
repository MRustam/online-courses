$(document).ready(function () {








    //All instructors.
    $.get('/api/instructors/all', function (data) {

        $('#instructors-content').append(
            '<h1 class="my-4">All Instructors' +
            '</h1>');

        $.each(data, function (index, el) {

            $('#instructors-content').append(
                '    <div class="row">\n' +
                '        <div class="col-lg-4 col-sm-6 text-center mb-4">\n' +
                '            <img class="rounded-circle img-fluid d-block mx-auto" src="http://placehold.it/200x200" alt="">\n' +
                '            <h3>' + el.firstName + ' ' + el.lastName + '<br/>' +
                '                <small> work experience: ' + el.detail.workExperience + ' years</small>\n' +
                '            </h3>\n' +
                '            <p>email: ' + el.detail.email + '<br/>skype: ' + el.detail.skype  + '</p>' +
                '        </div>\n' +
                '    </div>');

        })

    }).fail(function (err) {
        alert(err);
    })







    // All students.
    $.get('/api/students/all', function (data) {

        $('#students-content').append('<h1 class="my-4">All Students</h1>' +
            '<div class="row" id="student-row">' +

            '</div>');

        $.each(data, function (index, el) {

            $('#student-row').append(
                '        <div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">\n' +
                '          <div class="card h-100">\n' +
                '            <a href="#"><img class="card-img-top" src="/img/700x400.png" alt=""></a>\n' +
                '            <div class="card-body">\n' +
                '              <h4 class="card-title">\n' +
                '                <a href="#">' + el.firstName + ' ' + el.lastName + '</a>\n' +
                '              </h4>\n' +
                '              <p class="card-text">' +
                'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur eum quasi sapiente nesciunt? Voluptatibus sit, repellat sequi itaque deserunt, dolores in, nesciunt, illum tempora ex quae? Nihil, dolorem!' +
                '               </p>\n' +
                '            </div>\n' +
                '          </div>\n' +
                '        </div>');

        })

    }).fail(function (err) {
        alert(err);
    })





});
