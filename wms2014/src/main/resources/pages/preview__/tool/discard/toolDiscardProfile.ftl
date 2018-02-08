<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="工装报废维护">
<script language="JavaScript" type="text/JavaScript"> 

	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	 <tr>
        		<@ww.textfield label="'报废编号'" name="'deviceNo'" value="'07010102-01'" cssClass="'underline'" readonly="true" disabled="true"/>
        		<@ww.textfield label="'报废名称'" name="'installPlace'" value="''" cssClass="'underline'" required="true"/>
     		</tr>
	        <tr>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">工装编号:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='tool_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 				<input type="text" style="border:0px" name="device.name" disabled="true" value="工装名称"/>
		 		</td>
	        	<@ww.textfield label="'工装图号'" name="'area.code'" value=""  required="false" cssClass="'underline'" readonly="true" disabled="true"/>
	        </tr>
	       	<tr>
	      		 <@ww.select label="'工装类别'"
		                     name="'toolCategory'"
		                   	 headerKey="1" 
		                     headerValue="Select Month"
		                    list="{
		                    		'模具', 
		                    		'夹具', 
		                    		'工位器具',
		                    		'检具',
		                    		'辅具'
		                    	  }"
		                    value="selectedDevice"
		                    disabled="true"
		                    	
		        	/> 
		        	
		        	<@ww.select label="'类别'"
		                    	name="'category'"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{
		                    			
		                    	  	  }"
		                    	value="selectedDevice"
		                    	 disabled="true"
		                    	
		        	/>  
	        <tr>
	        	<@ww.select label="'使用单位'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    			
		                    		'焊冲一厂', 
		                    		'总装一厂'
		                    	  }"
		                    value="selectedDevice"
		                    disabled="true"
		                    	
		        	/> 
	       		<@ww.textfield label="'使用定额'" name="'name'" required="false" value="''" cssClass="'underline'" readonly="true" disabled="true"/>
	       	</tr>
	       	<tr>
	       		<@ww.textfield label="'生产数量'" name="'name'" required="false" value="''" cssClass="'underline'"/>
	       		<@ww.textfield label="'工装价值'" name="'name'" required="false" value="''" cssClass="'underline'"/>
	        </tr>

	        <tr>
	       		 <@ww.textfield label="'工装现状'" name="'name'" required="false" value="''" cssClass="'underline'"/>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">申报人:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='tool_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 			</td>
	        </tr>

			<tr>
				<@ww.select label="'申报单位'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    			
		                    		'焊冲一厂', 
		                    		'总装一厂'
		                    	  }"
		                    value="selectedDevice"
		                    required="true"
		                    	
		        	/> 
		     	<@pp.datePicker label="'申报时间'" name="'cardCreatedTime'"
		        	value="" cssClass="'underline'" size="15" required="true"/>	
			</tr>
			<@oneLine>
	        <tr>
	        	 <td align="right" vlign="middle" rowspan="3">
                	<label  class="label">报废原因:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="deviceExtInfo.comment" rows="3" cols="80"></textarea>
            	</td>            	
			</tr>
			</@oneLine>
			<tr>
		       	<td colspan="8"><HR align="middle" size="1" width="95%" color="#999999" noshade/></td><td></td>
		    </tr>
			 <tr>
			    <@eam2008_RecordLog creator="sa" createdTime="2007-01-01" 
				 			   	  lastOperator="sa" lastModifiedTime="2007-01-01"/>
			 </tr>
  		</@inputTable>
         <@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="提交" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/tool/listTools.html"/>
        </@buttonBar>

    </@ww.form>
</@htmlPage>