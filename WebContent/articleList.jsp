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
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body style="overflow-y: auto;">
	<label>选择目录:</label>
	<select name="catalog" style="width: 200px;height: 30px;">
		<option value="1">目录一</option>
		<option value="2">目录二</option>
		<option value="3">目录三</option>
	</select>
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
	%>
	<div id="article-list">
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
	</div>
	 
	<script type="text/javascript">
		$("select[name='catalog']").change(function(){
			var catalog = $(this).val();
			$.ajax({
				method: 'get',
				url: '/myFirstJavaProject/getArticlesData',
				dataType:'json',
				data:{
					catalog: catalog
				},
				success: function(res){
					var strHtml ='';
					//console.log(res);
					if (catalog == '1') {
						for(var i=0; i<res.length; i++) {
							strHtml += '<article>' +
							'<h2>'+ res[i].title +'</h2><label>所属目录:'+ res[i].catalog+'</label>' +
							'<p>'+ res[i].content+'</p>'
							+'</article>';
						}
						
					} else if (catalog == '2') {
						var strHtml ='';
						for(var i=0; i<res.length; i++) {
							
							strHtml +='<div class="container-fluid">' +
							'<!-- Stack the columns on mobile by making one full-width and the other half-width -->' +
								'<div class="row">' +
								  '<div class="col-xs-12 col-md-8">' +
										'<h2>'+ res[i].title+'</h2><label>所属目录:'+ res[i].catalog+'</label>' +
										'<p>'+ res[i].content+'</p>' +
								'</div>'+
								  '<div class="col-xs-6 col-md-4">' +
								  	'<h2>'+ res[i].title+'</h2><label>所属目录:'+ res[i].catalog+'</label>'+
									'<p>'+ res[i].content+'</p>' +
								'</div>' +
								'</div>'+
							'</div>';
						}
					}
					//console.log(strHtml);
					$('#article-list').html(strHtml);
				}
			});
		});
	</script>
</body>
</html>