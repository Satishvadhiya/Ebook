<%@page import="java.util.List"%>
<%@page import="com.entity.Cart"%>
<%@page import="com.entity.User"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.CartDAOImpl"%>
<%@page import="com.DAO.CartDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <%@include file="allcomponent/allcss.jsp" %>
    </head>
    <body style="background-color: #f0f1f2">
        <%@include file="allcomponent/navbar.jsp" %>
        <c:if test="${empty userobj}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        
        <c:if test="${not empty succMsg}">
            <div class="alert alert-success text-center" role="alert">
                <b>${succMsg}</b>
            </div>
            <c:remove var="succMsg" scope="session" />
        </c:if>
        <c:if test="${not empty failedMsg}">
            <div class="alert alert-danger text-center" role="alert">
                <b>${failedMsg}</b>
            </div>
        <c:remove var="failedMsg" scope="session" />
        </c:if>
        
        
        <div class="container">
            <div class="row p-2">
                <div class="col-md-6">
                    <div class="card mt-4 bg-white">
                        <div class="card-body">
                            <h3 class="text-center text-success">Your Selected Items</h3>
                            <table class="table table-striped">
                            <thead class="bg-white text-dark">
                                <tr>
                                  <th scope="col">Book Name</th>
                                  <th scope="col">Author</th>
                                  <th scope="col">Price</th>
                                  <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    User u =(User)session.getAttribute("userobj");
                                    CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
                                    List<Cart> cart = dao.getBookByUser(u.getId());
                                    double totalPrice=0.0;
                                    for(Cart c:cart)
                                    {
                                        totalPrice=c.getTotalPrice();
                                        %>
                                        <tr>
                                            <th scope="row"><%=c.getBookName()%></th>
                                            <td><%=c.getAuthor()%></td>
                                            <td><%=c.getPrice()%></td>
                                            <td><a href="removebook?bid=<%=c.getBid()%>&uid=<%=c.getUserId()%>&cid=<%=c.getCid()%>" class="btn btn-danger text-white btn-sm">Remove</a></td>
                                        </tr>
                                    <%}
                                %>
                                <tr>
                                    <th>Total Price</th>
                                    <td></td>
                                    <td></td>
                                    <th><%=totalPrice%></th>
                                </tr>
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6">
                    <div class="card mt-4">
                        <div class="card-body">
                            <h3 class="text-center text-success">Your Details for Orders</h3>
                            <form action="order" method="post">
                                <input type="hidden" value="${userobj.id}" name="id"/>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                      <label for="inputEmail4">Name</label>
                                      <input type="text" class="form-control" id="inputEmail4" readonly="readonly" value="<%=u.getName()%>" name="username">
                                    </div>
                                    <div class="form-group col-md-6">
                                      <label for="inputPassword4">Email</label>
                                      <input type="email" class="form-control" id="inputPassword4" readonly="readonly" value="<%=u.getEmail()%>" name="email">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                      <label for="inputEmail4">Phone Number</label>
                                      <input type="text" class="form-control" id="inputEmail4"  value="<%=u.getPhno()%>" name="phno">
                                    </div>
                                    <div class="form-group col-md-6">
                                      <label for="inputPassword4">Address</label>
                                      <input type="text" class="form-control" placeholder="Enter Address" required="required" id="inputPassword4" name="address">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                      <label for="inputEmail4">Landmark</label>
                                      <input type="text" class="form-control" placeholder="Landmark" required="required" id="inputEmail4" value="" name="landmark">
                                    </div>
                                    <div class="form-group col-md-6">
                                      <label for="inputPassword4">City</label>
                                      <input type="text" class="form-control" placeholder="City" required="required" id="inputPassword4" value="" name="city">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                      <label for="inputEmail4">State</label>
                                      <input type="text" class="form-control" placeholder="State" required="required" id="inputEmail4" value="" name="state">
                                    </div>
                                    <div class="form-group col-md-6">
                                      <label for="inputPassword4">Zip</label>
                                      <input type="number" class="form-control" placeholder="PinCode" required="required" id="inputPassword4" value="" name="pincode">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label>Payment Type</label>
                                        <select class="form-control" name="payment">
                                            <option value="No Select">--Select--</option>
                                            <option value="COD">Cash On Delivery</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button class="btn btn-warning text-white">Order Now</button>
                                    <a href="index.jsp" class="btn btn-success">Continue Shopping</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </body>
</html>
