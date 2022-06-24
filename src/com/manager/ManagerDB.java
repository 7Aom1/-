package com.manager;

import com.core.*;

import java.util.*;
import java.sql.*;
import java.*;

public class ManagerDB {
    private ConnDB conn = new ConnDB();

    //����Ա�����֤
    public int checkManager(ManagerForm managerForm) {
        int flag = 0;
        ChStr chStr=new ChStr();
        String sql = "select * from manager where name='"+managerForm.getName()+"' and PWD = '"+managerForm.getPwd()+"'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                    flag = 1;
                }
        } catch (SQLException ex) {
            flag = 0;
        }finally{
        	conn.close();
        }
        return flag;
    }

    //���Ŀ���ʱӦ�õĲ�ѯ����
    public ManagerForm query_pwd(ManagerForm managerForm) {
    	ManagerForm managerForm1 = null;
        String sql = "SELECT * FROM manager WHERE name='"+managerForm.getName()+"'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                managerForm1 = new ManagerForm();
                managerForm1.setId(Integer.valueOf(rs.getString(1)));
                managerForm1.setName(rs.getString(2));
                managerForm1.setPwd(rs.getString(3));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }finally{
        	conn.close();
        }
        return managerForm1;
    }

    //�޸Ĺ���Ա����
    public int updatePwd(ManagerForm managerForm){
    	String sql="UPDATE manager SET pwd='"+managerForm.getPwd()+"' where name='"+managerForm.getName()+"'";
	    int ret=conn.executeUpdate(sql);
	    System.out.println("�޸Ĺ���Ա����ʱ��SQL��"+sql);
	    conn.close();
	    return ret;
    }
}