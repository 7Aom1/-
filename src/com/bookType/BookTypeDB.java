package com.bookType;

import com.core.ConnDB;
import com.bookType.BookTypeForm;

import java.sql.*;
import java.util.*;

public class BookTypeDB {
    private ConnDB conn=new ConnDB();
    
//查询数据
public Collection query(String strif){
    BookTypeForm bookTypeForm=null;
    Collection bookTypeColl=new ArrayList();
    String sql="";
    if(strif!="all" && strif!=null && strif!=""){
        sql="select * from bookType where "+strif+"";
    }else{
        sql="select * from bookType";
    }
    ResultSet rs=conn.executeQuery(sql);
    try {
        while (rs.next()) {
            bookTypeForm=new BookTypeForm();
            bookTypeForm.setId(Integer.valueOf(rs.getString(1)));
            bookTypeForm.setTypeName(rs.getString(2));
            bookTypeForm.setDays(rs.getInt(3));
            bookTypeColl.add(bookTypeForm);
            System.out.print(bookTypeForm.getTypeName());
        }
    } catch (SQLException ex) {
    }
    conn.close();
    return bookTypeColl;
}
//用于修改的查询
public BookTypeForm queryM(BookTypeForm bookTypeForm){
    BookTypeForm bookTypeForm1=null;
    String sql="select * from bookType where id="+bookTypeForm.getId()+"";
    System.out.println("修改时的SQL："+sql);
    ResultSet rs=conn.executeQuery(sql);
    try {
        while (rs.next()) {
            bookTypeForm1=new BookTypeForm();
            bookTypeForm1.setId(Integer.valueOf(rs.getString(1)));
            bookTypeForm1.setTypeName(rs.getString(2));
            bookTypeForm1.setDays(rs.getInt(3));
        }
    } catch (SQLException ex) {
    }
    conn.close();
    return bookTypeForm1;
}
//添加数据
public int insert(BookTypeForm bookTypeForm){
String sql1="SELECT * FROM bookType WHERE typename='"+bookTypeForm.getTypeName()+"'";
ResultSet rs = conn.executeQuery(sql1);
String sql = "";
int falg = 0;
try {
	//******判断图书类型是否存在******
    if (rs.next()) { 
        falg = 2;
    } else {
    	//******将图书的类型名称和可借天数插入bookType表******
        sql ="Insert into bookType(typename,days) values('"+bookTypeForm.getTypeName()+"',"+bookTypeForm.getDays()+")";
        falg = conn.executeUpdate(sql);
        System.out.println("添加图书类型的SQL：" + sql);
        conn.close();
    }
} catch (SQLException ex) {
    falg = 0;
}
System.out.println("falg:"+falg);
return falg;
}

//修改数据
public int update(BookTypeForm bookTypeForm){
//******根据图书类型ID，修改图书类型名和可借天数******
String sql="Update bookType set typename='"+bookTypeForm.getTypeName()+"',days="+bookTypeForm.getDays()+" where id="+bookTypeForm.getId()+"";
int falg=conn.executeUpdate(sql);
System.out.println("修改数据时的SQL："+sql);
conn.close();
return falg;
}

//删除数据
public int delete(BookTypeForm bookTypeForm){
	//******根据图书类型id,删除图书类型信息******
	String sql="Delete from bookType where id="+bookTypeForm.getId()+"";
	int falg=conn.executeUpdate(sql);
	System.out.println("删除时的SQL："+sql);
	return falg;
}

}
