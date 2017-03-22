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
<#include "../includes/hco2011.ftl" />

<@htmlPage >
  <@ww.form namespace="'/security'" name="'listForm'" action="'searchUsers'" method="'get'">
     <@ww.token name="searchUsersToken"/>
        <@inputTable>
             <#include "userSearcher.ftl"/>
        </@inputTable>
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/security/editUser.html"/>
        </@buttonBar>
         <@list title="${action.getText('list.title')}" 
         		includeParameters="onlyInvalid|onlyValid|loginName|name|department.id|office.id" 
         		fieldMap="like:loginName|name" >
            <@vcolumn title="">
                <input type="checkbox" name="userIds" value="#{object.id}" width="30" <#if !object.enabled>disabled="disabled"</#if> />
            </@vcolumn>
            <@vcolumn title="${action.getText('user.loginName')}">
            	<a href="editUser.html?user.id=#{object.id}">${object.loginName}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('user.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('工号')}" property="code" sortable="desc"/>
            <@alignRight/>
            <@vcolumn title="${action.getText('institution')}" property="institustion.name" sortable="desc"/>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc"/>
            <@vcolumn title="${action.getText('user.email')}" property="email"/>
            <@vcolumn title="${action.getText('user.locale')}">
                ${object.locale.getDisplayName(action.locale)}
            </@vcolumn>           

         </@list>
        <#if !first>
        <@buttonBar>
        	<#if onlyValid >
	          <@vsubmit name="'disable'"  value="'${action.getText('disabled')}'">
		                <@ww.param name="'onclick'" value="'return validateDelete(confirmInvalids(\"userIds\", \"${action.getText('confirm.disable')}\"), checkInvalidParms());'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	
		      </@vsubmit> 
		    </#if>        
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
