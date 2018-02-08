<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备转移查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	    <#include "deviceMigrateSearcher.ftl" />
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editMigrate.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>转移编号</th>
			    <th>转移名称</th>		
			 	<th>转移申请人</th>
			 	<th>转移日期</th>
			 	<th>原地点</th>
			 	<th>新地点</th>
			 	<th>转移原因</th>
			 </tr>
			 <tr>
			 	<td><input type="checkbox" name="checkbox" value="true"></td>
			 	<td style="text-align:left"><a href="editMigrate.html">0001</a></td>
			 	<td style="text-align:left">机器人转移</td>
			 	<td style="text-align:left">sa</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">旱冲一厂</td>
			 	<td style="text-align:left">机动部</td>
			 	<td style="text-align:left">机动部该设备在维修</td>
			 </tr>
			 <tr>
			 	<td><input type="checkbox" name="checkbox" value="true"></td>
			 	<td style="text-align:left"><a href="editMigrate.html">0002</a></td>
			 	<td style="text-align:left">机床转移</td>
			 	<td style="text-align:left">sa</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">旱冲一厂</td>
			 	<td style="text-align:left">机动部</td>
			 	<td style="text-align:left">机动部该设备在维修</td>
			 </tr>
	     	</@listTable>
	     	</table>   
	     	<@buttonBar>
	     		<input name="delete" type="button" value="失效" class="button"/>
	     		<input name="print" type="button" value="打印" class="button"/>
	     	</@buttonBar>  	
     </@ww.form>
</@htmlPage>