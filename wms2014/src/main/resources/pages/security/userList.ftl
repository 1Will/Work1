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
<#--$Id: userList.ftl 6134 2007-07-30 10:05:53Z qsun $ -->
<#include "../includes/macros.ftl" />

<@htmlPage >
  <@ww.form namespace="'/security'" name="'listForm'" action="'searchUsers'" method="'get'">
     <@ww.token name="searchUsersToken"/>
     <@ww.hidden name="'hiddenexcludedisabled'" value=""/>
        <@inputTable>
        <#--
            <tr>
                <@ww.textfield label="'${action.getText('user.loginName')}'" name="'loginName'" value="'${req.getParameter('loginName')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('user.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
            </tr>
            <tr>
                <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    				  value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
        	          list="departments" emptyOption="true" disabled="false">
	      		</@ww.select>
                <@ww.checkbox label="'${action.getText('excludeDisabled')}'" name="'exclude_disabled'" value="'true'" fieldValue="'true'"/>
            </tr>
        -->
             <#include "userSearcher.ftl"/>
        </@inputTable>
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/security/editUser.html"/>
        </@buttonBar>
         <@list title="${action.getText('list.title')}" includeParameters="exclude_disabled|loginName|name|filiale.id|department.id|user.type" fieldMap="like:loginName|name" >
            <@vcolumn title="">
                <input type="checkbox" name="userIds" value="#{object.id}" width="30" <#if !object.enabled || (loginUser.id==object.id)>disabled="disabled"</#if> />
            </@vcolumn>
            <@vcolumn title="${action.getText('user.loginName')}">
            <#--
                <#if !object.enabled>
                    ${object.loginName}
                <#else>
            -->
                    <a href="editUser.html?user.id=#{object.id}">${object.loginName}</a>
            <#--
                </#if>
            -->
            </@vcolumn>
            <@vcolumn title="${action.getText('user.name')}" property="name"/>
            <@vcolumn title="${action.getText('user.locale')}">
                ${object.locale.getDisplayName(action.locale)}
            </@vcolumn>           
            <@vcolumn title="${action.getText('filiale')}" property="filiale.name"/>           
            <@vcolumn title="${action.getText('department')}" property="department.name"/>
            
            <#assign userType=""/>
            <#if object.userType?exists>
            <#if object.userType=='SYSTEM_USER'>
              <#assign userType="${action.getText('system.user')}"/>
            <#else>
              <#assign userType="${action.getText('not.system.user')}" />
            </#if>
            </#if>
            <@vcolumn title="${action.getText('user.type')}" property="userType">
             ${userType}
            </@vcolumn>
            
         </@list>
        <#if !first>
        <@buttonBar>
            <@vsubmit name="'disable'" value="'${action.getText('disable')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmInvalids(\"userIds\", \"${action.getText('confirm.disable')}\"), checkInvalidParms());'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
      </#if>
    </@ww.form>
    <script language="JavaScript">
      function validateDelete(delFun, checkFun) {
	    if (delFun) {
		  checkFun;
		  return true;
		}
         return false;
    	}
    </script>
</@htmlPage>
