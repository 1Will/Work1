<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装报废查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	 	<#include "toolDiscardSearcher.ftl"/>
	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/discard/editToolDiscard.html"/> 
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>报废编号</th>
			 	<th>报废名称</th>
			 	<th>工装编号</th>
			 	<th>工装图号</th>
			 	<th>工装名称</th>
			 	<th>工装类别</th>
			 	<th>报废原因</th>
			 	<th>使用单位</th>
			 	<th>报废时间</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editToolDiscard.html">07010102-01</a></td>
				<td style="text-align:left">工装报费单</td>
				<td style="text-align:left">0701011</td>
				<td style="text-align:left">0701011-009</td>
				<td style="text-align:left">复合膜</td>
				<td style="text-align:left">模具</td>
				<td style="text-align:left">........</td>
				<td style="text-align:left">焊冲一厂</td>
				<td >2007-09-01</td>
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editToolDiscard.html">07010102-02</a></td>
				<td style="text-align:left">工装报费单</td>
				<td style="text-align:left">0701022</td>
				<td style="text-align:left">0701022-008</td>
				<td style="text-align:left">拉延膜</td>
				<td style="text-align:left">模具</td>
				<td style="text-align:left">........</td>
				<td style="text-align:left">焊冲一厂</td>
				<td >2007-09-01</td>	
			</tr>
	     	</@listTable>
	     	</table>
	    <@buttonBar>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@htmlPage>