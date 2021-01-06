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
 * Servlet implementation class getArticlesData
 */
@WebServlet(description = "获取文章列表数据", urlPatterns = { "/getArticlesData" })
public class getArticlesData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getArticlesData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		 request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		//获取请求参数
		
		String catalog = request.getParameter("catalog");
		//从请求参数中获取标题值
		String title = request.getParameter("article_title");
		PrintWriter writer = response.getWriter();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//建立数据库连接获取文章列表
		// 注册 JDBC 驱动器
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //  数据库的用户名与密码，需要根据自己的设置
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
		String sqlQuery = "";
       if (title != null) {
    	    sqlQuery = "SELECT * FROM test_data.article_table where article_catalog ='"+ catalog+ "' and article_title ='"+ title+"'"; 
       } else {
    	   sqlQuery = "SELECT * FROM test_data.article_table where article_catalog ='"+ catalog+ "'"; 
       }
       ResultSet rsQuery = null;
		try {
			rsQuery = stmt.executeQuery(sqlQuery);
			request.setAttribute("result", rsQuery);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   
 	   // 设置响应内容类型
 	      response.setContentType("text/html");
 	   // 展开结果集数据库
        try {
        	//Map<String, String> map = new HashMap<String,String>();
        	 
        	ArrayList list = new ArrayList();
        	while(rsQuery.next()){
        		JSONObject jsonObject = new JSONObject();
			   // 通过字段检索
        	   int id = rsQuery.getInt("article_id");
        	   String title1  = rsQuery.getString("article_title");
			   jsonObject.put("title", title1);
			   String content = rsQuery.getString("article_content");
			   jsonObject.put("content", content);
			   int catalog1 = rsQuery.getInt("article_catalog");
			   jsonObject.put("catalog", catalog1);
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
