package com.user.servlet;

import com.DAO.BookOrderImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Book_Order;
import com.entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
            HttpSession session = request.getSession();
            
            int id=Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("username");
            String email=request.getParameter("email");
            String phno=request.getParameter("phno");
            String address=request.getParameter("address");
            String landmark=request.getParameter("landmark");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String pincode=request.getParameter("pincode");
            String paymenttype=request.getParameter("payment");
           
            String fullAdd=address+","+landmark+","+city+","+state+","+pincode;
            
            CartDAOImpl dao=new CartDAOImpl(DBConnect.getConn());
            List<Cart> blist =dao.getBookByUser(id);
            
            if(blist.isEmpty())
            {
                session.setAttribute("failedMsg", "Add Items");
                response.sendRedirect("checkout.jsp");
            }
            else
            {
                BookOrderImpl dao2=new BookOrderImpl(DBConnect.getConn());
                int i=dao2.getOrderNo();
                Book_Order o=null;
                ArrayList<Book_Order> orderList=new ArrayList<Book_Order>();

                for(Cart c:blist)
                {
                    o=new Book_Order();
                    o.setOrderId("BOOK-ORD-00"+i);
                    o.setUserName(name);
                    o.setEmail(email);
                    o.setPhno(phno);
                    o.setFulladd(fullAdd);
                    o.setBookName(c.getBookName());
                    o.setAuthor(c.getAuthor());
                    o.setPrice(c.getPrice()+"");
                    o.setPaymenttype(paymenttype);
                    orderList.add(o);
                    i++;
                }

                if("No Select".equals(paymenttype))
                {
                    session.setAttribute("failedMsg", "Choose Payment Method");
                    response.sendRedirect("checkout.jsp");
                }
                else
                {
                    boolean f=dao2.saveOrder(orderList);
                    if(f)
                    {
                        session.setAttribute("succMsg", "Choose Payment Method");
                        response.sendRedirect("order_success.jsp");
                    }
                    else
                    {
                        session.setAttribute("failedMsg", "Your Order Failed");
                        response.sendRedirect("order_success.jsp");
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
