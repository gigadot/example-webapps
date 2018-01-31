<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>This is admin page.</h2>
<% 
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
if (firstName != null && lastName != null) {
    out.println("Hello, " + firstName + " " + lastName);
} else {
    out.println("firstName and lastName parameters not found");
}
%>
</body>
</html>
