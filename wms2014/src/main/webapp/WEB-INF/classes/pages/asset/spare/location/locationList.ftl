<#--库位查询页面 04/11/2009 -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form namespace="'/spare'" name="'locationList'" action="'searchLocation'" method="'post'">
		<@ww.token name="searchLocationToken"/>
		
		<#include "locationSearcher.ftl"/>
		
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/spare/editLocation.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	        </#if>
		</@buttonBar>
		
		<@list title="${action.getText('locationList')}" 
		includeParameters="locationCode|locationStatus|onlyValid|onlyInvalid" 
		fieldMap="like:locationCode">
			<@vlh.checkbox property="id" name="locationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            
			<#if (object.disabled)>
            	<@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <#else>
            	<@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
            	<a href="editLocation.html?location.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            
            <@vcolumn title="${action.getText('maxWeight')}" property="maxWeight" sortable="asc">
            	<@alignRight />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('length')}" property="length" sortable="asc" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('wide')}" property="wide" sortable="asc" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('hight')}" property="hight" sortable="asc" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('volume')}" property="volume" sortable="asc" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            
			<#assign status=''/>
        	<#if object.status?exists>
		       <#if '${object.status}' == 'NON_USE'>
		       <#assign status = "${action.getText('NON_USE')}"/>
		       <#elseif '${object.status}' == 'USED'>
		       <#assign status = "${action.getText('USED')}"/>
        	   </#if>
        	</#if>
			<@vcolumn title="${action.getText('status')}" attributes="width='50'">
				${status}
				<@alignLeft/>
			</@vcolumn>
			
			<@vcolumn title="${action.getText('describe')}" property="describe" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
		</@list>
			<#if !first>
        <@buttonBar>
             <#if !(action.isReadOnly())>
              <@eam2008_disabledOrEnabled_button message="${action.getText('location')}" boxName="locationIds" jsFunctionName="checkInvalidParms()"/>
             </#if>
        </@buttonBar>
        </#if>
	</@ww.form>
</@htmlPage>