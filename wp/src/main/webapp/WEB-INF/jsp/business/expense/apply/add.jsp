<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/expense/apply/add.js"></script>

	<div style="padding: 5px;">
		<!--基础信息begin-->
		<form id="attr_form"  method="get">
			<table class="gp_maintable">
				<tr>
					<td colspan="6">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${apply.id}" />
					    <input type="hidden" name="createdTime" value="${apply.createdTime}" />
					    <input type="hidden" name="creator" value="${apply.creator}" />
					</td>
				</tr>
				<tr>
					<th width="11%"><div align="right">费用编码</div></th>
					<td width="15%"><div align="left">
							<input id=code value="${apply.code}" 
							required="required" size="30" class="easyui-validatebox"
								name="code" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">费用名称</div></th>
					<td width="15%"><div align="left">
							<input id="expensename" value="${apply.expensename}"
								class="easyui-validatebox" size="30" required="required"
								name="expensename" type="text"></input>
						</div></td>
					<th width="10%"><div align="right" >申请人</div></th>
					<td width="15%"><div align="left">
							<input id="applyperson" value="${apply.applyperson}"
								class="easyui-validatebox" size="30" required="required"
								name="applyperson" type="text"></input>
						</div></td>
				</tr>
				<tr>
					<th width="11%"><div align="right">部门</div></th>
					<td width="15%"><div align="left">
							<input id="deparment" value="${apply.deparment}" size="30" class="easyui-validatebox"
								name="deparment" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">费用方式</div></th>
					<td width="15%"><div align="left">
							<input id="expensetype" value="${apply.expensetype}"
								class="easyui-validatebox" size="30" name="expensetype" type="text"></input>
						</div></td>
					<th width="10%"><div align="right" >申请金额</div></th>
					<td width="15%"><div align="left">
							<input id="applymoney" value="${apply.applymoney}"
								class="easyui-validatebox" size="30" name="applymoney" type="text"></input>
						</div></td>
				</tr>
				<tr>
					<th width="11%"><div align="right">申请时间</div></th>
					<td width="15%"><div align="left">
							<input name="applytime" class="easyui-datetimebox" value="${apply.applytime}" size="30"
								type="text"  required="required"></input>
						</div></td>
					<th width="12%"><div align="right">申请状态</div></th>
					<td width="15%"><div align="left">
							<input id="applystate" value="${apply.applystate}"
								class="easyui-validatebox" name="applystate" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">剩余金额</div></th>
					<td width="15%"><div align="left">
							<input id="residualmoney" value="${apply.residualmoney}"
								class="easyui-validatebox" size="30" name="residualmoney" type="text"></input>
						</div></td>
				</tr>
				
				<tr>
					<th width="11%"><div align="right">说明</div></th>
					<td width="15%" colspan="5"><div align="left">
							<textarea id="description" required="required" name="description" cols="110" rows="2">${apply.description}</textarea>
						</div></td>
				</tr>
			</table>
		</form>
		<!--基础信息end-->
		<div style="text-align: right; height: 10%; padding: 2px;region:south; border:false">
		<a class="easyui-linkbutton" id="addcoding_but" iconCls="icon-save"
			href="javascript:void(1)" onclick="saveOrUpdate()" style="margin-right:20px;">保存</a>
		</div>
	</div>

