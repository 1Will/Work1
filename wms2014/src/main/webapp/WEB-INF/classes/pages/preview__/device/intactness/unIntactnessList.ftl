<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备鉴定查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'鉴定编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'鉴定名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	   
	        <tr>
	            <@dept0/>
	        	<@pp.dateRanger label="'鉴定日期'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>
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
	      		 <@ww.checkbox label="'仅查询失效'" name="'exclude_disabled'" value="'true'" fieldValue="'true'"/>
	        </tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editDeviceIntactness.html"/>
        </@buttonBar>
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>鉴定编号</th>
				<th>鉴定名称</th>
				<th>部门</th>
				<th>鉴定日期</th>
				<th>审批状态</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="" value="true" disabled="true"></td>
				<td style="text-align:left"><a href="editDeviceIntactness.html">07010102-02</a></td>
				<td style="text-align:left">冲焊一厂鉴定</td>
				<td style="text-align:left">冲焊一厂</td>
				<td>2007-01-23</td>
				<td style="text-align:left">已审批</td>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDeviceIntactness.html">07010102-04</a></td>
				<td style="text-align:left">涂装一厂鉴定</td>
				<td style="text-align:left">涂装一厂</td>
				<td>2007-01-23</td>
				<td style="text-align:left">未审批</td>
			</tr>			
	     	</@listTable>
	     	<@buttonBar>
	    		<@vbutton value="失效" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    		<@vbutton value="打印" class="button" />
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>