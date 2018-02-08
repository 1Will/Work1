<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备领用查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'领用单编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'领用单名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>       
	        <tr>
	        	<@ww.select label="'部门'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'机动部', 
	                    	'技术部',
	                    	'品管部',
	                    	'生产部',
	                    	'冲压'
	                    }"
	                 value="selectedDevice"
	      		/>  
	        	<@pp.dateRanger label="'领用日期'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>
		  		 <@ww.select label="'状态'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'归还', 
	                    	'领用'
	                    }"
	                 value="selectedDevice"
	      		/>  
	        </tr>	 
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editDeviceBorrowBill.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>领用单编号</th>
				<th>领用单名称</th>
				<th>设备编号</th>
				<th>设备名称</th>
				<th>设备类别</th>
				<th>部门</th>
				<th>领用人</th>
				<th>领用日期</th>
				<th>验收人</th>
				<th>验收日期</th>
				<th>保管员</th>
				<th>状态</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDeviceBorrowBill.html">07010102-02</a></td>
				<td style="text-align:left">设备领用单</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">机动部</td>
				<td style="text-align:left">王辉</td>
				<td>2007-09-22 12:30</td>
				<td style="text-align:left">赵云</td>
				<td>2007-09-22 12:30</td>
				<td style="text-align:left">张家辉</td>
				<td style="text-align:left">已归还</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>