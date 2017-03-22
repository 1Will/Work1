  <@ww.hidden name="'xb'" value="'${req.getParameter('xb')?if_exists}'" /><#-- the value of sex select list -->
  <@inputTable>
	<tr align="left">
		<@ww.textfield label="'${action.getText('employee.no')}'" name="'employeeNo'" value="'${req.getParameter('employeeNo')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('employee.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		
		<td align="right" valign="top">
	    	<label class="label">${action.getText('employee.sex')}:</lable>
	    </td>
	    <td>
	    	<select name="sex">
    			<option value="">${action.getText('select.option.all')}</option>
    			<option value="true">${action.getText('employee.sex.man')}</option>
    			<option value="false">${action.getText('employee.sex.woman')}</option>
    		</select>
    		</td>
	</tr>
	<tr>
		<@ww.select 
    		label="'${action.getText('employee.inst')}'"
			required="false"
			name="'inst.id'" 
			value="'${req.getParameter('inst.id')?if_exists}'"
			listKey="id"
			listValue="name" 
			list="allInsts"
	    	emptyOption="false" 
	    	disabled="false"
	    	onchange="'InstitutionSelectDeptDWR(\"inst.id\",\"dept.id\",\"${action.getText('select.option.all')}\",\"true\")'"
	    	/>
	    	
	    	<@ww.select 
    		label="'${action.getText('employee.dept')}'"
			required="false"
			name="'dept.id'" 
			value="'${req.getParameter('dept.id')?if_exists}'" 
			listKey="id"
			listValue="name" 
			list="allDepts"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	
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
	    	<#--
	   		<@ww.textfield label="'${action.getText('employee.duty')}'" name="'duty'" value="'${req.getParameter('duty')?if_exists}'" cssClass="'underline'"/>
	   		-->
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript"> 
	/*查询刷新时保持下拉列表的值*/
	window.onload=function(){
		selector = document.all("sex");
  		groups = selector.options.length;
		  for (i=0; i<groups; i++) {
		  <#if req.getParameter('xb')?exists>
		    if (selector.options[i].value == "${req.getParameter('xb')?if_exists}") {
		      selector.options[i].selected="true";
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
		    InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('select.option.all')}","true");
		    //重新设置为异步方式
		    DWREngine.setAsync(true);
		    if('' == "${req.getParameter('dept.id')?if_exists}"){
		    	getObjByName('dept.id').value = -1;
		    }else{
				getObjByName('dept.id').value = "${req.getParameter('dept.id')?if_exists}"; 
			}
		}else{
			 InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('select.option.all')}","true");
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
	}
	
	/*封装下拉列表的值并传递*/
  function checkInvalidParms() {
  	//alert(getObjByName('sex').value);
  	if(getObjByName('sex').value == 'true'){
  		getObjByName('xb').value = true;
  		//alert(getObjByName('xb').value)
  	}else if(getObjByName('sex').value == 'false'){
  		getObjByName('xb').value = false;
  	}else{
  		getObjByName('xb').value = '';
  	}
  	if (getObjByName('inst.id').value == -1){
  		getObjByName('inst.id').value = '';
  	}
  	if(getObjByName('dept.id').value == -1){
  		getObjByName('dept.id').value = '';
  	}
  	if(getObjByName('duty').value == -1){
  		getObjByName('duty').value = '';
  	}
    return true;
  }
  </script>