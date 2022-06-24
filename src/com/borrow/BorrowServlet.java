package com.borrow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.book.*;
import com.borrow.*;
import com.reader.*;

public class BorrowServlet extends HttpServlet {
	/******************�ڹ��췽����ʵ����Borrow����Ӧ�õĳ־ò���Ķ���**************************/
	   private BorrowDB borrowDAO = null;
	   private ReaderDB readerDAO=null;
	   private BookDB bookDAO=null;
	   private ReaderForm readerForm=new ReaderForm();
	   public BorrowServlet() {
	       this.borrowDAO = new BorrowDB();
	       this.readerDAO=new ReaderDB();
	       this.bookDAO=new BookDB();
	   }
	/******************************************************************************************/
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	        String action =request.getParameter("action");
	        
	        if(action==null||"".equals(action)){
	            request.setAttribute("error","���Ĳ�������");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	        }else if("bookborrow".equals(action)){
	        	bookborrow(request,response);  //ͼ�����
	        }else if("bookrenew".equals(action)){
	            bookrenew(request,response);  //ͼ������
	        }else if("bookback".equals(action)){
	            bookback(request,response);  //ͼ��黹
	        }else if("Bremind".equals(action)){
	            bremind(request,response);  //���ĵ�������
	        }else if("borrowQuery".equals(action)){
	            borrowQuery(request,response);  //������Ϣ��ѯ
	        }
	    }
    
 /*********************ͼ����Ĳ�ѯ***********************/
    private void borrowQuery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        String str=null;
        String flag[]=request.getParameterValues("flag");
        if (flag!=null){
        	String aa = flag[0];
        	//����ͼ�����ƻ��߶������ƽ��в�ѯ
            if ("a".equals(aa)) {
                if (request.getParameter("f") != null) {
                	//******���ʵ��ͼ�����ƻ��߶������Ƶ�ģ����ѯ��******
                    str = request.getParameter("f") + " like '" + request.getParameter("key") + "%'";
                }
            }
            if ("b".equals(aa)) {
                String sdate = request.getParameter("sdate");
                String edate = request.getParameter("edate");
                if (sdate != null && edate != null) {
                	//******���ݽ���ʱ��ʵ�������ѯ******
                    str = "borrowTime  between '" + sdate + "' and '" + edate +"'";
                }
                System.out.println("����" + str);
            }
            //ͬʱѡ�����ں��������в�ѯ
            if (flag.length == 2) {
                if (request.getParameter("f") != null) {
                	//******���ʵ��ͼ�����ƻ��߶������Ƶ�ģ����ѯ��******
                    str = request.getParameter("f") + " like '" + request.getParameter("key") + "%'";
                }
                System.out.println("���ں�����");
                String sdate = request.getParameter("sdate");
                String edate = request.getParameter("edate");
                String str1 = null;
                if (sdate != null && edate != null) {
                	//******���ݽ���ʱ��ʵ�������ѯ******
                	str1 = "borrowTime between '" + sdate + "' and '" + edate +"'";
                }
                
                str = str + " and " + str1;
                System.out.println("���������ڣ�" + str);
            }
        }
       request.setAttribute("borrowQuery",borrowDAO.borrowQuery(str));
       System.out.print("������ѯͼ�������Ϣʱ��str:"+str);
       request.getRequestDispatcher("borrowQuery.jsp").forward(request, response);
    }
    
    /*********************��������***********************/
    private void bremind(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        request.setAttribute("Bremind",borrowDAO.bremind());
        request.getRequestDispatcher("bremind.jsp").forward(request, response);
    }

    /*********************ͼ�����***********************/
    private void bookborrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ѯ������Ϣ
    	//******��װ����������******
        readerForm.setBarCode(request.getParameter("barcode"));
        ReaderForm reader = (ReaderForm) readerDAO.queryM(readerForm);
        request.setAttribute("readerinfo", reader);
        //��ѯ���ߵĽ�����Ϣ
        request.setAttribute("borrowinfo",borrowDAO.borrowinfo(request.getParameter("barcode")));
        //��ɽ���
        String f = request.getParameter("f");
        String key = request.getParameter("inputkey");
        if (key != null && !key.equals("")) {
            BookForm bookForm=bookDAO.queryB(f, key);
            if (bookForm!=null){
            	int ret = borrowDAO.insertBorrow(reader, bookDAO.queryB(f, key));
                if (ret == 1) {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    request.getRequestDispatcher("bookBorrow_ok.jsp").forward(request, response);

                } else {
                    request.setAttribute("error", "添加借阅信息失败，图书已借!");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("error", "没有该图书!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }else{
        	request.getRequestDispatcher("bookBorrow.jsp").forward(request, response);
        }
    }

    /*********************图书继借***********************/
    private void bookrenew(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        //��ѯ������Ϣ
    	//******��װ����������******
        readerForm.setBarCode(request.getParameter("barcode"));
        ReaderForm reader = (ReaderForm) readerDAO.queryM(readerForm);
        request.setAttribute("readerinfo", reader);
        //��ѯ���ߵĽ�����Ϣ
        request.setAttribute("borrowinfo",borrowDAO.borrowinfo(request.getParameter("barcode")));
         if(request.getParameter("id")!=null){//��if������ж��Ƿ�������
             int id = Integer.parseInt(request.getParameter("id"));
             if (id > 0) {    
            	 //ִ�м̽����
                 int ret = borrowDAO.renew(id);
                 if (ret == 0) {
                     request.setAttribute("error", "ͼ��̽�ʧ��!");
                     request.getRequestDispatcher("error.jsp").forward(request, response);
                 } else {
                     request.setAttribute("bar", request.getParameter("barcode"));
                     request.getRequestDispatcher("bookRenew_ok.jsp").forward(request, response);
                 }
             }
         }else{
        	 request.getRequestDispatcher("bookRenew.jsp").forward(request, response);
         }
    }
    /*********************ͼ��黹***********************/
    private void bookback(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        //��ѯ������Ϣ
    	//******��װ����������******
        readerForm.setBarCode(request.getParameter("barcode"));
        ReaderForm reader = (ReaderForm) readerDAO.queryM(readerForm);
        request.setAttribute("readerinfo", reader);
        //��ѯ���ߵĽ�����Ϣ
        request.setAttribute("borrowinfo",borrowDAO.borrowinfo(request.getParameter("barcode")));
         if(request.getParameter("id")!=null){
             int id = Integer.parseInt(request.getParameter("id"));
             if (id > 0) { 
            	 //ִ�й黹����
                 int ret = borrowDAO.back(id);
                 if (ret == 0) {
                     request.setAttribute("error", "ͼ��黹ʧ��!");
                     request.getRequestDispatcher("error.jsp").forward(request, response);
                 } else {
                     request.setAttribute("bar", request.getParameter("barcode"));
                     request.getRequestDispatcher("bookBack_ok.jsp").forward(request, response);
                 }
             }
         }else{
        	 request.getRequestDispatcher("bookBack.jsp").forward(request, response);
         }
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

doGet(request, response);
}
}

