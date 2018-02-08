<#include "../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('demarcateDetail.title')}">
	 <@ww.form name="'demarcatePlanDetail'" action="'searchDemarcatePlanDetail'" method="'post'">
	   <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>制造单位</th>
			 	<th>使用单位</th>
			 	<th>责任人</th>
			 	<th>本次标定日期</th>
			 	<th>标定周期(月)</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">1</td>
				<td style="text-align:left">AMS10L-1</td>
				<td style="text-align:left">左前纵梁总成补焊</td>
				<td style="text-align:left">AMS10L</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">2007-9-20</td>
				<td style="text-align:left">12</td>
			</tr>
	     	</@listTable>
	     	</table>   
     </@ww.form>         
</@framePage>