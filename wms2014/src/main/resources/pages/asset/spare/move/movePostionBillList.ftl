<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
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
 <#include "../../../includes/eam2008.ftl" />
 <@htmlPage title="${action.getText('movePostionBill.title')}">
 	<@ww.form namespace="'/spare'" name="'listFrom'" action="'searchMovePostionBill'" method="'post'">
		<@ww.token name="searchMovePostionBillToken"/>
		<#include "movePostionBillSearcher.ftl"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>   
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/spare/editMovePostionBill.html"/>
	        </#if>
		</@buttonBar>
		<@list title="${action.getText('MovePostionBillList')}" 
				includeParameters="movePostionBillNo|movePostionBillName|MovePositionBillPeople|storageGrade.id|warehouse.id|MovePositionBillDate_start|MovePositionBillDate_end|moveStatus|onlyValid|onlyInvalid" 
				fieldMap="like:movePostionBillNo|movePostionBillName|MovePositionBillPeople,date:MovePositionBillDate_start|MovePositionBillDate_end">
			<@vlh.checkbox property="id" name="movePostionBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <#if (object.disabled)>
            	<@vcolumn title="${action.getText('movePostionBillNo')}" property="billNo" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <#else>
            	<@vcolumn title="${action.getText('movePostionBillNo')}" property="billNo" sortable="desc">
            	<a href="editMovePostionBill.html?movePostionBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('movePostionBillName')}" property="billName" sortable="asc">
            	<@alignLeft />
            </@vcolumn> 
            <#-- 仓库级别 -->
            <@vcolumn title="${action.getText('仓库级别')}" property ="storageGrade.value" sortable="desc"> 
				<@alignLeft />
            </@vcolumn>     
            <#-- 仓库 -->
            <@vcolumn title="${action.getText('仓库')}"  property ="warehouse.name" sortable="desc"> 
				<@alignLeft />
            </@vcolumn>           
            <@vcolumn title="${action.getText('MovePositionBillPeople')}" property="user.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('MovePositionBillDate')}" property="moveTime" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter />
            </@vcolumn>
            <#assign status=''/>
		       <#if '${object.moveStatus}'=='unMovePosition'>
		           <#assign status = "${action.getText('unMovePosition')}"/>
		       <#elseif '${object.moveStatus}'=='movePositioning'>
		          <#assign status = "${action.getText('movePositioning')}"/>
		       <#else>
		         <#assign status = "${action.getText('movePositioned')}"/>
        	   </#if>
         	<@vcolumn title="${action.getText('status')}" attributes="width='50'" sortable="asc">
           			${status}
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
		<#if !(action.isReadOnly())>
		<@buttonBar>
        	<@eam2008_disabledOrEnabled_button message="${action.getText('movePostionBill')}" boxName="movePostionBillIds" jsFunctionName="checkInvalidParms()"/>
		</@buttonBar>
        </#if>
        </#if>
	</@ww.form>
 </@htmlPage>