$(document).ready(function () {

    var id = new URL(window.location.href).searchParams.get("id");

    // Populate main page with all courses.
    if (id == null) {

        var url;
        var category = new URL(window.location.href).searchParams.get("category");
        var studentId = new URL(window.location.href).searchParams.get("studentId");

        if (category != null) {
            url = '/api/course/bycategory/' + category;
        } else if (studentId > 0 ) {
            url = '/api/course/all/by-student-id/' + studentId;
        } else {
            url = '/api/course/all';
        }

        //Courses or current course with details.
        $.get(url, function (data) {
            //Populate cards(each course) on all courses page.
            $.each(data, function (index, el) {
                var div = $('<div class="col-lg-3 col-md-6 mb-4">' +
                    '           <div class="card h-100" style="border:2px solid wheat;">' +
                    '               <div class="div-courseRating">' +
                    '                   <span class="small font-weight-light font-italic ml-1">rating: ' + el.score + '</span><br/>' +
                    '                   <span class="small font-weight-light font-italic ml-1">instructor rating: ' + el.rating + '</span><br/>' +
                    '                   <span class="small font-weight-light font-italic ml-1">enrolled: ' + el.enrolled + '</span>' +
                    '               </div>' +
                    '               <img class="card-img-top" src="/img/abstract.jpg" />' +
                    '               <div class="card-body">' +
                    '                   <h5 class="card-title">' + el.title + '</h5>' +
                    '                   <p class="card-text small font-weight-light font-italic">' + el.description + '</p>' +
                    '               </div>' +
                    '               <div class="card-footer text-center">' +
                    '                   <a href="?id=' + el.id + '" class="btn btn-info btn-sm">More</a>' +
                    '               </div>' +
                    '           </div>' +
                    '       </div>');

                $('#courses-list').append(div);
            });
        }).fail(function (err) {
            console.log(err);
        })

    } else {

        // Populate main page current course. Works if id were passed by parameter (/api/courses/all#?id).
        $.get('/api/course/' + id, function (data) {
            // Populate course data.
            $('#current-course').append('<div class="card">' +
                '<img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">' +
                '<div class="card-body">' +
                '<h3 class="card-title">' + data.title + '</h3>' +
                '<h6>category: <span class="font-weight-light font-italic small">' + data.category + '</span></h6>' +
                '<h6>start: <span class="font-weight-light" style="color: darkgreen">' + data.date + '</span></h6>' +
                '<h6>duration: <span class="font-weight-light" style="color: darkgreen">' + data.duration + '</span></h6>' +
                '<h6>started: <span style="color: #e4606d">' + (data.status ? 'yes' : 'no') + '</span></h6>' +
                '<h5>instructor: <span><a href="#' + data.instructorId + '">' + data.owner + '</a></span></h5>' +
                '<hr>' +
                '<h6>reviews: <span style="color: darkgreen">' + (data.rcount != null ? data.rcount : 'nothing') + '</span></h6>' +
                '<h6><a href="/students?cId=' + id + '">enrolled students: </a><span style="color: darkgreen">' + (data.enrolled > 0 ? data.enrolled : 'nobody') + '</span></h6>' +
                '<hr>' +
                '<p class="card-text font-italic"><span class="font-weight-bold">description: </span>' + data.description + '</p>' +
                '<div sec:authorize="hasRole(&#39;STUDENT&#39;)">' +
                '     <button onclick="enrollFunction(' + id + ')" class="btn btn-success w-25">Enroll course</button>' +
                '     <button onclick="leaveFunction(' + id + ')" class="btn btn-success w-25">Leave course</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="card card-outline-secondary my-4" id="current-course-with-reviews">' +
                '<div class="card-header">' +
                'Reviews' +
                '</div>' +
                '</div>');

            // Populate reviews.
            $.get('/api/review/bycourse/' + id, function (data) {

                $.each(data, function (index, el) {
                    $('#current-course-with-reviews').append('<div class="card-body">' +
                        '<p class="small font-weight-light font-italic">' + el.text + '</p>' +
                        '<small class="text-muted">posted: ' + el.created + '</small>' +
                        '<hr>' +
                        '</div>');
                });

            });
            $('#current-course').append('<a href="#" class="btn btn-secondary" style="margin-bottom: 100px;">Leave a Review</a>');

        }).fail(function (err) {
            alert(err);
        });
    }
});

function enrollFunction(id) {

    $.ajax({
        url: '/api/student/enroll/courseId/' + id,
        contentType: 'application/json',
        method: 'PUT',
        success: function () {
            alert('success!');
            window.location.reload();
        },
        error: function (err) {
            console.log('error! - ' + err);
        }
    });
}

function leaveFunction(id) {

    $.ajax({
        url: '/api/student/leave/courseId/' + id,
        contentType: 'application/json',
        method: 'PUT',
        success: function () {
            alert('success!');
            window.location.reload();
        },
        error: function (err) {
            console.log('error! - ' + err);
        }
    });
}

