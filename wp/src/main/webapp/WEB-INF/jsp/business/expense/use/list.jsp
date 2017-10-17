<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/expense/use/list.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="north" data-options="border:false,plain:true"
		split="false" style="overFlow: hidden; padding: 5px">
		<form id="attr_form" method="post">
			<table>
				<tr>
					<td>申请人:</td>
					<td><input name="usePerson" class="easyui-validatebox"
						style="width: 150px" onkeydown="if(event.keyCode==13)return false;"/></td>
					
					<td><a style="width:70px;text-align:center;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchUse()">查询</a></td>
					<td><a style="width:70px;text-align:center;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-undo" onclick="clearForm()">重置</a></td>
					
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok',border:false">
		<table id="info_datagrid" title="费用使用列表">
		</table>
	</div>
	<div id="toolbar">
		<shiro:hasPermission name="use:create">
			<a href="javascript:void(0)" id="btn_newInfo" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newUse()">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="use:update">
			<a href="javascript:void(0)" id="btn_editInfo" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" disabled="true" onclick="editUse()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="use:delete">
			<a href="javascript:void(0)" id="btn_destroyInfo" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" disabled="true" onclick="destroyUse()">删除</a>
		</shiro:hasPermission>
	</div>

</body>
</html>