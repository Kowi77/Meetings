var ajaxUrl = "user/";
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
            {"data": "firstName"},
            {"data": "lastName"},
            {"data": "birthday"},
            {"data": "login"},
            {"data": "password"},
            {"data": "info",
                "sHeightMatch": "auto"},
            {"data": "adress"},
            {"orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {"orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
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
