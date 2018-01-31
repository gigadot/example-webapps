<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <h2>My name is <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h2>
    </body>
</html>
