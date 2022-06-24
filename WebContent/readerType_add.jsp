<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<head>
<title>添加读者类型信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" rel="stylesheet">
</head>
<script language="jscript">
function check(form){
	if(form.name.value==""){
		alert("请输入类型名称!");form.name.focus();return false;
	}
	if(form.number.value==""){
		alert("请输入可借数量!");form.number.focus();return false;
	}	
}
</script>
<body>
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<div class="ban">
<table width="778" border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
        <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者类型管理 &gt; 添加读者类型 &gt;&gt;&gt;</td>
      </tr>
      <tr>
          <td align="center">	
	          <form name="form1" method="post" action="readerType?action=readerTypeAdd">
					<table height="100"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
				      <tr>
				        <td width="84" align="center">类型名称：</td>
				        <td width="191" height="39">
				          <input name="name" type="text" size="28">        </td>
				      </tr>
				      <tr>
				        <td width="84" align="center">可借数量：</td>
				        <td height="35"><input name="number" type="text" id="number">
				          (本)</td>
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
