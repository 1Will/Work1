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
 		<@ww.textfield label="'${action.getText('voc.code')}'" name="'voc.code'" value="'${req.getParameter('voc.code')?if_exists}'" cssClass="'underline'" />
		<@pp.dateRanger label="'${action.getText('voc.connectDate')}'" 
 			name="'voc.connectDate'" 
		    value="'${req.getParameter('voc.connectDate_start')?if_exists}|${req.getParameter('voc.connectDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
		<@ww.textfield label="'${action.getText('voc.title')}'" name="'voc.title'" value="'${req.getParameter('voc.title')?if_exists}'" cssClass="'underline'"/>
	
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('voc.customerInfo')}'" name="'voc.customerInfo'" value="'${req.getParameter('voc.customerInfo')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('voc.supplier')}'" name="'voc.supplier'" value="'${req.getParameter('voc.supplier')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('voc.importance')}'" 
			name="'importance.id'" 
			value="'${req.getParameter('importance.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allImportances"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
		<@ww.select label="'${action.getText('voc.type')}'" 
			name="'type.id'" 
			value="'${req.getParameter('type.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allTypes"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@ww.textfield label="'${action.getText('voc.salesman')}'" name="'voc.salesman'" value="'${req.getParameter('voc.salesman')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('voc.principal')}'" name="'voc.principal'" value="'${req.getParameter('voc.principal')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.select label="'${action.getText('voc.status')}'" 
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
		<#if req.getParameter('type.id')?exists>
			getObjByName('type.id').value='${req.getParameter('type.id')?if_exists}';
		</#if>
		<#if req.getParameter('status.id')?exists>
			getObjByName('status.id').value='${req.getParameter('status.id')?if_exists}';
		</#if>
		<#if req.getParameter('status.id')?exists>
			getObjByName('importance.id').value='${req.getParameter('importance.id')?if_exists}';
		</#if>
		}
	function checkInvalidParms(){
		if (getObjByName('importance.id').value==-1){
			getObjByName('importance.id').value='';
		}
		if (getObjByName('status.id').value==-1){
			getObjByName('status.id').value='';
		}
		if (getObjByName('type.id').value==-1){
			getObjByName('type.id').value='';
		}
		
		var beginDateMsg="${action.getText('voc.connectDate.dateFormate.error')}";
	    if(queryDate("voc.connectDate_start","voc.connectDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('voc.connectDate.dateFormate.error')}";
	    if(queryDate("voc.connectDate_start","voc.connectDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('voc.connectDate_start').value>getObjByName('voc.connectDate_end').value){
				alert('${action.getText('voc.connectDate.earlyError')}');
				getObjByName('voc.connectDate_end').value="";
	       		getObjByName('voc.connectDate_end').focus();
				return false;
			}
		return true;
    }
</script>