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

<#-- $Id: nationalProfile.ftl 2009-11-26 17:11:35Z wliu $ -->

<#include "../../../../includes/macros.ftl" />

<@htmlPage title="国家信息管理维护">
<@ww.form name="'listForm'" action="'saveNational_'" method="'post'">
	<@ww.token name="saveNationalToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'国家编码'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'国家名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" required="true"/>
		</tr>
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
	</#if>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/nationalManager_/listNational_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>