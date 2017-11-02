var ajaxUrl = "meeting/";
var datatableApi;
var form=$('#meetingForm');

//Таблица с участниками совещания
$(function () {
    datatableApi = $("#dataEmployers").DataTable({
        "ajax": {
            "url": ajaxUrl + "employers/",
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "columns": [
            {"data": "id",
                "name":"qwerty"
            },
            {"data": "fullname"},
            {"data": "birthday"},
            {"data": "departId"},
            {
                "render": renderDeleteBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [[0,"asc"]],
        "initComplete": errorHandling
    });
});

function renderDeleteBtn(data, type, row) {
        return "<a onclick='deleteRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + "employer/" + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Запись удалена!");
        }
    });
}
function updateTable() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "employers/",
        dataType: 'json',
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function errorHandling() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        errorNoty(jqXHR.status, jqXHR.responseText);
    });
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        error: function () {
            errorNoty("Error!!!")
        },
        success: function () {
            successNoty("Данные о совещании сохранены!");
            //TODO
        }
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



