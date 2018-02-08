<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>项目名称</th>
	        		<th>设备类型</th>
	        		<th>设备规格</th>
	        		<th>购机时间</th>
	        		<th>维修部位</th>
	        		<th>维修内容摘要</th>
	        		<th>维修工时(时)</th>
	        		<th>责任单位</th>
	        		<th>责任人</th>
	        		<th>备注</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">机床</td>
            		<td style="text-align:left">20mm*300mm</td>
            		<td >2007-08-01</td>
            		<td style="text-align:left">床身</td>
            		<td style="text-align:left">......</td>
            		<td style="text-align:right">30</td>
            		<td style="text-align:left">维修部</td>
            		<td style="text-align:left">sa</td>
            		<td style="text-align:left">.....</td>
            	
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editRepairPlanItem.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>