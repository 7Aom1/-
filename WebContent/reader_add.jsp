<%@ page language="java" import="java.util.*" contentType= "text/html; charset=UTF-8" %>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<script language="jscript">
function check(form){
	if(form.name.value==""){
		alert("请输入读者姓名!");form.name.focus();return false;
	}
	if(form.barcode.value==""){
		alert("请输入条形码!");form.barcode.focus();return false;
	}
	if(form.paperNO.value==""){
		alert("请输入证件号码!");form.paperNO.focus();return false;
	}
}
</script>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
        <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者档案管理 &gt; 添加读者信息 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="100%" height="493"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">
	<form name="form1" method="post" action="reader?action=readerAdd">
	<table width="600" height="432"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="173" align="center">读者条形码：</td>
        <td width="427" height="39">
          <input name="barCode" type="text"> 
          *         </td>
      </tr>
      <tr>
        <td width="173" align="center">姓名：</td>
        <td width="427" height="39">
          <input name="name" type="text"> 
          *         </td>
      </tr>
      <tr>
        <td width="173" align="center">性别：</td>
        <td height="39"><input name="sex" type="radio" class="noborder" id="radiobutton" value="男" checked>男 
                        <input name="sex" type="radio" class="noborder" value="女"> 女</td>
      </tr>
      <tr>
        <td align="center">年龄：</td>
        <td height="39"><input name="sage" type="text" id=""sage"">* </td>
      </tr>
      <tr>
        <td align="center">电话：</td>
        <td height="39"><input name="tel" type="text" id="tel"></td>
      </tr>
      <tr>
        <td width="173" align="center">读者类型：</td>
        <td width="427" height="39"><select name="typeID" size="1">
						           	   <option value="1">学生</option>
						           	   <option value="2">教师</option>
						            </select>   *   </td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
        <td height="39"><input name="Submit" type="submit" class="btn_grey" value="保存" onClick="return check(form1)">
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
