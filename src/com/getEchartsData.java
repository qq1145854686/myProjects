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
 * Servlet implementation class getEchartsData
 */
@WebServlet(description = "获取统计图数据", urlPatterns = { "/getEchartsData" })
public class getEchartsData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEchartsData() {
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
		//注册JDBC驱动
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
		String sqlQuery = "SELECT "+ 
		"sum(if(article_catalog = 1,1,0)) AS catalog1 ," +
		"sum(if(article_catalog = 2,1,0)) AS catalog2 ," +
		"sum(if(article_catalog = 3,1,0)) AS catalog3 " +
		"FROM test_data.article_table";
		ResultSet rs = null;
		System.out.println(sqlQuery);
		try {
			rs = stmt.executeQuery(sqlQuery);
			ArrayList list = new ArrayList();
			while(rs.next()){
        		JSONObject jsonObject = new JSONObject();
			   // 通过字段检索
        	   int catalog1 = rs.getInt("catalog1");
        	   int catalog2 = rs.getInt("catalog2");
        	   int catalog3 = rs.getInt("catalog3");
        	   
        	   System.out.println("catalog1:"+catalog1);
        	   jsonObject.put("catalog1", catalog1);
        	   jsonObject.put("catalog2", catalog2);
        	   jsonObject.put("catalog3", catalog3);
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
