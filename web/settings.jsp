<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : settings
    Created on : 06-May-2014, 14:45:50
    Author     : Erik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href= "/SLLS/css/bootstrap.min.css" rel = "stylesheet">
        <link href= "/SLLS/css/styles.css" rel = "stylesheet">
        <script type="text/javascript" src="/SLLS/js/jquery.js"></script>
        <title>User Settings</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class ="col-lg-12">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <c:set var="user_type" value="${sessionScope.userType}"/>
                            <c:set var="success" value="${requestScope.purchaseSuccess}"/>
                            <c:if test="${!null && success}">
                                <div class="alert alert-dismissable alert-success user-feedback">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Well Done!</strong> Premium fee will be collected from your account! Please re-login for changes to take effect.
                                </div>
                            </c:if>
                            <c:choose>
                                <c:when test="${user_type == 2}">
                                    <div class ="page-header">
                                        <h3>Congratulations - you are a Premium user! Enjoy reading!</h3>
                                    </div>
                                </c:when>
                                <c:when test="${user_type == 1}">
                                    <h3>Purchase Premium now and lend books for up to 4 weeks!</h3>
                                    <br>
                                    <hr>
                                    <br>
                                    <form method="post" action="Controller">
                                        <button name="command" value="purchase" type="submit" class="btn btn-primary btn-sm btn-block">Purchase Premium!</button>
                                    </form>
                                </c:when>
                                <c:when test="${user_type == 3 ||user_type == 4}">
                                    <h3>You are an Administrator, there's no options for you here.</h3>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
