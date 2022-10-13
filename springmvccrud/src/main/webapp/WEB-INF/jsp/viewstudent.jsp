
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<%@ page isELIgnored="false"%>
</head>
<body>
	<h1>Student List</h1>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Marks</th>
			<th>Department</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="stu" items="${slist}">
			<tr>
				<td>${stu.id}</td>
				<td>${stu.name}</td>
				<td>${stu.marks}</td>
				<td>${stu.department}</td>
				<td><a href="editstudent/${stu.id}">Edit</a></td>
				<td><a href="deletestudent/${stu.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="studentform">Add New Student</a>
</body>
</html>
