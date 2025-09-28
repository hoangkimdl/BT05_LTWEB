<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Video</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        form { width: 500px; margin: 20px auto; display: flex; flex-direction: column; gap: 12px; }
        label { font-weight: bold; }
        input, textarea, select { padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        button { padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background: #0056b3; }
        .back { margin-top: 15px; display: inline-block; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Add New Video</h2>

    <form action="<c:url value='/admin/video/insert'/>" method="post" enctype="multipart/form-data">
        <label for="videoid">Video ID:</label>
        <input type="text" id="videoid" name="videoid" required />

        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required />

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="3"></textarea>

        <label for="categoryid">Category:</label>
        <select id="categoryid" name="categoryid" required>
            <c:forEach items="${listcate}" var="c">
                <option value="${c.categoryId}">${c.categoryname}</option>
            </c:forEach>
        </select>

        <label for="poster">Poster:</label>
        <input type="file" id="poster" name="poster" accept="image/*" />

        <label for="active">Status:</label>
        <select id="active" name="active">
            <option value="1">Active</option>
            <option value="0">Inactive</option>
        </select>

        <button type="submit">Save</button>
    </form>

    <div class="back">
        <a href="<c:url value='/admin/videos'/>">← Back to Video List</a>
    </div>
</body>
</html>
