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
			request.setAttribute("error", "���Ĳ�������");
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

	/*********************** ��Ӷ�����Ϣ **************************/
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
			request.setAttribute("error", "������Ϣ���ʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "�ö�����Ϣ�Ѿ���ӣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("reader_ok.jsp?para=1").forward(request, response);
		}
	}

	/*********************** ��ѯȫ��������Ϣ **************************/
	private void readerQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("reader", readerDAO.query());
		request.getRequestDispatcher("reader.jsp").forward(request, response);
	}

	/*********************** ��ѯ�޸Ķ�����Ϣ **************************/
	private void readerModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReaderForm readerForm = new ReaderForm();
		System.out.println("��ѯ�޸Ķ�����Ϣ��" + request.getParameter("barCode"));
		readerForm.setBarCode(request.getParameter("barCode"));
		request.setAttribute("readerQueryif", readerDAO.queryM(readerForm));
		request.getRequestDispatcher("reader_Modify.jsp").forward(request,
				response);
	}

	/*********************** �޸Ķ�����Ϣ **************************/
	private void readerModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ReaderForm readerForm = new ReaderForm();		
		readerForm.setBarCode(request.getParameter("barCode"));
		readerForm.setName(request.getParameter("name"));
		readerForm.setSex(request.getParameter("sex"));
		readerForm.setSage(request.getParameter("sage"));
		readerForm.setTel(request.getParameter("tel"));
		readerForm.setTypeID(Integer.parseInt(request.getParameter("typeID"))); //��Ҫ������������ת����
		
		int ret = readerDAO.update(readerForm);
		if (ret == 0) {
			request.setAttribute("error", "�޸Ķ�����Ϣʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("reader_ok.jsp?para=2").forward(request, response);
		}
	}

	/*********************** ɾ��������Ϣ **************************/
	private void readerDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ReaderForm readerForm = new ReaderForm();
		readerForm.setBarCode(request.getParameter("barCode"));
		int ret = readerDAO.delete(readerForm);
		if (ret == 0) {
			request.setAttribute("error", "ɾ��������Ϣʧ�ܣ�");
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
