<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备点检实施查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	 	<#include "checkPointProcSearcher.ftl"/>
	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/checkpoint/editCheckPointProc.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>点检实施编号</th>
			 	<th>点检实施名称</th>
			 	<th>部门</th>
			 	<th>点检日期</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCheckPointProc.html">1</a></td>
				<td style="text-align:left">设备点检2008.1.1</td>
				<td style="text-align:left">焊冲一厂</td>
				<td>2008-01-20</td>
			</tr>			
	     	</@listTable>
	     	</table>
	    <@buttonBar>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>	   
     </@ww.form>
</@htmlPage>