<%@ page import="java.util.ArrayList" %>
<%@ page import="pl.coderslab.model.UserGroup" %>
<%@ page import="pl.coderslab.dao.UserGroupDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 19.02.2020
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Assign Group</title>
    <link rel="stylesheet" href="CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1 id="user">Przypisz użytkownika do grupy</h1>
    <form name="userGroup" method="post" action="assignGroup">
        <p id="p1">Wybierz użytkownika</p>
        <select class="form-select-button" style="width: 250px" name="userId" size="10">
            <c:forEach items="${listOfUsers}" var="user">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
        </select>

        <p id="p2">Wybierz grupę do której chcesz przypisać użytkownika</p>
        <select class="form-select-button" style="width: 250px" name="groupId" size="10">
            <c:forEach items="${listOfGroups}" var="group">
                <option value="${group.id}">${group.userGroupName}</option>
            </c:forEach>
        </select>
        <p><input type="submit"></p>
        <p>${empty message ? "" : message}</p>
    </form>
</div>

<%--
<%

    ArrayList list = UserGroupDAO.showAll();
    int size = list.size();

%>

<select name="someName">

    <%for (int i=0;i<size;i++)
    {

        YourClass obj = (YourClass)list.get(i);

    %>

    <option value="<%=obj.getValue()%>" ><%=obj.getText()%></option>

    <%}%>
--%>

</select>
</body>
</html>
