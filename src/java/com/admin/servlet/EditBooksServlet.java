package com.admin.servlet;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editbooks")
public class EditBooksServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            int id=Integer.parseInt(request.getParameter("id"));
            String bookName=request.getParameter("bname");
            String author=request.getParameter("author");
            String price=request.getParameter("price");
            String status=request.getParameter("status");
          
            BookDtls b =new BookDtls();
            b.setbookId(id);
            b.setBookName(bookName);
            b.setAuthor(author);
            b.setPrice(price);
            b.setStatus(status);
            
            BookDAOImpl dao=new BookDAOImpl(DBConnect.getConn());
            boolean f=dao.updateEditBooks(b);
            
            HttpSession session=request.getSession();
            if(f)
            {
                session.setAttribute("succMsg", "Book Update Successfully..");
                response.sendRedirect("admin/all_books.jsp");
            }
            else
            {
                session.setAttribute("failedMsg", "Something Wrong..");
                response.sendRedirect("admin/all_books.jsp");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
