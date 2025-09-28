<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Category</title>
    <style>
        form { width: 400px; margin: 20px auto; display: flex; flex-direction: column; gap: 10px; }
        label { font-weight: bold; }
        input, select { padding: 8px; }
        button { padding: 10px; background: #28a745; color: white; border: none; cursor: pointer; }
        button:hover { background: #1e7e34; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Edit Category</h2>
    <form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="categoryid" value="${cate.categoryId}" />

        <label>Name:</label>
        <input type="text" name="categoryname" value="${cate.categoryname}" required />

        <label>Status:</label>
        <select name="status">
            <option value="1" ${cate.status==1 ? 'selected' : ''}>Active</option>
            <option value="0" ${cate.status==0 ? 'selected' : ''}>Inactive</option>
        </select>

        <label>Image:</label>
        <input type="file" name="images" />
        <c:if test="${cate.images != null}">
            <p>Current: <img src="<c:url value='/image?fname=${cate.images}'/>" width="100"/></p>
        </c:if>

        <button type="submit">Update</button>
    </form>
</body>
</html>
