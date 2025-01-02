package com.admin.servlet;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delete")
public class BooksDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            int id=Integer.parseInt(request.getParameter("id"));
            BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
            boolean f = dao.deleteBooks(id);
            
            HttpSession session=request.getSession();
            if(f)
            {
                session.setAttribute("succMsg", "Book Delete Successfully..");
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
