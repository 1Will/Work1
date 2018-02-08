<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装保养标准查询">

<script language="JavaScript" type="text/JavaScript"> 
</script>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<#include "maintenanceRuleSearcher.ftl"/>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton  value="${action.getText('new')}" url="editMaintenanceRule.html"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  		  <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>保养标准编号</th>
                <th>保养标准名称</th>
			 	<th>工装编号</th>
				<th>工装名称</th>
				<th>工装图号</th>
				<th>部门</th>
				<th>保养分类</th>
				<th>保养负责人</th>
				<th>状态</th>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editMaintenanceRule.html">07010102-02</a></td>
				<td style="text-align:left">轮轴保养</td>
				<td style="text-align:left">by001</td>
				<td style="text-align:left">普通车床</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">一保</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">提交</td>
			</tr>	
	  		</@listTable>
        </table>
        	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('确认删除?')"/>
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>