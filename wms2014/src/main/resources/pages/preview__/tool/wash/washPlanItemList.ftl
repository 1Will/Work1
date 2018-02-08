<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="清洗计划明细">
	 <@ww.form name="'listForm'" action="" method="'post'">
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>工装图号</th>
	  				<th>工序</th>
	  				<th>计划清洗日期</th>	  				
	  				<th>责任人</th>
	  				<th>监督人</th>
	  				<th>车型</th>
	  				<th>备注</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="#">0001</a></td>
	  				<td style="text-align:left">夹具1</td>
	  				<td style="text-align:left">001</th>
	  				<td style="text-align:left">1/3</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>system</td>
	  				<td>sa</td>
	  				<td>mrp</td>
	  				<td>...</td>
	  			</tr>	  			 		
	  		</@listTable>
        </table>
         <@buttonBar>
         		<@redirectButton value="新建" url="#"/>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@framePage>