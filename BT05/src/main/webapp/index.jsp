<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BT05 - Admin Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #e3f2fd, #ffffff);
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            padding: 30px;
            font-size: 28px;
            color: #2c3e50;
            margin: 0;
        }
        .container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 30px;
            max-width: 1000px;
            margin: 0 auto;
            padding: 40px;
        }
        .card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            padding: 30px 20px;
            text-align: center;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .card:hover {
            transform: translateY(-8px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }
        .card h3 {
            margin: 15px 0 10px;
            font-size: 22px;
            color: #34495e;
        }
        .card a {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 18px;
            background: #007bff;
            color: #fff;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            transition: background 0.3s;
        }
        .card a:hover {
            background: #0056b3;
        }
        .icon {
            font-size: 40px;
            color: #007bff;
        }
    </style>
    <!-- Font Awesome cho icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <h2>🚀 Admin Dashboard - BT05 Project</h2>
    <div class="container">
        <div class="card">
            <div class="icon"><i class="fa fa-list"></i></div>
            <h3>Manage Categories</h3>
            <a href="<c:url value='/admin/categories'/>">Go to Categories</a>
        </div>
        <div class="card">
            <div class="icon"><i class="fa fa-users"></i></div>
            <h3>Manage Users</h3>
            <a href="<c:url value='/admin/users'/>">Go to Users</a>
        </div>
        <div class="card">
            <div class="icon"><i class="fa fa-video"></i></div>
            <h3>Manage Videos</h3>
            <a href="<c:url value='/admin/videos'/>">Go to Videos</a>
        </div>
    </div>
</body>
</html>
