package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class getAllArticle
 */
@WebServlet(description = "获取文章列表", urlPatterns = { "/getAllArticle" })
public class getAllArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("进入到初始化方法");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入到doGet()方法");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
        final String PASS = "123456";
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
        System.out.println("stmt:"+stmt);
        String sqlQuery = "SELECT * FROM test_data.article_table";
 	   ResultSet rsQuery = null;
		try {
			rsQuery = stmt.executeQuery(sqlQuery);
			request.setAttribute("result", rsQuery);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   System.out.println(rsQuery);
 	   // 设置响应内容类型
 	      response.setContentType("text/html");
 	   // 展开结果集数据库
        try {
        	//Map<String, String> map = new HashMap<String,String>();
        	 JSONObject jsonObject = new JSONObject();
        	ArrayList list = new ArrayList();
        	while(rsQuery.next()){
			   // 通过字段检索
        	   int id = rsQuery.getInt("article_id");
        	   jsonObject.put("id", id);
			   String title  = rsQuery.getString("article_title");
			   jsonObject.put("title", title);
			   System.out.print(title);
			   String content = rsQuery.getString("article_content");
			   jsonObject.put("content", content);
			  /* for(String key : map.keySet()){ String value = map.get(key);
				  System.out.println(key+"  "+value); 
			   }*/
			   System.out.println("jsonObject:"+ jsonObject);
			   list.add(jsonObject);
			   System.out.println("list:"+list);
			}
			//ServletRequest application = null;
			request.setAttribute("list", list);
			request.getRequestDispatcher("articleList.jsp").forward(request, response);
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
