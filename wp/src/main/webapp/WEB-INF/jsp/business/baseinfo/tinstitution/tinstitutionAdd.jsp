<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/tinstitution/add.js"></script>

	<!--右边窗口begin-->
	<div region="center" border="false" style="padding: 5px;">
		<!--基础信息begin-->
		<form id="attr_form"  method="get">
			<table width="100%" border="0" cellspacing="0" cellpadding="5"
				class="gp_maintable">
				<tr>
					<td colspan="6">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${tinstitution.id}" />
					    <input type="hidden" name="sysTinstitution.id" value="${tinstitution.sysTinstitution.id}" />
					</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%" class="specalt"><div align="right">组织结构编号</div></th>
					<td width="25%"><div align="left">${tinstitution.id}</div></td>
					<th width="22%" class="specalt"><div align="right">上级机构编号</div></th>
					<td width="25%"><div align="left">${tinstitution.sysTinstitution.id}</div></td>
					<td width="3%">&nbsp;</td>
				</tr>

				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%"><div align="right">单位编码</div></th>
					<td width="25%"><div align="left">
							<input id="code" value="${tinstitution.code}" 
							required="required" size="30" class="easyui-validatebox"
								name="code" type="text"></input>
						</div></td>
					<th width="22%"><div align="right">单位名称</div></th>
					<td width="25%"><div align="left">
							<input id="name" value="${tinstitution.name}"
								class="easyui-validatebox" size="30" required="required"
								name="name" type="text"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%"><div align="right">法人代表</div></th>
					<td width="25%"><div align="left">
							<input id="legalperson" value="${tinstitution.legalperson}"
								class="easyui-validatebox" required="required" size="30"
								name="legalperson" type="text"></input>
						</div></td>
					<th width="22%"><div align="right">负责人</div></th>
					<td width="25%"><div align="left">
							<input id="lader" value="${tinstitution.lader}"
								class="easyui-validatebox" size="30" required="required"
								name="lader" type="text"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%"><div align="right">网址</div></th>
					<td width="25%"><div align="left">
							<input id="website" value="${tinstitution.website}"
								class="easyui-validatebox" size="30"
								name="website" type="text"></input>
						</div></td>
					<th width="22%"><div align="right">地址</div></th>
					<td width="25%"><div align="left">
							<input id="address" value="${tinstitution.lader}"
								class="easyui-validatebox" size="30" data-options="required:true"
								name="address" type="text"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%"><div align="right">邮箱</div></th>
					<td width="25%"><div align="left">
							<input id="email" value="${tinstitution.email}" data-options="required:false,validType:['email','length[0,20]'] "
								class="easyui-validatebox" size="30"
								name="email" type="text"></input>
						</div></td>
					<th width="22%"><div align="right">传真</div></th>
					<td width="25%"><div align="left">
							<input id="fax" value="${tinstitution.fax}"
								class="easyui-validatebox" size="30"
								name="fax" type="text"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<th><div align="right">创办时间</div></th>
					<td>
						 <div align="left">
							<input name="setuptime" class="easyui-datetimebox" value="${tinstitution.setuptime}" size="30"
								type="text"  required="required"></input>
						</div>
					</td>
					<th width="25%"><div align="right">联系电话</div></th>
					<td width="70%"><div align="left">
							<input name="tel" value="${tinstitution.tel}" size="30"
								type="text"></input>
						</div></td>
					<td>&nbsp;</td>
				</tr>

				
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="35%"><div align="right">简介</div></th>
					<td width="55%" colspan="3"><div align="left">
							<textarea name="comment1"  id="comment1"
								class="KindEditor" style="width: 750px; height: 100px;">${tinstitution.comment1}</textarea>
						</div></td>
					<td width="2%">&nbsp;</td>
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