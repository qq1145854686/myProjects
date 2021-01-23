<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文章详情页</title>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style>
	h3 {
		text-align: center;
	}
	p {
		text-indent:2em;
	}
</style>
</head>
<body>
	<div class="container-fuild">
		<%
		String title = String.valueOf(request.getAttribute("title"));
		String content = String.valueOf(request.getAttribute("content"));
		%>
		<h3>
			 ${title }
		</h3>
		<hr>
		<p>
			${content}
		</p>
	</div>
</body>
</html>