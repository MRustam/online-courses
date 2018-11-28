$(document).ready(function () {



    //Courses or current course with details.
    $.get('/api/courses/all', function (data) {

        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");

        // Populate main page with all courses.
        if (id == null) {


            // Populate carousel on all courses page.
            var div = $('<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">' +
                '<ol class="carousel-indicators">' +
                '<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>' +
                '<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>' +
                '<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>' +
                '</ol>' +
                '<div class="carousel-inner" role="listbox">' +
                '<div class="carousel-item active">' +
                '<img class="d-block img-fluid" src="/img/900x350.png" alt="First slide">' +
                '</div>' +
                '<div class="carousel-item">' +
                '<img class="d-block img-fluid" src="/img/900x350.png" alt="Second slide">' +
                '</div>' +
                '<div class="carousel-item">' +
                '<img class="d-block img-fluid" src="/img/900x350.png" alt="Third slide">' +
                '</div>' +
                '</div>' +
                '<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">' +
                '<span class="carousel-control-prev-icon" aria-hidden="true"></span>' +
                '<span class="sr-only">Previous</span>' +
                '</a>' +
                '<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">' +
                '<span class="carousel-control-next-icon" aria-hidden="true"></span>' +
                '<span class="sr-only">Next</span>' +
                '</a>' +
                '</div>');
            $('#courses-list').append(div);


            //Populate cards(each course) on all courses page.
            $.each(data, function (index, el) {
                var div = $('<div class="col-lg-3 col-md-6 mb-3">' +
                    '<div class="card h-100">' +
                    '<div class="div-rating"><p id="p-rating">' + el.rating + '</p></div>' +
                    '<img class="card-img-top" src="/img/abstract.jpg" />' +
                    '<div class="card-body">' +
                    '<h5 class="card-title">' + el.title + '</h5>' +
                    '<p class="card-text">' + el.description + '</p>' +
                    '</div>' +
                    '<div class="card-footer">' +
                    '<a href="?id=' + el.id + '" class="btn btn-primary">Find Out More!</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>');

                $('#courses-list').append(div);
            })

        } else {


            // Populate main page current course. Works if id were passed by parameter (/api/courses/all#?id).
            $.get('/api/courses/all/' + id, function (data) {

                // Populate course data.
                $('#current-course').append('<div class="card mt-4">' +
                    '<img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">' +
                    '<div class="card-body">' +
                    '<h3 class="card-title">' + data.title + '</h3>' +
                    '<h6>category: <span>' + data.category + '</span></h6>' +
                    '<h6>start: <span style="color: darkgreen">' + data.startDate + '</span></h6>' +
                    '<hr>' +
                    '<p class="card-text">' + data.description + '</p>' +
                    '</div>' +
                    '</div>' +
                    '<div class="card card-outline-secondary my-4" id="current-course-with-reviews">' +
                    '<div class="card-header">' +
                    'Course reviews' +
                    '</div>' +
                    '</div>');

                // Populate reviews.
                $.each(data.reviews, function (index, el) {

                    $('#current-course-with-reviews').append('<div class="card-body">' +
                        '<p>' + el.text + '</p>' +
                        '<small class="text-muted">posted: ' + el.creation_time + '</small>' +
                        '<hr>' +
                        '</div>');

                })

                $('#current-course').append('<a href="#" class="btn btn-success">Leave a Review</a>')


            }).fail(function (err) {
                alert(err);
            })

        }

    }).fail(function (err) {
        alert(err);
    })
    // End of populating courses or current course with details.
});
