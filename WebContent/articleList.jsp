<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的文章列表</title>
</head>
<body>
	<h1>文章列表</h1>
	<hr>
	<%
		List a = new ArrayList();
		a.add("贝贝");
		a.add("晶晶");
		a.add("欢欢");
		a.add("莹莹");
		a.add("妮妮");
		request.setAttribute("a",a);
		//response.setCharacterEncoding("utf-8");
		//request.setCharacterEncoding("utf-8");
	%>
	<!-- jstl表达式遍历输出内容 -->
	<c:forEach var="item"  items="${list}"  begin="0" end="10"  varStatus="s" >
		<article>
			<h2><c:out value="  ${item.title } "/></h2><label>所属目录:</label>&nbsp;<c:out value="  ${item.catalog } "/><br>
			<p><c:out value="${item.content} "/></p>
		</article>
		<aside>
			<a href="/myFirstJavaProject/getArticleDetail?id=${item.id}"><button>详情</button></a>
			<a  href="/myFirstJavaProject/deleteArticle?id=${item.id}"><button>删除</button></a>
			<a href="/myFirstJavaProject/addArticle.jsp?title=${item.title}&content=${item.content}&catalog=${item.catalog}&edit=1"  id="edit"><button>编辑</button></a>
		</aside>
		<hr>
	</c:forEach> 
</body>
</html>