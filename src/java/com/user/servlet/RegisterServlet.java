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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try
        {
            String name=req.getParameter("fname");
            String email=req.getParameter("email");
            String phno=req.getParameter("phno");
            String password=req.getParameter("password");
            String check=req.getParameter("check");
            
            User us=new User();
            
            us.setName(name);
            us.setEmail(email);
            us.setPhno(phno);
            us.setPassword(password);
            
            HttpSession session=req.getSession();
            
            if(check!=null)
            {
                UserDAOImpl dao=new UserDAOImpl(DBConnect.getConn());
                boolean f2=dao.checkUser(email, phno);
                if(f2)
                {
                    boolean f=dao.userRegister(us);
                    if(f)
                    {
                        session.setAttribute("succMsg","Account Created Successfully..");
                        res.sendRedirect("register.jsp");
                    }
                    else
                    {
                        session.setAttribute("failedMsg","Something wrong..");
                        res.sendRedirect("register.jsp");
                    }
                }
                else
                {
                    session.setAttribute("failedMsg","User Already Exits Try Another Email Id and Phone Number");
                    res.sendRedirect("register.jsp");
                }
            }
            else
            {
                session.setAttribute("failedMsg","Please Check Agree & Terms Condition..");
                res.sendRedirect("register.jsp");
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

}
