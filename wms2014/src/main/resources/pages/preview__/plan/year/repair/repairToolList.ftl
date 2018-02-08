<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="工具列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>工具名称</th>
	        		<th>工具规格</th>
	        		<th>工具型号</th>
	        		<th>计量单位</th>
	        		<th>使用数量</th>
	        		<#if req.getParameter("proc")?exists>
	        			<th>实际使用数量</th>
	        		</#if>
	        		<th>备注</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleTool.html',600,350);return false;">1</a></td>
            		<td style="text-align:left">钳子</td>
            		<td style="text-align:left">60mm</td>
            		<td style="text-align:left">A</td>
            		<td style="text-align:left">只</td>
            		<td style="text-align:right">5</td>
            		<#if req.getParameter("proc")?exists>
            			<td style="text-align:right">10</td>
            		</#if>
            		<td style="text-align:left">.....</td>
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleTool.html',600,350);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>
    