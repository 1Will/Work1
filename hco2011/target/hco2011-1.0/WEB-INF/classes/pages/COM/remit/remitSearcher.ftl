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
 		<@ww.textfield label="'${action.getText('remit.code')}'" name="'remit.code'" value="'${req.getParameter('remit.code')?if_exists}'" cssClass="'underline'" />
		<@pp.dateRanger label="'${action.getText('remit.remitDate')}'" 
 			name="'remit.remitDate'" 
		    value="'${req.getParameter('remit.remitDate_start')?if_exists}|${req.getParameter('remit.remitDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
			
		<@ww.select label="'${action.getText('remit.type')}'" 
			name="'type.id'" 
			value="'${req.getParameter('type.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allTypes"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('remit.customerInfo')}'" name="'remit.customerInfo'" value="'${req.getParameter('remit.customerInfo')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('remit.billing')}'" 
			name="'billing.id'" 
			value="'${req.getParameter('billing.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allBillings"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	window.onload=function(){
		<#if req.getParameter('deliveryWay.id')?exists>
			getObjByName('billing.id').value='${req.getParameter('billing.id')?if_exists}';
		</#if>
		<#if req.getParameter('type.id')?exists>
			getObjByName('type.id').value='${req.getParameter('type.id')?if_exists}';
		</#if>
		}
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('remit.remitDate.dateFormate.error')}";
	    if(queryDate("remit.remitDate_start","remit.remitDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('remit.remitDate.dateFormate.error')}";
	    if(queryDate("remit.remitDate_start","remit.remitDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('remit.remitDate_start').value>getObjByName('remit.remitDate_end').value){
				alert('${action.getText('remit.remitDate.earlyError')}');
				getObjByName('remit.remitDate_end').value="";
	       		getObjByName('remit.remitDate_end').focus();
				return false;
			}
		if (getObjByName('billing.id').value==-1){
			getObjByName('billing.id').value='';
		}
		if (getObjByName('type.id').value==-1){
			getObjByName('type.id').value='';
		}
		return true;
    }
</script>