package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateUserINFO
 */
@WebServlet(description = "更新个人主页信息", urlPatterns = { "/updateUserINFO" })
public class updateUserINFO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateUserINFO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = ( response).getWriter();
		final String DB_URL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String id =  request.getParameter("id");
		System.out.println("username:"+username);
		
		 //  数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "Qq4210831994.";
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/x-www-form-urlencoded;chartset=UTF-8");
        try{
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行 SQL 查询
            Statement stmt = conn.createStatement();
            System.out.println("stmt:"+stmt);
            String sql;
            sql = "UPDATE user SET username='"+ username +"' , password='"+password+"' ,userrole="+role+" where iduser="+id +"";
            System.out.println("sql:"+sql);
           int result = stmt.executeUpdate(sql);
           if (result == 1) {
        	   out.append("更新成功请重新登录!");
        	} else {
        		out.append("更新失败!");
        	}
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

}
