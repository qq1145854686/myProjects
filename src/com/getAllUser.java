package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class getAllUser
 */
@WebServlet(description = "获取所有用户", urlPatterns = { "/getAllUser" })
public class getAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//设置返回编码
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		// 注册 JDBC 驱动器
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    //数据库的用户名与密码，需要根据自己的设置
        final String DB_URL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
        final String USER = "root";
        final String PASS = "Qq4210831994.";
        request.setCharacterEncoding("utf-8");
        
     // 打开一个连接
        Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 执行 SQL 查询
        Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   String sqlQuery = "SELECT * FROM test_data.user";
 	   ResultSet rsQuery = null;
		try {
			rsQuery = stmt.executeQuery(sqlQuery);
			request.setAttribute("result", rsQuery);
			ArrayList list = new ArrayList();
			while(rsQuery.next()){
        		JSONObject jsonObject = new JSONObject();
			   // 通过字段检索
        	   int id = rsQuery.getInt("iduser");
        	   //System.out.println("id:"+id);
        	   jsonObject.put("id", id);
			   String username  = rsQuery.getString("username");
			   jsonObject.put("username", username);
			   String password = rsQuery.getString("password");
			   jsonObject.put("password", password);
			   String role = rsQuery.getString("userrole");
			   jsonObject.put("role", role);
			   list.add(jsonObject);
			}
			writer.append(list.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
