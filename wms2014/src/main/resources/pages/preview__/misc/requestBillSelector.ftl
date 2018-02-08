<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="申购单选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择申购单</th>
			 	<th>申购单编号</th>
			 	<th>申购单名称</th>
			 	<th>设备名称</th>
			 	<th>设备类型</th>
			 	<th>设备规格</th>
			 	<th>设备数量</th>
			 	<th>设备单价</th>
			 	<th>申购总价</th>
			 	<th>申购人</th>
			 	<th>申购日期</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">申购机床</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">Tom</td>
				<td >2007-07-04</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">申购机床</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">Tom</td>
				<td >2007-07-04</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">申购机床</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">100</td>
				<td style="text-align:right">100</td>
				<td style="text-align:left">Tom</td>
				<td >2007-07-04</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>