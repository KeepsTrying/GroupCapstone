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
        
        <div class="container" style="border: 2px solid black; box-shadow: 10px 10px 5px #888888; margin-bottom: 5%; background-color: rgba(255,255,255,0.2);">
            <h1 class="text-center">Administrator</h1>
            <hr/>
            <h2>Add User Form</h2>
            <form class="col-sm-6 form-horizontal" method="POST" action="${pageContext.request.contextPath}/addUser">
                <div class="form-group">
                <label class="label-control">Username: </label><input type="text" 
                                 class="form-control col-sm-5" name="username"/><br/>
                </div>
                <div class="form-group">
                <label class="label-control">Password: </label><input type="password" 
                                 class="form-control col-sm-5" name="password"/><br/>
                </div>
                <div class="form-group">
                <label class="label-control">Admin User? </label><input type="checkbox" 
                                 class="form-control col-sm-5" name="isAdmin" value="yes"/> <br/>
                </div>
                <div class="form-group">
                <label class="label-control">Email: </label><input type="email" 
                                class="form-control col-sm-5" name="email"/><br/>
                </div>
                <div class="form-group">
                    <label class="label-control">First Name: </label><input type="text" 
                                class="form-control col-sm-5" name="first-name"/><br/>
                </div>
                <div class="form-group">
                <label class="label-control">Last Name: </label><input type="text" 
                                class="form-control col-sm-5" name="last-name"/><br/>
                </div>
                <div class="form-group">
                <label class="label-control">Latitude: </label><input type="number" 
                                class="form-control col-sm-5" name="latitude"/><br/>
                </div>
                <div class="form-group">
                <label class="label-control">Longitude: </label><input type="number" 
                               class="form-control col-sm-5"  name="longitude"/><br/>
                </div>
                <input class="btn btn-info pull-right" type="submit" value="Add User"/><br/>
            </form>
        </div>
                
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
