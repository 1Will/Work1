<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@framePage title="工装领用管理">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 		 
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>领用人</th>
			 	<th>领用日期</th>
			 	<th>使用设备</th>
			 	<th>生产数量</th>
			 	<th>产品尾件状况</th>
			 	<th>验收人</th>
			 	<th>验收日期</th>
			 	<th>保管员</th>
			 	<th>入库日期</th>
			<tr>
			<tr>	
				<td style="text-align:left">sa</td>
				<td >2008-7-23</td>
				<td>轴承</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">sa</td>
				<td >2008-7-23</td>
				<td style="text-align:left">system</td>
				<td >2008-7-23</td>
			</tr>			
	     	</@listTable>
	     	</table>
     

     </@ww.form>
</@framePage>