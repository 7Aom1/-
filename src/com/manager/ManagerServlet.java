package com.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ManagerServlet extends HttpServlet {
	private ManagerDB managerDAO = null; // ����ManagerDAO�Ķ���

	public ManagerServlet() {
		this.managerDAO = new ManagerDB(); // ʵ����ManagerDAO��
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("login".equals(action)) {// ��actionֵΪloginʱ������managerLogin()������֤����Ա���
			managerLogin(request, response);
		} else if ("querypwd".equals(action)) {
			pwdQuery(request, response);// ���Ŀ���ʱӦ�õĲ�ѯ
		} else if ("modifypwd".equals(action)) {
			modifypwd(request, response); // ���Ŀ���
		}
	}

	// ����Ա�����֤

	public void managerLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ManagerForm managerForm = new ManagerForm();//ʵ����managerForm��
		managerForm.setName(request.getParameter("name"));//��ȡ����Ա���Ʋ�����name����
		managerForm.setPwd(request.getParameter("pwd"));//��ȡ����Ա���벢����pwd����
		int ret = managerDAO.checkManager(managerForm);//����ManagerDAO���checkManager()����
		if (ret == 1) {
			/**********����¼��ϵͳ�Ĺ���Ա���Ʊ��浽session��***********************************/
            HttpSession session=request.getSession();
            session.setAttribute("manager",managerForm.getName());
		/***********************************************************************************/
			request.getRequestDispatcher("main.jsp").forward(request, response);  //ת��ϵͳ������
		} else {
			request.setAttribute("error", "������Ĺ���Ա���ƻ��������");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);//ת��������ʾҳ
		}
	}

	// �޸�����ʱ��ѯ
	private void pwdQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ManagerForm managerForm = new ManagerForm();
		HttpSession session = request.getSession();
		String manager = (String) session.getAttribute("manager");
		managerForm.setName(manager);
		System.out.print("��ѯ����manager:" + manager);
		request.setAttribute("pwdQueryif", managerDAO.query_pwd(managerForm));
		request.getRequestDispatcher("pwd_Modify.jsp").forward(request,response);
	}

	// �޸Ĺ���Ա����
	private void modifypwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ManagerForm managerForm = new ManagerForm();
		//******��װ����Ա������������Ϣ******
		managerForm.setName(request.getParameter("name"));
		managerForm.setPwd(request.getParameter("pwd"));
		int ret = managerDAO.updatePwd(managerForm);
		if (ret == 0) {
			request.setAttribute("error", "���Ŀ���ʧ�ܣ�");
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
