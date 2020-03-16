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
    <title>All Users In Group</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <table border="1px" cellpadding="3" cellspacing="2" align="center">
        <c:url var="page" value="/user/showuser"></c:url>
        <thead>
        <th>ID</th>
        <th>User name</th>
        <th>Email</th>
        <th>LINK</th>
        </thead>
        <tbody align="center">
        <c:forEach items="${users}" var="user">
            <c:url var="thisPage" value="/user/showuser">
                <c:param name="id" value="${user.id}"></c:param>
            </c:url>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}></td>
                <td>${user.email}</td>
                <td><a href="<c:out value="${thisPage}"/>">${user.id}</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

