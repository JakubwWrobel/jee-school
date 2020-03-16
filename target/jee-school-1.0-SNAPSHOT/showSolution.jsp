<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 14.03.2020
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Show Solution</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <c:out value="SOLUTION ID: ${solution.id}"></c:out>
    <c:out value="CREATED: ${solution.created}"></c:out>
    <c:out value="UPDATED: ${solution.updated}">${solution.updated}</c:out>
    <c:out value="DESCRIPTION: ${solution.description}">${solution.description}</c:out>
    <c:out value="USER ID: ${solution.users_id.email}">${solution.description}</c:out>
    <c:out value="EXERCISE ID: ${solution.exercise_id.id}">${solution.description}</c:out>
</div>
</body>
</html>
