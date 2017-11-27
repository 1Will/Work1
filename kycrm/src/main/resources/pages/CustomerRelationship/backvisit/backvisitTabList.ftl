<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('backVisitList')}">
	<@ww.form name="'listForm'" namespace="'/backvisit'" action="'searchBackVisitTab'" method="'post'">
	    <@ww.token name="searchBackVisitTabToken"/>
	    <#assign returnName='replaceWord'>
        <@list title="${action.getText('backVisitList')}" 
        includeParameters="name|customer|contactArchive|backVisitWay.id|customerInfo.id|continueBackVisit|backVisitDate|backVisitDate_start|backVisitDate_end|nextBackVisitDate|nextBackVisitDate_start|nextBackVisitDate_end|employee|continueBackVisit|onlyInvalid|onlyValid" 
        fieldMap="like:name|customer|contactArchive|employee,date:backVisitDate_start|backVisitDate_end|nextBackVisitDate_start|nextBackVisitDate_end" >
             <#--<#if (object.disabled)>
	            <@vlh.column title="${action.getText('name')}"  property="name" sortable="desc">
	             ${object.name}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            <@vcolumn title="${action.getText('name')}" property="name" sortable="desc">
                <@alignLeft/>
            </@vcolumn>
            </#if>
            -->
            <#if (object.disabled)>
	            <@vlh.column title="${action.getText('backVisitType')}"  property="backVisitType.name" sortable="desc">
	             ${object.backVisitTyp?if_exists}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            	<@vcolumn title="${action.getText('backVisitType')}" property="backVisitType.name" sortable="desc">
            	<#if (object.backVisitType?exists)>
            		${object.backVisitType.name?if_exists}
            	</#if>
            <@alignLeft/>
            </@vcolumn>
                
            </#if>
            <@vcolumn title="${action.getText('customer')}" property="customerInfo.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('customerInfo.step')}" property="customerInfo.step.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchive')}" property="contactArchive.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
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
           <#assign returnName=returnName +'replaceWord'>
           <@ww.hidden name="'${returnName}'" value="'${object.backVisitContent?if_exists}'"/>
	            <span title="${object.backVisitContent?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	document.write(s.slice(0,18)+"...");
		            </script>
	            </span>
            <@alignLeft />
            </@vcolumn>
            
            <#assign continueBackVisit=""/>
            <#if (object.continueBackVisit)=='0'>
            	<#assign continueBackVisit="${action.getText('continueBack.yes')}">
            <#else>
           	 	<#assign continueBackVisit="${action.getText('continueBack.no')}">
		    </#if>
			<@vcolumn title="${action.getText('continueBackVisit')}" attributes="width='20'">
				${continueBackVisit}
            <@alignLeft attributes="width:40;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('nextBackVisitDate')}" property="nextBackVisitDate" format="yyyy-MM-dd" sortable="desc">
            <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('backVisitSum')}" property="backVisitSum" sortable="desc">
            <a href="javascript:visitBack_OpenDialog(#{object.customerInfo.id?if_exists})" >${object.customerInfo.backVisitSum?if_exists}</a>
            <@alignLeft/>
             </@vcolumn>
            <@vcolumn title="${action.getText('checkdetail')}"  sortable="desc">
             <a href="editBackVisit.html?backVisit.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&fromType=h">详细
            </a>
            <@alignLeft/>
             </@vcolumn>
        </@list>
        <@buttonBar>
		<@vsubmit name="'close'" value="'关闭'" onclick="'window.close()'"/>
    </@buttonBar>
    <input  type="hidden" value="h" name="fromType" />
    </@ww.form>
</@htmlPage>