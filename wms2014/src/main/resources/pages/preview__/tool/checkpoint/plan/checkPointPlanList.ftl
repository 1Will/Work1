<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备点检计划查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	 	<#include "checkPointPlanSearcher.ftl"/>
	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/checkpoint/editCheckPointPlan.html"/> 
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>项目号</th>
                <th>点检计划编号</th>
                <th>点检计划名称</th>
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<th>部门</th>
			 	<th>计划执行时间</th>
			<tr>
			<tr>
					<td><input type="checkbox"  name="checkbox" value="true"></td>
					<td style="text-align:left"><a href="editCheckPointPlan.html">1</a></td>
					<td style="text-align:left">JAC-DJ-01</td>
					<td style="text-align:left">冲压机器人08年1月点检计划</td>
					<td style="text-align:left">421--175</td>
					<td style="text-align:left">冲压机器人</td>
					<td style="text-align:left">冲压一厂</td>			
					<td>2008-02-01</td>
			</tr>
			<tr>			
					<td><input type="checkbox"  name="checkbox" value="true"></td>
					<td style="text-align:left"><a href="editCheckPointPlan.html">2</a></td>
					<td style="text-align:left">JAC-DJ-02</td>
					<td style="text-align:left">双梁桥式起重机08年2月点检计划</td>
					<td style="text-align:left">211--012</td>
					<td style="text-align:left">双梁桥式起重机</td>
					<td style="text-align:left">冲压一厂</td>			
					<td>2008-02-01</td>
			</tr>
	     	</@listTable>
	     	</table>
	    <@buttonBar>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@htmlPage>