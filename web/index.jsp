<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Specialised Literature Lending System - SLLS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href= "css/bootstrap.min.css" rel = "stylesheet">
        <link href= "css/styles.css" rel = "stylesheet">

    </head>
    <body>

        <jsp:include page="/components/header.jsp"/>
        
       <div class ="container">
            <div class ="row">
                <div class ="col-lg-7">
                    <div class = "panel panel-default">
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Latest book release!<small> Posted 20 March 2014</small></h3>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sit amet arcu lorem. Duis sit amet volutpat tortor. Praesent luctus feugiat ante, id facilisis diam ornare vel. Vivamus ac felis ornare magna mollis posuere nec eu dolor. Fusce ultricies egestas libero, eget aliquet nisl commodo quis. Donec at tincidunt felis. Nullam suscipit metus faucibus ipsum viverra scelerisque.</p>

                        </div>
                        <div class ="panel-body">
                            <div class ="page-header">
                                <h3>Yet another post!<small> Posted 10 March 2014</small></h3>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sit amet arcu lorem. Duis sit amet volutpat tortor. Praesent luctus feugiat ante, id facilisis diam ornare vel. Vivamus ac felis ornare magna mollis posuere nec eu dolor. Fusce ultricies egestas libero, eget aliquet nisl commodo quis. Donec at tincidunt felis. Nullam suscipit metus faucibus ipsum viverra scelerisque.</p>

                        </div>
                    </div>

                </div>
                <div class ="col-lg-5">
                    <div class="panel panel-default">
                        <div class ="panel-body">
                            <c:set var="success" value="${requestScope.insertSuccess}" />
                            <c:choose>
                                <c:when test = "${success != null && success}">

                                    <div class="alert alert-dismissable alert-success user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Well done!</strong> You successfully registered with SLLS.
                                    </div>
                                </c:when>
                                <c:when test ="${success != null && !success}">
                                   
                                    <div class="alert alert-dismissable alert-danger user-feedback">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>Sorry!</strong> Something went wrong. Please try again.
                                    </div>
                                    
                                </c:when>
                            </c:choose>
                            <div class ="page-header">
                                <h3><small> Register now!</small></h3>
                            </div>
                            <form action="Controller" method="post">
                                <div class="form-group">
                                    <label class="control-label sr-only" for="firstName">First Name</label>
                                    <input class="form-control input-sm" name="firstName" type="text" id="firstName" placeholder="First Name">
                                </div>
                                <div class="form-group">
                                    <label class="control-label sr-only" for="lastName">Last Name</label>
                                    <input class="form-control input-sm" name="lastName" type="text" id="lastName" placeholder="Last Name">
                                </div>
                                <div class="form-group">
                                    <label class="control-label sr-only" for="userName">Username</label>
                                    <input class="form-control input-sm" name="userName" type="text" id="userName" placeholder="Username">
                                </div>
                                <div class="form-group">
                                    <label class="control-label sr-only" for="pWord">Password</label>
                                    <input class="form-control input-sm" name="pWord" type="password" id="pWord" placeholder="Password">
                                </div>
                                <div class="form-group form-inline">
                                    <label class="control-label sr-only" for="zipCode">ZIPCode</label>
                                    <input class="form-control input-sm" name="zipCode" type="text" id="zipCode" placeholder="ZIP-Code">
                                    <label class="control-label sr-only" for="city">City</label>
                                    <input class="form-control input-sm" name="city" type="text" id="city" placeholder="City">
                                </div>
                                <div class="form-group form-inline">
                                    <label class="control-label sr-only" for="street">Street</label>
                                    <input class="form-control input-sm" name="street" type="text" id="street" placeholder="Street">
                                    <label class="control-label sr-only" for="houseNo">House No.</label>
                                    <input class="form-control input-sm" name="houseNo" type="text" id="houseNo" placeholder="House No.">
                                </div>
                                <div class="form-group">
                                    <label class="control-label sr-only" for="iban">IBAN</label>
                                    <input class="form-control input-sm" name="iban" type="text" id="iban" placeholder="IBAN">
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary btn-block btn-sm" name="command" value="Register"/>
                                </div>
                            </form>
                        </div>
                    </div>     
                </div>

            </div>

        </div>

        <script type= "text/javascript" src = "js/jquery.js"></script>
        <script type= "text/javascript" src ="js/bootstrap.js"></script> 
    </body>
</html>
