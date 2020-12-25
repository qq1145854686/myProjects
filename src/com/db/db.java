//java代码db类实现数据库连接，一般每次页面需要连接数据库都创建此类对象
package com.db;
import java.sql.*;
public class db {
	private  Connection dbConn; 
	 private Statement stateMent; 
	 public db() {
		 //jdbc名称
		 String driverName= "com.mysql.jdbc.Driver"; 
		 //数据库URL
		 String dbURL="jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true"; 
		 String userName="root";//默认用户
		 String userPwd="Qq4210831994."; 
		 try {
			 //注册数据库驱动
			 Class.forName(driverName); 
			 //获取数据库连接
			 dbConn=DriverManager.getConnection(dbURL,userName, userPwd);
			 System.out.println("Connection Successful!"); 
			 }catch (Exception e) { 
				 System.out.println(e.getMessage()); 
				 }
		 }
		 public int executeUpdate(String sql) throws SQLException{
			 stateMent=dbConn.createStatement();
			 return stateMent.executeUpdate(sql);
		 }
	    public ResultSet executeQuery(String sql) throws SQLException{
	    	stateMent = dbConn.createStatement();
	    			return stateMent.executeQuery(sql);
	    }
		public void closeConn() throws SQLException{
			stateMent.close();
			dbConn.close();
		}
		public static void main(String[] args) {
			new db();
		}
		public PreparedStatement PreparedStatement(String sql) throws SQLException{
			return dbConn.prepareStatement(sql);
		}
}
