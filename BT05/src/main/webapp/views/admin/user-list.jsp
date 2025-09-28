<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<style>
body {
	font-family: Arial, sans-serif;
	padding: 20px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
}

th {
	background: #007bff;
	color: white;
}

a.btn {
	padding: 5px 10px;
	border-radius: 3px;
	color: white;
	text-decoration: none;
}

a.edit {
	background: #28a745;
}

a.delete {
	background: #dc3545;
}

a.add {
	background: #007bff;
	margin-bottom: 10px;
	display: inline-block;
}
</style>
</head>
<body>
	<h2>User List</h2>
	<!-- Ô search -->
	<form action="<c:url value='/admin/user/search'/>" method="get"
		style="margin-bottom: 10px;">
		<input type="text" name="keyword" placeholder="Search by username" />
		<button type="submit">Search</button>
	</form>
	<a class="btn add" href="<c:url value='/admin/user/add'/>">+ Add
		User</a>
	<table>
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Fullname</th>
			<th>Email</th>
			<th>Role</th>
			<th>Status</th>
			<th>Avatar</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${listuser}" var="u">
			<tr>
				<td>${u.userId}</td>
				<td>${u.username}</td>
				<td>${u.fullname}</td>
				<td>${u.email}</td>
				<td>${u.role}</td>
				<td><c:choose>
						<c:when test="${u.status == 1}">Active</c:when>
						<c:otherwise>Locked</c:otherwise>
					</c:choose></td>
				<td><c:if test="${u.avatar != null}">
						<img src="<c:url value='/image?fname=${u.avatar}'/>" width="80" />
					</c:if></td>
				<td><a class="btn edit"
					href="<c:url value='/admin/user/edit?id=${u.userId}'/>">Edit</a> <a
					class="btn delete"
					href="<c:url value='/admin/user/delete?id=${u.userId}'/>"
					onclick="return confirm('Bạn có chắc muốn xóa?')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
