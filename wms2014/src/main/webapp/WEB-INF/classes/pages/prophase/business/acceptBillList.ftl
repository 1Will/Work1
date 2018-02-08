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
<#--$Id: purchaseBillList.ftl 11311 2008-03-13 13:19:59Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('acceptList.title')}">
	<@ww.form namespace="'/accept'" action="'searchAcceptBills'" method="'post'">
		<@ww.token name="searchAcceptsToken"/>
		<#include "acceptBillSearcher.ftl"/>
		  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@buttonBar>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			
       <#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/accept/editAcceptBill.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
		</#if>
		</@buttonBar>
		<@list title="${action.getText('acceptBill')}" 
		includeParameters="acceptBillNo|acceptBilName|readOnly|department.id|acceptDate_start|acceptDate_end|supplier.name|acceptPeople.name|merchandiseName|specification|model|toolingDevFlag|onlyValid|onlyInvalid"
		 fieldMap="like:acceptBillNo|acceptBilName|merchandiseName|specification|model|supplier.name|acceptPeople.name" >
		    <@vlh.checkbox property="id" name="acceptBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			 <#if (object.disabled)>
             <@vcolumn title="${action.getText('accept.billNo')}" property="acceptBillNo" sortable="desc">
            	${object.acceptBillNo}
            	<@alignLeft />
            </@vcolumn>
            <#else>
              <@vcolumn title="${action.getText('accept.billNo')}" property="acceptBillNo" sortable="desc">
            	<a href="editAcceptBill.html?acceptBill.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.acceptBillNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('accept.name')}" property="acceptBilName" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
           
            <@vcolumn title="${action.getText('accept.Person')}" property="acceptPeople.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             
            <@vcolumn title="${action.getText('accept.supplier')}" property="supplier.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('accept.merchandiseName')}" property="merchandiseName" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('accept.specification')}" property="specification" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('accept.model')}" property="model" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('acceptDate')}" property="acceptDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('memo')}" property="memo">
            	<@alignCenter/>
            </@vcolumn>
		</@list>
		<#if !first>
        <@buttonBar>
           <#if !(action.isReadOnly())>
             <@eam2008_disabledOrEnabled_button message="${action.getText('aceptBill')}" boxName="acceptBillIds" jsFunctionName="checkInvalidParms()"/>
           </#if>
        </@buttonBar>
        </#if>
	</@ww.form>
</@htmlPage>