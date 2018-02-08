<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="检查计划明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>项目名称</th>
			 	<th>检查部位</th>
			 	<th>检查标准</th>
			 	<th>检查开始时间</th>
			 	<th>状态</th>
			 	<th>备注</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/check/editCheckPlanItem.html',600,300);return false;">08072001</a></td>
				<td style="text-align:left">吊车检查</td>
				<td style="text-align:left">底座</td>
				<td style="text-align:left">...</td>
				<td>2008-9-1</td>
				<td style="text-align:left">已审批，待执行</td>
				<td>.....</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/check/editCheckPlanItem.html',600,300);return false;">08072054</a></td>
				<td style="text-align:left">喷漆机检查</td>
				<td style="text-align:left">喷口</td>
				<td style="text-align:left">...</td>
				<td>2008-8-2</td>
				<td style="text-align:left">已执行</td>	
				<td>.....</td>	
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/check/editCheckPlanItem.html',600,300);"/>
     	<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
     </@buttonBar>
     </@ww.form>
</@framePage>