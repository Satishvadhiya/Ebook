<%@page import="com.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DB.DBConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Ebook Index</title>
        <%@include file="allcomponent/allcss.jsp"%>
        <style type="text/css"> 
        .back-img 
        {
            background: url("img/b.jpg");
            height: 60vh;
            width: 100%;
            background-repeat: no-repeat;
            background-size: cover;  
        }
        .crd-ho:hover
        {
            background-color: #fcf7f7;
        }
        </style>
    </head>
    <body style="background-color: #f7f7f7">
        <%
            User us =(User) session.getAttribute("userobj");
            
        %>
        
        <%@include file="allcomponent/navbar.jsp"%>
        <div class="container-fluid back-img">
            <h1 class="text-center p-3 text-success"><i class="fas fa-book"></i> E-Book Management System</h1>
        </div>
        
        <!-- Start Recent Book -->
        <div class="container">
            <h3 class="text-center mt-4">Recent Book</h3>
            <div class="row">
            <%
                BookDAOImpl dao2 = new BookDAOImpl(DBConnect.getConn());
                List<BookDtls> list2=dao2.getRecentBook();
                for(BookDtls b:list2)
                {%>
                    <div class="col-md-3">
                    <div class="card crd-ho">
                    <div class="card-body text-center">
                        <img alt="" src="book/<%=b.getPhotoName()%>" style="width: 150px; height: 200px;" class="img-thumblin">
                        <p><%=b.getBookName()%></p>
                        <p><%=b.getAuthor()%></p>
                        <p><%if(b.getBookCategory().equals("Old"))
                            {
                                %>
                                Categories:<%=b.getBookCategory()%></p>
                                <div class="row">
                                <a href="view_books.jsp?bid=<%=b.getbookId()%>" class="btn btn-success btn-sm ml-5">View Details</a>
                                <a href="" class="btn btn-danger btn-sm ml-1"><i class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
                        </div>
                            <%}
                            else
                            {%>
                                <p>Categories:<%=b.getBookCategory()%></p>
                            <div class="row">
                                <%
                            if (us == null) 
                            {%>
                                <a href="login.jsp" class="btn btn-danger btn-sm ml-1">Add Cart</a>
                            <%
                            } 
                            else 
                            {%>
                            <a href="cart?bid=<%= b.getbookId() %>&uid=<%= us.getId() %>" class="btn btn-danger btn-sm ml-1">Add Cart</a>
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
            <div class="text-center mt-1">
            <a href="all_recent_book.jsp" class="btn btn-danger btn-sm text-center">View All</a>
            </div>
        </div>
        <!-- End Recent Book -->
        <hr>
        <!-- Start New Book -->
        <div class="container">
            <h3 class="text-center mt-4">New Book</h3>
            <div class="row">
                    <%
                        BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
                        List<BookDtls> list = dao.getNewBook();
                        for (BookDtls b:list) 
                        {%>
                        <div class="col-md-3">
                           <div class="card crd-ho">
                            <div class="card-body text-center">
                            <img alt="" src="book/<%=b.getPhotoName()%>" style="width: 150px; height: 200px;" class="img-thumblin">
                            <p><%=b.getBookName()%></p>
                            <p><%=b.getAuthor()%></p>
                            <p>Categories: <%=b.getBookCategory()%></p>
                            <div class="row">
                            <%
                            if (us == null) 
                            {%>
                                <a href="login.jsp" class="btn btn-danger btn-sm ml-1">Add Cart</a>
                            <%
                            } 
                            else 
                            {%>
                            <a href="cart?bid=<%= b.getbookId() %>&uid=<%= us.getId() %>" class="btn btn-danger btn-sm ml-1">Add Cart</a>
                            <%}
                            %>

                            <a href="view_books.jsp?bid=<%=b.getbookId()%>" class="btn btn-success btn-sm ml-1">View Details</a>
                            <a href="" class="btn btn-danger btn-sm ml-1"><i class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
                            </div>
                            </div>
                           </div> 
                        </div>
                        <%}
                    %>
            </div>
            <div class="text-center mt-1">
            <a href="all_new_book.jsp" class="btn btn-danger btn-sm text-center">View All</a>
            </div>
        </div>
        <!-- End New Book -->
        <hr>
        <!-- Start Old Book -->
        <div class="container">
            <h3 class="text-center mt-4">Old Book</h3>
            <div class="row">
                <%
                BookDAOImpl dao3 = new BookDAOImpl(DBConnect.getConn());
                List<BookDtls> list3=dao3.getOldBook();
                for(BookDtls b:list3)
                {%>
                    <div class="col-md-3">
                    <div class="card crd-ho">
                    <div class="card-body text-center">
                        <img alt="" src="book/<%=b.getPhotoName()%>" style="width: 150px; height: 200px;" class="img-thumblin">
                        <p><%=b.getBookName()%></p>
                        <p><%=b.getAuthor()%></p>
                        <p>Categories: <%=b.getBookCategory()%></p>
                            <div class="row">
                                <a href="view_books.jsp?bid=<%=b.getbookId()%>" class="btn btn-success btn-sm ml-5">View Details</a>
                                <a href="" class="btn btn-danger btn-sm ml-1"><i class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
                            </div>
                    </div>
                    </div>
                </div>
                <%}
            %>
            </div>
            <div class="text-center mt-1">
            <a href="all_old_book.jsp" class="btn btn-danger btn-sm text-center">View All</a>
            </div>
        </div>
        <!-- End Old Book -->
        <%@include file="allcomponent/footer.jsp" %>
    </body>
</html>
