<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/custom/contact/list.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="north" data-options="border:false,plain:true"
		split="false" style="overFlow: hidden; padding: 5px">
		<form id="attr_form" method="post">
			<table>
				<tr>
					<td>客户名称:</td>
					<td><input name="custName" class="easyui-validatebox"
						style="width: 150px" onkeydown="if(event.keyCode==13)return false;"/></td>
					<td>联系人名称:</td>
					<td><input name="name" class="easyui-validatebox"
						style="width: 150px" onkeydown="if(event.keyCode==13)return false;"/></td>
					
					<td><a style="width:70px;text-align:center;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchContact()">查询</a></td>
					<td><a style="width:70px;text-align:center;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-undo" onclick="clearForm()">重置</a></td>
					
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok',border:false">
		<table id="contact_datagrid" title="联系人列表">
		</table>
	</div>
	<div id="toolbar">
		<shiro:hasPermission name="contact:create">
			<a href="javascript:void(0)" id="btn_newContact" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newContact()">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="contact:update">
			<a href="javascript:void(0)" id="btn_editContact" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" disabled="true" onclick="editContact()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="contact:delete">
			<a href="javascript:void(0)" id="btn_destroyContact" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" disabled="true" onclick="destroyContact()">删除</a>
		</shiro:hasPermission>
	</div>

</body>
</html>