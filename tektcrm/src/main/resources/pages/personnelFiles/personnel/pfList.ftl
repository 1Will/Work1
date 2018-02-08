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

<#--$Id: pfList.ftl 2010-02-08 wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('personnelFilesManager')}">
	<@ww.form name="'listFrom'" namespace="'/personnelFile'" action="'searchPersonByUser'" method="'post'" >
		<@ww.token name="searchPersonByUserToken"/>
		<#include "./personnelFilesSearcher.ftl" />
		<#if employeesIds?exists>
			<@ww.hidden name="'employeesIds_a'" value="'${employeesIds?if_exists}'"/>
		<#else>
			<@ww.hidden name="'employeesIds_a'" value="'${req.getParameter('employeesIds_a')?if_exists}'"/>
		</#if>
		
		<#if sysUser?exists>
			<@ww.hidden name="'sysUser'" value="'${sysUser?if_exists}'"/>
		</#if>
		<#--用于项目组成员去重-->
		<@ww.hidden name="'projectInfo.id'" value="'${req.getParameter('projectInfo.id')?if_exists}'"/>
		
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
          <#if !(action.isReadOnly())>
          <#if backVisitCheckBox?exists>
          <@ww.hidden name="'backVisitCheckBox'" value="'${backVisitCheckBox?if_exists}'"/>
				 <input class="button" type="button" value="保存" onclick="return_employeess()"/>
		  </#if>
          </#if>
        </@buttonBar>
	<@list title="${action.getText('personnelFilesList')}" includeParameters="code|backVisitCheckBox|name|employeesIds_a|projectInfo.id|fileNo|inst.id|dept.id|duty.id|sysUser" fieldMap="like:code|name|fileNo" >
		<#if !object.disabled>
			<#if backVisitCheckBox?exists>
				<#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="personnelNames">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
	            </#if>
            </#if>
          	<@ww.hidden name="'#{object.id}'" value="'#{object.id}:${object.name}'"/>
          	
	        <@vcolumn title="${action.getText('personnel.code')}" property="code" sortable="asc">
	        <#if backVisitCheckBox?exists>
	        ${object.code}
	        <#else>
	        <a href="javascript: returnDialog(new Array(#{object.id},'${object.code}','${object.name}','${object.inst.id}','${object.dept.id}','${object.mobile}','${(object.birthday?string('yyyy-MM-dd'))?if_exists}','${object.email?if_exists}','${object.duty.id?if_exists}','${object.dept.name}','${object.idNumber}'));">
	            	${object.code}
	            </a>
	        </#if>
	            
	            <@alignLeft/>
	        </@vcolumn>
	        
	        <@vcolumn title="${action.getText('personnel.name')}" property="name" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
	        
	        <@vcolumn title="${action.getText('personnel.fileNo')}" property="fileNo" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('personnel.inst')}" property="inst.name" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('personnel.dept')}" property="dept.name" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('personnel.duty')}" property="duty.name" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('personnel.sex')}" property="sex" sortable="asc">
				<#if !object.sex>
						${action.getText('personnel.sex.man')}
					<#else>
						${action.getText('personnel.sex.women')}
				 </#if>
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('personnel.mobile')}" property="mobile" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('personnel.telphone')}" property="telphone" sortable="asc">
	            <@alignLeft/>
	        </@vcolumn>
		</#if>
	</@list>
</@ww.form>
</@htmlPage>
<script>
	var employeesIds = getObjByName('employeesIds_a').value;
	var nameAids =employeesIds.split(',');
	var idss ='';
	for(var i=0;i<nameAids.length;i++){
		if(i==nameAids.length-1){
			idss+=nameAids[i].split(':')[0];
		}else{
			idss+=nameAids[i].split(':')[0]+',';
		}
	}
	var ids = idss.split(',');
	var selector = document.getElementsByName("personnelNames");
	for(var i =0 ;i<ids.length;i++){
		for(var j=0;j<selector.length;j++){
			if(selector[j].value == ids[i]){
				selector[j].checked = true;
			}
		}
	}
</script>
<script language="javascript">
	function employeess(){
		var name_="";
		var selector = document.getElementsByName("personnelNames");
		var length = selector.length;
		if(length){
			for(var i=0;i<length;i++){
				if(selector[i].checked == true){
					var temp = selector[i].value;
					var tempName = getObjByName(temp).value;
					if(name_==""){
						name_ =tempName;
					}else{
						name_+=","+tempName;
					}
				}
			}
		}
		returnDialog(new Array(name_));
	}
	
	function return_employeess(){
		var employeesIds = getObjByName('employeesIds_a').value;
		returnDialog(new Array(employeesIds));
	}
	
	document.onclick=function(){
		var employeesIds = getObjByName('employeesIds_a').value;
		var selector = document.getElementsByName("personnelNames");
		var length = selector.length;
		if(length){
			for(var i=0;i<length;i++){
				if(selector[i].checked == true){
					var temp = selector[i].value;
					var tempName = getObjByName(temp).value;
					if(employeesIds.indexOf(tempName)<0){
						employeesIds += tempName +",";
					}
				}else{
					var temp = selector[i].value;
					var tempName = getObjByName(temp).value;
					employeesIds = employeesIds.replace(tempName+',','');
				}
			}
		}
		getObjByName('employeesIds_a').value = employeesIds;
	
	}
</script>
