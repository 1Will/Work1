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

<#-- $Id: employeeSearcher.ftl.ftl 2009-12-14 9:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@inputTable>
  	<@ww.hidden name="'xb'" value="'${req.getParameter('xb')?if_exists}'"/>
  	<tr>
  		<@ww.textfield label="'${action.getText('employee.no')}'" name="'employeeNo'" value="'${req.getParameter('employeeNo')?if_exists}'" cssClass="'underline'" />
  		<@ww.textfield label="'${action.getText('employee.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
  		<@ww.select label="'${action.getText('employee.sex')}'" 
			name="'employee.sex'" 
			value="'${req.getParameter('employee.sex')?if_exists}'"
			headerKey="id"
			headerValue="name"
			list="{
				'${action.getText('select.option.all')}',
				'${action.getText('employee.sex.man')}',
				'${action.getText('employee.sex.woman')}'
			}"
			emptyOption="false" 
			disabled="false" >
		</@ww.select>
  	</tr>
  	<tr>
  		<@ww.select 
    		label="'${action.getText('employee.inst')}'"
			name="'inst.id'" 
			value="'${req.getParameter('inst.id')?if_exists}'"
			listKey="id"
			listValue="name" 
			list="allInsts"
	    	emptyOption="false" 
	    	disabled="false"
	    	onchange="'InstitutionSelectDeptDWR(\"inst.id\",\"dept.id\",\"${action.getText('select.option.all')}\",\"true\")'">
	    </@ww.select>
	    <@ww.select 
    		label="'${action.getText('employee.dept')}'"
			name="'dept.id'" 
			value="'${req.getParameter('dept.id')?if_exists}'" 
			listKey="id"
			listValue="name" 
			list="allDepts"
	    	emptyOption="false" 
	    	disabled="false">
	    </@ww.select>
	    <@ww.select 
    		label="'${action.getText('employee.duty')}'"
			required="false"
			name="'duty'" 
			value="'${req.getParameter('duty')?if_exists}'" 
			listKey="id"
			listValue="name" 
			list="allDutys"
	    	emptyOption="false" 
	    	disabled="false"/>
  	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript"> 
	var sexSele=document.all("employee.sex");
    var sexGroups=sexSele.options.length;
    for(i=0;i<sexGroups;i++){
		<#if req.getParameter('employee.sex')?exists>
        if(sexSele.options[i].value=="${req.getParameter('employee.sex')?if_exists}"){
           sexSele.options[i].selected="true";
        }
		</#if>
    }
	var selector = document.all("inst.id");
	groups = selector.options.length;
  	for (i=0; i<groups; i++) {
    	<#if req.getParameter('inst.id')?exists>
    	if (selector.options[i].value == "${req.getParameter('inst.id')?if_exists}") {
      		selector.options[i].selected="true";
    	}
    	</#if>
  	}
	var selector = document.all("dept.id");
	groups = selector.options.length;
	for (i=0; i<groups; i++) {
		<#if req.getParameter('dept.id')?exists>
	    if (selector.options[i].value == "${req.getParameter('dept.id')?if_exists}") {
	      selector.options[i].selected="true";
	    }
		</#if>
 	}
 		 
	if(-1 != getObjByName('inst.id').value){
		//设置同步
	    DWREngine.setAsync(false); 
	    //回调单位的值后触发DWR 级联部门  
	    InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('select.option.all')}","false");
	    //重新设置为异步方式
	    DWREngine.setAsync(true);
	    if('' == "${req.getParameter('dept.id')?if_exists}"){
	    	getObjByName('dept.id').value = -1;
	    }else{
			getObjByName('dept.id').value = "${req.getParameter('dept.id')?if_exists}"; 
		}
	}else{
		 InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('select.option.all')}","false");
	}

	var selector = document.all("duty");
 		groups = selector.options.length;
		for (i=0; i<groups; i++) {
		  <#if req.getParameter('duty')?exists>
		  if (selector.options[i].value == "${req.getParameter('duty')?if_exists}") {
		      selector.options[i].selected="true";
		  }
		  </#if>
		}

  function checkInvalidParms() {
  
  	if (getObjByName('employee.sex').value=="${action.getText('select.option.all')}"){
		getObjByName('xb').value='';
	}else if(getObjByName('employee.sex').value=="${action.getText('employee.sex.man')}"){
		getObjByName('xb').value=true;
	}else if(getObjByName('employee.sex').value=="${action.getText('employee.sex.woman')}"){
		getObjByName('xb').value=false;
	}
	
  	if (getObjByName('inst.id').value == -1){
  		getObjByName('inst.id').value = "";
  	}
  	if(getObjByName('dept.id').value == -1){
  		getObjByName('dept.id').value = "";
  	}
  	if(getObjByName('duty').value == -1){
  		getObjByName('duty').value = '';
  	}
    return true;
  }
</script>
