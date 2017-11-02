<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование данных совещания</title>
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
        <div class="col-xs-4">
            <input type="datetime-local" class="form-control" id="date" name="date" value="${meeting.date}">
        </div>
    </div>

    <div class="form-group">
        <label for="depart" class="control-label col-xs-3">Подразделение</label>
        <div class="col-xs-4">
            <div class="select-and-input">
                <select name="selectName" onchange="parentNode.getElementsByTagName('input')[0].value=value">
                    <option value="111">222</option>
                    <c:forEach var="dep" items="${departs}">
                        <option value="${dep.name}">${dep.name}22222</option>
                    </c:forEach>
                </select>
                <input type="text" id="depart" name="depart" value=${meeting.employerId}>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="employer" class="control-label col-xs-3">Ответственный</label>
        <div class="col-xs-4">
            <div class="select-and-input">
                <select name="selectName" onchange="parentNode.getElementsByTagName('input')[0].value=value">
                    <c:forEach var="empl" items="${allEmployers}">
                        <option value="${empl.fullname}">${empl.fullname}</option>
                    </c:forEach>
                </select>
                <input type="text" id="employer" name="employer" value=${meeting.employerId}>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="member" class="control-label col-xs-3">Участник</label>
        <div class="col-xs-4">
            <div class="select-and-input">
                <select name="selectName" onchange="parentNode.getElementsByTagName('input')[0].value=value">
                    <c:forEach var="depart" items="${departs}">
                        <option value="${depart.name}">${depart.name}</option>
                    </c:forEach>
                </select>
                <input type="text" id="member" name="member" value=${meeting.employerId}>
            </div>
        </div>
    </div>


    <div class="container-fluid">
        <h4>Состав участников</h4>
        <table class="table table-striped display" id="dataEmployers">
            <thead>
            <tr>
                <th hidden>Id</th>
                <th>Имя</th>
                <th>Дата рождения</th>
                <th>Подразделение</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <td>
                <td hidden>
            </td>
            </tbody>
        </table>
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
