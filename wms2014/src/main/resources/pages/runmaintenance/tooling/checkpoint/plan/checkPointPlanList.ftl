<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="点检计划查询">
	 <@ww.form name="'listForm'" action="''" method="'post'">
	   <#include "checkPointPlanSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="listcheckPointPlanDetail.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  value="true"></th>
			 	<th>点检计划编号</th>
			 	<th>点检计划名称</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>计划日期</th>
			 	<th>部门</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="listcheckPointPlanDetail.html">07010102-01</a></td>
				<td style="text-align:left">包边膜点检</td>
				<td style="text-align:left">0701011</td>
				<td style="text-align:left">0701011-009</td>
				<td style="text-align:left">2007-05</td>
				<td style="text-align:left">机动部</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="listcheckPointPlanDetail.html">07010555-01</a></td>
				<td style="text-align:left">SRV车门夹具点检</td>
				<td style="text-align:left">0701045</td>
				<td style="text-align:left">0701054-009</td>
				<td style="text-align:left">2007-05</td>
				<td style="text-align:left">机动部</td>
			</tr>
	     	</@listTable>
	     	</table> 
     <@buttonBar>
     	<@vbutton value="删除" class="button" />
     </@buttonBar>
     </@ww.form>
</@htmlPage>