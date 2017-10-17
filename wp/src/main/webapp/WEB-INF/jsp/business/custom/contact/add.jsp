<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/custom/contact/add.js"></script>

	<!--右边窗口begin-->
	<div region="center" border="false" style="padding: 5px;">
		<!--基础信息begin-->
		<form id="attr_form"  method="get">
			<table width="100%" border="0" cellspacing="0" cellpadding="5"
				class="gp_maintable">
				<tr>
					<td colspan="8">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${contact.id}" />
					    <input type="hidden" name="createdTime" value="${contact.createdTime}" />
					    <input type="hidden" name="creator" value="${contact.creator}" />
					</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">客户名称</div></th>
					<td width="15%"><div align="left">
							<input id="custName" value="${contact.custName}" 
							required="required" size="30" class="easyui-validatebox"
								name="custName" type="text"></input>
						</div></td>
					<th width="12%"><div align="right">联系人名称</div></th>
					<td width="15%"><div align="left">
							<input id="name" value="${contact.name}"
								class="easyui-validatebox" size="30" required="required"
								name="name" type="text"></input>
						</div></td>
					<th width="10%"><div align="right" >性别</div></th>
					<td width="15%"><div align="left">
							<%-- <input id="sex" value="${contact.sex}" required="required" 
								class="easyui-validatebox" size="30" name="sex" type="text"></input> --%>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sex" <c:if test="${contact.sex=='1'}">checked="checked"</c:if> value="1">男
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sex" <c:if test="${contact.sex=='0'}">checked="checked"</c:if> value="0">女
							
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">生日</div></th>
					<td width="15%"><div align="left">
							<input name="birthDate" class="easyui-datetimebox" value="${contact.birthDate}" size="30"
								type="text"  required="required"></input>
						</div></td>
					<th width="12%"><div align="right">兴趣</div></th>
					<td width="15%"><div align="left">
							<input id="interest" value="${contact.interest}"
								class="easyui-validatebox" name="interest" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">性格</div></th>
					<td width="15%"><div align="left">
							<input id="character" value="${contact.character}"
								class="easyui-validatebox" name="character" type="text" size="30"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">行业</div></th>
					<td width="15%"><div align="left">
							<input id="industry" value="${contact.industry}" 
							class="easyui-validatebox" name="industry" type="text" size="30"></input>
						</div></td>
					<th width="12%"><div align="right">部门</div></th>
					<td width="15%"><div align="left">
							<input id="dept" value="${contact.dept}"
								class="easyui-validatebox" name="dept" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">职位</div></th>
					<td width="15%"><div align="left">
							<input id="office" value="${contact.office}"
								class="easyui-validatebox" name="office" type="text" size="30"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">手机号码</div></th>
					<td width="15%"><div align="left">
							<input id="mobile" value="${contact.mobile}"  required="required" 
							 class="easyui-validatebox" name="mobile" type="text" size="30"></input>
						</div></td>
					<th width="12%"><div align="right">QQ</div></th>
					<td width="15%"><div align="left">
							<input id="qq" value="${contact.qq}"
								class="easyui-validatebox" name="qq" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">邮箱</div></th>
					<td width="15%"><div align="left">
							<input id="email" value="${contact.email}"
								class="easyui-validatebox" name="email" type="text" size="30"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">籍贯</div></th>
					<td width="15%"><div align="left">
							<input id="nativePlace" value="${contact.nativePlace}" 
							 class="easyui-validatebox" name="nativePlace" type="text" size="30"></input>
						</div></td>
					<th width="12%"><div align="right">学校</div></th>
					<td width="15%"><div align="left">
							<input id="school" value="${contact.school}"
								class="easyui-validatebox" name="school" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">专业</div></th>
					<td width="15%"><div align="left">
							<input id="profession" value="${contact.profession}"
								class="easyui-validatebox" name="profession" type="text" size="30"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">熟悉程度</div></th>
					<td width="15%"><div align="left">
							<%-- <input id="type" value="${contact.type}" 
							 	class="easyui-validatebox" name="type" type="text" size="30"></input> --%>
							 	<select id="cc" class="easyui-combobox" name="type" style="width:200px;">
								 	<c:forEach items="${list}" var="item">
								 		<option <c:if test="${item.codingname==contact.type}">selected="selected"</c:if>> ${item.cnname}</option>
								 	</c:forEach>
								 </select>
						</div></td>
					<th width="12%"><div align="right">信息完整度</div></th>
					<td width="15%"><div align="left">
							<input id="customerInfoIntegrity" value="${contact.customerInfoIntegrity}"
								class="easyui-validatebox" name="customerInfoIntegrity" type="text" size="30"></input>
						</div></td>
					<th width="10%"><div align="right">住址</div></th>
					<td width="15%"><div align="left">
							<input id="homeAddress" value="${contact.homeAddress}"
								class="easyui-validatebox" name="homeAddress" type="text" size="30"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">印象描述</div></th>
					<td width="80%" colspan="5"><div align="left">
							<textarea id="enterpriseSynopsis" name="enterpriseSynopsis" cols="110" rows="5">${contact.enterpriseSynopsis}</textarea>
						</div></td>
					
					<td width="3%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="11%"><div align="right">备注</div></th>
					<td width="80%" colspan="5"><div align="left">
						<textarea id="remarks" name="remarks" cols="110" rows="5">${contact.remarks}</textarea>
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
			href="javascript:void(1)" onclick="saveOrUpdate()" style="margin-right:20px;">保存</a>
	</div>
	<!--窗口底部end-->