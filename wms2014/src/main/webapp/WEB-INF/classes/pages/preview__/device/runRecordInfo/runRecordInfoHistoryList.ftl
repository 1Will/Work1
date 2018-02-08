<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@framePage title="停机记录管理">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	 
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th>停机记录编号</th>
	  				<th>停机记录名称</th>
	  				<th>${action.getText('device.code')}</th>
	  				<th>${action.getText('device.name')}</th>
	  				<th>部门</th>
	  				<th>${action.getText('stop.time0')}</th>
	  				<th>${action.getText('stop.time1')}</th>
	  				<th>停机原因</th>
	  				<th>停机原因类别</th>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">HAN ZHONG JI停机</td>
	  				<td style="text-align:left">0021</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>2005-5-1 12:30</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别c</td>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0002</td>
	  				<td style="text-align:left">HAN ZHONG JI停机</td>
	  				<td style="text-align:left">0021</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>2005-5-1 12:30</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别c</td>
	  			</tr>	  			
	  		</@listTable>
        </table>
      </@ww.form>
      
</@framePage>