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
<#--$Id: roleInfo.ftl 6148 2007-07-31 01:41:34Z qsun $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('warnningRule.title')}">
 <@ww.form namespace="'/base/warnningRule'" name="'warnningRule'" action="'saveWarnningRule'" method="'post'" validate="true">
 <@ww.token name="saveWarnningRuleToken"/>
    <@inputTable>
        <#if warnningRule.id?exists>
            <@ww.hidden name="'warnningRule.id'" value="#{warnningRule.id}"/>
        </#if>
        <tr>
            <@ww.textfield label="'${action.getText('warnningRule.code')}'"  name="'warnningRule.code'" value="'${warnningRule.code?if_exists}'" cssClass="'underline'" required="true" disabled="true">
            </@ww.textfield>
            <@ww.textfield label="'${action.getText('warnningRule.typeName')}'"  name="'warnningRule.typeName'" value="'${warnningRule.typeName?if_exists}'" cssClass="'underline'" required="true" disabled="true"/>
        </tr>
    </@inputTable>
     <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
            <#--
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('role')}[${role.name?if_exists}]?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${(!role.id?exists || role.advanced)?string}"/>
            </@vsubmit>
            -->
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/warnningRule/listWarnningRules.html"/>
      </@buttonBar>
      <#if !warnningRule.new>
        <@titleBar title="${action.getText('user.granted')}"/>
        <@listTable>
            <tr>
                <th></th>
                <th>${action.getText('user.loginName')}</th>
                <th>${action.getText('user.name')}</th>
            </tr>
            <#list warnningRule.users as user>
                <tr>
                    <td>
                        <input type="checkbox" name="userIds" value="#{user.id}" width="30" />
                    </td>
                    <td style="text-align:left">${user.loginName}</td>
                    <td style="text-align:left">${user.name}</td>
                </tr>
            </#list>
        </@listTable>
        <@buttonBar>
            <select name="user.id">
                <#list grantableUsers as grantableUser>
                    <option value="#{grantableUser.id}">${grantableUser.name}</option>
                </#list>
            </select>
            <@vsubmit name="'grant_user'" value="'${action.getText('grant')}'">
                <@ww.param name="'disabled'" value="${grantableUsers.isEmpty()?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('user')}?" />
            <@vsubmit name="'revoke_user'" value="'${action.getText('revoke')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"userIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${warnningRule.users.isEmpty()?string}"/>
            </@vsubmit>
        </@buttonBar>
      </#if>
<#--
    <script type="text/javascript">
        document.forms["warnningRule"].elements["warnningRule.code"].focus();
    </script>
    -->
 </@ww.form>
</@htmlPage>