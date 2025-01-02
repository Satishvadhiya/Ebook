package com.user.servlet;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/add_old_book")
@MultipartConfig
public class AddOldBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try
      {
          String bookName=request.getParameter("bname");
          String author=request.getParameter("author");
          String price=request.getParameter("price");
          String categories="Old";
          String status="Active";
          Part part=request.getPart("bimg");
          String fileName=part.getSubmittedFileName();
          
          String useremail=request.getParameter("user");
          
          BookDtls b = new BookDtls(bookName,author,price,categories,status,fileName,useremail);
          BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
          
          boolean f = dao.addBooks(b);
          HttpSession session=request.getSession();
          if(f)
          {
              String path="D:\\Ebook\\web\\book";
              File file = new File(path);
              part.write(path +File.separator+ fileName);
              
              session.setAttribute("succMsg", "Old Book Add Successfully..");
              response.sendRedirect("sell_book.jsp");
          }
          else
          {
              session.setAttribute("failedMsg", "Something wrong..");
              response.sendRedirect("sell_book.jsp");
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}
