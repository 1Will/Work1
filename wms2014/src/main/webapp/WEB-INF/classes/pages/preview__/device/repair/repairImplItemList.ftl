<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修实施列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>项目名称</th>
	        		<th>设备类型</th>
	        		<th>设备规格</th>
	        		<th>维修部位</th>
	        		<th>计划维修工时(时)</th>
	        		<th>计划开工时间</th>
	        		<th>计划完工时间</th>
	        		<th>实施维修内容摘要</th>
	        		<th>实施维修工时(时)</th>
	        		<th>实施单位</th>
	        		<th>备注</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRepairImplItem.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">机床维修</td>
            		<td style="text-align:left">444kljljh</td>
            		<td style="text-align:left">12*255</td>
            		<td style="text-align:left">床身</td>
            		<td style="text-align:right">20</td>
            		<td>2007-05-01</td>
            		<td>2007-05-05</td>
            		<td style="text-align:left">......</td>
            		<td style="text-align:right">20</td>
            		<td style="text-align:left">维修部</td>
            		<td style="text-align:left">.......</td>
            	
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editRepairImplItem.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>