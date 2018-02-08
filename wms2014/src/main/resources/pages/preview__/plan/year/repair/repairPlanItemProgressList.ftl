<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />

<@framePage title="大项修计划发生详细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	 		<@listTable>
	 			<tr>
	 				<th>项目号</th>
	 				<th>设备编号</th>
	 				<th>设备名称</th>
	 				<th>预计维修开始时间</th>
	 				<th>预计维修结束时间</th>
	 				<th>预计材料费用(元)</th>
	 				<th>预计维修费用(元)</th>
	 				<th>实际维修开始时间</th>
	 				<th>实际维修结束时间</th>
	 				<th>实际材料费用(元)</th>
	 				<th>实际维修费用(元)</th>
	 				<th>维修人</th>
	 				<th>实施情况</th>
	 				<th>备注</th>
	 			</tr>
	 			<tr>
	 				<td style="text-align:left">08072001</td>
	 				<td style="text-align:left">20080809110401000</td>
					<td style="text-align:left">精密普通机床</td>
					<td>2007-8-1</td>
					<td>2007-8-2</td>
					<td style="text-align:right">100</td>
					<td style="text-align:right">500</td>
					<td>2007-8-1</td>
					<td>2007-8-2</td>
					<td style="text-align:right">200</td>
					<td style="text-align:right">500</td>
					<td style="text-align:left">TOM</td>
					<td style="text-align:left">完成</td>
					<td style="text-align:left">.....</td>
	 			</tr>
	 			<tr>
	 				<td style="text-align:left">08072888</td>
	 				<td style="text-align:left">压缩机维修</td>
	 				<td style="text-align:left">20080809110402131</td>
					<td style="text-align:left">单螺杆压缩机</td>
					<td>2007-8-1</td>
					<td>2007-8-2</td>
					<td style="text-align:right">100</td>
					<td style="text-align:right">500</td>
					<td>2007-8-1</td>
					<td></td>
					<td style="text-align:right"></td>
					<td style="text-align:right"></td>
					<td style="text-align:left">TOM</td>
					<td style="text-align:left">修理中</td>
					<td style="text-align:left">.....</td>
	 			</tr>
	 		</@listTable>
	 	</table>
	 </@ww.form>
</@framePage>