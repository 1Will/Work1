<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="验收单附加工具列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>工具名称</th>
				<th>工具型号</th>
				<th>工具规格</th>
				<th>工具数量</th>
				<th>所属设备</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editReceiveBillTool.html',null,300); return false;">JH-021</a></td>
				<td style="text-align:left">轴承TX</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">轴承</td>	
				<td style="text-align:left">10</td>	
				<td style="text-align:left">轴承</td>				
			</tr>		
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/editReceiveBillTool.html',null,300)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>