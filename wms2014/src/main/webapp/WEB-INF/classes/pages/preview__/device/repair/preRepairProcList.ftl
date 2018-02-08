<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="预防性维修实施查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'预防性维修计划编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'预防性维修计划名称'" name="'device.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
				<@dept0/>
	 		</tr>
	 	</@inputTable>
	 	<@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	        	<tr>
	        		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	        		<th>预防性维修计划编号</th>
	        		<th>预防性维修计划名称</th>
	        		<th>部门</th>
	        		<th>计划开始时间</th>
	        		<th>计划完成时间</th>
	        		<th>实际开始时间</th>
	        		<th>实际完成时间</th>
	        		<th>执行结果</th>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:right"><a href="editPreRepairProc.html" >1</a></td>
	        		<td style="text-align:left">冲压一厂10月预防性维修计划</td>
	        		<td style="text-align:left">冲压一厂</td>
	        		<td>2007-10-1</td>
	        		<td>2007-10-28</td>
	        		<td>2007-10-11</td>
	        		<td>2007-10-29</td>
	        		<td style="text-align:left">未完成</th>
	        	</tr>
	        </@listTable>
	    </table>
	    <@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="listPreRepairPlan.html"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
	 </@ww.form>
</@htmlPage>