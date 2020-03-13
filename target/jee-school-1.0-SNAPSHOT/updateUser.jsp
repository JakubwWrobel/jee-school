<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 02.03.2020
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1 id="user">Zaktualizuj użytkownika</h1>
    <c:if test="${users != null}">
        <form name="update" method="post" target="/updateuser, _blank">
            <p>Podaj użytkownika którego chcesz zaaktualizować</p>
            <select name="user" id="updateSelect" class="form-select-button" style="width: 250px" size="10">
                <c:forEach var="user" items="${users}">
                    <option selected="selected" value="${user.id}" name="user">${user.username}</option>
                </c:forEach>
            </select>
            <p><input type="submit" formtarget="_self"></p>
        </form>
    </c:if>
    <p> ${empty messages ? "" : messages}</p>
</div>
</body>
</html>
