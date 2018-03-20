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
	<!-- EL表达式 -->
	${jsonList}
	<hr>
	${list[0].name}
	<hr>
	<table class="table table-striped">
		<tr>
			<td>id</td>
			<td>name</td>
			<td>code</td>
			<td>loginName</td>
		</tr>

		<!-- jstl -->
		<c:forEach items="${list}" var="l">
			<tr>
				<td><a href='${l.id}/detail.do'>${l.id}</a></td>
				<td>${l.name}</td>
				<td>${l.code}</td>
				<td>${l.loginName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>