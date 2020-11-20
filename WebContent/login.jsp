<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的登录页面</title>
<style>
	body{
		background-image: url("./登陆背景.png");
		background-repeat: no-repeat;
	    background-size: cover;
	}
	.wrap{
		background-image: url("./背景.png");
		text-align: center;
		margin: 200px auto;
		width: 300px;
		height: 300px;
		border: 1px solid gray;
	}
	.box {
		padding-left: 40px;
		padding-right: 40px; 
		padding-top: 20px;
		padding-bottom: 20px;
		margin-top: 60px;
		margin-left: 20px;
		margin-right: 20px;
	}
</style>
</head>
<body>
	<div class="wrap">
		<h3>登陆</h3>
		<div class="box">
			<form action="do_login.jsp">
				<label>用户名:</label><input type="text" name="username" placeholder="请输入用户名"/><br>
				<label>密码:</label><input type="password" name="pwd" placeholder="密码"/><br>
				<button type="submit">登录</button>
			</form>
		</div>
	</div>
	
	<!-- <hr>
	<form action="/myFirstJavaProject/loginServlet">
		<label>用户名:</label><input type="text" name="username" placeholder="请输入用户名"/><br>
		<label>密码:</label><input type="password" name="pwd" placehoder="请输入密码"/><br>
		<button type="submit">提交</button>
	</form>
	<hr>
	<a href="/myFirstJavaProject/Jdbctest2">测试JDBC</a>
	<hr>
	<a href="/myFirstJavaProject/databaseAccess">访问MYSQL</a>
	<hr>
	<a href="/myFirstJavaProject/addArticle">添加文章</a> -->
</body>
</html>