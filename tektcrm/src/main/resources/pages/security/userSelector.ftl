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

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('title')}">

<@ww.form name="'listForm'" action="'userSelector'" method="'post'">
   <@ww.hidden name="'multipleSelect'" value="'${multipleSelect?if_exists}'"/>
    <#include "./userSearcher.ftl" />
    <@ww.hidden name="'filterUserIds'" value="'${filterUserIds?if_exists}'"/>
    <@ww.hidden name="'uIds'" value="'${req.getParameter('uIds')?if_exists}'"/>
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
     <@list title="${action.getText('list.title')}" excel=false setupTable=false
            includeParameters="exclude_disabled|filterUserIds|uIds|loginName|name|department.id|multipleSelect|telphoneNumber|onlyInvalid|onlyValid" 
            fieldMap="like:loginName|name|telphoneNumber" >
                <@vlh.checkbox id="userIds" name="userIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
               </@vlh.checkbox>
            <input type="hidden" id="userNames" name="userNames" value="${object.name}"/>
            <@vcolumn title="${action.getText('user.loginName')}">
              <#if ('${multipleSelect?if_exists}'=="T")>
                 ${object.loginName}   
              <#else>
                <a href="javascript: returnDialog(new Array(#{object.id},'${object.name}','${object.loginName}','${object.workNo}'));">
	                ${object.loginName}
	            </a>
              </#if>
            </@vcolumn>
            <@vcolumn title="${action.getText('user.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc"/>
            <@vcolumn title="${action.getText('user.email')}" property="email"/>
            <@vcolumn title="${action.getText('user.locale')}">
                ${object.locale.getDisplayName(action.locale)}
            </@vcolumn>
      </@list>
	      <#if (multipleSelect=="T")>
		      <#if !first>
			      <@buttonBar>
				      <@vsubmit name="'choose'" value="'${action.getText('choose')}'">
						  <@ww.param name="'onclick'" value="'return confirmSelects(\"userIds\");'"/>
						  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	
				      </@vsubmit>
				  </@buttonBar>
			  </#if>
	      </#if>
</@ww.form>
 <script language="javascript">	
	<#if req.getParameter('uIds')?exists>
		var userIds = getObjByName('uIds').value;
		var ids =userIds.split(",");
		var selector = document.getElementsByName("userIds");
		for(var i =0 ;i<ids.length;i++){
			for(var j=0;j<selector.length;j++){
				if(selector[j].value == ids[i]){
					selector[j].checked = true;
				}
			}
		}
		document.onclick = function(){
			var users = getObjByName('uIds').value;
			var ids;
			if(users == ''){
				ids = new Array();
			}else{
				ids=users.split(",");
			}
			var selector = document.getElementsByName("userIds");
			if(selector.length){
				for(var i=0;i<selector.length;i++){
					var temp = selector[i].value;
					if(selector[i].checked == true){
						if(ids.indexOf(temp)<0){
							ids.push(temp);
						}
					}else{
						ids.remove(temp);
					}
				}
			}
			getObjByName('uIds').value = ids.toString();
		}
	
		function confirmSelects(boxname) {
			var userIds = getObjByName('uIds').value;
			returnDialog(userIds,false);
		}
  <#else>
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
</#if>
 </script>
</@htmlPage>
