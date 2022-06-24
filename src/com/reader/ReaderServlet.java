package com.reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class ReaderServlet extends HttpServlet {
	private ReaderDB readerDAO = null;

	public ReaderServlet() {
		this.readerDAO = new ReaderDB();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		System.out.println("\nreader*********************action=" + action);
		if (action == null || "".equals(action)) {
			request.setAttribute("error", "您的操作有误！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else if ("readerAdd".equals(action)) {
			readerAdd(request, response);
		} else if ("readerQuery".equals(action)) {
			readerQuery(request, response);
		} else if ("readerModifyQuery".equals(action)) {
			readerModifyQuery(request, response);
		} else if ("readerModify".equals(action)) {
			readerModify(request, response);
		} else if ("readerDel".equals(action)) {
			readerDel(request, response);
		}
	}

	/*********************** 添加读者信息 **************************/
	private void readerAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ReaderForm readerForm = new ReaderForm();	
		readerForm.setBarCode(request.getParameter("barCode"));
		readerForm.setName(request.getParameter("name"));
		readerForm.setSex(request.getParameter("sex"));
		readerForm.setSage(request.getParameter("sage"));
		readerForm.setTel(request.getParameter("tel"));
		readerForm.setTypeID(Integer.parseInt(request.getParameter("typeID")));
		
		int a = readerDAO.insert(readerForm);
		
		if (a == 0) {
			request.setAttribute("error", "读者信息添加失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "该读者信息已经添加！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("reader_ok.jsp?para=1").forward(request, response);
		}
	}

	/*********************** 查询全部读者信息 **************************/
	private void readerQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("reader", readerDAO.query());
		request.getRequestDispatcher("reader.jsp").forward(request, response);
	}

	/*********************** 查询修改读者信息 **************************/
	private void readerModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReaderForm readerForm = new ReaderForm();
		System.out.println("查询修改读者信息：" + request.getParameter("barCode"));
		readerForm.setBarCode(request.getParameter("barCode"));
		request.setAttribute("readerQueryif", readerDAO.queryM(readerForm));
		request.getRequestDispatcher("reader_Modify.jsp").forward(request,
				response);
	}

	/*********************** 修改读者信息 **************************/
	private void readerModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ReaderForm readerForm = new ReaderForm();		
		readerForm.setBarCode(request.getParameter("barCode"));
		readerForm.setName(request.getParameter("name"));
		readerForm.setSex(request.getParameter("sex"));
		readerForm.setSage(request.getParameter("sage"));
		readerForm.setTel(request.getParameter("tel"));
		readerForm.setTypeID(Integer.parseInt(request.getParameter("typeID"))); //需要进行数据类型转换。
		
		int ret = readerDAO.update(readerForm);
		if (ret == 0) {
			request.setAttribute("error", "修改读者信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("reader_ok.jsp?para=2").forward(request, response);
		}
	}

	/*********************** 删除读者信息 **************************/
	private void readerDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ReaderForm readerForm = new ReaderForm();
		readerForm.setBarCode(request.getParameter("barCode"));
		int ret = readerDAO.delete(readerForm);
		if (ret == 0) {
			request.setAttribute("error", "删除读者信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("reader_ok.jsp?para=3").forward(
					request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
