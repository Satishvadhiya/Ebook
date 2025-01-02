package com.DAO;

import com.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO{
    
    private Connection conn;

    public UserDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public boolean userRegister(User us) {
        
        boolean f=false;
        
        try
        {
            String sql="insert into user(name,email,phno,password) values(?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,us.getName());
            pst.setString(2,us.getEmail());
            pst.setString(3,us.getPhno());
            pst.setString(4,us.getPassword());
            
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
        
        return f;
    }

    @Override
    public User login(String email, String password) 
    {   
        User us = null;
        try
        {
            String sql="select * from user where email=? and password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,email);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next())
            {
                us=new User();
                us.setId(rs.getInt(1));
                us.setName(rs.getString(2));
                us.setEmail(rs.getString(3));
                us.setPhno(rs.getString(4));
                us.setPassword(rs.getString(5));
                us.setAddress(rs.getString(6));
                us.setLandmark(rs.getString(7));
                us.setCity(rs.getString(8));
                us.setState(rs.getString(9));
                us.setPincode(rs.getString(10));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return us;
    }

    @Override
    public boolean checkpassword(int id,String ps) 
    {
        boolean f=false;
        try
        {
            String sql="select * from user where id=? and password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, ps);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                f=true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public boolean updateProfile(User us) 
    {
        boolean f=false;
        
        try
        {
            String sql="update user set name=?,email=?,phno=? where id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,us.getName());
            pst.setString(2,us.getEmail());
            pst.setString(3,us.getPhno());
            pst.setInt(4, us.getId());
            
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
        
        return f;
    }

    @Override
    public boolean checkUser(String em, String phno) 
    {
        boolean f =true;
        try
        {
            String sql="select * from user where email=? or phno=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,em);
            pst.setString(2,phno);            
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                f=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
     
    
}
