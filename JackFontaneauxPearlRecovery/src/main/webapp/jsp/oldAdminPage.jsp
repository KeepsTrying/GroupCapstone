<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

        <title>Admin</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/adminPostStyles.css" rel="stylesheet"/>

    </head>
    <body>
        <%@include file="navbarSnippet.jsp" %>
        <%@include file="headerSnippet.jsp" %>
        <%@include file="logoSnippet.jsp" %>

        <div class="page-header text-center">
            <h1>Administrator</h1>
        </div>
        <div class="container-fluid">
            <div class="row-fluid top80">
                <div class="col-sm-6 col-sm-offset-3 top25">
                    <a href="#pubWorks" data-toggle="collapse">
                        <h3>Published Pages<span class="caret"></span></h3>
                    </a>
                    <div class="row table-responsive table-bordered collapse" id="pubWorks">
                        <table class="table" >
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Date</th>
                                    <th colspan="3">Options</th>
                                </tr>
                            </thead>
                            <c:forEach var="currentPage" items="${publishedPages}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/category/${currentPage.pageId}">
                                            <c:out value="${currentPage.title}"></c:out>
                                            </a>
                                        </td>
                                        <td><c:out value="${currentPage.publishDate}"></c:out></td>
                                    <td><a href="${pageContext.request.contextPath}/edit/${currentPage.pageId}">Edit</a></td>
                                    <td><a href="${pageContext.request.contextPath}/delete/${currentPage.pageId}">Delete</a></td>
                                    <td><a href="${pageContext.request.contextPath}/unPublishPage/${currentPage.pageId}">Unpublish</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <button class="btn btn-default top25 left20 bottom10">
                            Next 10 <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                    </div>
                </div>
                <div class="col-sm-6 col-sm-offset-3 top25">
                    <a href="#unPubWorks" data-toggle="collapse">
                        <h3>Unpublished Pages<span class="caret"></span></h3>
                    </a>
                    <div class="row table-responsive table-bordered collapse" id="unPubWorks">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Date</th>
                                    <th colspan="3">Options</th>
                                </tr>
                            </thead>
                            <c:forEach var="currentPage" items="${unpublishedPages}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/category/${currentPage.pageId}">
                                            <c:out value="${currentPage.title}"></c:out>
                                            </a>
                                        </td>
                                        <td><c:out value="${currentPage.publishDate}"></c:out></td>
                                    <td><a href="${pageContext.request.contextPath}/edit/${currentPage.pageId}">Edit</a></td>
                                    <td><a href="${pageContext.request.contextPath}/delete/${currentPage.pageId}">Delete</a></td>
                                    <td><a href="${pageContext.request.contextPath}/publishPage/${currentPage.pageId}">Publish</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="col-sm-6 col-sm-offset-3 top25 bottom150">
                    <a href="#draftWorks" data-toggle="collapse">
                        <h3>Draft Pages<span class="caret"></span></h3>
                    </a>
                    <div class="row table-responsive table-bordered collapse" id="draftWorks">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Date</th>
                                    <th colspan="3">Options</th>
                                </tr>
                            </thead>
                            <c:forEach var="currentPage" items="${draftPosts}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/category/${currentPage.pageId}">
                                            <c:out value="${currentPage.title}"></c:out>
                                            </a>
                                        </td>
                                        <td><c:out value="${currentPage.publishDate}"></c:out></td>
                                    <td><a href="${pageContext.request.contextPath}/edit/${currentPage.pageId}">Edit</a></td>
                                    <td><a href="${pageContext.request.contextPath}/delete/${currentPage.pageId}">Delete</a></td>
                                    <td><a href="${pageContext.request.contextPath}/completePage/${currentPage.pageId}">Complete</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>	
    </body>
</html>