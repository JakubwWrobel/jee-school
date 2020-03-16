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
    <title>Panel użytkownika</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Rozwiąż zadanie</label></h1>
    <form action="/user/resolveexercise" method="post">
            <p>Podaj rozwiązanie</p>
            <input id="solutions" name="description" >
            <p>
            <p><input type="submit"></p>
            <p>
                <span class="success">${messages.success}</span>
            </p>
    </form>
</div>
</body>
</html>
