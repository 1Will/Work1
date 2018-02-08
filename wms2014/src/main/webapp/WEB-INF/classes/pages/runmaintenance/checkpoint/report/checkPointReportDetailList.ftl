<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->
<#include "../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('checkPointRepairDetailList.title')}">
	<@ww.form name="'checkPointRepairDetail'" action="'saveCheckPointReportDetails'" method="'post'">
	<@ww.token name="saveCheckPointReportDetailsToken"/>
	<@ww.hidden name="'report.id'" value="'#{report.id?if_exists}'"/>
	<@ww.hidden name="'addDeviceIds'" value=""/>
	<@ww.hidden name="'addDevice'" value=""/>
	<@ww.hidden name="'saveDetails'" value="''"/>
	<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#assign itemNo=1/>
		<@list title="" excel=false setupTable=false
           includeParameters="report.id" 
           fieldMap="like:report.id" >
           <#if object.spare?exists>
              <input type="hidden" name="hiddenDetailIds" value="#{object.id}"/>
            </#if>
           <@vlh.checkbox property="id" name="checkPointReportDetailIds">
			    <@vlh.attribute name="width" value="30" />
			  </@vlh.checkbox>
		   <@vcolumn title="${action.getText('detail.itemNo')}">
		    	${itemNo}
		    	<@alignCenter/>
	       </@vcolumn>
	       <#assign itemNo = itemNo+1/>
	       <@vcolumn title="${action.getText('checkPointReportDetail.device.No')}" property="device.deviceNo">
		    	<@alignLeft/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.device.name')}" property="device.name">
		    	<@alignLeft/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.device.model')}" property="device.model">
		    	<@alignLeft/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.device.specification')}" property="device.specification">
		    	<@alignLeft/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.runTime')}">
	       		<input type="text" name="runTime" 
		    		   class="underline"  value="${object.runTime?if_exists}"   size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.maintenanceTime')}">
	       		<input type="text" name="maintenanceTime" 
		    		   class="underline"  value="${object.maintenanceTime?if_exists}"  size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.faultTime')}">
	       		<input type="text" name="faultTime" 
		    		   class="underline"  value="${object.faultTime?if_exists}"  size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.productTotalOutput')}">
	       		<input type="text" name="productTotalOutput" 
		    		   class="underline"  value="${object.productTotalOutput?if_exists}"  size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.inferiorProductOutput')}">
	       		<input type="text" name="inferiorProductOutput" 
		    		   class="underline"  value="${object.inferiorProductOutput?if_exists}"  size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	       </@vcolumn>
	       <@vcolumn title="${action.getText('checkPointReportDetail.comment')}">
		        <input type="text" name="comment"  accept="list_of_mime_types"
		    		  class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		   </@vcolumn>
	    </@list>
	    <#if !(action.isReadOnly())>
	    	<@buttonBar>
	    		<@vbutton name="new"  class="button" value="${action.getText('new')}" onclick="detail_openDialog();"/>
	    		<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('checkPointReportDetail')}?" />
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
	    		  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	    		</@vsubmit>
	        	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	        		<@ww.param name="'onclick'" value="'return confirmDeletes(\"checkPointReportDetailIds\", \"${confirmMessage}\");'"/>
	        	    <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	        	</@vsubmit>
        	</@buttonBar>
        </#if>
	 </@ww.form>
	 <script language="javascript">
	 	window.onload = function () {
		 	 <#if report.submit>
		 	 	document.forms[0].elements["new"].disabled="true";
				document.forms[0].elements["save"].disabled="true";
				document.forms[0].elements["delete"].disabled="true";
			</#if>
		}
	 
		 function detail_openDialog() {
	      var url = '${req.contextPath}/popup/deviceSelector.html';
	      eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,"DeviceCheckPoint");
	    }
	    function refresh_multi_device_information(reslut) {
	      if (null != result) {
	        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
	        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
	        document.forms[0].elements["addDevice"].value = "addDevices";
	        document.forms[0].submit();
	      }
	    }
	    
	    function submitDetailIds(){
	         var detailIdSelector=document.getElementsByName("checkPointReportDetailIds");
	         var runTime = document.getElementsByName("runTime");
	         var maintenanceTime = document.getElementsByName("maintenanceTime");
	         var faultTime = document.getElementsByName("faultTime");
	         var comment = document.getElementsByName("comment");
	         var productTotalOutput = document.getElementsByName("productTotalOutput");
	         var inferiorProductOutput = document.getElementsByName("inferiorProductOutput");
	         var detailIds="";
	         if (detailIdSelector.length) {
	             for (var i = 0; i < detailIdSelector.length; i++){
	               if(!validateComment(comment[i].value,0,250)){
	                   return false;
	               }
	                detailIds += detailIdSelector[i].value+",";
	                <#--if(''!= runTime[i].value){
	                	alert("ss");
	                	if(isNumber(formatDigital(runTime[i].value))){
	                		detailIds += formatDigital(runTime[i].value)+",";
	                	}else {
	                		alert(runTime[i].value+"${action.getText('not.number')}");
	                	}
	                }else{
	                	detailIds += detailIdSelector[i].value+",";
	                }-->
	                if (runTime[i].value != null && runTime[i].value != ""){
	                	if(!validateRunTime(runTime[i].value)){
		                    return false;
		                }
	                }
	                detailIds += formatDigital(runTime[i].value)+",";
	                if (maintenanceTime[i].value != null && maintenanceTime[i].value != ""){
	                	if(!validateMaintenanceTime(maintenanceTime[i].value)){
		                    return false;
		                }
	                }
	                detailIds += formatDigital(maintenanceTime[i].value)+",";
	                if (faultTime[i].value != null && faultTime[i].value != ""){
	                	if(!validateFaultTime(faultTime[i].value)){
		                    return false;
		                }
	                }
	                detailIds += formatDigital(faultTime[i].value)+",";
	                if (productTotalOutput[i].value != null && productTotalOutput[i].value != ""){
	                	if(!validateProductTotalOutput(productTotalOutput[i].value)){
		                    return false;
		                }
	                }
	                detailIds += formatDigital(productTotalOutput[i].value)+",";
	                if (inferiorProductOutput[i].value != null && inferiorProductOutput[i].value != ""){
	                	if(!validateInferiorProductOutput(inferiorProductOutput[i].value)){
		                    return false;
		                }
	                }
	                detailIds += formatDigital(inferiorProductOutput[i].value)+",";
	                detailIds += comment[i].value+" ,";
	             }
	         }
	         detailIds=detailIds.substring(0,detailIds.lastIndexOf(","));
	         document.getElementById("saveDetails").value=detailIds;
	         return true;
	      }
	    function validateRunTime(number){
		      number = formatDigital(number);
		      var control = isDoubleNumberBetweenBoolean(number,1001,0);
			  if(control!=1){
	              alert("${action.getText('select.right.runTime')}");
	              return false;
	          }
			  return true;
		  }
		
		function validateMaintenanceTime(number){
		      number = formatDigital(number);
		      var control = isDoubleNumberBetweenBoolean(number,1001,0);
			  if(control!=1){
	              alert("${action.getText('select.right.maintenanceTime')}");
	              return false;
	          }
			  return true;
		  }
		  
		function validateFaultTime(number){
		      number = formatDigital(number);
		      var control = isDoubleNumberBetweenBoolean(number,1001,0);
			  if(control!=1){
	              alert("${action.getText('select.right.faultTime')}");
	              return false;
	          }
			  return true;
		  }    
	      
	    function validateComment(commentValue,min,max){
		    if(isNotEmptyValue(commentValue)==true){
		        if(!isValidLengthValue(commentValue,min,max)){
		           alert("${action.getText('commentValue.length')}"+max);
		           return false;
		        }
		     }
		     return true;
		  }
		  
		  function validateProductTotalOutput(number){
		      number = formatDigital(number);
		      var control = isNumberBetween(number,1000001,0);
			  if(!control){
	              alert("${action.getText('select.right.productTotalOutput')}");
	              return false;
	          }
			  return true;
		  }
		  
		  function validateInferiorProductOutput(number){
		      number = formatDigital(number);
		      var control = isNumberBetween(number,1000001,0);
			  if(!control){
	              alert("${action.getText('select.right.inferiorProductOutput')}");
	              return false;
	          }
			  return true;
		  }
    </script>
</@framePage>   