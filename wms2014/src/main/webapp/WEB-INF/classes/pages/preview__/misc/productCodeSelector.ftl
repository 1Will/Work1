<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="种类选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th><input type="checkbox" name="checkbox" value="true"></th>
            	<th>序号</th>
			 	<th>种类代码</th>
			 	<th>种类名称</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">1</td>
				<td style="text-align:left">E3</td>
				<td style="text-align:left">冲头（异型)</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">2</td>
				<td style="text-align:left">E4</td>
				<td style="text-align:left">冲头及冲套（标准)</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>