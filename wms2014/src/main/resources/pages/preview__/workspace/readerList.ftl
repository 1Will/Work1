<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="通知人列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
			<table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>项目号</th>
			 	<th>通知人</th>
			 	<th>阅读意见</th>
			 	<th>阅读时间</th>
			<tr>
			<tr>	
				<td style="text-align:left">01</td>	
				<td style="text-align:left">system</td>	
				<td style="text-align:left">不用提交</td>		
				<td >2008-7-25</td>
				
				
			</tr>
			<tr>	
				<td style="text-align:left">02</td>	
				<td style="text-align:left">Tom</td>	
				<td style="text-align:left">尽快实施</td>		
				<td >2008-7-25</td>
				
				
			</tr>
	     	</@listTable>
	     	</table>       
     </@ww.form>
</@framePage>