<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 09.03.2020
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Add Exercise</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>

    <div id="main">
        <form method="post" action="/exercise/addexercise">
            <p>
            <p>Podaj Nazwę Zadania</p>
            <input id="exercisename" type="text" name="exercisename">
            <span class="error">${messages.exercise}</span>
            </p>
            <p>Podaj Opis Zadania</p>
            <input id="exercisedesc" type="text" name="exercisedesc">
            </p>

            <input type="submit">

        </form>
        <span class="success">${messages.success}</span>
    </div>
</div>
</body>
</html>
