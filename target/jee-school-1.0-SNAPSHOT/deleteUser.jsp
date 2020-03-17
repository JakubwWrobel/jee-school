<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 01.03.2020
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
    <link rel="stylesheet" href="CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1 id="user">Usuń użytkownika</h1>

    <c:if test="${users=!null}">
        <form target="/deleteuser" method="post" id="deleteuser">
            <p id="p1">Wybierz użytkownika do usunięcia</p>
            <select class="form-select-button" name="user" style="width: 250px" size="10">
                <c:forEach var="user" items="${userList}">
                    <option value="${user.id}" selected="selected" name="user">${user.username}</option>
                </c:forEach>
            </select>
            <p><input type="submit" formtarget="_self"></p>
            <p> ${empty message ? "" : message}</p>
        </form>
    </c:if>
</div>
</body>
</html>
