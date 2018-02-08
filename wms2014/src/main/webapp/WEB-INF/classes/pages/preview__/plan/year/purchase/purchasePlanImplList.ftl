<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<@framePage title="采购计划实施">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>项目号</th>
			 	<th>设备名称</th>
			 	<th>设备型号</th>
			 	<th>设备规格</th>
			 	<th>设备类别</th>
			 	<th>计划采购数量(件)</th>
			 	<th>实际采购数量(件)</th>
			 	<th>计划单价(元)</th>
			 	<th>实际单价(元)</th>
			 	<th>计划总金额(元)</th>
			 	<th>实际总金额(元)</th>
			 	<th>执行采购时间</th>
			 	<th>完成采购时间</th>
			<tr>
			<tr>
			<#--
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPurchasePlanItem.html',800,300);return false;">08072001</a></td>
			-->
				<td style="text-align:left">08072001</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:right">10000</td>
				<td style="text-align:right">10000</td>
				<td>2007-08-22</td>	
				<td>2007-08-25</td>	
			</tr>
			<tr>
				<td style="text-align:left">08072002</td>
				<td style="text-align:left">螺杆压缩机</td>
				<td style="text-align:left">01</td>
				<td style="text-align:left">50*80</td>
				<td style="text-align:left">单螺杆压缩机</td>
				<td style="text-align:right">120</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">500</td>
				<td style="text-align:right">300</td>
				<td style="text-align:right">60000</td>
				<td style="text-align:right">36000</td>
				<td>2007-08-22</td>	
				<td>2007-08-25</td>	
			</tr>
	     	</@listTable>
	     	</table>
     </@ww.form>
</@framePage>