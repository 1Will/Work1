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

<#-- $Id: cityProfile.ftl 2009-11-26 17:54:35Z wliu $ -->

<#include "../../../../includes/macros.ftl" />

<@htmlPage title="城市信息管理维护">
<@ww.form name="'listForm'" action="'saveCity_'" method="'post'">
	<@ww.token name="saveCityToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'城市编码'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'城市名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.select 
    		label="'省份'"
			required="true"
			name="'province'" 
			value="${req.getParameter('province')?if_exists}" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'安徽省',
				'福建省',
				'广东省',
				'黑龙江省',
				'贵州省'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
		</tr>
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
	</#if>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/cityManager_/listCity_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>