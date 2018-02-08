<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="设备托管维护">
<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='${req.contextPath}/preview/device/trusteeship/trusteeshipDeviceList.html';
 		document.getElementById("fees").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = document.getElementById("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	 <tr>
        		<@ww.textfield label="'托管编号'" name="'deviceNo'" value="'07010102-01'" cssClass="'underline'" readonly="true" disabled="true"/>
        		<@ww.textfield label="'托管名称'" name="'installPlace'" value="''" cssClass="'underline'" required="true"/>
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
		                    	
		        	/> 
		        <@ww.select label="'现托管部门'"
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
	         	<@ww.select label="'原所属生产线'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    		'  ',	
		                    		'B1线', 
		                    		'B2线',
		                    		'A线',
		                    		'内饰工段',
		                    		'底盘工段',
		                    		'合装工段',
		                    		'调试工段'
		                    	  }"
		                    value="selectedDevice"
		                    	
		        	/> 
		        	<@ww.select label="'现托管生产线'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    		'  ',	
		                    		'B1线', 
		                    		'B2线',
		                    		'A线',
		                    		'内饰工段',
		                    		'底盘工段',
		                    		'合装工段',
		                    		'调试工段'
		                    	  }"
		                    value="selectedDevice"
		                    	
		        	/> 
	        </tr>
	        <tr>
	        	<@ww.select label="'托管申请部门'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    		'',	
		                    		'焊冲一厂', 
		                    		'总装一厂'
		                    	  }"
		                    value="selectedDevice"
		                    	
		        	/> 
	       		<td align="right" valign="top"><span class="required">*</span><label class="label">托管申请人:</label></td>
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
	        	<@ww.textarea  label="'申请原因'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
			</tr>
  		</@inputTable>
         <@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/trusteeship/listTrusteeship.html"/>
       		<@vbutton value="打印" class="button" />
        </@buttonBar>

    </@ww.form>
    <ul id="beautytab">
		<li>
		<a id="MigrateList" onclick="activeTab(this);" class="selectedtab" href="${req.contextPath}/preview/device/trusteeship/trusteeshipDeviceList.html" target="frame" >设备托管明细</a>
		</li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
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