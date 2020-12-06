<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的第一个jsp应用程序</title>
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
	 <h5>我的应用首页</h5>
	 <hr>
	 <%
	 	String username="admin";
	 	//List list2 = new ArrayList();
	 	//list2= request.getAttribute("list2");
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
	 <div style="float: right; height:20px;"><strong>欢迎你！ <%= username %> </strong></div>
	 <div style="clear: both;"></div>
	 <hr>
	 
	 <div class="contaier" style="width: 450px; margin: 130px auto;">
	 	<div class="title" style="border-bottom: 1px solid black;">发布文章</div>
	 	<form action="/myFirstJavaProject/addArticle" method="GET">
	 		<table >
	 			<tr>
	 				<td>文章标题:</td>
	 				<td><input type="text" name="title" placeholder="请输入文章标题"/></td>
	 			</tr>
	 			<tr>
	 				<td>文章内容:</td>
	 				<td>
	 					<textarea rows="5" cols="25" name="content" placeholder="请输入文章内容">
	 					</textarea>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>所属目录:</td>
	 				<td>
	 					<select name="catalog">
	 						 <c:forEach items="${list2}"  var="k" > 
	 						 	<%-- <c:out value="  ${k} "/><br> --%> 
	 						 	<option value="${k} ">${k} </option> 
	 						 </c:forEach>
	 					</select>
	 					<!-- <select>
	 						<option value="1">目录1</option>
	 						<option value="2">目录2</option>
	 						<option value="3">目录3</option>
	 					</select> -->
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>文章封面:</td>
	 				<td>
	 					<label>选择封面:</label>
	 					<input type="file" name="file" id="file"/>
	 				</td>
	 			</tr>
	 			<tr><td align="center">
	 				<button type="submit">确定添加</button>
	 			</td></tr>
	 		</table>
	 	</form>
	 </div>
	 
</body>
</html>