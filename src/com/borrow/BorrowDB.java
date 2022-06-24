package com.borrow;

import com.core.ConnDB;
import com.book.BookForm;
import com.borrow.BorrowForm;
import com.reader.ReaderForm;

import java.util.*;
import java.sql.*;
import java.util.Date;


public class BorrowDB {
    ConnDB conn = new ConnDB();
    
    public int insert() {
        String sql = "INSERT INTO borrow(bookid) values(1) ";
        int ret = conn.executeUpdate(sql);
        return ret;
    }
    //*****************************ͼ�����******************************
    public int insertBorrow(ReaderForm readerForm,BookForm bookForm){
    	int falg = 0;
    			
    	//******��η�ֹ���߽����ظ���ͼ��?******
    	
    	String sql1="select * from borrow "
    			+ "where bookID ='"+bookForm.getId()+"' and readerID='"+readerForm.getId()+"'";
    	ResultSet rs1=conn.executeQuery(sql1);
    	try{
    		if(rs1.next()){
    			return falg;
    		}else{
    			
		//��ȡϵͳ����
		Date dateU=new Date();
		java.sql.Date date=new java.sql.Date(dateU.getTime());
		
		//******����ͼ�������룬��ѯͼ��Ŀɽ�����days******
		String sql2="select days from bookInfo, booktype "
				+ "where bookInfo.typeID=bookType.id and"
				+ "bookInfo.barcode='"+bookForm.getBarCode()+"'";
		ResultSet rs2=conn.executeQuery(sql2);
		int days=0;
		try {
		         if (rs2.next()) {
		              days = rs2.getInt(1);
		         }
		} catch (SQLException ex) {
		}
    		
		//******����黹ʱ��******
		String date_str=String.valueOf(date);//��ʱ����з���
		String dd = date_str.substring(8,10);
		String DD = date_str.substring(0,8)+ String.valueOf(Integer.parseInt(dd) + days);
		java.sql.Date backTime= java.sql.Date.valueOf(DD);
		
		//******��readerID,bookID,borrowTime,backTime����borrow��******
		String sql ="Insert into borrow(readerID,bookID) values("+readerForm.getId()+","+bookForm.getId()+")";
		falg = conn.executeUpdate(sql);
		System.out.println("���ͼ�������Ϣ��SQL��" + sql);
		conn.close();
    		}
    	}
    	catch (SQLException ex){	
    	}
    	return falg;
  
}
    
