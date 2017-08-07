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
<@htmlPage title="${action.getText('noticeList.title')}">
     <@ww.form namespace="'/notice'" name="'notice'" action="'searchnotices'" method="'post'">
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 <@ww.token name="searchWashsToken"/>   
	 	 <#include "noticeSearcher.ftl"/>
	 	  <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<#if !(action.isReadOnly())>
	 			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/notice/editNotice.html"/>
	 		</#if>  
          </@buttonBar>
          <#-- <@ww.hidden name="'saveOrSendFlag'" value="#{sendNotice.id}"/>-->
          <#if loginUser?exists>
          <#if loginUser.id?exists>
             <input type="hidden" name="loginUser.id" value="#{loginUser.id}" />
          </#if>
        </#if>
	 		<@list title="${action.getText('sendNotice.list')}" 
        		includeParameters="loginUser.id|title1|sendUserName|onlyValid|onlyInvalid" 
        		fieldMap="like:title1|sendUserName" >
        		<#if (object.disabled)>
        		    <@vlh.checkbox property="id" name="sendNoticeIds">
	            	  <@vlh.attribute name="width" value="30" />
	                </@vlh.checkbox>
	            <@vcolumn title="${action.getText('send.title')}" property="title" sortable="desc">
	                  ${object.title}
	                  <@alignLeft/>
                </@vcolumn>
                <#else>
                  <@vlh.checkbox property="id" name="sendNoticeIds">
	            	  <@vlh.attribute name="width" value="30" />
	                </@vlh.checkbox>
	            <@vcolumn title="${action.getText('send.title')}" property="title" sortable="desc">
	                 <a href="editNotice.html?sendNotice.id=#{object.id}&sendFlag=send">${object.title}</a>
                </@vcolumn>
             </#if>
             <#--
                <@vcolumn title="${action.getText('revuser')}" property="noticeUser.name">
        		     <@alignLeft/>
                </@vcolumn>
             -->
        		<@vcolumn title="${action.getText('send.content')}" property="content">
        		     <@alignLeft/>
                </@vcolumn>
                  <@vcolumn title="${action.getText('send.fileName')}" property="name">
        		     <@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('send.sendDate')}" property="sendDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <@vcolumn title="${action.getText('durationDate')}" property="validityDate" format="yyyy-MM-dd" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>
                <#assign sendStatus = ''/>
            <#if object.sendStatus?exists>
            <#if '${object.sendStatus}' == 'UNSEND'>
              <#assign sendStatus = "${action.getText('unsendStatus')}"/>
            <#else>
              <#assign sendStatus = "${action.getText('edsendStatus')}"/>
            </#if>
            </#if>
           <@vcolumn title="${action.getText('Status')}" property="sendStatus" sortable="desc">
             ${sendStatus}
             <@alignCenter/>
            </@vcolumn>
               <#-- <@vcolumn title="${action.getText('wash.planBeginDate')}" property="position" sortable="desc">
        		     <@alignCenter/>
                </@vcolumn>-->
        	</@list>
        	<#if !first>
	        	<#if !(action.isReadOnly())>
			        <@buttonBar>
	        	         <@crm_disabledOrEnabled_button message="${action.getText('noticeBill')}" boxName="sendNoticeIds" jsFunctionName="checkInvalidParms()"/>
			        </@buttonBar>
		        </#if>
	        </#if>
	  
	 </@ww.form>
</@htmlPage>
