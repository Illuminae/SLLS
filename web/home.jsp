<%@page import="active_record.RegisteredUserActiveRecord"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : home
    Created on : 02-May-2014, 16:18:43
    Author     : Erik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SLLS - Welcome!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href= "css/bootstrap.min.css" rel = "stylesheet">
        <script type="text/javascript" src="/SLLS/js/jquery.js"></script>
        <link href= "css/styles.css" rel = "stylesheet">
    </head>
    <body>
        <jsp:include page="/components/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class ="col-lg-10">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Welcome back!</h3>
                            </div>
                            <c:set var="user_type" value="${sessionScope.userType}"/>
                            <c:choose>
                                <c:when test="${user_type == 1 ||user_type == 2}">
                                    <button id="check_button" type="button" class="btn btn-default btn-lg btn-block">Check now whether you have any pending borrowing requests!</button>
                                </c:when>
                                <c:when test="${user_type == 3 ||user_type == 4}">
                                    ..and have a nice day!
                                </c:when>
                            </c:choose>

                        </div>
                    </div>
                </div>
                <div class ="col-lg-2">

                </div>
            </div>
        </div>
        <script type='text/javascript'>
            $(document).ready(function() {
                $('#check_button').click(function() {
                    window.location.href = "/SLLS/Controller?command=borrow_requests";
                });
            });
        </script>
    </body>
</html>
