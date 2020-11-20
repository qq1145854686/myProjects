package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getArticleDetail
 */
@WebServlet(description = "获取文章详情", urlPatterns = { "/getArticleDetail" })
public class getArticleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getArticleDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取页面路劲参数
		String id = request.getParameter("id");
		System.out.print(id);
		//int id = Integer.parseInt(str);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//注册JDBC驱动器
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //  数据库的用户名与密码，需要根据自己的设置
        final String DB_URL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
        final String USER = "root";
        final String PASS = "123456";
        request.setCharacterEncoding("utf-8");
        //打开一个连接
        Connection conn = null;
        try {
			 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sqlQuery = "SELECT * FROM test_data.article_table where article_id ='"+id+"'";
        System.out.print(sqlQuery);
        
		//执行sql语句
        Statement stmt = null;
        try {
			 stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {
			System.out.print("123");
			ResultSet rsQuery = stmt.executeQuery(sqlQuery);
			System.out.print(rsQuery);
			while(rsQuery.next()){
				System.out.print("456");
				 String title  = rsQuery.getString("article_title");
				 request.setAttribute("title", title);
				 System.out.print(title);
				 String content = rsQuery.getString("article_content");
				 request.setAttribute("content", content);
			}
			request.getRequestDispatcher("detail.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置响应内容类型
	      response.setContentType("text/html");
	      
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
