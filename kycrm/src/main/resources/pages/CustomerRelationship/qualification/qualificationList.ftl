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

<@framePage title="${action.getText('qualification.title')}">
	<@ww.form name="'listForm'" action="'searchQualification'" method="'post'">
		<@ww.token name="searchQualificationToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if customerId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerId}"/>
		</#if>
		<#assign itemNo=1/>
        <@list title=""
            includeParameters="|customerInfo.id|readOnly|onlyInvalid|onlyValid|" 
        	fieldMap="customerInfo.name|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="qualificationIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('qualification.fileNo')}" property="itemNo">
				<a href="###" onclick="qualification_OpenUpdateDialog('#{object.id}')">${itemNo}</a>
				<@alignLeft/>
            </@vcolumn>
            <#assign itemNo=itemNo+1/>
            <@vcolumn title="${action.getText('name')}" property="name">
				<@alignLeft/>
            </@vcolumn>
			 <@vcolumn title="${action.getText('level')}" property="level.name">
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('certificateNumber')}" property="certificateNumber">
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('obtainDate')}" property="obtainDate" format="yyyy-MM-dd">
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('approvalUnit')}" property="approvalUnit">
				<@alignLeft/>
            </@vcolumn>
            
		</@list>
        <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="qualification_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('customer.qualification.remove')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"qualificationIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
<script>
function qualification_OpenDialog(){
	  var url= "${req.contextPath}/customerRelationship/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerId?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function qualification_OpenUpdateDialog(qualificationId){
	 var url ='${req.contextPath}/customerRelationship/editQualification.html?qualification.id='+qualificationId+'&readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerId?if_exists}';
	  openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 </script>
</@framePage>