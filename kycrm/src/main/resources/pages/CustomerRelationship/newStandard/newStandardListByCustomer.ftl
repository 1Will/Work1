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

<@framePage title="${action.getText('newStandard.genre')}">
	<@ww.form name="'listForm'" action="'searchNewStandard'" namespace="'/customerRelationship'" method="'post'">
		<@ww.token name="searchNewStandard"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if customerId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
		</#if>
		<#assign itemNo=1/>
        <@list title=""
            includeParameters="" 
        	fieldMap="" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="newStandardIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('itemNo.num')}" property="itemNo">
				<a href="###" onclick="caProfile_OpenDialog(#{object.id});">${itemNo}</a>
				<@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo+1/>
            <@vcolumn title="${action.getText('newStandard.name')}" property="name">
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('newStandard.genre')}" property="genre.name">
				<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('newStandard.partakeGenre')}" property="partakeGenre.name">
				<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('newStandard.ranking')}" property="ranking">
				<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('newStandard.approveTime')}" property="approveTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('newStandard.approveNumber')}" property="approveNumber">
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('newStandard.approveUnit')}" property="approveUnit">
				<@alignLeft/>
            </@vcolumn>
		</@list>
        <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="contactArchives_OpenDialog();"/>
                    <#assign confirmMessage = "${action.getText('delete.sure')}" />		           
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"newStandardIds\", \"${confirmMessage}\");'"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
<script>
	function contactArchives_OpenDialog(){
	   url= "${req.contextPath}/customerRelationship/editnewStandByCustomer.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}";
	   //popupModalDialog(url, 850, 600);
	   //window.open(url);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	function caProfile_OpenDialog(caId){
	   var url = "${req.contextPath}/customerRelationship/editnewStandByCustomer.html?newStandard.id="+caId+"&readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId}";
	   //popupModalDialog(url, 850, 600);
	   //window.open(url);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>
</@framePage>