<#--
   Copyright (c) 2001-2010 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: locationSearcherCommon.ftl 2010-04-1 16:19:59Z wliu $ -->
<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
	<@ww.form name="'listForm'" action="'searchLocationSelector'" method="'post'">
	 <@ww.token name="searchLocationToken"/>
	<#include "locationSearcherDetail.ftl"/>
	 	 <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<@hrefButton value="${action.getText('new')}" url="editLocation.html?requestSourceType=PopupWin"/>
        </@buttonBar>
     <@list title="${action.getText('list')}"
         			includeParameters="code|warehouse|regional|locationType|bearload|status|onlyValid|onlyinvalid" 
                   fieldMap="like:code" >
            <@vcolumn title="${action.getText('code')}" property="code" sortable="asc"  >
		         <a href="javascript: returnDialog(new Array(#{object.id},'${object.code}',#{object.warehouse.id},'${object.warehouse.name}'));">${object.code}</a>
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('warehouse')}" property="warehouse.name"  sortable="asc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('regional')}" property="regional.name"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('locationType')}" property="locationType.value"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('bearload')}" property="bearload.value"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('maxWeight')}" property="maxWeight"  sortable="desc" >
                <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('status')}" property="status"  sortable="desc" >
            	<#if object.status=="USED">
            		${action.getText('USED')}
            		<#else>
            		${action.getText('NON_USE')}
            	</#if>
                <@alignLeft />
            </@vcolumn>
	  	</@list>
		
     </@ww.form>
</@htmlPage>