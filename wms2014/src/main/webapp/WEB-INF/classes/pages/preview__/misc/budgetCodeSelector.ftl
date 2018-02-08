<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="科目选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th><input type="checkbox" name="checkbox" value="true"></th>
            	<th>序号</th>
			 	<th>预算代码</th>
			 	<th>预算名称</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">1</td>
				<td style="text-align:left">maintence</td>
				<td style="text-align:left">维修与保养</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">2</td>
				<td style="text-align:left">copy</td>
				<td style="text-align:left">模具复制</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>