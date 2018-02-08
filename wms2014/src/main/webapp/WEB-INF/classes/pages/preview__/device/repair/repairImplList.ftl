<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="维修实施查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'维修实施编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'维修实施名称'" name="'area.code'" value="" cssClass="'underline'"/>
	        </tr>
	        <tr>
	 			<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"/>
	        </tr>
	        <tr>
			 	<@pp.datePicker label="'从维修实施开工时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     		<@pp.datePicker label="'至维修实施开工时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
	        </tr>
	        <tr>
	        	<@dept0/>
	        	<@workflow/>
	        </tr>
        </@inputTable> 
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
		 	<th>维修实施编号</th>
		 	<th>维修实施名称</th>
		 	<th>维修计划编号</th>
		 	<th>设备编号</th>
		 	<th>设备名称</th>
		 	<th>部门</th>
		 	<th>维修实施开工时间</th>
		 	<th>维修实施完工时间</th>
		 	<th>实施总费用(元)</th>
		 	<th>实施负责人</th>
			<th>状态</th>
		 	</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/device/repair/editRepairImpl.html">20080809110401000</a></td>
				<td style="text-align:left">机床维修</td>
				<td style="text-align:left">20060456</td>
				<td style="text-align:left">200608094564</td>
				<td style="text-align:left">机床</td>
				<td style="text-align:left">机动部</td>
				<td>2008-08-09</td>
				<td>2008-08-12</td>
				<td style="text-align:right">500</td>
				<td style="text-align:left">TOM</td>
				<td style="text-align:left">.......</td>				
			</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/device/repair/editRepairImpl.html">20080809110401000</a></td>
				<td style="text-align:left">螺杆维修</td>
				<td style="text-align:left">200805061245</td>
				<td style="text-align:left">200805061245</td>
				<td style="text-align:left">螺杆</td>
				<td style="text-align:left">机动部</td>
				<td>2008-08-09</td>
				<td>2008-08-12</td>
				<td style="text-align:right">500</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">........</td>			
			</tr>
	     	</@listTable>
	     	</table>
	     	 <@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/preview/device/repair/editRepairImpl.html"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>
	    	
	    	
     </@ww.form>
</@htmlPage>