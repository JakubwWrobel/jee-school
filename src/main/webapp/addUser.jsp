<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 18.02.2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<html>
<head>
    <title>Start</title>
    <link rel="stylesheet" href="CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1 id="user">Dodaj użytkownika</h1>
    <form method="post" action="/adduser">
        <p>
        <p>What's your name?</p>
        <input id="userName" name="userName" value="${fn:escapeXml(param.userName)}">
        <span class="error">${messages.userName}</span>
        </p>
        <p>
        <p>What's your email?</p>
        <input id="email" name="email" value="${fn:escapeXml(param.email)}">
        <span class="error">${messages.email}</span>
        </p>
        <p>
        <p>What's your password?</p>
        <input id="password" type="password" name="password" value="${fn:escapeXml(param.password)}">
        <span class="error">${messages.password}</span>
        </p>
        <p><input type="submit"></p>
        <p>
            <span class="success">${messages.success}</span>
            <span class="emailExists">${messages.emailExists}</span>
        </p>
    </form>
</div>
</body>
</html>

