<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 		<#include "deviceSearcher.ftl"/>
	 		
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择工装</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装规格</th>
			 	<th>工装类型</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">JAC-9080</td>
				<td style="text-align:left">普通机床</td>
				<td style="text-align:left">20mm*500mm</td>
				<td style="text-align:left">机床</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">JAC-9555</td>
				<td style="text-align:left">精装机床</td>
				<td style="text-align:left">20mm*500mm</td>
				<td style="text-align:left">机床</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">JAC-9666</td>
				<td style="text-align:left">普通螺杆压缩机</td>
				<td style="text-align:left">20mm*500mm</td>
				<td style="text-align:left">压缩机</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>