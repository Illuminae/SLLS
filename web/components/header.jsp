<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class = "navbar navbar-inverse">
    <div class = "container">
        <div class = "navbar-header">
            <button type="button" class = "navbar-toggle" data-toggle = "collapse" data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:set var="login" value="${sessionScope.isVerified}"/>
            <c:choose>
                <c:when test="${login == null || !login}">
                    <a href="/SLLS/index.jsp" class ="navbar-brand">SLLS</a>
                </c:when>
                <c:when test="${login != null || login}">
                    <a href="/SLLS/home.jsp" class ="navbar-brand">SLLS</a>
                </c:when>
            </c:choose>
        </div>
        <div class = "navbar-collapse navbar-responsive-collapse collapse" style ="height: auto;">
            <ul class = "nav navbar-nav navbar-left">
                <c:choose>
                    <c:when test="${login == null || !login}">
                        <li class = "active"><a href = "/SLLS/index.jsp">Home</a></li>
                    </c:when>
                    <c:when test="${login != null ||login}">
                        <li class = "active"><a href = "/SLLS/home.jsp">Home</a></li>
                    </c:when>
                </c:choose>
                <li><a href = "/SLLS/public/downloads.jsp">Downloads</a></li>
                <li><a href = "/SLLS/public/sitenotice.jsp">Site Notice</a></li>
                    <c:choose>
                        <c:when test="${login == null || !login}">
                        </c:when>
                        <c:when test="${login || login != null}">
                            <c:set var="user_type" value="${sessionScope.userType}"/>
                            <c:choose>
                                <c:when test="${user_type == 1 ||user_type == 2}">
                                <li><a href = "#">Book Search</a></li>
                                <li><a href = "#">User Settings</a></li>
                                <li><a href = "#">My Books</a></li>
                                </c:when>
                                <c:when test="${user_type == 3 ||user_type == 4}">
                                <li><a href = "#">Review Section</a></li>
                                <li><a href = "#">Admin Panel</a></li>
                                </c:when>
                            </c:choose>
                        </c:when>
                    </c:choose>
            </ul>
            <c:choose>
                <c:when test="${login == null || !login}">
                    <ul class ="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
                            <div class="dropdown-menu" style="padding: 15px; padding-bottom: 15px;">
                                <form action="Controller" method="post">
                                    <input id="user_username" style="margin-bottom: 15px;" type="text" name="UserUsername" size="30" placeholder="Username"/>
                                    <input id="user_password" style="margin-bottom: 15px;" type="password" name="UserPword" size="30" placeholder="Password"/>
                                    <button class="btn btn-default" style="clear: left; width: 100%; height: 32px; padding: 0px" type="submit" name="command" value="login"/>Login</button>
                                </form>
                            </div>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${login != null || login}">
                    <ul class ="nav navbar-nav navbar-right">
                        <li class="active"><a href="/SLLS/Controller?command=logout">Logout</a></li>
                    </ul>
                </c:when>
            </c:choose>

        </div>
    </div> 
</div>
