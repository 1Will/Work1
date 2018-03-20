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

<#-- $Id: custContactReportSearch.ftl 2010-04-06 wliu $ -->

<#include "../includes/hco2011.ftl" />

<@inputTable>
	<tr>
   	    <@ww.textfield label="'${action.getText('流程名称')}'" 
		                name="'flow.name'" 
		                value="'${req.getParameter('flow.name')?if_exists}'" 
		                cssClass="'underline'"/>
		                <@ww.textfield label="'${action.getText('提交人')}'" 
		                name="'submitPer.name'" 
		                value="'${req.getParameter('submitPer.name')?if_exists}'" 
		                cssClass="'underline'"/>
		
   	   
	</tr>
	<tr>
	   <@pp.dateRanger label="'${action.getText('提交时间')}'" name="'runTask.submitTime'" 
            value="'${req.getParameter('runTask.submitTime_start')?if_exists}|${req.getParameter('runTask.submitTime_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
	</tr>
	<script language="javascript">
	
	function checkInvalidParms_seach(){
		var beginDateMsg="${action.getText('时间格式错误！')}";
	    if(queryDate("runTask.submitTime_start","runTask.submitTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	
	    if(getObjByName('runTask.submitTime_start').value>getObjByName('runTask.submitTime_end').value){
				alert('${action.getText('开始时间必须小于结束时间！')}');
				getObjByName('runTask.submitTime_end').value="";
	       		getObjByName('runTask.submitTime_end').focus();
				return false;
		} 
		return true;
    }
</script>
	<#--
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
	-->
</@inputTable>