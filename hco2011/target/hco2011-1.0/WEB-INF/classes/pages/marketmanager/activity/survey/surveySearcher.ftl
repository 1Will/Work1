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

<#-- $Id: surveySearcher.ftl 2011-02-22 11:00:35Z hmguan $ -->

<@inputTable>
	<tr>
        <@textfield label="${action.getText('survey.code')}" name="survey.code" anothername="code" value="${req.getParameter('survey.code')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
 		<@pp.dateRanger label="'${action.getText('survey.surveyTime')}'" 
 						name="'survey.surveyTime'" 
			            value="'${req.getParameter('survey.surveyTime_start')?if_exists}|${req.getParameter('survey.surveyTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            maxlength="10"/>     
        <@textfield label="${action.getText('survey.surveyTarget')}" name="survey.surveyTarget" anothername="surveyTarget" value="${req.getParameter('survey.surveyTarget')?if_exists}" cssClass="underline" maxlength="20"/>            
	</tr>
	<tr>
        <@textfield label="${action.getText('survey.persons')}" name="survey.persons" anothername="persons" value="${req.getParameter('survey.persons')?if_exists}" cssClass="underline" maxlength="20"/>            
		<@crm_onlySearchInvalid_checkBox />
	<tr>
		
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
        var dateMsg="${action.getText('survey.surveyTime.error')}";
	    if(queryDate("survey.surveyTime_start","survey.surveyTime_end",
	       dateMsg,null)==false){   
	   	   return false;
	    }	 
		return true;
    }
</script>