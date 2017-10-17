<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/plan/plan/add.js"></script>
</head>
<body>
	<div style="padding: 5px;">
		<!--基础信息begin-->
		<form id="attr_form"  method="get">
			<table class="gp_maintable">
				<tr>
					<td colspan="6">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${plan.id}" />
					    <input type="hidden" name="createdTime" value="${plan.createdTime}" />
					    <input type="hidden" name="creator" value="${plan.creator}" />
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
					<td width="15%" colspan="3"><div align="left">
							<input value=""
								class="easyui-validatebox" size="30" required="required" type="text"></input>
						</div></td>
					
				</tr>
				<tr>
					<th width="11%"><div align="right">联系电话</div></th>
					<td width="15%"><div align="left">
							<input id="phone" value="${plan.phone}" size="30" class="easyui-validatebox"
								name="phone" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">计划日期</div></th>
					<td width="15%"><div align="left">
							<input name="planDate" class="easyui-datetimebox" value="${plan.planDate}" size="30"
								type="text"></input>
						</div></td>
					<th width="11%"><div align="right">付款方式</div></th>
					<td width="15%"><div align="left">
							<select required="required" id="payment" name="payment" style="width:140px;">
								<option>转账</option>
								<option>支票</option>
								<option>现金</option>
							</select>
						</div></td>
					
				</tr>
				<tr>
					<th width="11%"><div align="right">批次</div></th>
					<td width="15%"><div align="left">
							<select required="required" id="batch" name="batch" style="width:140px;">
								<option >1</option>
								<option >2</option>
								<option >3</option>
								<option >4</option>
								<option >5</option>
								<option >无批次</option>
							</select>
						</div></td>
					<th width="12%"><div align="right">应付金额</div></th>
					<td width="15%"><div align="left">
							<input id="yingfu" value="${plan.yingfu}"
								class="easyui-validatebox" name="yingfu" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">货币种类</div></th>
					<td width="15%"><div align="left">
							<input id="currency" value="${plan.currency}"
								class="easyui-validatebox" name="currency" type="text" size="30"></input>
						</div></td>
				</tr>
				<tr>
					<th width="11%"><div align="right">是否提醒</div></th>
					<td width="15%"><div align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="notOrNot" value="1">是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="notOrNot" value="0">否
						</div></td>
					<th width="12%"><div align="right">是否开发票</div></th>
					<td width="15%"><div align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="billingOrIs" value="1">是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="billingOrIs" value="0">否
						</div></td>
					<th width="10%"><div align="right">是否到款</div></th>
					<td width="15%"><div align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="isOrNot" value="1">是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="isOrNot" value="0">否
						</div></td>
				</tr>
				<tr>
					<th width="11%"><div align="right">实付金额</div></th>
					<td width="15%"><div align="left">
							<input id="factSum" value="${plan.factSum}"
								class="easyui-validatebox" name="factSum" type="text" size="30"></input>
						</div></td>
					<th width="12%"><div align="right">付款日期</div></th>
					<td width="40%" ><div align="left">
							<input name="paytime" class="easyui-datetimebox" value="${plan.paytime}" size="30"
								type="text"></input>
						</div></td>
					<th width="12%"><div align="right">收款人</div></th>
					<td width="40%" ><div align="left">
							<input id="payeeId" value="${plan.payeeId}"
								class="easyui-validatebox" name="payeeId" type="text" size="30"></input>
						</div></td>
					
				</tr>
				
				<tr>
					<th width="11%"><div align="right">备注</div></th>
					<td width="15%" colspan="5"><div align="left">
							<textarea required="required" id="remark" name="remark" cols="110" rows="2">${plan.remark}</textarea>
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