<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<style>
	body{
		background: green;
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
		<h3>注册</h3>
		<div class="box">
			<form action="/myFirstJavaProject/register" method="post">
				<label>账号：</label><input type=''text" name="username"/>
				<label>密码：</label><input type=''password" name="password" />
				<button type=''submit" >注册</button>
				<a href="login.jsp">已有账号</a>
			</form>
			
		</div>
	</div>
</body>
</html>