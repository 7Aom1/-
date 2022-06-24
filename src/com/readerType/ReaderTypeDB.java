package com.readerType;

import com.core.ConnDB;
import com.readerType.ReaderTypeForm;

import java.util.*;
import java.sql.*;

public class ReaderTypeDB {
    private ConnDB conn=new ConnDB();
    
//��ѯ����
public Collection query(String strif){
    ReaderTypeForm readerTypeForm=null;
    Collection readerTypeColl=new ArrayList();
    String sql="";
    if(strif!="all" && strif!=null && strif!=""){
        sql="select * from readerType where "+strif+"";
    }else{
        sql="select * from readerType";
    }
    ResultSet rs=conn.executeQuery(sql);
    try {
        while (rs.next()) {
            readerTypeForm=new ReaderTypeForm();
            readerTypeForm.setId(Integer.valueOf(rs.getString(1)));
            readerTypeForm.setName(rs.getString(2));
            readerTypeForm.setNumber(rs.getInt(3));
            readerTypeColl.add(readerTypeForm);
        }
    } catch (SQLException ex) {
    }
    conn.close();
    return readerTypeColl;
}

//�����޸ĵĲ�ѯ
public ReaderTypeForm queryM(ReaderTypeForm readerTypeForm){
    ReaderTypeForm readerTypeForm1=null;
    String sql="select * from readerType where id="+readerTypeForm.getId()+"";
    System.out.println("�޸�ʱ��SQL��"+sql);
    ResultSet rs=conn.executeQuery(sql);
    try {
        while (rs.next()) {
            readerTypeForm1=new ReaderTypeForm();
            readerTypeForm1.setId(Integer.valueOf(rs.getString(1)));
            readerTypeForm1.setName(rs.getString(2));
            readerTypeForm1.setNumber(rs.getInt(3));
            System.out.println(rs.getInt(3));
        }
    } catch (SQLException ex) {
    }
    conn.close();
    return readerTypeForm1;
}

//�������
public int insert(ReaderTypeForm readerTypeForm){
	
String sql1="SELECT * FROM readerType WHERE name='"+readerTypeForm.getName()+"'";
ResultSet rs = conn.executeQuery(sql1);
String sql = "";
int falg = 0;
try {
    if (rs.next()) {
        falg = 2;
    } else {
    	//******�뽫�������ƺͿɽ���������readerType��******
        sql ="Insert into readerType(name,number) values('"+readerTypeForm.getName()+"',"+readerTypeForm.getNumber()+")";
        falg = conn.executeUpdate(sql);
        System.out.println("��Ӷ������͵�SQL��" + sql);
        conn.close();
    }
} catch (SQLException ex) {
    falg = 0;
}
System.out.println("falg:"+falg);
return falg;
}

//�޸�����
public int update(ReaderTypeForm readerTypeForm){
	
//******���ݶ�������ID���޸Ķ����������Ϳɽ�����******
String sql="Update readerType set name='"+readerTypeForm.getName()+"',number="+readerTypeForm.getNumber()+" where id="+readerTypeForm.getId()+"";
int falg=conn.executeUpdate(sql);
System.out.println("�޸�����ʱ��SQL��"+sql);
conn.close();
return falg;
}

//ɾ������
public int delete(ReaderTypeForm readerTypeForm){
	//*******���ݶ�������ID��ɾ������������Ϣ******
	String sql="Delete from readerType where id="+readerTypeForm.getId()+"";
	int flag=conn.executeUpdate(sql);
	System.out.println("ɾ��ʱ��SQL��"+sql);

	return flag;	
}
}