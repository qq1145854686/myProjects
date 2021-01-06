//java代码用JavaBean技术封装登录方法
package com.pojo;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;

import com.db.db;

public class Login {
	//通过传参得方式获取表单的值
public Map<String, Integer> login(String name,String pwd) {
	Map<String, Integer> map = new HashMap<String, Integer>();
	db dbcon;
	dbcon = new db();
	int count=-1;
	int role ;
	int id;
	try{
	//从数据库中获取值并判断用户是否存在

	//查询数据库信息，判断用户是否存在
	String sql = "select * from user where username='" + name + "' and password ='" + pwd + "' ";
	//执行查询，返回结果集
	ResultSet rs = dbcon.executeQuery(sql);
	System.out.println("rs:"+rs);
	if(rs.next()) {//如果查到数据库sql，则跳转到登录成功界面
		count = rs.getInt(1);
		role = rs.getInt("userrole");
		id =rs.getInt("iduser");
		map.put("count", count);
		map.put("role", role);
		map.put("user_id", id);
		
		 }else{
			 count = rs.getInt(0); 
		 }
	}catch(Exception e){
		e.printStackTrace();
	}
	return map;
}
}



