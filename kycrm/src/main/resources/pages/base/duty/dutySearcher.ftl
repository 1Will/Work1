  <@inputTable> 
	<tr align="left">
	    <@textfield anothername="code" maxlength="20" label="${action.getText('duty.code')}" name="code" value="${req.getParameter('code')?if_exists}" maxlength="20" type="S" />
	    <@textfield anothername="name" maxlength="20" label="${action.getText('duty.name')}" name="name" value="${req.getParameter('name')?if_exists}" maxlength="20" type="S"/>
	</tr>
	<tr>
		<@select 
		    
    		label="${action.getText('duty.jobName')}"
			name="jobName.id" 
			id="jobName.id"
			value="${jobNameId?if_exists}"
			listKey="id"
			listValue="name" 
			list="allJobNames"
			emptyOption="false"
	    	/>
		<@crm_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript"> 
 <#if jobNameId?exists>
   getObjByName('jobName.id').value=#{jobNameId}
 </#if>
 function checkInvalidParms(){
	 	if(getObjByName('jobName.id').value == -1){
	 	    getObjByName('jobName.id').value='';
	 	}
 	return true;
 }
</script>