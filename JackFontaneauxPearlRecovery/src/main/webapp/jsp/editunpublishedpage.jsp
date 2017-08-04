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

        <title>Edit</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Amaranth" rel="stylesheet">
        <script src='https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=367dc39dvvnxim7ps20rvoldumx2ghpixcr5qy3oz16yz6q1'></script>
        <script>
            tinymce.init({
                selector: '#mytextarea',
                encoding: 'xml'
            });
        </script>
    </head>

    <body>

        <%@include file="navbarSnippet.jsp" %>

        <%@include file="headerSnippet.jsp" %>

       
        <div class="container-fluid bg-3 text-center col-sm-10 col-sm-offset-1"> 
            <div class="row content">
                <form class="form-horizontal" id="create-form" action="${pageContext.request.contextPath}/edit/page/${page.pageId}" method="POST">
                    <!-- <div class="col-sm-2 sidenav">
                        <h1>Edit Unpublished Work</h1>
                    <c:forEach var="currentUnpublished" items="${unpublishedList}">
                        <p class="col-sm-12">
                            <button class="btn btn-default" onclick="location.href = '${pageContext.request.contextPath}/post/${currentUnpublished.postId}'" >
                        <c:out value="${currentUnpublished.title}"/>
                    </button>
                </p>
                    </c:forEach>
                </div> -->
                    <div class="col-sm-7" style="margin: 1%; border: 2px solid black; box-shadow: 10px 10px 5px #888888;">
                        <div class="form-group" id="author-row">
                            <div class="col-sm-3" style="text-align: left; font-size: 18px;">
                                <label>Authors:</label>
                            </div>

                            <c:forEach var="currentAuthor" items="${authorListChosen}">
                                <label class="col-sm-2 control-label">
                                    <input class="col-sm-2" name="author-chosen"  type="checkbox" checked value="${currentAuthor.userId}">
                                    <c:out value="${currentAuthor.firstName}"/> <c:out value="${currentAuthor.lastName}"/>
                                </label>
                            </c:forEach>
                            <c:forEach var="currentAuthor" items="${authorList}">
                                <label class="col-sm-2 control-label">
                                    <input class="col-sm-2" name="author-chosen" type="checkbox" value="${currentAuthor.userId}">
                                    <c:out value="${currentAuthor.firstName}"/> <c:out value="${currentAuthor.lastName}"/>
                                </label>
                            </c:forEach>
                        </div>
                        <div class="form-group" id="title-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Title:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="text" name="title" required class="col-sm-6" value="${page.title}"/>
                        </div>
                        <div class="form-group" id="title-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Nav Name:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="text" name="navName" required class="col-sm-6" value="${page.navName}"/>
                        </div>
                        <div class="form-group" id="publish-expire-date-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Publish Date:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="date" name="post-date" required class="col-sm-2" value="${page.publishDate}"/>
                        </div>

                        <div class="col-sm-3"></div>
                        <div class="form-group col-sm-7" id="image-row">

                            <select style="box-shadow: 10px 10px 5px #888888;" class="form-control" name="page-image-choice" required>
                                <option value="${imageChosen.imageId}" selected><c:out value="${imageChosen.imageUrl}"/></option>
                                <c:forEach var="currentImage" items="${imageList}">
                                    <option value="${currentImage.imageId}"><c:out value="${currentImage.imageUrl}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-sm-12" id="body-row">
                            <textarea id="mytextarea" name="body-content" required >${page.textArea}</textarea>
                        </div>
                        <div class="form-group" id="complete-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Is Complete:</label>
                            <input type="radio" class="col-sm-3" value="1" name="isComplete"/>
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Not Complete:</label>
                            <input type="radio" class="col-sm-3" value="0" name="isComplete" checked/>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <button style="box-shadow: 10px 10px 5px #888888;" class="btn btn-info col-sm-3" type="button" onclick="location.href='${pageContext.request.contextPath}'">Cancel</button>
                            <div class="col-sm-1"></div>
                            <button style="box-shadow: 10px 10px 5px #888888;" class="btn btn-info col-sm-3" type="submit">Edit Post</button>
                            <div class="col-sm-2"></div>
                        </div>
                    </div>
                    <div class="col-sm-2 sidenav" style="box-shadow: 10px 10px 5px #888888;">
                        <div class="form-group" id="type-decide">
                            <h1>Delete Page</h1>
                            <hr/>
                            <button style="box-shadow: 10px 10px 5px #888888;" id="delete" type="button" class="btn btn-info" onclick="location.href = '${pageContext.request.contextPath}/delete/page/${page.pageId}'">Delete</button>
                        </div>
                        <div class="form-group" id="category-show">
                            <h1>Categories</h1>
                            <hr/>
                            <div id="post-categories-wrapper">
                                <div id="post-categories-display">
                                    <label class="col-sm-6 control-label" style="text-align: left;">

                                        <input class="col-sm-4" name="category-type" id="page-category-click" checked type="radio" value="${catChosen.pageCategoryId}">

                                        <c:out value="${catChosen.categoryName}"/>
                                        
                                    </label>
                                    <c:forEach var="pageCategory" items="${pageList}">
                                        <label class="col-sm-6 control-label" style="text-align: left;">

                                            <input class="col-sm-4" name="category-type" id="page-category-click" type="radio" value="${pageCategory.pageCategoryId}">

                                            <c:out value="${pageCategory.categoryName}"/>
                                            
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>