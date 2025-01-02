package com.DAO;

import com.entity.BookDtls;
import com.entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO
{
    private Connection conn;
    public CartDAOImpl (Connection conn)
    {
        this.conn=conn;
    }
    
    @Override
    public boolean addCart(Cart c) 
    {
        boolean f=false;
        
        try
        {
            String sql="insert into cart(bid,uid,bookname,author,price,total_price)values(?,?,?,?,?,?)";
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setInt(1, c.getBid());
            pst.setInt(2, c.getUserId());
            pst.setString(3, c.getBookName());
            pst.setString(4, c.getAuthor());
            pst.setDouble(5, c.getPrice());
            pst.setDouble(6, c.getTotalPrice());
            int i = pst.executeUpdate();
            if(i==1)
            {
                f=true;
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Error"+e.toString());
        }
        return f;
    }

    @Override
    public List<Cart> getBookByUser(int userId) 
    {
        List<Cart> list = new ArrayList<>();
        Cart c=null;
        double totalPrice=0;
        try
        {
            String sql="select * from cart where uid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                c=new Cart();
                c.setCid(rs.getInt(1));
                c.setBid(rs.getInt(2));
                c.setUserId(rs.getInt(3));
                c.setBookName(rs.getString(4));
                c.setAuthor(rs.getString(5));
                c.setPrice(rs.getDouble(6));
                totalPrice=totalPrice+rs.getDouble(7);
                c.setTotalPrice(totalPrice);
                list.add(c);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteBook(int bid,int uid,int cid) 
    {
        boolean f=false;
        try
        {
            String sql="delete from cart where bid=? and uid=? and cid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bid);
            pst.setInt(2,uid);
            pst.setInt(3, cid);
            int i=pst.executeUpdate();
            if(i==1)
            {
                f=true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  f;
    }
    
    
}
