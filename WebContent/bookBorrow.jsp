<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.borrow.BorrowDB" %>
<%@ page import="com.borrow.BorrowForm" %>
<%@ page import="com.reader.ReaderForm" %>
<%@ page import="java.util.*"%>
<link href="CSS/contain.css" rel="stylesheet" type="text/css" />
<html>
<%
	ReaderForm readerForm=(ReaderForm)request.getAttribute("readerinfo");
	Collection coll=(Collection)request.getAttribute("borrowinfo");
	int borrowNumber=0;
%>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
		<script language="javascript">
		function checkreader(form){
			if(form.barcode.value==""){
				alert("请输入读者条形码!");form.barcode.focus();return;
			}
			form.submit();
		}
		function checkbook(form){
			if(form.barcode.value==""){
				alert("请输入读者条形码!");form.barcode.focus();return;
			}		
			if(form.inputkey.value==""){
				alert("请输入查询关键字!");form.inputkey.focus();return;
			}
			form.submit();
		}
		</script>
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<div class="ban">
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" height="509"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="27" valign="top" style="padding:5px;" class="word_orange">当前位置：图书借还 &gt; 图书借阅 &gt;&gt;&gt;</td>
  </tr>
  <tr>
    <td align="center" valign="top" style="padding:5px;"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<form name="form1" method="post" action="borrow?action=bookborrow">
      <tr>
        <td height="47" background="Images/borrowBackRenew.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="72" align="center" valign="top" background="Images/main_booksort_1.gif" bgcolor="#F8BF73"><table width="96%" border="0" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#F8BF73">
          <tr>
            <td valign="top" bgcolor="#F8BF73"><%

String barcode="";           
String name="";
String sex="";
String sage="";
String tel="";
int typeID=0;

if(readerForm!=null){
	barcode = readerForm.getBarCode();
	name=readerForm.getName();
	sex=readerForm.getSex();
	sage = readerForm.getSage();
	tel = readerForm.getTel();
	typeID=readerForm.getTypeID();
}
%>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

				
                  <tr>
                    <td><table width="90%" height="21" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="24%" height="18" style="padding-left:7px;padding-top:7px;"><img src="Images/reader_checkbg.jpg" width="142" height="18"></td>
                          <td width="76%" style="padding-top:7px;">读者条形码：
                            <input name="barcode" type="text" id="barcode" size="24" value="<%=barcode%>" >
                            &nbsp;
                            <input name="Button" type="button" class="btn_grey" value="确定" onClick="checkreader(form1)"></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="13" align="left" style="padding-left:7px;"><hr width="90%" size="1"></td>
                    </tr>
                  <tr>
                    <td align="center"><table width="96%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td height="27">姓&nbsp;&nbsp;&nbsp;&nbsp;名：
                            <input name="readername" type="text" id="readername" value="<%=name%>"></td>
                          <td>性&nbsp;&nbsp;&nbsp;&nbsp;别：
                            <input name="sex" type="text" id="sex" value="<%=sex%>"></td>
                          <td> 年龄：
                            <input name="readerType" type="text" id="readerType" value="<%=sage%>"></td>
                        </tr>
                        <tr>
                          <td height="27">电话：
                            <input name="paperType" type="text" id="paperType" value="<%=tel%>"></td>
                          <td>读者类型：
                            <input name="paperNo" type="text" id="paperNo" value="<%=typeID%>">
                          </td>
                        </tr>
                    </table></td>
                  </tr>
				 
              </table></td>
          </tr>
               <tr>
                 <td height="32" background="Images/borrow_if.gif">&nbsp;添加的依据：
	                   <input name="f" type="radio" class="noborder" value="barcode" checked>图书条形码 &nbsp;&nbsp;
	                   <input name="f" type="radio" class="noborder" value="bookname">图书名称&nbsp;&nbsp;
				   <input name="inputkey" type="text" id="inputkey">
                   <input name="Submit2" type="button" class="btn_grey" value="确定" onClick="checkbook(form1)">
                   </td>
               </tr> 
          <tr>
            <td valign="top" bgcolor="#FCEC9A" style="padding:5px"><table width="99%" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#F6B83B" bgcolor="#FFFFFF">
                   <tr align="center" bgcolor="#F9D16B">
                     <td width="29%" height="25">图书名称</td>
                     <td width="12%">借阅时间</td>
                     <td width="14%">应还时间</td>
                     <td width="17%">价格(元)</td>
                     <td width="14%">作者</td>
                   </tr>
<%
String bookname="";
String borrowTime="";
String backTime="";
Float price=new Float(0);
String author="";

if(coll!=null && !coll.isEmpty()){
	borrowNumber=coll.size();
	Iterator it=coll.iterator();
	while(it.hasNext()){
		BorrowForm borrowForm=(BorrowForm)it.next();
		bookname=borrowForm.getBookName();
		borrowTime=borrowForm.getBorrowTime();
		backTime=borrowForm.getBackTime();
		price=borrowForm.getPrice();
		author=borrowForm.getAuthor();
%>

                   <tr align="center">
                     <td height="25" style="padding:5px;">&nbsp;<%=bookname%></td>
                     <td style="padding:5px;">&nbsp;<%=borrowTime%></td>
                     <td style="padding:5px;">&nbsp;<%=backTime%></td>
                     <td align="center">&nbsp;<%=price%></td>
                     <td align="center">&nbsp;<%=author%></td>
                   </tr> 
<%	}
}
%>
 <input name="borrowNumber" type="hidden" id="borrowNumber" value="<%=borrowNumber%>">
                 </table>
			</td>
          </tr>
		 
        </table></td>
		 
      </tr>
      <tr>
        <td height="19" background="Images/main_booksort_2.gif">&nbsp;</td>
      </tr>
	 </form>
    </table></td>
  </tr>
</table>
    <%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</div>
</body>
</html>