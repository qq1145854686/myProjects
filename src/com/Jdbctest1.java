package com;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class Jdbctest1 {
	//private static final String Connection conn  = null;

	/*
	 * JDBC测试类
	 */
	public static void main(String[] args) throws SQLException {
			//创建一个driver实现类的对象
			Driver driver = new com.mysql.jdbc.Driver();
			//准备连接数据库的基本信息
			String url = "jdbc:mysql:localhost:3306/testdata";
			Properties info = new Properties();
			info.put("user", "root");
			info.put("password", "Qq4210831994.");
			//调用Driver接口的connect(url,info)获取数据库连接
			java.sql.Connection conn = driver.connect(url, info);
			System.out.println(conn);
	}
}
