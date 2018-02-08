<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="工装变更管理">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>工装变更编号</th>
			 	<th>工装变更名称</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>修改原因</th>
			 	<th>修改方案</th>
			 	<th>委托单位</th>
			 	<th>委托人</th>
			 	<th>承接人</th>
			 	<th>计划完成时间</th>
			<tr>
			<tr>	
				<td style="text-align:left">2008072911</td>
				<td style="text-align:left">参数修正</td>
				<td style="text-align:left">2008072911</td>
				<td style="text-align:left">测量工具</td>
				<td style="text-align:right">2008072911</td>
				<td style="text-align:left">........</td>
				<td style="text-align:left">.........</td>
				<td style="text-align:left">二厂</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">sa1</td>
				<td >2008-7-23</td>
				
			</tr>			
	     	</@listTable>
	     	</table>
     
   
     </@ww.form>
</@framePage>