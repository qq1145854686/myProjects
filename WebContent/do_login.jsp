<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.util.*,java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement" pageEncoding="ISO-8859-1"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!--获取页面参数  -->
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		System.out.println(username);
		//注册JDBC驱动
		Class.forName("com.mysql.jdbc.Driver");
		//打开一个连接
		Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true","root","123456");
		String sqlQuery = "SELECT * FROM test_data.user where username ='"+username+"'and password ='"+password+"'";
		Statement stmt = conn.createStatement();
		ResultSet rs= stmt.executeQuery(sqlQuery);
		if (rs.next()) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("error.jsp");
			System.out.print("用户名密码不存在!");
		}
		
		/* if("admin".equals(username) && "123456".equals(password)){
			session.setAttribute("username", username);
			//application.setAttribute("username", username);
			request.setAttribute("username", username);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("error.jsp");
		} */
		if (username != null) {
			request.setAttribute("username", username);
			application.setAttribute("username", username);
		}
		if(password != null) {
			request.setAttribute("password", password);
		}
		System.out.println("username:"+ username);
		Cookie cookie = new Cookie("username", username);
		System.out.println("设置了cookie");
		cookie.setMaxAge(60*60*24*10);
		
		//跳轉到index.jsp頁面
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		//response.sendRedirect("index.jsp");
		//response.setHeader("refresh", "2;URL=index.jsp");
	%>
</body>
</html>