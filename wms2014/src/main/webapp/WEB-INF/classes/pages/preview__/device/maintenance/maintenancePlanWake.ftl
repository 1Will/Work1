<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养工作提醒">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>设备编号</th>
				<th>资产编号</th>
				<th>设备名称</th>
				<th>设备型号</th>
				<th>设备规格</th>
				<th>设备类别</th>
				<th>使用部门</th>
				<th>工单编号</th>
				<th>保养级别</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>保养负责人</th>
				<th>保养工时</th>
				<th>保养内容</th>
				<th>保养要求</th>
				<th>提醒信息</th>
				<th>提醒内容</th>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenanceWakeReader.html');return false;">07010102-02</a></td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">08021102392</td>
				<td style="text-align:left">一保</td>
				<td>2007-09-22 12:30</td>
				<td>2007-09-25 12:30</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">72H</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">无实施计划</td>
				<td style="text-align:left">....</td>
			</tr>			
	     	</@listTable>
	     	</table>	
	   </@ww.form>   
</@framePage>		 