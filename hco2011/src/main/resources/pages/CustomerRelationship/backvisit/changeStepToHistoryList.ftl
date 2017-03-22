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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ 

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('等级变更列表')}">
	<@ww.form name="'listForm'" action="'searchChangeStepToHistory'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchChangeStepToHistoryToken"/>
        <@list title="${action.getText('等级变更列表')}" 
            includeParameters="customerId.id|backVisitId.id" 
        	fieldMap="">
            <@vcolumn title="${action.getText('客户名称')}" property="customerId.name" sortable="desc" >
           	<#if object?exists>
           	${object.customerId.name}
           	</#if>
           	<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>dsaaaaaaaaaaa
</@htmlPage>-->
<#include "../../includes/tab.ftl" />
 <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       text-align:right;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        text-align:right;
        width:80%;
    }
  </style>
  <base target="_self">
<@framePage title="${action.getText('')}">	
	<@ww.form namespace="'/backvisit'" name="'listForm'" action="'searchChangeStepToHistory'" method="'post'">
		<@ww.token name="searchChangeStepToHistoryToken"/>
		 <@list title="等级变更列表"  includeParameters="customerId.id" fieldMap="" >
            <@vcolumn title="${action.getText('客户名称')}" property="customerId.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('变更前等级')}" property="customerSteping.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('变更后等级')}" property="customerSteped.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('变更人')}" property="user.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('变更时间')}" property="changeDate" sortable="desc" format="yyyy-MM-dd">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('变更理由')}" property="changeReason">
            	<@alignLeft />
            </@vcolumn>
		</@list>
	</@ww.form>
</@framePage>
<script language="javascript">
</script>