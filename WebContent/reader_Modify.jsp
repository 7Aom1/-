<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.reader.ReaderForm" %>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<%
	ReaderForm readerForm=(ReaderForm)request.getAttribute("readerQueryif");
%>
<script language="jscript">
function check(form){
	if(form.name.value==""){
		alert("请输入读者姓名!");form.name.focus();return false;
	}
	if(form.paperNO.value==""){
		alert("请输入证件号码!");form.paperNO.focus();return false;
	}
}
</script>
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
    <td valign="top" bgcolor="#FFFFFF">
    <table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;">
    <table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者档案管理 &gt; 修改读者信息 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="100%" height="493"  border="0" cellpadding="0" cellspacing="0">
  	  <tr>
<%    String barCode=readerForm.getBarCode();
	  String name=readerForm.getName();
	  String sex=readerForm.getSex();  
	  String sage=readerForm.getSage();
	  String tel=readerForm.getTel();
	  int typeID=readerForm.getTypeID();
  %>
    <td align="center" valign="top">
	<form name="form1" method="post" action="reader?action=readerModify">
	<table width="600" height="432"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="173" align="center">读者条码：</td>
        <td width="427" height="39">
          <input name="barCode" type="text" value="<%=barCode%>" > *         
      </tr>
      <tr>
        <td width="173" align="center">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
        <td width="427" height="39">
          <input name="name" type="text" value="<%=name%>"> *         
      </tr>
      <tr>
        <td width="173" align="center">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
        <td height="35"><input name="sex" type="radio" class="noborder" id="radiobutton" value="男" <%if("男".equals(sex)) out.println("checked");%>>男
          <input name="sex" type="radio" class="noborder" value="女" <%if("女".equals(sex)) out.println("checked");%>>女</td>
      </tr>
      <tr>
        <td align="center">年&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
        <td><input name="sage" type="text" id="sage" value="<%=sage%>"></td>
      </tr>
      <tr>
        <td align="center">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
        <td><input name="tel" type="text" id="tel" value="<%=tel%>"></td>
      </tr>
      <tr>
        <td align="center">类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
        <td><select name="typeID" size="1">
					<option value="1" <%if(typeID==1) out.println("checked");%>>学生</option>
					<option value="2" <%if(typeID==2) out.println("checked");%>>教师</option>
			</select></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
        <td><input name="Submit" type="submit" class="btn_grey" value="保存" onClick="return check(form1)">&nbsp;
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
