<#include "../../includes/macros.ftl" />

<#macro framePage title="${action.getText('title')}">
    <html>
        <head>
        	<#--<meta http-equiv=content-type content="text/html; charset=UTF-8">-->
        	<meta http-equiv="Pragma" content="no-cache">
        	<meta http-equiv="Expires" content="-1">
            <title>${action.getText('name.application')}-${title}</title>
            <link rel="stylesheet" href="${req.contextPath}/stylesheets/valuelist.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css"/>
	        <script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/lang/global.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/jsrsClient.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
        </head>
        <body>
            <#nested>
        </body>
    </html>
</#macro>


<#macro audit>
 	<@inputTable>
 			<tr>
 				<@ww.textfield label="'审核说明'" name="'area.code'" value=""  cssClass="'underline'" readonly=""/>
 			</tr>
        	<tr>
	        	<td align="right" valign="top"><label class="label">审核人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="qs,sa,admin"  maxlength="150" />
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="people_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	<@ww.select label="'最终审核人'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('sa')}', 
	                    			'${action.getText('system')}', 
	                    			'${action.getText('qs')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        		/>
		        </td>
        	</tr>
	</@inputTable>
</#macro>

<#macro audit2>
            <@CUPeopleTimeInfo/>
			<tr>
 				<@ww.textfield label="'审核说明'" name="'area.code'" value=""  cssClass="'underline'" readonly=""/>
 				<@ww.textfield label="'状态'" name="'area.code'" value=""  cssClass="'underline'" readonly="true"/>
 			</tr>
        	<tr>
	        	<td align="right" valign="top"><label class="label">审核人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="qs,sa,admin"  maxlength="150" />
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="people_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	<@ww.select label="'最终审核人'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('sa')}', 
	                    			'${action.getText('system')}', 
	                    			'${action.getText('qs')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        		/>
		        </td>
        	</tr>
        	
</#macro>

<#macro dept0>  <#--查询页面 -->
<@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('机动部')}', 
	                    			'${action.getText('生产部')}'
	                    	  	  }"
	                    	value="selectedDevice"
/>
</#macro>

<#macro dept1> <#--新建页面(必选) -->
<@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('机动部')}', 
	                    			'${action.getText('生产部')}'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	required="true"
/>
</#macro>



<#macro workflow>
				<@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('提交')}', 
	                    			'${action.getText('审批中')}', 
	                    			'${action.getText('未审批')}',
	                    			'${action.getText('拒绝')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>
</#macro>

<#macro workflow1>
				<@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'${action.getText('提交')}', 
	                    			'${action.getText('审批中')}', 
	                    			'${action.getText('未审批')}',
	                    			'${action.getText('拒绝')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>
</#macro>
<#macro supplierSelector>
<tr>
                <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('供应商')}:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="30" readonly/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="supplier_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>    
</#macro>  
<#macro status>
    <@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('提交')}', 
	                    			'${action.getText('审批中')}', 
	                    			'${action.getText('未审批')}',
	                    			'${action.getText('拒绝')}'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>   
</#macro> 

<#macro companyProperty>
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
	                    	required="true"
	        	/>  
</#macro>

<#macro demo>
<@ww.textfield label="'备注'" name="'area.code'" value="" size="30" cssClass="'underline'" readonly="true" />

</#macro>


<#macro equimentLB0>
 <@ww.select label="'设备类别'"
	                    	name="nation"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有',
	                    	       '总装一厂',
	                    	       '总装二厂'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>	
</#macro>

<#macro equimentLB>
 <@ww.select label="'设备类别'"
	                    	name="nation"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '总装一厂',
	                    	       '总装二厂'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>	
</#macro>

<#macro equimentXH>
 <@ww.select label="'设备型号'"
	                    	name="nation"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       'Bird V758',
	                    	       'HUAYU 301',
	                    	       'HBH-DS200',
	                    	       'SR-CTS001'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>	
</#macro>

<#macro stop0>
	<@ww.select label="'停机原因类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('所有')}', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
	 />
</#macro>

<#macro stop1>
	<@ww.select label="'停机原因类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
	 />
</#macro>

<#macro err0>
	<@ww.select label="'故障原因类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('所有')}', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
	 />
