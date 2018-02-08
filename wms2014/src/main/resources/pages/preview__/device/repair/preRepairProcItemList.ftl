<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="预防性维修是实施列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
	        		<th>设备编号</th>
	        		<th>设备名称</th>
	        		<th>维修部位</th>
	        		<th>维修内容摘要</th>
	        		<th>责任单位</th>
	        		<th>责任人</th>
	        		<th>执行人</th>
	        		<th>计划执行日期</th>
	        		<th>备注</th>
	        		<th>执行结果</th>
	        		<th>操作</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td  style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairPlanItem.html',750,400);return false;">1</a></td>
            		<td  style="text-align:left">123--459</td>
            		<td  style="text-align:left">行车</td>
            		<td  style="text-align:left">车轮</td>
            		<td  style="text-align:left">B2工段行车大车车轮精度调整</td>
            		<td  style="text-align:left">外协</td>
            		<td  style="text-align:left">张三</td>
            		<td/>
            		<td  ><input type="text" class="underline" value="2007-12-23"/></td>
            		<td/>
            		<td>未完成</td>
            		<td><input type="button" class="button" value="详细"  onclick="popup();"/></td>
            	</tr>
            	
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td  style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairPlanItem.html',750,400);return false;">2</a></td>
            		<td  style="text-align:left">123--459</td>
            		<td  style="text-align:left">行车</td>
            		<td  style="text-align:left">制动</td>
            		<td  style="text-align:left">8台行车各制动装置检查调整</td>
            		<td  style="text-align:left">维修班</td>
            		<td  style="text-align:left" >张三</td>
            		<td/>
            		<td  ><input type="text" class="underline" value="2007-12-23"/></td>
            		<td/>
            		<td>未完成</td>
            		<td><input type="button" class="button" value="详细"  onclick="popup();"/></td>
            	</tr>
            	
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right" ><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairPlanItem.html',750,400);return false;">3</a></td>
            		<td  style="text-align:left">123--460</td>
            		<td  style="text-align:left">压力机</td>
            		<td style="text-align:left">制动，螺栓，滑块，顶起，行程开关，支架，撞块，气垫，紧固件</td>
            		<td  style="text-align:left">C1工段5台压力机离合器制动器间隙检查调整；
            		<br/>平衡缸固定螺栓检查紧固；滑块调整机构检查；
            		<br/>工作台顶起驱动装置、各行程开关限位、支架、撞块检查；
            		<br/>气垫调整装置检查；压力机基座关键紧固件检查是否松动。
            		<br/>压力机漏油、漏气现象治理。</td>
            		<td  style="text-align:left">维修班</td>
            		<td  style="text-align:left">张三</td>
            		<td/>
            		<td  ><input type="text" class="underline" value="2007-12-23"/></td>
            		<td>
            		<td style="text-align:left">未完成</td>
            		<td><input type="button" class="button" value="详细" onclick="popup();"/></td>
            	</tr>
            	
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairPlanItem.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
	    	
	    	<script>
	    		function popup() {
	    			popupModalDialog('${req.contextPath}/preview/device/repair/editPreRepairProcDetailX.html?proc=1',1024,768);
	    		}
	    	</script>
     </@ww.form>
</@framePage>
    