      //*************************************ͼ��̽�*********************************
      public int renew(int id){
          String sql0="SELECT bookid FROM borrow WHERE id="+id+"";
          ResultSet rs1=conn.executeQuery(sql0);
          int flag=0;
          try {
            if (rs1.next()) {
                //��ȡϵͳ����
                Date dateU = new Date();
                java.sql.Date date = new java.sql.Date(dateU.getTime());
                //******��ѯ��ǰbookid�Ŀɽ�����days******
                String sql1 = "select days from bookinfo,booktype"
                		+ "where bookinfo.typeid=booktype.id and"
                		+ "bookinfo.id=" + rs1.getInt(1)+"";
                ResultSet rs = conn.executeQuery(sql1);
                int days = 0;
                try {
                    if (rs.next()) {
                        days = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                }
                //******���¼���黹ʱ��******
                String date_str = String.valueOf(date);
                String dd = date_str.substring(8, 10);
                String DD = date_str.substring(0, 8) +  String.valueOf(Integer.parseInt(dd) + days);
                java.sql.Date backTime = java.sql.Date.valueOf(DD);
                //******���¸��µ�ǰbookid�Ĺ黹ʱ��backtime******
                String sql = "UPDATE borrow set backtime='" + backTime +"' where id=" + id +"";
                flag = conn.executeUpdate(sql);
            }
          } catch (Exception ex1) {}
          conn.close();
          return flag;
      }
      
      //*************************************ͼ��黹*********************************
      public int back(int id){
        int flag=0;
        try {
        	    //******��borrow����ɾ����ǰid�Ľ�����Ϣ��id�ĺ�����ʲô��borrow���id******
                String sql="delete from borrow where id = "+id+"";
                flag = conn.executeUpdate(sql);
        }catch (Exception ex1) {
        }
          conn.close();
          return flag;
      }

    //*****************************查询图书借阅信息************************
    public Collection borrowinfo(String barcode){
        //******根据读者条形码，查询读者的借阅id，图书名，借阅时间，归还时间，价格和作者******
        String sql= "select borrow.id,bookName,borrowTime,backTime,price,author "
                + "from borrow,bookinfo"
                + "where borrow.bookID = bookinfo.id and"
                +"borrow.readerID in (select id from reader where barcode='"+barcode+"')";

      ResultSet rs=conn.executeQuery(sql);
      Collection coll=new ArrayList();
      BorrowForm form=null;
      try {
          while (rs.next()) {
              form = new BorrowForm();
              form.setId(Integer.valueOf(rs.getString(1)));
              //******��װͼ�������Ϣ******
              form.setBookName(rs.getString(2));  
              form.setBorrowTime(rs.getString(3));
              form.setBackTime(rs.getString(4));
              form.setPrice(Float.valueOf(rs.getString(5)));
              form.setAuthor(rs.getString(6));
              
              System.out.println(rs.getString(2));
              
              coll.add(form);
          }
      } catch (SQLException ex) {
          System.out.println("������Ϣ��"+ex.getMessage());
      }
      conn.close();
      return coll;
      }
      
    //*************************��������******************************************
    public Collection bremind(){
    Date dateU = new Date();
    java.sql.Date date = new java.sql.Date(dateU.getTime());
    
    //******��Ӧ��ʱ��backTimeС�ڵ��ڵ�ǰʱ��date�����ҵ��ڵ�ͼ������������������ʱ��͹黹ʱ����Ϣ******
    String sql="select bookinfo.bookname,reader.name,borrow.borrowTime,borrow.backTime "
    		+"from bookinfo,borrow,reader"
    		+"where bookinfo.id = borrow.bookID and reader.id = borrow.readerID and borrow.backTime <='"+date+"'";
    ResultSet rs=conn.executeQuery(sql);
    System.out.println("��ʱ���ѵ�SQL��"+sql);
    Collection coll=new ArrayList();
    BorrowForm form=null;
    try {
        while (rs.next()) {
            form = new BorrowForm();
            //******��װ���ڵ�ͼ�������Ϣ******
            form.setBookName(rs.getString(1));
            form.setReaderName(rs.getString(2));
            form.setBorrowTime(rs.getString(3));
            form.setBackTime(rs.getString(4));
            coll.add(form);
            System.out.println("ͼ�����ƣ�"+rs.getString(1));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    conn.close();
    return coll;
}
    
//*************************ͼ����Ĳ�ѯ******************************************
public Collection borrowQuery(String strif){
    String sql="";  
    if(strif!="all" && strif!=null && strif!=""){
    	//******Ϊ����sql����������strif��******
        sql="select bookinfo.bookname, reader.name, borrow.borrowTime, borrow.backTime, bookinfo.price, bookinfo.author "
            +"from bookinfo,borrow,reader "	
            +"where bookinfo.id = borrow.bookID and reader.id = borrow.readerID and " + strif ;
    }else{
        sql="SELECT bookinfo.bookname, reader.name, borrow.borrowTime, borrow.backTime, bookinfo.price, bookinfo.author "
		   +"from bookinfo,borrow,reader "
		   +"where bookinfo.id = borrow.bookID and reader.id = borrow.readerID";
}
    
ResultSet rs=conn.executeQuery(sql);
System.out.println("ͼ����Ĳ�ѯ��SQL��"+sql);
Collection coll=new ArrayList();
BorrowForm form=null;
try {
    while (rs.next()) {
        form = new BorrowForm();
        //******��װͼ�������Ϣ******
        form.setBookName(rs.getString(1));
        form.setReaderName(rs.getString(2));
        form.setBorrowTime(rs.getString(3));
        form.setBackTime(rs.getString(4));
        form.setPrice(Float.valueOf(rs.getString(5)));
        form.setAuthor(rs.getString(6));
        coll.add(form);
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
conn.close();
return coll;
    }
  
}
