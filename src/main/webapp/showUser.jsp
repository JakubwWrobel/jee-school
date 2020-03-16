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
    <title>Show User</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <table border="1px" cellpadding="3" cellspacing="2" align="center">
        <thead>
        <th>ID</th>
        <th>User Name</th>
        <th>Group ID</th>
        <th>Email</th>
        </thead>
        <tbody align="center">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${empty user.userGroup.id ? "" : user.userGroup.id}</td>
            <td>${user.email}</td>
        </tr>
        </tbody>
    </table>
    <c:if var="test" test="${solutions != null}">
        <div id="solutions">
            <p>SOLUTIONS:</p>
            <table cellspacing="2" cellpadding="3" border="1" align="center">
                <thead>
                <th>Solution ID</th>
                <th>Solution Created</th>
                <th>Solution Updated</th>
                <th>Solution Description</th>
                <th>User ID Assigned</th>
                <th>Exercise ID Assigned</th>
                <th>Dodaj rozwiązanie</th>
                </thead>
                <tbody>
                <c:forEach var="sol" items="${solutions}">
                    <c:url var="thisPage" value="/user/resolveexercise">
                        <c:param name="id" value="${sol.id}"></c:param>
                    </c:url>
                    <tr id="tr">
                        <td>${sol.id}</td>
                        <td>${sol.created}</td>
                        <td>${sol.updated}</td>
                        <td>${sol.description}</td>
                        <td>${sol.users_id.id}</td>
                        <td>${sol.exercise_id.id}</td>
                        <td><a href="<c:out value="${thisPage}"/>">${sol.id}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <p>${empty solutions ? "Użytkownik nie posiada przypisanych zadań" : ""}</p>
    <p>${empty message ? "" : message}</p>
</div>
</body>
</html>