<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="计划日期调整明细">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>序号</th>
			 	<th>工单编号</th>
				<th>保养级别</th>
				<th>保养内容</th>
				<th>保养负责人</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>变更人</th>
				<th>变更原因</th>
				<th>变更日期</th>
				<th>变更后计划开工日期</th>
				<th>变更后计划完工日期</th>
				<th>审批人</th>
			</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td>1</td>
				<td>GD0001</td>
				<td>一保</td>
				<td>...</td>
				<td>gs</td>
				<td>07/05/03</td>
				<td>07/09/05</td>
				<td>gs</td>
				<td>....</td>
				<td>07/04/03</td>
				<td>07/05/03</td>
				<td>07/10/09</td>
				<td>ls</td>
			</tr>			
	     	</@listTable>
	     	</table>	
	   </@ww.form>   	
</@framePage>		 