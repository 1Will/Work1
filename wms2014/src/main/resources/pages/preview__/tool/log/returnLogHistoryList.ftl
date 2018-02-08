<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="工装归还管理">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>领用记录编号</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>领用人</th>
			 	<th>领用时间</th>
			 	<th>使用设备</th>
			 	<th>生产数量</th>
			 	<th>归还人</th>
			 	<th>归还时间</th>
			 	<th>损坏状态</th>
			 	<th>验收人</th>
			 	<th>验收日期</th>
			 	<th>保管员</th>
			 	<th>保管日期</th>
			<tr>
			<tr>	
				<td style="text-align:left">JAC-LY-2008072911</td>
				<td style="text-align:left">2008072911</td>
				<td style="text-align:left">测量工具</td>
				<td style="text-align:right">2008072911</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-23</td>
				<td>轴承</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">sa1</td>
				<td >2008-7-30</td>
				<td style="text-align:left">无</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-20</td>
				<td style="text-align:left">system</td>
				<td >2008-7-23</td>
			</tr>			
	     	</@listTable>
	     	</table>
         
     </@ww.form>
</@framePage>