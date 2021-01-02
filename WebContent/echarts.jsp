<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*,java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,net.sf.json.JSONObject" pageEncoding="utf-8"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统计图</title>
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/myFirstJavaProject/scripts/echarts.min.js"></script>
</head>
<body>
<%
		//注册JDBC驱动
		Class.forName("com.mysql.jdbc.Driver");
		//打开一个连接
		Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_data?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true","root","Qq4210831994.");
		String sqlQuery = "SELECT * FROM test_data.article_table";
		Statement stmt = conn.createStatement();
		ResultSet rs= stmt.executeQuery(sqlQuery);
		ArrayList list = new ArrayList();
		while(rs.next()){
			JSONObject jsonObject = new JSONObject();
			String title = rs.getString("article_title").toString();
			String content = rs.getString("article_content").toString();
			String catalog = rs.getString("article_catalog").toString();
			System.out.println(title);
			jsonObject.put("title", title);
			jsonObject.put("content", content);
			jsonObject.put("catalog", catalog);
			list.add(jsonObject);
		}
		request.setAttribute("list", list);
%>
<header>柱状图分析</header>
<hr>

<!-- 柱状图容器 -->
<div id="mychart" style="width: 60%; height: 400px; margin: 30px auto;"></div>
<hr>
<header>饼状图分析</header>
<script type="text/javascript">
	//请求后台数据接口
	$.ajax({
		url: "/myFirstJavaProject/getChartsData",
		type: 'GET',
		contentType: 'application/json',
		dataType:'json',
		success: function(data){
			console.log(data);
			var datax = [], datay = [];
			if (data && data.length) {
				for (var i=0; i<data.length; i++) {
					datax.push(data[i].title);
					datay.push(data[i].catalog);
				}
			}
			drawChart(datax, datay);
		}
	});
  /*
	*@params datax datay
	*@description 画统计图函数
	*/
	function drawChart(datax, datay) {
		var mychart = echarts.init(document.getElementById("mychart"));
		option = {
			    color: ['#3398DB'],
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
			            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: [
			        {
			            type: 'category',
			            //data: ['文章一', '文章二', '文章三', '文章四', '文章五', '文章六', '文章七'],
			            data: datax,
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value'
			        }
			    ],
			    series: [
			        {
			            name: '所属目录',
			            type: 'bar',
			            barWidth: '60%',
			            //data: ["目录一", "目录二", "目录三", "目录一", "目录二", "目录三", "目录二"]
			        	data: datay
			        }
			    ]
			};
		mychart.setOption(option);
	}
	
</script>
</body>
</html>