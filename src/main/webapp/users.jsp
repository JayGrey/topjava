<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>

current userId is: ${userId}
<p>
select another user
<form action="users" method="post">
    <select name="user_id">
        <option value="1">user 1</option>
        <option value="2">user 2</option>
    </select>
    <input type="submit" value="Submit">
</form>
</p>
</body>
</html>