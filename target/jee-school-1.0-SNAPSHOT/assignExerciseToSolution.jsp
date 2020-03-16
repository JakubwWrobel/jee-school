<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 09.03.2020
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Assign Exercise</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <div id="main">
        <h1><label id="user">Przypisz zadanie</label></h1>
        <form action="/solution/assignexercisetosolution" method="post">
            <p id="p1">Wybierz zadanie do przypisania</p>
            <select class="form-select-button" style="width: 450px" name="exerciseId" size="10">
                <c:forEach items="${listOfExercises}" var="exercise">
                    <option value="${exercise.id}">EXERCISE ID: ${exercise.title} DESCRIPTION: ${exercise.description}</option>
                </c:forEach>
            </select>
            <p id="p2">Wybierz rozwiązanie</p>
            <select class="form-select-button" style="width: 450px" name="solutionId" size="10">
                <c:forEach items="${listOfSolutions}" var="solution">
                    <option value="${solution.id}">SOLUTION ID: ${solution.id} USER: ${solution.users_id.username} EXERCISE ID: ${solution.exercise_id.id}</option>
                </c:forEach>
            </select>
            <p> ${empty message ? "" : message}</p>
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
