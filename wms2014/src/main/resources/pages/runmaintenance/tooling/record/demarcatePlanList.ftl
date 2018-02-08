<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="工装标定计划查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#include "demarcatePlanSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="计划" url="editDemarcatePlan.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  value="true"></th>
			 	<th>标定计划编号</th>
			 	<th>标定计划名称</th>
			 	<th>部门</th>
			 	<th>标定时间</th>
			 	<th>标定计划状态</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDemarcatePlan.html">BD_AMS10L-1</a></td>
				<td style="text-align:left">凌江夹具标定计划</td>
				<td style="text-align:left">生产部</td>
				<td style="text-align:left">2007-9-20</td>
				<td style="text-align:left">未实施</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDemarcatePlan.html">BD_AMS10L-2</a></td>
				<td style="text-align:left">本部宾悦夹具标定计划</td>
				<td style="text-align:left">生产部</td>
				<td style="text-align:left">2007-9-20</td>
				<td style="text-align:left">未实施</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDemarcatePlan.html">BD_AMS10L-3</a></td>
				<td style="text-align:left">外协厂家SRV夹具标定计划</td>
				<td style="text-align:left">生产部</td>
				<td style="text-align:left">2007-9-20</td>
				<td style="text-align:left">未实施</td>
			</tr>
	     	</@listTable>
	     	</table> 
     <@buttonBar>
     	<@vbutton value="删除" class="button" />
     </@buttonBar>
     </@ww.form>
</@htmlPage>