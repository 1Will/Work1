<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../common/head.jsp"%>
<%@include file="../../common/tag.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userList</title>
</head>
<body>
<form action="update.do" method="post" modelAttribute="user">
	<input type = "hidden" value="${user.id}" name="user.id">
	<table class="table table-bordered">
		<tr>
			<td>id</td>
			<td>${user.id}</td>
		</tr>
		<tr>
			<td>code</td>
			<td>${user.code}</td>
		</tr>
		<tr>
			<td>openId</td>
			<td>${user.openId}</td>
		</tr>
		<tr>
			<td>name</td>
			<td><input type = "text" class="form-control" value="${user.name}" name="name"></td>
		</tr>
		<tr>
			<td>loginName</td>
			<td><input type = "text" class="form-control" value="${user.loginName}" name="loginName"></td>
		</tr>
	</table>
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">修改</button>
		</div>
	</form>
</body>
</html>