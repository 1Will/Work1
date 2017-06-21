<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试Json</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div>
<a href="javascript:changeWord();">链接1</a><br>
<textarea id="tex">这里即将被改变</textarea>
</div>

<div>
<a href="javascript:changeWord2();">链接2</a><br>
<textarea id="tex2">这里即将被改变</textarea>
</div>
</body>
<script type="text/javascript">
function changeWord(){
	var aJson = {"name":"张三","age":"21"};
	var json = eval(aJson);
	$('#tex').val(json.name);
}
function changeWord2(){
	var aJson ={"people":[{"name":"李四","age":"21"},{"name":"王五","age":"28"}],"dog":"狗" };
	var json = eval(aJson);
	$('#tex2').val(json.people[1].name);
}
</script>
</html>