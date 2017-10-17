<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/record/record/add.js"></script>
</head>
<body>
	<div style="padding: 5px;">
		<!--基础信息begin-->
		<form id="attr_form"  method="get">
			<table class="gp_maintable">
				<tr>
					<td colspan="6">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${record.id}" />
					    <input type="hidden" name="createdTime" value="${record.createdTime}" />
					    <input type="hidden" name="creator" value="${record.creator}" />
					</td>
				</tr>
				<tr>
					<th width="11%"><div align="right">合同名称</div></th>
					<td width="15%"><div align="left">
							<input  value="" 
							required="required" size="30" class="easyui-validatebox"
								 type="text"></input>
						</div></td>
					<th width="12%"><div align="right">客户名称</div></th>
					<td width="15%"><div align="left">
							<input value=""
								class="easyui-validatebox" size="30" required="required" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">联系人</div></th>
					<td width="15%"><div align="left">
							<input value=""
								class="easyui-validatebox" size="30" required="required" type="text"></input>
						</div></td>
					
				</tr>
				<tr>
					<th width="11%"><div align="right">发票编码</div></th>
					<td width="15%"><div align="left">
							<input id="code" value="${record.code}" size="30" class="easyui-validatebox"
								name="code" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">开票时间</div></th>
					<td width="15%"><div align="left">
							<input name="billingTime" class="easyui-datetimebox" value="${record.billingTime}" size="30"
								type="text"></input>
						</div></td>
					<th width="11%"><div align="right">金额</div></th>
					<td width="15%"><div align="left">
							<input id="jine" value="${record.jine}" size="30" class="easyui-validatebox"
								name="jine" type="text"></input>
						</div></td>
					
				</tr>
				<tr>
					<th width="11%"><div align="right">发票抬头</div></th>
					<td width="15%"><div align="left">
							<input id="invoiceTitle" value="${record.invoiceTitle}" size="30" class="easyui-validatebox"
								name="invoiceTitle" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">货币种类</div></th>
					<td width="15%" colspan="3"><div align="left">
							<input id="currency" value="${record.currency}"
								class="easyui-validatebox" name="currency" type="text" size="30"></input>
						</div></td>
				</tr>
				
				<tr>
					<th width="11%"><div align="right">发票内容</div></th>
					<td width="15%" colspan="5"><div align="left">
							<textarea required="required" id="content" name="content" cols="110" rows="2">${record.content}</textarea>
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
</body>
</html>