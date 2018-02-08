<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="备件信息列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
              	<th>项目号</th>
			 	<th>备件编号</th>
			 	<th>备件名称</th>
			 	<th>备件型号</th>
			 	<th>备件规格</th>
			 	<th>备件类别</th>
			 	<th>备件单价</th>
			 	<th>使用数量(件)</th>
			 	<th>备件总价</th>

			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairSpares.html',750,400);return false;">123</a></td>
				<td style="text-align:left">07010102-02</td>
				<td style="text-align:left">机床轴承</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">120</td>
				<td style="text-align:right">120</td>
				<td style="text-align:right">14000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairSpares.html',750,400);return false;">145</a></td>
				<td style="text-align:left">07010102-02</td>
				<td style="text-align:left">机床轴承</td>
				<td style="text-align:left">CSM6102</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">100</td>	
				<td style="text-align:right">100</td>
				<td style="text-align:right">10000</td>		
			</tr>
	     	</@listTable>
	     	</table>
	     	
	     	<@buttonBar>
	    	<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editRepairSpares.html',750,400);"/>
	    	<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>