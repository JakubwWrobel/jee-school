
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
    <title>Add Group</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <div id="main">
        <form action="/usergroup/creategroup" method="post">
            <p>
            <p>Podaj Nazwę Grupy</p>
            <input id="groupname" type="text" name="groupname">
            </p>
            <input type="submit">
            <span class="error">${messages.groupname}</span>
            <span class="success">${messages.success}</span>
        </form>
    </div>
</div>
</body>
</html>
