<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : sitenotice
    Created on : 26-Mar-2014, 16:19:34
    Author     : Erik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href= "../css/bootstrap.min.css" rel = "stylesheet">
        <link href= "../css/styles.css" rel = "stylesheet">
        <title>Site Notice</title>
    </head>
    <body>
        <jsp:include page="../components/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class ="col-lg-12">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Copyright</h3>
                                <p>(c) Erik Baumert, Stafford 2014</p>
                                <p>Premium Membership is 10 Euros and will be collected from your provided bank account.</p>
                                <p>Cost of late return is 2 Euros per occasion.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
