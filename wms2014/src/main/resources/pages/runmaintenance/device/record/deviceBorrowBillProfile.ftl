<#include "../../../includes/eam2008.ftl" />


<@htmlPage title="${action.getText('deviceBorrowEdit.title')}">
    <@ww.form namespace="'/runmaintenance/device/record'" name="'toolingBorrowBill'" action="'saveDeviceBorrowBill'" method="'post'" validate="true">
        <@ww.token name="saveToolingBorrowBillToken"/>
        <@ww.hidden name="'toolingDev_Flag'" value="'${toolingDev_Flag?if_exists}'" />
        <#if toolingBorrowBill.id?exists>
          <@ww.hidden name="'toolingBorrowBill.id'" value="#{toolingBorrowBill.id}"/>
        </#if>
        <@ww.hidden name="'flag'" value="'Borrow'"/>
        <@ww.hidden name="'toolingState'" value=""/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@ww.hidden name="'oldDeviceIds'" value="'${oldDeviceIds?if_exists}'"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('billNo')}'" name="'toolingBorrowBill.billNo'" value="'${toolingBorrowBill.billNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('billName')}'" name="'toolingBorrowBill.billName'" value="'${toolingBorrowBill.billName?if_exists}'" cssClass="'underline'" required="true" />
        	</tr>
        	<#if toolingBorrowBill.returnFlag == false>
        	  <@eam2008_DeviceSelector_Borrow flag="Borrow" oldDeviceIds="${oldDeviceIds?if_exists}"/>
        	<#else>
        	  <@eam2008_DeviceSelector_Borrow display="true" flag="Borrow"/>
        	</#if>
        	 <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
        	 <tr>
	        	<#assign borrowName = ''/>
				<#if toolingBorrowBill.borrower?exists>
				 <#assign borrowName = "${toolingBorrowBill.borrower.name}" />
				 <#elseif loginUser?exists>
			          <#assign borrowName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('tooling.borrower')}:</label></td>
	        	<td>
	        		<input type="text" name="borrower.name" 
	        			class="underline"  value="${borrowName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
	        		<#if toolingBorrowBill.returnFlag == false>
		    		<a onClick="borrower_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	</#if>
		        </td>
		        <#assign borrowerId = ''/>
				<#if toolingBorrowBill.borrower?exists>
				 	<#assign borrowerId = "${toolingBorrowBill.borrower.id}" />
				 	<#elseif loginUser?exists>
				  <#assign borrowerId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="borrower.id" value="${borrowerId}" />
	            <@pp.datePicker label="'${action.getText('tooling.borrowerDateTm')}'" name="'toolingBorrowBill.borrowReturnDateTm'"
	     							value="'${(toolingBorrowBill.borrowReturnDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true" maxlength="10"/>
            </tr>
            
            <#if toolingBorrowBill.id?exists>
             <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		 	  <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('tooling.borrowState')}:</label></td>
            	<td>
		 			<input type="checkbox" name="returnStateFlag" value="TOOLING_NORMAL" onclick="changeReturnState()"/>${action.getText('tooling.return')}
		 		</td>
		 		<#assign repayPeople = ''/>
				<#if toolingBorrowBill.repayPeople?exists>
				<#assign repayPeople = "${toolingBorrowBill.repayPeople?if_exists}" />
				<#elseif loginUser?exists>
				<#assign repayPeople = "${loginUser.name}" />
				</#if>
	        	<td id="requestState" style="display:none" align="right" valign="top"><span id="produceNumSapn" class="required" style="display:none">*</span><label class="label">${action.getText('repayPeople')}:</label></td>
	        	<td id="requestState0" style="display:none">
	        		<input type="text" name="repayPeople" 
	        			class="underline"  value="${repayPeople}"  maxlength="150" size="20" disabled="true" readOnly="true"/>
	        		<label id=""></label>
		    		<a onClick="repayPeople_OpenDialog();">
		        		<img id="repayPeopleImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign repayPeopleName = ''/>
				<#if toolingBorrowBill.repayPeople?exists>
				 <#assign repayPeopleName = "${toolingBorrowBill.repayPeople?if_exists}" />
				 <#elseif loginUser?exists>
				<#assign repayPeopleName = "${loginUser.name}" />
				</#if>
				<input type="hidden" name="toolingBorrowBill.repayPeople" value="${repayPeopleName}" />
		 <#--		
                <td id="requestState" style="display:none" align="right" valign="top"><span id="produceNumSapn" class="required" style="display:none">*</span><label class="label">${action.getText('repayPeople')}:</label></td>
                <td id="requestState0" style="display:none">
              		<input type="text" name="toolingBorrowBill.repayPeople" class="underline"  value="${toolingBorrowBill.repayPeople?if_exists}"  maxlength="150" disabled="true"/>
                </td>
          -->      
		 	  </tr>
            <tr id="requestState1" style="display:none">
	        	<@ww.textarea  label="'${action.getText('returnDeviceState')}'" 
	        	         name="'toolingBorrowBill.productTailState'" 
	        	         value="'${toolingBorrowBill.productTailState?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="true"/>
	        	<@ww.textarea  label="'${action.getText('device.notes')}'" 
	        	         name="'toolingBorrowBill.repairMaintenance'" 
	        	         value="'${toolingBorrowBill.repairMaintenance?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="true"/>
			</tr>
            <tr id="requestState2" style="display:none">
	        	<#assign checkerName = ''/>
				<#if toolingBorrowBill.checker?exists>
				<#assign checkerName = "${toolingBorrowBill.checker.name}" />
				<#elseif loginUser?exists>
				<#assign checkerName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span id="checkerSpan" class="required" style="display:none">*</span><label class="label">${action.getText('checker')}:</label></td>
	        	<td>
	        		<input type="text" name="checker.name" 
	        			class="underline"  value="${checkerName}"  maxlength="150" size="20" disabled="true" readOnly="true"/>
	        		<label id=""></label>
		    		<a onClick="checker_OpenDialog();">
		        		<img id="checkerImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign checkerId = ''/>
				<#if toolingBorrowBill.checker?exists>
				 <#assign checkerId = "${toolingBorrowBill.checker.id}" />
				 <#elseif loginUser?exists>
				<#assign checkerId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="checker.id" value="${checkerId}" />
	            <@pp.datePicker label="'${action.getText('checkDateTime')}'" name="'toolingBorrowBill.checkDateTm'"
	     							value="'${(toolingBorrowBill.checkDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" maxlength="10"/>
            </tr>
            <tr id="requestState3" style="display:none">
	        	<#assign storemanName = ''/>
				<#if toolingBorrowBill.storeman?exists>
				 <#assign storemanName = "${toolingBorrowBill.storeman.name}" />
				 <#elseif loginUser?exists>
			          <#assign storemanName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span id="storemanSpan" class="required" style="display:none">*</span><label class="label">${action.getText('storeman')}:</label></td>
	        	<td>
	        		<input type="text" name="storeman.name" 
	        			class="underline"  value="${storemanName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="storeman_OpenDialog();">
		        		<img id="storemanImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
		        	</a>
		        </td>
		        <#assign storemanId = ''/>
				<#if toolingBorrowBill.storeman?exists>
				 <#assign storemanId = "${toolingBorrowBill.storeman.id}" /><#---->
				 <#elseif loginUser?exists>
					<#assign storemanId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="storeman.id" value="${storemanId}" />
	            <@pp.datePicker label="'${action.getText('inDateTime')}'" name="'toolingBorrowBill.inDateTm'"
	     							value="'${(toolingBorrowBill.inDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" maxlength="10"/>
            </tr>   
            </#if>       
        </@inputTable>
        <@buttonBar>
         <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	      </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/device/record/listDeviceBorrowBills.html?toolingDev_Flag=${req.getParameter('toolingDev_Flag')?if_exists}&&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if toolingBorrowBill.id?exists>
		      <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_toolingBorrowPdf('#{toolingBorrowBill.id}')"/>
		      <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_toolingBorrowXls('#{toolingBorrowBill.id}')"/>
	      </#if>
	    </@buttonBar>
	 </@ww.form>
		<script language="JavaScript" type="text/JavaScript">
		  function open_toolingBorrowXls(id){
		     var toolingBorrowBillId=id;
			 var url='${req.contextPath}/reports/tooling/toolingBrrow.xls?toolingBorrowBillId='+toolingBorrowBillId;
      		 window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		  }
		  function open_toolingBorrowPdf(id){
		     var toolingBorrowBillId=id;
			 var url='${req.contextPath}/reports/tooling/toolingBrrow.pdf?toolingBorrowBillId='+toolingBorrowBillId;
      		 window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		  }
		  
	      window.onload = function () {
	        <#if (toolingBorrowBill.borrowReturnDateTm)?exists>
	        <#else>
	          a = new Date();
			  var time=a.format("yyyy-MM-dd");   
			  document.forms[0].elements["toolingBorrowBill.borrowReturnDateTm"].value=time;
	        </#if>
	        /*
	         *  获取老的生产数量的值
	        */    
            var returnFlag = ${toolingBorrowBill.returnFlag?string};
            if (returnFlag) {                                         //如果归还，置returnStateFlag标签为选中状态
              document.forms["toolingBorrowBill"].elements["returnStateFlag"].checked = true;
              getObjByNameRe("requestState").style.display='inline';
		      getObjByNameRe("requestState0").style.display='inline';
		      getObjByNameRe("requestState1").style.display='inline';
		      getObjByNameRe("requestState2").style.display='inline';
		      getObjByNameRe("requestState3").style.display='inline';
            }
            /*
             *  根据领用状态字段的checkbox对象是否存在、其值是true或false，来置工装状态
             *  checkbox对象不存在：toolingState 为'TOOLING_BORROW'
             *  checkbox对象为true: toolingState 为'TOOLING_NORMAL'
             *  checkbox对象为false: toolingState 为'TOOLING_BORROW'
            */            
	        if (document.forms["toolingBorrowBill"].elements["returnStateFlag"] == null) {
	          document.forms["toolingBorrowBill"].elements["toolingState"].value='TOOLING_BORROW';      //0152为工装状态为"使用中"的code
	        } else if (document.forms["toolingBorrowBill"].elements["returnStateFlag"].checked == false) {
	          document.forms["toolingBorrowBill"].elements["toolingState"].value='TOOLING_BORROW';
	          img_isDisplay("none");
	          input_isDisabled(true);
	          
	        } else {
	          document.forms["toolingBorrowBill"].elements["toolingState"].value = 'TOOLING_NORMAL'  //0152为工装状态为"正常"的code
              span_isDisplay("inline");
              input_isDisabled(false);
	          img_isDisplay("inline");
	          disableElements(document.forms["toolingBorrowBill"],new Array("toolingBorrowBill.billName","toolingBorrowBill.borrowReturnDateTm"),true);
	          hideCalendarsImg(document.forms["toolingBorrowBill"],new Array("toolingBorrowBill.borrowReturnDateTm"),"none");
	        }
	        
	      }
	    </script>
	    <script language="JavaScript" type="text/JavaScript">
	      /*
	       *  对生产数量,产品尾件状况,维修保养内容,入库日期,验收日期字段控件是否disable
	       *  msg : true|false
	      */
	      function input_isDisabled(msg) {
	        document.forms["toolingBorrowBill"].elements["toolingBorrowBill.repayPeople"].disabled=msg;
	        document.forms["toolingBorrowBill"].elements["toolingBorrowBill.productTailState"].disabled=msg;
	        document.forms["toolingBorrowBill"].elements["toolingBorrowBill.repairMaintenance"].disabled=msg;
	        document.forms["toolingBorrowBill"].elements["toolingBorrowBill.inDateTm"].disabled=msg;
	        document.forms["toolingBorrowBill"].elements["toolingBorrowBill.checkDateTm"].disabled=msg;	        
	      }
	      /*
	       *  对验收人,保管员,入库日期,验收日期字段的IMG标签是否显示
	       *  msg : none|inline
	      */
	      function img_isDisplay(msg) {
	      	getObjByNameRe("repayPeopleImg").style.display=msg;
            getObjByNameRe("checkerImg").style.display=msg;
	        getObjByNameRe("storemanImg").style.display=msg;
	        getObjByNameRe("toolingBorrowBill_toolingBorrowBill.inDateTm_trigger").style.display=msg;
	        getObjByNameRe("toolingBorrowBill_toolingBorrowBill.checkDateTm_trigger").style.display=msg;	        
	      }
	      /*
	       *  对验收人，保管员，生产数量字段的span标签是否显示
	       *  msg : none|inline
	      */
	      function span_isDisplay(msg) {
	        getObjByNameRe("checkerSpan").style.display=msg;
	        getObjByNameRe("storemanSpan").style.display=msg;
	        getObjByNameRe("produceNumSapn").style.display=msg;
	      }
		  function borrower_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, desigerSelectorHandler);
		  }
		  function desigerSelectorHandler(result) {
		    document.forms["toolingBorrowBill"].elements["borrower.id"].value = result[0];
		    document.forms["toolingBorrowBill"].elements["borrower.name"].value = result[1];
		  }
		  
		  
		  function repayPeople_OpenDialog(){
		  	var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, repayPeopleSelectorHandler);
		  }
		  function repayPeopleSelectorHandler(result){
		  	var repayPeople = result[1];
		  	document.forms["toolingBorrowBill"].elements["repayPeople"].value = repayPeople;
		  	document.forms["toolingBorrowBill"].elements["toolingBorrowBill.repayPeople"].value = repayPeople;
		  }
		  
		  
		  
		  function checker_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, checkerSelectorHandler);
		  }
		  function checkerSelectorHandler(result) {
		    document.forms["toolingBorrowBill"].elements["checker.id"].value = result[0];
		    document.forms["toolingBorrowBill"].elements["checker.name"].value = result[1];
		  }
		  function storeman_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, storemanSelectorHandler);
		  }
		  function storemanSelectorHandler(result) {
		    document.forms["toolingBorrowBill"].elements["storeman.id"].value = result[0];
		    document.forms["toolingBorrowBill"].elements["storeman.name"].value = result[1];
		  }
		  function device_OpenDialog(url) {
		    popupModalDialog(url, 800, 600, deviceSelectorHandler);
		  }
		  function deviceSelectorHandler(result) {
		    document.forms["toolingBorrowBill"].elements["device.id"].value = result[0];
		    document.forms["toolingBorrowBill"].elements["device.name"].value = result[1];
		  }
		  function changeReturnState() {
		    if (document.forms["toolingBorrowBill"].elements["returnStateFlag"].checked == true) {
		      getObjByNameRe("requestState").style.display='inline';
		      getObjByNameRe("requestState0").style.display='inline';
		      getObjByNameRe("requestState1").style.display='inline';
		      getObjByNameRe("requestState2").style.display='inline';
		      getObjByNameRe("requestState3").style.display='inline';
		      document.forms["toolingBorrowBill"].elements["toolingState"].value = 'TOOLING_NORMAL';
		    <#if (toolingBorrowBill.checkDateTm)?exists>
	        <#else>
	          a = new Date();
			  var time=a.format("yyyy-MM-dd");   
			  document.forms[0].elements["toolingBorrowBill.checkDateTm"].value=time;
	        </#if>
		      span_isDisplay("inline");
	          input_isDisabled(false);
	          img_isDisplay("inline");
	          
		    } else {
		      getObjByNameRe("requestState").style.display='none';
		      getObjByNameRe("requestState0").style.display='none';
		      getObjByNameRe("requestState1").style.display='none';
		      getObjByNameRe("requestState2").style.display='none';
		      getObjByNameRe("requestState3").style.display='none';
		      document.forms["toolingBorrowBill"].elements["toolingState"].value = 'TOOLING_BORROW';
		      span_isDisplay("none");
	          input_isDisabled(true);
	          img_isDisplay("none");	      
		    }
		  }
		  function customize_validate() {
		   if(getObjByNameRe("toolingBorrowBill.billName").value==''){
		           alert("${action.getText('toolingBorrowBill.billName.not.null')}");
		           return false;
		   }else{
		      if(!isValidLength(document.forms[0], "toolingBorrowBill.billName", null, 50)){
				alert("${action.getText('toolingBorrowBill.billName.MaxLength')}");
				return  false;
			   }   
		      }
		    if(getObjByNameRe("device.deviceNo").value==''){
		      alert("${action.getText('select.device.name')}");
		      return false;
		    }
		   <#--   
		    if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		      return false;
		    }
		    -->
		    if (document.forms["toolingBorrowBill"].elements["borrower.name"].value == '') {
		      alert("${action.getText('select.borrower.name')}");
		      return false;
		    }
		    if (document.forms["toolingBorrowBill"].elements["toolingBorrowBill.borrowReturnDateTm"].value == '') {
		       alert("${action.getText('select.borrowReturnDateTm')}");
		       return false;
		    } else {
		      if (!borrowReturnDateTmValidate()) {
		        return false;
		      }
		    }
		  function borrowReturnDateTmValidate() {
		  	var borrowReturnDateTm=getObjByNameRe("toolingBorrowBill.borrowReturnDateTm").value;
		    if (!validateDateFormate(borrowReturnDateTm, "${action.getText('tooling.borrowerDateTm')}")) {
		      return false;
		    }
		    if(isDateLessThenCurrent(borrowReturnDateTm)){
		    	alert("${action.getText('borrowReturnDateTm.earlyError')}");
		    	return false;
		    }
			return true;
		  }  
		   
		    if ('' != getObjByNameRe("toolingBorrowBill.checkDateTm").value) {
			   if (!isDate("toolingBorrowBill.checkDateTm")) {
			      alert("${action.getText('checkDateTime')}" + "," + "${action.getText('dateFormate.error')}");
			      return false;
			    }
			    var date = getObjByName('toolingBorrowBill.checkDateTm').value;
		        if (isDateLessThenCurrent(date)) {
		          alert("${action.getText('checkDateTime.later.error')}");
		          return false;
		        }
              if (!checkDateTm()) {
                return false;
              }

            }
            if ('' != getObjByNameRe("toolingBorrowBill.inDateTm").value) {
 			if (!isDate("toolingBorrowBill.inDateTm")) {
			  alert("${action.getText('inDateTime')}" + "," + "${action.getText('dateFormate.error')}");
		      return false;
		    }
		    var date = getObjByName('toolingBorrowBill.inDateTm').value;
	        if (isDateLessThenCurrent(date)) {
	          alert("${action.getText('inDateTm.later.error')}");
	          return false;
	        }           
            var inDateTm = getObjByName('toolingBorrowBill.inDateTm').value;
              if (!inDateTmValidate()) {
                return false;
              }
            }
	       if(getObjByName('produceNumSapn').style.display == 'inline'){
	       		if(getObjByName('toolingBorrowBill.repayPeople').value == ''){
	       			alert("${action.getText('select.toolingBorrowBill.repayPeople')}");
	       		}
	       		
	       }
       
   
		   if (getObjByNameRe("checkerSpan").style.display == 'inline') {
		      if (document.forms["toolingBorrowBill"].elements["checker.name"].value == '') {
		        alert("${action.getText('select.checker.name')}");
		        return false;
		      }
		    }
		    if (getObjByNameRe("storemanSpan").style.display == 'inline') {
		      if (document.forms["toolingBorrowBill"].elements["storeman.name"].value == '') {
		        alert("${action.getText('select.store.name')}");
		        return false;
		      }
		    }
 
            if ('' != getObjByNameRe("toolingBorrowBill.inDateTm").value) {
              if (!isDate("toolingBorrowBill.inDateTm")) {
              	 alert("${action.getText('inDateTime')}" + "," + "${action.getText('dateFormate.error')}");
                return false;
              }
            }
            if ((document.forms["toolingBorrowBill"].elements["toolingBorrowBill.productTailState"].value).length>250) {
              alert("${action.getText('toolingBorrowBill.productTailState.maxLength')}");
              return false;
            }
            
            if(getObjByNameRe("toolingBorrowBill.repairMaintenance").value!=''){
		   if(!isValidLength(document.forms[0], "toolingBorrowBill.repairMaintenance", null, 250)){
				alert("${action.getText('toolingBorrowBill.repairMaintenance')}");
				return  false;
			}
		  }
		    return true;
		   
		  }
		  
		 
		  function checkDateTm() {
		    var borrowReturnDateTm=getObjByNameRe("toolingBorrowBill.borrowReturnDateTm").value;
		    var checkDateTm=getObjByNameRe("toolingBorrowBill.checkDateTm").value;
		    if (!validateDateFormate(checkDateTm, "${action.getText('checkDateTime')}")) {
		      return false;
		    }
		    var borrowReturnYear=borrowReturnDateTm.substr(0,4);
		    var borrowReturnMonth=borrowReturnDateTm.substr(5,2);
			var borrowReturnDay=borrowReturnDateTm.substr(8,2);
			var checkYear=checkDateTm.substr(0,4);
		    var checkMonth=checkDateTm.substr(5,2);
			var checkDay=checkDateTm.substr(8,2);
			if (borrowReturnYear>checkYear) {
			  alert("${action.getText('checkDateTm.earlyError')}");
			  return false;
			} else if ((borrowReturnYear==checkYear) && (borrowReturnMonth>checkMonth)) {
			  alert("${action.getText('checkDateTm.earlyError')}");
			  return false;
			} else if ((borrowReturnMonth==checkMonth) && (borrowReturnDay>checkDay)) {
			  alert("${action.getText('checkDateTm.earlyError')}");
			  return false;
			}
			return true;
		  }
		  function inDateTmValidate() {
		    var borrowDateTm = document.forms[0].elements["toolingBorrowBill.borrowReturnDateTm"].value;
		    var inDateTm = document.forms[0].elements["toolingBorrowBill.inDateTm"].value;
		    if (!isDateLessThenOldDate(inDateTm, borrowDateTm)) {
		      alert("${action.getText("inDateTm.earlyError")}");
		      return false;
		    }
		    return true;
		  }
		  function validateDateFormate(strDate, validateObjectMessage) {    //验证时间格式
		    var dateFormate = /^(?:[123][0-9]\d{2})\-(?:0?[1-9]{1}|1([0-2])?)\-(?:0?[1-9]{0,1}|([12][0-9]){1,2}|(3[0-1]){1,2})$/
		    if (!dateFormate.test(strDate)) {
		      if ('' == validateObjectMessage) {
		        alert("${action.getText('dateFormate.error')}");
		      } else {
		        alert(validateObjectMessage + "," + "${action.getText('dateFormate.error')}");
		      }
		      return false;
		    }
		    return true;
		  }
		</script>
 
</@htmlPage>