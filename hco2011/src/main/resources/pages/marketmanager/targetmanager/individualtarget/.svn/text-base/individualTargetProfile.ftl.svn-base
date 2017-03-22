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

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('person.targetManagement.profile')}">
<@ww.form name="'listForm'" action="'saveIndividualTargetAction'" method="'post'">
	<@ww.token name="saveIndividualTargetActionToken"/>
	<#if targetManagement.product?exists>
	<@ww.hidden id="productid" name="'product.id'" value="'${targetManagement.product.id?if_exists}'"/>	
	<#else>
	<@ww.hidden id="productid" name="'product.id'" value="''"/>
	</#if>
	<#if targetManagement.saleman?exists>
	<@ww.hidden id="salesmanid" name="'saleman.id'" value="'${targetManagement.saleman.id?if_exists}'"/>	
	<#else>
	<@ww.hidden id="salesmanid" name="'saleman.id'" value="''"/>
	</#if>
	<#if targetManagement.id?exists>
		<@ww.hidden name="'targetManagement.id'" value="#{targetManagement.id}"/>
	</#if>
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('编号')}" maxlength="10"  name="targetManagement.code"  value="${targetManagement.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
			<#--<@textfield id="lcmName" label="${action.getText('业务员名称')}" maxlength="3"  name="targetManagement.lcmName"  value="${targetManagement.lcmName?if_exists}"  required="true" anothername="checklcmName"/>
			-->
			<td align="right" valign="top">
	       		<label class="label"><font color="red">*</font>${action.getText('业务员')}:</label>
	     	</td>
	     	<td>
	     		<#if targetManagement.saleman?exists>
		   		<input type="text" id="salesmanName" name="salesmanName" class="underline"  value="${targetManagement.saleman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="salesmanName" name="salesmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@textfield id="targetName" label="${action.getText('目标名称')}" maxlength="20"  name="targetManagement.targetName"  value="${targetManagement.targetName?if_exists}"  required="true" anothername="checktargetName"/>
			</tr>
		<tr>
			<@select  label="${action.getText('所属部门')}" 
							id="departmentName"
                           name="targetManagement.departmentName" 
                           value="${req.getParameter('targetManagement.departmentName')?if_exists}"
                           listKey="top" 
                           listValue="top"
                           list="department" 
                           emptyOption="false" 
                           disabled="false" 
                           required="true" 
                           anothername="checkdepartmentName">
            </@select>
            <@textfield id="customerCount" label="${action.getText('联系客户数量')}" maxlength="10"  name="targetManagement.customerCount"  value="#{targetManagement.customerCount?if_exists}" />
			<@textfield id="visitCount" label="${action.getText('访问次数')}" maxlength="10"  name="targetManagement.visitCount"  value="#{targetManagement.visitCount?if_exists}" />
		</tr>
		<@textfield id="newCustomerNum" label="${action.getText('新客户开发数量')}" maxlength="10"  name="targetManagement.newCustomerNum"  value="#{targetManagement.newCustomerNum?if_exists}" />
		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('产品')}:</label>
	     	</td>
	     	<td>
	     		<#if targetManagement.product?exists>
		   		<input type="text" id="productName" name="productName" class="underline"  value="${targetManagement.product.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="productName" name="cproductName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td> 
		<@select  label="${action.getText('计划类型')}" 
							id="saveCompanyTargetAction_targetManagement.planType"
                           name="targetManagement.planType" 
                           value="${req.getParameter('targetManagement.planType')?if_exists}"
                           listKey="key" 
                           listValue="value"
                           list="planType" 
                           emptyOption="true" 
                           disabled="false" 
                           required="true" 
                           anothername="checkPlanType">
               </@select>
		<tr>
		</tr>
		<tr>
			<@textfield id="year" label="${action.getText('年份')}" maxlength="4"  name="targetManagement.year"  value="#{targetManagement.year?if_exists}"  required="true" anothername="checkyear"/>
			<@select  label="${action.getText('季度')}" 
							id="saveCompanyTargetAction_targetManagement.quarter"
                           name="targetManagement.quarter" 
                           value="${req.getParameter('targetManagement.quarter')?if_exists}"
                           listKey="key" 
                           listValue="value"
                           list="quarterMap" 
                           emptyOption="false" 
                           disabled="false" >
               </@select>
               <@select  label="${action.getText('月份')}" 
							id="saveCompanyTargetAction_targetManagement.month"
                           name="targetManagement.month" 
                           value="${req.getParameter('targetManagement.month')?if_exists}"
                           listKey="top" 
                           listValue="top"
                           list="month" 
                           emptyOption="false" 
                           disabled="false" >
               </@select>
			</tr>
			<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('备注')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="targetManagement.remark" rows="6" cols="170" >${targetManagement.remark?if_exists}</textarea>
	        </td>
			</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/individualTarget/listIndividualTargetAction.html"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
	String.prototype.Trim = function()
	{
	        return this.replace(/(^\s*)|(\s*$)/g, "");
	}
		<#-- 提交验证-->
		function storeValidation(){
			<#-- if(!textfieldCheck_checkcode()){
				jgetObjByName("#code").focus();
				return false;
			}-->
			
			if(jgetObjByName("#salesmanName").val()==""){
				alert("请选择业务员!");
				jgetObjByName("#salesmanName").focus();
				return false;
			}
			if(!textfieldCheck_checktargetName()){
				jgetObjByName("#targetName").focus();
				return false;
			}
			if(!selectCheck_checkdepartmentName()){
				jgetObjByName("#departmentName").focus();
				return false;
			}
			if(!isNumber("customerCount")){
				alert("联系客户数量是数字(整数)!");
				jgetObjByName("#customerCount").focus();
				return false;
			}
			
			if(!isNumber("visitCount")){
				alert("访问次数是数字(整数)!");
				jgetObjByName("#visitCount").focus();
				return false;
			}
			
			
			if(!isNumber("newCustomerNum")){
				alert("新客户开发数量是数字(整数)!");
				jgetObjByName("#newCustomerNum").focus();
				return false;
			}
			if(jgetObjByName("#productName").val()==""){
				alert("请选择产品!");
				jgetObjByName("#productName").focus();
				return false;
			}
			if(!selectCheck_checkPlanType()){
				jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").focus();
				return false;
			}
			if(!textfieldCheck_checkyear()){
				jgetObjByName("#year").focus();
				return false;
			}
			if(!isNumber("year")){
				alert("年份是数字(整数)!");
				jgetObjByName("#year").focus();
				return false;
			}
			return true;
			
		}
	
		<#-- 注册事件-->
	function addEvent(){
		jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").change(function(){
			objDisabled();
		});
	}
	<#-- 计划类型，季度，月份的关系显示-->
	function objDisabled(){
		if(jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").val() == 1){
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.quarter").attr("disabled",true);
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.month").attr("disabled",true);
		}
		if(jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").val() == 2){
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.quarter").attr("disabled",false);
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.month").attr("disabled",true);
		}
		if(jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").val() == 3){
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.quarter").attr("disabled",true);
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.month").attr("disabled",false);
		}
	}
	<#-- 赋值-->
	function assignValue(){
		<#if targetManagement.planType?exists>
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.planType").val("#{targetManagement.planType?if_exists}");
		</#if>
		<#if targetManagement.quarter?exists>
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.quarter").val("#{targetManagement.quarter?if_exists}");
		</#if>
		<#if targetManagement.month?exists>
			jgetObjByName("#saveCompanyTargetAction_targetManagement\\.month").val("#{targetManagement.month?if_exists}");
		</#if>
		<#if targetManagement.departmentName?exists>
			var departmentName = "${targetManagement.departmentName}";
			getObjByName("targetManagement.departmentName").value=departmentName.Trim();
		</#if>
	}
	//弹出产品查询模态窗体
	function products_OpenDialog(){
	   var url = "${req.contextPath}/com/listProductsWindow.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			jgetObjByName("#productid").val(result[0]);
			jgetObjByName("#productName").val(result[2]);
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
			jgetObjByName("#salesmanid").val(result[0]);
			jgetObjByName("#salesmanName").val(result[2]);
		}
	}
	jgetObjByName(function(){
		assignValue();
		objDisabled();
		addEvent();
	});
</script>

</@htmlPage>
