var ajaxUrl = "meetings/";
var datatableApi;
var form=$('#detailsForm');

$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            var json = JSON.parse(stringData);
            $(json).each(function () {
                this.date = this.date.replace('T', ' ').substr(0, 16);
                console.log(this.employer);
                n = this.employer.split(' ');
                this.employer = n[0] + " " + n[1].substr(0,1) + ". " + n[2].substr(0, 1) + ".";
                console.log(this.employer);
            });
            return json;
        }
    }
});

function updateTable() {
    $.ajax({
        type: "GET",
        url: ajaxUrl,
        dataType: 'json',
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
        }
    });
}

function filterByDepart(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "depart/" + id,
        success: updateTableByData
    });
}

function filterByEmployer(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "employer/" + id,
        success: updateTableByData
    });
}

function filterByMember(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "member/" + id,
        success: updateTableByData
    });
}

function filterByDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "date/",
        data: $("#dateFilter").serialize(),
        success: updateTableByData
    });
}

//Отрисовка таблицы отфильтрованными данными
function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

//Datatable
$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "columns": [
            {"data": "date"},
            {"data": "theme",
                "render": function (data, type, row) {return "<a href=meeting/" + row.id + "/>" + data + ""}},
            {"data": "depart"},
            {"data": "employer"},
            {"data": "quantity"}
        ],
        "order": [[0,"asc"]],
        "initComplete": errorHandling
    });
});

//User's noties creating

function errorHandling() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        errorNoty(jqXHR.status, jqXHR.responseText);
    });
}

function successNoty(message) {
    $("#success").css({"display" : ""});
    $("#success").html(message);
    setTimeout(function(){$("#success").css({"display" : "none"})}, 5000);
}

function errorNoty(status, respounce) {
    $("#error").css({"display" : ""});
    $("#error").html("Статус ошибки: " + status + "<br>" + respounce);
    setTimeout(function(){$("#error").css({"display" : "none"})}, 7000);
}
