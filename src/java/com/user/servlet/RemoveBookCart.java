package com.user.servlet;

import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/removebook")
public class RemoveBookCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        int bid=Integer.parseInt(request.getParameter("bid"));
        int uid=Integer.parseInt(request.getParameter("uid"));
        int cid=Integer.parseInt(request.getParameter("cid"));
        
        CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
        boolean f=dao.deleteBook(bid,uid,cid);
        HttpSession session=request.getSession();
        if(f)
        {
            session.setAttribute("succMsg", "Your Item is Removed");
            response.sendRedirect("checkout.jsp");
        }
        else
        {
            session.setAttribute("failedMsg", "Something Wrong");
            response.sendRedirect("checkout.jsp");
        }
    }
}
