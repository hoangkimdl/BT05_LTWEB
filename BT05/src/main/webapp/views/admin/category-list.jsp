<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List</title>
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
	<h2>Category List</h2>
	<!-- Ô search -->
	<form action="<c:url value='/admin/category/search'/>" method="get"
		style="margin-bottom: 10px;">
		<input type="text" name="keyword"
			placeholder="Search by category name" />
		<button type="submit">Search</button>
	</form>
	<a class="btn add" href="<c:url value='/admin/category/add'/>">+
		Add Category</a>
	<table>
		<tr>
			<th>#</th>
			<th>Image</th>
			<th>Name</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="cate" items="${listcate}" varStatus="stt">
			<tr>
				<td>${stt.index + 1}</td>
				<td><c:if test="${cate.images != null}">
						<img src="<c:url value='/image?fname=${cate.images}'/>"
							width="100" />
					</c:if></td>
				<td>${cate.categoryname}</td>
				<td><c:choose>
						<c:when test="${cate.status == 1}">Active</c:when>
						<c:otherwise>Inactive</c:otherwise>
					</c:choose></td>
				<td><a class="btn edit"
					href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Edit</a>
					<a class="btn delete"
					href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>"
					onclick="return confirm('Bạn có chắc muốn xóa?')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
