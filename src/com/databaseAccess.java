package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class databaseAccess
 */
@WebServlet(description = "severlet访问数据库", urlPatterns = { "/databaseAccess" })
public class databaseAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public databaseAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//设置request的编码方式
		response.setContentType("text/html;charSet=UTF-8");
		PrintWriter out = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("进入doGet()方法");
		 final String DB_URL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC";
	       
	   //  数据库的用户名与密码，需要根据自己的设置
	        final String USER = "root";
	        final String PASS = "Qq4210831994.";
	        try{
	            // 注册 JDBC 驱动器
	            Class.forName("com.mysql.jdbc.Driver");

	            // 打开一个连接
	            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
	            // 执行 SQL 查询
	            Statement stmt = conn.createStatement();
	            System.out.println("stmt:"+stmt);
	            String sql;
	            //sql = "SELECT id, first, last, age FROM Employees";
	            sql = "INSERT INTO article_table (article_id, article_title, article_content) VALUES (2, '标题2', '内容2')";
	            System.out.println("sql:"+sql);
	            //ResultSet rs = stmt.executeQuery(sql);
	            boolean rs = stmt.execute(sql);
	            System.out.println("rs:"+rs);
	            
	            String docType =
	                    "<!doctype html public \"-//w3c//dtd html 4.0 " +          "transitional//en\">\n";
	                     out.println(docType +
	                     "<html>\n" +
	                     "<head><title>" + "</title></head>\n" +
	                     "<body bgcolor=\"#f0f0f0\">\n" +
	                     "<h1 align=\"center\">" +  "</h1>\n");
				// 展开结果集数据库
	            /*while(rs.next()){
	               // 通过字段检索
	               int id  = rs.getInt("id");
	               int age = rs.getInt("age");
	               String first = rs.getString("first");
	               String last = rs.getString("last");
	               System.out.println(id);
	               // 输出数据
	               
	               out.println("<li>"+"ID: " + id + "</li>");
	               out.println("<li>"+"Age: " + age + "</li>");
	               out.println("<li>"+"First: " + first + "</li>");
	               out.print("<li>"+"Last: " + last + "</li>");
	               out.append("<ul>" +
		            		"<li>"+"ID: " + id + "</li>"+
		            		"<li>"+"Age: " + age + "</li>"+
		            		"<li>"+"First: " + first + "</li>"+
		            		"<li>"+"Last: " + last + "</li>"+
		            		 "</ul>");
	            }
	          
	            out.println("</body></html>");*/
	            
	            // 完成后关闭
	            //rs.close();
	            stmt.close();
	            conn.close();
	         }catch(SQLException se){
	            // 处理 JDBC 错误
	            se.printStackTrace();
	         }catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	         }
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
