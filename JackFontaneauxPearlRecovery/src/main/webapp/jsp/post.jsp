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

        <title>Post</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Amaranth" rel="stylesheet">

    </head>
    <body>
        <%@include file="navbarSnippet.jsp" %>
        <%@include file="headerSnippet.jsp" %>
        <%@include file="logoSnippet.jsp" %>


        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">

                    <h2 style="color: black;">Similar Posts</h2>
                    <c:forEach var="currentSimilar" items="${similarList}">
                        <p class="col-sm-12">
                            <button class="btn btn-default" onclick="location.href = '${pageContext.request.contextPath}/post/${currentSimilar.postId}'" >
                                <c:out value="${currentSimilar.title}"/>
                            </button>
                        </p>
                    </c:forEach>

                </div>
                <div class="col-sm-8 text-left" id="blog-body"> 
                    <div class="heading-wrapper" 
                         style="background-image:url('${headerImage.imageUrl}');
                         min-height: 500px;
                         background-attachment: fixed;
                         background-position: center;
                         background-repeat: no-repeat;
                         background-size: contain;
                         color:white;
                         font-size: 20px;">
                        <div class="jumbotron" style="background-color: rgba(200,200,200,.5)">
                            <h1 style="font-family: sans-serif; font-weight: bold;" class="text-center" ><c:out value="${post.title}"/></h1>
                            <p><span class="text-left">
                                    <c:forEach var="currentAuthor" items="${authorList}">
                                        <c:out value="${currentAuthor.firstName} ${currentAuthor.lastName}"/>
                                    </c:forEach>
                                </span>

                                <span class="pull-right">
                                    <c:out value="${post.postDate}"/>
                                </span>
                            </p>
                        </div>
                    </div>
                    <hr/>
                    <div id="content">
                        ${post.content}
                    </div>
                    <div class="preview-wrapper"
                         style="background-image:url('${postImage.imageUrl}');
                         min-height: 500px;
                         background-attachment: fixed;
                         background-position: center;
                         background-repeat: no-repeat;
                         background-size: contain;
                         box-shadow: 5px 5px 2px #888888;
                         color:white;">
                    </div>
                </div>
                <div class="col-sm-2 container-fluid sidenav">

                    <div class="well">
                        <p id="map" style="height: 200px; background-image: url('${headerImage.imageUrl}'); background-size: cover;"></p>
                    </div>
                    <div class="well">
                        <p style="height: 200px; background-image: url('${headerImage.imageUrl}'); background-size: cover;"></p>
                    </div>
                    <input type="hidden" id="post-lat" value="${post.latitude}"/>
                    <input type="hidden" id="post-long" value="${post.longitude}"/>
                    <div class="row">
                        <div class="col-md-12" id="current">
                            <h2>Current Conditions in <span id="json-city"></span></h2>

                            <div class="col-md-6" id="current-icon">	
                            </div>

                            <div class="col-md-6" id="current-data">
                            </div>

                        </div><!--End Column div-->
                    </div><!--End Row Div-->
                    <hr/>
                    <div class="row">
                        <div class="col-md-12" id="five-day">
                            <h2>Five Day Forecast</h2>
                            <div id="five-day-cast">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row show-comments">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-6">
                        <h2 style="text-align: left;">Comments</h2>
                        <hr/>
                        <input type="hidden" id="post-id" value="${post.postId}"/>
                        <ul class="list-group" id="errorMessagesDisplayComment"></ul>
                        <div id="comment-div">

                        </div>
                    </div>
                    <div class="col-sm-3">
                        <ul class="list-group" id="errorMessagesDeleteComment"></ul>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <button id="delete" type="button" class="btn btn-info" onclick="location.href = '${pageContext.request.contextPath}/delete/post/${post.postId}'">Delete</button>
                        </sec:authorize>
                    </div>
                </div>
                <div class="row comments">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-6">
                        <!--<form class="form-horizontal" action="${pageContext.request.contextPath}/comment/${post.postId}" method="POST">-->
                        <div class="form-group">
                            <h2 class="pull-left">Add Comment</h2>
                            <ul class="list-group" id="errorMessagesCreateComment"></ul>
                            <div class="form-group col-sm-12">
                                <input type="text" class="form-control" id="input-name" style="width: 20%;" name="anonymous-comment-name" placeholder="Name"/>

                            </div>
                            <div class="form-group col-sm-12">
                                <textarea class="form-control col-sm-12" id="input-text" name="anonymous-comment-content" placeholder="Enter your comment here"></textarea>
                            </div>
                            <button type="button" id="add-comment" class="btn btn-default pull-left">Comment</button>
                        </div>
                        <!--</form>-->
                    </div>
                    <div class="col-sm-3"></div>
                </div>
            </div>


            <%@include file="footerSnippet.jsp"%>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/forPost.js"></script>
            <script>
                                function myMap() {
                                    var mapOptions = {
                                        center: new google.maps.LatLng(${post.latitude}, ${post.longitude}),
                                        zoom: 7,
                                        mapTypeId: google.maps.MapTypeId.HYBRID
                                    };
                                    var map = new google.maps.Map(document.getElementById("map"), mapOptions);

                                    var marker = new google.maps.Marker({position: mapOptions.center});

                                    marker.setMap(map);
                                }
            </script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB3XwEVBKQ01qcMdmumH3Mzs6fES3bYhaI&callback=myMap"></script>
    </body>
</html>