</#macro>

<#macro err1>
	<@ww.select label="'故障原因类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
	 />
</#macro>

<#macro acc0>
	<@ww.select label="'事故原因类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('所有')}', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
	 />
</#macro>

<#macro acc1>
	<@ww.select label="'事故原因类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
	 />
</#macro>

<#macro feedback0>
	<@ww.select label="'反馈类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('所有')}', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>

<#macro feedback1>
	<@ww.select label="'反馈类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>

<#macro toolChangeType0>
	<@ww.select label="'变更类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'所有', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>

<#macro toolChangeType1>
	<@ww.select label="'变更类别'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('类别a')}', 
		                    			'${action.getText('类别b')}',
		                    			'${action.getText('类别c')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>



<#macro deviceSelector>
<tr>
	<td align="right" valign="top"><label class="label"><font color="red">*</font>设备编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" readonly maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="device_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@ww.textfield label="'设备名称'" name="'area.code'" value=""  cssClass="'underline'" readonly="true"/>
</tr>		    
<tr>
	<@ww.textfield label="'购机时间'" name="'area.code'" value="" cssClass="'underline'"  readonly="true"/>
	<@ww.textfield label="'所属部门'" name="'area.code'" value="" cssClass="'underline'"  readonly="true"/>
</tr>    
</#macro>

<#macro deviceSelector1>

	<td align="right" valign="top"><label class="label"><font color="red">*</font>设备编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="device_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
</#macro>

<#macro deviceSelector2>
<tr>
	<td align="right" valign="top"><label class="label"><font color="red">*</font>设备编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="device_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
    <@ww.textfield label="'所属部门'" name="'area.code'" value="" cssClass="'underline'"  readonly="true" required="false"/>
</tr>
</#macro>

<#macro deviceSelector2 img="display">
<tr>
	<td align="right" valign="top"><label class="label">
	<#if img?string=='display'>
	  <font color="red">*</font>
	<#else>
	</#if>
	设备编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
	        		<#if img?string=='display'>
			    		<a onClick="device_OpenDialog();">
			        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
			        	<input type="text" style="border:0px" name="tooling.name" disabled value="设备名称"/>
			        <#else>
			        <input type="text" style="border:0px" name="tooling.name" disabled value="设备名称"/>
			        </#if>
		         
		        </td>
    <@ww.textfield label="'部门'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
</tr>
</#macro>

<#macro toolSelector>
<tr>
	<td align="right" valign="top"><label class="label"><font color="red">*</font>工装编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="tool_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		        <@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
</tr>		     
</#macro>

<#macro toolingSelector1 img="display">
<tr>
	<td align="right" valign="top"><label class="label">
	<#if img?string=='display'>
	  <font color="red">*</font>
	<#else>
	</#if>
	工装编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
	        		<#if img?string=='display'>
			    		<a onClick="tool_OpenDialog();">
			        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
			        	<input type="text" style="border:0px" name="tooling.name" disabled value="工装名称"/>
			        <#else>
			        <input type="text" style="border:0px" name="tooling.name" disabled value="工装名称"/>
			        </#if>
		         
		        </td>
    <@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
</tr>
</#macro>

<#macro requisitionLogSelector>
	<td align="right" valign="top"><label class="label"><font color="red">*</font>领用记录编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="requisition_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		          <@ww.textfield label="'领用人'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
                <@pp.datePicker label="'领用时间'" name="'repair.estimate.time2'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	<tr>
		<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
              <@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		        <@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
     </tr>
</#macro>

<#macro budgetSelector>
            <tr>
                <@ww.select label="'费用来源'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('')}',
	                    			'${action.getText('计划内')}', 
	                    			'${action.getText('计划外')}'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	required="true"
	        		/>

                <td align="right" valign="top"><label class="label"><span style="color:red">*</span>预算项目号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="30"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="budget_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>
</#macro>

<#macro requestBillSelector>
            	 <td align="right" valign="top"><label class="label">申购单编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" onblur="shipmentPlan_Vehicle_Code_RemotePick(this.value);"
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="requestBill_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
</#macro>

