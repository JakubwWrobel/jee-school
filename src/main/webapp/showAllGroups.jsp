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
    <title>All Groups</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <form action="/usergroup/showallgroups" method="post">
        <table border="1px" cellpadding="3" cellspacing="2" align="center">
            <thead>
            <th>ID</th>
            <th>Title</th>
            <th>LINK</th>
            </thead>
            <tbody align="center">
            <c:forEach items="${groups}" var="group">
                <c:url var="thisPage" value="/usergroup/allusersingroup">
                    <c:param name="id" value="${group.id}"></c:param>
                </c:url>
                <tr>
                    <td>${group.id}</td>
                    <td>${group.userGroupName}</td>
                    <td><a href="<c:out value="${thisPage}"/>">${group.id}</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
