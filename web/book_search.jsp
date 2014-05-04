<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" uri="/WEB-INF/tlds/slls" %>
<%-- 
    Document   : book_search
    Created on : 02-May-2014, 23:15:41
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
        <title>Find your Book here!</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class ="col-lg-12">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Specified Literature Lending System Book Search</h3>
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
                                        <th>
                                            Status
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <t:GenerateTable list="${requestScope.bookList}">
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
                                            <td>
                                                ${status}
                                            </td>
                                        </tr>
                                    </t:GenerateTable>
                                </tbody>
                            </table>
                            <button id="lend_button" type="button" class="btn btn-primary btn-sm btn-block hidden">I want this one!</button>
                            <c:set var="success" value="${requestScope.lendSuccess}" />
                            <c:choose>
                                <c:when test = "${success != null && success}">

                                    <div class="alert alert-dismissable alert-success user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Well done!</strong> The owner has been notified about your request.
                                    </div>
                                </c:when>
                                <c:when test ="${success != null && !success}">

                                    <div class="alert alert-dismissable alert-danger user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Sorry!</strong> Something went wrong. Please try again.
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
                    $('#lend_button').removeClass("hidden");
                    //$('#lend_button').show();
                });

                $('#lend_button').click(function() {
                    window.location.href = "/SLLS/Controller?command=lend_book&isbn=" + $('.selectedRow').get(0).childNodes[1].innerText;
                });
            });
        </script>
    </body>
</html>
