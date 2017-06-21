<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<a href="testJson.jsp">跳转</a>
<a href="testAjax.jsp">测试ajax</a>
	<span>请选择：</span>
	<select id="select1" onchange='btnChange()'>
		<option></option>
		<option>a</option>
		<option>b</option>
		<option>c</option>
	</select>
	<span>请选择：</span>
	<select id="select2"></select>
	<!-- <input type="text" name="text1" id="text1" value="">
<input type="button" name="button1" id="button1" value="测试JS后台" onclick="button1()"> -->
	<div id="tt"></div>
	<script type="text/javascript">
		function btnChange() {
			var obj = document.getElementById("select1"); //定位id
			var index = obj.selectedIndex; // 选中索引
			var text = obj.options[index].text; // 选中文本
			var value = obj.options[index].value; // 选中值
			JsClick(value);
		}

		function JsClick(num) {
			//先构建一个请求的对象  
			var xmlhttp;
			if (window.XMLHttpRequest)//判定浏览器不同，所new的对象不同  
			{// code for IE7+, Firefox, Chrome, Opera, Safari  
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5  
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			//向服务器发出请求  
			xmlhttp.open("POST", "TestJS?id=" + num, true);
			xmlhttp.send(null);
			// 查看对象的状态函数  
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200)//readState为4且status为200表示服务器已返回  
				{
					document.getElementById("select2").innerHTML = xmlhttp.responseText;//返回后更改页面内容。  
				}
			}
		}
	</script>
</body>
</html>