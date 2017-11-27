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
 		<@ww.textfield label="'${action.getText('rejection.code')}'" name="'rejection.code'" value="'${req.getParameter('rejection.code')?if_exists}'" cssClass="'underline'" />
 		<@ww.textfield label="'${action.getText('rejection.customerInfo')}'" name="'rejection.customerInfo'" value="'${req.getParameter('rejection.customerInfo')?if_exists}'" cssClass="'underline'"/>
		<@pp.dateRanger label="'${action.getText('rejection.rejectionDate')}'" 
 			name="'rejection.rejectionDate'" 
		    value="'${req.getParameter('rejection.rejectionDate_start')?if_exists}|${req.getParameter('rejection.rejectionDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/>
	
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('rejection.products')}'" name="'rejection.products'" value="'${req.getParameter('rejection.products')?if_exists}'" cssClass="'underline'"/>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('rejection.rejectionDate.dateFormate.error')}";
	    if(queryDate("rejection.rejectionDate_start","rejection.rejectionDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('rejection.rejectionDate.dateFormate.error')}";
	    if(queryDate("rejection.rejectionDate_start","rejection.rejectionDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('rejection.rejectionDate_start').value>getObjByName('rejection.rejectionDate_end').value){
				alert('${action.getText('rejection.rejectionDate.earlyError')}');
				getObjByName('rejection.rejectionDate_end').value="";
	       		getObjByName('rejection.rejectionDate_end').focus();
				return false;
			}
		return true;
    }
</script>