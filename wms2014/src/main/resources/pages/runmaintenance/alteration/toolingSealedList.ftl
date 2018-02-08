<#include "../../includes/eam2008.ftl" />

<#if toolingDevFlag=="TOOLING">
  <#assign pageTitle="${action.getText('toolingsealed.Query')}"/>
<#else>
  <#assign pageTitle="${action.getText('devicesealed.Query')}"/>
</#if>

<@htmlPage title="${pageTitle}">
	 <@ww.form name="'alteration'" action="'searchToolingSealeds'" method="'post'">
	   <@ww.token name="searchToolingSealedsToken"/>
	 	 <#include "toolingSearcher.ftl"/>
	 	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'" />
        	<#if toolingDevFlag=="TOOLING">
        	  <#if !(action.isReadOnly())>
        	   <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/alteration/editToolingSealed.html?toolingDevFlag=${toolingDevFlag}&sealedFlag="+"T" />
        	 </#if>
        	<#else>
        	  <#if !(action.isReadOnly())>
        	    <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/alteration/editToolingSealed.html?toolingDevFlag=${toolingDevFlag}&sealedFlag="+"T" />
        	  </#if>
        	</#if>
        </@buttonBar>
        <#if toolingDevFlag=="TOOLING">
           <#assign listTitle="${action.getText('toolingsealed.Query')}"/>
        <#else>
           <#assign listTitle="${action.getText('devicesealed.Query')}"/>
        </#if>
        
         <@list title="${listTitle}" 
         		includeParameters="sealedBillNo|readOnly|sealedBillName|asset.deviceNo|asset.name|asset.graphNo|sealedDateApp_start|sealedDateApp_end|toolingDevFlag|onlyValid|onlyInvalid" 
         		fieldMap="like:sealedBillNo|sealedBillName|asset.deviceNo|asset.name|asset.graphNo|toolingDevFlag,date:sealedDateApp_start|sealedDateApp_end" >
            <@vlh.checkbox property="id" name="alterationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
           <#if (object.disabled)>
            <@vcolumn title="${action.getText('toolingsealed.sealedBillNo')}" property="sealedBillNo" sortable="asc">
                ${object.sealedBillNo}
                 <@alignLeft/>
            </@vcolumn>
            <#else>
            <@vcolumn title="${action.getText('toolingsealed.sealedBillNo')}" property="sealedBillNo" sortable="asc">
                <a href="editToolingSealed.html?alteration.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&toolingDevFlag=${toolingDevFlag}&sealedFlag="+"T">${object.sealedBillNo}</a>
                 <@alignLeft/>
            </@vcolumn>
             </#if>
            <@vcolumn title="${action.getText('toolingsealed.sealedBillName')}" property="sealedBillName" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${deviceNoTitle}" property="asset.deviceNo"  sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${assetNameTitle}" property="asset.name"  sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <#if toolingDevFlag=="TOOLING">
	            <@vcolumn title="${assetgraphNoTitle}" property="asset.graphNo"  sortable="desc">
	                 <@alignLeft/>
	            </@vcolumn>
	        </#if>
            <@vcolumn title="${action.getText('toolingsealed.sealedUsed')}" property="sealedUsed.name"  sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingsealed.sealedDateApp')}" property="sealedDateApp" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            
            <#assign sealedStatus = ''/>
            <#if '${object.status}' == 'REQUEST'>
              <#assign sealedStatus = "${action.getText('REQUEST')}"/>
            <#elseif '${object.status}' == 'SEALED'>
              <#assign sealedStatus = "${action.getText('SEALED')}"/>
            <#else>
              <#assign sealedStatus = "${action.getText('NORMAL')}"/>
            </#if>
         	<@vcolumn title="${action.getText('sealed_state')}">
           		 ${sealedStatus}
                 <@alignLeft/>
            </@vcolumn>

        </@list>
        <#if !first>
        <@buttonBar>
        <#if toolingDevFlag=="TOOLING">
        
           <#if !(action.isReadOnly())>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('toolingsealed')}" boxName="alterationIds" jsFunctionName="checkInvalidParms()"/>
	       </#if>
	    <#else>
	    <#if !(action.isReadOnly())>
	    	 <@eam2008_disabledOrEnabled_button message="${action.getText('devicesealed')}" boxName="alterationIds" jsFunctionName="checkInvalidParms()"/>
	   </#if>
	    </#if>
	    </@buttonBar>
        </#if>
     </@ww.form>
</@htmlPage>