<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: dTreeStyle.ftl 2010-02-04 zsz $ -->
<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('salaryItems.profile.title')}">
     <@ww.form action="'saveSalaryItems'" namespace="'/salaryItems'" method="'post'" >
       <@ww.token name="saveSalaryItemsToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <#if salaryItems.id?exists>
                <@ww.hidden name="'salaryItems.id'" value="#{salaryItems.id}"/>
         </#if>
         <@inputTable>
             <tr>
                <@textfield label="${action.getText('salaryItems.code')}" name="salaryItems.code" anothername="code" value="${salaryItems.code?if_exists}" required="false" disabled="true" cssClass="underline" maxlength="20"/>
                <@textfield label="${action.getText('salaryItems.name')}" name="salaryItems.name" value="${salaryItems.name?if_exists}"  required="true" anothername="name" maxlength="20"/>
                <@select 
					anothername="selectCheckInst"
	        		label="${action.getText('salaryItems.type')}" 
					name="type.id" 
					value="${req.getParameter('type.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allTypes"
					required="true"
					emptyOption="true" 
					disabled="false"
					>
				</@select>
             </tr>
			<tr>
		   	   <@select 
					anothername="selectCheckInst"
	        		label="${action.getText('salaryItems.order')}" 
					name="orders.id" 
					value="${req.getParameter('orders.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allOrders"
					required="true"
					emptyOption="true" 
					disabled="false"
					>
				</@select>
			</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('salaryItems.introduction')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="salaryItems.introduction" rows="4"  >${salaryItems.introduction?if_exists}</textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("salaryItems.introduction").cols =width;
			</script>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('salaryItems.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="salaryItems.remark" rows="4" >${salaryItems.remark?if_exists}</textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("salaryItems.remark").cols =width;
			</script>
		</tr>
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         </#if>
	        <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/salaryItems/listSalaryItems.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
         </@buttonBar>	
     </@ww.form>

<script>
  //验证各属性是否必填、格式
  function validate(){
		//项目名称
		if(getObjByName('salaryItems.name').value==''){
			alert("${action.getText('salaryItemsFile.name.requiredstring')}");
			getObjByName('salaryItems.name').focus();
			return false;
		}
		//类型
		if(getObjByName('type.id').value==''){
			alert("${action.getText('salaryItemsFile.type.requiredstring')}");
	        getObjByName('type.id').focus();
			return false;
		}
		//序号
		if(getObjByName('orders.id').value==''){
			alert("${action.getText('salaryItemsFile.order.requiredstring')}");
	        getObjByName('orders.id').focus();
			return false;
		}
    	return true;
  }
</script>

<script>
	
	window.onload=function(){
        //保存成功或者更新时保留下拉列表的值
		<#if salaryItems.type?exists>
			getObjByName('type.id').value=${salaryItems.type.id};
		</#if>
		<#if salaryItems.orders?exists>
			getObjByName('orders.id').value=${salaryItems.orders.id};
		</#if>
	 }
</script>
</@htmlPage>
