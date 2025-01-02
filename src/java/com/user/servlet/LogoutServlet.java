package com.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            HttpSession session =request.getSession();
            session.removeAttribute("userobj");
            
            HttpSession session2=request.getSession();
            session2.setAttribute("succMsg", "Logout Successfully..");
            response.sendRedirect("login.jsp");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
