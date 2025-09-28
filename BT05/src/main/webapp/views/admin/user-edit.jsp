<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <style>
        form { width: 400px; margin: 20px auto; display: flex; flex-direction: column; gap: 10px; }
        label { font-weight: bold; }
        input, select { padding: 8px; }
        button { padding: 10px; background: #28a745; color: white; border: none; cursor: pointer; }
        button:hover { background: #1e7e34; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Edit User</h2>
    <form action="<c:url value='/admin/user/update'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="userid" value="${user.userId}" />

        <label>Username:</label>
        <input type="text" name="username" value="${user.username}" readonly />

        <label>Fullname:</label>
        <input type="text" name="fullname" value="${user.fullname}" />

        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" />

        <label>Role:</label>
        <select name="role">
            <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
            <option value="user" ${user.role == 'user' ? 'selected' : ''}>User</option>
        </select>

        <label>Status:</label>
        <select name="status">
            <option value="1" ${user.status == 1 ? 'selected' : ''}>Active</option>
            <option value="0" ${user.status == 0 ? 'selected' : ''}>Locked</option>
        </select>

        <label>Avatar:</label>
        <input type="file" name="avatar" />
        <c:if test="${user.avatar != null}">
            <p>Current: <img src="<c:url value='/image?fname=${user.avatar}'/>" width="100"/></p>
        </c:if>

        <button type="submit">Update</button>
    </form>
</body>
</html>
