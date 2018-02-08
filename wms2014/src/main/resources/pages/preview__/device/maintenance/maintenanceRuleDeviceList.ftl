<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="设备保养标准">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>保养部位</th>
			 	<th>方法</th>
			 	<th>要求</th>
			 	<th>类别</th>
			<tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:center"><a href="#" onclick="return false;">1</a></td>
				<td style="text-align:left">外观保养</td>
				<td style="text-align:left">清洗</td>
				<td style="text-align:left">1清洗机床外表及死角，无油污黄袍，拆卸罩盖，清除带轮油污。</td>
				<td style="text-align:left">一保</td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:center"><a href="#" onclick="return false;">2</a></td>
				<td style="text-align:left">传动系统</td>
				<td style="text-align:left">调整、检查</td>
				<td style="text-align:left">调整传送带松紧，检查调整离合器、制动器摩擦片的间隙。</td>
				<td style="text-align:left">一保</td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:center"><a href="#" onclick="return false;">3</a></td>
				<td style="text-align:left">气路系统</td>
				<td style="text-align:left">检查、清洗</td>
				<td style="text-align:left">检查离合器、平衡器及气垫气压，检查气动元件是否漏气，油雾器是否出油，清洗滤油器。</td>
				<td style="text-align:left">一保</td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:center"><a href="#" onclick="return false;">4</a></td>
				<td style="text-align:left">润滑系统</td>
				<td style="text-align:left">检查</td>
				<td style="text-align:left">检查手压泵及油室，油路是否畅通添加新油，传动齿轮加润滑脂，检查油压表是否完好。</td>
				<td style="text-align:left">一保</td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:center"><a href="#" onclick="return false;">5</a></td>
				<td style="text-align:left">电器</td>
				<td style="text-align:left">检查</td>
				<td style="text-align:left">检查各限位是否可靠，擦洗电器箱，电动机。检查电器线路及元件。</td>
				<td style="text-align:left">一保</td>
			</tr>
	     	</@listTable>
	     	</table>
	    <@buttonBar>
	    	<@redirectButton value="新建" url="#"/>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@framePage>