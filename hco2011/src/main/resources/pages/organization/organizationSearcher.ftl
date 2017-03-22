<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: organizationSearcher.ftl 2010-01-26 wliu $ -->

<#include "../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@textfield anothername="checkCode" label="${action.getText('organization.code')}" name="code" value="${code?if_exists}" cssClass="underline" />
		<@textfield anothername="checkName" label="${action.getText('organization.name')}" name="name" value="${name?if_exists}" cssClass="underline" />
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script>
	function checkInvalidParms(){
		return true;
	}
</script>