<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: productProfile.ftl 2009-12-16 11:09:35Z mfzhnag $ -->
<#include "../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" namespace="'/supplierManager'" action="'searchContactInfo'" method="'post'">
		<@ww.token name="seacherContactInfoToken"/>
        <#if supplier?exists>
        <#if supplier.id?exists>
            <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        </#if>
        </#if>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#assign itemNo=1/>
        <@list title="" excel=false setupTable=false
        	  includeParameters="supplier.id" 
        	  fieldMap="like:" >
            <@vlh.checkbox property="id" name="contactInfoIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('contactArchives.fileNo')}">
				<a href="" onclick="editOpenContactInfo(#{object.id})">#{itemNo}</a>
			<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('contactArchives.name')}" property="name" >
            <@alignLeft/>
            </@vcolumn>
			<#if object.sex>
				<#assign sex="${action.getText('contactArchives.women')}"/>
			<#else>
				<#assign sex="${action.getText('contactArchives.man')}"/>
			</#if>
			<@vcolumn title="${action.getText('contactArchives.sex')}" property="sex">
				${sex}
				<@alignLeft/>
			</@vcolumn>            
            <@vcolumn title="${action.getText('contactArchives.dept')}" property="dept" >
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.duty')}" property="duty" >
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.phone')}" property="phone" >
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.mobilePhone')}" property="mobilePhone" >
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.qq')}" property="qq" >
            <@alignLeft/>
            </@vcolumn>  
            <@vcolumn title="${action.getText('contactArchives.email')}" property="email" >
            <@alignLeft/>
            </@vcolumn>  
            <@vcolumn title="${action.getText('contactArchives.temperament')}" property="temperament.name" >
            <@alignLeft/>
            </@vcolumn>     
            <@vcolumn title="${action.getText('contactArchives.caType')}" property="type.name">
     			<@alignLeft/>
            </@vcolumn>                                            
        </@list>
	     <#if !first>
			<#if !(action.isReadOnly())>
			<@buttonBar>
				<@vbutton name="'button'"  class="button" value="${action.getText('new')}" onclick="openContactInfo()"/>
	            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('contactArchives.info')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"contactInfoIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	        </@buttonBar>
        </#if>
        </#if>
    </@ww.form>
<script type="text/javascript">
function openContactInfo(){
        <#if supplier?exists>
        <#if supplier.id?exists>
        if(getObjByName('readOnly').value==''){
			var url = '${req.contextPath}/supplierManager/editContactInfo.html?supplier.id='+#{supplier.id};
		}else{
			var url = '${req.contextPath}/supplierManager/editContactInfo.html?supplier.id='+#{supplier.id}+'&readOnly=${req.getParameter('readOnly')?if_exists}';
		}
			popupModalDialog(url,800,600);
			self.location.reload();
		</#if>
		</#if>
	} 
function editOpenContactInfo(id){
        <#if supplier?exists>
        <#if supplier.id?exists>
        	if(getObjByName('readOnly').value==''){
				var url = '${req.contextPath}/supplierManager/editContactInfo.html?supplier.id='+#{supplier.id}+'&contactArchives.id='+id;
			}else{
				var url = '${req.contextPath}/supplierManager/editContactInfo.html?supplier.id='+#{supplier.id}+'&contactArchives.id='+id+'&readOnly=${req.getParameter('readOnly')?if_exists}';
			}
			popupModalDialog(url,800,600);
			//window.open(url,800,600);
			self.location.reload();
		</#if>
		</#if>
	}  
</script>  
</@framePage>