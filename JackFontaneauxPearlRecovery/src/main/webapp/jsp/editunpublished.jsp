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
                <form class="form-horizontal" id="create-form" action="${pageContext.request.contextPath}/edit/post/${post.postId}" method="POST">
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
                                    <input class="col-sm-2" name="author-chosen" type="checkbox" checked value="${currentAuthor.userId}">
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
                            <input style="box-shadow: 10px 10px 5px #888888;" type="text" name="title" class="col-sm-6" value="${post.title}"/>
                        </div>
                        <div class="form-group" id="publish-expire-date-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Post Date:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="date" name="post-date" class="col-sm-2" value="${post.postDate}"/>

                            <label class="col-sm-2 control-label" id="expire-label" style="text-align: left; font-size: 18px;">Expiration Date:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="date" name="expire-date" id="expire-input" class="col-sm-2" value="${post.expirationDate}"/>
                        </div>
                        <div class="form-group" id="lat-long-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Latitude:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="number" name="post-latitude" class="col-sm-2" value="${post.latitude}"/>

                            <label class="col-sm-2 control-label" style="text-align: left; font-size: 18px;">Longitude:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="number" name="post-longitude" class="col-sm-2" value="${post.longitude}"/>
                        </div>
                        
                        <div class="form-group col-sm-10" id="image-row">
                            <label class="col-sm-4 control-label" style="text-align: left; font-size: 18px;">Header Image:</label>
                            <select style="box-shadow: 10px 10px 5px #888888;" class="form-control col-sm-8" name="header-image-choice" required>
                                <option value="${headChosen.imageId}" selected><c:out value="${headChosen.imageUrl}"/></option>
                                <c:forEach var="currentImage" items="${imageListHead}">
                                    <option value="${currentImage.imageId}"><c:out value="${currentImage.imageUrl}"/></option>
                                </c:forEach>
                            </select>
                                
                        </div>
                        <div class="form-group col-sm-10" id="image-row">
                            <label class="col-sm-4 control-label" style="text-align: left; font-size: 18px;">Preview Image:</label>
                            <select style="box-shadow: 10px 10px 5px #888888;" class="form-control col-sm-8" name="preview-image-choice" required>
                                <option value="${previewChosen.imageId}" selected><c:out value="${previewChosen.imageUrl}"/></option>
                                <c:forEach var="currentImage" items="${imageListPreview}">
                                    <option value="${currentImage.imageId}"><c:out value="${currentImage.imageUrl}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" id="synopsis-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Synopsis:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" type="text" class="col-sm-6" name="synopsis" value="${post.synopsis}"/>
                        </div>
                        <div class="row" id="body-row">
                            <textarea id="mytextarea" name="body-content">${post.content}</textarea>
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
                            <h1>Delete Post</h1>
                            <hr/>
                            <button style="box-shadow: 10px 10px 5px #888888;" id="delete" type="button" class="btn btn-info" onclick="location.href = '${pageContext.request.contextPath}/delete/post/${post.postId}'">Delete</button>
                        </div>
                        <div class="form-group" id="category-show">
                            <h1>Categories</h1>
                            <hr/>
                            <div id="post-categories-wrapper">
                                <div id="post-categories-display">
                                    <c:forEach var="postCategory" items="${postListChosen}">
                                        <label class="col-sm-6 control-label" style="text-align: left;">

                                            <input class="col-sm-4" name="category-type" id="post-category-click" type="checkbox" checked value="${postCategory.postCategoryId}">

                                            <c:out value="${postCategory.categoryName}"/>
                                        </label>
                                    </c:forEach>
                                    <c:forEach var="postCategory" items="${postList}">
                                        <label class="col-sm-6 control-label" style="text-align: left;">

                                            <input class="col-sm-4" name="category-type" id="post-category-click" type="checkbox" value="${postCategory.postCategoryId}">

                                            <c:out value="${postCategory.categoryName}"/>
                                        </label>
                                    </c:forEach>
                                </div>
                                <button type="button" style="margin-top: 10px; box-shadow: 10px 10px 5px #888888;" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Search Category</button>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create Post Category</h4>
                            </div>
                            <div class="modal-body">
                                <label class="control-label">Category Name:</label>
                                <input style="box-shadow: 10px 10px 5px #888888;" type="text" name="category-add" id="category-add" class="form-control" required/>
                            </div>
                            <div class="modal-footer">
                                <button style="box-shadow: 10px 10px 5px #888888;" type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                <button style="box-shadow: 10px 10px 5px #888888;" id="add-category" class="btn btn-default">Create Category</button>
                            </div>
                            <ul class="list-group" id="errorMessagesCreate"></ul>

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>