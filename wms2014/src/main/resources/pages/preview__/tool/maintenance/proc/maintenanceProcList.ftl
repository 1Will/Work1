<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="预防性保养实施查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
		 <#include "maintenanceProcSearcher.ftl"/>
	 	<@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editMaintenanceProc.html"/>     
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	        	<tr>
	        		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	        		<th>保养实施编号</th>
	        		<th>保养实施名称</th>
	        		<th>工装编号</th>
	        		<th>工装名称</th>
	        		<th>工装图号</th>
	        		<th>部门</th>
	        		<th>实际费用</th>
	        		<th>计划开始日期</th>
	        		<th>实施日期</th>
	        		<th>负责人</th>
	        		<th>状态</th>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="editMaintenanceProc.html" >2008070122100</a></td>
	        		<td style="text-align:left">机床保养</td>
	        		<td style="text-align:left">JAC-2131</td>
	        		<td style="text-align:left">机床</td>
	        		<td style="text-align:left"></td>
	        		<td style="text-align:left">机动部</td>
	        		<td style="text-align:right">500</td>
	        		<td >2007-7-1</td>
	        		<td >2007-7-1</td>
	        		<td style="text-align:left">TOM</td>
	        		<td style="text-align:left">未提交</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="editMaintenanceProc.html">2008070155600</a></td>
	        		<td style="text-align:left">压缩机保养</td>
	        		<td style="text-align:left">JAC-2145</td>
	        		<td style="text-align:left">压缩机</td>
	        		<td style="text-align:left"></td>
	        		<td style="text-align:left">机动部</td>
	        		<td style="text-align:right">500</td>
	        		<td >2007-7-1</td>
	        		<td>2007-7-1</td>
	        		<td style="text-align:left">TOM</td>
	        		<td style="text-align:left">正在审核</td>
	        	</tr>
	        </@listTable>
	    </table>
	    <@buttonBar>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
	 </@ww.form>
</@htmlPage>