package com.admin.servlet;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/add_books")
@MultipartConfig
public class BooksAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      try
      {
          String bookName=request.getParameter("bname");
          String author=request.getParameter("author");
          String price=request.getParameter("price");
          String categories=request.getParameter("categories");
          String status=request.getParameter("status");
          Part part=request.getPart("bimg");
          String fileName=part.getSubmittedFileName();
          
          BookDtls b = new BookDtls(bookName,author,price,categories,status,fileName,"admin");
          BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
          
          boolean f = dao.addBooks(b);
          HttpSession session=request.getSession();
          if(f)
          {
              String path="D:\\Ebook\\web\\book";
              File file = new File(path);
              part.write(path +File.separator+ fileName);
              
              session.setAttribute("succMsg", "Book Add Successfully..");
              response.sendRedirect("admin/add_books.jsp");
          }
          else
          {
              session.setAttribute("failedMsg", "Something wrong..");
              response.sendRedirect("admin/add_books.jsp");
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}
