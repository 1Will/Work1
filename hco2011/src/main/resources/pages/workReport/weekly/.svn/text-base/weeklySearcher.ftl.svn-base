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

<#include "../../includes/hco2011.ftl" />
  <@inputTable>
  	<@ww.hidden name="'isSuperSys'" value="'${req.getParameter('isSuperSys')?if_exists}'"/>
  	<tr>
		<@textfield label="${action.getText('weekly.code')}" name="code" value="${req.getParameter('code')?if_exists}" cssClass="underline"/>
		<@textfield label="${action.getText('weekly.name')}" name="name" value="${req.getParameter('name')?if_exists}" cssClass="underline"/>
		<#--<@ww.hidden label="'${action.getText('weekly.rapporteur')}'" name="'rapporteur'" value="'${req.getParameter('rapporteur')?if_exists}'" cssClass="'underline'"/>-->
		<#--<@ww.hidden  name="'rapporteur'" value="'${loginUser?if_exists}'" cssClass="'underline'"/>-->
		<@textfield label="${action.getText('weekly.rapporteur')}" name="rapporteur" value="${req.getParameter('rapporteur')?if_exists}" cssClass="underline" readonly="false" />
  	</tr>
  	<tr>
	<@select label="${action.getText('weekly.inst')}" 
       name="inst.id" 
       value="${req.getParameter('institution.id')?if_exists}"
       listKey="id" 
       listValue="name"
       list="allInsts" 
       emptyOption="false" 
       disabled="false"
       required="false"
       onchange="InstitutionSelectDeptDWR(\"inst.id\",\"dept.id\",\"${action.getText('select.option.all')}\",\"false\")">
   </@select>
   <@select label="${action.getText('weekly.dept')}" 
       name="dept.id" 
       value="${req.getParameter('dept.id')?if_exists}"
       listKey="id" 
       listValue="name"
       list="allDepts" 
       emptyOption="false" 
       disabled="false" 
       required="false"
       onchange="DutyCascadeDWR(\"dept.id\",\"duty.id\",#{user.organization.id?if_exists},\"${action.getText('select.option.all')}\",\"search\")">
   </@select>
   <@select label="${action.getText('weekly.duty')}" 
       name="duty.id"
       value="${req.getParameter('duty.id')?if_exists}"
       listKey="id" 
       listValue="name"
       list="allDutys" 
       emptyOption="false" 
       disabled="false" 
       required="false">
   </@select>
   	</tr>
  	<tr>
  	<@datePickerRanger
		anothername="startDate"
		label="${action.getText('weekly.startDate')}"
       	name="startDate"
 		value="${req.getParameter('startDate')?if_exists}" 
		cssClass="underline" 
		maxlength="10"  
		flag="true">
	</@datePickerRanger>
	<@datePickerRanger
		anothername="endDate"
		label="${action.getText('weekly.endDate')}"
       	name="endDate"
 		value="${req.getParameter('endDate')?if_exists}" 
		cssClass="underline" 
		maxlength="10"  
		flag="true">
	</@datePickerRanger>
	<@crm_onlySearchInvalid_checkBox/>
  	</tr>
  </@inputTable>
<SCRIPT>
	function checkInvalidParms() {
  
		if (-1 == getObjByName('inst.id').value) {
			getObjByName('inst.id').value = '';
	    }
	    if (-1 == getObjByName('dept.id').value) {
			getObjByName('dept.id').value = '';
	    }
	    if (-1 == getObjByName('duty.id').value) {
			getObjByName('duty.id').value = '';
	    }

    	return true;
	}
	window.onload=function(){
		//单位
    	<#if req.getParameter('inst.id')?exists>
    	        getObjByName('inst.id').value='${req.getParameter('inst.id')?if_exists}';
		    	//设置同步
		    	DWREngine.setAsync(false); 
		    	//回调单位的值后触发DWR 级联部门  
		    	InstitutionSelectDeptDWR("inst.id","dept.id",'${action.getText('select.option.all')}',"false");
		    	//重新设置为异步方式
		    	DWREngine.setAsync(true); 
	    </#if>
 	
		//部门
	     <#if req.getParameter('dept.id')?exists>
	    	 getObjByName('dept.id').value='${req.getParameter('dept.id')?if_exists}';
	    	 //设置同步
	    	 DWREngine.setAsync(false); 
	    	 //回调单位的值后触发DWR 级联职务 
			 DutyCascadeDWR('dept.id','duty.id',#{user.organization.id?if_exists},'${action.getText('select.option.all')}',"search")
	    	 //重新设置为异步方式
	    	 DWREngine.setAsync(true); 
	     </#if> 
	     
		 //职位
		<#if req.getParameter('duty.id')?exists>
			getObjByName('duty.id').value='${req.getParameter('duty.id')?if_exists}';
		</#if>
	}
</script>