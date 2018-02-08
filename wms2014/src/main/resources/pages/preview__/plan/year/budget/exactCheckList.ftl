<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="精度检查详细列表">
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
            		<td><a href="#" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editExactCheck.html',750,400);return false;">1</a></td>
            		<td style="text-align:left">检查压力机垂直度、平行度，并调整到出厂精度值</td>
            		<td style="text-align:right">12</td>
            		<td style="text-align:left">一年检查2次，平均需调整1次</td>        		
            	</tr>            
            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editExactCheck.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
     </@ww.form>
</@framePage>