<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 19.02.2020
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" href="/CSS/userpage.css">
</head>
<body>
<div class="back"   >
    <h1><label id="user">Panel użytkownika</label></h1>
    <div  id="main">
        <div id="firstDiv">
            <div>
                <a href="/adduser" id="add">Dodaj użytkownika</a>
            </div>
            <div>
                <a href="/assignGroup">Przypisz grupę użytkownikowi</a>
            </div>
            <div>
                <a href="/deleteuser" id="deleteUser">Usuń użytkownika</a>
            </div>
            <div>
                <a href="/updateuser" id="updateUser">Zaaktualizuj użytkownika</a>
            </div>
        </div>
        <div id="secDiv">
            <div>
                <a href="/usergroup/creategroup" id="addgroup">Stwórz grupę użytkowników</a>
            </div>
            <div>
                <a href="/usergroup/updateusergroup">Edytuj grupę</a>
            </div>
            <div>
                <a href="/usergroup/deleteusergroup" id="deleteGroup">Usuń Grupę</a>
            </div>
            <div>
                <a href="/usergroup/showallgroups" id="showGroups">Pokaż wszystkie Grupy</a>
            </div>
        </div>
        <div id="thirdDiv">
            <div>
                <a href="/exercise/addexercise" id="addexercise">Stwórz zadanie</a>
            </div>
            <div>
                <a href="/exercise/updateexercise">Edytuj Zadanie</a>
            </div>
            <div>
                <a href="/exercise/deleteexercise" id="deleteExercise">Usuń Zadanie</a>
            </div>
            <div>
                <a href="/exercise/showallexercises" id="showExercises">Pokaż wszystkie zadania</a>
            </div>
        </div>
        <div id="fourDiv">
            <div>
                <a href="/solution/addsolution" id="addsolution">Dodaj rozwiązanie</a>
            </div>
            <div>
                <a href="/solution/assignexercisetosolution">Przypisz zadanie do rozwiązania</a>
            </div>
            <div>
                <a href="/solution/deletesolution" id="deletesolution">Usuń rozwiązanie</a>
            </div>
            <div>
                <a href="/updateuser" id="updateUser6">Zaaktualizuj użytkownika</a>
            </div>
        </div>
    </div>
    <div id="solutions">
        <p>SOLUTIONS:</p>
        <table cellspacing="2" cellpadding="3" border="1" align="center">
            <thead>
            <th>Solution ID</th>
            <th>Solution Created</th>
            <th>Solution Updated</th>
            <th>Solution Description</th>
            <th>User ID Assigned</th>
            </thead>
            <tbody>
            <c:forEach var="sol" items="${solutions}">
                <tr id="tr">
                    <td>${sol.id}</td>
                    <td><a href="#"></a>${ sol.created }</td>
                    <td>${sol.updated}</td>
                    <td>${sol.description}</td>
                    <td>${ sol.users_id.id }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
