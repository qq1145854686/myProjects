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
	<nav><h5 style="display:inline-block;">我的应用首页&nbsp;&gt;</h5><label id="second"></label>&nbsp;&gt;</nav>
	 <hr>
	 <%
	 	String username="admin";
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