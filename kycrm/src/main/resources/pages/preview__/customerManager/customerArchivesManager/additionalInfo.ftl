<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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
<#-- $Id: additionalInfo.ftl  2009-11-26 11:04:50Z wliu $-->

<#include "../../../includes/macros.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'listAdditionalInfo_'" method="'post'">
		<@ww.token name="listAdditionalInfoToken"/>
		<@inputTable>
		<tr>
			<@ww.textfield label="'营业执照'" name="'license'" value="'license-number'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'税号'" name="'tax'" value="'tax-number'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'营业额'" name="'turnover'" value="'1234578'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@ww.textfield label="'开户行'" name="'bank'" value="'l0000000'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'开户行账号'" name="'bankAccount'" value="'10101010'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'网址'" name="'website'" value="'www.yj-technology.com'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@ww.textarea label="'备注'" name="'comment'" value="''" rows="'3'" />
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("comment").cols =width;
			</script>
		</tr>
		</@inputTable>
		<@buttonBar>
			<#--<@vbutton name="'add'"  class="button" value="${'新建'}" onclick=""/>-->
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick=""/>
            <#--<#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>-->
        </@buttonBar>
    </@ww.form>
</@framePage>
