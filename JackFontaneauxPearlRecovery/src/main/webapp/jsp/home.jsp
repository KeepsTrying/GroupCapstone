<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Home</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/homeStyles.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Amaranth" rel="stylesheet">

    </head>

    <body>
        
        <%@include file="navbarSnippet.jsp" %>

        <%@include file="headerSnippet.jsp" %>
        
        <%@include file="logoSnippet.jsp" %>
        
       
        <div class="container-fluid bg-3 text-center">    
            <div class="landing-sidebar" id="left-side"></div>
            <div class="landing-sidebar" id="right-side"></div>
            
            <div class="row-fluid">
                    
                    <div class="row" id="thumDiv">
                        
                        <div id="blogSectionHeader">
                            <h3 class="myFont" >Blog Posts</h3>
                            <hr>
                            <h4><c:out value="${searchMessage}"/></h4><br/>
                            <ul class="list-group" id="errorMessages"></ul>
                        </div>
                    <c:forEach var="currentPost" items="${listOfPosts}">
                    <div class="col-sm-3 thum-buffer myFont" id="${currentPost.postId}" onclick="location.href = '${pageContext.request.contextPath}/post/${currentPost.postId}'" 


                         <c:forEach var="currentImage" items="${currentPost.imageList}">
                             <c:if test="${currentImage.imageTypeId == 1}">
                                 style = "background-image: url('${currentImage.imageUrl}');
                                 background-size: cover; color: black;"
                             </c:if>
                         </c:forEach>

                         >
                        <div class="container-fluid">
                            <p>
                                <c:forEach var="currentAuthor" items="${currentPost.authorList}">
                                    <c:out value="${currentAuthor.firstName}"/> <c:out value="${currentAuthor.lastName}"/>,  
                                </c:forEach>
                            </p>
                            <br>
                            <p><c:out value="${currentPost.title}"/></p>
                            <br>
                            <p><c:out value="${currentPost.synopsis}"/></p>
                            <br>
                            <p><c:out value="${currentPost.postDate}"/></p>     
                        </div>
                    </div>
                    </c:forEach>
                   </div>
            </div>
        </div>
                        
        <div class="row text-center top50">
            <button class="btn btn-default hidden" id="showMoreBtn">
                Show More
            </button>
        </div>

        <%@include file="footerSnippet.jsp" %>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
    </body>
</html>