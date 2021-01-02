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
 * Servlet implementation class updateUser
 */
@WebServlet(description = "更新用户信息", urlPatterns = { "/updateUser" })
public class updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateUser() {
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
        response.setContentType("application/x-www-form-urlencoded;chartset=UTF-8");
        
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
		JSONObject pa=JSONObject.fromObject(str);
    	String username=pa.getString("username");
    	String role=pa.getString("role");
    	String password=pa.getString("password");
    	int id = pa.getInt("id");
    	System.out.println("username:"+username);
		
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
        	   request.getRequestDispatcher("getAllUser").forward(request, response);
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
