 <#include "../../../includes/eam2008.ftl" />

	<@framePage title="${action.getText('device.title')}">   
	<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">        
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th>项目号</th>
			 	<th>使用单位</th>
			 	<th>变更日期</th>
			 	<th>修改原因</th>
			 	<th>修改方案</th>
			 	<th>承接单位</th>
			 	<th>承接人</th>
			 	<th>委托人</th>
			<tr>
			<tr>
				<td>001</td>
				<td style="text-align:left">冲焊一厂</td>
				<td>2007-02-03</td>
				<td >.........</td>
				<td style="text-align:left">拉延膜修改方案</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:left">段裘炳</td>
				<td style="text-align:left">段裘炳</td>
	
			</tr>
			<tr>
				<td>002</td>
				<td style="text-align:left">冲焊一厂</td>
				<td>2008-02-03</td>
				<td >.........</td>
				<td style="text-align:left">拉延膜修改方案</td>
				<td style="text-align:left">冲焊一厂</td>
				<td style="text-align:left">段裘炳</td>
				<td style="text-align:left">段裘炳</td>
			</tr>
	     	</@listTable>
	     	</table>
	     </@ww.form>
</@framePage>