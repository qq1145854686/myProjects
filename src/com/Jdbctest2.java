package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

/**
 * Servlet implementation class Jdbctest2
 */
@WebServlet(description = "JDBC测试类", urlPatterns = { "/Jdbctest2" })
public class Jdbctest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Jdbctest2() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入jdbctest2的do.get()方法");
		PrintWriter out = null ;
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//创建一个driver实现类的对象
		Driver driver = null;
		try {
			driver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//准备连接数据库的基本信息
		String url = "jdbc:mysql:localhost:3306/test_data?serverTimezone=UTC&useSSL=false";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "123456");
		//调用Driver接口的connect(url,info)获取数据库连接
		java.sql.Connection conn = null;
		try {
			conn = driver.connect(url, info);
			System.out.println(conn);
			//if (conn == null) {
				//out.println("<h3>"+"无"+"</h3>");
			//} else {
				//out.println("<h3>"+conn+"</h3>");
			//}
			
		} catch (SQLException e) {
			System.out.println(e);
			out.println("<h5>"+e+"</h5>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conn);
		//out.println("<h3>"+conn+"</h3>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
