<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.readerType.ReaderTypeForm" %>
<%@ page import="com.core.ChStr"%>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<head>
<title>修改读者类型信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="CSS/style.css">
</head>

<body>
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<%
ReaderTypeForm readerTypeForm=(ReaderTypeForm)request.getAttribute("readerTypeQueryif");
ChStr chStr=new ChStr();
%>
<div class="ban">
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者类型管理 &gt; 修改读者类型 &gt;&gt;&gt;</td>
      </tr>
	  <tr> 
		  <td align="center" valign="top" border="0">
			  <form name="form1" method="post" action="readerType?action=readerTypeModify">
			  <table width="100%" height="200" border="0" cellpadding="0" cellspacing="0">
			  	  <tr>
				    <td width="28%" align="center">类型号：      </td>
				    <td width="72%"><input name="id" size="10" value="<%=readerTypeForm.getId()%>" readonly> </td>
				  </tr>
				  <tr>
				    <td width="28%" align="center">类型名称：      </td>
				    <td width="72%"><input name="name" type="text" size="28" value="<%=readerTypeForm.getName()%>"></td>
				  </tr>
				  <tr>
				    <td align="center">可借数量：</td>
				    <td><input name="number" type="text" size="25" value="<%=readerTypeForm.getNumber()%>">(本)</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td><input name="Submit" type="submit" class="btn_grey" value="保存">&nbsp;
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
