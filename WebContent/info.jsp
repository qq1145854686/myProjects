<%@ page language="java" contentType="text/html; charset=utf-8"
    import="javax.servlet.ServletContext,java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<style>
	label{
		display:inline-block;
		width: 85px;
		vertical-align: middle;
	}
	input, textarea{
		margin-top: 20px;
		width: 200px;
	}
	.hidden {
		display: none;
	}
</style>
</head>
<body>
	<%
	   int role = (Integer) application.getAttribute("role"); 
	   int user_id = (Integer) application.getAttribute("user_id"); 
		String username = (String) session.getAttribute("username"); 
		String password = (String) session.getAttribute("password");
		
	%>
	<div style="display: none;">
		<span id="user_id"><%= user_id %></span>
		<span id="username"><%= username %></span>
		<span class="password"><%= password %></span>
		<c:choose>
		    <c:when test="${role == 1}">
		        <span class="role">管理员</span>
		    </c:when>
		    <c:when test="${role == 2}">
		        <span class="role">普通用户</span>
		    </c:when>
		    <c:otherwise>
		        <span class="role">暂无分配此角色</span>
		    </c:otherwise>
	   </c:choose>
	</div>
	<form  method="post" style="width: 400px; height:300px; margin: 20px auto; text-align: center;">
		<label>用户名:</label><input type="text" name="username"/><br>
		<label>密码:</label><input type="password" name="password"/><span id="open-eye" class="glyphicon glyphicon-eye-open"></span><span id="close-eye" class="glyphicon glyphicon-eye-close hidden"></span><br>
		<label>角色:</label><input type="text" name="role" readonly="readonly"/><br>
		<label>个人简介:</label><textarea rows="10" cols="20" name="description"></textarea><br>
		<button type="button" id="submit">提交</button>
	</form>
	<script type="text/javascript">
		$("input[name='username']").val($("#username").html());
		$("input[name='password']").val($(".password").html());
		$("input[name='role']").val($(".role").html());
		//控制密码显示与隐藏
		$("#open-eye").click(function(){
			$("input[name='password']").prop('type', "text");
			$(this).hide();
			$("#close-eye").removeClass('hidden');
			$('#close-eye').show();
		});
		$("#close-eye").click(function(){
			$("input[name='password']").prop('type', "password");
			$(this).hide();
			
			$("#open-eye").show();
		});
		//提交请求后台
		$("#submit").click(function(){
			var id =$("#user_id").html();
			var username =$("input[name='username']").val();
			var password =$("input[name='password']").val();
			var role =$("input[name='role']").val();
			if (role == '管理员') {
				role = '1';
			} else if (role == '普通用户') {
				role = '2'
			}
			var description = $("textarea[name='description']").val();
			$.ajax({
				url: '/myFirstJavaProject/updateUserINFO',
				method: 'post',
				data: {
					id: id,
					username:username,
					password: password,
					role: role,
					description: description
				},
				dataType: 'json',
				success: function(res){
					console.log(res);
				}
			});
		});
	</script>
</body>
</html>