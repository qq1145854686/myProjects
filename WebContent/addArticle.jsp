<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
<title>添加文章</title>
<!-- 字节跳动页面引用速度较快 -->
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script> -->
<style type="text/css">
	.high{ color: red; }
	.msg{ font-size: 13px; }
	 .onError{ color: red; }
	 .onSuccess{ color: green; }
	 .hidden {display: none;}
</style>
</head>
<body>
	<% 
		//设置编码
		request.setCharacterEncoding("utf-8");
		 //Boolean edit = Boolean.valueOf(request.getParameter("edit")); 
		String edit = (request.getParameter("edit"));
		request.setAttribute("edit", edit);
	%>
	<c:set var="edit" scope="page" value="${edit}"/>
	<c:if test="${ edit >0}">
		<%
			//设置编码
			request.setCharacterEncoding("utf-8");
			//获取浏览器地址栏参数
			String title = request.getParameter("title");	
			String content = request.getParameter("content");	
			System.out.println("content:"+ content);
			int catalog = Integer.parseInt(request.getParameter("catalog"));	
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("catalog", catalog);
		%>
		<labe id="title" class="hidden"><%= title %></labe>
		<labe id="content" class="hidden"><%= content %></labe>
		<labe id="catalog" class="hidden"><%= catalog %></labe>
	</c:if>
	<p>
		<div class="contaier" style="width: 450px; margin: 30px auto;">
		 	<div class="title" style="border-bottom: 1px solid black;">发布文章</div>
		 	<form action="/myFirstJavaProject/addArticle" method="get">
		 		<table >
		 			<tr>
		 				<td>文章标题:</td>
		 				<td><input class="required" type="text"  name="title" placeholder="请输入文章标题"/></td>
		 			</tr>
		 			<tr >
		 				<td>文章内容:</td>
		 				<td>
		 					<textarea rows="10" cols="51" name="content" placeholder="请输入文章内容">
		 					</textarea>
		 				</td>
		 			</tr>
		 			<tr >
		 				<td>所属目录:</td>
		 				<td>
		 					<select name="catalog">
		 						<option value="1">目录1</option>
		 						<option value="2">目录2</option>
		 						<option value="3">目录3</option>
		 					</select> 
		 				</td>
		 			</tr>
		 			<tr >
		 				<td>文章封面:</td>
		 				<td>
		 					<label>选择封面:</label>
		 					<input type="file" name="file" id="file"/>
		 				</td>
		 			</tr>
		 			<tr><td align="center">
		 				<button type="submit" id="submit">确定添加</button>
		 			</td></tr>
		 		</table>
		 	</form>
		 </div>
	</p>
	<script type="text/javascript">
		 //console.log($('#text').html());
		 //为表单的必填文本框添加提示信息（选择form中的所有后代input元素）
			 $("form :input.required").each(function () {
				 var $parent = $(this).parent();
				 //创建元素
				 var $required = $("<strong class='high'>*</strong>");
				 //将它追加到文档中
				 $(this).parent().append($required);
				//为表单元素添加失去焦点事件
				 $("form :input").blur(function(){
					//验证名称
					 if($(this).is("#title")){
					  if($.trim(this.value) == "" || $.trim(this.value).length < 6){
					  var errorMsg = " 请输入至少6位的名称！";
					  //class='msg onError' 中间的空格是层叠样式的格式
					  $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
					  }else{
					  var okMsg=" 输入正确";
					  $parent.find(".high").remove();
					  $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
					  }
					 }
				 });
			});
		   //提交表单时校验
		 	$("#submit").click(function(){
		 		var $parent = $(this).parent();
		 		if($("input[name='title']").val()==''){
		 			 var errorMsg = " 请输入至少6位的名称！";
					  //class='msg onError' 中间的空格是层叠样式的格式
					  $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
					  return false
		 		}else {
		 			console.log('123');
		 		} 
		 	});
		   //详情页表单数据回显
		   var title = $("#title").html();
		   if(title != null) {
			   $("input[name=title]").val(title);
			   $("textarea[name=content]").val($("#content").html());
			   $("select[name=catalog]").val(($("#catalog").html()));
		   }
		   
</script>
</body>
</html>