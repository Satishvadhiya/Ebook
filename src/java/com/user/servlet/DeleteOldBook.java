package com.user.servlet;

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

@WebServlet("/deleteoldbook")
public class DeleteOldBook extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            String em=request.getParameter("em");
            int id=Integer.parseInt(request.getParameter("id"));
            BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
            boolean f=dao.oldBookDelete(em,"Old",id);
            
            HttpSession session=request.getSession();
            if(f)
            {
                session.setAttribute("succMsg", "Old Book Delete Successfully");
                response.sendRedirect("old_book.jsp");
            }
            else
            {
                session.setAttribute("failedMsg", "Something Wrong");
                response.sendRedirect("old_book.jsp");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
