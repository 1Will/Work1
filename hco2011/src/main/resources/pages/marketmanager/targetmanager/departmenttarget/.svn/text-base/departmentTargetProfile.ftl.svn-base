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
<@htmlPage title="${action.getText('DeptTargetManagement.profile.title')}">
<@ww.form name="'listForm'" action="'saveDepartmentTargetAction'" method="'post'">
	<@ww.token name="savetargetManagementToken"/>
	<#if targetManagement.product?exists>
	<@ww.hidden id="productid" name="'product.id'" value="'${targetManagement.product.id?if_exists}'"/>	
	<#else>
	<@ww.hidden id="productid" name="'product.id'" value="''"/>
	</#if>
	<#if targetManagement.id?exists>
		<@ww.hidden name="'targetManagement.id'" value="#{targetManagement.id}"/>
	</#if>
	<@inputTable>
		<tr>
		
			<@textfield id="code" label="${action.getText('编号')}" maxlength="10"  name="targetManagement.code"  value="${targetManagement.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
			
			<@select  label="${action.getText('部门名称')}" 
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
			<@textfield id="targetName" label="${action.getText('目标名称')}" maxlength="20"  name="targetManagement.targetName"  value="${targetManagement.targetName?if_exists}"  required="true" anothername="checktargetName"/>
			</tr>
		<tr>
		<@textfield id="orderCount" label="${action.getText('订单数量')}" maxlength="10"  name="targetManagement.orderCount"  value="#{targetManagement.orderCount?if_exists}" />
		<@textfield id="orderMoney" label="${action.getText('订单金额')}" maxlength="10"  name="targetManagement.orderMoney"  value="#{targetManagement.orderMoney?if_exists}" />
		<@textfield id="sellReceivedPayments" label="${action.getText('销售回款')}" maxlength="10"  name="targetManagement.sellReceivedPayments"  value="#{targetManagement.sellReceivedPayments?if_exists}" />
		</tr>
		<tr>
		<@textfield id="loseRate" label="${action.getText('丢单比率')}" maxlength="10"  name="targetManagement.loseRate"  value="#{targetManagement.loseRate?if_exists}" />
		<@textfield id="visitCount" label="${action.getText('人均访问客户次数')}" maxlength="10"  name="targetManagement.visitCount"  value="#{targetManagement.visitCount?if_exists}" />
		<@textfield id="successRate" label="${action.getText('业务员行动计划完成率')}" maxlength="10"  name="targetManagement.successRate"  value="#{targetManagement.successRate?if_exists}" />
		</tr>
		<tr>
			<@textfield id="newCustomerNum" label="${action.getText('新客户开发数量')}" maxlength="10"  name="targetManagement.newCustomerNum"  value="#{targetManagement.newCustomerNum?if_exists}" />
			<@ww.textfield label="'${action.getText('地区')}'" name="'targetManagement.area'" value="'${targetManagement.area?if_exists}'" cssClass="'underline'" />
			<#--<@textfield id="product" label="${action.getText('产品')}" maxlength="3"  name="targetManagement.product"  value="${targetManagement.product?if_exists}"  required="true" anothername="checkproduct"/>
			-->
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
		</tr>	
		<tr>
			<@select  label="${action.getText('计划类型')}" 
							id="saveCompanyTargetAction_targetManagement.planType"
                           name="targetManagement.planType" 
                           value="${req.getParameter('targetManagement.planType')?if_exists}"
                           listKey="key" 
                           listValue="value"
                           list="planType" 
                           emptyOption="false" 
                           disabled="false" 
                           required="true" 
                           anothername="checkPlanType">
               </@select>
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
			</tr>
			<tr>
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
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/departmentTarget/listDepartmentTargetAction.html"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
		<#-- 提交验证-->
		function storeValidation(){
			<#-- if(!textfieldCheck_checkcode()){
				jgetObjByName("#code").focus();
				return false;
			}-->
			if(!selectCheck_checkdepartmentName()){
				jgetObjByName("#departmentName").focus();
				return false;
			}
			
			if(!textfieldCheck_checktargetName()){
				jgetObjByName("#targetName").focus();
				return false;
			}
			if(!isNumber("orderCount")){
				alert("订单数量是数字(整数)!");
				jgetObjByName("#orderCount").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#orderMoney").val())){
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
			if(!isNumber("visitCount")){
				alert("人均客户访问次数是数字(整数)!");
				jgetObjByName("#visitCount").focus();
				return false;
			}
			if(isNaN(jgetObjByName("#successRate").val())){
				alert("业务员行动计划完成率是数字!");
				jgetObjByName("#successRate").focus();
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
	String.prototype.Trim = function()
	{
	        return this.replace(/(^\s*)|(\s*$)/g, "");
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
	jgetObjByName(function(){
	
		assignValue();
		objDisabled();
		addEvent();
	});
</script>

</@htmlPage>
