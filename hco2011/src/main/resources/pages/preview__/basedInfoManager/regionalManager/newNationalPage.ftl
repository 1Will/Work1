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

<#-- $Id: newNationalPage.ftl 2009-11-30 9:00:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'editNational_'" method="'post'">
	<@ww.token name="editNationalToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'国家编码'" name="'code'" value="'national_001'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'国家名称'" name="'name'" value="'中国'" cssClass="'underline'" required="true"/>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@redirectButton value="${'新建'}" url="${req.contextPath}/nationalManager_/saveNational_.html"/>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'新建省份'}" url="${req.contextPath}/provinceManager_/saveProvince_.html"/>
    </@buttonBar>
</@ww.form>
</@framePage>