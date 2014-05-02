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
        <link href= "css/styles.css" rel = "stylesheet">
    </head>
    <body>
        <jsp:include page="/components/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class ="col-lg-7">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Welcome back!</h3>
                            </div>
                            <p>Placeholder, Display new books or reports?</p>
                        </div>
                    </div>
                </div>
                <div class ="col-lg-5">

                </div>
            </div>
        </div>
    </body>
</html>
