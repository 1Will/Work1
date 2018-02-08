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

<#-- $Id: userSelector.ftl 11122 2008-02-26 12:54:35Z zbzhang $ -->

<#include "../includes/macros.ftl" />

<@htmlPage title="${action.getText('title')}">

<@ww.form name="'listForm'" action="'userSelector'" method="'post'">
   <@ww.hidden name="'multipleSelect'" value="'${multipleSelect?if_exists}'"/>
   <@ww.hidden name="'hiddenexcludedisabled'" value=""/>
    <#include "./userSearcher.ftl" />
    <@ww.hidden name="'filterUserIds'" value="'${filterUserIds?if_exists}'"/>
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
     <@list title="${action.getText('list.title')}" excel=false setupTable=false
            includeParameters="exclude_disabled|filterUserIds|loginName|name|filiale.id|department.id|multipleSelect|hiddenexcludedisabled|user.type" 
            fieldMap="like:loginName|name" >
            <#if ('${multipleSelect?if_exists}'=="T")>
                <@vlh.checkbox title="" name="spareIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
               </@vlh.checkbox>
            </#if>
            <input type="hidden" name="userNames" value="${object.name}"/>
            <@vcolumn title="${action.getText('user.loginName')}">
              <#if ('${multipleSelect?if_exists}'=="T")>
                 ${object.loginName}   
              <#else>
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}','${object.loginName}'));">
	                    ${object.loginName}
                </a>
              </#if>
            </@vcolumn>
            <@vcolumn title="${action.getText('user.name')}" property="name"/>
            <@vcolumn title="${action.getText('user.locale')}">
                ${object.locale.getDisplayName(action.locale)}
            </@vcolumn>
            <@vcolumn title="${action.getText('user.email')}" property="email"/>
            <@vcolumn title="${action.getText('filiale')}" property="filiale.name"/>
            <@vcolumn title="${action.getText('department')}" property="department.name"/>
      </@list>
      <#if (multipleSelect=="T")>
        <#if !first>
        <@buttonBar>
		      		<@vsubmit name="'choose'" value="'${action.getText('choose')}'" >
		      			<@ww.param name="'onclick'" value="'return confirmSelects(\"spareIds\");'"/>
	                	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            	</@vsubmit>
	   </@buttonBar>
	   </#if>
     </#if>
</@ww.form>
 <script language="javascript">	
  function confirmSelects(boxname) {
	      		if (!hasChecked(boxname)) {
	      			alert("${action.getText('user.noSelect')}");
	      			return false;
	      		}
	      		chooseUsers(boxname);
	      		return true;
	      	}
	      	
	      	function chooseUsers(boxname) {
	      		var selector = document.getElementsByName(boxname);
	      		var userNames = document.getElementsByName("userNames");
			    if (!selector) {
			        return false;
			    }
			    
			    var user = "";
			    if (selector.length) {
			        for (i = 0; i < selector.length; i++) {
			            if (selector[i].checked) {
			                user += selector[i].value + ",";
			                user += userNames[i].value + ",";
			            }
			        }
			    }
			    returnDialog(user,false);
	      	}
	      	
 </script>
</@htmlPage>
