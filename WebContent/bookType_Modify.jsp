<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.bookType.BookTypeForm" %>
<%@ page import="com.core.ChStr"%>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<head>
<title>修改图书类型信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="CSS/style.css">
</head>

<body>
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<%
BookTypeForm bookTypeForm=(BookTypeForm)request.getAttribute("bookTypeQueryif");
ChStr chStr=new ChStr();
%>
<div class="ban">
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
 	  <tr>
          <td height="22" valign="top" class="word_orange">当前位置：图书管理 &gt; 图书档案管理 &gt; 修改图书信息 &gt;&gt;&gt;</td>
      </tr>
      <tr>
            <td align="center" valign="top">
             <form name="form1" method="post" action="bookType?action=bookTypeModify">
 			      <input name="id" type="hidden" size="50" value="<%=bookTypeForm.getId()%>"> 
				  <table width="100%" height="111" border="0" cellpadding="0" cellspacing="0">
				      <tr>
					    <td width="29%" align="center">类型编号：      </td>
					    <td width="71%"><input name="ID" type="text" size="30" value="<%=bookTypeForm.getId()%>" readonly>
					      </td>
					  </tr>
					  <tr>
					    <td width="29%" align="center">类型名称：      </td>
					    <td width="71%"><input name="typeName" type="text" size="30" value="<%=bookTypeForm.getTypeName()%>">
					      </td>
					  </tr>
					  <tr>
					    <td align="center">可借天数：</td>
					    <td><input name="days" type="text" size="25" value="<%=bookTypeForm.getDays()%>">
					      (天)</td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td><input name="Submit" type="submit" class="btn_grey" value="保存">
						&nbsp;
						<input name="Submit3" type="button" class="btn_grey" value="返回" onClick="history.back()"></td>
					  </tr>
				</table>
			 </form>
			</td>
      </tr>
</table>
</div>
</body>
</html>
