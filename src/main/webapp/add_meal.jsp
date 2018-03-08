<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add new meal</title>
</head>
<body>
<p>
    <a href="<c:url value="/"/>">Home</a>
</p>

<form action="<c:url value="/meals"/>" method="post">
    <input type="text" name="name" />
    <input type="number" name="calories" />
    <input type="date" name="date"/>
    <input type="time" name="time"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>