package com.reader;

import com.core.ConnDB;

import java.util.*;
import java.sql.*;

public class ReaderDB {
	private ConnDB conn = new ConnDB();

	// 查询数据
	public Collection query() {
		ReaderForm readerForm = null;
		Collection readerColl = new ArrayList();
		String sql = "select * from reader";
		ResultSet rs = conn.executeQuery(sql);
		String birthday="";
		try {
			while (rs.next()) {
				readerForm = new ReaderForm();
				readerForm.setBarCode(rs.getString(2));
				readerForm.setName(rs.getString(3));
				readerForm.setSex(rs.getString(4));
				readerForm.setSage(rs.getString(5));
				readerForm.setTel(rs.getString(6));
				readerForm.setTypeID(Integer.valueOf(rs.getString(7)));
				readerColl.add(readerForm);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return readerColl;
	}

	// 用于修改的查询
	public ReaderForm queryM(ReaderForm readerForm) {
		ReaderForm readerForm1 = null;
		String sql =  "select * from reader where barcode= '"+readerForm.getBarCode()+"'";
		System.out.println("修改读者信息时的SQL：" + sql);
		ResultSet rs = conn.executeQuery(sql);
		String birthday="";
		try {
			while (rs.next()) {
				readerForm1 = new ReaderForm();
				readerForm1.setId(Integer.valueOf(rs.getString(1)));
				readerForm1.setBarCode(rs.getString(2));
				readerForm1.setName(rs.getString(3));
				readerForm1.setSex(rs.getString(4));
				readerForm1.setSage(rs.getString(5));				
				readerForm1.setTel(rs.getString(6));
				readerForm1.setTypeID(Integer.valueOf(rs.getString(7)));
				
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return readerForm1;
	}

	// 添加数据
	public int insert(ReaderForm readerForm) {
		String sql1 = "SELECT * FROM reader WHERE barCode='" + readerForm.getBarCode() + "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int falg = 0;
		try {
			if (rs.next()) {
				falg = 2;
			} else {
				sql = "Insert into reader (barCode,name,sex,sage,tel,typeID) values('"+ readerForm.getBarCode()+ "','"+ readerForm.getName()+ "','"+ readerForm.getSex()+ "','"+ readerForm.getSage()+ "','"+ readerForm.getTel() + "','"+ readerForm.getTypeID()+ "')";
				falg = conn.executeUpdate(sql);
				System.out.println("添加读者信息的SQL：" + sql);
				conn.close();
			}
		} catch (SQLException ex) {
			falg = 0;
		}
		System.out.println("falg:" + falg);
		return falg;
	}

	// 修改数据
	public int update(ReaderForm readerForm) {
		
		String sql = "Update reader set name='" + readerForm.getName()+ "',"
				    + "sex='" + readerForm.getSex() + "',"
				    + "sage='"+ readerForm.getSage() + "',"
				    + "tel='" + readerForm.getTel()+ "',"
				    + "typeID='" + readerForm.getTypeID()+ "'"
				    + "where barCode='"+ readerForm.getBarCode() + "'";
		
		int falg = conn.executeUpdate(sql);
		System.out.println("修改数据时的SQL：" + sql);
		System.out.println("修改数据时的falg：" + falg);
		conn.close();
		return falg;
	}

	// 删除数据
	public int delete(ReaderForm readerForm) {
		
		String sql = "Delete from reader where barCode='" + readerForm.getBarCode() + "'";
		int falg = conn.executeUpdate(sql);
		System.out.println("删除时的SQL：" + sql);
		System.out.println("删除数据时的falg：" + falg);
		return falg;
	}
}
