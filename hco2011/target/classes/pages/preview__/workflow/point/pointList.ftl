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

<@framePage>
	<@ww.form name="'listForm'" action="'listToDoTask_'" method="'post'">
		<@ww.token name="listToDoTask_ReportToken"/>
		<@listTable>
        	<tr>
        	    <th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编号</th>
        		<th>节点名称</th>
			 	<th>负责人</th>
			 	<th>前一节点</th>
			 	<th>后一节点</th>
			 	<th>是否启用</th>
			</tr>
			<tr>
			    <td><input type="checkbox" name="checkbox" value="true"></td>
				<td>1</td>
				<td style="text-align:left" onclick="openPage('${req.contextPath}/point_/editPoint_.html');">组长审核</td>
				<td style="text-align:left">组长A</td>
				<td style="text-align:left">开始节点</td>
				<td style="text-align:left">部门经理审核</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>2</td>
				<td style="text-align:left">部门经理A</td>
				<td style="text-align:left" onclick="openPage('${req.contextPath}/point_/editPoint_.html');">部门经理审核</td>
				<td style="text-align:left">组长审核</td>
				<td style="text-align:left">人事经理审核</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>3</td>
				<td style="text-align:left" onclick="openPage('${req.contextPath}/point_/editPoint_.html');">人事经理审核</td>
				<td style="text-align:left">人事经理A</td>
				<td style="text-align:left">部门经理审批</td>
				<td style="text-align:left">总经理审批</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>4</td>
				<td style="text-align:left" onclick="openPage('${req.contextPath}/point_/editPoint_.html');">总经理审批</td>
				<td style="text-align:left">总经理A</td>
				<td style="text-align:left">人事经理审核</td>
				<td style="text-align:left">结束节点</td>
				<td style="text-align:left">是</td>
			</tr>
        </@listTable>
        <@buttonBar>
			<@htmlButton value="${action.getText('new')}" onclick="openPage('${req.contextPath}/point_/editPoint_.html')"/>
			<@redirectButton value="${action.getText('删除')}" url="${req.contextPath}/advisoryManager/editAdvisory.html"/>
        </@buttonBar>
    </@ww.form>
<script language="javascript">
//失去焦点隐藏导航层
function onBlur(op)
{
	getObjByName(op).style.display="none";
}

function openPage()
{
    var url = "${req.contextPath}/point_/editPoint_.html";
    popupModalDialog(url, 800, 600);
}
</script>
</@framePage>