<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<table>
    <thead>
    <tr>
        <td>Description</td>
        <td>Calories</td>
        <td>Date</td>
    </tr>
    </thead>
    <%--@elvariable id="mealList" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"--%>
    <%--<jsp:useBean id="formatter" class="java.time.format.DateTimeFormatter" />--%>
    <c:forEach var="meal" items="${mealList}">
        <c:choose>
            <c:when test="${meal.exceed}">
                <tr class="exceed">
            </c:when>
            <c:otherwise>
                <tr class="normal">
            </c:otherwise>
        </c:choose>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <%--<td>${meal.dateTime.format(${formatter})}</td>--%>
        <td>
        ${meal.dateTime.format(<%%>)}
        </td>
        </tr>
    </c:forEach>
    
</table>
</body>
</html>
