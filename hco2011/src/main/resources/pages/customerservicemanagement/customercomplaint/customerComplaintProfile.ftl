<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: customerInfoProfile.ftl 2009-12-14 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('客户投诉维护页面')}">
<@ww.form name="'listForm'" action="'saveCustomerComplaintAction'" method="'post'">
	<@ww.token name="saveCustomerComplaintActionToken"/>
	<#if customerComplaint.customerInfo?exists>
		<@ww.hidden  id="customerInfo.id" name="'customerInfo.id'" value="'${customerComplaint.customerInfo.id?if_exists}'"/>
	<#else>
		<@ww.hidden  id="customerInfo.id" name="'customerInfo.id'" value="''"/>
	</#if>
	<#if customerComplaint.salesman?exists>
		<@ww.hidden id="salesman.id" name="'salesman.id'" value="'${customerComplaint.salesman.id?if_exists}'"/>
	<#else>
		<@ww.hidden id="salesman.id" name="'salesman.id'" value="''"/>
	</#if>
	<#if customerComplaint.linkman?exists>
		<@ww.hidden id="linkman.id" name="'linkman.id'" value="'${customerComplaint.linkman.id?if_exists}'"/>
	<#else>
		<@ww.hidden id="linkman.id" name="'linkman.id'" value="''"/>
	</#if>
	
	
	<#if customerComplaint.id?exists>
		<@ww.hidden name="'customerComplaint.id'" value="#{customerComplaint.id}"/>
	</#if>
	<@inputTable>
		<tr>
		<@textfield id="code" label="${action.getText('编号')}" maxlength="3"  name="customerComplaint.code"  value="${customerComplaint.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
		<@textfield id="complaintTitle" label="${action.getText('投诉主题')}" maxlength="3"  name="customerComplaint.complaintTitle"  value="${customerComplaint.complaintTitle?if_exists}"  required="true" anothername="checkcomplaintTitle"/>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('客户')}:</label>
	     	</td>
	     	<td>
			<#if customerComplaint.customerInfo?exists>
				<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="${customerComplaint.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
			
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('服务人员')}:</label>
	     	</td>
	     	<td>
	     	<#if customerComplaint.salesman?exists>
				<input type="text" id="salesmanName" name="salesmanName" class="underline"  value="${customerComplaint.salesman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="salesmanName" name="salesmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('联系人')}:</label>
	     	</td>
	     	<td>
	     		<#if customerComplaint.linkman?exists>
		   		<input type="text" id="linkmanName" name="linkmanName" class="underline"  value="${customerComplaint.linkman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="linkmanName"  name="linkmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="linkman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@textfield id="code" label="${action.getText('电话')}" maxlength="3"  name="customerComplaint.telephone"  value="${customerComplaint.telephone?if_exists}"  required="false" anothername="checktelephone"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('投诉类型')}'" 
				id="complaintType"
				name="'complaintType.id'" 
				value="'${req.getParameter('customerComplaint.complaintType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allComplaintType"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('紧急程度')}'" 
				id="urgencyDegree"
				name="'urgencyDegree.id'" 
				value="'${req.getParameter('customerComplaint.urgencyDegree.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allUrgencyDegree"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<#--<@textfield id="serviceType" label="${action.getText('投诉类型')}" maxlength="3"  name="customerComplaint.complaintType"  value="${customerComplaint.complaintType?if_exists}"  required="false" anothername="checkcomplaintType"/>
				<@textfield id="serviceWay" label="${action.getText('紧急程度')}" maxlength="3"  name="customerComplaint.urgencyDegree"  value="${customerComplaint.urgencyDegree?if_exists}"  required="false" anothername="checkurgencyDegree"/>
			--><@textfield id="costTime" label="${action.getText('花费时间')}" maxlength="3"  name="customerComplaint.costTime"  value="${customerComplaint.costTime?if_exists}"  required="false" anothername="checkcostTime"/>
		</tr>		
		<tr>
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'customerComplaint.startTime'" 
	   			value="'${(customerComplaint.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<@ww.select label="'${action.getText('处理状态')}'" 
				id="disposeState"
				name="'disposeState.id'" 
				value="'${req.getParameter('customerComplaint.disposeState.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allDisposeState"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('处理方式')}'" 
				id="disposeWay"
				name="'disposeWay.id'" 
				value="'${req.getParameter('customerComplaint.disposeWay.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allDisposeWay"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<#--<@textfield id="code" label="${action.getText('处理状态')}" maxlength="3"  name="customerComplaint.disposeState"  value="${customerComplaint.disposeState?if_exists}"  required="false" anothername="checkdisposeState"/>
		<@textfield id="code" label="${action.getText('处理方式')}" maxlength="3"  name="customerComplaint.disposeWay"  value="${customerComplaint.disposeWay?if_exists}"  required="false" anothername="checkdisposeWay"/>
		-->
		</tr>
		<tr>
		<#--<@textfield id="code" label="${action.getText('回访确认')}" maxlength="3"  name="customerComplaint.backValidate"  value="${customerComplaint.backValidate?if_exists}"  required="false" anothername="checkbackValidate"/>
		--><@ww.select label="'${action.getText('回访确认')}'" 
				id="backValidate"
				name="'backValidate.id'" 
				value="'${req.getParameter('customerComplaint.backValidate.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allBackValidate"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('投诉内容')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerComplaint.complaintContent" rows="4" cols="150" >${customerComplaint.complaintContent?if_exists}</textarea>
	        </td>
		</tr>
		
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('处理结果')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerComplaint.disposeContent" rows="4" cols="150" >${customerComplaint.disposeContent?if_exists}</textarea>
	        </td>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('客户反馈')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerComplaint.customerFeedback" rows="4" cols="150" >${customerComplaint.customerFeedback?if_exists}</textarea>
	        </td>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('备注')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerComplaint.remark" rows="4" cols="150" >${customerComplaint.remark?if_exists}</textarea>
	        </td>
		</tr>
		
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/customerComplaint/listCustomerComplaintAction.html"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
		<#-- 提交验证-->
		function storeValidation(){
			<#--if(!textfieldCheck_checkcode()){
				jgetObjByName("#code").focus();
				return false;
			}-->
			if(!textfieldCheck_checkcomplaintTitle()){
				jgetObjByName("#complaintTitle").focus();
				return false;
			}
			if(jgetObjByName("#customerInfoName").val()==""){
					alert("请选择客户!");
					jgetObjByName("#customerInfoName").focus();
					return false;
			}
			if(jgetObjByName("#salesmanName").val()==""){
					alert("请选择服务人员!");
					jgetObjByName("#salesmanName").focus();
					return false;
			}
			if(jgetObjByName("#linkmanName").val()==""){
					alert("请选择联系人!");
					jgetObjByName("#linkmanName").focus();
					return false;
			}
			<#--if(jgetObjByName("#customerComplaint\\.startTime").val()==""){
					alert("请输入开始时间");
					jgetObjByName("#customerComplaint\\.startTime").focus();
					return false;
			}-->
			if(!dateCheckPicker(true,'customerComplaint.startTime','开始时间','%Y-%m-%d')){
				return false;
			}
			<#--if(isNaN(jgetObjByName("#orderMoney").val())){
				alert("订单金额是数字!");
				jgetObjByName("#orderMoney").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#sellReceivedPayments").val())){
				alert("销售回款是数字!");
				jgetObjByName("#sellReceivedPayments").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#loseRate").val())){
				alert("丢单比率是数字!");
				jgetObjByName("#loseRate").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#visitCount").val())){
				alert("人均客户访问次数是数字!");
				jgetObjByName("#visitCount").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#successRate").val())){
				alert("业务员行动计划完成率是数字!");
				jgetObjByName("#successRate").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#newCustomerNum").val())){
				alert("新客户开发数量是数字!");
				jgetObjByName("#newCustomerNum").focus();
				return false;
			}
			if(!textfieldCheck_checkproduct()){
				jgetObjByName("#product").focus();
				return false;
			}
			if(!selectCheck_checkPlanType()){
				jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").focus();
				return false;
			}
			if(!textfieldCheck_checkyear()){
				jgetObjByName("#year").focus();
				return false;
			}-->
			
		}
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
			jgetObjByName("#customerInfo\\.id").val(result[0]);
			jgetObjByName("#customerInfoName").val(result[1]);
			
		}
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			jgetObjByName("#salesman\\.id").val(result[0]);
			jgetObjByName("#salesmanName").val(result[2]);
		}
	}
	
	//联系人查询模态窗体
	function linkman_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			jgetObjByName("#linkman\\.id").val(result[0]);
			jgetObjByName("#linkmanName").val(result[2]);
		}
	}
	
	jgetObjByName(function(){
		<#if customerComplaint.complaintType?exists>
			jgetObjByName("#complaintType").val("${customerComplaint.complaintType.id?if_exists}");
		</#if>
		
		<#if customerComplaint.urgencyDegree?exists>
			jgetObjByName("#urgencyDegree").val("${customerComplaint.urgencyDegree.id?if_exists}");
		</#if>
		<#if customerComplaint.disposeState?exists>
			jgetObjByName("#disposeState").val("${customerComplaint.disposeState.id?if_exists}");
		</#if>
		<#if customerComplaint.disposeWay?exists>
			jgetObjByName("#disposeWay").val("${customerComplaint.disposeWay.id?if_exists}");
		</#if>
		<#if customerComplaint.backValidate?exists>
			jgetObjByName("#backValidate").val("${customerComplaint.backValidate.id?if_exists}");
		</#if>
			
	
	});
</script>

</@htmlPage>
