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
    <title>All Exercises</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back">
    <h1><label id="user">Panel użytkownika</label></h1>
    <form action="exercise/showallexercises" method="post">
        <table border="1px" cellpadding="3" cellspacing="2" align="center">
            <thead>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            </thead>
            <tbody align="center">
            <c:forEach items="${exercises}" var="exercise">
                <tr>
                    <td>${exercise.id}</td>
                    <td>${exercise.title}</td>
                    <td>${exercise.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
