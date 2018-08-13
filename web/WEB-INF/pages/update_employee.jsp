<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="jspf/bootstrap.jspf"/>
    <link rel="stylesheet" type="text/css" media="screen" href="/styles/style.css"/>
    <title>Update employee</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<div id="update_employee">
    <br>
    <h4>Updating employee</h4>
    <form action="update_employee" method="post">
        <input type="hidden" name="id" value="${id}"/>
        <div class="form-group">
            <label for="firstName">First name</label><p style="color: red"><c:out value="${errors.firstNameError}"/></p>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${firstName}" required="required"/>
            <label for="lastName">Last name</label><p style="color: red"><c:out value="${errors.lastNameError}"/></p>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${lastName}" required="required"/>
            <label for="email">Mail</label><p style="color: red"><c:out value="${errors.emailError}"/></p>
            <input type="email" class="form-control" id="email" name="email" value="${email}" required="required"/>
            <label for="salary">Salary</label><p style="color: red"><c:out value="${errors.salaryError}"/></p>
            <input type="number" class="form-control" id="salary" name="salary" value="${salary}" required="required"/>
            <label for="department">Department</label><select class="form-control" name="department" id="department">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}"><c:out value="${department.name}"/></option>
            </c:forEach>
        </select>
        </div>
        <button type="submit" class="btn btn-primary">Обновить</button>
    </form>
</div>
</body>
</html>
