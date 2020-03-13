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
    <title>Update Group</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <c:if test="${exercises != null}">
        <h1><label id="user">Zaktualizuj Grupę</label></h1>
        <div id="main">
            <form method="post" action="/exercise/updateexercise">
                <p>
                <p>Podaj nową nazwę zadania</p>
                <input id="exercise" name="exerciseName" value="${fn:escapeXml(exercises.title)}">
                <span class="error">${messages.exercise}</span>
                </p>
                <p>Podaj nowy opis zadania</p>
                <input id="exerciseDesc" name="exerciseDesc" value="${fn:escapeXml(exercises.description)}">
                </p>
                <p><input type="submit"></p>
                <p>
                    <span class="success">${messages.success}</span>
                </p>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
