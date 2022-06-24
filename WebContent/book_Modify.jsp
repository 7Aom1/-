<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.book.BookForm" %>
<%@ page import="java.util.*"%>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<script language="jscript">
function check(form){
	if(form.barcode.value==""){
		alert("请输入条形码!");form.barcode.focus();return false;
	}
	if(form.bookName.value==""){
		alert("请输入图书姓名!");form.bookName.focus();return false;
	}
	if(form.price.value==""){
		alert("请输入图书定价!");form.price.focus();return false;
	}
}
</script>
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
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：图书管理 &gt; 图书档案管理 &gt; 修改图书信息 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="100%" height="493"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">
	<form name="form1" method="post" action="book?action=bookModify">
<%	//获取不到session对象
    BookForm bookForm=(BookForm)request.getAttribute("bookQueryif");
    int ID=bookForm.getId();
	String barcode=bookForm.getBarCode();	
	String bookname=bookForm.getBookName();
	String author=bookForm.getAuthor();
	String ISBN=bookForm.getIsbn();
	Float price=bookForm.getPrice();
	int typeID=bookForm.getTypeID();
  %>
	<table width="600" height="432"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="173" align="center"><input name="id" type="hidden" id="id" value="<%=ID%>">
          条&nbsp;形&nbsp;码：</td>
        <td height="39">
          <input name="barcode" type="text" id="barcode" value="<%=barcode%>" ></td>
      </tr>
      <tr>
        <td align="center">图书名称：</td>
        <td height="39" ><input name="bookName" type="text" id="bookName" value="<%=bookname%>">
          * </td>
      </tr>
      <tr>
        <td align="center">作者：</td>
        <td><input name="author" type="text" id="author" value="<%=author%>"></td>
      </tr>
      <tr>
        <td align="center">ISBN：</td>
        <td><input name="Isbn" type="text" id="translator" value="<%=ISBN%>"></td>
      </tr>
      <tr>
        <td align="center">价格：</td>
        <td><input name="price" type="text" id="price" value="<%=price%>"> 
          (元) * </td>
      </tr>
      <tr>
        <td align="center">类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
        <td><select name="typeID" size="1">
					<option value="1" <%if(typeID==1) out.println("checked");%>>计算机</option>
					<option value="2" <%if(typeID==2) out.println("checked");%>>文学</option>
			</select></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
        <td><input name="Submit" type="submit" class="btn_grey" value="保存" onClick="return check(form1)">
&nbsp;
<input name="Submit2" type="button" class="btn_grey" value="返回" onClick="history.back()"></td>
      </tr>
    </table>
	</form>
	</td>
  </tr>
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
