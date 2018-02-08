
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('filialeList.title')}">
    <@ww.form name="'listForm'" action="'searchFiliales'" method="'post'">
        <@ww.token name="searchFilialesToken"/>
        <#include "filialeSearcher.ftl"/>
        <@buttonBar> 
	 		 <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'" />   
	 		 <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/filiale/editFiliale.html"/>    
        </@buttonBar>
        <@list title="${action.getText('filiale.list')}" includeParameters="id|name|code|leader|onlyValid|onlyInvalid" 
        	fieldMap="like:id|name|code|leader|onlyValid|onlyInvalid">
             <@vlh.checkbox property="id" name="filialeIds">
                 <@vlh.attribute name="width" value="30"/>
             </@vlh.checkbox>
             <@vcolumn title="${action.getText('filiale.code')}" property="code" sortable="desc"> 
               <#if onlyValid> 
                   <a href="editFiliale.html?filiale.id=#{object.id}">${object.code}</a> 
               </#if>
               <#if onlyInvalid> 
               	${object.code}
               </#if>
             </@vcolumn> 
             <@vcolumn title="${action.getText('filiale.name')}" property="name" sortable="desc" />
             <@vcolumn title="${action.getText('filiale.leader')}" property="leader" />  
             <@vcolumn title="${action.getText('filiale.comment')}" property="comment"/>  
        </@list>
       <#if !first>
	    <#--    <@buttonBar>
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('filiale')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"filialeIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	        </@buttonBar>
	     -->   
	       <@buttonBar>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('filiale')}" boxName="filialeIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>