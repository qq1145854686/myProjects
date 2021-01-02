package com.pojo;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONObject;
public class jdbcUtils {
	private static final String url;
	private static final String user;
	private static final String pwd;
	private static java.sql.Connection conn = null;
	private java.sql.PreparedStatement preparedStatement = null;
    private static java.sql.Statement statement = null;
    private  ResultSet resultSet;
 
	static {
		url = "jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
		user = "root";
		pwd= "Qq4210831994.";
	}
	//注册jdbc方法
	public static void loadDriver () {
		  try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	//获得连接方法
	public static java.sql.Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 conn =  DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
     * 查询（无参）
     *
     * @param sql
     * @return
     */
    public ArrayList gainQuery(String sql) {
    		ArrayList list = new ArrayList();
        	resultSet = gainResultSet(sql);
            System.out.println("resultSet:"+resultSet);
       
        try {
            while ( resultSet.next()) {
            	JSONObject jsonObject = new JSONObject();
            	System.out.println("123");
                //rs.get+数据库中对应的类型+(数据库中对应的列别名)
                String username =  resultSet.getString("username");
                jsonObject.put("username", username);
                String password =  resultSet.getString("password");
                jsonObject.put("password", password);
                System.out.println("username:"+username);
                if (username == null && password == null) {
                    list.add("error");
                   
                } else {
                    list.add(jsonObject);
                }
            }
        } catch (SQLException e) {
            System.out.println("结果集解析出错!--" + e.getMessage());
            list.add("error");
        } finally {
        	//release();
        }
        return list;
    }
    /**
     * 获取结果集（无参）
     *
     * @param sql
     * @return
     */
    private ResultSet gainResultSet(String sql) {
        conn = jdbcUtils.getConnection();
        try {
        	java.sql.PreparedStatement pst =  conn.prepareStatement(sql,
        		     ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			resultSet= statement.executeQuery(sql);
            //statement = conn.createStatement();
          
          //获取结果集的列数
            System.out.println(resultSet.getMetaData().getColumnCount());
            //将结果集滚动到最后一个
            //resultSet.last();
            //获取结果集当前行数
            System.out.println(resultSet.getRow());
            System.out.println("获取结果集（无参）成功。");
        } catch (SQLException e) {
            System.out.println("获取结果集（无参）失败!--" + e.getMessage());
        }
        return resultSet;
    }
	//释放资源
	public static void release () {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
