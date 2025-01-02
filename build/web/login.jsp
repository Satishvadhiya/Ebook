<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EBook: Login</title>
        <%@include file="allcomponent/allcss.jsp" %>
    </head>
    <body style="background-color: #f0f1f2">
        <%@include file="allcomponent/navbar.jsp" %>
        <div class="container">
            <div class="row mt-2">
                <div class="col-md-4 offset-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center">Login</h3>
                            <c:if test="${not empty failedMsg}">
                                <h5 class="text-center text-danger">${failedMsg}</h5>
                                <c:remove var="failedMsg" scope="session" />
                            </c:if> 
                            <c:if test="${not empty succMsg}">
                                <h5 class="text-center text-success">${succMsg}</h5>
                                <c:remove var="succMsg" scope="session" />
                            </c:if>  
                                
                            <form action="login" method="post">
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Email Address</label>
                                  <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required" name="email">
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Password</label>
                                  <input type="password" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required" name="password">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary btn-block">Login</button><br>
                                </div>
                            </form>
                                <form method="GET">
                                <div class="text-center">
                                    <p style="color: #999999">&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash; OR &ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;</p>
                                </div>

                                <div class="text-center">
                                    <a href="google" class="btn btn-block btn-light"> <img src="img/google_icon-icon.ico" style="width: 19px; height: 19px;"> Sign With Google<br>
                                    </a><p>Don't have an account? <a href="register.jsp">Sign Up</a></p>
                                </div>
                                </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
