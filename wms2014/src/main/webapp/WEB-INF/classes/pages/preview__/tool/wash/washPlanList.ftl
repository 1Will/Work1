<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="清洗计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	 <#include "washSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editCalibrationPlan.html"/>
        </@buttonBar>
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>清洗计划编号</th>
	  				<th>清洗计划名称</th>
	  				<th>计划执行日期</th>
	  				<th>部门</th>
	  				<th>编制人</th>
	  				<th>编制日期</th>	  				
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editWashPlan.html">0001</a></td>
	  				<td style="text-align:left">焊装一2005年5月第一周模具清洗计划</td>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">生产部</th>
	  				<td style="text-align:left">system</td>
	  				<td>2005-5-1 12:00</td>
	  			</tr>	  			 		
	  		</@listTable>
        </table>
         <@buttonBar>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@htmlPage>