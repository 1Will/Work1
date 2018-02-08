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
 		<@ww.textfield label="'${action.getText('contractCustomer.code')}'" name="'contractCustomer.code'" value="'${req.getParameter('contractCustomer.code')?if_exists}'" cssClass="'underline'" />
 		<@ww.textfield label="'${action.getText('contractCustomer.name')}'" name="'contractCustomer.name'" value="'${req.getParameter('contractCustomer.name')?if_exists}'" cssClass="'underline'" />
 		<@ww.textfield label="'${action.getText('contractCustomer.customerInfo')}'" name="'contractCustomer.customerInfo'" value="'${req.getParameter('contractCustomer.customerInfo')?if_exists}'" cssClass="'underline'" />
 	</tr>
 	<tr>
		<@pp.dateRanger label="'${action.getText('contractCustomer.affixTime')}'" 
 			name="'contractCustomer.affixTime'" 
		    value="'${req.getParameter('contractCustomer.affixTime_start')?if_exists}|${req.getParameter('contractCustomer.affixTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
		<@ww.textfield label="'${action.getText('contractCustomer.products')}'" name="'contractCustomer.products'" value="'${req.getParameter('contractCustomer.products')?if_exists}'" cssClass="'underline'"/>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('contractCustomer.affixTime.dateFormate.error')}";
	    if(queryDate("contractCustomer.affixTime_start","contractCustomer.affixTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('contractCustomer.affixTime.dateFormate.error')}";
	    if(queryDate("contractCustomer.affixTime_start","contractCustomer.affixTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('contractCustomer.affixTime_start').value>getObjByName('contractCustomer.affixTime_end').value){
				alert('${action.getText('contractCustomer.affixTime.earlyError')}');
				getObjByName('contractCustomer.affixTime_end').value="";
	       		getObjByName('contractCustomer.affixTime_end').focus();
				return false;
			}
		return true;
    }
</script>