<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <jsp:include page="jspf/bootstrap.jspf"/>
    <link rel="stylesheet" type="text/css" media="screen" href="/styles/style.css"/>
    <title>Add employee</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<div id="add">
    <h4>Add new employee to department</h4>
    <form action="add_employee" method="post">
        <input type="hidden" name="departmentId" value="${departmentId}"/>
        <div class="form-group">
            <label for="firstName">First name</label><p style="color: red"><c:out value="${errors.firstNameError}"/></p>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${firstName}" required="required"/>
            <label for="lastName">Last name</label><p style="color: red"><c:out value="${errors.lastNameError}"/></p>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${lastName}" required="required"/>
            <label for="mail">Mail</label><p style="color: red"><c:out value="${errors.emailError}"/></p>
            <input type="email" class="form-control" id="mail" name="mail" value="${mail}" required="required"/>
            <label for="salary">Salary</label><p style="color: red"><c:out value="${errors.salaryError}"/></p>
            <input type="number" class="form-control" id="salary" name="salary" value="${salary}" required="required"/>
            <label for="hiring_day">Hiring day</label><p style="color: red"><c:out value="${errors.dateError}"/></p>
            <input type="date" class="form-control" id="hiring_day" name="hiringDay" value="<fmt:formatDate value="${hiringDay}"  pattern="dd.MM.yyyy" />" required="required"/>
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>
</body>
</html>
