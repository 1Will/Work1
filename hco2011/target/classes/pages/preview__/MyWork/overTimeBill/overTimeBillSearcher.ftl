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
		<@ww.textfield label="'编号'" name="'code'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'创建日期'" 
			name="'createTime'" 
   			value="'${req.getParameter('createTime')?if_exists}'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
		<@ww.textfield label="'申请人'" name="'applyPerson'" value="'${req.getParameter('applyPerson')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.select 
    		label="'部门'"
			required="false"
			name="'dept'" 
			value="${req.getParameter('dept')?if_exists}" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'研发部',
				'销售部',
				'管理部'
				}"
		    emptyOption="true" 
		    disabled="false"/>
	    <@ww.select 
    		label="'状态'"
			required="true"
			name="'state'" 
			value="state" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'新建',
				'通过',
				'未通过'
				}"
		    emptyOption="false" 
		    disabled="false"/>
	</tr>
</@inputTable>