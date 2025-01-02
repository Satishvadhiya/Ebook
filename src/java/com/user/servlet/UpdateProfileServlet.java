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

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
            int id=Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("fname");
            String email=request.getParameter("email");
            String phno=request.getParameter("phno");
            String password=request.getParameter("password");
            
            User us=new User();
            us.setId(id);
            us.setName(name);
            us.setEmail(email);
            us.setPhno(phno);
            
            HttpSession session =request.getSession();
            UserDAOImpl dao=new UserDAOImpl(DBConnect.getConn());
            boolean f=dao.checkpassword(id, password);
            if(f)
            {
                boolean f2=dao.updateProfile(us);
                if(f2)
                {
                    session.setAttribute("succMsg","Profile Update Successfully..");
                    response.sendRedirect("edit_profile.jsp");
                }
                else
                {
                    session.setAttribute("failedMsg","Something Wrong");
                    response.sendRedirect("edit_profile.jsp");
                }
            }
            else
            {
                session.setAttribute("passMsg","Your Password is Incorrect");
                response.sendRedirect("edit_profile.jsp");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
