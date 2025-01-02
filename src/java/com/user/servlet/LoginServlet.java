package com.user.servlet;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
            HttpSession session = request.getSession();
            
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            
            if("admin@gmail.com".equals(email) && "admin".equals(password))
            {
                User us = new User();
                us.setName("Admin");
                session.setAttribute("userobj", us);
                response.sendRedirect("admin/home.jsp");
            }
            else
            {
                User us=dao.login(email, password);
                if(us!=null)
                {
                    session.setAttribute("userobj", us);
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    session.setAttribute("failedMsg", "Email & Password Invalid");
                    response.sendRedirect("login.jsp");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
