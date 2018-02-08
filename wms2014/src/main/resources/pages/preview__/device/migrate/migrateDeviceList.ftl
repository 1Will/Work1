<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="设备转移明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<th>原地点</th>
			 	<th>新地点</th>
				<th>原负责人</th>
				<th>新负责人</th>
			<tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<#--<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;">JAC-BY001</a></td>-->
				<td style="text-align:center"><a href="#" onclick=popup()>1</a></td>
				<td style="text-align:right">jac-jd-025</td>
				<td style="text-align:right">机床</td>
				<td style="text-align:right">机动部仓库</td>
				<td>
					<input type="text" name="" class="underline"  value="" />
				</td>
				<td style="text-align:right">sa</td>
				<td>
					<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
				</td>
			</tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<#--<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;">JAC-BY001</a></td>-->
				<td style="text-align:center"><a href="#" onclick=popup()>2</a></td>
				<td style="text-align:right">jac-jd-026</td>
				<td style="text-align:right">气压机</td>
				<td style="text-align:right">品管部车间</td>
				<td>
					<input type="text" name="" class="underline"  value="" />
				</td>
				<td style="text-align:right">张海</td>
				<td>
					<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
				</td>
			</tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<#--<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;">JAC-BY001</a></td>-->
				<td style="text-align:center"><a href="#" onclick=popup()>3</a></td>
				<td style="text-align:right">jac-jd-027</td>
				<td style="text-align:right">托盘</td>
				<td style="text-align:right">质检仓</td>
				<td>
					<input type="text" name="" class="underline"  value="" />
				</td>
				<td style="text-align:right">sa</td>
				<td>
					<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
				</td>
			</tr>
	     	</@listTable>
	     	</table>	    
	     <@buttonBar>
	    		<@vbutton class="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlaneditDeviceList.html',null,300);return false;"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    </@buttonBar>  	
	    <script>

	    		function peopleMainManager_OpenDialog() {
					popupModalDialog('${req.contextPath}/popup/maintenanceManagerSelector.html',null, 300);
				}
	    	</script>
     </@ww.form>
</@framePage>