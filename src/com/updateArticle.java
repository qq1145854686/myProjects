package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class updateArticle
 */
@WebServlet(description = "更新文章内容", urlPatterns = { "/updateArticle" })
public class updateArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateArticle() {
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
		
		 //  数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "Qq4210831994.";
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;chartset=UTF-8");
        //获取请求参数
        String id = request.getParameter("article_id");
        String $title = request.getParameter("article_title");
        String $content = request.getParameter("article_content");
		String catalog = request.getParameter("article_catalog");
		//获取post参数 
		/*StringBuffer sb = new StringBuffer() ; 
		InputStream is = request.getInputStream(); 
		InputStreamReader isr = new InputStreamReader(is, "utf-8");   
		BufferedReader br = new BufferedReader(isr); 
		String s = "" ; 
		while((s=br.readLine())!=null){ 
		sb.append(s) ; 
		} 
		String str =sb.toString();
		System.out.println("str:"+str);
		//JSONObject pa = new JSONObject(str);
		JSONObject pa=JSONObject.fromObject(str);
    	String $title=pa.getString("article_title");
    	int catalog=pa.getInt("article_catalog");
    	String $content=pa.getString("article_content");
    	int id = pa.getInt("article_id");
    	System.out.println("title:"+$title);*/
		
    	try{
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");
            // 打开一个连接
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行 SQL 查询
            Statement stmt = conn.createStatement();
            String sql;
            //sql = "UPDATE article_table SET article_title=concat('123', article_title) , article_content=concat('456', article_content) , article_catalog="+catalog+" where article_id="+id +"";
            sql = "UPDATE article_table SET article_title='"+ $title +"' , article_content='"+$content+"' ,article_catalog="+catalog+" where article_id="+id +"";
            int result = stmt.executeUpdate(sql);
          // 设置响应内容类型
  	      response.setContentType("text/html");
          if (result == 1) {
        	   //response.sendRedirect("getArticlesData?catalog=3");
        	   request.getRequestDispatcher("getAllArticle").forward(request, response);
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
