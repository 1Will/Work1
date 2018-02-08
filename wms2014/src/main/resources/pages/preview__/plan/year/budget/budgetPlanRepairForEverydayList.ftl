<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
            		<th>明细内容</th>
	        		<th>明细金额(元)</th>
	        		<th>备注</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">检查调整离合器</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>        		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">2</a></td>
            		<td style="text-align:left">制动器摩擦片的间隙</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>    		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">3</a></td>
            		<td style="text-align:left">更换磨损的摩擦片 </td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>  		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">4</a></td>
            		<td style="text-align:left">检查离合器制动器弹簧完好情况，添加润滑脂 </td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>  		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">5</a></td>
            		<td style="text-align:left">更换气路中损坏的电气元件，消除漏气现象</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>  		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">6</a></td>
            		<td style="text-align:left">更换老化的电气元件，整理电气线路，电动机保养</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>  		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">7</a></td>
            		<td style="text-align:left">检查各气动泵、电磁阀、行程开关、传感器</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>  		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);return false;">8</a></td>
            		<td style="text-align:left">更换各老化密封圈 ；更换老化机械配件</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>  		
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepairForEveryday.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>