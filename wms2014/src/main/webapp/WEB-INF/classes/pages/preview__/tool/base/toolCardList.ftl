<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装卡片查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	 	<#include "toolCardSearcher.ftl"/>
	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/base/editTool.html"/> 
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>工装编号</th>
			 	<th>工装图号</th>
			 	<th>工装名称</th>
			 	<th>产品名称</th>
			 	<th>工装类别</th>
			 	<th>模具类别</th>
			 	<th>使用单位</th>
			 	<th>下发时间</th>
			 	<th>状态</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editTool.html">07010102-02</a></td>
				<td style="text-align:left">07010103</td>
				<td style="text-align:left">复合膜</td>
				<td style="text-align:left">后横梁下板</td>
				<td style="text-align:left">模具</td>
				<td style="text-align:left">冲头</td>
				<td style="text-align:left">焊冲一厂</td>
				<td >2007-09-01</td>
				<td style="text-align:left">正常</td>
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editTool.html">07010102-11</a></td>
				<td style="text-align:left">07010102</td>
				<td style="text-align:left">拉延膜</td>
				<td style="text-align:left">后横梁下板</td>
				<td style="text-align:left">模具</td>
				<td style="text-align:left">冲头</td>
				<td style="text-align:left">焊冲一厂</td>
				<td >2007-09-01</td>	
				<td style="text-align:left">使用中</td>
			</tr>
	     	</@listTable>
	     	</table>
	    <@buttonBar>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
     	
</@htmlPage>