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

<#-- $Id: contactArchivesList.ftl 2009-12-08 13:50:35Z wliu $ -->
<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('technology.genre')}">
	<@ww.form name="'listForm'" action="'searchTechnology'" namespace="'/customerRelationship'" method="'post'">
		<@ww.token name="searchTechnologyTab"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'remove'" value="'remove'"/>
		<#if customerId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerId}"/>
		</#if>
		<#assign itemNo=1/>
        <@list title=""
            includeParameters="" 
        	fieldMap="" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="technologyIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('itemNo.num')}" property="itemNo">
				<a href="###" onclick="caProfile_OpenDialog(#{object.id});">${itemNo}</a>
				<@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo+1/>
            <@vcolumn title="${action.getText('technology.name')}" property="name">
				<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('technology.applyTime')}" property="applyTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('technology.authorizeTime')}" property="authorizeTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('technology.applyPassTime')}" property="applyPassTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('technology.applyNumber')}" property="applyNumber">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('technology.patentNumber')}" property="patentNumber">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('technology.genre')}" property="genre.name">
				<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('technology.type')}" property="type.name">
				<@alignLeft/>
			</@vcolumn>
		</@list>
        <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="contactArchives_OpenDialog();"/>
                    <#assign confirmMessage = "${action.getText('delete.sure')}" />		           
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"technologyIds\", \"${confirmMessage}\");'"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
<script>
	function contactArchives_OpenDialog(){
	   url= "${req.contextPath}/customerRelationship/editTechnology.html?readOnly=${req.getParameter('readOnly')?if_exists}&customer.id=#{customerId?if_exists}&popWindowFlag=popWindowFlag";
	   //popupModalDialog(url, 850, 600);
	   //window.open(url);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	function caProfile_OpenDialog(caId){
	   var url = "${req.contextPath}/customerRelationship/editTechnology.html?technology.id="+caId+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	   //popupModalDialog(url, 850, 600);
	   //window.open(url);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>
</@framePage>