<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="预算发生详细">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>预算编号</th>
			 	<th>预算名称</th>
			 	<th>预算部门</th>
			 	<th>预算费用(元)</th>
			 	<th>已预定费用(元)</th>
			 	<th>预发生费用(元)</th>
			 	<th>预算制定日期</th>
			 	<th>执行状态</th>
			 	<th>预算执行日期</th>
			 	<th>备注</th>
			 	<tr>
			<tr>
				<td style="text-align:left">08072001</td>
				<td style="text-align:left">新增设备采购预算</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">200000</td>
				<td style="text-align:right">80000</td>
				<td style="text-align:right">60000</td>
				<td>2008-7-23</td>
				<td style="text-align:left">已完成</td>	
				<td>2008-8-01</td>
				<td style="text-align:left">...</td>
					
			</tr>
			<tr>
				<td style="text-align:left">08072002</td>
				<td style="text-align:left">大项修预算</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">80000</td>	
				<td style="text-align:right">80000</td>
				<td style="text-align:right">60000</td>
				<td>2008-7-20</td>
				<td style="text-align:left">执行中</td>
				<td>2008-8-05</td>
				<td style="text-align:left">...</td>
				
			</tr>
			<tr>
				<td style="text-align:left">08072003</td>
				<td style="text-align:left">设备预算</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">90000</td>
				<td style="text-align:right">70000</td>
				<td style="text-align:right">60000</td>	
				<td>...</td>
				<td style="text-align:left">预定</td>
				<td>...</td>
				<td style="text-align:left">等待制定</td>
			</tr>			
	     	</@listTable>
	     	</table>
     </@ww.form>
</@framePage>