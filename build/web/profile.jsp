<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>Welcome, ${user.name}!</h1>
    <img src="${user.picture}" alt="Profile Picture" />
    <p>Email: ${user.email}</p>
</body>
</html>
