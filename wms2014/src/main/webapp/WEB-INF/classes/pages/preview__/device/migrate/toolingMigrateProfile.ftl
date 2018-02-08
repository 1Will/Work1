<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="工装转移维护">
<script language="JavaScript" type="text/JavaScript"> 

	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	 <tr>
        		<@ww.textfield label="'转移编号'" name="'deviceNo'" value="'07010102-01'" cssClass="'underline'" readonly="true" disabled="true"/>
        		<@ww.textfield label="'转移名称'" name="'installPlace'" value="''" cssClass="'underline'" required="true"/>
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
	       		<@ww.textfield label="'原地点'" name="'name'" disabled="false" value="''" cssClass="'underline'"/>
	       		<@ww.textfield label="'新地点'" name="'name'" required="true" value="''" cssClass="'underline'"/>
	        </tr>
	         <tr>
	       		<@ww.select label="'原所属部门'"
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
		        <@ww.select label="'现所属部门'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    			
		                    		'焊冲一厂', 
		                    		'总装一厂'
		                    	  }"
		                    value="selectedDevice"
		                    disabled="false"
		                    	
		        	/> 
	        </tr>
	         <tr>
	       		<@ww.textfield label="'原所属生产线'" name="'name'" disabled="true" value="''" cssClass="'underline'"/>
	       		<@ww.textfield label="'现所属生产线'" name="'name'" required="false" value="''" cssClass="'underline'"/>
	        </tr>
	        <tr>
	        	<@ww.select label="'转移申请部门'"
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
	       		<td align="right" valign="top"><span class="required">*</span><label class="label">转移申请人:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='tool_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 			</td>
	       	</tr>
            <tr>
                 <@pp.datePicker label="'申请日期'" name="'cardCreatedTime'"
		        	value="" cssClass="'underline'" size="15" required="true"/>	
            </tr>
	        <tr>
	        	<@ww.textarea  label="'转移原因'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
			</tr>
			<tr>
		       	<td colspan="8"><HR align="middle" size="1" width="95%" color="#999999" noshade/></td><td></td>
		    </tr>
		    <tr>
		        <td align="right" valign="top"><span class="required"></span><label class="label">申请状态:</label></td>
            	<td>
		 			<input type="checkbox" name="faultStateFlag" value="TOOLING_NORMAL" onclick="changeSealedFaultState()"/>批准
		 		</td>
		    </tr>
			 <tr id="requestPeople" style="display:none">
	       		<td align="right" valign="top"><span class="required">*</span><label class="label">批准人:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='tool_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 			</td>
	 			<@pp.datePicker label="'批准日期'" name="'cardCreatedTime'"
		        	value="" cssClass="'underline'" size="15" required="true"/>	
			 </tr>
			 <tr id="requestIdea" style="display:none">
			 	<td align="right" vlign="middle" rowspan="3">
                	<label  class="label">审批意见:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="deviceExtInfo.comment" rows="3" cols="50"></textarea>
            	</td>  
			 </tr>
  		</@inputTable>
         <@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/tooling/migrate/listMigrate.html"/>
       		<@vbutton value="打印" class="button" />
        </@buttonBar>

    </@ww.form>
    <script>
    function changeSealedFaultState() {
    	   if (document.forms[0].elements["faultStateFlag"].checked == true) {
			     document.getElementById("requestPeople").style.display='inline';
			    document.getElementById("requestIdea").style.display='inline';
	     }else{
			     document.getElementById("requestPeople").style.display='none';
			    document.getElementById("requestIdea").style.display='none';
	     }
	  }
    </script>
</@htmlPage>