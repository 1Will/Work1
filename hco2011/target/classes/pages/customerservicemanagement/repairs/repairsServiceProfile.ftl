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
<@htmlPage title="${action.getText('repairs.edit')}">
<@ww.form name="'listForm'" action="'saveRepairsAction'" method="'post'">
	<@ww.token name="saveRepairsActionToken"/>
	<#if repairs.id?exists>
		<@ww.hidden  id="repairs.id" name="'repairs.id'" value="'${repairs.id?if_exists}'"/>
	</#if>
	
	<#if repairs.customerInfo?exists>
		<@ww.hidden id="customerInfoid" name="'customerInfo.id'" value="'${repairs.customerInfo.id?if_exists}'"/>
	<#else>
		<@ww.hidden id="customerInfoid" name="'customerInfo.id'" value="''"/>
	</#if>
	
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('repairs.code')}" maxlength="3"  name="repairs.code"  value="${repairs.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('repairs.customerInfo')}:</label>
	     	</td>
	     	<td>
			<#if repairs.customerInfo?exists>
				<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="${repairs.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
			
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		
		<tr>
	     	<@pp.datePicker 
				label="'${action.getText('repairs.repairsTime')}'" 
				name="'repairs.repairsTime'" 
	   			value="'${(repairs.repairsTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<@textfield id="mobilePhone" label="${action.getText('repairs.mobilePhone')}" maxlength="30"  name="repairs.mobilePhone"  value="${repairs.mobilePhone?if_exists}"  required="false" anothername="checktelephone"/>
		</tr>
		<tr>
			<@textfield id="workPhone" label="${action.getText('repairs.workPhone')}" maxlength="30"  name="repairs.workPhone"  value="${repairs.workPhone?if_exists}"  required="false" anothername="checktelephone"/>
			<@ww.select label="'${action.getText('repairs.applyMode')}'" 
				id="applyMode"
				name="'applyMode.id'" 
				value="'${req.getParameter('repairs.applyMode.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allApplyMode"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>		
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('repairs.cause')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="repairs.cause" rows="6" cols="170" >${repairs.cause?if_exists}</textarea>
	        </td>
		</tr>
		<tr>	
		<@textfield id="servicePer" label="${action.getText('repairs.servicePer')}" maxlength="30"  name="repairs.servicePer"  value="${repairs.servicePer?if_exists}"  required="false" anothername="checkservicePer"/>
		<@pp.datePicker 
				label="'${action.getText('repairs.applyTime')}'" 
				name="'repairs.applyTime'" 
	   			value="'${(repairs.applyTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
		
		</tr>		
		<tr>
			<@pp.datePicker 
				label="'${action.getText('repairs.solveTime')}'" 
				name="'repairs.solveTime'" 
	   			value="'${(repairs.solveTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
		<@textfield id="repairsPer" label="${action.getText('repairs.repairsPer')}" maxlength="30"  name="repairs.repairsPer"  value="${repairs.repairsPer?if_exists}"  required="false" anothername="checkrepairsPer"/>
		</tr>		
		<tr>
			<@ww.select label="'${action.getText('repairs.callbackValidate')}'" 
				id="callbackValidate"
				name="'callbackValidate.id'" 
				value="'${req.getParameter('repairs.callbackValidate.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allCallbackValidate"
				emptyOption="true" 
				disabled="false">
			</@ww.select>		
			<@pp.datePicker 
				label="'${action.getText('repairs.callbackTime')}'" 
				name="'repairs.callbackTime'" 
	   			value="'${(repairs.callbackTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
		</tr>		
		<tr>
			<@ww.select label="'${action.getText('repairs.disposalState')}'" 
				id="disposalState"
				name="'disposalState.id'" 
				value="'${req.getParameter('repairs.disposalState.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allDisposalState"
				emptyOption="true" 
				disabled="false">
			</@ww.select>		
			
			<@pp.datePicker 
				label="'${action.getText('repairs.factSolveTime')}'" 
				name="'repairs.factSolveTime'" 
	   			value="'${(repairs.factSolveTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('repairs.detail')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="repairs.detail" rows="6" cols="170" >${repairs.detail?if_exists}</textarea>
	        </td>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('repairs.remark')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="repairs.remark" rows="6" cols="170" >${repairs.remark?if_exists}</textarea>
	        </td>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/repairs/listRepairsAction.html"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
		<#-- 提交验证-->
		function storeValidation(){
			if(jgetObjByName("#customerInfoid").val()==""){
					alert("请选择客户!");
					return false;
			}
		
			<#--if(!textfieldCheck_checkcode()){
				jgetObjByName("#code").focus();
				return false;
			}
			if(!textfieldCheck_checkserviceTitle()){
				jgetObjByName("#serviceTitle").focus();
				return false;
			}
			if(jgetObjByName("#repairsName").val()==""){
					alert("请选择客户!");
					jgetObjByName("#repairsName").focus();
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
			if(jgetObjByName("#repairs\\.startTime").val()==""){
					alert("请输入开始时间");
					jgetObjByName("#repairs\\.startTime").focus();
					return false;
			}
			
			if(isNaN(jgetObjByName("#costTime").val())){
				alert("花费时间是数字!");
				jgetObjByName("#costTime").focus();
				return false;
			}
			
			
			if(isNaN(jgetObjByName("#expense").val())){
				alert("费用是数字!");
				jgetObjByName("#expense").focus();
				return false;
			}
			if(!dateCheckPicker(true,'repairs.startTime','开始时间','%Y-%m-%d')){
				return false;
			}
			-->
		}
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
			jgetObjByName("#customerInfoid").val(result[0]);
			jgetObjByName("#customerInfoName").val(result[1]);
			
		}
	}
	
	jgetObjByName(function(){
		<#if repairs.applyMode?exists>
			jgetObjByName("#applyMode").val("${repairs.applyMode.id?if_exists}");
		</#if>
		<#if repairs.callbackValidate?exists>
			jgetObjByName("#callbackValidate").val("${repairs.callbackValidate.id?if_exists}");
		</#if>
		<#if repairs.disposalState?exists>
			jgetObjByName("#disposalState").val("${repairs.disposalState.id?if_exists}");
		</#if>
	});
</script>

</@htmlPage>
