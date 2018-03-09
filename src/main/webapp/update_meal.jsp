<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update meal</title>
</head>
<body>
<p>
    <a href="<c:url value="/"/>">return to home</a>
</p>
<p>
<h2>Update meal</h2>
</p>
<%--@elvariable id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"--%>
<form action="<c:url value="/meals"/>" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td><label for="name">Description: </label></td>
            <td><input type="text" id="name" name="name" value="${meal.description}" required/></td>
        </tr>
        <tr>
            <td><label for="calories">Calories:</label></td>
            <td><input type="number" id="calories" name="calories" placeholder="number of calories" min="0"
                       value="${meal.calories}" required/></td>
        </tr>
        <tr>
            <td><label for="date">Date</label></td>
            <td><input type="date" id="date" name="date" value="${meal.dateTime.toLocalDate()}" required/></td>
        </tr>
        <tr>
            <td><label for="time">Time</label></td>
            <td><input type="time" id="time" name="time" value="${meal.dateTime.toLocalTime()}" required/></td>
        </tr>
    </table>
    <button type="submit">Submit</button>
</form>
</body>
</html>