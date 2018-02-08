<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>设备编号</th>
	        		<th>设备名称</th>
	        		<th>维修部位</th>
	        		<th>维修内容摘要</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">123--459</td>
            		<td style="text-align:left">机器人</td>
            		<td style="text-align:left">手臂</td>
            		<td style="text-align:left">...</td>            		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);return false;">2</a></td>
            		<td style="text-align:left">123--459</td>
            		<td style="text-align:left">机器人</td>
            		<td style="text-align:left">制动</td>
            		<td style="text-align:left">...</td>            		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);return false;">3</a></td>
            		<td style="text-align:left">123--459</td>
            		<td style="text-align:left">机器人</td>
            		<td style="text-align:left">螺栓</td>
            		<td style="text-align:left">...</td>            		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);return false;">4</a></td>
            		<td style="text-align:left">123--459</td>
            		<td style="text-align:left">机器人</td>
            		<td style="text-align:left">行程开关</td>
            		<td style="text-align:left">...</td>            		
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>