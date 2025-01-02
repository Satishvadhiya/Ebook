<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Address</title>
        <%@include file="allcomponent/allcss.jsp" %>
    </head>
    <body style="background-color: #f0f1f2">
        <%@include file="allcomponent/navbar.jsp" %>
        <div class="container">
            <div class="row p-3">
                <div class="col-md-6 offset-md-3">
                    <div class="card mt-5">
                        <div class="card-body">
                            <h4 class="text-center text-primary">Add Address</h4>
                            <form>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                      <label for="inputPassword4">Address</label>
                                      <input type="text" class="form-control" id="inputPassword4">
                                    </div>
                                    <div class="form-group col-md-6">
                                      <label for="inputEmail4">Landmark</label>
                                      <input type="text" class="form-control" id="inputEmail4" value="">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                      <label for="inputPassword4">City</label>
                                      <input type="text" class="form-control" id="inputPassword4" value="">
                                    </div>
                                    <div class="form-group col-md-4">
                                      <label for="inputEmail4">State</label>
                                      <input type="text" class="form-control" id="inputEmail4" value="">
                                    </div>
                                    <div class="form-group col-md-4">
                                      <label for="inputPassword4">Pincode</label>
                                      <input type="number" class="form-control" id="inputPassword4" value="">
                                    </div>
                                </div>
                                <div class="text-center">
                                    <a class="btn btn-warning text-white">Add Address</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
