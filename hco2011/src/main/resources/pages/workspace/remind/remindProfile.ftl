<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('remind.title')}">
 <@ww.form namespace="'/remind'" name="'remind'" action="'saveRemind'" method="'post'" validate="true">
 <@ww.token name="saveRemindToken"/>
    <@inputTable>
        <#if remind.id?exists>
            <@ww.hidden name="'remind.id'" value="#{remind.id}"/>
        </#if>
        <tr>
        	<#if remind.id?exists>
        		<@ww.textfield label="'${action.getText('remind.code')}'"  name="'remind.code'" value="'${remind.code?if_exists}'" cssClass="'underline'" required="true" disabled="true"/>
            	<@ww.textfield label="'${action.getText('remind.type')}'"  name="'remind.type'" value="'${remind.type?if_exists}'" cssClass="'underline'" required="true" disabled="true"/>
        	<#else>
        		<@ww.textfield label="'${action.getText('remind.code')}'"  name="'remind.code'" value="'${remind.code?if_exists}'" cssClass="'underline'" required="true"/>
            	<@ww.textfield label="'${action.getText('remind.type')}'"  name="'remind.type'" value="'${remind.type?if_exists}'" cssClass="'underline'" required="true"/>
        	</#if>
            <#if remind.type == "回访提醒">
            <@ww.textfield label="'${action.getText('提前提醒小时')}'"  name="'remind.days'" value="'${remind.days?if_exists}'" cssClass="'underline'" required="true"/>
       		<#else>
            <@ww.textfield label="'${action.getText('remind.days')}'"  name="'remind.days'" value="'${remind.days?if_exists}'" cssClass="'underline'" required="true"/>
        	</#if>
        </tr>
    </@inputTable>
     <@buttonBar>
     <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
     </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/remind/listRemind.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
      </@buttonBar>
 </@ww.form>
</@htmlPage>