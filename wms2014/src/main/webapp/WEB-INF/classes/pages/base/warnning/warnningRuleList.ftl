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
<#--$Id: roleList.ftl 6148 2007-07-31 01:41:34Z qsun $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('warnningRuleList.title')}">
  <@ww.form namespace="'/base/warnningRule'" name="'listWarnningRule'" action="'searchWarnningRules'" method="'get'">
     <@ww.token name="searchWarnningRulesToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('warnningRule.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('warnningRule.typeName')}'" name="'typeName'" value="'${req.getParameter('typeName')?if_exists}'" cssClass="'underline'"/>
            </tr>
        </@inputTable>
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <#--
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/warnningRule/editWarnningRule.html"/>
            -->
        </@buttonBar>
         <@list title="${action.getText('warnningRule.list')}" includeParameters="code|typeName" fieldMap="like:code|typeName" >
            <#--
            <@vlh.checkbox property="id" name="warnningRuleIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            -->
            <@vcolumn title="${action.getText('warnningRule.code')}">
                <a href="editWarnningRule.html?warnningRule.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('warnningRule.typeName')}" property="typeName">
              <@alignLeft/>
            </@vcolumn>
         </@list>
         <#--
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('warnningRule')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"warnningRuleIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
      </#if>
      -->
    </@ww.form>
</@htmlPage>
