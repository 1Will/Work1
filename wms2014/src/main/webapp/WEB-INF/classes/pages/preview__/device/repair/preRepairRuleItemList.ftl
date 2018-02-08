<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="预防性维修标准列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
            		<th>维修部位</th>
	        		<th>维修内容</th>
	        		<th>上次维修日期</th>
	        		<th>运行台时阀值(小时)</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleItem.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">大车轮</td>
            		<td style="text-align:left"> 调整精度</td>
            		<td>2007-12-11</td>
            		<td style="text-align:right">200</td>            	
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleItem.html',750,400);return false;">2</a></td>
            		<td style="text-align:left">制动支架</td>
            		<td style="text-align:left">制动支架加固修复</td>
            		<td>2007-12-11</td>
            		<td style="text-align:right">150</td>            	
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleItem.html',750,400);return false;">3</a></td>
            		<td style="text-align:left">模具传感器</td>
            		<td style="text-align:left">A线SRV及重卡拉延模传感器修复</td>
            		<td>2007-12-11</td>
            		<td style="text-align:right">100</td>            	
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleItem.html',750,400);return false;">4</a></td>
            		<td style="text-align:left">压力机</td>
            		<td style="text-align:left">A-5，压力机离合器漏气检查处理</td>
            		<td>2007-12-11</td>
            		<td style="text-align:right">100</td>            	
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td style="text-align:right"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleItem.html',750,400);return false;">5</a></td>
            		<td style="text-align:left">压力机</td>
            		<td style="text-align:left">B1-4压力机前工作台漏油检查</td>
            		<td>2007-12-11</td>
            		<td style="text-align:right"> 500</td>            	
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editPreRepairRuleItem.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>
    