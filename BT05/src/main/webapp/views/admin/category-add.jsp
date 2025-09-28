<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Category</title>
    <style>
        form { width: 400px; margin: 20px auto; display: flex; flex-direction: column; gap: 10px; }
        label { font-weight: bold; }
        input, select { padding: 8px; }
        button { padding: 10px; background: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background: #0056b3; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Add New Category</h2>
    <form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">
        <label>Name:</label>
        <input type="text" name="categoryname" required />

        <label>Status:</label>
        <select name="status">
            <option value="1">Active</option>
            <option value="0">Inactive</option>
        </select>

        <label>Image:</label>
        <input type="file" name="images" />

        <button type="submit">Save</button>
    </form>
</body>
</html>
