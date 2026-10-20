<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
</head>
<body>
    <% String name = (String)session.getAttribute("fullName"); %>
    <h1>Registration of <%= name%> is successfully completed</h1>
    <h2><label>You can search student to get full details</label></h2>
    <a href = "existingStudents.html"><input type = "button" value = "Search Students"></a>
</body>
</html>