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

<#-- $Id: deviceDepreciationDetailSelector.ftl 11122 2009-09-04 08:40:35Z wliu $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('deviceDepreciationDetail.title')}">

<@ww.form name="'listForm'" action="'searchDeviceDepreciationDetail'" method="'post'">
    <#include "./deviceDepreciationDetailSearcher.ftl" />
    <@ww.hidden name="'deviceCard.id'" value="'#{deviceId?if_exists}'"/>
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
	<@list title="${action.getText('list.title')}" includeParameters="deviceCard.id|month" fieldMap="like:" excel=false setupTable=false >
            <@vlh.column title="${action.getText('deviceDepreciationDetail.deviceCard')}"  property="deviceCard.name"/>
            <@vlh.column title="${action.getText('deviceDepreciationDetail.originalValue')}"  property="originalValue" format="${action.getText('currency1Format')}"/>
            <@vlh.column title="${action.getText('deviceDepreciationDetail.monthDepreciationScale')}"  property="monthDeprecitionScale" format="${action.getText('currency1Format')}"/>
            <@vlh.column title="${action.getText('deviceDepreciationDetail.depreciationValue')}"  property="deprecitionValue" format="${action.getText('currency1Format')}"/>
            <@vlh.column title="${action.getText('deviceDepreciationDetail.afterDepreValue')}"  property="afterDepreValue" format="${action.getText('currency1Format')}"/>
            <@vlh.column title="${action.getText('deviceDepreciationDetail.month')}"  property="month" />
	</@list>  
</@ww.form>

</@htmlPage>
