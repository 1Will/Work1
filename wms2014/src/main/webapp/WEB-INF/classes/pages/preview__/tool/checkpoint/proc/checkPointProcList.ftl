<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备点检实施查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	 	<#include "checkPointProcSearcher.ftl"/>
	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>点检计划编号</th>
			 	<th>点检计划名称</th>
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<th>部门</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCheckPointProc.html">1</a></td>
				<td style="text-align:left">压力机设备点检2008.1</td>
				<td style="text-align:left">0701011</td>
				<td style="text-align:left">压力机设备</td>
				<td style="text-align:left">焊冲一厂</td>
			</tr>			
	     	</@listTable>
	     	</table>	   
     </@ww.form>
</@htmlPage>