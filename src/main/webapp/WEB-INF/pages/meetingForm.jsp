<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Данные о совещании</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="resources/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.15/media/css/dataTables.bootstrap.min.css">

    <script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js" defer></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/dataTables.bootstrap.min.js" defer></script>
    <%--<script type="text/javascript" src="resources/meetingsUtils.js" defer></script>--%>
    <script type="text/javascript" src="resources/formUtils.js" defer></script>
</head>
<body>
<form class="form-horizontal" id="meetingForm">
    <input type="hidden" id="id" name="id" value="${meeting.id}">

    <div class="form-group">
        <label for="theme" class="control-label col-xs-3">Тема совещания</label>
        <div class="col-xs-6">
            <input type="text" class="form-control" id="theme" name="theme" value="${meeting.theme}">
        </div>
    </div>
    <div class="form-group">
        <label for="date" class="control-label col-xs-3">Время проведения</label>
        <div class="col-xs-3">
            <input type="datetime-local" class="form-control" id="date" name="date" value="${meeting.date}">
        </div>
    </div>

    <div class="form-group">
        <label for="selectDepart" class="control-label col-xs-3">Подразделение</label>
        <div class="col-xs-6">
            <div class="select-and-input">
                <input type="text" id="depart" name="depart" value="${currentDepartId}">
                <select name="selectDepart" id="selectDepart">
                    <c:forEach var="dep" items="${departs}">
                        <option value="${dep.id}">${dep.name}</option>");
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="selectEmployer" class="control-label col-xs-3">Ответственный</label>
        <div class="col-xs-6">
            <div class="select-and-input">
                <input type="text" id="employer" name="employer" value=${meeting.employerId}>
                <select name="selectEmployer" id="selectEmployer" onchange="parentNode.getElementsByTagName('input')[0].value=value">
                </select>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <h4>Состав участников</h4>
        <table class="table table-striped display" id="datatableMembers">
            <thead>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Дата рождения</th>
                <th>Подразделение</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="emp" items="${employersOfMeeting}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.fullname}</td>
                    <td>${emp.birthday}</td>
                    <td>${emp.departName}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <div class="form-group">
        <label for="selectMember" class="control-label col-xs-3">Участник</label>
        <div class="col-xs-6">
            <div class="select-and-input">
                <form id="memberForm">
                    <select name="selectMember" id="selectMember">
                        <c:forEach var="emp" items="${allEmployers}">
                            <option value="${emp.id}">${emp.fullname}</option>
                        </c:forEach>
                    </select>
                    <a class="btn btn-primary" type="button" onclick="addMember()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"> Добавить участника</span>
                    </a>
                </form>
            </div>
        </div>
    </div>

    <div class="col-xs-offset-3 col-xs-9">
        <button class="btn btn-primary" type="button" onclick="save()">
            <span class="glyphicon glyphicon-ok" aria-hidden="true">Сохранить</span>
        </button>
    </div>
</form>
<div class="col-xs-offset-3 col-xs-9">
    <form action="/">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-ok" aria-hidden="true">Не сохранять</span>
        </button>
    </form>
</div>




<div id="success" style="display: none" class="alert alert-success"></div>
<div id="error" style="display: none" class="alert alert-danger"></div>

</body>
</html>
