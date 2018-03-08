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
    <a href="<c:url value="/"/>">Home</a>
</p>
<%--@elvariable id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"--%>
<form action="<c:url value="/meals"/>" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <input type="hidden" name="action" value="update">
    <input type="text" name="name" value="${meal.description}"/>
    <input type="number" name="calories" value="${meal.calories}"/>
    <input type="date" name="date" value="${meal.dateTime.toLocalDate()}"/>
    <input type="time" name="time" value="${meal.dateTime.toLocalTime()}"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>