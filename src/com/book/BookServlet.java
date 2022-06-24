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
			request.setAttribute("error", "您的操作有误！");
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
		} else if ("bookifQuery".equals(action)) {   //图书档案查询
			bookifQuery(request, response);
		}
	}

	/*********************** 添加图书信息 **************************/
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
			request.setAttribute("error", "该图书信息已经添加！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "图书信息添加失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/*********************** 查询全部图书信息 **************************/
	public void bookQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("book", bookDAO.query()); // 将查询结果保存到book中
		request.getRequestDispatcher("book.jsp").forward(request, response);
	}
	
	/*********************** 查询修改图书信息 **************************/
	private void bookModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		System.out.println("查询修改图书信息：" + request.getParameter("ID"));
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		
		//HttpSession session = request.getSession();
		//session.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_Modify.jsp").forward(request,
				response);
	}

	/*********************** 修改图书信息 **************************/
	public void bookModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BookForm bookForm = new BookForm(); // 实例化BookForm类
		bookForm.setId(Integer.parseInt(request.getParameter("id")));
		bookForm.setBarCode(request.getParameter("barcode")); // 获取并设置条形码属性
		bookForm.setBookName(request.getParameter("bookName"));
		bookForm.setAuthor(request.getParameter("author"));
		bookForm.setIsbn(request.getParameter("Isbn"));
		bookForm.setPrice(Float.valueOf(request.getParameter("price")));
		bookForm.setTypeID(Integer.parseInt(request.getParameter("typeID")));
		
		int ret = bookDAO.update(bookForm); // 调用修改图书信息的方法update()
		if (ret == 0) {
			request.setAttribute("error", "修改图书信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response); // 转到错误提示页面
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=2").forward(request,response); // 转到修改成功页面
		}
	}

	/*********************** 删除图书信息 **************************/
	public void bookDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		int ret = bookDAO.delete(bookForm);
		if (ret == 0) {
			request.setAttribute("error", "删除图书信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=3").forward(request,response);
		}
	}

	/*********************** 条件查询图书信息 **************************/
	private void bookifQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		if (request.getParameter("f") != null) {
			//******如何实现条形码、书名或者作者的模糊查询？******
			str = request.getParameter("f") + " like '" + request.getParameter("key") + "%'";
		}
		request.setAttribute("ifbook", bookDAO.queryCondition(str));//str的值为null或是模糊查询的内容，例如 书名+“like ' ”+数据库（输入的内容）+ “%‘”
		System.out.print("条件查询图书信息时的str:" + str);
		request.getRequestDispatcher("bookQuery.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
