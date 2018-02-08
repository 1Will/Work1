<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="保养人列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>名称</th>
			 	<th>职位</th>
			 	<th>部门</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">administrator</td>
				<td style="text-align:left">it</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">qs</td>
				<td style="text-align:left">administrator</td>
				<td style="text-align:left">it</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">System</td>
				<td style="text-align:left">administrator</td>
				<td style="text-align:left">it</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>