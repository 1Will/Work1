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
	<tr>
 		<@ww.textfield label="'${action.getText('co.code')}'" name="'co.code'" value="'${req.getParameter('co.code')?if_exists}'" cssClass="'underline'" />
 		<@ww.textfield label="'${action.getText('co.customerInfo')}'" name="'co.customerInfo'" value="'${req.getParameter('co.customerInfo')?if_exists}'" cssClass="'underline'"/>
 		<@ww.select label="'${action.getText('co.type')}'" 
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
		<@pp.dateRanger label="'${action.getText('co.deliveryTime')}'" 
 			name="'co.deliveryTime'" 
		    value="'${req.getParameter('co.deliveryTime_start')?if_exists}|${req.getParameter('co.deliveryTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
		<@ww.textfield label="'${action.getText('co.products')}'" name="'co.products'" value="'${req.getParameter('co.products')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('co.deliveryWay')}'" 
			name="'deliveryWay.id'" 
			value="'${req.getParameter('deliveryWay.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allDeliveryWays"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('co.linkman')}'" name="'co.linkman'" value="'${req.getParameter('co.linkman')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('co.phone')}'" name="'co.phone'" value="'${req.getParameter('co.phone')?if_exists}'" cssClass="'underline'"/>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
window.onload=function(){
		<#if req.getParameter('deliveryWay.id')?exists>
			getObjByName('deliveryWay.id').value='${req.getParameter('deliveryWay.id')?if_exists}';
		</#if>
		<#if req.getParameter('type.id')?exists>
			getObjByName('type.id').value='${req.getParameter('type.id')?if_exists}';
		</#if>
		}
	function checkInvalidParms(){
		if (getObjByName('deliveryWay.id').value==-1){
			getObjByName('deliveryWay.id').value='';
		}
		if (getObjByName('type.id').value==-1){
			getObjByName('type.id').value='';
		}
		
		var beginDateMsg="${action.getText('co.deliveryTime.dateFormate.error')}";
	    if(queryDate("co.deliveryTime_start","co.deliveryTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('co.deliveryTime.dateFormate.error')}";
	    if(queryDate("co.deliveryTime_start","co.deliveryTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('co.deliveryTime_start').value>getObjByName('co.deliveryTime_end').value){
				alert('${action.getText('co.endTime.earlyError')}');
				getObjByName('co.deliveryTime_end').value="";
	       		getObjByName('co.deliveryTime_end').focus();
				return false;
			}
		return true;
    }
</script>