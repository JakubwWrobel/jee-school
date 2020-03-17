<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 14.03.2020
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Show All Users</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <form action="/user/showallusers" method="post">
        <table border="1px" cellpadding="3" cellspacing="2" align="center">
            <thead>
            <th>ID</th>
            <th>User Name</th>
            <th>Group ID</th>
            <th>Email</th>
            </thead>
            <tbody align="center">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${empty user.userGroup.id ? "" : user.userGroup.id}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
