<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<head>
<title>添加图书类型信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" rel="stylesheet">
</head>
<script language="jscript">
function check(form){
	if(form.typeName.value==""){
		alert("请输入类型名称!");form.typeName.focus();return false;
	}
	if(form.days.value==""){
		alert("请输入可借天数!");form.days.focus();return false;
	}	
}
</script>
<body>
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<div class="ban">
<table width="778" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
          <td height="22" valign="top" class="word_orange">当前位置：图书管理 &gt; 图书类型管理 &gt; 添加图书类型 &gt;&gt;&gt;</td>
      </tr>
      <tr>
          <td align="center">	
             <form name="form1" method="post" action="bookType?action=bookTypeAdd">
					<table height="100"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
				      <tr>
				        <td width="77" align="center">类型名称：</td>
				        <td width="198" height="39">
				          <input name="typeName" type="text" size="30">        </td>
				      </tr>
				      <tr>
				        <td width="77" align="center">可借天数：</td>
				        <td height="35"><input name="days" type="text" id="days" size="25">
				          (天)</td>
				      </tr>
				      <tr>
				        <td align="center">&nbsp;</td>
				        <td><input name="Submit" type="submit" class="btn_grey" value="保存" onClick="return check(form1)">
				          &nbsp;
				          <input name="Submit2" type="button" class="btn_grey" value="关闭" onClick="window.close();"></td>
				      </tr>
				    </table>
	          </form>
	        </td>
         </tr>
</table>
</div>
</body>
</html>
