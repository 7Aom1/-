<%@ page language="java" import="java.util.*" contentType= "text/html; charset=UTF-8" %>
<%@ page import="com.reader.ReaderForm" %>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	Collection coll=(Collection)request.getAttribute("reader");
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
        <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者档案管理 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><%
if(coll==null || coll.isEmpty()){
%>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">暂无读者信息！</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <a href="reader_add.jsp">添加读者信息</a> </td>
  </tr>
</table>
 <%
}else{
  //通过迭代方式显示数据
  Iterator it=coll.iterator();
  
  String barCode="";
  String name="";
  String sex="";
  String sage="";
  String tel="";
  int typeID=0;
  
  %>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="87%">&nbsp;      </td>
<td width="13%">
      <a href="reader_add.jsp">添加读者信息</a></td>	  
  </tr>
</table>  
  <table width="95%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="13%" bgcolor="#F9D16B">条形码</td>  
    <td width="10%" bgcolor="#F9D16B">姓名</td>
    <td width="10%" bgcolor="#F9D16B">性别</td>
    <td width="12%" bgcolor="#F9D16B">年龄</td>
    <td width="15%" bgcolor="#F9D16B">电话</td>
    <td width="10%" bgcolor="#F9D16B">类型</td>
    <td width="5%" bgcolor="#F9D16B">修改</td>
    <td width="5%" bgcolor="#F9D16B">删除</td>
  </tr>
<%
  while(it.hasNext()){
    ReaderForm readerForm=(ReaderForm)it.next();
    barCode=readerForm.getBarCode();
	name=readerForm.getName();
	sex=readerForm.getSex();
	sage=readerForm.getSage();
	tel=readerForm.getTel();
	typeID=readerForm.getTypeID();
%> 
  <tr align="center">
    <td align="center"><%=barCode%></td>  
    <td align="center"><%=name%></td>
    <td align="center"><%=sex%></td>
    <td align="center"><%=sage%></td>
    <td align="center"><%=tel%></td>
    <td align="center"><%=typeID%></td>
    <td align="center"><a href="reader?action=readerModifyQuery&barCode=<%=barCode%>">修改</a></td>
    <td align="center"><a href="reader?action=readerDel&barCode=<%=barCode%>">删除</a></td>
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
