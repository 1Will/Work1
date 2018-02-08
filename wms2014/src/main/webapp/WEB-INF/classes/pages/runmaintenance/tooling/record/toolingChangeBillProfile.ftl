<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingChangeBillEdit.title')}">
    <@ww.form namespace="'/runmaintenance/tooling/record'" name="'toolingChangeBill'" action="'saveToolingChangeBill'" method="'post'" validate="true">
        <@ww.token name="saveToolingChangeBillToken"/>
        <#if toolingChangeBill.id?exists>
          <@ww.hidden name="'toolingChangeBill.id'" value="#{toolingChangeBill.id}"/>
        </#if>
        <@ww.hidden name="'flag'" value="'ToolingChange'"/>
        <@ww.hidden name="'toolingState'" value=""/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('tooling.changeBillNo')}'" name="'toolingChangeBill.billNo'" value="'${toolingChangeBill.billNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('tooling.changeBillName')}'" name="'toolingChangeBill.billName'" value="'${toolingChangeBill.billName?if_exists}'" cssClass="'underline'" required="true" />
        	</tr>
        	<#if (toolingChangeBill.changeBillFlag)==false>
        	<@eam2008_ToolingSelector flag="ToolingChange"/>
        	<#else>
        	<@eam2008_ToolingSelector toolingStatus="true"/>
        	</#if>
	         <tr>
	            <@ww.textfield label="'${action.getText('department')}'" name="'tooling.department'" value="''" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
                <#-- 2008-06-24修改‘下发日期’为该变更单的下发日期！-->
                <@ww.textfield label="'${action.getText('tooling.usedDate')}'" name="'tooling.usedDate'" value="''" cssClass="'underline'" required="false" disabled="true" readonly="true"/>	            
	     		
	     		
            </tr>
            <tr>
            <#--
                <@ww.textfield label="'${action.getText('changeBill.fileNo')}'" name="'toolingChangeBill.fileNo'" value="'${toolingChangeBill.fileNo?if_exists}'" cssClass="'underline'" required="false" />
            -->
                <@pp.datePicker label="'${action.getText('tooling.planCompleteTime')}'" name="'toolingChangeBill.planCompleteTime'"
	     							value="'${(toolingChangeBill.planCompleteTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true" maxlength="10"/>	
                <@ww.select label="'${action.getText('changBill.acceptDepartment')}'" required="false" name="'department.id'" 
			    		    value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
			                list="departments" emptyOption="true" disabled="false">
			    </@ww.select>
            </tr>
            <tr>
	        	<#assign acceptorName = ''/>
				<#if toolingChangeBill.acceptor?exists>
				 <#assign acceptorName = "${toolingChangeBill.acceptor.name}" />
				<#elseif loginUser?exists>
				 <#assign acceptorName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('changBill.acceptManager')}:</label></td>
	        	<td>
	        		<input type="text" name="acceptor.name" 
	        			class="underline"  value="${acceptorName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
	        		<#if !(toolingChangeBill.changeBillFlag)>
		    		<a onClick="acceptor_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	</#if>
		        </td>
		        <#assign acceptorId = ''/>
				<#if toolingChangeBill.acceptor?exists>
				 	<#assign acceptorId = "${toolingChangeBill.acceptor.id}" />
				<#elseif loginUser?exists>
				    <#assign acceptorId = "#{loginUser.id}" />
				</#if>
				<input type="hidden" name="acceptor.id" value="${acceptorId}" />
	        	<#assign bailorName = ''/>
				<#if toolingChangeBill.bailor?exists>
				 <#assign bailorName = "${toolingChangeBill.bailor.name}" />
				<#elseif loginUser?exists>
				 <#assign bailorName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><label class="label">${action.getText('changeBill.bailorManager')}:</label></td>
	        	<td>
	        		<input type="text" name="bailor.name" 
	        			class="underline"  value="${bailorName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
	        		<#if !(toolingChangeBill.changeBillFlag)>
		    		<a onClick="bailor_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	</#if>
		        </td>
		        <#assign bailorId = ''/>
				<#if toolingChangeBill.bailor?exists>
				 	<#assign bailorId = "${toolingChangeBill.bailor.id}" />
				<#elseif loginUser?exists>
					<#assign bailorId = "#{loginUser.id}" />
				</#if>
				<input type="hidden" name="bailor.id" value="${bailorId}" />
            </tr>
            <tr>
            	<@pp.datePicker label="'${action.getText('toolingChange.createdTime')}'" name="'toolingChangeBill.createdDateTime'"
	     						value="'${(toolingChangeBill.createdDateTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true" maxlength="10"/>	
            	<@ww.textfield label="'${action.getText('toolingChange.creator')}'" name="'toolingChangeBill.createdPeople'" value="'${toolingChangeBill.createdPeople?if_exists}'" cssClass="'underline'" required="false" disabled="false" readonly="false"/>	            
            </tr>
            <tr>
	        	<@ww.textarea  label="'${action.getText('changeBill.changeReason')}'" 
	        	         name="'toolingChangeBill.changeReason'" 
	        	         value="'${toolingChangeBill.changeReason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false" required="true"/>
	        	<@ww.textarea  label="'${action.getText('changBill.changeSolution')}'" 
	        	         name="'toolingChangeBill.changeSolution'" 
	        	         value="'${toolingChangeBill.changeSolution?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false"/>
            </tr>
            <#if toolingChangeBill.id?exists>
             <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		 	 <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('changeBill.checkStatus')}:</label></td>
            	<td>
		 			<input type="checkbox" name="changeStateFlag" value="TOOLING_NORMAL" onclick="changeCheckState();"/>${action.getText('changeBill.agree')}
		 		</td>
		 	 </tr>
		 	 <tr id="changeAgree" style="display:none">
                <@pp.datePicker label="'${action.getText('tooling.actualCompleteTime')}'" name="'toolingChangeBill.actualCompleteTime'"
	     						value="'${(toolingChangeBill.actualCompleteTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true" maxlength="10"/>	
		 	    <@ww.textarea  label="'${action.getText('changeBill.checkResult')}'" 
	        	               name="'toolingChangeBill.checkResult'" 
	        	               value="'${toolingChangeBill.checkResult?if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" disabled="false"/>
		 	 </tr>
			</#if> 
        </@inputTable>
        <@buttonBar>
         <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	     </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/tooling/record/listToolingChangeBills.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if toolingChangeBill.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_toolingChangeBillPdf('#{toolingChangeBill.id}')"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_toolingChangeBillXls('#{toolingChangeBill.id}')"/>
	      </#if>
	    </@buttonBar>
	 </@ww.form>
	 <script language="JavaScript" type="text/JavaScript">
	   window.onload = function() {
	     <#if toolingChangeBill.id?exists>
	       var url = '${req.contextPath}/runmaintenance/tooling/record/listToolingChangeDocs.html?toolingChangeBill.id=${toolingChangeBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
		   document.all.frame.src= url;
		   document.getElementById("changeDoc").className = "selectedtab";
		 </#if>
		 <#if toolingChangeBill.tooling?exists>
		   <#if (toolingChangeBill.tooling.department)?exists>
		     document.forms[0].elements["tooling.department"].value = '${(toolingChangeBill.tooling.department.name)?if_exists}';
		   </#if>
		   document.forms[0].elements["tooling.usedDate"].value = '${(toolingChangeBill.tooling.usedStartedTime?string('yyyy-MM-dd'))?if_exists}'
		 </#if>
		 <#if toolingChangeBill.acceptDepartment?exists>
		   document.forms[0].elements["department.id"].value = #{(toolingChangeBill.acceptDepartment.id)?if_exists};
		 </#if>
		 <#if (toolingChangeBill.changeBillFlag)==true>
		   document.forms[0].elements["changeStateFlag"].checked=true;
		 </#if>
		 <#if toolingChangeBill.new>
		   document.forms[0].elements["toolingChangeBill.createdPeople"].value = '${loginUser.name?if_exists}';
		 </#if>
		 <#--
		function open_toolingChangeBillXLS(){
		var url='${req.contextPath}/reports/device/toolingChangeBill.xls?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		}
		-->
		 /*
          *  根据验证状态字段的checkbox对象是否存在、其值是true或false，来置工装状态
          *  checkbox对象不存在：toolingState 为'TOOLING_CHANGE'
          *  checkbox对象为true: toolingState 为'TOOLING_NORMAL'
          *  checkbox对象为false: toolingState 为'TOOLING_CHANGE'
         */ 
		 if (document.forms[0].elements["changeStateFlag"] == null) {
	       document.forms[0].elements["toolingState"].value='TOOLING_CHANGE';      //TOOLING_CHANGE为工装状态为"变更中"
	     } else if (document.forms[0].elements["changeStateFlag"].checked == true) {
	       changeStateFlagCheckedEqualTrue();
	     } else {
           changeStateFlagCheckedEqualFalse();
	     }
	   }
	   function changeStateFlagCheckedEqualTrue() {
	     document.forms[0].elements["toolingState"].value='TOOLING_NORMAL';      //TOOLING_NORMAL为工装状态为"正常"
	     //设置实际完成时间和验证结果控件的可输入状态
	     disableElements(document.forms["toolingChangeBill"],new Array("toolingChangeBill.actualCompleteTime","toolingChangeBill.checkResult"),false );      
	     disableElements(document.forms["toolingChangeBill"],new Array("toolingChangeBill.billName","toolingChangeBill.planCompleteTime","department.id","toolingChangeBill.changeReason","toolingChangeBill.changeSolution"),true);
	     hideCalendarsImg(document.forms["toolingChangeBill"],new Array("toolingChangeBill.planCompleteTime"),"none");
	     document.getElementById("changeAgree").style.display="inline";
	   }
	   function changeStateFlagCheckedEqualFalse() {
	     document.forms[0].elements["toolingState"].value = 'TOOLING_CHANGE'  //TOOLING_CHANGE为工装状态为"变更中"
	     //设置实际完成时间和验证结果控件的不可输入状态
	     disableElements(document.forms["toolingChangeBill"],new Array("toolingChangeBill.actualCompleteTime","toolingChangeBill.checkResult"),true );      
	    // disableElements(document.forms["toolingChangeBill"],new Array("toolingChangeBill.billName","toolingChangeBill.planCompleteTime","department.id","toolingChangeBill.changeReason","toolingChangeBill.changeSolution"),false);
	     //hideCalendarsImg(document.forms["toolingChangeBill"],new Array("toolingChangeBill.planCompleteTime"),"inline");
	     document.getElementById("changeAgree").style.display="none";
	   }
       function acceptor_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, acceptorSelectorHandler);
	   }
	   function acceptorSelectorHandler(result) {
	     document.forms["toolingChangeBill"].elements["acceptor.id"].value = result[0];
		 document.forms["toolingChangeBill"].elements["acceptor.name"].value = result[1];
	   }	
	   function bailor_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, bailorSelectorHandler);
	   }   
	   function bailorSelectorHandler(result) {
	     document.forms["toolingChangeBill"].elements["bailor.id"].value = result[0];
		 document.forms["toolingChangeBill"].elements["bailor.name"].value = result[1];
	   }
	   function changeCheckState() {
	     if (document.forms["toolingChangeBill"].elements["changeStateFlag"].checked == true) {
	       changeStateFlagCheckedEqualTrue();
		 } else {
           changeStateFlagCheckedEqualFalse();
		 }
	   }
	   function customize_validate() {
	     if ('' == document.forms["toolingChangeBill"].elements["toolingChangeBill.billName"].value) {
	       alert("${action.getText('toolingChangeBill.billName.requiredstring')}");
	       return false;
	     } else {
	       if (!isValidLengthValue(document.forms["toolingChangeBill"].elements["toolingChangeBill.billName"].value,0,50)) {
	         alert("${action.getText('toolingChangeBill.billName.maxLength')}");
	         return false;
	       }
	     }
	     if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		   return false;
		 }
	     /*
	      *  验证计划完成时间字段的时间格式
	     */
	     if ('' != document.forms["toolingChangeBill"].elements["toolingChangeBill.planCompleteTime"].value) {
	       if (!planCompleteTimeValidate()) {
	         return false;
	       }
	     } else {
	       alert("${action.getText('select.planCompleteTime')}");
	       return false;
	     }
	     /*
	      *  验证承接人字段是否为空
	     */
	     if ('' == document.forms["toolingChangeBill"].elements["acceptor.id"].value) {
	       alert("${action.getText('select.acceptor')}");
	       return false;
	     }
	     /*
	      *  验证编制日期字段的时间格式
	     */
	     if ('' != document.forms["toolingChangeBill"].elements["toolingChangeBill.createdDateTime"].value) {
	       if (!createTimeValidate()) {
	         return false;
	       }
	     } else {
	       alert("${action.getText('select.createdDateTime')}");
	       return false;
	     }
	     //验证编制人是否溢出
	     if ('' != document.forms["toolingChangeBill"].elements["toolingChangeBill.createdPeople"].value) {  
	       if (!isValidLengthValue(document.forms["toolingChangeBill"].elements["toolingChangeBill.createdPeople"].value,0,50)) {
	         alert("${action.getText('toolingChangeBill.createdPeople.maxLength')}");
             return false;
	       }
	     }
	     if ('' == document.forms["toolingChangeBill"].elements["toolingChangeBill.changeReason"].value) {
	       alert("${action.getText('toolingChangeBill.changeReason.requiredstring')}");
	       return false;
	     } else {
	       if (!isValidLengthValue(document.forms["toolingChangeBill"].elements["toolingChangeBill.changeReason"].value,0,250)) {
	         alert("${action.getText('toolingChangeBill.changeReason.maxLength')}");
             return false;
           }
	     } 
	     if ('' != document.forms["toolingChangeBill"].elements["toolingChangeBill.changeSolution"].value) {  //验证修改方案长度是否溢出
	       if (!isValidLengthValue(document.forms["toolingChangeBill"].elements["toolingChangeBill.changeSolution"].value,0,250)) {
	         alert("${action.getText('toolingChangeBill.changeSolution.maxLength')}");
             return false;
	       }
	     }
	     <#if toolingChangeBill.id?exists>
	       if (document.forms["toolingChangeBill"].elements["changeStateFlag"].checked == true) {
	         if ('' != document.forms["toolingChangeBill"].elements["toolingChangeBill.actualCompleteTime"].value) {
	           if (!actualCompleteTimeValidate()) {
	             return false;
	           }
	         } else {
	           alert("${action.getText('select.actualCompleteTime')}");
	           return false;
	         }
	       }
	       /*
	        *  验证验证结果字段长度是否溢出
	       */
	       if ('' != document.forms["toolingChangeBill"].elements["toolingChangeBill.checkResult"].value) {
	         if (!isValidLength(document.forms["toolingChangeBill"], new Array("toolingChangeBill.checkResult"), 0, 250)) {
	           alert("${action.getText('checkResult.length.overflow')}");
	           return false;
	         }
	       }
	     </#if>
		 return true;
	   }
	   //验证计划完成日期
	   function planCompleteTimeValidate() {
	     var faultOccurDateTm = document.forms["toolingChangeBill"].elements["toolingChangeBill.planCompleteTime"].value;
		 if (!isDate("toolingChangeBill.planCompleteTime")) {
		   alert("${action.getText('tooling.planCompleteTime')}" + "${action.getText('dateFormate.error')}");
		   return false;
		 }
		 return true;
	   }
	   //验证实际完成日期
	   function actualCompleteTimeValidate() {
	     if (!isDate("toolingChangeBill.actualCompleteTime")) {
		   alert("${action.getText('tooling.actualCompleteTime')}" + "${action.getText('dateFormate.error')}");
		   return false;
		 }
		 return true;
	   }
	   //验证创建日期
	   function createTimeValidate() {
	     var createdDateTime = document.forms["toolingChangeBill"].elements["toolingChangeBill.createdDateTime"].value;
		 if (!isDate("toolingChangeBill.createdDateTime")) {
		   alert("${action.getText('toolingChange.createdTime')}" + "${action.getText('dateFormate.error')}");
		   return false;
		 }
		 return true;
	   }
	   function open_toolingChangeBillPdf(id) {
				var toolingChangeBillId=id;
				var url='${req.contextPath}/reports/tooling/toolingChangeBill.pdf?toolingChangeBillId='+toolingChangeBillId;
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	   function open_toolingChangeBillXls(id) {
				var toolingChangeBillId=id;
				var url='${req.contextPath}/reports/tooling/toolingChangeBill.xls?toolingChangeBillId='+toolingChangeBillId;
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	 </script>
     <#if toolingChangeBill.id?exists>
	 <ul id="beautytab">
	  <li><a id="changeDoc" onclick="activeTab(this);"  href="listToolingChangeDocs.html?toolingChangeBill.id=#{toolingChangeBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('changeDoc')}</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
    </#if>
</@htmlPage>