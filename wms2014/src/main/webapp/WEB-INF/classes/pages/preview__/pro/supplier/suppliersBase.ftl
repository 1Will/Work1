<#include "../../includes/macros2.ftl" />
<script language="JavaScript" type="text/JavaScript"> 

	
	function supplier_OpenDialog() {
		popupModalDialog('${req.contextPath}/pro/supplier/listSupplierLevelHistory.html',750,400);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',750,400);
	}	

</script>
<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
<@inputTable>
<tr>
<@ww.textfield label="'供应商编号'" name="'suppliers.code'" value="" cssClass="'underline'" required="true" readonly="true" />	
<@ww.textfield label="'供应商名称'" name="'suppliers.name'" value="" cssClass="'underline'" required="true" />	
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
	                    	required="true"
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
	                    	required="true"
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
	                    	required="true"
	        	/>
	 	       <@companyProperty />
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
	                    	required="true"
	        	/>   
	          <@ww.textfield label="'供应商地区'" name="'manufuture.name'" value="" cssClass="'underline'" required="true" />	
	       </tr>
<tr>
            <td align="right" valign="top"><label class="label"><font color="red">*</font>供应商等级:</label></td>
	        	<td>
	        		<select type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""/>
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
		        <@ww.textfield label="'法人'" name="'area.code'" value="" cssClass="'underline'" required="true"  readonly="true"/>
</tr>
 <tr>
	<@ww.textfield label="'备注'" name="'suppliers.jypz'" value="" required="true" size="40" cssClass="'underline'" />
	</tr>
	<tr>
<@audit2 />
</tr>        	    
</@inputTable>
<@buttonBar>
 <@redirectButton value="保存" url="#"/>
  <@redirectButton value="提交" url="#"/>
 <@redirectButton value="返回" url="${req.contextPath}/preview/pro/supplier/listSuppliers.html"/>
</@buttonBar>
</@ww.form>