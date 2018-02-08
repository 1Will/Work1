<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="新增设备计划查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'新增设备计划编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'新增设备计划名称'" name="'device.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@dept0/>
	 			<@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'"/> 
	 		</tr>
	 		<tr>
		  		 <@ww.select label="'审批状态'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'已审批', 
	                    	'未审批'
	                    }"
	                 value="selectedDevice"
	      		/>  
	 		</tr>
	 	</@inputTable>
	 	<@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editAppendDevice.html"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	        	<tr>
	        		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	        		<th>新增设备计划编号</th>
	        		<th>新增设备计划名称</th>
	        		<th>部门</th>
	        		<th>年度</th>
	        		<th>编制人</th>
	        		<th>编制日期</th>
	        		<th>总费用</th>
	        		<th>审批状态</th>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="editAppendDevice.html" >000001</a></td>
	        		<td style="text-align:left">2008年度新增设备计划</td>
	        		<td style="text-align:left">冲压一厂</td>
	        		<td>2008</td>
	        		<td>sa</td>
	        		<td>2007-12-23</td>
	        		<td>10000</td>
	        		<td style="text-align:left">未审批</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="editAppendDevice.html" >000002</a></td>
	        		<td style="text-align:left">2007年度新增设备计划</td>
	        		<td style="text-align:left">焊冲一厂</td>
	        		<td>2007</td>
	        		<td>sa</td>
	        		<td>2006-12-23</td>
	        		<td>10000</td>
	        		<td style="text-align:left">已审批</td>
	        	</tr>
	        </@listTable>
	    </table>
	    <@buttonBar>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
	 </@ww.form>
</@htmlPage>