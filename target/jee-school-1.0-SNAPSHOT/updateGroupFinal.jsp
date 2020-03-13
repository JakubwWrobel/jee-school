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
    <title>Update Group</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <c:if test="${usergroup != null}">
        <h1><label id="user">Zaktualizuj Grupę</label></h1>
        <div id="main">
            <form method="post" action="/usergroup/updateusergroup">
                <p>
                <p>Podaj nową nazwę grupy</p>
                <input id="groupName" name="groupName" value="${fn:escapeXml(usergroup.userGroupName)}">
                <span class="error">${messages.group}</span>
                </p>
                <p><input type="submit"></p>
                <p>
                    <span class="success">${messages.success}</span>
                </p>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
