<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.book.BookDB" %>
<%@ page import="com.book.BookForm" %>
<%@ page import="java.util.*"%>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<%
	Collection coll=(Collection)request.getAttribute("ifbook");
%>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<div class="ban">
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：系统查询 &gt; 图书档案查询 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">
	<form action="" method="post" name="form1">  
 <table width="98%" height="38"  border="0" cellpadding="0" cellspacing="0" bgcolor="#E3F4F7" class="tableBorder_gray">
  <tr>
    <td align="center" bgcolor="#F9D16B">

&nbsp;<img src="Images/search.gif" width="45" height="28"></td>
    <td bgcolor="#F9D16B">请选择查询依据：
      <select name="f" class="wenbenkuang" id="f">
	        <option value="barcode">条形码</option>
	        <option value="bookname" selected>书名</option>
	        <option value="author">作者</option>
      </select>
      <input name="key" type="text" id="key">
      <input name="Submit" type="submit" class="btn_grey" value="查询"></td>
  </tr>
</table>
<%
if(coll==null || coll.isEmpty()){
%>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">暂无图书信息！</td>
            </tr>
          </table>
          <%
}else{
  //通过迭代方式显示数据
  Iterator it=coll.iterator();
  String barcode="";
  String bookname="";
  String author="";
  String isbn="";
  float price=0;
  int typeID=0;
  %>  
  <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="20%" bgcolor="#F9D16B">条形码</td>  
    <td width="20%" bgcolor="#F9D16B">图书名称</td>
    <td width="20%" bgcolor="#F9D16B">作者</td>
    <td width="20%" bgcolor="#F9D16B">ISBN</td>
    <td width="10%" bgcolor="#F9D16B">价格</td>
    <td width="10%" bgcolor="#F9D16B">图书类型</td>
    </tr>
<%
  while(it.hasNext()){
    BookForm bookForm=(BookForm)it.next();
	bookname=bookForm.getBookName();
	barcode=bookForm.getBarCode();
	author=bookForm.getAuthor();
	isbn=bookForm.getIsbn();
	price=bookForm.getPrice();
	typeID=bookForm.getTypeID();
	%> 
  <tr align="center">
    <td>&nbsp;<%=barcode%></td>  
    <td>&nbsp;<%=bookname%></td>  
    <td>&nbsp;<%=author%></a></td>
    <td>&nbsp;<%=isbn%></td>  
    <td>&nbsp;<%=price%></td>  
    <td>&nbsp;<%=typeID%></td>  
    </tr>
<%
  }
}
%>  
</table>
	</form>
</td>
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
