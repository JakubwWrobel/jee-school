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
    <title>Delete Group</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <c:if test="${groups != null}">
    <div id="main">
        <form method="post" action="/usergroup/deleteusergroup">
            <p>Podaj nazwę grupy</p>
            <select name="group" id="deleteGroup" class="form-select-button" style="width: 250px" size="10">
                <c:forEach var="group" items="${groups}">
                    <option selected="selected" value="${group.id}" name="group">${group.userGroupName}</option>
                </c:forEach>
            </select>
            <p><input type="submit" formtarget="_self"></p>
        </form>
        </c:if>
        <p> ${empty message ? "" : message}</p>
    </div>
</div>
</body>
</html>
