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
                            <script type="text/javascript">
                            $(document).ready( function () {
                            $('#table_id').DataTable();
                            } );
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
                                    <t:GenerateTable list="${requestScope.bookList}">
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
