<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="设备点检标准查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">
   
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>点检标准编号</th>
			 	<th>点检标准名称</th>
			 	<th>部门</th>
			 	<th>点检负责人</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCheckPointRule.html">07010102-01</a></td>
				<td style="text-align:left">机动部点检标准</td>
				<td>机动部</td>
				<td>system</td>
			</tr>		
	     	</@listTable>
	     	</table>
	    <@buttonBar>
	    	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/checkpoint/editCheckPointRule.html"/> 
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@framePage>