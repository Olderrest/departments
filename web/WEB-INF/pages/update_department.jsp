<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="jspf/bootstrap.jspf"/>
    <link rel="stylesheet" type="text/css" media="screen" href="/styles/style.css"/>
    <title>Update department</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div id="add_depart">
    <h4>Add new department</h4>
    <form action="update_department" method="post">
        <input type="hidden" name="departmentId" value="${department.id}"/>
        <div class="form-group">
            <label for="name">Name of department</label><p style="color: red"><c:out value="${errors.departmentNameError}"/></p>
            <input type="text" class="form-control" id="name" name="name" value="${name}" required="required"/>
        </div>
        <button type="submit" class="btn btn-primary">Обновить</button>
    </form>
</div>
</body>
</html>
