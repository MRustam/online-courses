$(document).ready(function () {

    $.get('/api/courses/all', function (data) {
        $.each(data, function (i, el) {

            var tr = $("<tr></tr>");

            tr.append("<th scope='row'>" + el.id + "</th>");
            tr.append("<td>" + el.title + "</td>");
            tr.append("<td>" + el.category + "</td>");
            tr.append("<td>" + el.description + "</td>");
            tr.append("<td>" + el.startDate + "</td>");
            tr.append("<td>" + el.rating + "</td>");
            $("#course").append(tr);

        })
    }).fail(function (err) {
        alert(err);
    })

});