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
        includeParameters="contactArchive.id|customerInfo.id|readOnly|onlyInvalid|onlyValid|employee|backVisitDate_start|backVisitDate_end|projectInfo.id|" 
        fieldMap="" >
         <@vcolumn title="${action.getText('重要程度')}" property="importanceType.name" sortable="desc">
             <#if (object.backVisitType?exists)>
             <#if object.importanceType.id==563>
             <font color="red">${object.importanceType.name}</font>
             <#else>
             <font color="green">${object.importanceType.name}</font>
             </#if>
             </#if>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('backVisitType')}" property="backVisitType.name" sortable="desc">
            <a href="javascript:backvist_OpenDialog('#{object.id}')">
            ${object.backVisitType.name?if_exists}
            <@alignLeft/>
            </a>
            </@vcolumn>
            <@vcolumn title="${action.getText('customer')}" property="customerInfo.name" sortable="desc">
            <@vlh.attribute name="width" value="8%" />
            ${object.customerInfo.name?if_exists}
            <@alignLeft/>
            </@vcolumn>
            <#if contactArchiveId?exists>
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
            <@alignLeft/>
            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
             <@vcolumn title="${action.getText('employee')}" property="employee.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('backVisitContent')}" property="backVisitContent" sortable="desc">
	            <@vlh.attribute name="width" value="60%" />
	            <span title="${object.backVisitContent?if_exists}">
		            <script>
		            	var s = "${object.backVisitContent?if_exists}";
		            	if(s.length>18){
		            		document.write(s.slice(0,18)+"...");
		            	}else{
		            		document.write(s);
		            	}
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
	  		 //popupModalDialog(url, 800, 600);
	  		 openNewWindow(url);
	  		 if(isIE()){self.location.reload();}
	 }
	
</script>