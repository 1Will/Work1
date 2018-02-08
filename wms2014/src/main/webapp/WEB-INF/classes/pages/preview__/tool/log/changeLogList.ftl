<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装变更查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#include "changeLogSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editChangeLog.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>工装变更编号</th>
			 	<th>工装变更名称</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>承接单位</th>
			 	<th>委托人</th>
			 	<th>承接人</th>
			 	<th>计划完成时间</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editChangeLog.html">2008072911</a></td>
				<td style="text-align:left">028</a></td>
				<td style="text-align:left">测量工具参数修改</td>
				<td style="text-align:left">测量工具</td>
				<td style="text-align:left">JAC-2008072911</td>
				<td style="text-align:right">2008072911</td>
				<td style="text-align:left">二厂</td>
				<td style="text-align:left">sa</td>
				<td style="text-align:left">sa1</td>
				<td >2008-7-23</td>
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>