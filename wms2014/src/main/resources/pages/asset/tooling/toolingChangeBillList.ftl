<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('tooling.changeBillRecord')}">
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
	<@ww.form namespace="'/asset/tooling'" name="'toolingChangeBill'" action="''" method="'post'">
		 <@ww.token name="searchToolingChangeBillToken"/>
		 <input type="hidden" name="tooling.id" value="#{tooling.id}"/>
		<@list title="" excel=false setupTable=false
        		includeParameters="tooling.id" 
        		fieldMap="like:tooling.id" >
	            <@vcolumn title="${action.getText('tooling.changeBillNo')}" property="billNo" sortable="desc">
	              	<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('tooling.changeBillName')}" property="billName" sortable="desc">
	            	<@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('changBill.acceptDepartment')}" property="acceptDepartment.name">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('changBill.acceptManager')}" property="acceptor.name">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.planCompleteTime')}" property="planCompleteTime" format="yyyy-MM-dd">
                 <@alignCenter/>
            </@vcolumn>
            <#if (object.changeBillFlag?string)=='true'>
              <#assign status="${action.getText('changeBill.changed')}"/>
            <#else>
              <#assign status="${action.getText('changeBill.changeing')}"/>
            </#if>
            <@vcolumn title="${action.getText('tooling.changeBillStatus')}">
            ${status}
            <@alignLeft/>
            </@vcolumn>
        </@list>
	</@ww.form>
</@framePage>
