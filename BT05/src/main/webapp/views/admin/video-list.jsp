<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Video List</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 10px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        th { background: #007bff; color: white; }
        a.btn { padding: 5px 10px; border-radius: 3px; color: white; text-decoration: none; }
        a.edit { background: #28a745; }
        a.delete { background: #dc3545; }
        a.add { background: #007bff; margin-top: 10px; display: inline-block; }
        form.search { margin-bottom: 10px; }
        form.search input { padding: 5px; }
        form.search button { padding: 6px 12px; }
    </style>
</head>
<body>
    <h2>Video List</h2>

    <!-- Ô search -->
    <form class="search" action="<c:url value='/admin/video/search'/>" method="get">
        <input type="text" name="keyword" placeholder="Search by title"/>
        <button type="submit">Search</button>
    </form>

    <a class="btn add" href="<c:url value='/admin/video/add'/>">+ Add Video</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Poster</th>
            <th>Views</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listvideo}" var="v">
            <tr>
                <td>${v.videoId}</td>
                <td>${v.title}</td>
                <td>
                    <c:if test="${v.poster != null}">
                        <img src="<c:url value='/image?fname=${v.poster}'/>" width="120"/>
                    </c:if>
                </td>
                <td>${v.views}</td>
                <td>${v.category.categoryname}</td>
                <td>
                    <a class="btn edit" href="<c:url value='/admin/video/edit?id=${v.videoId}'/>">Edit</a>
                    <a class="btn delete" href="<c:url value='/admin/video/delete?id=${v.videoId}'/>"
                       onclick="return confirm('Bạn có chắc muốn xóa video này?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
