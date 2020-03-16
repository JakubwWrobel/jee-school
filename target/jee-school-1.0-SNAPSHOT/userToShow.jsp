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
    <title>User To Show</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <c:if test="${users != null}">
        <form name="update" method="post" target="/showuser, _blank">
            <p>Wybierz użytkownika</p>
            <select name="id" id="updateSelect" class="form-select-button" style="width: 250px" size="10">
                <c:forEach var="user" items="${users}">
                    <option selected="selected" value="${user.id}" name="id">${user.username} - ${user.email}</option>
                </c:forEach>
            </select>
            <p><input type="submit" formtarget="_self"></p>
        </form>
    </c:if>
    <p> ${empty messages ? "" : messages}</p>
</div>
</body>
</html>
