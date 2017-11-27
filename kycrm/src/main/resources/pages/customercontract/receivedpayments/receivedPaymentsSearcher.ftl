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
 		<@ww.textfield label="'${action.getText('receivedPayments.payee')}'" name="'receivedPayments.payee'" value="'${req.getParameter('receivedPayments.payee')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('receivedPayments.contractManagement')}'" name="'receivedPayments.contractManagement'" value="'${req.getParameter('receivedPayments.contractManagement')?if_exists}'" cssClass="'underline'"/>
		<@pp.dateRanger label="'${action.getText('receivedPayments.paytime')}'" 
 			name="'receivedPayments.paytime'" 
		    value="'${req.getParameter('receivedPayments.paytime_start')?if_exists}|${req.getParameter('receivedPayments.paytime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
	</tr>
	<tr>
	<#--
		<@ww.select label="'${action.getText('receivedPayments.payment')}'" 
			name="'payment.id'" 
			value="'${req.getParameter('payment.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allPayment"
			emptyOption="false" 
			disabled="false">
		</@ww.select>-->
		<td align="right"><label for="" class="label">${action.getText('receivedPayments.isOrNot')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="isOrNot0" name="isOrNot" value="0" />是
	        	<input type="radio" id="isOrNot1" name="isOrNot" value="1" />否
	        	<input type="radio" id="isOrNot2" name="isOrNot" value="2" checked />所有
			</td>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	window.onload=function(){
		<#if req.getParameter('payment.id')?exists>
			getObjByName('payment.id').value='${req.getParameter('payment.id')?if_exists}';
		</#if>
		}
		
	<#if req.getParameter('isOrNot')?exists>
    	if(${req.getParameter('isOrNot')?if_exists}=='0'){
			getObjByName('isOrNot0').checked=true;
		}else if(${req.getParameter('isOrNot')?if_exists}=='1'){
			getObjByName('isOrNot1').checked=true;
		}else{
			getObjByName('isOrNot2').checked=true;
		}
	</#if>
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('receivedPayments.paytime.dateFormate.error')}";
	    if(queryDate("receivedPayments.paytime_start","receivedPayments.paytime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('receivedPayments.paytime.dateFormate.error')}";
	    if(queryDate("receivedPayments.paytime_start","receivedPayments.paytime_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
	    if(getObjByName('receivedPayments.paytime_start').value>getObjByName('receivedPayments.paytime_end').value){
				alert('${action.getText('receivedPayments.paytime.earlyError')}');
				getObjByName('receivedPayments.paytime_end').value="";
	       		getObjByName('receivedPayments.paytime_end').focus();
				return false;
			}
		return true;
    }
</script>