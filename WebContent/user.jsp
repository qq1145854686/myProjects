<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="
    java.util.*,
    java.sql.Connection,
    java.sql.DriverManager,
    java.sql.ResultSet,
    java.sql.SQLException,
    java.sql.Statement,
    net.sf.json.JSONObject,
    com.pojo.jdbcUtils,java.util.List" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<style type="text/css">
	table{
		margin: 80px auto;
	}
	table td ,th {
		width: 200px;
		text-align: center;
	}
</style>
</head>
<body>
	<%
		//创建数据库连接对象
		jdbcUtils db = new jdbcUtils();
	 //注册JDBC驱动
	Class.forName("com.mysql.jdbc.Driver");
	//打开一个连接
	Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true","root","Qq4210831994.");
	String sqlQuery = "select * from test_data.user";
	Statement stmt = conn.createStatement();
	ResultSet rs= stmt.executeQuery(sqlQuery);
	ArrayList list = new ArrayList();
	while (rs.next()) {
		JSONObject jsonObject = new JSONObject();
    	System.out.println("123");
        //rs.get+数据库中对应的类型+(数据库中对应的列别名)
        String username =  rs.getString("username");
        jsonObject.put("username", username);
        String password =  rs.getString("password");
        jsonObject.put("password", password);
        int role = rs.getInt("userrole");
        jsonObject.put("role", role);
        System.out.println("username:"+username);
        if (username == null && password == null) {
            list.add("error");
         } else {
            list.add(jsonObject);
        };
      } 
	  System.out.println("list:"+ list);
	  request.setAttribute("list", list);
	%>
	
	<table border="1" style="border-collapse:collapse;" >
		<thead>
			<!-- <th>用户id</th> -->
			<th>用户名</th>
			<th>用户密码</th>
			<th>角色</th>
		</thead>
		<tbody>
		<c:forEach var="item"  items="${list}"  begin="0" end="10"  varStatus="s" >
		 <tr>
		 	<td><c:out value="  ${item.username } "/></td>
			<td><c:out value="  ${item.password } "/></td>
			<td>
				  <c:if test="${item.role==1}">管理员</c:if>
    			  <c:if test="${item.role==2}">普通用户</c:if>
			</td>
		 </tr>
			
		</c:forEach>
		</tbody>
	</table>
</body>
</html>