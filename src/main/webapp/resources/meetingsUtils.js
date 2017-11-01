var ajaxUrl = "meetings/";
var datatableApi;
var form=$('#detailsForm');

//Prepare for add/edit user

function add() {
    $("#modalTitle").html("Добавление пользователя");
    $("#detailsForm").find(":input").val("");
    $("#editRow").modal();
}


function updateRow(id) {
    $("#modalTitle").html("Редактирование пользователя");
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

//Save/delete user

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data:form.serialize(),
        success: function () {
            $("#editRow").modal("hide");
             updateTable();
             successNoty("Пользователь сохранен");
        }
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Пользователь удален");
        }
    });
}

//Update datatable

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
            {"data": "date",
                "sHeightMatch": "auto"},
            {"data": "theme",
                "render": function (data, type, row) {return "<a href=meeting/" + row.id + "/>" + data + ""}},
            {"data": "employerId"},
            {"data": "employerId"},
            {"data": "employerId"}
        ],
        "order": [[0,"asc"]],
        "initComplete": errorHandling
    });
});

//Edit and delete impl

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
        return "<a onclick='deleteRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
}

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