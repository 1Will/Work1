<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />


<@htmlPage title="申购单明细列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>项目号</th>
			 	<th>设备名称</th>
				<th>设备型号</th>
				<th>设备类别</th>
				<th>设备数量</th>				
				<th>设备单价</th>
				<th>设备总价</th>
				<th>费用来源</th>
				<th>供应商</th>
				<th>备注</th>
			<tr>
				<td style="text-align:left">JH-021</td>
				<td style="text-align:left">轴承</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:left">计划内</td>
				<td style="text-align:left">TCL</td>
				<td style="text-align:left">...</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
	 </@ww.form>
</@htmlPage>