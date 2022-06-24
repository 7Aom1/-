package com.book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

public class BookServlet extends HttpServlet {
	private BookDB bookDAO = null;

	public BookServlet() {
		this.bookDAO = new BookDB();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		System.out.println("\nbook*********************action=" + action);
		if (action == null || "".equals(action)) {
			request.setAttribute("error", "���Ĳ�������");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("bookAdd".equals(action)) {
			bookAdd(request, response);
		} else if ("bookQuery".equals(action)) {
			bookQuery(request, response);
		} else if ("bookModifyQuery".equals(action)) {
			bookModifyQuery(request, response);
		} else if ("bookModify".equals(action)) {
			bookModify(request, response);
		} else if ("bookDel".equals(action)) {
			bookDel(request, response);
		} else if ("bookifQuery".equals(action)) {   //ͼ�鵵����ѯ
			bookifQuery(request, response);
		}
	}

	/*********************** ���ͼ����Ϣ **************************/
	public void bookAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BookForm bookForm = new BookForm();
		bookForm.setBarCode(request.getParameter("barcode"));
		bookForm.setBookName(request.getParameter("bookName"));
		bookForm.setAuthor(request.getParameter("author"));
		bookForm.setIsbn(request.getParameter("Isbn"));
		bookForm.setPrice(Float.valueOf(request.getParameter("price")));
		bookForm.setTypeID(Integer.parseInt(request.getParameter("typeID")));
		
		int a = bookDAO.insert(bookForm);
		if (a == 1) {
			request.getRequestDispatcher("book_ok.jsp?para=1").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "��ͼ����Ϣ�Ѿ���ӣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "ͼ����Ϣ���ʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/*********************** ��ѯȫ��ͼ����Ϣ **************************/
	public void bookQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("book", bookDAO.query()); // ����ѯ������浽book��
		request.getRequestDispatcher("book.jsp").forward(request, response);
	}
	
	/*********************** ��ѯ�޸�ͼ����Ϣ **************************/
	private void bookModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		System.out.println("��ѯ�޸�ͼ����Ϣ��" + request.getParameter("ID"));
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		
		//HttpSession session = request.getSession();
		//session.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_Modify.jsp").forward(request,
				response);
	}

	/*********************** �޸�ͼ����Ϣ **************************/
	public void bookModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BookForm bookForm = new BookForm(); // ʵ����BookForm��
		bookForm.setId(Integer.parseInt(request.getParameter("id")));
		bookForm.setBarCode(request.getParameter("barcode")); // ��ȡ����������������
		bookForm.setBookName(request.getParameter("bookName"));
		bookForm.setAuthor(request.getParameter("author"));
		bookForm.setIsbn(request.getParameter("Isbn"));
		bookForm.setPrice(Float.valueOf(request.getParameter("price")));
		bookForm.setTypeID(Integer.parseInt(request.getParameter("typeID")));
		
		int ret = bookDAO.update(bookForm); // �����޸�ͼ����Ϣ�ķ���update()
		if (ret == 0) {
			request.setAttribute("error", "�޸�ͼ����Ϣʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response); // ת��������ʾҳ��
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=2").forward(request,response); // ת���޸ĳɹ�ҳ��
		}
	}

	/*********************** ɾ��ͼ����Ϣ **************************/
	public void bookDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		int ret = bookDAO.delete(bookForm);
		if (ret == 0) {
			request.setAttribute("error", "ɾ��ͼ����Ϣʧ�ܣ�");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=3").forward(request,response);
		}
	}

	/*********************** ������ѯͼ����Ϣ **************************/
	private void bookifQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		if (request.getParameter("f") != null) {
			//******���ʵ�������롢�����������ߵ�ģ����ѯ��******
			str = request.getParameter("f") + " like '" + request.getParameter("key") + "%'";
		}
		request.setAttribute("ifbook", bookDAO.queryCondition(str));//str��ֵΪnull����ģ����ѯ�����ݣ����� ����+��like ' ��+���ݿ⣨��������ݣ�+ ��%����
		System.out.print("������ѯͼ����Ϣʱ��str:" + str);
		request.getRequestDispatcher("bookQuery.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
