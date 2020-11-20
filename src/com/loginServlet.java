package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  //servlet生命周期，的三个方法，1.被创建，执行且只执行一次init方法，2.提供服务，执行service方法，执行多次 3.被销毁，当Servlet服务器正常关闭时，执行destroy方法，只执行一次。
    
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
    	System.out.println("进入severlet的init()方法");
        super.init();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	System.out.println("进入到service()");
        super.service(req, resp);
    }
    
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    	System.out.println("进入到destroy()方法");
        super.destroy();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入到doGet()方法");
		//设置响应内容类型
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 
		request.setCharacterEncoding("utf-8");//���ù��ʱ���utf-8����һ���ǽ��ղ����ģ������ı����ʽ��utf-8
		response.setHeader("Content-type", "text/html;charset=UTF-8"); // ���ص�ʱ��Ҳ��Ҫ����utf-8
		String username1 = request.getParameter("username");
		
		Enumeration<String> names = request.getParameterNames();
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			System.out.print(cookies[i].getName()+cookies[i].getValue()+";");
		}
		System.out.println("cookies:"+cookies);
		Cookie username = new Cookie("username", username1);
		Cookie password = new Cookie("password",  request.getParameter("pwd"));
		username.setMaxAge(60*60*24);
		password.setMaxAge(60*60*24);
		response.addCookie(username);
		response.addCookie(password);
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			System.out.println(name);
			String[] values = request.getParameterValues(name);
			if(values.length == 1) {
				System.out.println(values[0]);
			} else {
				for(int i=0; i<values.length; i++) {
					System.out.println(values[i]);
				}
			}
		}
		System.out.println(names);
		System.out.println("username:"+username1);//����̨��ӡֵ	
		response.getWriter().append("Welcome"+username1+"!");//��ǰ�˷�����Ϣ
		
		 // 设置错误代码和原因
	     //response.sendError(404, "找不到请求资源" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
