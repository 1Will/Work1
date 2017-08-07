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

<#-- $Id: organizationProfile.ftl 2010-01-26 wliu $ -->

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('organization.profile')}">
<@ww.form namespace="'/organizationOperate'" name="'organizationInfo'" action="'saveOrg'" method="'post'">
 <@ww.token name="saveOrgToken"/>
    <@inputTable>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if org.id?exists>
            <@ww.hidden name="'org.id'" value="#{org.id}"/>
        </#if>
	 	<tr>
	 		<#if org.new>
            	<@textfield anothername="checkCode" label="${action.getText('organization.code')}" name="org.code" value="${org.code?if_exists}" cssClass="underline" required="true" maxlength="20"/>
            <#else>
            	<@textfield anothername="checkCode" label="${action.getText('organization.code')}" name="org.code" value="${org.code?if_exists}" cssClass="underline" required="true" readonly="true" disabled="true" maxlength="20"/>
            </#if>
            <@textfield anothername="checkName" label="${action.getText('organization.name')}" name="org.name" value="${org.name?if_exists}" cssClass="underline" required="true" maxlength="20"/>
        </tr>
        <tr>
        	<@textfield anothername="checkLeader" label="${action.getText('organization.leader')}" name="org.leader" value="${org.leader?if_exists}" required="true" cssClass="underline" maxlength="20"/>
        	<@select 
        		anothername="selectCheckIndustry"
        		label="${action.getText('organization.industry')}" 
				name="industry.id" 
				value="${req.getParameter('industry.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allIndustry"
				required="false"
				emptyOption="true" 
				disabled="false">
			</@select>
		</tr>
		<tr>
			<@select 
				anothername="selectCheckCompanyNature"
        		label="${action.getText('organization.nature')}" 
				name="nature.id" 
				value="${req.getParameter('nature.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allNature"
				required="false"
				emptyOption="true" 
				disabled="false">
			</@select>
			<@textfield anothername="checkLegalPerson" label="${action.getText('organization.legalPerson')}" name="org.legalPerson" value="${org.legalPerson?if_exists}" cssClass="underline" maxlength="20"/>
		</tr>
        <tr>
        	<@textfield anothername="checkPhone" label="${action.getText('organization.tel')}" name="org.tel" value="${org.tel?if_exists}" required="true" cssClass="underline" maxlength="20"/>
        	<@textfield anothername="checkPostCode" label="${action.getText('organization.postCode')}" name="org.postCode" value="${org.postCode?if_exists}" type="P" cssClass="underline" maxlength="20"/>
        </tr>
        <tr>	
        	<@textfield anothername="checkEmail" label="${action.getText('organization.email')}" name="org.email" value="${org.email?if_exists}" type="E" cssClass="underline" maxlength="50"/>
        	<@textfield anothername="checkWebsite" label="${action.getText('organization.website')}" name="org.website" value="${org.website?if_exists}" cssClass="underline" maxlength="50"/>
        </tr>
        <tr>
        	<@textfield anothername="checkFax" label="${action.getText('organization.fax')}" name="org.fax" value="${org.fax?if_exists}" type="P" cssClass="underline" maxlength="50"/>
			<@datePickerRanger
				anothername="dateCheckSetupTime"
        		label="${action.getText('organization.setupTime')}"
	           	name="org.setupTime"
	     		value="${(org.setupTime?string('yyyy-MM-dd'))?if_exists}" 
				cssClass="underline" 
				maxlength="10" 
				flag="true" />
        </tr>
        <@text
			label="${action.getText('organization.address')}"
    		colspan="10"
    		name="org.address"
    		value="${org.address?if_exists}"
    		size="95"
    		maxLength="100" />
        
        <@textarea 
        	label="${action.getText('organization.comment')}"
        	name="org.comment"
        	rows="4"
        	cols="150"
        	value="${org.comment?if_exists}" />
    </@inputTable>
    <@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>	          
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/organizationOperate/listOrg.html"/>
	</@buttonBar>	

</@ww.form>

<script type="text/javascript">

	window.onload = function () {
		//所属行业
	    <#if org.industry?exists>
			getObjByName('industry.id').value='${org.industry.id?if_exists}';
		</#if>
		//单位性质
	    <#if org.nature?exists>
			getObjByName('nature.id').value='${org.nature.id?if_exists}';
		</#if>
	}
	
 	function storeValidation(){
 	
		//验证编码
		if(!textfieldCheck_checkCode()){
		getObjByName('org.code').focus();
			return false;
		}
	
		//验证名称
		if(!textfieldCheck_checkName()){
		getObjByName('org.name').focus();
			return false;
		}
	
		//验证机构负责人
		if(!textfieldCheck_checkLeader()){
		getObjByName('org.leader').focus();
			return false;
		}
	
		//验证办公电话
		if(!textfieldCheck_checkPhone()){
			getObjByName('org.tel').focus();
			return false;
		}
	
		//验证邮编
		if(!textfieldCheck_checkPostCode()){
			getObjByName('org.postCode').focus();
			return false;
		}
		
		//验证电子邮件
		if(!textfieldCheck_checkEmail()){
			getObjByName('org.email').focus();
			return false;
		}
	
		//验证传真
		if(!textfieldCheck_checkFax()){
			getObjByName('org.fax').focus();
			return false;
		}
	
		//验证创立时间
		if(!dateCheck_dateCheckSetupTime()){
			getObjByName('org.setupTime').focus();
			return false;
		}
	
		return true;	
 	}
</script>
</@htmlPage>