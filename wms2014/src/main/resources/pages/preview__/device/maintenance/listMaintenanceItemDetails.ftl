<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养计划详细列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>保养部位</th>
			 	<th>保养方法</th>
				<th>保养要求</th>
				<th>计划执行人</th>
				<th>计划完成日期</th>
			</tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:center">1</td>
				<td style="text-align:right">机床轴</td>
				<td style="text-align:right">打磨，上蜡</td>
				<td style="text-align:right">同时进行安全检查</td>
				<td style="text-align:right">赵云</td>
				<td>08-08-08</td>
			</tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:center">2</td>
				<td style="text-align:right">气压吊床</td>
				<td style="text-align:right">更换链条</td>
				<td style="text-align:right">同时进行压力测试</td>
				<td style="text-align:right">何敏</td>
				<td>08-01-23</td>
			</tr>
					
	     	</@listTable>
	     	</table>	    
	     <@buttonBar>
	    		<@vbutton class="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    </@buttonBar>  	
     </@ww.form>
</@framePage>