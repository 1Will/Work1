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
<#-- $Id: docList.ftl 7921 2007-10-22 02:36:23Z qsun $ -->
<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('listWorkWarnning.title')}">
	 <@ww.form name="'listWorkWarnning'" action="'searchWorkWarnning'" method="'post'">
	 	<@ww.token name="searchWorkWarnningToken"/>
		<#include "workWarnningSearcher.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>   
        </@buttonBar>
        <@list title="${action.getText('workWarnning.list')}" includeParameters="type|loginUser.id|warnningDate|warnningDate_start|warnningDate_end|onlyUnRead|onlyRead" 
        	fieldMap="like:type,date:warnningDate_start|warnningDate_end">
             <@vlh.checkbox property="id" name="workWarnningIds">
                 <@vlh.attribute name="width" value="30"/>
             </@vlh.checkbox> 
             <@vcolumn title="${action.getText('warnning.type')}" property="type" sortable="desc">
               <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('warnning.content')}" property="content">
               <a href="###" onclick="open_detailDialog(${object.id})">${object.content}</a>
               <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('warnning.warnningDate')}" property="warnningDate" format="yyyy-MM-dd" sortable="desc">
               <@alignCenter/>
             </@vcolumn>
             <#assign status=''/>
         	 <#if object.readFlag>
         		<#assign status="${action.getText('warnning.readed')}"/>
         	 <#else>
         		<#assign status="${action.getText('warnning.notRead')}"/>	
         	 </#if>
             <@vcolumn title="${action.getText('warnning.status')}" property="readFlag">
               <@alignLeft/>
               ${status}
             </@vcolumn>
        </@list>
        <#if !first>
         <@buttonBar>
          <#if (action.isOnlyRead())>
		  	<#assign confirmMessage1 = "${action.getText('confirm.unRead')}${action.getText('workWarnning')}?" />
            <@vsubmit name="'unRead'" value="'${action.getText('warnning.notRead')}'">
	                <@ww.param name="'onclick'" value="'return validateInvalid(confirmUnReads(\"workWarnningIds\", \"${confirmMessage1}\"),checkInvalidParms());'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	
            </@vsubmit>
		  <#else>
		    <#assign confirmMessage1 = "${action.getText('confirm.read')}${action.getText('workWarnning')}?"  />
            <@vsubmit name="'read'" value="'${action.getText('warnning.readed')}'">
	                <@ww.param name="'onclick'" value="'return validateInvalid(confirmReads(\"workWarnningIds\", \"${confirmMessage1}\"),checkInvalidParms());'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	
            </@vsubmit>
		  </#if>
		 </@buttonBar>
	   </#if>
		  <script language="javascript">
		  	var noSelectUnread = "${action.getText('select.unRead.record')}";
		  	var noSelectRead = "${action.getText('select.read.record')}";
			function validateInvalid(delFun, checkFun) {
		      if (delFun) {
		        checkFun;
		        return true;
		      }
		      return false;
		   }
		   function confirmUnReads(boxName, message) {
		     if (hasChecked(boxName)) {
               return confirmDelete(message);
             } else {    	
               alert(noSelectUnread);
               return false;
             }
		   }
		   function confirmReads(boxName, message) {
		     if (hasChecked(boxName)) {
               return confirmDelete(message);
             } else {    	
               alert(noSelectRead);
               return false;
             }
		   }
		   function open_detailDialog(id){
		   	   var url = '${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnningDetail.html?workWarnningId='+id;
		       popupModalDialog(url, 865, 600);
	       }
		   <#if (action.isOnlyRead())>
		     document.getElementById("OnlyReadFlag").checked=true;
		   </#if>
		  </script>
	 </@ww.form>
</@htmlPage>