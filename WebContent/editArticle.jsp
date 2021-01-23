<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<title>文章编辑页</title>
<style>
	form{
		width: 450px;
		margin:100px auto;
	}
	.hidden{
		display: none;
	}
</style>
</head>
<body>
<%
	//获取页面参数
	String id = request.getParameter("id");
	//打印参数
	System.out.println("id:"+id);
	//注册JDBC驱动
	Class.forName("com.mysql.jdbc.Driver");
	//打开一个连接
	Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true","root","Qq4210831994.");
	String sqlQuery = "SELECT * FROM test_data.article_table where article_id = '"+ id +"'";
	Statement stmt = conn.createStatement();
	ResultSet rs= stmt.executeQuery(sqlQuery);
	String title = "";
	String content = "";
	String catalog = "";
	while(rs.next()) {
		title = rs.getString("article_title").toString();
		content = rs.getString("article_content").toString();
		catalog = rs.getString("article_catalog").toString();
		//打印结果
		System.out.println("title:"+title);
	}
%>
<form   method="post">
	<label id="article_id" class="hidden"><%= id %></label>
	<label>文章标题:</label><input type="text" name="title" value="<%= title %>"/><br>
	<label>文章内容:</label><textarea name="content" value="" rows="10" cols="50"><%= content %></textarea><br>
	<label>所属目录:</label>
	<select name="catalog" >
		<option value="1">目录一</option>
		<option value="2">目录二</option>
		<option value="3">目录三</option>
	</select>
	<button type="button"  id="submit">更新</button>
</form>
	<script type="text/javascript">
		$("select[name=catalog]").val('3');
		//更新文章
		$("#submit").click(function(){
			 $.ajax({
				url: '/myFirstJavaProject/updateArticle',
				type: 'POST',
				async:false, //请求改为同步
				contentType: 'application/x-www-form-urlencoded',
				data: {
					article_id: $("#article_id").text(),
					article_title: $("input[name='title']").val(),
					article_content: $("textarea[name = content]").val(),
					article_catalog: '3'
				},
				success: function(data) {
					console.log(data);
					
					document.write(data);
				}
			}); 
		});
		
	</script>
</body>
</html>