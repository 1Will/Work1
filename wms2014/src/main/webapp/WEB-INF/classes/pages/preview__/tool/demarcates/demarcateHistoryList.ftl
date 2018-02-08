<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="标定历史记录">
	 <@ww.form name="'listForm'" action="'#'" method="'post'">
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			 	<th>上次标定日期</th>
			 	<th>使用单位</th>
			 	<th>所在位置</th>
			 	<th>负责人</th>
			 	<th>标定周期</th>
			 	<th>标定结果</th>
			<tr>
			<tr>	
				<td >2007-02-07</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">12个月</td>
				<td style="text-align:left">合格</td>
			</tr>
			<tr>	
				<td >2006-01-11</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">周海燕</td>
				<td style="text-align:left">6个月</td>
				<td style="text-align:left">不合格</td>
			</tr>		
	     	</@listTable>
	     	</table>
     
   
     </@ww.form>
</@framePage>