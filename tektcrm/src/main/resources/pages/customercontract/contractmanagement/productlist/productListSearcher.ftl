<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: customerInfoSearcher.ftl 2009-12-10 15:50:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@inputTable>
	<@ww.hidden name="'contractManagement.id'" value="${req.getParameter('contractManagement.id')?if_exists}"/>
	<tr>
		<@ww.textfield label="'${action.getText('合同编码')}'" name="'contractManagement.code'" value="'${req.getParameter('contractManagement.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('合同名称')}'" name="'contractManagement.contractName'" value="'${req.getParameter('contractManagement.contractName')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('客户名称')}'" name="'contractManagement.customerInfo.name'" value="'${req.getParameter('contractManagement.customerInfo.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('联系人')}'" name="'contractManagement.linkman.name'" value="'${req.getParameter('contractManagement.linkman.name')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('部门')}'" name="'contractManagement.deparment.name'" value="'${req.getParameter('contractManagement.deparment.name')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('负责人')}'" name="'contractManagement.saleman.name'" value="'${req.getParameter('contractManagement.saleman.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.select label="'${action.getText('合同状态')}'" 
				id="state"
				name="'contractManagement.state.id'" 
				value="'${req.getParameter('contractManagement.state.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<script language="javascript">
				<#if req.getParameter('contractManagement.state.id')?exists>
					getObjByName('contractManagement.state.id').value = '${req.getParameter('contractManagement.state.id')?if_exists}';
				</#if>
			</script>
		<@ww.textfield label="'${action.getText('产品类型')}'" name="'product.mode'" value="'${req.getParameter('product.mode')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('产品名称')}'" name="'product.name'" value="'${req.getParameter('product.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>	
		<@pp.dateRanger label="'${action.getText('签订日期')}'" 
 				name="'contractManagement.ciemdinghTime'" 
			    value="'${req.getParameter('contractManagement.ciemdinghTime_start')?if_exists}|${req.getParameter('contractManagement.ciemdinghTime_end')?if_exists}'"
			    readonly="true"
			    cssClass="'underline'" 
			    maxlength="10"/>
	</tr>
</@inputTable>
