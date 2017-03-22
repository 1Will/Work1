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

<#include "../../includes/macros.ftl" />
	<#--
 	<@ww.hidden name="'inst.id'" value="'${req.getParameter('inst.id')?if_exists}'" />
 	<@ww.hidden name="'dept.id'" value="'${req.getParameter('dept.id')?if_exists}'" />
 	<@ww.hidden name="'duty.id'" value="'${req.getParameter('duty.id')?if_exists}'" />
 	-->
 	<#if user.organization?exists>
		<@ww.hidden name="'orgId'" value="#{user.organization.id}"/>
	</#if>
<@inputTable>
<tr>
<@ww.textfield label="'${action.getText('personnel.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
<@ww.textfield label="'${action.getText('personnel.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
</tr>
<tr>
	<@ww.textfield label="'${action.getText('personnel.fileNo')}'" name="'fileNo'" value="'${req.getParameter('fileNo')?if_exists}'" cssClass="'underline'"/>
	<@ww.select label="'${action.getText('personnel.inst')}'" 
       name="'inst.id'" 
       value="'${req.getParameter('institution.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allInsts" 
       emptyOption="false" 
       disabled="false" 
       required="false"
       onchange="'InstitutionSelectDeptDWR(\"inst.id\",\"dept.id\",\"${action.getText('')}\",\"false\")'">
   </@ww.select>
   </tr>
   <tr>
   <@ww.select label="'${action.getText('personnel.dept')}'" 
       name="'dept.id'" 
       value="'${req.getParameter('dept.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allDepts" 
       emptyOption="false" 
       disabled="false" 
       required="false"
        onchange="'DutyCascadeDWR(\"dept.id\",\"duty.id\",#{user.organization.id?if_exists},\"${action.getText('select.option.all')}\",\"search\")'">
   </@ww.select>
   <@ww.select label="'${action.getText('personnel.duty')}'" 
       name="'duty.id'" 
       value="'${req.getParameter('duty.id')?if_exists}'"
       listKey="id" 
       listValue="name"
       list="allDutys" 
       emptyOption="false" 
       disabled="false" 
       required="false">
   </@ww.select>
</tr>
<#if sysUser?exists>
<#else>
<tr>
	<@crm_onlySearchInvalid_checkBox/>
</tr>
</#if>
</@inputTable>
<#--
	<script language="javascript">
			
		var all="${action.getText('all')}";
		function changeCurrentValue(id,text,valueId){
			getObjByName("search"+id).value=text;
			getObjByName(id).style.display="none";
			getObjByName('img'+id).src="${req.contextPath}/images/unSelected.jpg"
			getObjByName(id+'.id').value=valueId;

		}
		function display(id){
			if(getObjByName(id).style.display=="none"){
				getObjByName('img'+id).src="${req.contextPath}/images/Selected.jpg"
				var a=Position.positionedOffset(getObjByName('search'+id));
				getObjByName(id).style.left=a[0];
				getObjByName(id).style.top=a[1]+20;
				getObjByName(id).style.display="block";
			}else {
				getObjByName(id).style.display="none";
				getObjByName('img'+id).src="${req.contextPath}/images/unSelected.jpg"
			}
		}
	</script>
	-->
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
		
		var instSelector = document.all("inst.id");
	  	instGroups = instSelector.options.length;
	  	for (i=0; i<instGroups; i++) {
	    	<#if req.getParameter('inst.id')?exists>
			    if (instSelector.options[i].value == "${req.getParameter('inst.id')?if_exists}") {
					instSelector.options[i].selected="true";
			    }
	    	</#if>
	  	}
 	
		var deptSelector = document.all("dept.id");
		deptGroups = deptSelector.options.length;
		for (i=0; i<deptGroups; i++) {
		    <#if req.getParameter('dept.id')?exists>
			    if (deptSelector.options[i].value == "${req.getParameter('dept.id')?if_exists}") {
					deptSelector.options[i].selected="true";
			    }
		    </#if>
		}
		
		var dutySelector = document.all("duty.id");
		dutyGroups = dutySelector.options.length;
		for (i=0; i<dutyGroups; i++) {
		    <#if req.getParameter('duty.id')?exists>
			    if (dutySelector.options[i].value == "${req.getParameter('duty.id')?if_exists}") {
					dutySelector.options[i].selected="true";
			    }
		    </#if>
		}
		
		//单位
    	<#if req.getParameter('inst.id')?exists>
    	        getObjByName('inst.id').value='${req.getParameter('inst.id')?if_exists}';
		    	//设置同步
		    	DWREngine.setAsync(false); 
		    	//回调单位的值后触发DWR 级联部门  
		    	InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('')}","false");
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
		<#if req.getParameter('rapporteur')?exists>
			getObjByName('rapporteur').value='${req.getParameter('rapporteur')?if_exists}';
		</#if>
		
		<#--
		<#if req.getParameter('inst.id')?exists>
			getObjByName('inst.id').value= "${req.getParameter('inst.id')?if_exists}";
		</#if>
		<#if req.getParameter('searchInst')?exists>
			getObjByName('searchInst').value= "${req.getParameter('searchInst')?if_exists}";
		</#if>
		<#if req.getParameter('dept.id')?exists>
			getObjByName('dept.id').value= "${req.getParameter('dept.id')?if_exists}";
		</#if>
		<#if req.getParameter('searchDept')?exists>
			getObjByName('searchDept').value= "${req.getParameter('searchDept')?if_exists}";
		</#if>
		<#if req.getParameter('duty.id')?exists>
			getObjByName('duty.id').value= "${req.getParameter('duty.id')?if_exists}";
		</#if>
		<#if req.getParameter('searchDuty')?exists>
			getObjByName('searchDuty').value= "${req.getParameter('searchDuty')?if_exists}";
		</#if>
		-->
	}
	<#--
function closediv(id){
	if(getObjByName(id).style.display=='block'){
	    getObjByName(id).style.display='none';
	    getObjByName('img'+id).src="${req.contextPath}/images/unSelected.jpg"
    }
}
 
var out_Instdiv = true;//标识
var out_Deptdiv = true;//标识
var out_Dutydiv = true;//标识
 
function checkdiv(event){
    var event  = window.event || event;
    var obj = event.srcElement ? event.srcElement : event.target;
    if(out_Instdiv && obj!=getObjByName('imgInst') && obj!=getObjByName('searchInst')){
        closediv('Inst');
    }
    if(out_Deptdiv && obj!=getObjByName('imgDept') && obj!=getObjByName('searchDept')){
        closediv('Dept');
    }
    if(out_Dutydiv && obj!=getObjByName('imgDuty') && obj!=getObjByName('searchDuty')){
        closediv('Duty');
    }
}
 
 
document.onclick = function(event){
   checkdiv(event);
}
 
getObjByName("Inst").onmouseover = function(){
   out_Instdiv = false;
}
 
getObjByName("Inst").onmouseout = function(){
   out_Instdiv = true;
}
getObjByName("Dept").onmouseover = function(){
   out_Deptdiv = false;
}
 
getObjByName("Dept").onmouseout = function(){
   out_Deptdiv = true;
}
getObjByName("Duty").onmouseover = function(){
   out_Dutydiv = false;
}
 
getObjByName("Duty").onmouseout = function(){
   out_Dutydiv = true;
}
-->
</SCRIPT>