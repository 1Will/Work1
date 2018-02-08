<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="点检项目">

<script language="JavaScript" type="text/JavaScript"> 

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>点检部位</th>
			 	<th>点检</th>
			 	<th>故障台时(小时)</th>
			 	<th>设备保养(小时)</th>
			 	<th>其它(小时)</th>
			 	<th>操作人</th>
			<tr>			
			<#list ["1", "2"] as x>			
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300); return false;">${x}</a></td>				
				<td><input type="text" class="underline"/>
				<td><input type="text" class="underline"/>
				<td><input type="text" class="underline"/>
				<td><input type="text" class="underline"/>
				<td><input type="text" class="underline"/>
			</tr>
			</#list>
			<tr>
			<td></td>
				<td colspan="1" style="text-align:right;font-weight:bold" >合计:</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
			</tr>
	     </@listTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/tool/checkpoint/listCheckPointPlans.html"/>	
        </@buttonBar>
    </@ww.form>
    
	<ul id="beautytab">
		<li>
		<a id="extInfo" onclick="activeTab(this);" class="selectedtab" href="listCheckPointPlanItems.html" target="frame" >点检计划明细</a>
		</li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>