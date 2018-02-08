<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="费用列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>费用项目</th>
	        		<th>费用来源</th>
	        		<th>预算编号</th>
	        		<th>费用额(元)</th>
	        		<#if req.getParameter("proc")?exists>
	        			<th>实际费用(元)</th>
	        		</#if>
	        		<th>备注</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleFee.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">维修费</td>
            		<td style="text-align:left">预算内</td>
            		<td style="text-align:left">YS-01</td>
            		<td style="text-align:right">6</td>
            		<#if req.getParameter("proc")?exists>
            			<td>12</td>
            		</#if>
            		<td style="text-align:left">.....</td>
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleFee.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>
    