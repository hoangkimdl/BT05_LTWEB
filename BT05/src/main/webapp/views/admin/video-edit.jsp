<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Video</title>
    <style>
        form { width: 500px; margin: 20px auto; display: flex; flex-direction: column; gap: 10px; }
        label { font-weight: bold; }
        input, textarea, select { padding: 8px; }
        button { padding: 10px; background: #28a745; color: white; border: none; cursor: pointer; }
        button:hover { background: #1e7e34; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Edit Video</h2>
    <form action="<c:url value='/admin/video/update'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="videoid" value="${video.videoId}" />

        <label>Title:</label>
        <input type="text" name="title" value="${video.title}" required />

        <label>Description:</label>
        <textarea name="description" rows="3">${video.description}</textarea>

        <label>Category:</label>
        <select name="categoryid">
            <c:forEach items="${listcate}" var="c">
                <option value="${c.categoryId}" ${video.category.categoryId == c.categoryId ? 'selected' : ''}>${c.categoryname}</option>
            </c:forEach>
        </select>

        <label>Poster:</label>
        <input type="file" name="poster" />
        <c:if test="${video.poster != null}">
            <p>Current: <img src="<c:url value='/image?fname=${video.poster}'/>" width="120"/></p>
        </c:if>

        <label>Status:</label>
        <select name="active">
            <option value="1" ${video.active == 1 ? 'selected' : ''}>Active</option>
            <option value="0" ${video.active == 0 ? 'selected' : ''}>Inactive</option>
        </select>

        <button type="submit">Update</button>
    </form>
</body>
</html>
