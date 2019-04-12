$(document).ready(function () {

    var id = new URL(window.location.href).searchParams.get("id");

    // Populate main page with all ru.rmamedov.createdCourses.
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
            //Populate cards(each course) on all ru.rmamedov.createdCourses page.
            $.each(data, function (index, el) {
                var div = $('<div class="col-lg-3 col-md-6 mb-4">' +
                    '           <div class="card h-100" style="border:2px solid wheat;">' +
                    '               <div class="div-courseRating">' +
                    '                   <span class="small font-weight-light font-italic ml-1">rating: ' + el.score + '</span><br/>' +
                    '                   <span class="small font-weight-light font-italic ml-1">instructor rating: ' + el.rating + '</span><br/>' +
                    '                   <span class="small font-weight-light font-italic ml-1">enrolledCourses: ' + el.enrolled + '</span>' +
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

                $('#ru.rmamedov.createdCourses-list').append(div);
            });
        }).fail(function (err) {
            console.log(err);
        })

    } else {

        // Populate main page current course. Works if id were passed by parameter (/api/ru.rmamedov.createdCourses/all#?id).
        $.get('/api/course/' + id, function (data) {
            // Populate course persist.
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
                '<h6>comments: <span style="color: darkgreen">' + (data.rcount != null ? data.rcount : 'nothing') + '</span></h6>' +
                '<h6><a href="/students?cId=' + id + '">enrolledCourses students: </a><span style="color: darkgreen">' + (data.enrolled > 0 ? data.enrolled : 'nobody') + '</span></h6>' +
                '<hr>' +
                '<p class="card-text font-italic"><span class="font-weight-bold">description: </span>' + data.description + '</p>' +
                '<div sec:authorize="hasRole(&#39;STUDENT&#39;)">' +
                '     <button onclick="subscribe(' + id + ')" class="btn btn-success w-25">Enroll course</button>' +
                '     <button onclick="unsubscribe(' + id + ')" class="btn btn-info w-25">Leave course</button><br/>' +
                '     <button onclick="deleteCourse(' + id + ')" class="btn btn-danger w-25 mt-3">Delete course</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="card card-outline-secondary my-4" id="current-course-with-comments">' +
                '<div class="card-header">' +
                'Reviews' +
                '</div>' +
                '</div>');

            // Populate comments.
            $.get('/api/review/bycourse/' + id, function (data) {

                $.each(data, function (index, el) {
                    $('#current-course-with-comments').append('<div class="card-body">' +
                        '<p class="small font-weight-light font-italic">' + el.text + '</p>' +
                        '<small class="text-muted"><span class="font-weight-bold">posted:</span> ' + el.created + '</small><br/>' +
                        '<small class="text-muted"><span class="font-weight-bold">owner:</span> ' + el.owner.fullName + '</small>' +
                        '<hr>' +
                        '</div>');
                });

            });
            $('#current-course').append('<div>' +
                '   <textarea name="review-text" id="review-text" class="col-lg-12 col-md-6 col-sm-3"/><br/>' +
                '   <button onclick="addReview(' + id + ')" class="btn btn-info" style="margin-bottom: 100px;">Leave a Review</button>' +
                '</div>');

        }).fail(function (err) {
            alert(err);
        });
    }
});

function deleteCourse(id) {

    $.ajax({
        url: '/api/course/delete/' + id,
        contentType: 'application/json',
        method: 'DELETE',
        success: function () {
            alert('You are successfully deleted this course!');
            window.location.replace('/home');
        },
        error: function () {
            console.log('You should be logget in as a instructor!');
        }
    });
}

function subscribe(id) {

    $.ajax({
        url: '/api/student/enroll/courseId/' + id,
        contentType: 'application/json',
        method: 'PUT',
        success: function () {
            alert('You are successfully enrolledCourses on this course!');
            window.location.reload();
        },
        error: function () {
            console.log('You should be logget in as a student!');
        }
    });
}

function unsubscribe(id) {

    $.ajax({
        url: '/api/student/leave/courseId/' + id,
        contentType: 'application/json',
        method: 'PUT',
        success: function () {
            alert('You are successfully drop this course!');
            window.location.reload();
        },
        error: function () {
            console.log('You should be logget in as a student!');
        }
    });
}

function addReview(id) {

    var review = {
        text: $("#review-text").val(),
        created: new Date().toJSON()
    };
    $.ajax({
        method: "POST",
        url: "/api/review/add/" + id,
        contentType: "application/json",
        data: JSON.stringify(review),
        success: function () {
            alert('success!');
            window.location.reload();
        },
        error: function () {
            alert('You should be logget in!');
            window.location.reload();
        }
    })
}



