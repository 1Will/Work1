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
<#-- $Id: deviceWasterStatisticsList.ftl 2009-09-23 14:42:50Z wliu $-->



<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('deviceWasterStatistics.title')}">
<@ww.form name="'listForm'" action="'searchDeviceWasterStatistics'" method="'post'">
    <#include "./deviceWasterStatisticsSearcher.ftl" />
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
	<@list title="${action.getText('deviceWasterStatistics.list.title')}" 
		includeParameters="deviceType.id|filiale.id|department.id|supplier.id|deviceCard.deviceNo|month" 
		fieldMap="like:deviceCard.name" excel=false setupTable=false >
            
            <@vcolumn title="${action.getText('deviceWasterStatistics.deviceCard.deviceNo')}" property="deviceCard.deviceNo">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.deviceCard.name')}" property="deviceCard.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.deviceCard.model')}" property="deviceCard.model">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.deviceCard.specification')}" property="deviceCard.specification">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.deviceType')}" property="deviceCard.deviceType.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.filiale')}" property="deviceCard.filiale.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.department')}" property="deviceCard.department.name" attributes="width='100'">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.leader')}" property="leader">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.supplier')}" property="deviceCard.supplier.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.scrappedDate')}" property="scrappedDate" format="yyyy-MM-dd" attributes="width='70'">
                        <@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.scrappedReasons')}" property="scrappedReasons">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.originalValue')}" property="originalValue" attributes="width='70'">
                        <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.netValue')}" property="netValue" attributes="width='70'">
                        <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceWasterStatistics.curDeprecitionSum')}" property="curDeprecitionSum" attributes="width='70'">
                        <@alignRight />
            </@vcolumn>
			<@vcolumn title="${action.getText('deviceWasterStatistics.month')}"  property="month" attributes="width='70'">
			            <@alignLeft />
            </@vcolumn>
	</@list>  
</@ww.form>

</@htmlPage>