<#include "/pages/includes/eam2008.ftl" />
<@htmlPage title="年度预算查询">
	 <@ww.form name="'yearBudget'" action="''" method="'post'"> 
	  <#include "./yearBudgetSearcher.ftl"/>
		<@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/preview/plan/budget/editYearBudget.html"/> 
        </@buttonBar>      
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th width="5%"><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
                <th>预算编号</th>
			 	<th>预算名称</th>
			 	<th>分类</th>
                <th>年度</th>
                <th>部门</th>
			 	<th>费用</th>
			 	<th>预算制定时间</th>
			 	<th>制定人</th>
			 	<th>状态</th>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/plan/budget/editYearBudget.html">2008072911</a></td>
				<td style="text-align:left">2008冲焊一厂</td>
				<td style="text-align:left">工装</td>
				<td style="text-align:left">2008</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">600000</td>
				<td>2007-8-25</td>
				<td style="text-align:left">System</td>
				<td style="text-align:left">未审批</td>
			</tr>
	     	</@listTable>
	    </table>
	    <@buttonBar>
	        <@vsubmit name="'delete'" value="'${action.getText('delete')}'" />      
        	<@redirectButton value="预览汇总" url="#"/>
        	<@redirectButton value="生成季度计划" url="#"/>    
        </@buttonBar>
     </@ww.form>
</@htmlPage>