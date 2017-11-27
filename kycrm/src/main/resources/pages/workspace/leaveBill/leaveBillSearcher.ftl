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

<#-- $Id: contactArchivesSearcher.ftl 2009-12-08 11:00:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<#--<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>-->
	<tr>
 		<@ww.textfield label="'${action.getText('leaveBill.code')}'" name="'leaveBill.code'" value="'${req.getParameter('leaveBill.code')?if_exists}'" cssClass="'underline'" />
 		<@pp.dateRanger label="'${action.getText('leaveBill.crateDate')}'" 
 			name="'leaveBill.crateDate'" 
		    value="'${req.getParameter('leaveBill.crateDate_start')?if_exists}|${req.getParameter('leaveBill.crateDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('leaveBill.applyPerson')}'" name="'leaveBill.applyPerson'" value="'${req.getParameter('leaveBill.applyPerson')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('leaveBill.dept')}'" 
			name="'dept.id'" 
			value="'${req.getParameter('dept.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allDepts"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('leaveBill.startTime')}'" 
 			name="'leaveBill.startTime'" 
		    value="'${req.getParameter('leaveBill.startTime_start')?if_exists}|${req.getParameter('leaveBill.startTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
			
		<@pp.dateRanger label="'${action.getText('leaveBill.endTime')}'" 
 			name="'leaveBill.endTime'" 
		    value="'${req.getParameter('leaveBill.endTime_start')?if_exists}|${req.getParameter('leaveBill.endTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
	</tr>
	<tr>
		<@ww.select label="'${action.getText('leaveBill.status')}'" 
			name="'status.id'" 
			value="'${req.getParameter('status.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allStatus"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
window.onload=function(){
		<#if req.getParameter('dept.id')?exists>
			getObjByName('dept.id').value='${req.getParameter('dept.id')?if_exists}';
		</#if>
		<#if req.getParameter('status.id')?exists>
			getObjByName('status.id').value='${req.getParameter('status.id')?if_exists}';
		</#if>
		}
	function checkInvalidParms(){
		if (getObjByName('dept.id').value==-1){
			getObjByName('dept.id').value='';
		}
		if (getObjByName('status.id').value==-1){
			getObjByName('status.id').value='';
		}
		
		var beginDateMsg="${action.getText('leaveBill.createDate.dateFormate.error')}";
	    if(queryDate("leaveBill.crateDate_start","leaveBill.crateDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('leaveBill.createDate.dateFormate.error')}";
	    if(queryDate("leaveBill.crateDate_start","leaveBill.crateDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
			
		var beginDateMsg="${action.getText('leaveBill.startTime.dateFormate.error')}";
	    if(queryDate("leaveBill.startTime_start","leaveBill.startTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('leaveBill.startTime.dateFormate.error')}";
	    if(queryDate("leaveBill.startTime_start","leaveBill.startTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
		var beginDateMsg="${action.getText('leaveBill.endTime.dateFormate.error')}";
	    if(queryDate("leaveBill.endTime_start","leaveBill.endTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('leaveBill.endTime.dateFormate.error')}";
	    if(queryDate("leaveBill.endTime_start","leaveBill.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
		return true;
    }
</script>