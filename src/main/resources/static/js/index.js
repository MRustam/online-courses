$(document).ready(function () {

    var id = new URL(window.location.href).searchParams.get("id");

    // Populate main page with all courses.
    if (id == null) {

        var url;
        var category = new URL(window.location.href).searchParams.get("category");

        if (category != null) {
            url = '/api/course/bycategory/' + category;
        } else {
            url = '/api/course/all';
        }

        //Courses or current course with details.
        $.get(url, function (data) {

            //Populate cards(each course) on all courses page.
            $.each(data, function (index, el) {
                var div = $('<div class="col-lg-3 col-md-6 mb-4">' +
                    '<div class="card h-100">' +
                    '<div class="div-rating"><p id="p-rating">' + el.rating + '</p></div>' +
                    '<img class="card-img-top" src="/img/abstract.jpg" />' +
                    '<div class="card-body">' +
                    '<h5 class="card-title">' + el.title + '</h5>' +
                    '<p class="card-text">' + el.description + '</p>' +
                    '</div>' +
                    '<div class="card-footer text-center">' +
                    '<a href="?id=' + el.id + '" class="btn btn-info btn-sm">More</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>');

                $('#courses-list').append(div);
            })
        }).fail(function (err) {
            alert(err);
        })

    } else {

        // Populate main page current course. Works if id were passed by parameter (/api/courses/all#?id).
        $.get('/api/course/' + id, function (data) {

            // Populate course data.
            $('#current-course').append('<div class="card">' +
                '<img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">' +
                '<div class="card-body">' +
                '<h3 class="card-title">' + data.title + '</h3>' +
                '<h6>category: <span>' + data.category + '</span></h6>' +
                '<h6>start: <span style="color: darkgreen">' + data.startDate + '</span></h6>' +
                '<hr>' +
                '<p class="card-text font-italic">' + data.description + '</p>' +
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
                        '<p>' + el.text + '</p>' +
                        '<small class="text-muted">posted: ' + el.created + '</small>' +
                        '<hr>' +
                        '</div>');
                });

            });

            $('#current-course').append('<a href="#" class="btn btn-success" style="margin-bottom: 100px;">Leave a Review</a>');


        }).fail(function (err) {
            alert(err);
        })
    }
});
