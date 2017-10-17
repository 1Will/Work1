<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/selectUserList.js"></script>

	<div region="north" data-options="border:false,plain:true"
		split="false" style=" padding: 5px">
		<form id="attr_form1" method="post">
			<table>
				<tr>
					<td>用户名:</td>
					<td><input name="username1" class="easyui-validatebox"
						style="width: 150px" onkeydown="if(event.keyCode==13)return false;"/></td>
					<td>姓名:</td>
					<td><input name="realName1" class="easyui-validatebox"
						style="width: 150px" onkeydown="if(event.keyCode==13)return false;"/></td>
					<td><a style="width:70px;text-align:center;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchUser()">查询</a></td>
					<td><a style="width:70px;text-align:center;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-undo" onclick="clearForm()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<table id="selectUser" title="用户列表">
		</table>
	</div>
