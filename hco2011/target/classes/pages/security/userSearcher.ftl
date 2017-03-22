<@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('user.loginName')}'" name="'loginName'" value="'${req.getParameter('loginName')?if_exists}'" cssClass="'underline'"/>
			   <@ww.textfield label="'${action.getText('user.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
            </tr>   
            <tr>   
               <@ww.select label="'${action.getText('institution')}'" 
		                   name="'institution.id'" 
		                   value="'${req.getParameter('institution.id')?if_exists}'"
		                   listKey="id" listValue="name"
		                   list="institutions" emptyOption="false" 
		                   disabled="false" required="false"
		                   onchange="'InstitutionSelectDeptDWR(\"institution.id\",\"department.id\",\"${action.getText('select.option.all')}\",\"true\")'">
	           </@ww.select>
               <@ww.select label="'${action.getText('department')}'" 
                           name="'department.id'" 
                           value="'${req.getParameter('department.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="departments" emptyOption="false" 
                           disabled="false" required="false">
               </@ww.select>
            </tr>
            <#--
            <tr>
                <@ww.checkbox label="'${action.getText('excludeDisabled')}'" name="'exclude_disabled'" value="'true'" fieldValue="'true'"/>
                <td>
                </td>
            </tr>
            -->
            <tr>
            	<@crm_onlySearchInvalid_checkBox />
            </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  var selector = document.all("institution.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('institution.id')?exists>
    if (selector.options[i].value == "${req.getParameter('institution.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  //页面刷新时保持单位联动部门
	if(-1 != getObjByName('institution.id').value){
		//设置同步
	    DWREngine.setAsync(false); 
	    //回调单位的值后触发DWR 级联部门  
		InstitutionSelectDeptDWR("institution.id","department.id","${action.getText('select.option.all')}","true");
	    //重新设置为异步方式
	    DWREngine.setAsync(true);  
	    if('' == "${req.getParameter('department.id')?if_exists}"){
	    	getObjByName('department.id').value = -1;
	    }else{
			getObjByName('department.id').value = "${req.getParameter('department.id')?if_exists}"; 
		}
	}else{
		InstitutionSelectDeptDWR("institution.id","department.id","${action.getText('select.option.all')}","true");
	}
	 
  function checkInvalidParms() {
    if (-1 == document.getElementById("institution.id").value) {
		document.getElementById("institution.id").value = '';
    }
    if (-1 == document.getElementById("department.id").value) {
		document.getElementById("department.id").value = '';
    }
    return true;
  }
</script>