<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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


<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('salaryItems.title.list')}">
	<@ww.form name="'listFrom'" namespace="'/salaryItems'" action="'listSalaryItemsWindow'" method="'post'" >
		<@ww.token name="searchSalaryItemsToken"/>
		<#include "./salaryItemsSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
	<@list title="${action.getText('salaryItemsList')}" includeParameters="code|name|type.id|order.id|readOnly|onlyInvalid|onlyValid" fieldMap="like:code|name|" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="salaryItemsIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	    <@vcolumn title="${action.getText('salaryItems.code')}" property="code" sortable="asc">
            ${object.code}
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salaryItems.name')}" property="name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salaryItems.type')}" property="type.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salaryItems.order')}" property="orders.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salaryItems.introduction')}" property="introduction" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salaryItems.remark')}" property="remark" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
	</@list>
	<#if !first>
       <@buttonBar>
			<input type="button" class="button" onclick="confrm()" value="确定"/>
	   </@buttonBar>
	</#if>
</@ww.form>
<script>
		function confrm(){
			var checkboxes = jgetObjByName("input[name='salaryItemsIds']:not(input[checked=false])");
			var l = checkboxes.length;
			var allId = ""; 
			for(var a = 0; a<l; a++){
			allId+=" ";
			allId+=checkboxes[a].value;
			}
		returnDialog(allId);
		}
</script>
</@htmlPage>
