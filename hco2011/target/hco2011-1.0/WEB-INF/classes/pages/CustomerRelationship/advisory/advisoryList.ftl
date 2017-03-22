<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('advisoryManager')}">
	<@ww.form name="'listForm'" namespace="'/advisoryManager'" action="'searchAdvisory'" method="'post'">
		<@ww.token name="searchAdvisoryToken"/>
		<#include "./advisorySearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'" />
		<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/advisoryManager/editAdvisory.html"/>
        </#if>
        </@buttonBar>
        <@list title="${action.getText('advisoryList')}" 
        includeParameters="advisory.name|industry|advisory.connectPeople|isNoB|advisory.customerServiceName|advisory.advisoryTime_start|advisory.advisoryTime_end|onlyInvalid|onlyValid|readOnly|advisory.infoSource" 
        fieldMap="like:advisory.name|advisory.connectPeople|advisory.customerServiceName|advisory.customerService,date:advisory.advisoryTime_start|advisory.advisoryTime_end" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="advisoryIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
             <#if (object.disabled)>
	            <@vlh.column title="${action.getText('advisory.name')}"  property="name" sortable="desc">
	             ${object.name}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            <@vcolumn title="${action.getText('advisory.name')}" property="name" sortable="desc">
                <a href="editAdvisory.html?advisory.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.name}</a>
                <@alignLeft/>
            </@vcolumn>
            </#if>
            <#--
            <@vcolumn title="${action.getText('advisory.cust_type')}" property="customerType.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('advisory.industry_ID.id')}" property="industry.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('advisory.enter_nature_ID.id')}" property="companyNature.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('advisory.moveTel')}" property="mobile" sortable="desc">
            <@alignLeft attributes="width:110;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('advisory.connectPeople')}" property="connectPeople" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('advisory.customerService')}" property="customerServiceName" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('advisory.consultationTime')}" property="advisoryTime" format="yyyy-MM-dd" sortable="desc">
            <@alignCenter attributes="width:110;"/>
            </@vcolumn>
            <#assign isNoB=""/>
            <#if object.isNoBack>
            	<#assign isNoB="${action.getText('advisory.isNoBack.yes')}">
            <#else>
           	 	<#assign isNoB="${action.getText('advisory.isNoBack.no')}">
		    </#if>
		    <@vcolumn title="${action.getText('advisory.NoBack')}" sortable="desc">
				${isNoB}
			<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('advisory.advisoryContent')}" property="advisoryContent" sortable="desc">
            <@alignLeft attributes="width:200;"/>
            </@vcolumn>
        </@list>
	    <#if !first>
	        <#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_button message="${action.getText('advisory')}" boxName="advisoryIds" jsFunctionName="checkInvalidParms()"/>
		        </@buttonBar>
	        </#if>
        </#if>
    </@ww.form>
</@htmlPage>