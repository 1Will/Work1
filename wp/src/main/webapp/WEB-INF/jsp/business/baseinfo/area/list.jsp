<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/baseinfo/area/list.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="west" title="区域列表" split="true" style="width: 150px;"
		collapsible="true">
		<div id="area_tree"></div>
	</div>
	<!-- 功能区域end -->
	<!--中间内容begin-->
	<div region="center">
		<div id="datadic_center"></div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div id="area_add" iconCls="icon-add" onclick="areaAdd()">添加区域</div>
		<div id="area_delete" iconCls="icon-remove" onclick="areaDelete()">删除区域</div>
	</div>
</body>
</html>