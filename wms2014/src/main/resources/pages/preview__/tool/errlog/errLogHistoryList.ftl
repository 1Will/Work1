<#include "../../includes/macros2.ftl" />

<@framePage title="故障记录管理">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th>故障记录编号</th>
	  				<th>故障记录名称</th>
	  				<th>上次故障日期</th>
	  				<th>使用部门</th>
	  				<th>故障详细</th>
	  				<th>解决方案</th>
	  				<th>负责人</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td style="text-align:left">滑轮轴的齿轮错位</td>
	  				<td style="text-align:left">停止该机床，申请维修。</td>
	  				<td>陆兵</td>
	  				<td>已解决</td>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0002</td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td>2005-6-1 12:00</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td style="text-align:left">链条生修</td>
	  				<td style="text-align:left">上润滑油</td>
	  				<td>张杰</td>
	  				<td>待解决</td>
	  			</tr>	  		
	  		</@listTable>
        </table>      
      </@ww.form>
</@framePage>