<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="设备点检标准">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
            	<th>点检部位</th>
			 	<th>点检内容</th>
			 	<th>点检标准</th>
			 	<th>点检方法</th>
			 	<th>点检要求</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#">1</a></td>
				<td style="text-align:left">急停按钮</td>
				<td>是否有效</td>
				<td>是否有效</td>
				<td>按下并检查是否有报警</td>
				<td>..</td>
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#">2</a></td>
				<td style="text-align:left">空气滤清器</td>
				<td>定期更换</td>
				<td>定期更换</td>
				<td>目视,实测</td>
				<td>..</td>
			</tr>	
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#">3</a></td>
				<td style="text-align:left">气压显示器</td>
				<td>指示是否在正常围           工作时：5.0-6.0</td>
				<td>目视</td>
				<td>目视</td>
				<td>..</td>
			</tr>		
	     	</@listTable>
	     	</table>
	    <@buttonBar>
	    	<@redirectButton value="新建" url="#"/>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@framePage>