<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="审批历史">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
			<table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>项目号</th>
			 	<th>审批时间</th>
			 	<th>审批人</th>
			 	<th>审批意见</th>
			<tr>
			<tr>	
				<td style="text-align:left">01</td>				
				<td >2008-7-25</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">可以，基本同意</td>
			</tr>
			<tr>	
				<td style="text-align:left">02</td>				
				<td >2008-7-25</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">同意</td>
			</tr>
			<tr>	
				<td style="text-align:left">03</td>				
				<td >2008-7-25</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">可以，基本同意</td>
			</tr>
			<tr>	
				<td style="text-align:left">04</td>				
				<td >2008-7-25</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">未审批</td>
			</tr>
	     	</@listTable>
	     	</table>       
     </@ww.form>
</@framePage>