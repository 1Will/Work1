<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('deviceFaultBillEdit.title')}">
    <@ww.form namespace="'/runmaintenance/fault'" name="'deviceFaultBill'" action="'saveDeviceFaultBill'" method="'post'" validate="true">
        <@ww.token name="saveDeviceFaultBillToken"/>
       <#-- <@ww.hidden name="'toolingDevFlag'" value="${(bTooling?string)?if_exists}"/>-->
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#if faultBill.id?exists>
          <@ww.hidden name="'faultBill.id'" value="#{faultBill.id}"/>
        </#if>
        <@ww.hidden name="'flag'" value="'DeviceFault'"/>
        <@ww.hidden name="'deviceState'" value=""/>
        <@inputTable>
        <tr>
	    	<@ww.select label="'${action.getText('faultBillCategory')}'"  name="'faultBill.result'" 
	    			     listKey="value" listValue="label" value="'${faultBill.result?if_exists}'"
	                     list="results"
	                     required="true">
	    	</@ww.select>

        </tr>
        	<tr>
        		<@ww.textfield label="'${action.getText('faultBillNo')}'" name="'faultBill.billNo'" value="'${faultBill.billNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('faultBillName')}'" name="'faultBill.billName'" value="'${faultBill.billName?if_exists}'" cssClass="'underline'" required="true" />
        	 
        	</tr>
        	<@eam2008_DeviceSelector flag="DeviceFault" required="true" select="true"/>
	        <tr>
          	<#if device?exists && faultBill.deviceCatorgy?exists>
		  	  	<@ww.textfield label="'${action.getText('device.category')}'" name="'deviceCategory'"   value="'${faultBill.deviceCatorgy?if_exists}'" cssClass="'underline'" disabled="true" readonly="true" />
			<#else>
			  	<@ww.textfield label="'${action.getText('device.category')}'" name="'deviceCategory'"   value="" cssClass="'underline'" disabled="true" readonly="true" />
			</#if>
			<input type="hidden" name="device.category" value="${faultBill.deviceCatorgy?if_exists}" /> 
			
	        <@ww.textfield label="'${action.getText('device.devicePosition')}'" name="'faultBill.devicePosition'" value="'${faultBill.devicePosition?if_exists}'" cssClass="'underline'"/>
	        </tr>
	        <tr>
	        <@ww.textfield label="'${action.getText('tooling.toolingName')}'" name="'faultBill.toolingName'" value="'${faultBill.toolingName?if_exists}'" cssClass="'underline'"/>
	            <@pp.datePicker label="'${action.getText('faultOccurDateTime')}'" name="'faultBill.faultOccurDateTm'"
	     							value="'${(faultBill.faultOccurDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true" maxlength="10"/>
	        	
			</tr>
			<tr>	
			<#assign managerName = ''/>
				<#if faultBill.manager?exists>
				 <#assign managerName = "${faultBill.manager.name}" />
				   <#elseif loginUser?exists>
		           <#assign managerName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('faultManager')}:</label></td>
	        	<td>
	        		<input type="text" name="manager.name" 
	        			class="underline"  value="${managerName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="manager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign managerId = ''/>
				<#if faultBill.manager?exists>
				 	<#assign managerId = "${faultBill.manager.id}" />
				 	<#elseif loginUser?exists>
				  <#assign managerId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" />
				
				<@ww.select label="'${action.getText('faultCatorgy')}'" name="'faultBill.faultCatorgy'"
				    		listKey="id" listValue="value" value="'${(faultBill.faultCatorgy)?if_exists}'"
				    		list="faultCatorgys" 
				    		required="true">
		    	</@ww.select>
			 </tr>	
			<tr>	
				<@ww.textarea  label="'${action.getText('faultDetailContent')}'" 
	        	         name="'faultBill.faultDetailContent'" 
	        	         value="'${faultBill.faultDetailContent?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" required="true"/>
           </tr>
            <#if faultBill.id?exists>
             <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		 	 <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('fault.status')}:</label></td>
            	<td>
		 			<input type="checkbox" name="faultStateFlag" value="DEVICE_NORMAL" onclick="changeFaultState()"/>${action.getText('faultBill.solution')}
		 		</td>
		 	 </tr>
		 	 <tr>
		       <@pp.datePicker label="'${action.getText('faultBill.stopTimeBegin')}'" name="'faultBill.stopTimeBegin'" onchange="'calStopDateDiff();'"
	     							value="'${(faultBill.stopTimeBegin?string('yyyy-MM-dd HH:mm'))?if_exists}'" cssClass="'underline'" required="false" size="15" dateFormat="datetime" showsTime="true" maxlength="16"/>
	     	   <@pp.datePicker label="'${action.getText('faultBill.stopTimeEnd')}'" name="'faultBill.stopTimeEnd'" onchange="'calStopDateDiff();'"
	     							value="'${(faultBill.stopTimeEnd?string('yyyy-MM-dd HH:mm'))?if_exists}'" cssClass="'underline'" size="15" required="false" dateFormat="datetime" showsTime="true" maxlength="16"/>
		 	 </tr>
		 	 <tr>
		 	   <@pp.datePicker label="'${action.getText('faultBill.repairTimeBegin')}'" name="'faultBill.repairTimeBegin'"
	     							value="'${(faultBill.repairTimeBegin?string('yyyy-MM-dd HH:mm'))?if_exists}'" cssClass="'underline'"  dateFormat="datetime"  showsTime="true" required="false" size="15" maxlength="16" onchange="'callTime();'"/>
	     	   <@pp.datePicker label="'${action.getText('faultBill.repairTimeEnd')}'" name="'faultBill.repairTimeEnd'"
	     							value="'${(faultBill.repairTimeEnd?string('yyyy-MM-dd HH:mm'))?if_exists}'" cssClass="'underline'"  dateFormat="datetime" showsTime="true" size="15" required="false" maxlength="16" onchange="'callTime();'"/>
		 	 </tr>
             <tr>
             <tr>
                  <@ww.textfield label="'${action.getText('fault.lossTime')}'" name="'faultBill.faultLossTime'" value="'${faultBill.faultLossTime?if_exists}'" cssClass="'underline'" required="false"/>
	              <@ww.textfield label="'${action.getText('fault.repairTime')}'" name="'faultBill.faultRepairTime'" value="'${faultBill.faultRepairTime?if_exists}'" cssClass="'underline'" required="false"/>
	         </tr>	
	        	<tr>
	        	<#--<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('fault.repairPeople')}:</label></td>
	        	<td>
	        		<input type="text" name="repairPeople" 
	        			class="underline"  value="${faultBill.repairPeople?if_exists}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="repairPeople_OpenDialog();">
		        		<img id="repairPeopleImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	<@ww.hidden name="'faultBill.repairPeople'" value="'${faultBill.repairPeople?if_exists}'"/>
		        </td>-->
		        <@ww.textfield label="'${action.getText('fault.repairPeople')}'" name="'faultBill.repairPeople'" value="'${faultBill.repairPeople?if_exists}'" cssClass="'underline'"/>
			 </tr>
             <tr>
                <@ww.textarea  label="'${action.getText('faultCause')}'" 
	        	         name="'faultBill.faultCause'" 
	        	         value="'${faultBill.faultCause?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="true"/>
	        	<@ww.textarea  label="'${action.getText('faultSolution')}'" 
	        	         name="'faultBill.faultSolution'" 
	        	         value="'${faultBill.faultSolution?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="true"/>
			 </tr>
		</#if> 
        </@inputTable>
        <@buttonBar>
        <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	     </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/fault/listDeviceFaultBills.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	     
	       <#--
	      	<@vbutton name="print"  class="button" value="${action.getText('faultRecord.print')}" onclick="open_discardPdf('#{faultBill.id}')"/>
	        <@redirectButton name="Link" value="${action.getText('faultRecord.print')}" url="${req.contextPath}/preview/device/repair/listReqRepairs.html"/>-->

	        <#if faultBill.id?exists>
              <@vbutton name="print"  class="button" value="${action.getText('DeviceButtonUrlPDF')}" onclick="open_FaultBillPdf('#{faultBill.id}')"/>
              <@vbutton name="print"  class="button" value="${action.getText('DeviceButtonUrlXLS')}" onclick="open_FaultBillXls('#{faultBill.id}')"/>
              <@vbutton name="'faultRepairLink'"  class="button" value="${action.getText('faultRepairDetailReport')}" onclick="faultRepairDetailReport_openDialog(#{faultBill.id});"/>
	        </#if>
	        
	    </@buttonBar>
	 </@ww.form>
		<script language="JavaScript" type="text/JavaScript">
		 function peopleMainManager_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
		 }	
		 function userUnSealedSelectorHandler(result) {
		 if(null!=result){
		 
		  		document.forms[0].elements["manager.name"].value = result[1];
		  		document.forms[0].elements["manager.id"].value = result[0];
		  		}
		}
		 function changeSealedFaultState() {
			   if (document.forms[0].elements["unusedAgree"].checked == true) {
				    getObjByNameRe("requestPeople").style.display='inline';
				    getObjByNameRe("requestIdea").style.display='inline';
		     }else{
				    getObjByNameRe("requestPeople").style.display='none';
				    getObjByNameRe("requestIdea").style.display='none';
		     }
		  }
		  window.onload = function() { 
		    var faultFlag = ${(faultBill.faultFlag)?string};
		    if (faultFlag) {
		      document.forms[0].elements["faultStateFlag"].checked=true;
		    }
		    <#if faultBill.faultCatorgy?exists>
		    	getObjByName('faultBill.faultCatorgy').value=#{faultBill.faultCatorgy.id?if_exists}
		    </#if>
		    <#if faultBill.department?exists>
		      <#if faultBill.toolingDev?exists>
		      <#else>
		      document.forms[0].elements["department.id"].value=#{faultBill.department.id?if_exists};
		      </#if>
	        <#elseif loginUser.department?exists>
	          document.forms[0].elements["department.id"].value=#{loginUser.department.id};
	        </#if>
		    <#if (faultBill.id)?exists>
	       	<#else>
		       	a = new Date();
				var time=a.format("yyyy-MM-dd");
				document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value=time;
			</#if>
		    /*
             *  根据故障状态字段的checkbox对象是否存在、其值是true或false，来置设备状态
             *  checkbox对象不存在：deviceState 为'DEVICE_REPAIR'
             *  checkbox对象为true: deviceState 为'DEVICE_NORMAL'
             *  checkbox对象为false: deviceState 为'DEVICE_REPAIR'
            */ 
		    if (document.forms[0].elements["faultStateFlag"] == null) {
	          document.forms[0].elements["deviceState"].value='DEVICE_REPAIR';      //DEVICE_REPAIR为设备状态为"维修中"
	        } else if (document.forms[0].elements["faultStateFlag"].checked == false) {
	          document.forms[0].elements["deviceState"].value='DEVICE_NORMAL';      //DEVICE_NORMAL为设备状态为"正常"
	          disableElements(document.forms["deviceFaultBill"],new Array("faultBill.faultSolution","faultBill.faultCause","faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.faultRepairTime","faultBill.stopTimeEnd","faultBill.repairTimeEnd","faultBill.faultLossTime","faultBill.repairPeople"),true );
	          disableCalendars(document.forms["deviceFaultBill"],new Array("faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd"),true);
	          //document.forms[0].elements["repairPeopleImg"].style.display="none";
	        } else {
	          document.forms[0].elements["deviceState"].value = 'DEVICE_REPAIR'  //DEVICE_REPAIR为设备状态为"维修中"
              disableElements(document.forms["deviceFaultBill"],new Array("faultBill.faultSolution","faultBill.faultCause","faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd","faultBill.faultRepairTime","faultBill.faultLossTime","faultBill.repairPeople"),false );
              disableCalendars(document.forms["deviceFaultBill"],new Array("faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd"),false);
	          //document.forms[0].elements["repairPeopleImg"].style.display="inline";
	        }
		  }
	    </script>
	    <script language="JavaScript" type="text/JavaScript">
	    /*
	    *
	    *  计算维修耗时
	    */
	     function callTime(){
            if ('' != document.forms[0].elements["faultBill.repairTimeBegin"].value && '' != document.forms[0].elements["faultBill.repairTimeEnd"].value) {
              var repairBeginTime = document.forms[0].elements["faultBill.repairTimeBegin"].value;
              var repairEndTime = document.forms[0].elements["faultBill.repairTimeEnd"].value;
              repairBeginTime = new Date(repairBeginTime.substr(0,4),repairBeginTime.substr(5,2),repairBeginTime.substr(8,2),repairBeginTime.substr(11,2),repairBeginTime.substr(14,2));
              repairEndTime = new Date(repairEndTime.substr(0,4),repairEndTime.substr(5,2),repairEndTime.substr(8,2),repairEndTime.substr(11,2),repairEndTime.substr(14,2));
              var timeDiff = Date.prototype.DateDiff('n',repairEndTime) - Date.prototype.DateDiff('n',repairBeginTime);
              document.forms[0].elements["faultBill.faultRepairTime"].value=timeDiff;
            }
          }
	  function customize_validate() {
	      	if(!document.forms[0].onsubmit()) {
	          return false;
	        }
	        if(!faultBill_name()){
		  		return false;
		  	}
		  	if(!device_deviceNo()){
		  		return false;
		  	}
	        <#--if(!eam2008_device_validate("${action.getText('select.device.no')}")){
					return false;
			}-->
			//验证部门是否为空
			 <#if faultBill.toolingDev?exists>
		     <#else>
	            if ('' == document.forms[0].elements["department.id"].value) {
	              alert("${action.getText("select.department")}");
	              return false;
	            }
	         </#if>
			 if(getObjByNameRe("faultBill.toolingName").value!=''){
			   if(!isValidLength(document.forms[0], "faultBill.toolingName", null, 50)){
				alert("${action.getText('faultBill.toolingName.length')}");
				return  false;
				}
              }
	        /*
	         *  验证故障发生时间字段的时间格式
	        */
	        if ('' != document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value) {
	          if (!faultOccurDateTmValidate()) {
	            return false;
	          }
	        } else {
	          alert("${action.getText('select.faultOccurDateTm')}");
	          return false;
	        }
	        /*
	         *  验证负责人字段是否为空
	        */
	        if ('' == document.forms["deviceFaultBill"].elements["manager.name"].value) {
	          alert("${action.getText('select.manager')}");
	          return false;
	        }
	        
	       /*
	        * 验证故障类别是否为空
	        */
	        if('' == getObjByName('faultBill.faultCatorgy').value){
	        	alert("${action.getText('select.faultCatorgy')}");
	        	return false;
	        }
	        
	         if ('' == document.forms["deviceFaultBill"].elements["faultBill.faultDetailContent"].value) {
	            alert("${action.getText('select.faultDetailContent')}");
	            return false;
	          } else {
	            if (!isValidLength(document.forms["deviceFaultBill"], new Array("faultBill.faultDetailContent"), 0, 250)) {
	              alert("${action.getText('faultDetailContent.length.overflow')}");
	              return false;
	            }
	          }
	        <#if faultBill.id?exists> 
	          if (document.forms[0].elements["faultStateFlag"].checked == true) {
	            if (!faultBeginEndStopDateTimeValidate()) {                   //验证停机开始时间以及停机结束时间
	              return false;
	            }
	            if (!faultBeginEndRepairTimeValidate()) {                     //验证维修开始时间以及维修结束时间
	              return false;
	            }
	            if ('' != document.forms["deviceFaultBill"].elements["faultBill.faultLossTime"].value) {
		          if (!isNumberBetweenBoolen(document.forms["deviceFaultBill"].elements["faultBill.faultLossTime"].value,10000000001,0)) {
		            alert("${action.getText('faultLossTime.formate.error')}");
		            return false;
		          }
		        }
		       if(getObjByNameRe("faultBill.repairPeople").value!=''){
			   if(!isValidLength(document.forms[0], "faultBill.repairPeople", null, 50)){
				alert("${action.getText('faultBill.repairPeople.length')}");
				return  false;
				}
              }
		        if ('' != document.forms["deviceFaultBill"].elements["faultBill.faultSolution"].value) {
		          if (!isValidLength(document.forms["deviceFaultBill"], new Array("faultBill.faultSolution"), 0, 250)) {
		            alert("${action.getText('faultSolution.length.overflow')}");
		            return false;
		          }
		        }
		        if ('' != document.forms["deviceFaultBill"].elements["faultBill.faultCause"].value) {
		          if (!isValidLength(document.forms["deviceFaultBill"], new Array("faultBill.faultCause"), 0, 250)) {
		            alert("${action.getText('faultCause.length.overflow')}");
		            return false;
		          }
		        }
		      }
	        </#if>
	 
	        return true;
	      }
	      //验证停机开始时间以及停机结束时间
		  function faultBeginEndStopDateTimeValidate() {
		    if ('' != document.forms["deviceFaultBill"].elements["faultBill.stopTimeBegin"].value) {
	          if (!isDate("faultBill.stopTimeBegin")) {
	            alert("${action.getText('faultBill.stopTimeBegin')}"+ "${action.getText('dateTimeFormate.error')}");
		        return false;
	          }else{
	          	 if(!isDateLessThenOldDate(document.forms["deviceFaultBill"].elements["faultBill.stopTimeBegin"].value,document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value)){
	          	 	alert("${action.getText('faultBill.faultOccurDateTm.lessThen.stopTimeBegin')}");
		        	return false;
	          	 }
	          }
	        } 
	        if ('' != document.forms["deviceFaultBill"].elements["faultBill.stopTimeEnd"].value) {
	          if (!isDate("faultBill.stopTimeEnd")) {
	            alert("${action.getText('faultBill.stopTimeEnd')}" + "${action.getText('dateTimeFormate.error')}");
		        return false;
	          }else{
	          	 if(!isDateLessThenOldDate(document.forms["deviceFaultBill"].elements["faultBill.stopTimeEnd"].value,document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value)){
	          	 	alert("${action.getText('faultBill.faultOccurDateTm.lessThen.stopTimeEnd')}");
		        	return false;
	          	 }
	          }
	        }
	        var beginStopTime = document.forms["deviceFaultBill"].elements["faultBill.stopTimeBegin"].value;
	        var endStopTime = document.forms["deviceFaultBill"].elements["faultBill.stopTimeEnd"].value;
	        if (('' != beginStopTime) && ('' != endStopTime)) {
	          if (!isDate_Hour_Min_LessThenOldDate_Hour_Min(endStopTime,beginStopTime)) {
	            alert("${action.getText('stopTimeEnd.stopTimeBegin.logic.error')}");
	            return false;
	          }
	        }
		    return true;
		  }
		  //验证维修开始时间以及维修结束时间
		  function faultBeginEndRepairTimeValidate() {
		    if ('' != document.forms["deviceFaultBill"].elements["faultBill.repairTimeBegin"].value) {
	          if (!isDate("faultBill.repairTimeBegin")) {
	            alert("${action.getText('faultBill.repairTimeBegin')}" + "," + "${action.getText('dateFormate.error')}");
		        return false;
	          }else{
	          	 if(!isDateLessThenOldDate(document.forms["deviceFaultBill"].elements["faultBill.stopTimeBegin"].value,document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value)){
	          	 	alert("${action.getText('faultBill.faultOccurDateTm.lessThen.repairTimeBegin')}");
		        	return false;
	          	 }
	          }
	        } 
	        if ('' != document.forms["deviceFaultBill"].elements["faultBill.repairTimeEnd"].value) {
	          if (!isDate("faultBill.repairTimeEnd")) {
	            alert("${action.getText('faultBill.repairTimeEnd')}" + "," + "${action.getText('dateFormate.error')}");
		        return false;
	          }else{
	          	 if(!isDateLessThenOldDate(document.forms["deviceFaultBill"].elements["faultBill.repairTimeEnd"].value,document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value)){
	          	 	alert("${action.getText('faultBill.faultOccurDateTm.lessThen.repairTimeEnd')}");
		        	return false;
	          	 }
	          }
	        } 
	        var repairTimeBegin = document.forms["deviceFaultBill"].elements["faultBill.repairTimeBegin"].value;
	        var repairTimeEnd = document.forms["deviceFaultBill"].elements["faultBill.repairTimeEnd"].value;
	        if (('' != repairTimeBegin) && ('' != repairTimeEnd)) {
	          if (!isDateLessThenOldDate(repairTimeEnd,repairTimeBegin)) {
	            alert("${action.getText('repairTimeEnd.repairTimeBegin.logic.error')}");
	            return false;
	          }
	        }
		    return true;  
		  }
		  function manager_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, managerSelectorHandler);
		  }
		  function managerSelectorHandler(result) {
		    document.forms["deviceFaultBill"].elements["manager.id"].value = result[0];
		    document.forms["deviceFaultBill"].elements["manager.name"].value = result[1];
		  }
		  function changeFaultState() {
		    if (document.forms["deviceFaultBill"].elements["faultStateFlag"].checked == true) {
		      document.forms["deviceFaultBill"].elements["deviceState"].value = 'DEVICE_NORMAL';
	          disableElements(document.forms["deviceFaultBill"],new Array("faultBill.faultSolution","faultBill.faultCause","faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd","faultBill.faultRepairTime","faultBill.faultLossTime","faultBill.repairPeople"),false );
	          disableCalendars(document.forms["deviceFaultBill"],new Array("faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd"),false);
             // document.forms[0].elements["repairPeopleImg"].style.display="inline";
		    } else {
		      document.forms["deviceFaultBill"].elements["deviceState"].value = 'DEVICE_REPAIR';
	          disableElements(document.forms["deviceFaultBill"],new Array("faultBill.faultSolution","faultBill.faultCause","faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd","faultBill.faultRepairTime","faultBill.faultLossTime","faultBill.repairPeople"),true );
              disableCalendars(document.forms["deviceFaultBill"],new Array("faultBill.stopTimeBegin","faultBill.repairTimeBegin","faultBill.stopTimeEnd","faultBill.repairTimeEnd"),true);
		     // document.forms[0].elements["repairPeopleImg"].style.display="none";
		    }
		  }
		  
		  function faultOccurDateTmValidate() {
		    if (!isDate("faultBill.faultOccurDateTm")) {
		      alert("${action.getText('faultOccurDateTime')}" + "," + "${action.getText('dateFormate.error')}");
		      return false;
		    }
		    var date = document.forms["deviceFaultBill"].elements["faultBill.faultOccurDateTm"].value;
	        if (isDateLessThenCurrent(date)) {
	          alert("${action.getText('faultOccurDateTm.later.error')}");
	          return false;
	        }
		    return true;
		  }
	      function faultBill_name(){
			  var name=document.forms[0].elements["faultBill.billName"].value;
				if(!(name=='')){
				  	if(!isValidLength(document.forms[0], "faultBill.billName", null, 50)){
				  		alert("${action.getText('faultBill.billName.length')}");
				  		return  false;
				  	}
			  	}
				return true;
	      }
	      function device_deviceNo(){
			  var name=document.forms[0].elements["device.deviceNo"].value;
				if(name!=''){
				  	if(!isValidLength(document.forms[0], "device.deviceNo", null, 50)){
				  		alert("${action.getText('faultBill.deviceNo.length')}");
				  		return  false;
				  	}
			  	}else{
			  		alert("${action.getText('faultBill.device.deviceNo')}");
			  		return false;
			  	}
				return true;
	      }
         function calStopDateDiff(){
            if ('' != document.forms[0].elements["faultBill.stopTimeBegin"].value && '' != document.forms[0].elements["faultBill.stopTimeEnd"].value) {
              var stopTimeBegin = document.forms[0].elements["faultBill.stopTimeBegin"].value;
              var stopTimeEnd = document.forms[0].elements["faultBill.stopTimeEnd"].value;
              stopTimeBegin = new Date(stopTimeBegin.substr(0,4),stopTimeBegin.substr(5,2),stopTimeBegin.substr(8,2),stopTimeBegin.substr(11,2),stopTimeBegin.substr(14,2));
              stopTimeEnd = new Date(stopTimeEnd.substr(0,4),stopTimeEnd.substr(5,2),stopTimeEnd.substr(8,2),stopTimeEnd.substr(11,2),stopTimeEnd.substr(14,2));
              var timeDiff = Date.prototype.DateDiff('n',stopTimeEnd) - Date.prototype.DateDiff('n',stopTimeBegin);
              document.forms[0].elements["faultBill.faultLossTime"].value=timeDiff;
            }
          }
          function repairPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, repairPeopleSelectorHandler);
		  }
		  function repairPeopleSelectorHandler(result) {
		    if (result != null) {
		      document.forms[0].elements["faultBill.repairPeople"].value = result[1];
		      document.forms[0].elements["repairPeople"].value = result[1];
		    }
		  }
		  /*
           * 打开维修详细报告页面
          */
          function faultRepairDetailReport_openDialog(faultBillId) {
            var url = '${req.contextPath}/runmaintenance/faultRepair/editFaultRepair.html?readOnly=${req.getParameter('readOnly')?if_exists}&faultBill.id=' + faultBillId + '&toolingDevFlag=DEVICE';
	        popupModalDialog(url, 1024,768);
	      }

	      function open_FaultBillXls(id) {
				var faultBillId=id;
			
				var url='${req.contextPath}/reports/device/deviceRepairReqBill.xls?faultBillId='+faultBillId;
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      }
	      function open_FaultBillPdf(id) {
				var faultBillId=id;
				var url='${req.contextPath}/reports/device/deviceRepairReqBill.pdf?faultBillId='+faultBillId;
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      }
		</script>

</@htmlPage>