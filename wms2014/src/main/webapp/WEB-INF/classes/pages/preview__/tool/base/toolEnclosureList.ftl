 <#include "../../../includes/eam2008.ftl" />
 <#include "/javascripts/selectAll.js" />

	<@framePage title="${action.getText('deviceExtInfo')}">   
	<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">        
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>项目号</th>
			 	<th>附件名称</th>
			 	<th>附件型号</th>
			 	<th>所需个数</th>
			 	<th>单价(元)</th>
			 	<th>供应商名称</th>
			 	<th>备注</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editToolEnclosure.html',600,300); return false;">001</a></td>
				<td style="text-align:left">异型冲头</td>
				<td style="text-align:left">16-80-10-7-A3</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">10</td>
				<td style="text-align:left">苏州环球</td>
				<td >.........</td>
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editToolEnclosure.html',600,300);return false;">002</a></td>
				<td style="text-align:left">圆形冲头</td>
				<td style="text-align:left">10-100-6.6</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">10</td>
				<td style="text-align:left">苏州环球</td>
				<td >.........</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editToolEnclosure.html',600,300);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  
	    </@ww.form>
</@framePage>