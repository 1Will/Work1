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

<#-- $Id: competitorSearcher.ftl 2011-02-24 11:00:35Z hmguan $ -->

<@inputTable>
	<tr>
        <@textfield label="${action.getText('competitor.code')}" name="competitor.code" anothername="code" value="${req.getParameter('competitor.code')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
        <@textfield label="${action.getText('competitor.companyName')}" name="competitor.companyName" anothername="companyName" value="${req.getParameter('competitor.companyName')?if_exists}" required="false" cssClass="underline" maxlength="20"/>
		<@ww.select label="'${action.getText('competitor.industry')}'" 
			name="'industry.id'" 
			value="'${req.getParameter('industry.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allIndustry"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
 	</tr>
 	<tr>
		<@ww.select label="'${action.getText('competitor.nature')}'" 
			name="'nature.id'" 
			value="'${req.getParameter('nature.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allNature"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
        <@textfield label="${action.getText('competitor.business')}" name="competitor.business" anothername="business" value="${req.getParameter('competitor.business')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
        <@textfield label="${action.getText('competitor.targetMarket')}" name="competitor.targetMarket" anothername="targetMarket" value="${req.getParameter('competitor.targetMarket')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
	</tr>
	<tr>
        <@textfield label="${action.getText('competitor.customerInfo')}" name="competitor.customerInfo" anothername="customerInfo" value="${req.getParameter('competitor.customerInfo')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
        <@textfield label="${action.getText('competitor.products')}" name="competitor.products" anothername="products" value="${req.getParameter('competitor.products')?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
		<@crm_onlySearchInvalid_checkBox />
	<tr>
		
</@inputTable>
<script language="javascript">
	window.onload=function(){
		<#if req.getParameter('industry.id')?exists>
			getObjByName('industry.id').value='${req.getParameter('industry.id')?if_exists}';
		</#if>
		<#if req.getParameter('nature.id')?exists>
			getObjByName('nature.id').value='${req.getParameter('nature.id')?if_exists}';
		</#if>
	}
	function checkInvalidParms(){
		if (getObjByName('industry.id').value==-1){
			getObjByName('industry.id').value='';
		}
		if (getObjByName('nature.id').value==-1){
			getObjByName('nature.id').value='';
		}
		return true;
    }
</script>