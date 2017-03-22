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
		<@ww.textfield label="'${action.getText('编号')}'" name="'qaStorage.code'" value="'${req.getParameter('qaStorage.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('问题')}'" name="'qaStorage.question'" value="'${req.getParameter('qaStorage.question')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('版本号')}'" name="'qaStorage.versionNumber'" value="'${req.getParameter('qaStorage.versionNumber')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.select label="'${action.getText('类型')}'" 
				id="type"
				name="'qaStorage.type.id'" 
				value="'${req.getParameter('qaStorage.type.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
		<@ww.select label="'${action.getText('状态')}'" 
				id="state"
				name="'qaStorage.state.id'" 
				value="'${req.getParameter('qaStorage.state.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('严重程度')}'" 
				id="severityDegree"
				name="'qaStorage.severityDegree.id'" 
				value="'${req.getParameter('qaStorage.severityDegree.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allSeverityDegree"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
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
    	<#if req.getParameter('qaStorage.type.id')?exists>
    		jgetObjByName("#type").val("${req.getParameter('qaStorage.type.id')?if_exists}");
    	</#if>
    	<#if req.getParameter('qaStorage.state.id')?exists>
    		jgetObjByName("#state").val("${req.getParameter('qaStorage.state.id')?if_exists}");
    	</#if>
    	<#if req.getParameter('qaStorage.severityDegree.id')?exists>
    		jgetObjByName("#severityDegree").val("${req.getParameter('qaStorage.severityDegree.id')?if_exists}");
    	</#if>
    	
    });
</script>