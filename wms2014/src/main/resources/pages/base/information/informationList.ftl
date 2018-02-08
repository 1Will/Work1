<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z xschen $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('msg.title')}">
	 <@ww.form name="'listmsg'" namespace="'/base/msg'" action="'searchMsgs'" method="'post'">
	    <@ww.token name="searchMsgsToken"/>
	    <#include "./informationSerach.ftl" />

         <@buttonBar>        
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms();'"/> 
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/msg/editMsg.html"/> 
        </@buttonBar>   
        <#assign itemNo = 0/>   
        <@list title="${action.getText('msg.list')}" 
        	includeParameters="id|name|msg.id" 
        	fieldMap="like:id|name" >
            <@vlh.checkbox property="id" name="msgIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
                <a href="editMsg.html?msg.id=#{object.id}">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('Msg.name')}"  property="name" sortable="asc">
            	<a href="editMsg.html?msg.id=#{object.id}" title="${action.getText('download')}">${object.name}</a>
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('Msg.content')}" property="content" >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('Msg.publishDate')}" property="publishDate" >
             <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('msg.msgType')}" property="msgType" >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('msg.msgStatus')}"  property="msgStatus">
            <@alignCenter />
            </@vcolumn>
        	
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('document')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"msgIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
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