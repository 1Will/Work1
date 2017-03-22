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
 		<@ww.textfield label="'${action.getText('overTimeBill.code')}'" name="'overTimeBill.code'" value="'${req.getParameter('overTimeBill.code')?if_exists}'" cssClass="'underline'" />
		<@pp.dateRanger label="'${action.getText('overTimeBill.crateDate')}'" 
 			name="'overTimeBill.crateDate'" 
		    value="'${req.getParameter('overTimeBill.crateDate_start')?if_exists}|${req.getParameter('overTimeBill.crateDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
	</tr>
	<tr>
	    <@ww.textfield label="'${action.getText('overTimeBill.applyPerson')}'" name="'overTimeBill.applyPerson'" value="'${req.getParameter('overTimeBill.applyPerson')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('overTimeBill.dept')}'" 
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
		<@pp.dateRanger label="'${action.getText('overTimeBill.startTime')}'" 
 			name="'overTimeBill.startTime'" 
		    value="'${req.getParameter('overTimeBill.startTime_start')?if_exists}|${req.getParameter('overTimeBill.startTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
			
		<@pp.dateRanger label="'${action.getText('overTimeBill.endTime')}'" 
 			name="'overTimeBill.endTime'" 
		    value="'${req.getParameter('overTimeBill.endTime_start')?if_exists}|${req.getParameter('overTimeBill.endTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
	</tr>
	<tr>
		<@ww.select label="'${action.getText('overTimeBill.status')}'" 
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
		var beginDateMsg="${action.getText('overTimeBill.createDate.dateFormate.error')}";
	    if(queryDate("overTimeBill.crateDate_start","overTimeBill.crateDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('overTimeBill.createDate.dateFormate.error')}";
	    if(queryDate("overTimeBill.crateDate_start","overTimeBill.crateDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
		var beginDateMsg="${action.getText('overTimeBill.startTime.dateFormate.error')}";
	    if(queryDate("overTimeBill.startTime_start","overTimeBill.startTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('overTimeBill.startTime.dateFormate.error')}";
	    if(queryDate("overTimeBill.startTime_start","overTimeBill.startTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
		var beginDateMsg="${action.getText('overTimeBill.endTime.dateFormate.error')}";
	    if(queryDate("overTimeBill.endTime_start","overTimeBill.endTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('overTimeBill.endTime.dateFormate.error')}";
	    if(queryDate("overTimeBill.endTime_start","overTimeBill.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
		return true;
		
    }
</script>