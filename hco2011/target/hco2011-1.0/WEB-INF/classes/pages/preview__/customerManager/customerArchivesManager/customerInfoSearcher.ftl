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

<#-- $Id: customerInfoSearcher.ftl 2009-11-26 8:32:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'客户编码'" name="'code'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户名称'" name="'name'" value="''" cssClass="'underline'"/>
		<@ww.select 
    		label="'客户类型'"
			required="false"
			name="'customerType'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'潜在',
				'意向',
				'已签'
				}"
		    emptyOption="false" 
		    disabled="false"/>
	</tr>
	<tr>
		<@ww.select 
    		label="'行业'"
			required="false"
			name="'industryType'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'IT行业',
				'金融业',
				'服务业',
				'养殖业'
				}"
		    emptyOption="false" 
		    disabled="false"/>
		    
		<@ww.select 
    		label="'企业性质'"
			required="false"
			name="'enterprisePropertyType'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'国营',
				'民营'
				}"
		    emptyOption="false" 
		    disabled="false"/>

		<@ww.select 
    		label="'省份'"
			required="false"
			name="'provinceType'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'安徽省',
				'广东省',
				'福建省',
				'上海市',
				'北京市'
				}"
		    emptyOption="false" 
		    disabled="false"/>
	</tr>
	<tr>
		<@ww.textfield label="'主要联系人'" name="'keyPerson'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'企业法人'" name="'legalPerson'" value="''" cssClass="'underline'"/>
	</tr>
</@inputTable>