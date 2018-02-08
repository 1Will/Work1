<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@framePage title="随机设备查询">
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
			 	<th>安装地点</th>
			 	<th>建卡时间</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRandDevice.html',600,300);return false;">07010102-02</a></td>
				<td style="text-align:left">07010102-02</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">总装一厂</td>
				<td>2007-08-12</td>		
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRandDevice.html',600,300);return false;">53010000-01</a></td>
				<td style="text-align:left">53010000-01</td>
				<td style="text-align:left">螺杆压缩机</td>
				<td style="text-align:left">01</td>
				<td style="text-align:left">50*80</td>
				<td style="text-align:left">单螺杆压缩机</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">总装一厂</td>
				<td>2007-08-12</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editRandDevice.html',600,300);"/>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     	</@buttonBar>
     </@ww.form>
</@framePage>