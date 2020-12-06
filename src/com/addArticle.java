package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addArticle
 * 
 */
@WebServlet(description = "添加文章servlet", urlPatterns = { "/addArticle" })
public class addArticle<boolen> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("进入初始化生命周期");
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//注释掉影响显示乱码问题
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		System.out.print("进入doGet处理方法");
		PrintWriter out = ( response).getWriter();
		final String DB_URL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
		System.out.println(DB_URL);
		 //  数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "123456";
        request.setCharacterEncoding("utf-8");
        
        //((ServletResponse) request).setContentType("text/html");
        response.setContentType("text/html;chartset=UTF-8");
        response.setCharacterEncoding("utf-8");
		//获取请求的参数
		//String title = request.getParameter("title");
        //String title = new String(request.getParameter("title").getBytes("iso-8859-1"), "utf-8");
        String title = request.getParameter("title");
		String content = request.getParameter("content");
		String catalog = request.getParameter("catalog");
		System.out.println("catalog:"+catalog);
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
            sql = "INSERT INTO article_table (article_title, article_content, article_catalog) VALUES ( ' "+title+" ',' "+content+"',' "+catalog+" ')";
            System.out.println("sql:"+sql);
           //boolean rs = stmt.execute(sql);
           System.out.println("123");
           int result = stmt.executeUpdate(sql);
           if (result == 1) {
        	   //response.setCharacterEncoding("UTF-8");
        	   //response.setContentType("text/html;charset=UTF-8"); 
        	   request.getRequestDispatcher("getAllArticle").forward(request, response);
        	   /*String sqlQuery = "SELECT * FROM test_data.article_table";
        	   ResultSet rsQuery = stmt.executeQuery(sqlQuery);
        	   System.out.println(rsQuery);
        	   // 设置响应内容类型
        	      response.setContentType("text/html");
        	   // 展开结果集数据库
               while(rsQuery.next()){
                  // 通过字段检索
                  String title1  = rsQuery.getString("article_title");
                  String content1 = rsQuery.getString("article_content");
                 

                  // 输出数据
                  out.write("<h5>"+ title1+"</h5>" +
     				"<hr>" +
     				"<p>"+ content1 +"</p>");
               }*/
           } else {
        	   
           }
           //System.out.println("rs:" + rs);
           
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.print("进入doPost()方法");
		//设置请求的编码类型
		
		
	
	}

}
