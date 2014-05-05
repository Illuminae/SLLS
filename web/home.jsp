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
                            
                            <button id="check_button" type="button" class="btn btn-default btn-sm btn-block hidden">Check now whether you have any pending borrowing requests!</button>
                            <p>Placeholder, Display new books or reports?</p>
                            <%--<%
                                RegisteredUserActiveRecord user = (RegisteredUserActiveRecord) request.getSession().getAttribute("currentUser");
                            %>
                            <%=user.getFirst_name()%>
                            <%=user.getLast_name()%>
                            <%=user.getUser_id()%>--%>


                        </div>
                    </div>
                </div>
                <div class ="col-lg-2">

                </div>
            </div>
        </div>
    </body>
</html>
