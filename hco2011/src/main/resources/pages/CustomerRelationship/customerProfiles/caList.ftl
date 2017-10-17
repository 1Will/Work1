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

<@framePage title="${action.getText('contactArchives.title')}">
	<@ww.form name="'listForm'" action="'searchCA'" method="'post'">
		<@ww.token name="searchContactArchivesToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'remove'" value="'remove'"/>
		<#if customerId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerId}"/>
		</#if>
		<#if projectInfoId?exists>
			<@ww.hidden name="'projectInfo.id'" value="#{projectInfoId}"/>
		</#if>
		<#if customerTypeId?exists>
			<@ww.hidden name="'type.id'" value="${customerTypeId}"/>
		</#if>
		<#assign itemNo=1/>
        <@list title=""
            includeParameters="contactArchives.name|customerInfo.id|contactArchives.customerName|type.id|readOnly|onlyInvalid|onlyValid|remove|" 
        	fieldMap="like:contactArchives.name|contactArchives.customerName" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="contactArchivesIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('contactArchives.fileNo')}" property="itemNo">
				<a href="###" onclick="caProfile_OpenDialog(#{object.id});">${itemNo}</a>
				<@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo+1/>
            <@vcolumn title="${action.getText('contactArchives.name')}" property="name">
				<@alignLeft/>
            </@vcolumn>
			<#assign sex=""/>
			<#if object.sex>
				<#assign sex="${action.getText('contactArchives.women')}"/>
			<#else>
				<#assign sex="${action.getText('contactArchives.man')}"/>
			</#if>
			<@vcolumn title="${action.getText('contactArchives.sex')}" property="sex">
				${sex}
				<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('contactArchives.dept')}" property="dept">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('contactArchives.duty')}" property="duty">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.phone')}" property="phone">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.mobilePhone')}" property="mobilePhone">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.caType')}" property="type.name">
     			<@alignLeft/>
            </@vcolumn>
		</@list>
        <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="contactArchives_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('contact.customer.relationship.remove')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"contactArchivesIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
<script>
	//寮瑰嚭鑱旂郴浜烘柊寤烘ā鎬佺獥浣�
	function contactArchives_OpenDialog(){
	  var url="";
	<#if customerId?exists>
	url= "${req.contextPath}/customerRelationship/editContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&customer.id=#{customerId?if_exists}&customerType.id=${customerTypeId?if_exists}&popWindowFlag=popWindowFlag";
	 </#if>
	 
	 <#if projectInfoId?exists>
	url= "${req.contextPath}/customerRelationship/editContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectInfo.id=${projectInfoId?if_exists}&customer.id=${customerId?if_exists}&customerType.id=${customerTypeId?if_exists}";
	 </#if>
	   //popupModalDialog(url, 850, 600);
	   //window.open(url);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 //寮瑰嚭鑱旂郴浜虹淮鎶ゆā鎬佺獥浣�
	function caProfile_OpenDialog(caId){
	   var url = "${req.contextPath}/customerRelationship/editContactArchives.html?contactArchives.id="+caId+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	   //popupModalDialog(url, 850, 600);
	   //window.open(url);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>
</@framePage>