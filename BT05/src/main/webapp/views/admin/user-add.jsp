<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <style>
        form { width: 400px; margin: 20px auto; display: flex; flex-direction: column; gap: 10px; }
        label { font-weight: bold; }
        input, select { padding: 8px; }
        button { padding: 10px; background: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background: #0056b3; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Add New User</h2>
    <form action="<c:url value='/admin/user/insert'/>" method="post" enctype="multipart/form-data">
        <label>Username:</label>
        <input type="text" name="username" required />

        <label>Password:</label>
        <input type="password" name="password" required />

        <label>Fullname:</label>
        <input type="text" name="fullname" />

        <label>Email:</label>
        <input type="email" name="email" />

        <label>Role:</label>
        <select name="role">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>

        <label>Status:</label>
        <select name="status">
            <option value="1">Active</option>
            <option value="0">Locked</option>
        </select>

        <label>Avatar:</label>
        <input type="file" name="avatar" />

        <button type="submit">Save</button>
    </form>
</body>
</html>
