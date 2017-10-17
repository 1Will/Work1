<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/department/add.js"></script>

	<!--右边窗口begin-->
	<div region="center" border="false" style="padding: 5px;">
		<!--基础信息begin-->
		<form id="attr_form"  method="get">
			<table width="100%" border="0" cellspacing="0" cellpadding="5"
				class="gp_maintable">
				<tr>
					<td colspan="6">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${tdepartment.id}" />
					    <input type="text" name="department.id" value="${tdepartment.department.id}" />
					</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%" class="specalt"><div align="right">组织结构编号</div></th>
					<td width="25%"><div align="left">${tdepartment.id}</div></td>
					<th width="22%" class="specalt"><div align="right">上级机构编号</div></th>
					<td width="25%"><div align="left">${tdepartment.department.id}</div></td>
					<td width="3%">&nbsp;</td>
				</tr>

				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%"><div align="right">部门编码</div></th>
					<td width="25%"><div align="left">
							<input id="code" value="${tdepartment.code}" 
							required="required" size="30" class="easyui-validatebox"
								name="code" type="text"></input>
						</div></td>
					<th width="22%"><div align="right">部门名称</div></th>
					<td width="25%"><div align="left">
							<input id="name" value="${tdepartment.name}"
								class="easyui-validatebox" size="30" required="required"
								name="name" type="text"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				
			</table>
		</form>
		<!--基础信息end-->
	</div>
	<!--右边窗口begin-->
	<!--窗口底部begin-->
	<div region="south" border="false"
		style="text-align: right; height: 25%; padding: 2px;">
		<a class="easyui-linkbutton" id="addcoding_but" iconCls="icon-save"
			href="javascript:void(1)" onclick="saveOrUpdate1()" style="margin-right:20px;">保存</a>
	</div>
	<!--窗口底部end-->