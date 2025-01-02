package com.DAO;

import com.entity.Book_Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookOrderImpl implements BookOrderDAO
{
    private Connection conn;

    public BookOrderImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int getOrderNo() 
    {
        int i=1;
        try
        {
            String sql="select * from book_order";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public boolean saveOrder(List<Book_Order> blist) 
    {
        boolean f =false;
        try
        {
            String sql="insert into book_order(order_id,user_name,email,address,phone,book_name,author,price,payment)"
                     +"values(?,?,?,?,?,?,?,?,?)";
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            
            for(Book_Order b:blist)
            {
                pst.setString(1,b.getOrderId());
                pst.setString(2,b.getUserName());
                pst.setString(3,b.getEmail());
                pst.setString(4,b.getFulladd());
                pst.setString(5,b.getPhno());
                pst.setString(6,b.getBookName());
                pst.setString(7,b.getAuthor());
                pst.setString(8,b.getPrice());
                pst.setString(9,b.getPaymenttype());
                pst.addBatch();
            }
            int[] count=pst.executeBatch();
            conn.commit();
            f=true;
            conn.setAutoCommit(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<Book_Order> getBook(String email) 
    {
        List<Book_Order> list = new ArrayList<Book_Order>();
        Book_Order o=null;
        try
        {
            String sql="select * from book_order where email=? order by order_id";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,email);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                o=new Book_Order();
                o.setId(rs.getInt(1));
                o.setOrderId(rs.getString(2));
                o.setUserName(rs.getString(3));
                o.setEmail(rs.getString(4));
                o.setFulladd(rs.getString(5));
                o.setPhno(rs.getString(6));
                o.setBookName(rs.getString(7));
                o.setAuthor(rs.getString(8));
                o.setPrice(rs.getString(9));
                o.setPaymenttype(rs.getString(10));
                list.add(o);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Book_Order> getAllBook() 
    {
        List<Book_Order> list = new ArrayList<Book_Order>();
        Book_Order o=null;
        try
        {
            String sql="select * from book_order order by order_id";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                o=new Book_Order();
                o.setId(rs.getInt(1));
                o.setOrderId(rs.getString(2));
                o.setUserName(rs.getString(3));
                o.setEmail(rs.getString(4));
                o.setFulladd(rs.getString(5));
                o.setPhno(rs.getString(6));
                o.setBookName(rs.getString(7));
                o.setAuthor(rs.getString(8));
                o.setPrice(rs.getString(9));
                o.setPaymenttype(rs.getString(10));
                list.add(o);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    
}
