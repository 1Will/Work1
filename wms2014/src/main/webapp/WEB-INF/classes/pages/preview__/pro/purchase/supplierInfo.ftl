<#include "../../includes/macros2.ftl" />
<@framePage title="供应商信息">
	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@inputTable>
<tr>
<@ww.textfield label="'供应商编号'" name="'suppliers.code'" value="" cssClass="'underline'"   readonly="true" />	
<@ww.textfield label="'供应商名称'" name="'suppliers.name'" value="" cssClass="'underline'"   />	
</tr>
<tr>
	 	 <@ww.select label="'供应商类别'"
	                    	name="suppliers"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	      '设备供应商',
	                    	       '工装供应商' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	disabled="true"
	        	/>
	        	      	 <@ww.select label="'注册资金'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'10~100万元',
	                    	       '100~1000万元',
	                    	      '1000万元以上'
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	disabled="true"
	        	/>	
</tr>
<tr>
	        	<@ww.select label="'企业所属行业'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'冶金',
	                    	       '勘测',
	                    	      '电力',
	                    	      '铸造'
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	disabled="true"
	        	/>
	 	       <@ww.select label="'公司性质'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'国营', 
	                    			'集体', 
	                    			'外资', 
	                    			'私营'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	disabled="true"
	        	/>  
	 	       </tr>
	 	       <tr>
		 <@ww.select label="'供应商所属国家'"
	                    	name="nation"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '中国',
	                    	       '韩国',
	                    	       '美国' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	disabled="true"
	        	/>   
	          <@ww.textfield label="'供应商地区'" name="'manufuture.name'" value="" cssClass="'underline'" readonly="true"/>	
	       </tr>
<tr>
            <td align="right" valign="top"><label class="label">供应商等级:</label></td>
	        	<td>
	        		<select type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" disabled/>
	        			<option>所有</option>
	                    <option>恶劣</option>
	                    <option>一般</option>
	                    <option>良好</option>
	                    <option>优秀</option> 
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="supplier_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@ww.textfield label="'法人'" name="'area.code'" value="" cssClass="'underline'" />
</tr>
<tr>
	<@ww.textfield label="'联系人'" name="'area.code'" value="" cssClass="'underline'" />
	<@ww.textfield label="'电话'" name="'area.code'" value="" cssClass="'underline'" />
</tr>
<tr>
	<@ww.textfield label="'传真'" name="'area.code'" value="" cssClass="'underline'" />
	<@ww.textfield label="'邮编'" name="'area.code'" value="" cssClass="'underline'" />
	<@ww.textfield label="'地址'" name="'area.code'" value="" cssClass="'underline'" />
</tr>
<tr>
	<@ww.textfield label="'电邮'" name="'area.code'" value="" cssClass="'underline'" />
	<@ww.textfield label="'网址'" name="'area.code'" value="" cssClass="'underline'" />
</tr>
 <tr>
	 <@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	</tr>
	<tr>
</tr>        	    
</@inputTable>

</@ww.form>

</@framePage>