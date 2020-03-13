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
    <title>Add Solution</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <div id="main">
        <h1><label id="user">Panel użytkownika</label></h1>
        <p id="p1">Stwórz puste zadanie </p>
        <form action="/solution/addsolution" method="post">
            <p><input type="submit"></p>
        </form>
        <p> ${empty message ? "" : message}</p>
    </div>
    <%--    <div id="main">
            <form action="/solution/addsolution" method="post">
                <p>Podaj użytkownika do którego chcesz przypisać rozwiązanie</p>
                <select class="form-select-button" name="user" style="width: 250px" size="10">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}" selected="selected" name="user">${user.username}</option>
                    </c:forEach>
                </select>
                <p><input type="submit"></p>
                <p> ${empty message ? "" : message}</p>
            </form>
        </div>--%>
</div>
</body>
</html>
