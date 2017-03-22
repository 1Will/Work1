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

<#-- $Id: ceSearcher.ftl 2009-11-27 14:20:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'客户名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		<@ww.select 
    		label="'客户类型'"
			required="false"
			name="'customerType'" 
			value="${req.getParameter('customerType')?if_exists}" 
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
		<@ww.textfield label="'联系人'" name="'contactPerson'" value="'${req.getParameter('contactPerson')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'客服人员'" name="'customerService'" value="'${req.getParameter('customerService')?if_exists}'" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'咨询时间'" 
			name="'consultationTime'" 
   			value="'${req.getParameter('consultationTime')?if_exists}'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	</tr>
</@inputTable>