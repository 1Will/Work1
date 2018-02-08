<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="标定计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>工装图号</th>
	  				<th>标定周期</th>
	  				<th>计划标定日期</th>	  				
	  				<th>实际标定日期</th>
	  				<th>责任人</th>
	  				<th>标定结果</th>
	  				<th>备注</th>
	  				<th>使用单位</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="#">0001</a></td>
	  				<td style="text-align:left">夹具1</td>
	  				<td style="text-align:left">001</th>
	  				<td style="text-align:left">1年</td>
	  				<td>2005-5-1 12:00</td>
	  				<td><input type="text" class="underline" value="2005-5-1 12:00"/></td>
	  				<td>陈荣超</td>
	  				<td><input type="text" class="underline" value="合格"/></td>
	  				<td><input type="text" class="underline" value=""/></td>
	  				<td>焊装一厂</td>
	  			</tr>
	  		</@listTable>
        </table>
      </@ww.form>
</@framePage>