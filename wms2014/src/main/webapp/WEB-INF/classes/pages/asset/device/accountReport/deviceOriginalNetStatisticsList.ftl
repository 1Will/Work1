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
<#-- $Id: deviceOriginalNetStatisticsList.ftl 2009-09-22 15:28:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('deviceOriginalNetStatistics.title')}">

<@ww.form name="'listForm'" action="'searchDeviceOriginalNetStatistics'" method="'post'">
    <#include "./deviceOriginalNetStatisticsSearcher.ftl" />
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
	<@list title="${action.getText('deviceOriginalNetStatistics.list.title')}" 
		includeParameters="deviceType.id|month" 
		fieldMap="like:" excel=false setupTable=false >
            <@vcolumn title="${action.getText('deviceOriginalNetStatistics.deviceNum')}"  property="deviceNum">
            </@vcolumn>
          	<@vcolumn title="${action.getText('deviceOriginalNetStatistics.originaValue')}"  property="originaValue">
          	</@vcolumn>
            <@vcolumn title="${action.getText('deviceOriginalNetStatistics.netValue')}"  property="netValue">
            </@vcolumn>
			<@vcolumn title="${action.getText('deviceOriginalNetStatistics.curDeprecitionSum')}"  property="curDeprecitionSum">
			</@vcolumn>
			<@vcolumn title="${action.getText('deviceOriginalNetStatistics.originaOccupancy')}"  property="originaOccupancy">
			</@vcolumn>
			<@vcolumn title="${action.getText('deviceOriginalNetStatistics.netOccupancy')}"  property="netOccupancy">
			</@vcolumn>
			<@vcolumn title="${action.getText('deviceOriginalNetStatistics.month')}"  property="month">
			</@vcolumn>
	</@list>  
</@ww.form>

</@htmlPage>
