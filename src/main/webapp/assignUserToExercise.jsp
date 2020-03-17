<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 14.03.2020
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assign User</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <form name="userGroup" action="/solution/assignusertoexercise" method="post">
        <p id="p1">Wybierz Użytkownika</p>
        <select select class="form-select-button" style="width: 350px" name="userId" size="10">
            <c:forEach items="${users}" var="user">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
        </select>
        <p id="p2">Wybierz Rozwiązanie</p>
        <select select class="form-select-button" style="width: 350px" name="solutionId" size="10">
            <c:forEach items="${solutions}" var="solution">
                <option value="${solution.id}">SOLUTION ID: ${solution.id} EXERCISE: ${solution.exercise_id.id} USER: ${solution.users_id.username}</option>
            </c:forEach>
        </select>
        <p><input type="submit"></p>
        <p>${empty message ? "" : message}</p>
    </form>
</div>
</body>
</html>
