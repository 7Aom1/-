package com.book;
import com.core.ConnDB;
import com.book.BookForm;

import java.sql.*;
import java.util.*;

public class BookDB {
    private ConnDB conn=new ConnDB();
//��ѯ����
public Collection query(){
	BookForm bookForm=null;
	Collection bookColl=new ArrayList();
	String sql="select * from bookInfo";
	
	System.out.println("ͼ���ѯʱ��SQL��"+sql);
	ResultSet rs=conn.executeQuery(sql);
	try {
	    while (rs.next()) {
	        bookForm=new BookForm();
	        bookForm.setId(Integer.valueOf(rs.getString(1)));   
	        bookForm.setBarCode(rs.getString(2));       
	        bookForm.setBookName(rs.getString(3));
	        bookForm.setAuthor(rs.getString(4));
	        bookForm.setIsbn(rs.getString(5));
	        bookForm.setPrice(Float.valueOf(rs.getString(6)));  //�˴������������ת��
	        bookForm.setTypeID(Integer.valueOf(rs.getString(7)));   
	        bookColl.add(bookForm);
	    }
	} catch (SQLException ex) {
		ex.printStackTrace();
	}
	conn.close();
	return bookColl;
}

//��ѯ����
public Collection queryCondition(String strif){
BookForm bookForm=null;
Collection bookColl=new ArrayList();
String sql="";

if(strif != null){
	//******���������롢�����������ߣ�ʵ�ֲ�ѯ******
	sql="select * from bookInfo where "+strif+"";   
}else{
	sql="select * from bookInfo";
}
System.out.println("ͼ���ѯʱ��SQL��"+sql);
ResultSet rs=conn.executeQuery(sql);
try {
  while (rs.next()) {
      bookForm=new BookForm();
      bookForm.setBarCode(rs.getString(2));  
      //******��װͼ����Ϣ******
      bookForm.setBookName(rs.getString(3));
      bookForm.setAuthor(rs.getString(4));
      bookForm.setIsbn(rs.getString(5));
      bookForm.setPrice(Float.valueOf(rs.getString(6)));  //�˴������������ת��
      bookForm.setTypeID(rs.getInt(7));
      bookColl.add(bookForm);
  }
} catch (SQLException ex) {
	ex.printStackTrace();
}
conn.close();
return bookColl;
}

//�����޸ĵĲ�ѯ
public BookForm queryM(BookForm bookForm1){
	BookForm bookForm=null;
	String sql="select * from bookInfo where id='"+bookForm1.getId()+"'";
	System.out.println("�޸�ʱ��SQL��"+sql);
	ResultSet rs=conn.executeQuery(sql);
	try {
	    while (rs.next()) {
	        bookForm=new BookForm();
	        bookForm.setId(Integer.valueOf(rs.getString(1)));
	        bookForm.setBarCode(rs.getString(2));
	        bookForm.setBookName(rs.getString(3));
	        bookForm.setAuthor(rs.getString(4));
	        bookForm.setIsbn(rs.getString(5));
	        bookForm.setPrice(Float.valueOf(rs.getString(6)));  //�˴������������ת��
	        bookForm.setTypeID(Integer.valueOf(rs.getString(7)));
	    }
	} catch (SQLException ex) {
	}
	conn.close();
	return bookForm;
}

//���ڽ��ĵĲ�ѯ
public BookForm queryB(String f,String key){
BookForm bookForm=null; 
//******�޸�����sql���Ĵ���******
String sql="select * from bookInfo where " + f + " like'"+key+"%'";
System.out.println("��ѯ������Ϣʱ��SQL��"+sql);
ResultSet rs=conn.executeQuery(sql);
try {
    if (rs.next()) {
        bookForm=new BookForm();
        bookForm.setId(Integer.valueOf(rs.getString(1)));
        //******��װͼ����Ϣ******
        bookForm.setBarCode(rs.getString(2));
        bookForm.setBookName(rs.getString(3));
        bookForm.setAuthor(rs.getString(4));
        bookForm.setIsbn(rs.getString(5));
        bookForm.setPrice(Float.valueOf(rs.getString(6)));   //�˴������������ת��
        bookForm.setTypeID(Integer.valueOf(rs.getString(7)));  //�˴������������ת��
    }
} catch (SQLException ex) {
}
conn.close();
return bookForm;
}

//�������
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
	    System.out.println("���ͼ���SQL��" + sql);
	    conn.close();
	}
	} catch (SQLException ex) {
	falg = 0;
	}
	System.out.println("falg:"+falg);
	return falg;
}

//�޸�����
public int update(BookForm bookForm){
	String sql="Update bookInfo set barcode='"+bookForm.getBarCode()+"',"
			+  "bookname='"+bookForm.getBookName()+"',"
			+  "author='"+bookForm.getAuthor()+"',"
			+ "isbn='"+bookForm.getIsbn()+"',"
			+ "price='"+bookForm.getPrice()+"', "
			+ "typeID='"+bookForm.getTypeID()+"'"
			+ "where id='"+bookForm.getId()+"'";
	int falg=conn.executeUpdate(sql);
	System.out.println("�޸�����ʱ��SQL��"+sql);
	conn.close();
	return falg;
}

//ɾ������
public int delete(BookForm bookForm){
String sql="delete from bookInfo where id='"+bookForm.getId()+"'";
int falg=conn.executeUpdate(sql);
System.out.println("ɾ��ʱ��SQL��"+sql);
System.out.println("ɾ��ʱ��falg��"+falg);
return falg;
}

}
