<#include "../../includes/hco2011.ftl" />

<@framePage title="">
	<@ww.form name="'listForm'" namespace="'/backvisit'" action="'searchBackVisit'" method="'post'">
		<@ww.token name="searchBackVisitToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if contactArchiveId?exists>
			<@ww.hidden name="'contactArchive.id'" value="#{contactArchiveId}"/>
		</#if>
		
		<#if customerInfoId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
		</#if>
		<#if projectInfoId?exists>
			<@ww.hidden name="'projectInfo.id'" value="#{projectInfoId}"/>
		</#if>
        <@list title="" 
        includeParameters="contactArchive.id|customerInfo.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <@vcolumn title="${action.getText('backVisitType')}" property="backVisitType.name" sortable="desc">
            <a href="javascript:backvist_OpenDialog('#{object.id}')">
            ${object.backVisitType.name?if_exists}
            <@alignLeft/>
            </a>
            </@vcolumn>
            <#if contactArchiveId?exists>
            <@vcolumn title="${action.getText('customer')}" property="customerInfo.name" sortable="desc">
            ${object.customerInfo.name?if_exists}
            <@alignLeft/>
            </@vcolumn>
            </#if>
             <#if customerInfoId?exists>
            <@vcolumn title="${action.getText('contactArchive')}" property="contactArchive.name" sortable="desc">
            ${object.contactArchive.name?if_exists}
            <@alignLeft/>
            </@vcolumn>
            </#if>
             <#if projectInfoId?exists>
              <@vcolumn title="${action.getText('customer')}" property="customerInfo.name" sortable="desc">
            ${object.customerInfo.name?if_exists}
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchive')}" property="contactArchive.name" sortable="desc">
            ${object.contactArchive.name?if_exists}
            <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('backVisitWay')}" property="backVisitWay.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('costTime')}" property="costTime" sortable="desc" format="#####" >
            <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('backVisitDate')}" property="backVisitDate" sortable="desc" format="yyyy-MM-dd" >
            <@alignCenter attributes="width:110;"/>
            </@vcolumn>
             <@vcolumn title="${action.getText('employee')}" property="employee.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('backVisitContent')}" property="backVisitContent" sortable="desc">
	            <span title="${object.backVisitContent?if_exists}">
		            <script>
		            	var s = "${object.backVisitContent?if_exists}";
		            	document.write(s.slice(0,18)+"...");
		            </script>
	            </span>
            <@alignLeft />
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>
<script language="javascript">
 //弹出客户告警状态变更窗体
	function backvist_OpenDialog(id){
			 var url = "${req.contextPath}/backvisit/editBackVisit.html?openFlag=openFlag&backVisit.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	  		 popupModalDialog(url, 800, 600);
	  
	 }
	
</script>