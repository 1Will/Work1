<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@framePage title="事故记录管理">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th>事故记录编号</th>
	  				<th>事故记录名称</th>
	  				<th>上次事故日期</th>
	  				<th>使用部门</th>
	  				<th>事故详细</th>
	  				<th>解决方案</th>
	  				<th>负责人</th>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">JQ-ZZ-002事故</th>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td style="text-align:left">滑轮轴的齿轮错位</td>
	  				<td style="text-align:left">停止该机床，申请维修。</td>
	  				<td>吴伟</td>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0002</td>
	  				<td style="text-align:left">JQ-ZZ-003事故</th>
	  				<td>2005-6-1 12:00</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td style="text-align:left">链条断裂</td>
	  				<td style="text-align:left">申请报废</td>
	  				<td>卢振新</td>
	  			</tr>	  		
	  		</@listTable>
        </table>      
      </@ww.form>
</@framePage>