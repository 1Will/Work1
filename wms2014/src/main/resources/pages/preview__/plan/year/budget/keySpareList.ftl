<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="大项修详细列表">
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
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">压力机变频主电机</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">关键备件采购周期长，进口件多，同类型压力机通用备件只记入一次(下同)</td>        		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">2</a></td>
            		<td style="text-align:left">离合器制动器ROSS进口双阀</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">3</a></td>
            		<td style="text-align:left">电子凸轮可编程控制器</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">4</a></td>
            		<td style="text-align:left">滑块调整旋转编码器</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">5</a></td>
            		<td style="text-align:left">气垫调整旋转编码器</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">6</a></td>
            		<td style="text-align:left">CPU单元C200HG--CPU63--E</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">7</a></td>
            		<td style="text-align:left">电源模块C200HW--PA204</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">8</a></td>
            		<td style="text-align:left">模拟量模块C200H--AD003</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">9</a></td>
            		<td style="text-align:left">模拟量模块C200H--DA003</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">10</a></td>
            		<td style="text-align:left">输入单元C200H--IA122</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">11</a></td>
            		<td style="text-align:left">输入单元C200H--ID212</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">12</a></td>
            		<td style="text-align:left">晶体管输出单元C200H--OD217</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">13</a></td>
            		<td style="text-align:left">滑块调整装置蜗轮蜗杆机构</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">14</a></td>
            		<td style="text-align:left">工作台夹紧放松进口气动泵</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);return false;">15</a></td>
            		<td style="text-align:left">工作台电缆</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">......</td>       		
            	</tr>
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editRepair.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>