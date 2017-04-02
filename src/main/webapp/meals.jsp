<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 30.03.2017
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>

<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<a href="meals?action=create">Add Meal</a>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>isExceeded</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${mealsList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"></jsp:useBean>
            <tr <c:if test="${meal.exceed == true}">style="color:red"</c:if><c:if test="${meal.exceed == false}">style="color:green"</c:if>>
                <%--<td>${meal.dateTime.format(dateTimeFormat)}</td>--%>
                <td><%=TimeUtil.toString(meal.getDateTime())%></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><c:if test="${meal.exceed == true}">yes</c:if>
                    <c:if test="${meal.exceed == false}">no</c:if></td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</body>

</html>
