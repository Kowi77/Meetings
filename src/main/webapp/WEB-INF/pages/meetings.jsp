<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Обзор проведенных совещаний</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="resources/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.15/media/css/dataTables.bootstrap.min.css">

    <script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js" defer></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/dataTables.bootstrap.min.js" defer></script>
    <script type="text/javascript" src="resources/meetingsUtils.js" defer></script>
</head>
<body>
<div class="container-fluid">
    <div class="col-sm-8">
        <form class="form-horizontal" id="dateFilter">
            <%-- <div class="form-group">--%>
            <label class="control-label col-sm-1" for="startDate">Время проведения с: </label>
            <div class="col-sm-3">
                <input class="form-control" type="datetime-local" name="startDate" id="startDate">
            </div>
            <label class="control-label col-sm-1" for="endDate"> по: </label>
            <div class="col-sm-3">
                <input class="form-control" type="datetime-local" name="endDate" id="endDate">
            </div>
        </form>
        <a class="btn btn-danger" type="button" onclick="clearFilter()">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"> Показать все</span>
        </a>
        <a class="btn btn-primary" type="button" onclick="updateTable()">
            <span class="glyphicon glyphicon-filter" aria-hidden="true"> Применить фильтр</span>
        </a>
    </div>
    <div class="col-sm-4">
        <div class="dropdown col-sm-2" id="departFilter">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Выбор подразделения
                <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <c:forEach var="depart" items="${departs}">
                    <li><a onclick="filterByDepart(${depart.id})">${depart.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="dropdown col-sm-2" id="eployerFilter">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">С участием
                <span class="caret"></span></button>
            <ul class="dropdown-menu"
            <c:forEach var="emp" items="${employers}">
                <li><a onclick="filterByEmployer(${emp.id})">${emp.fullname}</a></li>
            </c:forEach>
            </ul>
        </div>
    </div>
</div>


<div class="container-fluid">
        <h7>Список проведенных совещаний</h7>
        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th>Дата и время</th>
                <th>Тема</th>
                <th>Подразделение</th>
                <th>Ответственный</th>
                <th>Состав</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
</div>

<a class="btn btn-primary" href="meeting/0">
    <span class="glyphicon glyphicon-plus" aria-hidden="true">  ДОБАВИТЬ</span>
</a>

<div id="success" style="display: none" class="alert alert-success"></div>
<div id="error" style="display: none" class="alert alert-danger"></div>
</body>
</html>
