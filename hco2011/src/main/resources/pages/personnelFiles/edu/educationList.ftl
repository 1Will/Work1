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



<#include "../../includes/hco2011.ftl" />
<@framePage >
	<@ww.form name="'listFrom'" namespace="'/personnelFile'" action="'searchEducation'" method="'post'" >
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="searchEducationToken"/>
	<#assign number=1/>
	<#if personnelFiles?exists>
           <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id}"/>
	</#if>
	<#if contactArchives?exists>
            <@ww.hidden name="'contactArchives.id'" value="#{contactArchives.id}"/>
	</#if>
	<@list title="" includeParameters="personnelFiles.id|contactArchives.id|" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="educationIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	    <@vcolumn title="${action.getText('education.number')}"  >
            <a href="###" onclick="showEducation('#{object.id}','${req.getParameter('readOnly')?if_exists}')">${number}</a>
            <#assign number=number+1 />
            <@alignCenter/>
        </@vcolumn>
        <@vcolumn title="${action.getText('education.degree')}" property="degree">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('education.edu')}" property="edu.name" >
            <@alignLeft/>
        </@vcolumn>
        <#-- 增加专业 显示 -->
        <@vcolumn title="${action.getText('education.profession')}" property="profession" >
            <@alignLeft/>
        </@vcolumn>
        <#-- 增加入学时间 显示 -->
        <@vcolumn title="${action.getText('education.admissionDate')}" property="admissionDate" format="yyyy-MM-dd">
            <@alignLeft/>
        </@vcolumn>
        <#-- 增加毕业时间 显示 -->
        <@vcolumn title="${action.getText('education.graduationDate')}" property="graduationDate" format="yyyy-MM-dd" >
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('education.school')}" property="school" >
            <@alignLeft/>
        </@vcolumn>
	</@list>
    <@buttonBar>
    <#if !(action.isReadOnly())>
    	<@vbutton value="${action.getText('new')}" onclick="newEducation()"/>
	  	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('education.edu')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"educationIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
   </@buttonBar>
</@ww.form>
</@framePage >
<script>
	function showEducation(id,readyOnly){
        	var url ='${req.contextPath}/personnelFile/editEdication.html?education.id='+id+'&readOnly='+readyOnly;
        	popupModalDialog(url,800,600);
        	//window.open(url);
        	if(isIE()){self.location.reload();};
    }
    function newEducation(id){
    var url ="";
    <#if personnelFiles?exists>
    url = '${req.contextPath}/personnelFile/editEdication.html?personnelFiles.id=#{personnelFiles.id}';
    <#else>
    url = '${req.contextPath}/personnelFile/editEdication.html?contactArchives.id=#{contactArchives.id}';
	</#if>
     	popupModalDialog(url,800,600);
     	//window.open(url);
        if(isIE()){self.location.reload();};
    }
     
</script>