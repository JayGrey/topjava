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
    <a href="<c:url value="/"/>">return to home</a>
</p>

<p>
<h2>Add new meal</h2>
</p>

<form action="<c:url value="/meals"/>" method="post">
    <table>
        <tr>
            <td><label for="name">Description: </label></td>
            <td><input type="text" id="name" name="name" required/></td>
        </tr>
        <tr>
            <td><label for="calories">Calories:</label></td>
            <td><input type="number" id="calories" name="calories" placeholder="number of calories" min="0" required/></td>
        </tr>
        <tr>
            <td><label for="date">Date</label></td>
            <td><input type="date" id="date" name="date" required/></td>
        </tr>
        <tr>
            <td><label for="time">Time</label></td>
            <td><input type="time" id="time" name="time" required/></td>
        </tr>
    </table>
    <button type="submit">Submit</button>
</form>
</body>
</html>