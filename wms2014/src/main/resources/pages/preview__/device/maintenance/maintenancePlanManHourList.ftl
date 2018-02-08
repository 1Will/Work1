<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养工时明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>工种类别</th>
				<th>工时单价</th>
				<th>工时数量</th>
				<th>工时费用</th>
				<th>备注</th>
			<tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editMaintenancePlanManHour.html',600,300);return false;">021</a></td>
				<td style="text-align:left">钳工</td>
				<td style="text-align:right">10</td>
				<td style="text-align:right">5</td>
				<td style="text-align:right">500</td>
				<td style="text-align:left">...</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editMaintenancePlanManHour.html',600,300);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    </@buttonBar>  	
     </@ww.form>
</@framePage>