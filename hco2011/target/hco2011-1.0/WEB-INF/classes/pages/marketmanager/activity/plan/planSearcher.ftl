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

<#-- $Id: planSearcher.ftl 2011-02-22 11:00:35Z hmguan $ -->

<@inputTable>
	<tr>
 		<@ww.textfield label="'${action.getText('plan.code')}'" name="'plan.code'" value="'${req.getParameter('plan.code')?if_exists}'" cssClass="'underline'" />	            
 		<@pp.dateRanger label="'${action.getText('plan.planTime')}'" 
 						name="'plan.planTime'" 
			            value="'${req.getParameter('plan.planTime_start')?if_exists}|${req.getParameter('plan.planTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            maxlength="10"/>    
		<@ww.textfield label="'${action.getText('plan.persons')}'" name="'plan.persons'" value="'${req.getParameter('plan.persons')?if_exists}'" cssClass="'underline'" />	                  
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	<tr>
		
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
        var dateMsg="${action.getText('plan.planTime.error')}";
	    if(queryDate("plan.planTime_start","plan.planTime_end",
	       dateMsg,null)==false){   
	   	   return false;
	    }	 
		return true;
    }
</script>