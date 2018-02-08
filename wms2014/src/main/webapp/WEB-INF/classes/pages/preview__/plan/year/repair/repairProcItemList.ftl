<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="大项修实施明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>项目名称</th>
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<th>设备型号</th>
			 	<th>设备规格</th>
			 	<th>类别</th>
			 	<th>使用单位</th>
			 	<th>承修单位</th>
			 	<th>计划修理时间(月)</th>
			 	<th>复杂系数</th>
			 	<th>停歇天数</th>
			 	<th>维修部位</th>
			 	<th>维修内容摘要</th>
			 	<th>实际开工时间</th>
			 	<th>实际完工时间</th>
				<#--<th>维修工时(时)</th>
			 	<th>预计材料费用(元)</th>
			 	<th>预计维修费用(元)</th>-->
			 	<th>操作</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editYearRepairPlanItem.html',650,400);return false;">08072001</a></td>
				<td style="text-align:left">机床修理</td>
				<td style="text-align:left">20080809110401000</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">大</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:left">外协</td>
				<td style="text-align:right">12</td>
				<td style="text-align:left">机械</td>
				<td style="text-align:right">4</td>
				<td style="text-align:left">床身</td>
				<td style="text-align:left">.......</td>
				<td>2007-07-01</td>
				<td>2007-07-05</td>
				<#--<td style="text-align:right">20</td>
				<td style="text-align:right">10000</td>
				<td style="text-align:right">50000</td>-->
				<td><input type="button" class="button" value="详细"  onclick="popup();"/></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editYearRepairPlanItem.html',650,400);return false;">08072054</a></td>
				<td style="text-align:left">压缩机维修</td>
				<td style="text-align:left">20080809110402131</td>
				<td style="text-align:left">螺杆压缩机</td>
				<td style="text-align:left">01</td>
				<td style="text-align:left">50*80</td>
				<td style="text-align:left">项</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:left">外协</td>
				<td style="text-align:right">10</td>
				<td style="text-align:left">电器</td>
				<td style="text-align:right">15</td>
				<td style="text-align:left">螺杆</td>
				<td style="text-align:left">.......</td>
				<td>2007-07-01</td>
				<td>2007-07-05</td>
				<#--<td style="text-align:right">20</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:right">1000</td>-->
				<td><input type="button" class="button" value="详细"  onclick="popup();"/></td>
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editYearRepairPlanItem.html',650,400);"/>
     	<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
     </@buttonBar>
     <script>
		function popup() {
			popupModalDialog('${req.contextPath}/preview/plan/year/repair/editPreRepairProcDetail.html?proc=1',1024,768);
		}
	 </script>
     </@ww.form>
</@framePage>