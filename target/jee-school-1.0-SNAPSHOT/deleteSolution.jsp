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
    <title>Delete Solution</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <c:if test="${solutions != null}">
    <div id="main">
        <form method="post" action="/solution/deletesolution">
            <p>Podaj nazwę rozwiązania do usunięcia</p>
            <select name="solution" id="deleteSolution" class="form-select-button" style="width: 450px" size="10">
                <c:forEach var="solutions" items="${solutions}">
                    <option selected="selected" value="${solutions.id}" name="solution">SOLUTION ID: ${solutions.id} EXERCISE ID: ${solutions.exercise_id.id} USER ASSIGNED: ${solutions.users_id.username}</option>
                </c:forEach>
            </select>
            <p><input type="submit" formtarget="_self"></p>
        </form>
        </c:if>
        <p> ${empty message ? "" : message}</p>
    </div>
</div>
</body>
</html>
