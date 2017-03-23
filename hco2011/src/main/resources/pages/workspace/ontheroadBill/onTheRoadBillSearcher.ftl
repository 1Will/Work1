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
 		<@ww.textfield label="'${action.getText('onTheRoadBill.code')}'" name="'onTheRoadBill.code'" value="'${req.getParameter('onTheRoadBill.code')?if_exists}'" cssClass="'underline'" />
		<@pp.dateRanger label="'${action.getText('onTheRoadBill.crateDate')}'" 
 			name="'onTheRoadBill.crateDate'" 
		    value="'${req.getParameter('onTheRoadBill.crateDate_start')?if_exists}|${req.getParameter('onTheRoadBill.crateDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>   
				<#--
			<script language="javascript">
				<#if advisory.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("advisory.advisoryTime").value==''){
						getObjByName("advisory.advisoryTime").value = date.format("yyyy-MM-dd");
					}
				</#if>
			</script>-->
	</tr>
	<tr>
	    <@ww.textfield label="'${action.getText('onTheRoadBill.applyPerson')}'" name="'onTheRoadBill.applyPerson'" value="'${req.getParameter('onTheRoadBill.applyPerson')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('onTheRoadBill.dept')}'" 
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
		<@pp.dateRanger label="'${action.getText('onTheRoadBill.startTime')}'" 
 			name="'onTheRoadBill.startTime'" 
		    value="'${req.getParameter('onTheRoadBill.startTime_start')?if_exists}|${req.getParameter('onTheRoadBill.startTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
			
		<@pp.dateRanger label="'${action.getText('onTheRoadBill.endTime')}'" 
 			name="'onTheRoadBill.endTime'" 
		    value="'${req.getParameter('onTheRoadBill.endTime_start')?if_exists}|${req.getParameter('onTheRoadBill.endTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
	</tr>
	<tr>
		<@ww.select label="'${action.getText('onTheRoadBill.status')}'" 
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
		var beginDateMsg="${action.getText('onTheRoadBill.createDate.dateFormate.error')}";
	    if(queryDate("onTheRoadBill.crateDate_start","onTheRoadBill.crateDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('onTheRoadBill.createDate.dateFormate.error')}";
	    if(queryDate("onTheRoadBill.crateDate_start","onTheRoadBill.crateDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
		var beginDateMsg="${action.getText('onTheRoadBill.startTime.dateFormate.error')}";
	    if(queryDate("onTheRoadBill.startTime_start","onTheRoadBill.startTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('onTheRoadBill.startTime.dateFormate.error')}";
	    if(queryDate("onTheRoadBill.startTime_start","onTheRoadBill.startTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
		var beginDateMsg="${action.getText('onTheRoadBill.endTime.dateFormate.error')}";
	    if(queryDate("onTheRoadBill.endTime_start","onTheRoadBill.endTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('onTheRoadBill.endTime.dateFormate.error')}";
	    if(queryDate("onTheRoadBill.endTime_start","onTheRoadBill.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
		return true;
    }
</script>