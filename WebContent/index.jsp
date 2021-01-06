<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识库管理</title>
<!-- 字节跳动引用速度较快 -->
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script> -->
<style type="text/css">
	nav ul {
		width: 100%;
	}
	nav ul li {
		background-color: #000;
		list-style: none;
		text-align: center;
		text-decoration: none;
		width: 100%;
		height: 50px;
		line-height: 50px;
		margin-bottom: 8px;
		margin-left: -40px;
	}
</style>
</head>
<body>
	<!-- jsp頁面初始化調用生命週期 
	<%-- <%!
		private int initNum = 0;
		private int serviceNum = 0;
		private int destroyNum = 0;
		public void jspInit () {
			initNum++;
			System.out.println("jspInit方法初始了" +initNum+"次");
		}
	%> --%>
	-->
	<!-- 統計生命週期次數 -->
	<!-- 
		<%-- <%
			serviceNum++;
			System.out.println("jspService()方法调用了"+ serviceNum+"次");
			String content1 = "生命周期初始化次数:" + initNum;
			String content2 = "服务调用次数:" + serviceNum;
		%> --%>
	 -->
	<!-- 
		<h1>hello world!</h1>
		<h4>生命周期测试</h4>
		<p><%-- <%= content1 %> --%></p>
		<p><%-- <%= content2 %> --%></p>
		<hr>
		<form action="/myFirstJavaProject/loginServlet">
			<label>用户名:</label><input  type="text" name="username"/><br>
			<lable>密码:</lable><input type="text" name="pwd"/><br>
			<button type="submit">登录</button>
		</form>
	 -->
	 <nav><h5 style="display:inline-block;">我的应用首页&nbsp;&gt;</h5><label id="second"></label>&nbsp;&gt;</nav>
	 
	 <hr>
	 <%
	 	String username="admin";
	 	//List list = new ArrayList();
	 	//list= request.getAttribute("list2");
	 	//username = application.getAttribute("username").toString();
	 	Cookie[] cookies = request.getCookies();
	 	System.out.println(cookies);
	 	String user = "";
	 	if (cookies != null) {
	 		//遍歷cookies對象集合
	 		for(Cookie cookie: cookies) {
	 			if(cookie.getName().equals("username")){
	 				user = cookie.getValue();
	 				System.out.println("user:"+ user);
	 			}
	 		}
	 	}
	 %> 
	
	 <header style="background: green;height: 100px;">
		 <div style="float: right; height:40px; line-height: 40px; padding:10px;"><strong>欢迎你！ <%= username %> </strong></div>
		 <div style="clear: both;"></div>
	 <hr>
	 </header>
		<main style="background-color:#fff; width: 100%; height: 400px;">
			<nav style="background-color:gray;width:200px;height:400px;float:left;">
			<ul id="nav">
				<li> <a href="addArticle.jsp?edit=0" target="article">文章管理</a></li>
				<li> <a href="user.jsp" target="article">用户管理</a></li>
				<li><a href="echarts.jsp" target="article">统计分析</a></li>
				<li> <a href="limit.jsp" target="article">权限管理</a></li>
				<li> <a href="info.jsp" target="article">个人中心</a></li>
			</ul>
			</nav>
			<section style="background: #ececec;margin-left: 200px;height: 400px; ">
				<iframe frameborder="0" width="100%" height="300px" name="article" scrolling="yes" src="addArticle.jsp"></iframe>
			</section>
			<!-- <aside style="width: 200px; float: right;">
				这是侧边栏<br>这是侧边栏<br>这是侧边栏<br>
			</aside> -->
		</main>
	<footer style="background-color: orange; height: 80px; line-height: 80px; text-align: center;">
	CopyRight@copy2020版权所有&copy湖北工业大学|反馈意见：1145854686@qq.com
	地址：湖北省武汉市洪山区南李路28号 |邮编：430068 
	</footer>
	 
	 <script type="text/javascript">
	 	$('#nav a').click(function(){
	 		var secondNavName = $(this).html();
	 		$('#second').html(secondNavName);
	 	});	 
	 </script>
	 
</body>
</html>