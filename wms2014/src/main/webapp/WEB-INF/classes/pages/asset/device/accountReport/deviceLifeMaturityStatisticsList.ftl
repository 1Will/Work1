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
<#-- $Id: deviceLifeMaturityStatisticsList.ftl 2009-09-23 12:58:50Z wliu $-->



<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('deviceLifeMaturityStatistics.title')}">
<@ww.form name="'listForm'" action="'searchDeviceLifeMaturityStatistics'" method="'post'">
    <#include "./deviceLifeMaturityStatisticsSearcher.ftl" />
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
	<@list title="${action.getText('deviceLifeMaturityStatistics.list.title')}" 
		includeParameters="deviceType.id|filiale.id|department.id|supplier.id|deviceCard.deviceNo|month" 
		fieldMap="like:deviceCard.name" excel=false setupTable=false >
            
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.deviceCard.deviceNo')}" property="deviceCard.deviceNo">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.deviceCard.name')}" property="deviceCard.name">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.deviceCard.model')}" property="deviceCard.model">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.deviceCard.specification')}" property="deviceCard.specification">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.deviceType')}" property="deviceCard.deviceType.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.filiale')}" property="deviceCard.filiale.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.department')}" property="deviceCard.department.name" attributes="width='70'">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.leader')}" property="leader">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.supplier')}" property="deviceCard.supplier.name">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.installationDate')}" property="installationDate" format="yyyy-MM-dd" attributes="width='70'">
                        <@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.useYear')}" property="useYear">
                        <@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.openingDate')}" property="openingDate" format="yyyy-MM-dd" attributes="width='70'">
                        <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.expirationDate')}" property="expirationDate" format="yyyy-MM-dd" attributes="width='70'">
                        <@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.originalValue')}" property="originalValue" attributes="width='70'">
                        <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.netValue')}" property="netValue" attributes="width='70'">
                        <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceLifeMaturityStatistics.curDeprecitionSum')}" property="curDeprecitionSum" attributes="width='70'">
			            <@alignRight />
            </@vcolumn>
			<@vcolumn title="${action.getText('deviceLifeMaturityStatistics.month')}"  property="month" attributes="width='70'">
			            <@alignCenter />
            </@vcolumn>
	</@list>  
</@ww.form>

</@htmlPage>