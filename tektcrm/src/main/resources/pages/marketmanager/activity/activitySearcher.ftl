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

<#-- $Id: activitySearcher.ftl 2011-02-22 11:00:35Z hmguan $ -->

<#include "../../includes/hco2011.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('业务编码')}'" name="'activity.code'" value="'${req.getParameter('activity.code')?if_exists}'" cssClass="'underline'" />
		
 		<@ww.textfield label="'${action.getText('activity.theme')}'" name="'activity.theme'" value="'${req.getParameter('activity.theme')?if_exists}'" cssClass="'underline'" />
		
		<@ww.select label="'${action.getText('activity.activityType')}'" 
			name="'activityType.id'" 
			value="'${req.getParameter('activityType.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allActivityType"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		</tr>
	<tr>
		<@ww.select label="'${action.getText('activity.progress')}'" 
			name="'progress.id'" 
			value="'${req.getParameter('progress.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allProgress"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	
		<@ww.select label="'${action.getText('activity.priority')}'" 
			name="'priority.id'" 
			value="'${req.getParameter('priority.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allPriority"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
 		
 		<@pp.dateRanger label="'${action.getText('activity.beginTime')}'" 
 						name="'activity.beginTime'" 
			            value="'${req.getParameter('activity.beginTime_start')?if_exists}|${req.getParameter('activity.beginTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            maxlength="10"/>            
			  </tr>
	<tr>          
 		<@pp.dateRanger label="'${action.getText('activity.endTime')}'" 
 						name="'activity.endTime'" 
			            value="'${req.getParameter('activity.endTime_start')?if_exists}|${req.getParameter('activity.endTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            maxlength="10"/>             
	
		<@ww.select label="'${action.getText('activity.status')}'" 
			name="'status.id'" 
			value="'${req.getParameter('status.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allStatus"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
 		<@ww.textfield label="'${action.getText('activity.place')}'" name="'activity.place'" value="'${req.getParameter('activity.place')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>         
		<@crm_onlySearchInvalid_checkBox />
	<tr>
		
</@inputTable>
<script language="javascript">
	window.onload=function(){
		<#if req.getParameter('activityType.id')?exists>
			getObjByName('activityType.id').value='${req.getParameter('activityType.id')?if_exists}';
		</#if>
		<#if req.getParameter('status.id')?exists>
			getObjByName('status.id').value='${req.getParameter('status.id')?if_exists}';
		</#if>
		<#if req.getParameter('progress.id')?exists>
			getObjByName('progress.id').value='${req.getParameter('progress.id')?if_exists}';
		</#if>
		<#if req.getParameter('priority.id')?exists>
			getObjByName('priority.id').value='${req.getParameter('priority.id')?if_exists}';
		</#if>
	}
	function checkInvalidParms(){
		if (getObjByName('activityType.id').value==-1){
			getObjByName('activityType.id').value='';
		}
		if (getObjByName('progress.id').value==-1){
			getObjByName('progress.id').value='';
		}
		if (getObjByName('priority.id').value==-1){
			getObjByName('priority.id').value='';
		}
        var beginDateMsg="${action.getText('activity.beginTime.error')}";
	    if(queryDate("activity.beginTime_start","activity.beginTime_end",
	       beginDateMsg,null)==false){   
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('activity.endTime.error')}";
	    if(queryDate("activity.endTime_start","activity.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }	 
	    if (getObjByName('status.id').value==-1){
			getObjByName('status.id').value='';
		}
		return true;
    }
</script>