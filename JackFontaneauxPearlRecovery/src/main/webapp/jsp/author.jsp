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

        <title>Create</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Amaranth" rel="stylesheet">
        <script src='https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=367dc39dvvnxim7ps20rvoldumx2ghpixcr5qy3oz16yz6q1'></script>

        <script>
            tinymce.init({
                selector: '#mytextarea'
            });
        </script>
    </head>

    <body>

        <%@include file="navbarSnippet.jsp" %>

        <%@include file="headerSnippet.jsp" %>
        <%@include file="logoSnippet.jsp" %>


        <div class="container-fluid bg-3 text-center col-sm-10 col-sm-offset-1"> 
            <h1 class="text-center">Author Portal</h1>
            <hr/>
            <div class="row content">
                <div class="col-sm-2 sidebar" style="box-shadow: 10px 10px 5px #888888; background-color: rgba(255,255,255,0.2);">

                    <h1>Edit Unpublished Posts</h1>
                    <hr/>
                    <c:forEach var="currentUnpublished" items="${unpublishedPostList}">
                        <p class="col-sm-12">
                            <button class="btn btn-default" onclick="location.href = '${pageContext.request.contextPath}/edit/post/${currentUnpublished.postId}'" >
                                <c:out value="${currentUnpublished.title}"/>
                            </button>
                        </p>
                    </c:forEach>

                    <h1>Edit Unpublished Pages</h1>
                    <hr/>
                    <c:forEach var="currentUnpublished" items="${unpublishedPageList}">
                        <p class="col-sm-12">
                            <button class="btn btn-default" onclick="location.href = '${pageContext.request.contextPath}/edit/page/${currentUnpublished.pageId}'" >
                                <c:out value="${currentUnpublished.title}"/>
                            </button>
                        </p>
                    </c:forEach>

                    <h1>Edit Draft Posts</h1>
                    <hr/>
                    <c:forEach var="currentUnpublished" items="${incompletePostList}">
                        <p class="col-sm-12">
                            <button class="btn btn-default" onclick="location.href = '${pageContext.request.contextPath}/edit/post/${currentUnpublished.postId}'" >
                                <c:out value="${currentUnpublished.title}"/>
                            </button>
                        </p>
                    </c:forEach>

                    <h1>Edit Draft Pages</h1>
                    <hr/>
                    <c:forEach var="currentUnpublished" items="${incompletePageList}">
                        <p class="col-sm-12">
                            <button class="btn btn-default" onclick="location.href = '${pageContext.request.contextPath}/edit/page/${currentUnpublished.pageId}'" >
                                <c:out value="${currentUnpublished.title}"/>
                            </button>
                        </p>
                    </c:forEach>

                </div>
                <form class="form-horizontal" id="create-form" 
                      action="${pageContext.request.contextPath}/create/post" method="POST">
                    <div class="col-sm-7" style="margin: 1%; border: 2px solid black; box-shadow: 10px 10px 5px #888888; background-color: rgba(255,255,255,0.2);"">

                        <div class="form-group" id="author-row">
                            <div class="col-sm-3" style="text-align: left; font-size: 18px;">
                                <label>Authors:</label>
                            </div>

                            <c:forEach var="currentAuthor" items="${authorList}">
                                <label class="col-sm-2 control-label">
                                    <input class="col-sm-2" id="authro" name="author-chosen" type="checkbox" value="${currentAuthor.userId}">
                                    <c:out value="${currentAuthor.firstName}"/> <c:out value="${currentAuthor.lastName}"/>
                                </label>
                            </c:forEach>
                        </div>
                        <div class="form-group" id="title-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Title:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="text" name="title" class="col-sm-6"/>
                        </div>
                        <div class="form-group" id="synopsis-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Synopsis:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="text" class="col-sm-6" name="synopsis"/>
                        </div>
                        <div class="form-group" id="tags-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Tags:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="text" name="tags" class="col-sm-6" placeholder="#addTags, #likeThis, #etc"/>
                        </div>
                        <div class="form-group" id="publish-expire-date-row">
                            <label class="col-sm-3 control-label" id="post-label" style="text-align: left; font-size: 18px;">Post Date:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="date" name="post-date" id="post-input" class="col-sm-2"/>

                            <label class="col-sm-2 control-label" id="expire-label" style="text-align: left; font-size: 18px;">Expiration Date:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="date" name="expire-date" id="expire-input" class="col-sm-2"/>
                        </div>
                        <div class="form-group" id="lat-long-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Latitude:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="number" name="post-latitude" class="col-sm-2"/>

                            <label class="col-sm-2 control-label" style="text-align: left; font-size: 18px;">Longitude:</label>
                            <input style="box-shadow: 10px 10px 5px #888888;" required type="number" name="post-longitude" class="col-sm-2"/>
                        </div>

                        <div class="form-group col-sm-12" id="image-header-row">
                            <select style="box-shadow: 10px 10px 5px #888888;" required class="form-control" id="header-image-choice" name="header-image-choice">
                                <option value="" disabled selected>Choose a Header Image</option>
                                <c:forEach var="currentImage" items="${imageListHeader}">
                                    <option value="${currentImage.imageId}"><c:out value="${currentImage.imageUrl}"/></option>
                                </c:forEach>
                            </select>
                            <button style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default" type="button" data-toggle="modal" data-target="#myHeaderModal">Choose an Image</button>
                        </div>


                        <div class="form-group col-sm-12" id="image-preview-row">
                            <select style="box-shadow: 10px 10px 5px #888888;" required class="form-control" id="preview-image-choice" name="preview-image-choice">
                                <option value="" disabled selected>Choose a Preview Image</option>
                                <c:forEach var="currentImage" items="${imageListPreview}">
                                    <option value="${currentImage.imageId}"><c:out value="${currentImage.imageUrl}"/></option>
                                </c:forEach>
                            </select>
                            <button style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default" type="button" data-toggle="modal" data-target="#myPreviewModal">Choose an Image</button>
                        </div>

                        <div class="form-group col-sm-12" id="image-page-row">
                            <select style="box-shadow: 10px 10px 5px #888888;" required class="form-control" id="page-image-choice" name="page-image-choice">
                                <option value="" disabled selected>Choose a Page Image</option>
                                <c:forEach var="currentImage" items="${imageListPage}">
                                    <option value="${currentImage.imageId}"><c:out value="${currentImage.imageUrl}"/></option>
                                </c:forEach>
                            </select>
                            <button style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default" type="button" data-toggle="modal" data-target="#myPageModal">Choose an Image</button>
                        </div>


                        <div class="form-group col-sm-12" id="body-row">
                            <textarea id="mytextarea" name="body-content">Enter Content Here!</textarea>
                        </div>
                        <div class="form-group" id="complete-row">
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Is Complete:</label>
                            <input type="radio" class="col-sm-3" value="1" name="isComplete"/>
                            <label class="col-sm-3 control-label" style="text-align: left; font-size: 18px;">Not Complete:</label>
                            <input type="radio" class="col-sm-3" value="0" name="isComplete" checked/>
                        </div>
                        <div class="form-group" id="button-row">
                            <div class="col-sm-2"></div>
                            <button class="btn btn-info col-sm-3" style="box-shadow: 10px 10px 5px #888888;" type="button" onclick="location.href = '${pageContext.request.contextPath}'">Cancel</button>
                            <div class="col-sm-1"></div>
                            <button class="btn btn-info col-sm-3" style="box-shadow: 10px 10px 5px #888888;" id="submitButton" type="submit">Submit Draft</button>
                            <div class="col-sm-2"></div>
                        </div>
                    </div>
                    <div class="col-sm-2 sidenav" style="box-shadow: 10px 10px 5px #888888; background-color: rgba(255,255,255,0.2);">
                        <div class="form-group" id="type-decide">
                            <h1 style="color:black;">Page Type</h1>
                            <hr/>
                            <label style="color: black;" class="col-sm-6 control-label" for="static-page"><input onclick="showPage()" id="pageType" type="radio" name="page-type" value="1"/> Static Page</label>
                            <label style="color: black;" class="col-sm-6 control-label" for="blog-post"><input onclick="showBlog()" id="postType" type="radio" name="page-type" value="2"/> Blog Post</label>

                        </div>
                        <div class="form-group" id="category-show">
                            <h1 style="color:black;">Categories</h1>
                            <hr/>
                            <div id="post-categories-wrapper">
                                <div id="post-categories-display">
                                    <!-- <c:forEach var="postCategory" items="${postList}">
                                         <label class="col-sm-6 control-label" style="text-align: left;">
    
                                             <input class="col-sm-4" name="category-type-post" id="post-category-click" type="checkbox" value="${postCategory.postCategoryId}">
    
                                        <c:out value="${postCategory.categoryName}"/>
                                    </label>
                                    </c:forEach> -->

                                </div>
                                <ul class="list-group" id="errorMessages"></ul>
                                <button type="button" style="margin-top: 10px; box-shadow: 10px 10px 5px #888888;" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Search Category</button>


                            </div>
                            <div id="page-categories-wrapper">
                                <div id="page-categories-display">
                                    <c:forEach var="pageCategory" items="${pageList}">
                                        <label class="col-sm-6 control-label" style="text-align: left; color: black;">

                                            <input class="col-sm-4" type="radio" name="category-type-page" id="page-category-click" value="${pageCategory.pageCategoryId}">

                                            <c:out value="${pageCategory.categoryName}"/>
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
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
                            <input type="text" name="category-add" id="category-add" class="form-control" required/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" id="add-category" class="btn btn-default">Create Category</button>
                        </div>
                        <ul class="list-group" id="errorMessagesCreate"></ul>

                    </div>

                </div>
            </div>
            <div class="modal fade" id="myHeaderModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Choose Header Image</h4>
                        </div>
                        <div class="modal-body">
                            <c:forEach var="currentImage" items="${imageListHeader}">
                                <button class="btn btn-default" 
                                        style="height: 100px; width: 100px; background-image: url('${currentImage.imageUrl}'); background-size: cover;"
                                        onclick="addHeaderChoice('${currentImage.imageUrl}')"></button>
                            </c:forEach>
                        </div>
                        <div class="modal-footer">
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" id="add-header-image" class="btn btn-default" data-dismiss="modal">Confirm Choice</button>
                        </div>
                        <ul class="list-group" id="errorMessagesCreate"></ul>

                    </div>

                </div>
            </div>

            <div class="modal fade" id="myPreviewModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-header" style="background-color: rgba(255,255,255,0.2);">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Choose Preview Image</h4>
                        </div>
                        <div class="modal-body">
                            <c:forEach var="currentImage" items="${imageListPreview}">
                                <button class="btn btn-default" 
                                        style="height: 100px; width: 100px; background-image: url('${currentImage.imageUrl}'); background-size: cover;"
                                        onclick="addPreviewChoice('${currentImage.imageUrl}')"></button>
                            </c:forEach>
                        </div>
                        <div class="modal-footer">
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" id="add-preview-image" class="btn btn-default" data-dismiss="modal">Confirm Choice</button>
                        </div>
                        <ul class="list-group" id="errorMessagesCreate"></ul>

                    </div>

                </div>
            </div>

            <div class="modal fade" id="myPageModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Choose Page Image</h4>
                        </div>
                        <div class="modal-body">
                            <c:forEach var="currentImage" items="${imageListPage}">
                                <button class="btn btn-default" 
                                        style="height: 100px; width: 100px; background-image: url('${currentImage.imageUrl}'); background-size: cover;"
                                        onclick="addPageChoice('${currentImage.imageUrl}')"></button>
                            </c:forEach>
                        </div>
                        <div class="modal-footer">
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                            <button type="button" style="box-shadow: 10px 10px 5px #888888;" id="add-page-image" class="btn btn-default" data-dismiss="modal">Confirm Choice</button>
                        </div>
                        <ul class="list-group" id="errorMessagesCreate"></ul>

                    </div>

                </div>
            </div>

        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/forAuthor.js"></script>
    </body>
</html>