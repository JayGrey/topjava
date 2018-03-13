<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }

        .filters {
            height: 50px;
        }

        div.filter {
            border: #000000 solid thin;
            padding: 10px;
            display: inline;
            margin-right: 10px;
        }
    </style>
    <script type="text/javascript">
        function make_param(param_name, param_value) {
            if (param_value != null && param_value.length > 0) {
                return param_name + '=' + param_value;
            } else {
                return "";
            }
        }

        function concat_nonempty_params(array) {
            var first_element = true;
            var result = "";

            array.forEach(function (value) {
                if (value != null && value.length > 0) {
                    result += (first_element ? "" : "&") + value;
                    if (first_element) {
                        first_element = false;
                    }
                }
            });

            return result;
        }

        function date_filter(jump_page) {
            var params = [];

            params.push(make_param("date_from", document.getElementById('date_from').value));
            params.push(make_param("date_to", document.getElementById('date_to').value));

            var param_string = concat_nonempty_params(params);

            var location_string = param_string.length > 0 ? jump_page + "?" + "filter=date&" + param_string : jump_page;

            console.log(location_string);
            window.location = location_string;
        }

        function time_filter(jump_page) {
            var params = [];

            params.push(make_param("time_from", document.getElementById('time_from').value));
            params.push(make_param("time_to", document.getElementById('time_to').value));

            var param_string = concat_nonempty_params(params);

            var location_string = param_string.length > 0 ? jump_page + "?" + "filter=time&" + param_string : jump_page;

            console.log(location_string);
            window.location = location_string;
        }
    </script>
</head>
<body>

<header>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
</header>
<main>
    <section>
        <div class="filters">
            <div class="filters">
                <div class="filter">
                    <span>Date from:</span><input id="date_from" name="date_from" type="date"/>
                    <span>Date to:</span><input id="date_to" name="date_to" type="date"/>
                    <span><a href="#" onmousedown="date_filter('meals')" target="_self">filter</a></span>
                </div>

                <div class="filter">
                    <span>Time from:</span><input id="time_from" name="time_from" type="time"/>
                    <span>Time to:</span><input id="time_to" name="time_to" type="time"/>
                    <span><a href="#" onmousedown="time_filter('meals')" target="_self">filter</a></span>
                </div>
            </div>
        </div>

    </section>
    <section>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
</main>
</body>
</html>