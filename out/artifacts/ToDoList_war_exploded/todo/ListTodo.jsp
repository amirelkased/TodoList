<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body, h1, h2, h3, p, ul, li {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .btn {
            display: inline-block;
            padding: 10px 15px;
            font-size: 14px;
            background-color: #007bff; /* Blue color for "New ToDo" button */
            color: #fff;
            border: none;
            cursor: pointer;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Status styles */
        .status-completed {
            color: #28a745; /* Green color for completed status */
            font-weight: bold;
        }

        .status-in-progress {
            color: #007bff; /* Blue color for in-progress status */
            font-weight: bold;
        }

        .status-pending {
            color: #ffc107; /* Yellow color for pending status */
            font-weight: bold;
        }

        .actions {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button-container {
            display: flex;
            gap: 5px;
        }

        .actions button {
            margin-right: 5px;
            background-color: transparent;
            border: 1px solid #ddd;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        /* Button colors */
        .edit {
            background-color: #28a745; /* Green color for "Edit" button */
        }

        .edit:hover {
            background-color: #218838;
        }

        .delete {
            background-color: #dc3545; /* Red color for "Delete" button */
        }

        .delete:hover {
            background-color: #c82333;
        }

        td.status-cell {
            width: 15%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
    <title>To-Do List</title>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>To-Do List</h1>
        <a href="${pageContext.request.contextPath}/todo/new">
            <button class="btn">New ToDo</button>
        </a>
        <a href="${pageContext.request.contextPath}/logout">
            <button class="btn">Log Out</button>
        </a>
    </div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Deadline</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty sessionScope.todoList}">
            <c:set var="i" value="1"/>
            <c:forEach items="${sessionScope.todoList}" var="task">
                <c:set var="taskId" value="${task.getTodo_id()}"/>
                <c:set var="title" value="${task.getTitle()}"/>
                <c:set var="description" value="${task.getDescription()}"/>
                <c:set var="date" value="${task.getDate()}"/>
                <c:set var="status" value="${task.getStatus()}"/>
                <tr>
                    <td>${i}</td>
                    <td>${title}</td>
                    <td>${description}</td>
                    <td>${date}</td>
                    <td class="status-cell status-${status.toLowerCase()}">${status}</td>
                    <td class="actions">
                        <div class="button-container">
                            <a href="${pageContext.request.contextPath}/todo/update?id=${taskId}">
                                <button class="edit">Edit</button>
                            </a>
                            <a href="${pageContext.request.contextPath}/todo/delete?id=${taskId}">
                                <button class="delete">Delete</button>
                            </a>
                        </div>
                    </td>
                </tr>
                <c:set var="i" value="${i + 1}"/>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>