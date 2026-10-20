<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Details</title>
</head>
<body>

    <h2>Student Information</h2>

    <%-- Check if the students list exists and is not empty --%>
    <c:choose>
        <c:when test="${not empty students}">
            <p>Found ${students.size()} record(s) matching your search:</p>
            <table border="1" cellpadding="10" cellspacing="0">
                <tr>
                    <th>Full Name</th>
                    <th>Date of Birth</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Class / Grade</th>
                    <th>Address</th>
                </tr>
                <c:forEach var="s" items="${students}">
                    <tr>
                        <td>${s.fullName}</td>
                        <td>${s.dob}</td>
                        <td>${s.gender}</td>
                        <td>${s.email}</td>
                        <td>${s.phone}</td>
                        <td>${s.classNumber}</td>
                        <td>${s.address}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p style="color: red;">No student details found or invalid search.</p>
        </c:otherwise>
    </c:choose>

    <br>
    <a href="existingStudents.html">Back to Search</a>

</body>
</html>