<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="采购计划详细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>设备名称</th>
			 	<th>设备型号</th>
			 	<th>设备规格</th>
			 	<th>设备类别</th>
			 	<th>设备数量(件)</th>
			 	<th>设备单价(元)</th>
			 	<th>设备总价(元)</th>
				<th>预计采购发生时间</th>

			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPurchasePlanItem.html',750,300);return false;">1</a></td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">750</td>
				<td style="text-align:right">7500</td>
				<td>2007-08-23</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPurchasePlanItem.html',750,300);return false;">2</a></td>
				<td style="text-align:left">螺杆压缩机</td>
				<td style="text-align:left">01</td>
				<td style="text-align:left">50*80</td>
				<td style="text-align:left">单螺杆压缩机</td>
				<td style="text-align:right">120</td>
				<td style="text-align:right">500</td>
				<td style="text-align:right">75000</td>
				<td>2007-09-23</td>
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editPurchasePlanItem.html',750,300);"/>
     	<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
     </@buttonBar>
     </@ww.form>
</@framePage>