<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f0f0f0;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 360px;
        }

        form {
            padding: 20px;
        }

        h2 {
            margin: 0 0 20px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Include padding and border in the width calculation */
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }

        .register-link, .login-link {
            color: #007bff;
            text-decoration: none;
        }

        .register-link:hover, .login-link:hover {
            text-decoration: underline;
        }
    </style>
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
        <h2>Sign in</h2>
        <% String errorMessage = (String) session.getAttribute("notify");
            if (errorMessage != null) {
        %>
        <p class="error-message" style="color: red; font-size: 14px;margin-top: 5px;"><%= errorMessage %>
        </p>
        <% session.setAttribute("notify", null);
        } %>
        <input type="text" placeholder="Username" name="username" required>
        <input type="password" placeholder="Password" name="password" required>
        <button type="submit">Login</button>
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/register"
                                     class="register-link">Register</a></p>
    </form>
</div>
</body>
</html>
