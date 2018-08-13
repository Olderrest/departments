<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/js/sorttable.js"></script>
    <jsp:include page="jspf/bootstrap.jspf"/>
    <link rel="stylesheet" type="text/css" media="screen" href="/styles/style.css"/>
    <title>Employees</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div id="employees-list">
    <h4>Department of ${department.name}</h4>
    <c:choose>
        <c:when test="${not empty employees}">
            <table id="sortable" class="table table-condensed table-hover">
                <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Salary($)</th>
                    <th>Hiring day</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td><c:out value="${employee.firstName}"/></td>
                        <td><c:out value="${employee.lastName}"/></td>
                        <td><c:out value="${employee.email}"/></td>
                        <td><c:out value="${employee.salary}"/></td>
                        <td><c:out value="${employee.hiringDay}"/></td>
                        <td width="50px">
                            <a href="<c:url value="/employee_update?id=${employee.id}"/>" class="btn btn-success">Редактировать</a>
                        </td>
                        <td width="50px">
                            <a href="<c:url value="/employee_delete?id=${employee.id}&department=${employee.departmentId}"/>"
                               class="btn btn-danger">Удалить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h3>Department of ${department.name} don't have any employees</h3>
        </c:otherwise>
    </c:choose>
    <br>
    <div id="btn">
        <a href="<c:url value="/add?departmentId=${department.id}"/>" class="btn btn-primary">Добавить</a>
        <a href="<c:url value="/employees?id=${department.id}"/>" class="btn btn-info">Список сотрудников</a>
    </div>
</div>
</body>
</html>
