<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>设备名称</th>
	        		<th>设备型号</th>
	        		<th>设备规格</th>
	        		<th>单价</th>
	        		<th>数量</th>
	        		<th>总价</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/appendDevice/editAppendDeviceDetail.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">机器人</td>
            		<td style="text-align:left">SB02312</td>
            		<td style="text-align:left">Wx1212</td>
            		<td style="text-align:right">1000</td>  
            		<td style="text-align:right">1</td>   
            		<td style="text-align:right">1000</td>          		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/appendDevice/editAppendDeviceDetail.html',750,400);return false;">2</a></td>
            		<td style="text-align:left">机床</td>
            		<td style="text-align:left">SB023542</td>
            		<td style="text-align:left">Wx121254</td>
            		<td style="text-align:right">1000</td>  
            		<td style="text-align:right">1</td>   
            		<td style="text-align:right">1000</td>            		
            	</tr>

            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/appendDevice/editAppendDeviceDetail.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>