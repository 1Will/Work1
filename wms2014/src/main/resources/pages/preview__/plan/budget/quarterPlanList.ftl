<#include "/pages/includes/eam2008.ftl" />
<@htmlPage title="季度计划查询">
	 <@ww.form name="'yearBudget'" action="''" method="'post'"> 
	  <#include "./quarterPlanSearcher.ftl"/>
		<@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/preview/plan/budget/editQuarterPlan.html"/> 
        	<@redirectButton value="申报" url="${req.contextPath}/preview/plan/budget/editQuarterPlan.html"/> 
        </@buttonBar>      
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th width="5%"><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
                <th>计划编号</th>
			 	<th>计划名称</th>
			 	<th>分类</th>
                <th>年度</th>
                <th>季度</th>
                <th>部门</th>
			 	<th>费用</th>
			 	<th>计划制定时间</th>
			 	<th>制定人</th>
			 	<th>状态</th>
			 	<th>计划状态</th>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/plan/budget/editQuarterPlan.html">2008072911-1</a></td>
				<td style="text-align:left">2008冲焊一厂第一季度计划(计划外)</td>
				<td style="text-align:left">工装</td>
				<td style="text-align:left">2008</td>
				<td style="text-align:left">第一季度</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">150000</td>
				<td>2008-02-10</td>
				<td style="text-align:left">System</td>
				<td style="text-align:left">未审批</td>
				<td style="text-align:left">计划外</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/plan/budget/editQuarterPlan.html">2008072911-2</a></td>
				<td style="text-align:left">2008冲焊一厂第一季度计划(计划中)</td>
				<td style="text-align:left">工装</td>
				<td style="text-align:left">2008</td>
				<td style="text-align:left">第一季度</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">150000</td>
				<td>2008-02-10</td>
				<td style="text-align:left">System</td>
				<td style="text-align:left">未审批</td>
				<td style="text-align:left">计划中</td>
			</tr>
	     	</@listTable>
	    </table>
	    <@buttonBar>
	        <@vsubmit name="'delete'" value="'${action.getText('delete')}'" />      
        	<@redirectButton value="预览汇总" url="#"/>
        </@buttonBar>
     </@ww.form>
</@htmlPage>