<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="检查实施明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>检查计划编号</th>
				<th>设备编号</th>
				<th>设备名称</th>
				<th>检查制定日期</th>
				<th>检查部门</th>
				<th>检查结论</th>
				<th>备注</th>			
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>				
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/check/editCheckPlanImplsItem.html',600,400);return false;">021</a></td>
				<td style="text-align:left">200703302</td>
				<td style="text-align:left">JAC-45</td>
				<td style="text-align:left">吊车</td>
				<td>2007-8-9</td>
				<td style="text-align:left">品检一部</td>
				<td style="text-align:left">未通过</td>
				<td style="text-align:left">尽快解决</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/check/editCheckPlanImplsItem.html',600,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    </@buttonBar>  	
     </@ww.form>
</@framePage>