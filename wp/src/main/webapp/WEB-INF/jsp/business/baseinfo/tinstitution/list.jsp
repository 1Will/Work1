<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/tinstitution/list.js"></script>
</head>
<body class="easyui-layout">
	<!-- 功能区域begin -->
	<div region="west" title="单位列表" split="true" style="width: 210px;"
		collapsible="true">
		<div id="organization_tree">
		</div>
	</div>
	<!-- 功能区域end -->
	<!--中间内容begin-->
	<div region="center">
		<div id="organization-center">
		</div>
	</div>
	<!--中间内容end-->
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div id="organization_add" iconCls="icon-add" onclick="tinstitutionAdd()">添加单位</div>
		<div id="organization_delete" iconCls="icon-remove" onclick="tinstitutionDelete()">删除单位</div>
	</div>
</body>
</html>