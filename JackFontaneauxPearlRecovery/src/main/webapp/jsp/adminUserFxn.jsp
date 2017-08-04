<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Function</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>        
    </head>
    <body>
        
        <%@include file="navbarSnippet.jsp" %>

        <%@include file="headerSnippet.jsp" %>
        <%@include file="logoSnippet.jsp" %>
        
        <div class="container" style="border: 2px solid black; box-shadow: 10px 10px 5px #888888; background-color: rgba(255,255,255,0.2);">
            <h1 class="text-center">Administrator</h1>
            <hr/>
            
            
            <h1>Users</h1>
            <a href="${pageContext.request.contextPath}/displayUserForm">Add a User</a><br/>
            <hr/>
            <c:forEach var="user" items="${users}">
                <div class="col-sm-4" style="border-left: 1px solid tomato;">
                <c:out value="${user.userName}"/> |
                <a href="deleteUser?userId=${user.userId}">Delete</a><br/><br/>
                </div><br/><br/>
            </c:forEach>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>