<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试上传1</title>
</head>
<body>
<!-- upload -->
	<form action="${pageContext.request.contextPath}/upload" method="post"
		enctype="multipart/form-data">
		用户名：<input type="text" name="username"> 文件：<input type="file"
			name="file1"><br /> <input type="submit" value="提交上传">
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
<!-- FlileUpload -->
	<form action="${pageContext.request.contextPath}/load" method="post"
		enctype="multipart/form-data">
		用户名：<input type="text" name="username"> 文件：<input type="file"
			name="file1"><br /> <input type="submit" value="提交上传">
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
<!-- FlileUpload -->


</body>
</html>