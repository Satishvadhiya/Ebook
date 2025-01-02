package com.DAO;

import com.entity.BookDtls;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO
{
    private Connection conn;

    public BookDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public BookDAOImpl() 
    {
        
    }
    
    @Override
    public boolean addBooks(BookDtls b) 
    {
        boolean f=false;
        try
        {
            String sql="insert into book_dtls (bookname,author,price,bookcategory,status,photo_name,email) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, b.getBookName());
            pst.setString(2, b.getAuthor());
            pst.setString(3, b.getPrice());
            pst.setString(4, b.getBookCategory());
            pst.setString(5, b.getStatus());
            pst.setString(6, b.getPhotoName());
            pst.setString(7, b.getEmail());
            
            int i = pst.executeUpdate();
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
    public List<BookDtls> getAllBooks() 
    {
        List<BookDtls> list = new ArrayList<BookDtls>();
        BookDtls b =null;
        try
        {
            String sql="select * from book_dtls order by bookid";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                
                list.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public BookDtls getBookById(int id) 
    {
        BookDtls b=null;
        try
        {
            String sql="select * from book_dtls where bookid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean updateEditBooks(BookDtls b) 
    {
        boolean f=false;
        try
        {
            String sql="update book_dtls set bookname=?,author=?,price=?,status=? where bookid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, b.getBookName());
            pst.setString(2, b.getAuthor());
            pst.setString(3, b.getPrice());
            pst.setString(4, b.getStatus());
            pst.setInt(5, b.getbookId());
            
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
    public boolean deleteBooks(int id) 
    {
        boolean f=false;
        try
        {
            String sql="delete from book_dtls where bookid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
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
    public List<BookDtls> getNewBook() 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b = null;
        try
        {
            String sql="select * from book_dtls where bookcategory=? and status=? order by bookid DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "New");
            pst.setString(2, "Active");
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next() && i<=4)
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<BookDtls> getRecentBook() 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b = null;
        try
        {
            String sql="select * from book_dtls where status=? order by bookid DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "Active");
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next() && i<=4)
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<BookDtls> getOldBook() 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b = null;
        try
        {
            String sql="select * from book_dtls where bookcategory=? and status=? order by bookid DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "Old");
            pst.setString(2, "Active");
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next() && i<=4)
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
                i++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<BookDtls> getAllRecentBook() 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b = null;
        try
        {
            String sql="select * from book_dtls where status=? order by bookid DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "Active");
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<BookDtls> getAllNewBook() 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b = null;
        try
        {
            String sql="select * from book_dtls where bookcategory=? and status=? order by bookid DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "New");
            pst.setString(2, "Active");
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<BookDtls> getAllOldBook() 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b = null;
        try
        {
            String sql="select * from book_dtls where bookcategory=? and status=? order by bookid DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "Old");
            pst.setString(2, "Active");
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<BookDtls> getBookByOld(String email, String category) 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b=null;
        try
        {
            String sql="select * from book_dtls where email=? and bookcategory=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, category);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean oldBookDelete(String email,String category,int id) 
    {
        boolean f=false;
        try
        {
            String sql="delete from book_dtls where email=? and bookcategory=? and bookid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, category);   
            pst.setInt(3, id);
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
    public List<BookDtls> getBookBySearch(String ch) 
    {
        List<BookDtls> list = new ArrayList<>();
        BookDtls b=null;
        try
        {
            String sql="select * from book_dtls where bookname like ? and status=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, "%"+ch);
            pst.setString(2,"Active");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                b=new BookDtls();
                b.setbookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getString(4));
                b.setBookCategory(rs.getString(5));
                b.setStatus(rs.getString(6));
                b.setPhotoName(rs.getString(7));
                b.setEmail(rs.getString(8));
                list.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    
}
