<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@framePage title="反馈记录管理">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th>${action.getText('device.code')}</th>
	  				<th>${action.getText('device.name')}</th>
	  				<th>反馈时间</th>
	  				<th>反馈人</th>
	  				<th>反馈内容</th>
	  				<th>反馈类别</th>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>sa</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别a</td>
	  			</tr>
	  			<tr>
	  				<td style="text-align:left">0002</td>
	  				<td style="text-align:left">HAN DIAN JI</td>
	  				<td>2005-6-1 12:00</td>
	  				<td>system</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别a</td>	  			
	  			</tr>	  			
	  		</@listTable>
        </table>
      </@ww.form>
</@framePage>