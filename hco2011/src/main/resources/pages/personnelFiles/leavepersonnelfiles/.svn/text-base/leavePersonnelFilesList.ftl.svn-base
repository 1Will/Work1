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


<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('personnelFilesManager')}">
	<@ww.form name="'listFrom'" namespace="'/personnelFile'" action="'searchLeavePersonnelFile'" method="'post'" >
		<@ww.token name="searchLeavePersonnelFileToken"/>
		<#include "./leavePersonnelFilesSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
	<@list title="${action.getText('personnelFilesList')}" includeParameters="code|name|fileNo|inst.id|dept.id|duty.id|readOnly|onlyInvalid|onlyValid" fieldMap="like:code|name|fileNo" >
	    <@vcolumn title="${action.getText('personnel.code')}" property="code" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.name')}" property="name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.fileNo')}" property="fileNo" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.inst')}" property="inst.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.dept')}" property="dept.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.duty')}" property="duty.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.mobile')}" property="mobile" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.telphone')}" property="telphone" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.leaveDate')}" property="leaveDate" format="yyyy-MM-dd" sortable="desc">
        	<@alignCenter/>
        </@vcolumn>
	</@list>
</@ww.form>
</@htmlPage>
