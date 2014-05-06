<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" uri="/WEB-INF/tlds/slls" %>
<%-- 
    Document   : myBooks
    Created on : 03-May-2014, 03:46:32
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
                                <h3>SLLS - Your Book Collection</h3>
                            </div>
                            <script type="text/javascript">
                                $(document).ready(function() {
                                    $('#table_id').DataTable();
                                    $('#table_od').DataTable();
                                });
                            </script>
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
                                    <t:GenerateTable list="${requestScope.myBookList}">
                                        <tr>
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
                            <table id="table_od" class="table table-hover table-striped table-bordered display">
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
                                    <t:GenerateTable list="${requestScope.myLentBooksList}">
                                        <tr>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <c:set var="success" value="${requestScope.bookSuccess}" />
                        <c:choose>
                            <c:when test = "${success != null && success}">

                                <div class="alert alert-dismissable alert-success user-feedback">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Well done!</strong> Book successfully added!
                                </div>
                            </c:when>
                            <c:when test ="${success != null && !success}">

                                <div class="alert alert-dismissable alert-danger user-feedback">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Sorry!</strong> Something went wrong. Please try again.
                                </div>

                            </c:when>
                        </c:choose>
                        <form class="form-horizontal" action="Controller" method="post">
                            <fieldset>

                                <!-- Form Name -->
                                <legend>Do you have a new book to share?</legend>

                                <!-- Appended Input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="isbn"></label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <input id="isbn" name="isbn" class="form-control" placeholder="ISBN" type="text" required="">
                                            <span class="input-group-addon">ISBN</span>
                                        </div>

                                    </div>
                                </div>
                                <!-- Appended Input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="author"></label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <input id="author" name="author" class="form-control" placeholder="Author" type="text" required="">
                                            <span class="input-group-addon">Author</span>
                                        </div>

                                    </div>
                                </div>
                                <!-- Appended Input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="title"></label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <input id="title" name="title" class="form-control" placeholder="Title" type="text" required="">
                                            <span class="input-group-addon">Title</span>
                                        </div>

                                    </div>
                                </div>
                                <!-- Appended Input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="year"></label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <input id="year" name="year" class="form-control" placeholder="Year of Publication" type="text" required="">
                                            <span class="input-group-addon">Year</span>
                                        </div>

                                    </div>
                                </div>
                                <!-- Appended Input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="publisher"></label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <input id="publisher" name="publisher" class="form-control" placeholder="Publisher" type="text">
                                            <span class="input-group-addon">Publisher</span>
                                        </div>

                                    </div>
                                </div>
                                <!-- Button -->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="command"></label>
                                    <div class="col-md-4">
                                        <button id="command" name="command" value="add_book" class="btn btn-primary">Add Book</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
