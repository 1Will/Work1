<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: userSelector.ftl 11122 2008-02-26 12:54:35Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
<base target="_self">
<@ww.form name="'listForm'" action="'searchLocation'" method="'post'"><#--searchLocationSelector-->
	<@ww.token name="searchLocationToken"/>
	<#--
    <@inputTable>
		<tr>
		   <@ww.textfield label="'${action.getText('locationCode')}'" name="'locationCode'" value="'${req.getParameter('locationCode')?if_exists}'" cssClass="'underline'" />	 
	 	   <@ww.select label="'${action.getText('status')}'" required="false" name="'status'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="status" emptyOption="false" disabled="false">
		   </@ww.select>
	 	</tr>
	</@inputTable>
	-->
	<#include "../warehouseInfo/location/locationSearcher.ftl"/> 
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
   <@list title="${action.getText('')}" 
	includeParameters="locationCode|status" 
	fieldMap="like:locationCode" >
            <@vcolumn title="${action.getText('locationCode')}" property="code" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id},'${object.code}'));">${object.code}</a>
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('maxWeight')}" property="maxWeight" format="${action.getText('currencyFormat')}">
                <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('length')}" property="length" format="${action.getText('currencyFormat')}" >
                <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('wide')}" property="wide" format="${action.getText('currencyFormat')}">
                <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('hight')}" property="hight" format="${action.getText('currencyFormat')}">
                <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('volume')}" property="volume" format="${action.getText('currencyFormat')}">
                <@alignRight />
            </@vcolumn>
             <#assign status=''/>
        	<#if object.status?exists>
		       <#if '${object.status}' == 'USED'>
		       <#assign status = "${action.getText('USED')}"/>
		       <#elseif '${object.status}' == 'NON_USE'>
		       <#assign status = "${action.getText('NON_USE')}"/>
        	   </#if>
        	</#if>
            <@vcolumn title="${action.getText('status')}" sortable="desc">
            ${status}
                <@alignLeft />
            </@vcolumn>
     </@list>
        <#if !first>
        <@buttonBar>
	   </@buttonBar>
	   </#if>
</@ww.form>
<script language="javascript">
 var inStatusSelector = document.all("status");
  	inStatusGroups = inStatusSelector.options.length;
  	for (i=0; i<inStatusGroups; i++) {
    <#if req.getParameter('status')?exists>
    	if (inStatusSelector.options[i].value == "${req.getParameter('status')?if_exists}") {
      	    inStatusSelector.options[i].selected="true";
    	}
    </#if>
    }
    function checkInvalidParms(){
	    if (getObjByNameRe("status").value == -1) {
		  getObjByNameRe("status").value = '';
		}
      	return true;
   	}
</script>
</@htmlPage>