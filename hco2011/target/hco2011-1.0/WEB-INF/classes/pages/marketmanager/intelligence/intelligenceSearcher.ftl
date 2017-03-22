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

<#-- $Id: intelligenceSearcher.ftl 2011-02-22 11:00:35Z hmguan $ -->

<@inputTable>
	<tr>
        <@textfield label="${action.getText('intelligence.code')}" name="intelligence.code" anothername="code" value="${req.getParameter('intelligence.code')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <@textfield label="${action.getText('intelligence.theme')}" name="intelligence.theme" anothername="theme" value="${req.getParameter('intelligence.theme')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <@textfield label="${action.getText('intelligence.customerInfo')}" name="intelligence.customerInfo" anothername="customerInfo" value="${req.getParameter('intelligence.customerInfo')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
 	</tr>
 	<tr>
        <@textfield label="${action.getText('intelligence.persons')}" name="intelligence.persons" anothername="persons" value="${req.getParameter('intelligence.persons')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
        <@textfield label="${action.getText('intelligence.contactArchives')}" name="intelligence.contactArchives" anothername="contactArchives" value="${req.getParameter('intelligence.contactArchives')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
 		<@pp.dateRanger label="'${action.getText('intelligence.analysisTime')}'" 
 						name="'intelligence.analysisTime'" 
			            value="'${req.getParameter('intelligence.analysisTime_start')?if_exists}|${req.getParameter('intelligence.analysisTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            maxlength="10"/>     
	</tr>
	<tr>
		<@ww.select label="'${action.getText('intelligence.important')}'" 
			name="'important.id'" 
			value="'${req.getParameter('important.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allImportant"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@crm_onlySearchInvalid_checkBox />
	<tr>
		
</@inputTable>
<script language="javascript">
	window.onload=function(){
		<#if req.getParameter('important.id')?exists>
			getObjByName('important.id').value='${req.getParameter('important.id')?if_exists}';
		</#if>
	}
	function checkInvalidParms(){
		if (getObjByName('important.id').value==-1){
			getObjByName('important.id').value='';
		}
        var dateMsg="${action.getText('intelligence.intelligenceTime.error')}";
	    if(queryDate("intelligence.analysisTime_start","intelligence.analysisTime_start",
	       dateMsg,null)==false){   
	   	   return false;
	    }	 
		return true;
    }
</script>