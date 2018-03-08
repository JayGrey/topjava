<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/utils.tld" prefix="util" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .exceed {
            color: red;
        }

        .normal {
            color: green;
        }
    </style>
</head>
<body>

<p>
    <a href="<c:url value="/"/>">Home</a>
</p>

<p>
    <a href="<c:url value="/add_meal.jsp"/>">add new meal</a>
</p>

<table>
    <thead>
    <tr>
        <td>action</td>
        <td>Description</td>
        <td>Calories</td>
        <td>Date</td>
    </tr>
    </thead>
    <%--@elvariable id="mealList" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"--%>
    <c:forEach var="meal" items="${mealList}">
        <tr class="${meal.exceed ? 'exceed' : 'normal'}">
            <td>
                <a href="<c:url value="/meals?action=update&id=${meal.id}" /> ">edit</a>
                <a href="<c:url value="/meals?action=delete&id=${meal.id}" /> ">delete</a>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><util:date_format format="dd.MM.yyyy HH:mm">${meal.dateTime}</util:date_format></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
