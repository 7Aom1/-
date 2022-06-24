<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.book.BookDB" %>
<%@ page import="com.book.BookForm" %>
<%@ page import="java.util.*"%>
<%@ page import="com.core.ChStr"%>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<%
	Collection coll=(Collection)request.getAttribute("book");
	ChStr chStr=new ChStr();
%>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body>
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<div class="ban">
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：图书管理 &gt; 图书档案管理 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><%if(coll==null || coll.isEmpty()){ %>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">暂无图书信息！</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <a href="book_add.jsp">添加图书信息</a> </td>
  </tr>
</table>
 <%
}else{
  //通过迭代方式显示数据
  Iterator it=coll.iterator();
  int ID=0;
  String barcode="";
  String bookname="";
  String author="";
  String Isbn="";
  float price=0;
  int typeID=0;
  %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="87%">&nbsp; </td>
	    <td width="13%"><a href="book_add.jsp">添加图书信息</a></td>	  
	  </tr>
</table>  
<table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="13%" bgcolor="#F9D16B">条形码</td>  
    <td width="16%" bgcolor="#F9D16B">图书名称</td>
    <td width="13%" bgcolor="#F9D16B">作者</td>
    <td width="14%" bgcolor="#F9D16B">ISBN</td>
    <td width="12%" bgcolor="#F9D16B">价格</td>
    <td width="12%" bgcolor="#F9D16B">类型</td>
    <td width="9%" bgcolor="#F9D16B">修改</td>
    <td width="5%" bgcolor="#F9D16B">删除</td>
  </tr>
<%
  while(it.hasNext()){
    BookForm bookForm=(BookForm)it.next();
	ID=bookForm.getId();
	barcode=bookForm.getBarCode();
	bookname=bookForm.getBookName();
	author=bookForm.getAuthor();
	Isbn=bookForm.getIsbn();
	price=bookForm.getPrice();
	typeID=bookForm.getTypeID();
	%> 
  <tr align="center">
    <td style="padding:5px;">&nbsp;<%=barcode%></td>  
    <td style="padding:5px;"><a href="book?action=bookDetail&ID=<%=ID%>"><%=bookname%></a></td>
    <td style="padding:5px;">&nbsp;<%=author%></td>  
    <td style="padding:5px;">&nbsp;<%=Isbn%></td>  
    <td style="padding:5px;">&nbsp;<%=price%></td>  
	<td style="padding:5px;">&nbsp;<%=typeID%></td>  
    <td align="center"><a href="book?action=bookModifyQuery&ID=<%=ID%>">修改</a></td>
    <td align="center"><a href="book?action=bookDel&ID=<%=ID%>">删除</a></td>
  </tr>
<%
  }
}
%>  
</table></td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</div>
</body>
</html>
