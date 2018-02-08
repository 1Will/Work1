<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="检查标准明细">
	 <@ww.form name="'listForm'" action="''" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>设备名称</th>
			 	<th>设备型号</th>
			 	<th>设备规格</th>
			 	<th>设备类别</th>
			 	<th>设备数量(件)</th>
			 	<th>检查部位</th>
			 	<th>检查周期</th>
			 	<th>上次检查时间</th>
			 	<th>状态</th>
			 	<th>备注</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/check/editCheckRulesItem.html',600,300);return false;">08072001</a></td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:right">10</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">每星期</td>
				<td>2008-7-6</td>
				<td style="text-align:left">已审核</td>
				<td style="text-align:left">...</td>		
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/check/editCheckRulesItem.html',600,300);return false;">08072002</a></td>
				<td style="text-align:left">螺杆压缩机</td>
				<td style="text-align:left">01</td>
				<td style="text-align:left">50*80</td>
				<td style="text-align:left">单螺杆压缩机</td>
				<td style="text-align:right">120</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">每星期</td>
				<td>2008-7-6</td>
				<td style="text-align:left">审核中</td>
				<td style="text-align:left">...</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/check/editCheckRulesItem.html',600,300);return false;">08072002</a></td>
				<td style="text-align:left">打码机</td>
				<td style="text-align:left">TM-07</td>
				<td style="text-align:left">40*50</td>
				<td style="text-align:left">办公用品</td>
				<td style="text-align:right">60</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">每星期</td>
				<td>2008-7-6</td>
				<td style="text-align:left">已审核</td>
				<td style="text-align:left">要求修理</td>	
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/check/editCheckRulesItem.html',600,300);"/>
     	<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
     </@buttonBar>
     </@ww.form>
</@framePage>