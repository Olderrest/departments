<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="jspf/bootstrap.jspf"/>
    <script type="text/javascript" src="/js/sorttable.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/styles/style.css"/>
    <title>Departments</title>
</head>
<body>
<div id="department-table">
    <h3>Departments</h3>
    <table id="sortable" class="table table-condensed table-hover">
        <thead>
        <tr>
            <th>Name of department</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td><c:out value="${department.name}"/></td>
                <td><a href="<c:url value="/depart_update?id=${department.id}"/>" class="btn btn-success">Редактировать</a></td>
                <td><a href="<c:url value="/depart_delete?id=${department.id}"/>" class="btn btn-danger">Удалить</a></td>
                <td><a href="<c:url value="/employees?id=${department.id}"/>" class="btn btn-info">Список сотрудников</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <div id="btn">
        <a href="<c:url value="/add_depart"/>" class="btn btn-primary">Добавить</a>
    </div>
</div>
</body>
</html>
