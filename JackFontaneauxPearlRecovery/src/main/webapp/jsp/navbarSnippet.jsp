<nav class="navbar navbar-inverse navbar-fixed-top myFont">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Home</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        Bios <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach var="currentBioPage" items="${theHand.bioPages}">
                            <li>
                                <a href="${pageContext.request.contextPath}/category/${currentBioPage.pageId}">
                                    <c:out value="${currentBioPage.navName}"></c:out>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        FAQs <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach var="currentFaqPage" items="${theHand.faqPages}">
                            <li>
                                <a href="${pageContext.request.contextPath}/category/${currentFaqPage.pageId}">
                                    <c:out value="${currentFaqPage.navName}"></c:out>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        Contact <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach var="currentContactPage" items="${theHand.contactPages}">
                            <li>
                                <a href="${pageContext.request.contextPath}/category/${currentContactPage.pageId}">
                                    <c:out value="${currentContactPage.navName}"></c:out>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        Events <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach var="currentEventPage" items="${theHand.eventPages}">
                            <li>
                                <a href="${pageContext.request.contextPath}/category/${currentEventPage.pageId}">
                                    <c:out value="${currentEventPage.navName}"></c:out>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        About Us <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach var="currentAboutPage" items="${theHand.aboutPages}">
                            <li>
                                <a href="${pageContext.request.contextPath}/category/${currentAboutPage.pageId}">
                                    <c:out value="${currentAboutPage.navName}"></c:out>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">
                            Admin <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/adminPost">Posts</a></li>
                            <li><a href="${pageContext.request.contextPath}/adminPage">Pages</a></li>
                            <li><a href="${pageContext.request.contextPath}/displayUserList">Users</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <li><a href="${pageContext.request.contextPath}/author">Create</a></li>
                </sec:authorize>

            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li>
                    <form class="form" role="form" action="${pageContext.request.contextPath}/search" method="POST">
                        <button type="submit" class="btn navbar-btn"><span class="glyphicon glyphicon-search"></span></button>
                        <input type="text" name="search" id="search" placeholder="search">
                    </form>
                </li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li style="color:white;" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">${pageContext.request.userPrincipal.name}<span class="caret"></span></a>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <input type="hidden" value="Admin" id="role"/>
                    </sec:authorize>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/j_spring_security_logout' />" > Logout</a></li>
                        </ul> 
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>