<#macro purchaseBillSelector>
            	 <td align="right" valign="top"><label class="label">采购单编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" onblur="shipmentPlan_Vehicle_Code_RemotePick(this.value);"
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="purchaseBill_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
</#macro>
<#macro CUPeopleTimeInfo>
			<tr>
				 <@ww.textfield label="'创建人'" name="'area.code'" value="'sa'" cssClass="'underline'" disabled="true" readonly="true"/>
				 <@ww.textfield label="'创建时间'" name="'area.code'" value="'2007-07-01'" cssClass="'underline'" disabled="true" readonly="true"/>
			</tr>
			<tr>
				 <@ww.textfield label="'最后修改人'" name="'area.code'" value="'system'" cssClass="'underline'" disabled="true" readonly="true"/>
				 <@ww.textfield label="'最后修改时间'" name="'area.code'" value="'2007-08-01'" cssClass="'underline'" disabled="true" readonly="true"/>
			</tr>
</#macro>

<#macro toolSelector>
			<tr>
			  <td align="right" valign="top"><label class="label">工具编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" onblur="shipmentPlan_Vehicle_Code_RemotePick(this.value);"
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="tool_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@ww.textfield label="'工具名称'" name="'area.code'" value="''" cssClass="'underline'" disabled="true" readonly="true"/>
		      </tr>
		      <tr>
				 <@ww.textfield label="'工具规格'" name="'area.code'" value="''" cssClass="'underline'" disabled="true" readonly="true"/>
				 <@ww.textfield label="'工具型号'" name="'area.code'" value="''" cssClass="'underline'" disabled="true" readonly="true"/>
			</tr>
</#macro>

<#macro toolingSelector>
			<tr>
			  <td align="right" valign="top"><label class="label">工装编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
		    		<a onClick="tooling_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	<input type="text" style="border:0px" name="tool.name" disabled value="工装名称"/>
		        </td>
		        
		       	<#--<@ww.textfield label="'工装名称'" name="'area.code'" value="''" cssClass="'underline'" disabled="true" readonly="true"/>-->
				 <@ww.textfield label="'工装图号'" name="'area.code'" value="''" cssClass="'underline'" disabled="true" readonly="true" required="true"/>
			</tr>
</#macro>

<#macro deviceLb>
<@ww.select label="'产品类别'"
	                    	name="supplierDevice"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'类别 A', 
	                    			'类别 B',
	                    			'类别 C',
	                    			'类别 D'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	required="true"
	        		/>
</#macro>
<#macro supplierSelector>
<td align="right" valign="top"><label class="label"><font color="red">*</font>供应商编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20" readonly/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="supplier_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		            <input type="text" style="border:0px" name="tooling.name" disabled value="合家福超市"/>
		        </td>
		         
</#macro>

<#macro docTypeSelector0>
	<@ww.select label="'文档类型'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('所有')}', 
		                    			'${action.getText('点检标准')}', 
		                    			'${action.getText('点检计划')}',
		                    			'${action.getText('采购单')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>

<#macro docTypeSelector1>
	<@ww.select label="'文档类型'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{
		                    			'${action.getText('点检标准')}', 
		                    			'${action.getText('点检计划')}',
		                    			'${action.getText('采购单')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	disabled="true"/>
</#macro>

<#macro workstatus0>
	<@ww.select id="workstatus" label="'工作状态'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('所有')}', 
		                    			'${action.getText('未审批')}',
		                    			'${action.getText('已审批')}',
		                    			'${action.getText('已拒绝')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>

<#macro workstatus1>
	<@ww.select id="workstatus" label="'工作状态'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('未审批')}':'2', 
		                    			'${action.getText('已审批')}',
		                    			'${action.getText('已拒绝')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>

</#macro>

<#macro RepairGrade>
<@ww.select id="Grade" label="'维修等级'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'请选择等级', 
		                    			'${action.getText('A')}', 
		                    			'${action.getText('B')}',
		                    			'${action.getText('C')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>
</#macro>
<#macro dutydept>
  <@ww.select label="'责任单位'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('机动部')}', 
	                    			'${action.getText('生产部')}',
	                    			'${action.getText('冲压一')}',
	                    			'${action.getText('冲压二')}',
	                    			'${action.getText('冲压三')}'
	                    	  	  }"
	                    	value="selectedDevice"
/>
</#macro>

