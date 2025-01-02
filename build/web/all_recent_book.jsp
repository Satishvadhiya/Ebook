<%@page import="com.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@page import="com.DB.DBConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Recent Book</title>
        <%@include file="allcomponent/allcss.jsp"%>
        <style type="text/css"> 
        .crd-ho:hover
        {
            background-color: #fcf7f7;
        }
        </style>
    </head>
    <body>
        <%
            User us=(User)session.getAttribute("userobj");
        %>
        <%@include file="allcomponent/navbar.jsp"%>
        <div class="container-fluid">
            <div class="row p-3">
                <%
                BookDAOImpl dao2 = new BookDAOImpl(DBConnect.getConn());
                List<BookDtls> list2=dao2.getAllRecentBook();
                for(BookDtls b:list2)
                {%>
                    <div class="col-md-3 p-1">
                    <div class="card crd-ho mt-2">
                    <div class="card-body text-center">
                        <img alt="" src="book/<%=b.getPhotoName()%>" style="width: 100px; height: 150px;" class="img-thumblin">
                        <p><%=b.getBookName()%></p>
                        <p><%=b.getAuthor()%></p>
                        <p><%if(b.getBookCategory().equals("Old"))
                            {
                                %>
                                Categories:<%=b.getBookCategory()%></p>
                                <div class="row ml-5">
                                <a href="view_books.jsp?bid=<%=b.getbookId()%>" class="btn btn-success btn-sm ml-4">View Details</a>
                                <a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%> <i class="fa-solid fa-indian-rupee-sign"></i></a>
                        </div>
                            <%}
                            else
                            {%>
                                <p>Categories:<%=b.getBookCategory()%></p>
                                <div class="row ml-4">
                                    <%
                                if (us == null) 
                                {%>
                                    <a href="login.jsp" class="btn btn-danger btn-sm ml-1"><i class="fas fa-cart-plus"></i> Add Cart</a>
                                <%
                                } 
                                else 
                                {%>
                                <a href="cart?bid=<%= b.getbookId() %>&uid=<%= us.getId() %>" class="btn btn-danger btn-sm ml-1"><i class="fas fa-cart-plus"></i> Add Cart</a>
                                <%}
                            %>
                                <a href="view_books.jsp?bid=<%=b.getbookId()%>" class="btn btn-success btn-sm ml-1">View Details</a>
                                <a href="" class="btn btn-danger btn-sm ml-1"><i class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
                            </div>
                            <%}
                            %>
                    </div>
                    </div>
                </div>
                <%}
                %>
            </div>
        </div>
    </body>
</html>
