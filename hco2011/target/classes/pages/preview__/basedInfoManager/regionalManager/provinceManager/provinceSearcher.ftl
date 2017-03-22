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

<#-- $Id: provinceSearcher.ftl 2009-11-26 17:28:35Z wliu $ -->

<#include "../../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'省份编码'" name="'code'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'省份名称'" name="'name'" value="''" cssClass="'underline'"/>
		<@ww.select 
    		label="'国家'"
			required="false"
			name="'national'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'中国',
				'俄罗斯',
				'美国',
				'英国',
				'法国'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
</@inputTable>