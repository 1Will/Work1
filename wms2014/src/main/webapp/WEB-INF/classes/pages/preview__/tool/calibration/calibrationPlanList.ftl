<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="标定计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	 <#include "calibrationSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editCalibrationPlan.html"/>
        </@buttonBar>
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>标定计划编号</th>
	  				<th>标定计划名称</th>
	  				<th>部门</th>
	  				<th>编制人</th>
	  				<th>编制日期</th>
	  				<th>审核人</th>
	  				<th>批准人</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCalibrationPlan.html">0001</a></td>
	  				<td style="text-align:left">焊装一夹具标定</td>
	  				<td style="text-align:left">品管部</th>
	  				<td style="text-align:left">system</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>sa</td>
	  				<td>sa</td>
	  			</tr>	  			 		
	  		</@listTable>
        </table>
         <@buttonBar>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@htmlPage>