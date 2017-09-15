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
<#-- $Id: -->
<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('receviceNoticeList.title')}">
     <@ww.form namespace="'/notice'" name="'receviceNotice'" action="'searchReceviceNotices'" method="'post'">
	 <@ww.token name="searchReceviceNoticeToken"/>   
	 	 <#include "receviceNoticeSearcher.ftl"/>
	 	<#if loginUser?exists>
          <#if loginUser.id?exists>
             <input type="hidden" name="loginUser.id" value="#{loginUser.id}" />
          </#if>
        </#if>
	 	  <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
          </@buttonBar>
	 		<@list title="${action.getText('receviceNotice.list')}"
        		includeParameters="loginUser.id|sender|receviceDate_start|receviceDate_end|onlyValid|onlyInvalid" 
        		fieldMap="like:sender|,date:receviceDate_start|receviceDate_end" >
        		 <@vlh.checkbox property="id" name="receviceNoticeIds">
	            	  <@vlh.attribute name="width" value="30" />
	                </@vlh.checkbox>
        		<#if (object.disabled)>
	            <@vcolumn title="${action.getText('notice.title')}" property="title" sortable="desc">
	                 ${object.title}
	                 <@alignLeft/>
                </@vcolumn>
                <#else>
	            <@vcolumn title="${action.getText('notice.title')}" property="title" sortable="desc">
	                 <a href="editReceviceNotice.html?receviceNotice.id=#{object.id}">${object.title}</a>
	                 <@alignLeft/>
                </@vcolumn>
                </#if>
                <@vcolumn title="${action.getText('notice.sender')}" property="noticeUser.name" sortable="desc">
	                 <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('notice.receviceDate')}" property="receviceDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignLeft/>
                </@vcolumn>
                <#assign readStatus = ''/>
                <#if object.readStatus?exists>
                <#if '${object.readStatus}' == 'UNREAD'>
                  <#assign readStatus = "${action.getText('UNREAD')}"/>
                <#else>
                  <#assign readStatus = "${action.getText('READED')}"/>
                </#if>
                </#if>
                <@vcolumn title="${action.getText('notice.status')}" property="readStatus" sortable="desc">
        		  ${readStatus}
        		  <@alignLeft/>
                </@vcolumn>
        	</@list>
        	<#if !first>
        	  <@buttonBar>
	            <@crm_disabledOrEnabled_button message="${action.getText('notice')}" boxName="receviceNoticeIds" jsFunctionName="checkInvalidParms()"/>
                <#assign confirmMessage = "${action.getText('confirm.unread')}?" />
               
                <@vsubmit name="'unread'" value="'${action.getText('unread')}'">
                  <@ww.param name="'onclick'" value="'return selectRecod(\"receviceNoticeIds\", \"${confirmMessage}\")'"/>
                  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
                </@vsubmit>
	          </@buttonBar>
	        </#if>
	  
	 </@ww.form>
</@htmlPage>