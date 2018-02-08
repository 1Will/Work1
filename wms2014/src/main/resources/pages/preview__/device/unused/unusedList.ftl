<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备闲置查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'闲置编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'闲置名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>       
	        <tr>
	        	<@pp.dateRanger label="'闲置日期'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>
		  		 <@ww.select label="'状态'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'申请中', 
	                    	'已闲置',
	                    	'已启用'
	                    }"
	                 value="selectedDevice"
	      		/>  
	      		<@ww.checkbox label="'仅查询失效'" name="'exclude_disabled'" value="'false'" fieldValue="'false'"/>
	        </tr>	 
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editUnused.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>闲置编号</th>
				<th>闲置名称</th>
				<th>设备编号</th>
				<th>设备名称</th>
				<th>闲置申请人</th>
				<th>闲置日期</th>
				<th>闲置原因</th>
				<th>状态</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editUnused.html">07010102-02</a></td>
				<td style="text-align:left">机床闲置</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">张辉</td>
				<td>2007-09-22 12:30</td>
				<td style="text-align:left">该设备目前位需使用</td>
				<td style="text-align:left">已闲置</td>
			</tr>
			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editUnused.html">07010102-03</a></td>
				<td style="text-align:left">机床闲置</td>
				<td style="text-align:left">CSM6106</td>
				<td style="text-align:left">精密普通机床</td>
				<td style="text-align:left">张辉</td>
				<td>2007-09-22 12:30</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">已启用</td>
			</tr>				
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton value="失效" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    		<@vbutton value="打印" class="button" />
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>