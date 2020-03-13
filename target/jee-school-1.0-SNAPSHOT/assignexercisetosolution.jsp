<%@ page import="java.util.ArrayList" %>
<%@ page import="pl.coderslab.model.UserGroup" %>
<%@ page import="pl.coderslab.dao.UserGroupDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 19.02.2020
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Przypisz rozwiązanie do zadania</title>
    <link rel="stylesheet" href="CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1 id="user">Wybierz zadanie</h1>
    <form name="exercises" method="post" action="/solution/assignexercisetosolution">
        <p id="p1">Wybierz użytkownika</p>
        <select class="form-select-button" style="width: 250px" name="exerciseId" size="10">
            <c:forEach items="${listOfExercises}" var="exercise">
                <option value="${exercise.id}">${exercise.title}</option>
            </c:forEach>
        </select>

        <p id="p2">Wybierz rozwiązanie</p>
        <select class="form-select-button" style="width: 250px" name="solutionId" size="10">
            <c:forEach items="${listOfSolutions}" var="solution">
                <option value="${solution.id}">${solution.id}</option>
                <option value="${solution.id}">${solution.exercise_id}</option>
            </c:forEach>
        </select>
        <p><input type="submit"></p>
        <p>${empty message ? "" : message}</p>
    </form>
</div>
</body>
</html>
