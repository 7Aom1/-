package com.book;
import com.core.ConnDB;
import com.book.BookForm;

import java.sql.*;
import java.util.*;

public class BookDB {
    private ConnDB conn=new ConnDB();
//查询数据
public Collection query(){
	BookForm bookForm=null;
	Collection bookColl=new ArrayList();
	String sql="select * from bookInfo";
	
	System.out.println("图书查询时的SQL："+sql);
	ResultSet rs=conn.executeQuery(sql);
	try {
	    while (rs.next()) {
	        bookForm=new BookForm();
	        bookForm.setId(Integer.valueOf(rs.getString(1)));   
	        bookForm.setBarCode(rs.getString(2));       
	        bookForm.setBookName(rs.getString(3));
	        bookForm.setAuthor(rs.getString(4));
	        bookForm.setIsbn(rs.getString(5));
	        bookForm.setPrice(Float.valueOf(rs.getString(6)));  //此处必须进行类型转换
	        bookForm.setTypeID(Integer.valueOf(rs.getString(7)));   
	        bookColl.add(bookForm);
	    }
	} catch (SQLException ex) {
		ex.printStackTrace();
	}
	conn.close();
	return bookColl;
}

//查询数据
public Collection queryCondition(String strif){
BookForm bookForm=null;
Collection bookColl=new ArrayList();
String sql="";

if(strif != null){
	//******根据条形码、书名或者作者，实现查询******
	sql="select * from bookInfo where "+strif+"";   
}else{
	sql="select * from bookInfo";
}
System.out.println("图书查询时的SQL："+sql);
ResultSet rs=conn.executeQuery(sql);
try {
  while (rs.next()) {
      bookForm=new BookForm();
      bookForm.setBarCode(rs.getString(2));  
      //******封装图书信息******
      bookForm.setBookName(rs.getString(3));
      bookForm.setAuthor(rs.getString(4));
      bookForm.setIsbn(rs.getString(5));
      bookForm.setPrice(Float.valueOf(rs.getString(6)));  //此处必须进行类型转换
      bookForm.setTypeID(rs.getInt(7));
      bookColl.add(bookForm);
  }
} catch (SQLException ex) {
	ex.printStackTrace();
}
conn.close();
return bookColl;
}

//用于修改的查询
public BookForm queryM(BookForm bookForm1){
	BookForm bookForm=null;
	String sql="select * from bookInfo where id='"+bookForm1.getId()+"'";
	System.out.println("修改时的SQL："+sql);
	ResultSet rs=conn.executeQuery(sql);
	try {
	    while (rs.next()) {
	        bookForm=new BookForm();
	        bookForm.setId(Integer.valueOf(rs.getString(1)));
	        bookForm.setBarCode(rs.getString(2));
	        bookForm.setBookName(rs.getString(3));
	        bookForm.setAuthor(rs.getString(4));
	        bookForm.setIsbn(rs.getString(5));
	        bookForm.setPrice(Float.valueOf(rs.getString(6)));  //此处必须进行类型转换
	        bookForm.setTypeID(Integer.valueOf(rs.getString(7)));
	    }
	} catch (SQLException ex) {
	}
	conn.close();
	return bookForm;
}

//用于借阅的查询
public BookForm queryB(String f,String key){
BookForm bookForm=null; 
//******修改下列sql语句的错误******
String sql="select * from bookInfo where " + f + " like'"+key+"%'";
System.out.println("查询借阅信息时的SQL："+sql);
ResultSet rs=conn.executeQuery(sql);
try {
    if (rs.next()) {
        bookForm=new BookForm();
        bookForm.setId(Integer.valueOf(rs.getString(1)));
        //******封装图书信息******
        bookForm.setBarCode(rs.getString(2));
        bookForm.setBookName(rs.getString(3));
        bookForm.setAuthor(rs.getString(4));
        bookForm.setIsbn(rs.getString(5));
        bookForm.setPrice(Float.valueOf(rs.getString(6)));   //此处必须进行类型转换
        bookForm.setTypeID(Integer.valueOf(rs.getString(7)));  //此处必须进行类型转换
    }
} catch (SQLException ex) {
}
conn.close();
return bookForm;
}

//添加数据
public int insert(BookForm bookForm){
	String sql1="SELECT * FROM bookInfo WHERE bookname='"+bookForm.getBookName()+"'";
	ResultSet rs = conn.executeQuery(sql1);
	String sql = "";
	int falg = 0;
	try {
	if (rs.next()) {
	    falg = 2;
	} else {
	    sql ="Insert into bookInfo (barcode,bookname,author,ISBN,price,typeID) values('"+bookForm.getBarCode()+"','"+bookForm.getBookName()+"','"+bookForm.getAuthor()+"','"+bookForm.getIsbn()+"','"+bookForm.getPrice()+"','"+bookForm.getTypeID()+"')";
	    falg = conn.executeUpdate(sql);
	    System.out.println("添加图书的SQL：" + sql);
	    conn.close();
	}
	} catch (SQLException ex) {
	falg = 0;
	}
	System.out.println("falg:"+falg);
	return falg;
}

//修改数据
public int update(BookForm bookForm){
	String sql="Update bookInfo set barcode='"+bookForm.getBarCode()+"',"
			+  "bookname='"+bookForm.getBookName()+"',"
			+  "author='"+bookForm.getAuthor()+"',"
			+ "isbn='"+bookForm.getIsbn()+"',"
			+ "price='"+bookForm.getPrice()+"', "
			+ "typeID='"+bookForm.getTypeID()+"'"
			+ "where id='"+bookForm.getId()+"'";
	int falg=conn.executeUpdate(sql);
	System.out.println("修改数据时的SQL："+sql);
	conn.close();
	return falg;
}

//删除数据
public int delete(BookForm bookForm){
String sql="delete from bookInfo where id='"+bookForm.getId()+"'";
int falg=conn.executeUpdate(sql);
System.out.println("删除时的SQL："+sql);
System.out.println("删除时的falg："+falg);
return falg;
}

}
