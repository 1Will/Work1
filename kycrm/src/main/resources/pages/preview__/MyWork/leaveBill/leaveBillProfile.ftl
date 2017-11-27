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

<#-- $Id: ceProfile.ftl 2009-11-27 14:49:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@htmlPage title="请假/调休管理维护">
<@ww.form name="'listForm'" action="'editLeaveBill_'" method="'post'">
	<@ww.token name="saveCEInfoToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'编号'" name="'code'" value="''" cssClass="'underline'" required="true"/>
			<@pp.datePicker 
				label="'创建日期'" 
				name="'createTime'" 
	   			value="''"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"/>
			<@ww.textfield label="'申请人'" name="'applyPerson'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'部门'"
				required="true"
				name="'customerType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
					'研发部',
					'销售部',
					'管理部'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			 <@pp.datePicker 
				label="'开始时间'" 
				name="'startTime'" 
	   			value="''"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"/>
			<@pp.datePicker 
				label="'结束时间'" 
				name="'endTime'" 
	   			value="''"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"/>
		</tr>
		<tr>
		 <@ww.textfield label="'时长'" name="'approvalPerson'" value="''" cssClass="'underline'"/>
			<@ww.select 
	    		label="'状态'"
				required="true"
				name="'state'" 
				value="state" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
					'新建',
					'通过',
					'未通过'
					}"
			    emptyOption="true" 
			    disabled="false"/>
			 <@ww.select 
	    		label="'类型'"
				required="true"
				name="'state'" 
				value="state" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
					'请假',
					'调休'
					}"
			    emptyOption="true" 
			    disabled="false"/>
			    
		</tr>
		<tr>
			 <@ww.textfield label="'负责人'" name="'approvalPerson'" value="''" cssClass="'underline'"/>
			<@pp.datePicker 
				label="'审批时间'" 
				name="'approvalTime'" 
	   			value="''"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"/>
			    
			
		</tr>
		<tr>
			<@ww.textarea label="'事由'" name="'incident'" value="''" rows="'3'"/>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("incident").cols =width;
			</script>
		</tr>
		<tr>
			<@ww.textarea label="'未通过原因'" name="'failReason'" value="''" rows="'3'" />
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("failReason").cols =width;
			</script>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/leaveBill_/listLeaveBill_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>