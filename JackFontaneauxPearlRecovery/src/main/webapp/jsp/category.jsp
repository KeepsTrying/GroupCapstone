<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Page</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/staticPage.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Amaranth" rel="stylesheet">
    </head>

    <body>

        <%@include file="navbarSnippet.jsp" %>

        <%@include file="headerSnippet.jsp" %>
        
        <%@include file ="logoSnippet.jsp" %>
        
       
        <!-- Page Content -->
        <div class="container">

            <hr class="featurette-divider">

            <!-- First Featurette -->
            <div class="featurette row" id="about">
                <div class="col-sm-8">
                    <h2 class="featurette-heading">
                        <c:out value="${staticPage.title}"></c:out>
                        </h2>
                        <p class="lead">${staticPage.textArea}</p>
                    <br>
                    <p>
                        <c:forEach var="currentAuthor" items="${staticPage.authorList}">
                            <c:out value="${currentAuthor.firstName}"/> <c:out value="${currentAuthor.lastName}"/>,
                        </c:forEach>
                        <span class="pull-right"><c:out value="${staticPage.publishDate}"></c:out></span>
                        </p>
                    </div>
                    <div class="col-sm-4">
                        <img class="featurette-image img-circle img-responsive pull-right"
                        <c:forEach var="currentImage" items="${staticPage.imageList}">
                            <c:if test="${currentImage.imageTypeId == 3}">
                                src="${currentImage.imageUrl}"
                            </c:if>
                        </c:forEach>
                        >  
                </div>
            </div>

            <hr class="featurette-divider">
        </div>

        <%@include file="footerSnippet.jsp" %>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>