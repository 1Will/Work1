<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养基本信息">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>保养标准编号</th>
			 	<th>设备编号</th>
				<th>资产编号</th>
				<th>设备名称</th>
				<th>设备型号</th>
				<th>设备规格</th>
				<th>设备类别</th>
				<th>使用部门</th>
				<th>定标日期</th>
				<th>定标人</th>
				<th>状态</th>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/editMaintenanceRule.html',800,600);return false;">07010102-02</a></td>
				<td style="text-align:left">by001</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">总装一厂</td>
				<td>2007-08-12</td>		
				<td style="text-align:left">system</td>
				<td style="text-align:left">已签字</td>
			</tr>			
	     	</@listTable>
	     	</table>	
	     	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/device/maintenance/editMaintenanceRule.html',800,600)"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>  	
	   </@ww.form>   
</@framePage>		 