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

<#include "../../includes/hco2011.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('personnelCasting.code')}'" name="'personnelCasting.code'" value="'${req.getParameter('personnelCasting.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('personnelCasting.name')}'" name="'personnelCasting.name'" value="'${req.getParameter('personnelCasting.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('personnelCasting.fileNo')}'" name="'personnelCasting.fileNo'" value="'${req.getParameter('personnelCasting.fileNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('personnelCasting.inst')}'" name="'personnelCasting.inst'" value="'${req.getParameter('personnelCasting.inst')?if_exists}'" cssClass="'underline'" />
	</tr>	
	<tr>
		<@ww.textfield label="'${action.getText('personnelCasting.dept')}'" name="'personnelCasting.dept'" value="'${req.getParameter('personnelCasting.dept')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('personnelCasting.duty')}'" name="'personnelCasting.duty'" value="'${req.getParameter('personnelCasting.duty')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>	
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
	
		return true;
    }
  jgetObjByName(function(){
  
 <#--  	<#if req.getParameter('personnelCasting.produceType.id')?exists>
    		jgetObjByName("#produceType").val("${req.getParameter('personnelCasting.produceType.id')?if_exists}");
    	</#if>
    	-->
    <#--	<#if req.getParameter('personnelCasting.state.id')?exists>
    		jgetObjByName("#state").val("${req.getParameter('personnelCasting.state.id')?if_exists}");
    	</#if>
    	-->
  });
</script>