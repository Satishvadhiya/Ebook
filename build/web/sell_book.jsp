<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sell Book</title>
        <%@include file="allcomponent/allcss.jsp" %>
    </head>
    <body style="background-color: #f0f1f2">
        <c:if test="${empty userobj}">
            <c:redirect url="login.jsp" />
        </c:if>
        <%@include file="allcomponent/navbar.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <div class="card mt-5">
                        <div class="card-body">
                            <h4 class="text-center text-primary">Sell Old Book</h4>
                                <c:if test="${not empty succMsg}">
                                    <b><p class="text-center text-success">${succMsg}</p></b>
                                    <c:remove var="succMsg" scope="session"/>
                                </c:if>
                                <c:if test="${not empty failedMsg}">
                                <b><p class="text-center text-success">${failedMsg}</p></b>
                                    <c:remove var="failedMsg" scope="session"/>
                                </c:if>
                            <form action="add_old_book" method="post" enctype="multipart/form-data">
                                
                                <input type="hidden" value="${userobj.email}" name="user">
                                
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Book Name*</label>
                                  <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required" name="bname">
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Author Name*</label>
                                  <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required" name="author">
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Price*</label>
                                  <input type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required="required" name="price">
                                </div>
                                <div class="form-group">
                                  <label for="exampleFormControlFile1">Upload Photo</label>
                                  <input type="file" name="bimg" class="form-control-file" id="exampleFormControlFile1"/>
                                </div>
                                <div>
                                <button type="submit" class="btn btn-primary btn-block">Sell</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
