 <#include "../../includes/macros2.ftl" />
 <#include "/javascripts/selectAll.js" />
<@framePage title="供应商级别变更历史">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th>项目号</th>
			<th>变更时间</th>
			<th>变更原因</th>
			<th>原级别</th>
			<th>新级别</th>
			<th>提交人</th>
			<th>审核人</th>
			</tr>
			<tr>
			<td style="text-align:left">1</td>
		    <td>07/03/05</td>
		    <td style="text-align:left">....</td>
		    <td style="text-align:left">一般</td>
		    <td style="text-align:left">良好</td>
		    <td style="text-align:left">ls</td>
		    <td style="text-align:left">system</td>
		    </tr>	
		    <tr>
            <td style="text-align:left">2</td>
		    <td>07/06/05</td>
		    <td style="text-align:left">....</td>
		    <td style="text-align:left">良好</td>
		    <td style="text-align:left">优秀</td>
		    <td style="text-align:left">ls</td>
		    <td style="text-align:left">system</td>
		    </tr>
	     	</@listTable>
	     	</table>
  </@ww.form> 
</@framePage>