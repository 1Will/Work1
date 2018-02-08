<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装保养计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
		 <#include "maintenancePlanSearcher.ftl"/>
	 	<@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editMaintenancePlan.html"/>     
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	        	<tr>
	        		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	        		<th>维修计划编号</th>
	        		<th>维修计划名称</th>
	        		<th>工装编号</th>
	        		<th>工装名称</th>
	        		<th>工装图号</th>
	        		<th>部门</th>
	        		<th>计划执行日期</th>
	        		<th>负责人</th>
	        		<th>状态</th>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="editMaintenancePlan.html" >2008070122100</a></td>
	        		<td style="text-align:left">机床维修</td>
	        		<td style="text-align:left">JAC-2131</td>
	        		<td style="text-align:left">机床</td>
	        		<td style="text-align:left"></td>
	        		<td style="text-align:left">机动部</td>
	        		<td >2007-7-1</td>
	        		<td style="text-align:left">TOM</td>
	        		<td style="text-align:left">未审批</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="editMaintenancePlan.html">2008070155600</a></td>
	        		<td style="text-align:left">压缩机维修</td>
	        		<td style="text-align:left">JAC-2145</td>
	        		<td style="text-align:left">压缩机</td>
	        		<td style="text-align:left"></td>
	        		<td style="text-align:left">机动部</td>
	        		<td>2007-7-1</td>
	        		<td style="text-align:left">TOM</td>
	        		<td style="text-align:left">未审批</td>
	        	</tr>
	        </@listTable>
	    </table>
	    <@buttonBar>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
	 </@ww.form>
</@htmlPage>