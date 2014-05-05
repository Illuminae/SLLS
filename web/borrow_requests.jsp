<%@page import="active_record.RegisteredUserActiveRecord"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" uri="/WEB-INF/tlds/slls" %>
<%-- 
    Document   : borrow_requests
    Created on : 05-May-2014, 02:30:41
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
        <script type="text/javascript" src="/SLLS/js/dataTables.js"></script>
        <script type="text/javascript" src="js/dataTables.bootstrap.js"></script>
        <link href="/SLLS/css/dataTables.bootstrap.css" rel="stylesheet">
        <title>Lending Requests</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class ="col-lg-12">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Someone would like to lend one of your books!</h3>
                            </div>

                            <table id="table_id" class="table table-hover table-striped table-bordered display">
                                <thead>
                                    <tr>
                                        <th>
                                            ISBN
                                        </th>
                                        <th>
                                            Title
                                        </th>
                                        <th>
                                            Author
                                        </th>
                                        <th>
                                            Year
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <t:GenerateTable list="${requestScope.myRequestsBookList}">
                                        <tr class="selectable">
                                            <td>
                                                ${isbn}
                                            </td>
                                            <td>
                                                ${title}
                                            </td>
                                            <td>
                                                ${author}
                                            </td>
                                            <td>
                                                ${pub_year}
                                            </td>
                                        </tr>
                                    </t:GenerateTable>
                                </tbody>
                            </table>
                            <div class="btn-group btn-group-justified hidden">
                                <a id="approve_button" class="btn btn-success">Approve Request.</a>
                                <a id="reject_button" class="btn btn-danger">Reject request.</a>
                            </div>
                            <c:set var="appsuccess" value="${requestScope.appSuccess}" />
                            <c:choose>
                                <c:when test = "${appsuccess != null && appsuccess}">
                                    <% RegisteredUserActiveRecord user = (RegisteredUserActiveRecord)request.getAttribute("requestingUser"); %>
                                    <div class="alert alert-dismissable alert-success user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Well done!</strong> Please send the book to the following address:
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>User Data</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr><td><%= user.getFirst_name()%> <%= user.getLast_name() %></td></tr>
                                                <tr><td><%= user.getStreet() %> <%= user.getHouse_no() %></td></tr>
                                                <tr><td><%=user.getZip_code()%> <%= user.getTown() %></td></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:when>
                                <c:when test ="${appsuccess != null && !appsuccess}">

                                    <div class="alert alert-dismissable alert-danger user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Sorry!</strong> something went wrong. Please try again.
                                    </div>

                                </c:when>
                            </c:choose>
                            <c:set var="rejsuccess" value="${requestScope.rejSuccess}" />
                            <c:choose>
                                <c:when test = "${rejsuccess != null && rejsuccess}">

                                    <div class="alert alert-dismissable alert-success user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Request rejected!</strong> 
                                    </div>
                                </c:when>
                                <c:when test ="${rejsuccess != null && !rejsuccess}">

                                    <div class="alert alert-dismissable alert-danger user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Sorry!</strong> Action could not be processed.
                                    </div>

                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#table_id').DataTable();

                $('.selectable').click(function() {
                    $('.selectedRow').removeClass("selectedRow");
                    $(this).addClass("selectedRow");
                    $('.btn-group-justified').removeClass("hidden");
                    //$('#lend_button').show();
                });

                $('#approve_button').click(function() {
                    window.location.href = "/SLLS/Controller?command=approve_request&isbn=" + $('.selectedRow').get(0).childNodes[1].innerText;
                });
                $('#reject_button').click(function() {
                    window.location.href = "/SLLS/Controller?command=reject_request&isbn=" + $('.selectedRow').get(0).childNodes[1].innerText;
                });

            });
        </script>
    </body>
</html>
