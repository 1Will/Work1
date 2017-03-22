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

<#-- $Id: customerInfoSearcher.ftl 2009-12-10 15:50:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('salaryStandard.code')}'" name="'salaryStandard.code'" value="'${req.getParameter('salaryStandard.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('salaryStandard.positionName')}'" name="'salaryStandard.positionName'" value="'${req.getParameter('salaryStandard.positionName')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('salaryStandard.salaryName')}'" name="'salaryStandard.salaryName'" value="'${req.getParameter('salaryStandard.salaryName')?if_exists}'" cssClass="'underline'" />
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	
	function checkInvalidParms(){
	
		return true;
    }
  jgetObjByName(function(){
  
<#--   	<#if req.getParameter('salaryStandard.contractType.id')?exists>
    		jgetObjByName("#contractType").val("${req.getParameter('salaryStandard.contractType.id')?if_exists}");
    	</#if>
    	<#if req.getParameter('salaryStandard.state.id')?exists>
    		jgetObjByName("#state").val("${req.getParameter('salaryStandard.state.id')?if_exists}");
    	</#if>
    	-->
    	
  });
</script>