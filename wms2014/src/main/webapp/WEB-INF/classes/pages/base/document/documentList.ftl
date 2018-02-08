<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('document.title')}">
	 <@ww.form name="'listdocument'" action="'searchDocuments'" method="'post'">
	 <@ww.token name="searchDocuments"/>
		<#assign itemNo=1/>
	 	<#include "documentSearcher.ftl"/>
	    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms();'"/> 
			<#if !(action.isReadOnly())>
        	  <@redirectButton value="${action.getText('upload')}" url="${req.contextPath}/base/document/editDocument.html"/> 
            </#if>
        </@buttonBar>      
        <@list title="${action.getText('document.list')}" 
        	includeParameters="id|name|category.id" 
        	fieldMap="like:id|name" >
            <@vlh.checkbox property="id" name="documentIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
              <#if !(action.isReadOnly())>
                <a href="editDocument.html?document.id=#{object.id}">#{itemNo}</a>
              <#else>
                #{itemNo}
              </#if>
                <@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('document.name')}"  property="name" sortable="desc">
            	<a href="downloadDocument.html?document.id=#{object.id}" title="${action.getText('download')}">${object.name}</a>
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('document.category')}" property="category.value" >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('document.description')}" property="description" >
             <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('creater')}" property="creator" >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('createdTime')}"  property="createdTime" format="yyyy-MM-dd" sortable="desc">
            <@alignCenter />
            </@vcolumn>
        	
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('document')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"documentIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
        </#if>
     </@ww.form>
     <script language="javascript">
       function validateDelete(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       }
     </script>
</@htmlPage>