<@inputTable>
	<tr>
 		<@ww.textfield label="'${action.getText('device.No')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'" />
	 	<@ww.textfield label="'${action.getText('device.Name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
    </tr>
	<tr>
 		<@ww.textfield label="'${action.getText('position')}'" name="'position'" value="'${req.getParameter('position')?if_exists}'" cssClass="'underline'" />
 		<@ww.textfield label="'${action.getText('lubricationDetail.planDutyPeople')}'" name="'planExePeople'" value="'${req.getParameter('planExePeople')?if_exists}'" cssClass="'underline'" />
	 	
    </tr>
    <tr>
    	<@ww.textfield label="'${action.getText('planExectPeople')}'" name="'planExectPeople'" value="'${req.getParameter('planExectPeople')?if_exists}'" cssClass="'underline'"/>
        <@pp.dateRanger label="'${action.getText('planCreatedTime')}'" name="'estimateExecDate'" 
                value="'${req.getParameter('estimateExecDate_start')?if_exists}|${req.getParameter('estimateExecDate_end')?if_exists}'"
                cssClass="'underline'" dateFormat="date"/>
    </tr>
	<#if planProcFlag?exists>
	<#if (planProcFlag=='PROC')>
    	<@ww.textfield label="'${action.getText('actualExectPeople')}'" name="'actualExePeople'" value="'${req.getParameter('actualExePeople')?if_exists}'" cssClass="'underline'"/>
        <@pp.dateRanger label="'${action.getText('actualCreatedTime')}'" name="'actualExecDate'" 
                value="'${req.getParameter('actualExecDate_start')?if_exists}|${req.getParameter('actualExecDate_end')?if_exists}'"
                cssClass="'underline'" dateFormat="date"/>  
    </tr>             	
    </#if> 
    </#if>    	
</@inputTable>
<script language="javascript">
	 function checkInvalidParms(){

			if(document.forms[0].elements["estimateExecDate_start"].value!=""){
	          	if(!validateTime(document.forms[0].elements["estimateExecDate_start"].value)){
	               alert("${action.getText('planCreatedTime.start_EndTimeMistake')}");
	               return false;
	          	}
    		}
	    	if(document.forms[0].elements["estimateExecDate_end"].value!=""){
	         	if(!validateTime(document.forms[0].elements["estimateExecDate_end"].value)){
	               alert("${action.getText('planCreatedTime.start_EndTimeMistake')}");
	               return false;
	          	}
    		}
	 	<#if planProcFlag?exists>
			<#if (planProcFlag=='PROC')>
			if(document.forms[0].elements["actualExecDate_start"].value!=""){
	          	if(!validateTime(document.forms[0].elements["actualExecDate_start"].value)){
	               alert("${action.getText('actualExecDate.start_EndTimeMistake')}");
	               return false;
	          	}
	    	}
	    	if(document.forms[0].elements["actualExecDate_end"].value!=""){
	         	if(!validateTime(document.forms[0].elements["actualExecDate_end"].value)){
	               alert("${action.getText('actualExecDate.start_EndTimeMistake')}");
	               return false;
	          	}
	    	}
			</#if>
		</#if>
	 	return true;
	 }
</script>