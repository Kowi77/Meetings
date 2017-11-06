var ajaxUrl = "meeting/";
var form=$('#meetingForm');
departId = 1;
var firstId;
var members;
var mems = [];

// Значение по умолчанию поля департамент, заполнение ответственного за него и списка участников
$(document).ready(function() {
    if ($("#depart").val() != ""){
        departId = $("#depart").val();
        getMembers($("#id").val());
    } else {
        departId = 1;
        $("#depart").val(1)
    }
    $("option[value=" + departId + "]").prop("selected", true);
    refreshSelectEmployer(departId);
})

// Обработчик выбора департамента
$("#selectDepart").on('change', function () {
    departId = $('#selectDepart :selected').val()
    $("#depart").val(departId);
    refreshSelectEmployer(departId);

});

//Добавление участника совещания
function addMember () {
    id = $("#selectMember :selected").val();
    members = (members == undefined ? [] : members);
    members.forEach(function (mem) {
        if(mem.id == id){
            errorNoty("Этот работник уже участвует в совещании!");
            id = -1;
        }
    });
    if (id == -1)
        return;
    $.ajax({
        type: "GET",
        url: ajaxUrl + "employerForUiById/" + id,
        dataType: 'json',
        success: function (data) {
            members.push(data);
           refreshMembers(members);
        }
    });
};

//Обновляем список возможных ответственных
function refreshSelectEmployer(departId) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "employersByDepart/" + departId,
        dataType: 'json',
        success: function (data) {
            if (firstId != 0) {
                firstId = data[0].id;
                $("#employer").val(firstId);
            }
            $("#selectEmployer").empty();
            data.forEach(function (emp) {
                $("#selectEmployer").append("<option value=" + emp.id + ">" + emp.fullname) + "</option>"
            });
        }
    });
}

//Получаем список участников
function getMembers(meetId) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "membersByMeet/" + meetId,
        dataType: 'json',
        success: function (data) {
            members = data;
            refreshMembers(data);
        }
    });
}

//Обновляем список участников
function refreshMembers(data) {
    $("#datatableMembers > tbody").empty();
    console.log(data);
    data.forEach(function (emp) {
        $("#datatableMembers > tbody").append("<tr><td>" + emp.fullname + "</td><td>" + getAge(emp.birthday) +
            "</td><td>" + emp.departName + "</td><td><a onclick='deleteMember(" + emp.id + ")'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Удалить</a></td></tr>");
    });
}
//
//Удаляем из списка участников
function deleteMember (id) {
    members.forEach(function (m) {
        if (m.id == id){
            console.log(members);
            members.splice(members.indexOf(m), 1);
            console.log(members);
            refreshMembers(members);
            return;
        }
    })
}

//Сохранение данных о совещании
function save() {
    members.forEach(function (m) {
        mems.push(m.id);
    })

    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: [form.serialize(), $.param({mems: mems})].join('&'),
        error: function () {
            errorHandling();
        },
        success: function () {
            successNoty("Данные о совещании сохранены!");
            history.back();
        }
    });
}

//Конвертация даты рождения
function getAge(birthday) {
    var ageDate = new Date(Date.now() - new Date(birthday).getTime());
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

//Сообщения пользователю
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



