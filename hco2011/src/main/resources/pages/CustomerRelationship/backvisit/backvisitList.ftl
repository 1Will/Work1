<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('backvisit')}">
	<@ww.form name="'listForm'" namespace="'/backvisit'" action="'searchBackVisit'" method="'post'">
		<@ww.token name="searchBackVisitToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./backvisitSearcher.ftl" />
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'" />
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/backvisit/editBackVisit.html"/>
			</#if>
        </@buttonBar>
        <#assign returnName='replaceWord'>
        <@list title="${action.getText('backVisitList')}" 
        includeParameters="backVisitType.id|customer|contactArchive|backVisitWay.id|customerInfo.id|continueBackVisit|backVisitDate|backVisitDate_start|backVisitDate_end|nextBackVisitDate|nextBackVisitDate_start|nextBackVisitDate_end|employee|continueBackVisit|readOnly|onlyInvalid|onlyValid" 
        fieldMap="like:name|customer|contactArchive|employee,date:backVisitDate_start|backVisitDate_end|nextBackVisitDate_start|nextBackVisitDate_end" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="backVisitIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
             <#if (object.disabled)>
	            <@vlh.column title="${action.getText('backVisitType')}"  property="backVisitType.name" sortable="desc">
	             ${object.backVisitTyp?if_exists}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            	<@vcolumn title="${action.getText('backVisitType')}" property="backVisitType.name" sortable="desc">
                <a href="editBackVisit.html?backVisit.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">
            	<#if (object.backVisitType?exists)>
            		${object.backVisitType.name?if_exists}
            	</#if>
            	</a>
            <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('customer')}" property="customerInfo.name" sortable="desc">
            <a href="javascript:customer_OpenDialog(#{object.customerInfo.id?if_exists},${req.getParameter('readOnly')?if_exists})" title="完整度：${object.customerInfo.customerInfoIntegrity?if_exists}%; 熟悉程度：${object.customerInfo.customerType.name?if_exists}">${object.customerInfo.name?if_exists}</a>
            <@alignLeft/>
            </@vcolumn>
            <!-- 联系人 -->
            <@vcolumn title="${action.getText('contactArchive')}" property="contactArchive.name" sortable="desc">
            	<a href="javascript:contactArchives_OpenDialog(<#if (object.contactArchive?exists)>#{object.contactArchive.id?if_exists}</#if>,${req.getParameter('readOnly')?if_exists})"><#if (object.contactArchive?exists)>${object.contactArchive.name}</#if></a>
            <@alignLeft/>
            </@vcolumn>
            <#if object.projectInfo?exists>
             <@vcolumn title="${action.getText('projectInfo.name')}" property="projectInfo.name" sortable="desc">
              <a href="javascript:editProjectInfo_OpenDialog('#{object.projectInfo.id}')"
                 title="${object.projectInfo.name}%">${object.projectInfo.name}</a>
            <@alignLeft/>
            </@vcolumn>
            <#else>
            <@vcolumn title="${action.getText('projectInfo.name')}" property="projectInfo.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            </#if>
             <@vcolumn title="${action.getText('customerInfo.step')}" property="customerInfo.step.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.state')}" property="customerInfo.state.name" sortable="desc">
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
			<@vcolumn title="${action.getText('continueBackVisit')}" attributes="width='20'" sortable="desc">
				${continueBackVisit?if_exists}
            <@alignLeft attributes="width:40;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('nextBackVisitDate')}" property="nextBackVisitDate" format="yyyy-MM-dd" sortable="desc">
            <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('backVisitSum')}" property="backVisitSum" sortable="desc">
            	<a href="javascript:visitBack_OpenDialog(#{object.customerInfo.id?if_exists})" >${object.backVisitSum?if_exists}</a>
            <@alignLeft/>
             </@vcolumn>
            <@vcolumn title="${action.getText('unconnect')}" property="unconnect" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
        </@list>
	    <#if !first>
	        <#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('回访登记')}" boxName="backVisitIds" jsFunctionName="checkInvalidParms()"/>
		        </@buttonBar>
	        </#if>
        </#if>
    </@ww.form>
</@htmlPage>