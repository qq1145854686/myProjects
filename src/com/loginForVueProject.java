package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class loginForVueProject
 */
@WebServlet(description = "处理登录", urlPatterns = { "/loginForVueProject" })
public class loginForVueProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginForVueProject() {
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
		
		//获取post参数 
		StringBuffer sb = new StringBuffer() ; 
		InputStream is = request.getInputStream(); 
		InputStreamReader isr = new InputStreamReader(is);   
		BufferedReader br = new BufferedReader(isr); 
		String s = "" ; 
		while((s=br.readLine())!=null){ 
			sb.append(s) ; 
		} 
		String str =sb.toString();
		System.out.println("str:"+str);
		JSONObject pa=JSONObject.fromObject(str);
    	String $username=pa.getString("username");
    	String $password=pa.getString("password");
    	
    	response.setCharacterEncoding("utf-8");
		PrintWriter out = ( response).getWriter();
		final String DB_URL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
		
		 //  数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "Qq4210831994.";
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;chartset=UTF-8");
    	try{
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行 SQL 查询
            Statement stmt = conn.createStatement();
          
            String sql;
            sql = "select * from test_data.user where username ='"+$username+"' and password='"+$password+"' ";
            System.out.println("sql:"+sql);
            //sql = "UPDATE article_table SET article_title='"+ $title +"' , article_content='"+$content+"' ,article_catalog="+catalog+" where article_id="+id +"";
            
           //执行查询返回结果集
           ResultSet rs = stmt.executeQuery(sql);
           System.out.println("rs:"+rs);
           JSONObject jsonObject = new JSONObject();
           if(rs.next()) {
        	   String username = rs.getString("username");
        	   jsonObject.put("username", username);
        	   String password = rs.getString("password");
        	   jsonObject.put("password", password);
        	   jsonObject.put("errorcode", "200");
        	   jsonObject.put("msg", "成功登录!");
        	   System.out.println("object:"+ jsonObject);
        	   out.append(jsonObject.toString());
           } else {
        	   out.println("<h5>用户名或密码不对！</h5>");
           }
           //int result = stmt.executeUpdate(sql);
           //if (result == 1) {
        	   //request.getRequestDispatcher("getAllArticle").forward(request, response);
        	//}
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
