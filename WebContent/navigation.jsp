<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="CSS/navigator.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function quit(){
		if(confirm("真的要退出系统吗?")){
			window.location.href="logout.jsp";
		}
	}
	function show(li){
        var sumMenu = li.getElementsByTagName("ul")[0];
        sumMenu.style.display = "block";
    }
    function hide(li){
        var sumMenu = li.getElementsByTagName("ul")[0];
        sumMenu.style.display = "none";
    }
</script>    
<div class="container">
	<div class="nav">
            <ul>
	            <li>
	            	<a href="main.jsp" class="word_white">首页</a>
	            </li>
	        	<li onmouseover="show(this)" onmouseout="hide(this)">读者管理
	        	   <ul class="menu1">
	                     <li><a href="readerType?action=readerTypeQuery" class="word_white">读者类型管理</a></li>
	                     <li><a href="reader?action=readerQuery" class="word_white">读者档案案例</a></li>
	               </ul>
	            </li>
	            <li onmouseover="show(this)" onmouseout="hide(this)">图书管理
	        	   <ul class="menu2">
	                     <li><a href=bookType?action=bookTypeQuery class="word_white">图书类型管理</a></li>
	                     <li><a href=book?action=bookQuery class="word_white">图书档案案例</a></li>
	               </ul>
	            </li>
	            <li onmouseover="show(this)" onmouseout="hide(this)">图书借还
	            	<ul class="menu3">
	                     <li><a href="borrow?action=bookborrow">图书借阅</a></li>
	                     <li><a href="borrow?action=bookrenew">图书续借</a></li>
	                     <li><a href="borrow?action=bookback">图书归还</a></li>
	               </ul>
	            </li>
	            <li onmouseover="show(this)" onmouseout="hide(this)">系统查询
	            	<ul class="menu4">
	                     <li><a href=book?action=bookifQuery>图书档案查询</a></li>
	                     <li><a href=borrow?action=borrowQuery>图书借阅查询</a></li>
	                     <li><a href=borrow?action=Bremind>借阅到期提醒</a></li>
	               </ul>
	            </li>
	            <li>
	            	<a  href="manager?action=querypwd" class="word_white">更改口令</a>
	            </li>
	        	<li>
	        		<a  href="#" onClick="quit()" class="word_white">退出系统</a>
	        	</li>
           </ul>
     </div>
</div>
