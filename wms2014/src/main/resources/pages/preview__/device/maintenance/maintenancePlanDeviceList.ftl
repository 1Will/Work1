<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养计划明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<#--<th>部门</th>
			 	<th>保养部位</th>
				<th>保养方法</th>
				<th>保养要求</th>-->
				<th>计划执行人</th>
				<th>计划完成日期</th>
				<th>执行结果</th>
			<tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<#--<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;">JAC-BY001</a></td>-->
				<td style="text-align:center"><a href="#" onclick=popup()>1</a></td>
				<td style="text-align:right">jac-jd-025</td>
				<td style="text-align:right">机床</td>
				<#--<td style="text-align:right">机动部</td>
				<td style="text-align:right">链条</td>
				<td style="text-align:right">链条上油</td>
				<td style="text-align:right">带动正常</td>-->
				<td style="text-align:right">张钱之</td>
				<td>08-01-05</td>
				<td style="text-align:right">未完成</td>
			</tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<#--<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;">JAC-BY001</a></td>-->
				<td style="text-align:center"><a href="#" onclick=popup()>2</a></td>
				<td style="text-align:right">jac-jd-026</td>
				<td style="text-align:right">气压吊床</td>
				<#--<td style="text-align:right">机动部</td>
				<td style="text-align:right">链条</td>
				<td style="text-align:right">链条上油</td>
				<td style="text-align:right">带动正常</td>-->
				<td style="text-align:right">何敏</td>
				<td>08-08-08</td>
				<td style="text-align:right">未完成</td>
			</tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<#--<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;">JAC-BY001</a></td>-->
				<td style="text-align:center"><a href="#" onclick=popup()>3</a></td>
				<td style="text-align:right">jac-jd-027</td>
				<td style="text-align:right">打磨机</td>
				<#--<td style="text-align:right">机动部</td>
				<td style="text-align:right">链条</td>
				<td style="text-align:right">链条上油</td>
				<td style="text-align:right">带动正常</td>-->
				<td style="text-align:right">吴伟</td>
				<td>08-01-22</td>
				<td style="text-align:right">完成</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     <@buttonBar>
	    		<@vbutton class="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    </@buttonBar>  	
	    <script>
	    		function popup() {
	    			popupModalDialog('${req.contextPath}/preview/device/maintenance/editMaintenanceDetail.html',1024,768);
	    		}
	    	</script>
     </@ww.form>
</@framePage>