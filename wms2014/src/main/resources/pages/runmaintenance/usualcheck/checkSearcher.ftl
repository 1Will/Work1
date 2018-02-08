<#assign assetNo=''/>
<#assign assetName=''/>
<#if toolingDevFlag?exists>
	<#if toolingDevFlag == 'DEVICE'>
	  <#assign assetNo="${action.getText('device.no')}"/>
	  <#assign assetName="${action.getText('device.name')}"/>
	<#elseif toolingDevFlag == 'TOOLING'>
	  <#assign assetNo="${action.getText('tooling.no')}"/>
	  <#assign assetName="${action.getText('tooling.name')}"/>
	</#if>
</#if>
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('usualcheck.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
 		<@ww.textfield label="'${action.getText('usualcheck.name')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
	</tr>
    <#if toolingDevFlag?exists>
      <#if toolingDevFlag == 'DEVICE'>
		<tr>
			<@ww.textfield label="'${assetNo}'" name="'assetNo'" value="'${req.getParameter('assetNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${assetName}'" name="'assetName'" value="'${req.getParameter('assetName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr> 
            <@ww.select label="'${action.getText('department')}'" 
                        name="'department.id'" 
                        value="'${req.getParameter('department.id')?if_exists}'"
                        listKey="id" listValue="name"
                        list="departments" emptyOption="false" 
                        disabled="false" required="false">
            </@ww.select>
            <@ww.textfield label="'${action.getText('checker')}'" name="'checker'" value="'${req.getParameter('checker')?if_exists}'" cssClass="'underline'"/>
            </tr>
    	   <tr> 
    	    <@pp.dateRanger label="'${action.getText('checkDate')}'" name="'checkDate'" 
		                    value="'${req.getParameter('checkDate_start')?if_exists}|${req.getParameter('checkDate_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
    	   <@ww.select label="'${action.getText('state')}'" 
                             required="false" name="'status'" 
                             value="'${status?if_exists}'"
                             listKey="value" listValue="label"
                             list="usualCheckStatus" 
                              emptyOption="false" 
                              disabled="false">
                </@ww.select>
    	  
		       
           </tr>     
          <tr>
          <@eam2008_onlySearchInvalid_checkBox/>
          </tr>
	  <#else>
	  	<tr>
			<@ww.textfield label="'${assetNo}'" name="'assetNo'" value="'${req.getParameter('assetNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'graphNo'" value="'${req.getParameter('graphNo')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.textfield label="'${assetName}'" name="'assetName'" value="'${req.getParameter('assetName')?if_exists}'" cssClass="'underline'"/>
		     <@ww.select label="'${action.getText('department')}'" 
                        name="'department.id'" 
                        value="'${req.getParameter('department.id')?if_exists}'"
                        listKey="id" listValue="name"
                        list="departments" emptyOption="false" 
                        disabled="false" required="false">
            </@ww.select>
		</tr>
		<tr>
            	<@ww.textfield label="'${action.getText('checker')}'" name="'checker'" value="'${req.getParameter('checker')?if_exists}'" cssClass="'underline'"/>
              
                <@pp.dateRanger label="'${action.getText('checkDate')}'" name="'checkDate'" 
		                    value="'${req.getParameter('checkDate_start')?if_exists}|${req.getParameter('checkDate_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
          <tr>
          <tr>
          <@ww.select label="'${action.getText('state')}'" 
                             required="false" name="'status'" 
                             value="'${status?if_exists}'"
                             listKey="value" listValue="label"
                             list="usualCheckStatus" 
                              emptyOption="false" 
                              disabled="false">
                               </@ww.select>
           <@eam2008_onlySearchInvalid_checkBox/>
          </tr>
	  </#if>
    </#if>
</@inputTable>
<script language="javascript" type="text/JavaScript">
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    <#else>
        <#if loginUser.department?exists>
	      getObjByNameRe("department.id").value = #{loginUser.department.id};
	    </#if>
    </#if>
    <#if first>

  	</#if>
  }
 var selector = document.all("status");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {

    <#if req.getParameter('status')?exists>
    if (selector.options[i].value == "${req.getParameter('status')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }

  function checkInvalidParms() {
    if (-1 == getObjByNameRe("department.id").value) {
		getObjByNameRe("department.id").value = '';
    }
     if (getObjByNameRe("status").value == -1) {
          getObjByNameRe("status").value = '';
        }
	//验证编制日期格式
	planCreatedMsg="${action.getText('checkDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("checkDate_start","checkDate_end",
	    planCreatedMsg,null)){
	  return false;
	}
  }
</script>