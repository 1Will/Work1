<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="维修申请查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'维修编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'维修名称'" name="'area.code'" value="" cssClass="'underline'"/>
	        </tr>
	        <tr>
	            <@ww.textfield label="'编制人'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@pp.datePicker label="'编制日期'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>	     		
	     		<#--<@pp.datePicker label="'申请人'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>-->
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
			 	<th>维修编号</th>
			 	<th>维修名称</th>
			 	<th>设备编号</th>
			 	<th>设备名称</th>
			 	<th>部门</th>
			 	<th>维修原因</th>
			 	<th>预计维修费用</th>
			 	<th>实际维修费用</th>
			 	<th>维修开始日期</th>
		 	</tr>
			<tr>
				<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/device/repair/editReqRepair.html">20080809110401000</a></td>						
				<td style="text-align:left">机床维修</td>
				<td style="text-align:left">JAC-021</td>
				<td style="text-align:left">机床</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">2008-02-01</td>
			</tr>				
	     	</@listTable>
	     	</table>
	     	 <@buttonBar>
	    		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/preview/device/repair/editReqRepair.html"/>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>
	    	
	    	
     </@ww.form>
</@htmlPage>