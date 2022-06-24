package com.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ManagerServlet extends HttpServlet {
	private ManagerDB managerDAO = null; // 声明ManagerDAO的对象

	public ManagerServlet() {
		this.managerDAO = new ManagerDB(); // 实例化ManagerDAO类
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println("获取的查询字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("login".equals(action)) {// 当action值为login时，调用managerLogin()方法验证管理员身份
			managerLogin(request, response);
		} else if ("querypwd".equals(action)) {
			pwdQuery(request, response);// 更改口令时应用的查询
		} else if ("modifypwd".equals(action)) {
			modifypwd(request, response); // 更改口令
		}
	}

	// 管理员身份验证

	public void managerLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ManagerForm managerForm = new ManagerForm();//实例化managerForm类
		managerForm.setName(request.getParameter("name"));//获取管理员名称并设置name属性
		managerForm.setPwd(request.getParameter("pwd"));//获取管理员密码并设置pwd属性
		int ret = managerDAO.checkManager(managerForm);//调用ManagerDAO类的checkManager()方法
		if (ret == 1) {
			/**********将登录到系统的管理员名称保存到session中***********************************/
            HttpSession session=request.getSession();
            session.setAttribute("manager",managerForm.getName());
		/***********************************************************************************/
			request.getRequestDispatcher("main.jsp").forward(request, response);  //转到系统主界面
		} else {
			request.setAttribute("error", "您输入的管理员名称或密码错误！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);//转到错误提示页
		}
	}

	// 修改密码时查询
	private void pwdQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ManagerForm managerForm = new ManagerForm();
		HttpSession session = request.getSession();
		String manager = (String) session.getAttribute("manager");
		managerForm.setName(manager);
		System.out.print("查询到的manager:" + manager);
		request.setAttribute("pwdQueryif", managerDAO.query_pwd(managerForm));
		request.getRequestDispatcher("pwd_Modify.jsp").forward(request,response);
	}

	// 修改管理员密码
	private void modifypwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ManagerForm managerForm = new ManagerForm();
		//******封装管理员姓名和密码信息******
		managerForm.setName(request.getParameter("name"));
		managerForm.setPwd(request.getParameter("pwd"));
		int ret = managerDAO.updatePwd(managerForm);
		if (ret == 0) {
			request.setAttribute("error", "更改口令失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("pwd_ok.jsp").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
