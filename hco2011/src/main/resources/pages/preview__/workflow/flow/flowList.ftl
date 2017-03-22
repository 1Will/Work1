<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: custContactReportList.ftl 2010-04-06 wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="${action.getText('流程查询')}">
	<@ww.form name="'listForm'" action="'listToDoTask_'" method="'post'">
		<@ww.token name="listToDoTask_ReportToken"/>
		<#include "./flowSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
        
		<@listTable>
        	<tr>
        		<th>编号</th>
        		<th>流程编码</th>
			 	<th>流程名称</th>
			 	<th>工作时限（天）</th>
			 	<th>是否启用</th>
			</tr>
			<tr>
				<td>1</td>
				<td style="text-align:left"><a href="editFlow_.html">QJD</a></td>
				<td style="text-align:left">请假单</td>
				<td style="text-align:left">15</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td>2</td>
				<td style="text-align:left"><a href="editFlow_.html">TXD</a></td>
				<td style="text-align:left">调休单</td>
				<td style="text-align:left">15</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td>3</td>
				<td style="text-align:left"><a href="editFlow_.html">JBD</a></td>
				<td style="text-align:left">加班单</td>
				<td style="text-align:left">15</td>
				<td style="text-align:left">是</td>
			</tr>
        </@listTable>
    </@ww.form>
<script language="javascript">
//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
</script>
</@htmlPage>