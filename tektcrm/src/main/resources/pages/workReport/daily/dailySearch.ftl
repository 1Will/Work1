  <@inputTable> 
  	<@ww.hidden name="'isSuperSys'" value="'${req.getParameter('isSuperSys')?if_exists}'"/>
	<tr align="left">
	    <@datePickerRanger
			anothername="currentDate"
    		label="${action.getText('daily.currentDate')}"
    		value="${req.getParameter('currentDate')?if_exists}"
           	name="currentDate"
			cssClass="underline" 
			flag="true">
		</@datePickerRanger>
		<@textfield name="rapporteur.name" label="${action.getText('daily.rapporteur')}" value="${rapporteur?if_exists}" />
	    <@select label="${action.getText('daily.inst')}" 
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
	   
	</tr>
	<tr>
	  	<@select label="${action.getText('daily.dept')}" 
       id="dept.id" 
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
   <@select label="${action.getText('daily.duty')}" 
       id="duty.id"
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
<script>
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
		<#if req.getParameter('rapporteur')?exists>
			getObjByName('rapporteur').value='${req.getParameter('rapporteur')?if_exists}';
		</#if>
	}
 </script>
</@inputTable>