<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="采购单明细">
<script language="JavaScript" type="text/JavaScript"> 
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html');
		window.close();
	}

</script>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
			 	<th>预算编号</th>
			 	<th>项目名称</th>
			 	<th>预算制定人</th>
			 	<th>预算制定日期</th>
			 	<th>预算部门</th>
			 	<th>预算费用(元)</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350);return false;">08072001</a></td>
				<td style="text-align:left">新增设备采购预算</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">200000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350);return false;">08072002</a></td>
				<td style="text-align:left">大项修预算</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">80000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350);return false;" >08072003</a></td>
				<td style="text-align:left">设备预算</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:right">100000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350);return false;">08072004</a></td>
				<td style="text-align:left">新增设备采购预算</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">冲焊二厂</td>
				<td style="text-align:right">200000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350);return false;">08072005</a></td>
				<td style="text-align:left">大项修预算</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">冲焊二厂</td>
				<td style="text-align:right">80000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350);return false;" >08072006</a></td>
				<td style="text-align:left">设备预算</td>
				<td style="text-align:left">System</td>
				<td>2008-8-23</td>
				<td style="text-align:left">冲焊二厂</td>
				<td style="text-align:right">100000</td>	
			</tr>
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@vbutton  value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/plan/budget/listAttacheBudget.html',600,350)"/>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@framePage>