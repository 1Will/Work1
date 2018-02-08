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
		<@ww.textfield label="'联系人姓名'" name="'name'" value="''" cssClass="'underline'"/>
		<@ww.select 
    		label="'性别'"
			required="false"
			name="'sex'" 
			value="selectedSex" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'男',
				'女'
				}"
		    emptyOption="false" 
		    disabled="false"/>
		<@ww.textfield label="'客户名称'" name="'name'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
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
</@inputTable>