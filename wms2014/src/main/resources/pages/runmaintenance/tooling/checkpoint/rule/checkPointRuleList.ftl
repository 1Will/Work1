<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="点检标准查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#include "checkPointRuleSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editCheckPointRule.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  value="true"></th>
			 	<th>点检标准编号</th>
			 	<th>点检标准名称</th>
			 	<th>分类</th>
			 	<th>部门</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCheckPointRule.html">07010102-01</a></td>
				<td style="text-align:left">设备点检标准</td>
				<td style="text-align:left">设备</td>
				<td style="text-align:left">机动部</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCheckPointRule.html">07010102-02</a></td>
				<td style="text-align:left">工装点检标准</td>
				<td style="text-align:left">工装</td>
				<td style="text-align:left">生产部</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCheckPointRule.html">07010102-03</a></td>
				<td style="text-align:left">特种设备点检标准</td>
				<td style="text-align:left">其它</td>
				<td style="text-align:left">生产部</td>
			</tr>
	     	</@listTable>
	     	</table> 
     <@buttonBar>
     	<@vbutton value="删除" class="button" />
     </@buttonBar>
     </@ww.form>
</@htmlPage>