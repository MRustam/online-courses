$(document).ready(function () {

<<<<<<< HEAD
    // Populate main page table with all courses.
=======
    // Populate main page with all courses.
>>>>>>> development
    $.get('/api/courses/all', function (data) {
        $.each(data, function (index, el) {

            var div = $('<div class="col-lg-3 col-md-6 mb-4">' +
<<<<<<< HEAD
                            '<div class="card">' +
=======
                            '<div class="card h-100">' +
>>>>>>> development
                                '<img class="card-img-top" src="/img/abstract.jpg" />' +
                                '<div class="card-body">' +
                                    '<h4 class="card-title">' + el.title +'</h4>' +
                                    '<p class="card-text">' + el.description + '</p>' +
<<<<<<< HEAD
                                    '<p>' + el.rating + '</p>' +
                                '</div>' +
                                '<div class="card-footer">' +
                                    '<a href="#?' + el.id + '" class="btn btn-primary">Find Out More!</a>' +
=======
                                    '<p id="p-rating">' + el.rating + '</p>' +
                                '</div>' +
                                '<div class="card-footer">' +
                                    '<a href="#?' + el.id + '" class="btn btn-primary">Find Out More!</a>' +
                                    '<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>' +
>>>>>>> development
                                '</div>' +
                            '</div>' +
                        '</div>');

            $('#courses-list').append(div);

        })
    }).fail(function (err) {
        alert(err);
    })

    // Populate table instructors page.
    $.get('/api/instructors/all', function (data) {
        $.each(data, function (index, el) {

            var tr = $('<tr></tr>');

            tr.append('<th scope="row">' + el.id + '</th>');
            tr.append('<td><a href="#?' + el.id + '">'+ el.firstName +'</a></td>');
            tr.append('<td>' + el.lastName + '</td>');

            $('#instructors-list').append(tr);

        })
    }).fail(function (err) {
        alert(err);
    })

});