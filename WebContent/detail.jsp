<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文章详情页</title>
</head>
<body>
	<%
		String title = String.valueOf(request.getAttribute("title"));
		String content = String.valueOf(request.getAttribute("content"));
		//out.println(title);
	%>
	<h3><%= title%></h3>
	<hr>
	<p>
		<%= content%>
	</p>
	
</body>
</html>