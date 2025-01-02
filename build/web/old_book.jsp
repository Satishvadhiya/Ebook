<%@page import="java.util.List"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="com.entity.User"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User : Old Book</title>
        <%@include file="allcomponent/allcss.jsp" %>
    </head>
    <body style="background-color: #f7f7f7">
        <%@include file="allcomponent/navbar.jsp" %>
        
        <c:if test="${empty userobj}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        
        <c:if test="${not empty succMsg }">
            <div class="alert alert-success text-center">
                <b>${succMsg}</b>
            </div>
            <c:remove var="succMsg" scope="session"/>
            
        </c:if>
                                
        <c:if test="${not empty failedMsg}">
            <div class="alert alert-danger text-center">
                <b>${failedMsg}</b>
            </div>
            <c:remove var="failedMsg" scope="session"/>
        </c:if>
        
        <div class="container p-5">
        <table class="table table-striped">
            <thead class="bg-dark text-white">
            <tr>
                <th scope="col">Book Name</th>
                <th scope="col">Author</th>
                <th scope="col">Price</th>
                <th scope="col">Category</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
                <%
                    User u =(User)session.getAttribute("userobj");
                    BookDAOImpl dao=new BookDAOImpl(DBConnect.getConn());
                    List<BookDtls> list=dao.getBookByOld(u.getEmail(),"Old");
                    for (BookDtls b:list) 
                    {%>
                        <tr>
                            <th><%=b.getBookName()%></th>
                            <td><%=b.getAuthor()%></td>
                            <td><%=b.getPrice()%></td>
                            <td><%=b.getBookCategory()%></td>
                            <td><a href="deleteoldbook?em=<%=b.getEmail()%>&id=<%=b.getbookId()%>" class="btn btn-danger btn-sm">Delete</a></td>
                        </tr>
                    <%}
                %>
            </tbody>
            </table>
            </div>
    </body>
</html>
