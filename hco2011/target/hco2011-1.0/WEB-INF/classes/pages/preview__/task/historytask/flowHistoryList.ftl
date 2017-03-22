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

<@htmlPage title="${action.getText('待办任务')}">
	<@ww.form name="'listForm'" action="'listToDoTask_'" method="'post'">
		<@ww.token name="listToDoTask_ReportToken"/>
		<@listTable>
        	<tr>
        		<th>编号</th>
        		<th>任务编码</th>
			 	<th>任务名称</th>
			 	<th>环节名称</th>
			 	<th>开始时间</th>
			 	<th>操作</th>
			</tr>
			<tr>
				<td>1</td>
				<td style="text-align:left"><a href="editToDoTask_.html">001</a></td>
				<td style="text-align:left">请假单</td>
				<td style="text-align:left">请假单主管审核</td>
				<td style="text-align:left">2011-02-10</td>
				<td style="text-align:left"><a href="editToDoTask_.html">查看</a></td>
			</tr>
			<tr>
				<td>2</td>
				<td style="text-align:left"><a href="editToDoTask_.html">002</a></td>
				<td style="text-align:left">请假单</td>
				<td style="text-align:left">请假单主管审核</td>
				<td style="text-align:left">2011-02-12</td>
				<td style="text-align:left"><a href="editToDoTask_.html">查看</a></td>
			</tr>
			<tr>
				<td>3</td>
				<td style="text-align:left"><a href="editToDoTask_.html">003</a></td>
				<td style="text-align:left">请假单</td>
				<td style="text-align:left">请假单主管审核</td>
				<td style="text-align:left">2011-02-15</td>
				<td style="text-align:left"><a href="editToDoTask_.html">查看</a></td>
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