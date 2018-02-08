<#include "../../includes/eam2008.ftl" />
<#assign preRepairTitle = ''/>
<#assign require = ''/>
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign preRepairTitle = "${action.getText('preRepairPlanEdit.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign preRepairTitle = "${action.getText('preRepairProcEdit.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${preRepairTitle}">
    <@ww.form namespace="'/runmaintenance/repair/plan'" name="'preRepairPlan'" action="'savePreRepairPlan'" method="'post'" validate="true">
        <@ww.token name="savePreRepairPlanToken"/>
         <@ww.hidden name="'preRepairPlan.id'" value="${preRepairPlan.id?if_exists}"/>
         <#--
         <#if planProcFlag?exists>
	       <#if (planProcFlag=='PROC')>
	         <@ww.hidden name="'preRepairProc.id'" value="${preRepairPlan.id?if_exists}"/>
	       </#if>
	     </#if>
	     -->
        <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
       <#--
        <#if planProcFlag?exists>
          <#if planProcFlag='PROC'>
	        <@buttonBar>
	    		<@vbutton class="button" value="${action.getText('preRepair.selectPlan')}" onclick="preRepairPlan_OpenDialog('${req.contextPath}/popup/preRepairPlanSelector.html?planProcFlag=PLAN&toolingDevFlag=${toolingDevFlag?if_exists}');"/>
	    	</@buttonBar>
	      </#if>
    	</#if>
    	-->
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('preRepairPlanNo')}'" name="'preRepairPlan.planNo'" value="'${preRepairPlan.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('preRepairPlanName')}'" name="'preRepairPlan.name'" value="'${preRepairPlan.name?if_exists}'" cssClass="'underline'" required="${require}" disabled="${disabled}"/>
        	</tr>
        	<tr>
        		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name" 
		                list="departments" emptyOption="true" disabled="${disabled}" required="${require}">
		    	</@ww.select>
		    	<#if planProcFlag?exists>
	              <#if (planProcFlag=='PLAN')>
        		     <@pp.datePicker label="'${action.getText('beginDateTime')}'" name="'preRepairPlan.beginDate'"
	     							  value="'${(preRepairPlan.beginDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  required="${require}" maxlength="10"/>
	 	  	  	  <#else>
	 	  	  	    <@ww.textfield label="'${action.getText('beginDateTime')}'" name="'preRepairPlan.beginDate'" value="'${(preRepairPlan.beginDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" disabled="true"/>
	 	  	  	  </#if>
	 	  	  	</#if>
        	</tr>
	        <tr>
	        	<#if planProcFlag?exists>
	              <#if (planProcFlag=='PLAN')>
        		     <@pp.datePicker label="'${action.getText('preRepairPlan.planCreatedTime')}'" name="'preRepairPlan.planCreatedTime'"
	     							  value="'${(preRepairPlan.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  required="${require}" maxlength="10"/>
	 	  	  	  <#else>
	 	  	  	    <@ww.textfield label="'${action.getText('preRepairPlan.planCreatedTime')}'" name="'preRepairPlan.planCreatedTime'" value="'${(preRepairPlan.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" disabled="true"/>
	 	  	  	  </#if>
	 	  	  	</#if>
	         	<#assign planCreatorName = ''/>
					<#if preRepairPlan.planCreator?exists>
					  <#assign planCreatorName = "${preRepairPlan.planCreator.name}" />
					<#elseif loginUser?exists>
					  <#assign planCreatorName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required">*</span>
	        	  </#if>
	        	</#if>
	        	<label class="label">${action.getText('preRepairPlan.planCreator')}:</label></td>
	        	<td>
	        		<input type="text" name="planCreator.name" 
	        			class="underline"  value="${planCreatorName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
	        	    <#if planProcFlag?exists>
			          <#if (planProcFlag=='PLAN')>
	        	        <a onClick="planCreator_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
	        	      </#if>
	        	    </#if>
		        </td>
		        <#assign planCreatorId = ''/>
				<#if preRepairPlan.planCreator?exists>
				 <#assign planCreatorId = "${preRepairPlan.planCreator.id}" />
				<#elseif loginUser?exists>
				 <#assign planCreatorId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="planCreator.id" value="${planCreatorId}" />
				
            </tr>
            <tr>
            	<#assign planAuditorName = ''/>
				<#if preRepairPlan.planAuditor?exists>
				 <#assign planAuditorName = "${preRepairPlan.planAuditor.name}" />
				</#if>
	        	<td align="right" valign="top">
				<label class="label">${action.getText('preRepairPlan.planAuditor')}:</label></td>
	        	<td>
	        		<input type="text" name="planAuditor.name" 
	        			class="underline"  value="${planAuditorName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
	        	    <#if planProcFlag?exists>
			          <#if (planProcFlag=='PLAN')>
	        	        <a onClick="planAuditor_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
	        	      </#if>
	        	    </#if>
		        </td>
		        <#assign planAuditorId = ''/>
				<#if preRepairPlan.planAuditor?exists>
				 <#assign planAuditorId = "${preRepairPlan.planAuditor.id}" />
				</#if>
				<input type="hidden" name="planAuditor.id" value="${planAuditorId}" />
				<#--
				<#if planProcFlag?exists>
		          <#if (planProcFlag=='PROC')>
					<@ww.textfield label="'${action.getText('preRepairPlan.procAllFee')}'" name="'preRepairPlan.procAllFee'" value="'${preRepairPlan.procAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
		   		  <#else>
		   		  -->
		   		  	<@ww.textfield label="'${action.getText('preRepairPlan.planAllFee')}'" name="'preRepairPlan.planAllFee'" value="'${preRepairPlan.planAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
		   		  <#--
		   		  </#if>
		        </#if>
		        -->
		   </tr>
	       <#if planProcFlag?exists>
		     <#if (planProcFlag=='PROC')>
		        <tr>
		             <@pp.datePicker label="'${action.getText('preRepairProc.reportDate')}'" name="'preRepairPlan.reportDate'"
	     							 value="'${(preRepairPlan.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							 required="true" maxlength="10"/>
	                <#assign reportorName = ''/>
					<#if preRepairPlan.reportor?exists>
					  <#assign reportorName = "${preRepairPlan.reportor.name}" />
					<#elseif loginUser?exists>
					  <#assign reportorName = "${loginUser.name}" />
					</#if>
		        	<td align="right" valign="top">
	        	      <span class="required">*</span>
	        	      <label class="label">${action.getText('preRepairProc.reportor')}:</label>
	        	    </td>
	        	    <td>
	        		  <input type="text" name="reportor.name" 
	        			     class="underline"  value="${reportorName}"  maxlength="140" size="20" disabled="true"/>
	        	        <a name="reportorSeector" onClick="reportor_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		            </td>
		            <#assign reportorId = ''/>
				    <#if preRepairPlan.reportor?exists>
				      <#assign reportorId = "${preRepairPlan.reportor.id}" />
				    <#elseif loginUser?exists>
				      <#assign reportorId = "${loginUser.id}" />
				    <#else>
				    </#if>
				    <input type="hidden" name="reportor.id" value="${reportorId}" />
		        </tr>
		        <tr>
		        	<@ww.textfield label="'${action.getText('preRepairPlan.procAllFee')}'" name="'preRepairPlan.procAllFee'" value="'${preRepairPlan.procAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
		        </tr>
		     </#if>
		   </#if>
        </@inputTable>
        <@buttonBar>
        <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlans.html?planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if (preRepairPlan.id)?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_preRepairPlanListPdf('#{preRepairPlan.id}')"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_preRepairPlanListXls('#{preRepairPlan.id}')"/>
	      </#if>
	    </@buttonBar>
	</@ww.form>
	    <script language="JavaScript" type="text/JavaScript">
	       window.onload = function () {
	            <#if preRepairPlan.id?exists>
	              	document.all.frame.src = '${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanDetails.html?preRepairPlan.id=' + #{preRepairPlan.id} +'&planProcFlag=' + '${planProcFlag?if_exists}' + '&toolingDevFlag=' + '${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		            document.getElementById("preRepairPlanDetail").className = "selectedtab";
	            </#if>
	       		<#if preRepairPlan.department?exists>
	     			document.forms["preRepairPlan"].elements["department.id"].value=#{preRepairPlan.department.id?if_exists};
	     		<#elseif loginUser.department?exists>
	     		    document.forms["preRepairPlan"].elements["department.id"].value= #{loginUser.department.id};
	     		</#if>
	     		<#if (preRepairPlan.id)?exists>
	     		  <#if planProcFlag?exists>
			        <#if (planProcFlag=='PROC')>
			          <#if !(preRepairPlan.reportDate?exists)>
			            a = new Date();
					    var time=a.format("yyyy-MM-dd");
					    document.forms["preRepairPlan"].elements["preRepairPlan.reportDate"].value=time;
			          </#if>
			        </#if>
			      </#if>
		       	<#else>
		       	  <#if planProcFlag?exists>
			        <#if (planProcFlag=='PLAN')>
			       	  a = new Date();
					  var time=a.format("yyyy-MM-dd");
					  getDateForNextMonthFirstDay(document.forms[0],"preRepairPlan.beginDate");
					  document.forms["preRepairPlan"].elements["preRepairPlan.planCreatedTime"].value=time;
					</#if>
				  </#if> 
				</#if>
	       }
	      function open_preRepairPlanListPdf(id) {
	     	var preRepairPlanId=id;
	     	<#if (toolingDevFlag=='TOOLING')>
	     	<#if (planProcFlag=='PLAN')>
				var url='${req.contextPath}/reports/tooling/toolingpreRepairPlanList.pdf?preRepairPlanId='+preRepairPlanId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			<#else>
				var url='${req.contextPath}/reports/tooling/toolingpreRepairProcList.pdf?preRepairPlanId='+preRepairPlanId+'&flag=PROC'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			</#if>
			<#else>
			<#if (planProcFlag=='PLAN')>
				var url='${req.contextPath}/reports/preRepair/preRepairPlanList.pdf?preRepairPlanId='+preRepairPlanId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			<#else>
				var url='${req.contextPath}/reports/preRepair/preRepairProcList.pdf?preRepairPlanId='+preRepairPlanId+'&flag=PROC'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			</#if>
			</#if>
	  		window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		 }
		 function open_preRepairPlanListXls(id) {
	     	var preRepairPlanId=id;
	     	<#if (toolingDevFlag=='TOOLING')>
	     	<#if (planProcFlag=='PLAN')>
				var url='${req.contextPath}/reports/tooling/toolingpreRepairPlanList.xls?preRepairPlanId='+preRepairPlanId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			<#else>
				var url='${req.contextPath}/reports/tooling/toolingpreRepairProcList.xls?preRepairPlanId='+preRepairPlanId+'&flag=PROC'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			</#if>
			<#else>
			<#if (planProcFlag=='PLAN')>
				var url='${req.contextPath}/reports/preRepair/preRepairPlanList.xls?preRepairPlanId='+preRepairPlanId+'&flag=PLAN'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			<#else>
				var url='${req.contextPath}/reports/preRepair/preRepairProcList.xls?preRepairPlanId='+preRepairPlanId+'&flag=PROC'+'&toolingDevFlag=${toolingDevFlag?if_exists}';
			</#if>
			</#if>
	  		window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		 } 
	      /*
	       * 编制人选择
	      */
	      function planCreator_OpenDialog () {
	        var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, desigerSelectorHandler_Creator);
	      }
	      function desigerSelectorHandler_Creator(result) {
		    document.forms["preRepairPlan"].elements["planCreator.id"].value = result[0];
		    document.forms["preRepairPlan"].elements["planCreator.name"].value = result[1];
		  }
		  
		  /*
		   * 审核人的选择
		  */
		  function planAuditor_OpenDialog () {
	       		var url = "${req.contextPath}/popup/userSelector.html?multiple='F'";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_Auditor);
	       }
	      function desigerSelectorHandler_Auditor(result) {
		    document.forms["preRepairPlan"].elements["planAuditor.id"].value = result[0];
		    document.forms["preRepairPlan"].elements["planAuditor.name"].value = result[1];
		  }
		  /*
		   * 报告人选择
		  */
		  function reportor_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, reportorSelectorHandler);
		  }
		  function reportorSelectorHandler(result) {
		    if (null != result) {
		      document.forms["preRepairPlan"].elements["reportor.id"].value = result[0];
		      document.forms["preRepairPlan"].elements["reportor.name"].value = result[1];
		    }
		  }
		  //验证计划开始日期function
		  function validateTime_beginDate(){
		    if(document.forms["preRepairPlan"].elements["preRepairPlan.beginDate"].value ==""){
			  alert("${action.getText('select.preRepairPlan.beginDate')}");
			  return false;
			}
			var date=document.getElementById("preRepairPlan.beginDate").value;
			if(!isDate("preRepairPlan.beginDate")){
			  alert("${action.getText('select.right.preRepairPlan.beginDate')}");
			  return false;
			}
			/*if(!isDateLessEqualThenCurrent(date)){
			  alert("${action.getText('afresh.preRepairPlan.beginDate')}");
			  return false;
			}*/
			return true;
		  }
		  //验证编制日期function
		  function validateTime_planCreatedTime(){
		    if(document.forms["preRepairPlan"].elements["preRepairPlan.planCreatedTime"].value ==""){
			  alert("${action.getText('select.preRepairPlan.planCreatedTime')}");
			  return false;
			}
			var date=document.getElementById("preRepairPlan.planCreatedTime").value;
			if(!isDate("preRepairPlan.planCreatedTime")){
		      alert("${action.getText('select.right.preRepairPlan.planCreatedTime')}");
			  return false;
			}
			if(isDateLessThenCurrent(date)){
			  alert("${action.getText('afresh.preRepairPlan.planCreatedTime')}");
			  return false;
			}
			return true;
		  }	
		  //验证预防性维修计划名称function
		  function preRepairPlan_name(){
	        var name=document.forms["preRepairPlan"].elements["preRepairPlan.name"].value;
	        if ('' == name) {
	          alert("${action.getText('preRepairPlan.name.requiredstring')}");
			  return  false;
	        }
		    if(!(name=='')){
		      if(!isValidLength(document.forms[0], "preRepairPlan.name", null, 50)){
			    alert("${action.getText('preRepairPlan.name.length')}");
				return  false;
			  }
			}
			return true;
		  }
		  /*
	       * 验证部门是否为空
	      */
	      function validateDepartment() {
	        var dept = document.forms[0].elements["department.id"].value;
	        if (dept =='' || dept == '-1') {
	          alert("${action.getText('department.id.requried')}");
	          return false;
	        }
	        return true;
	      }
		  function validate(){
		    if ('PROC' == document.forms[0].elements["planProcFlag"].value) {          //如果是预防性维修实施
		      //验证报告日期
			  if ('' == document.forms[0].elements["preRepairPlan.reportDate"].value) {
			    alert("${action.getText('select.preRepairPlan.reportDate')}");
				return false;
			  } else {
			    if(!isDate("preRepairPlan.reportDate")){        //验证日期类型是否为yyyy-MM-dd
				  alert("${action.getText('select.right.preRepairPlan.reportDate')}");
				  return false;
				}
				if(isDateLessThenCurrent(document.forms[0].elements["preRepairPlan.reportDate"].value)){
			      alert("${action.getText('afresh.preRepairPlan.reportDate')}");
			      return false;
			    }
			  }
			  //验证报告人
			  if ('' == document.forms[0].elements["reportor.name"].value) {
			    alert("${action.getText('select.reportor.name')}");
				return false;
			  }
		    } else {                                                                   //如果是预防性维修计划
		      //验证预防性维修计划名称
		      if(!preRepairPlan_name()){       
		    	return false;
		      }
		      //验证部门
              if (!validateDepartment()) {
                return false;
              }
		      //验证计划开始时间
		      if (!validateTime_beginDate()){
		  		return false;
		  	  }
		  	  //验证编制日期
		  	  if (!validateTime_planCreatedTime()){
		  		return false;
		  	  }
			  //验证编制人
		      if (document.forms["preRepairPlan"].elements["planCreator.name"].value == '') {
		        alert("${action.getText('select.planCreator.name')}");
		        return false;
		      }
		    }
		    return true;
		  
		  }
		/*  
		  function preRepairPlan_OpenDialog(url) {
		    popupModalDialog(url, 800, 600, refresh_preRepairPlan_information);	 
		  }
		  function refresh_preRepairPlan_information (result) {
	        document.forms[0].elements["preRepairPlan.id"].value  = result[0];
	        document.forms[0].elements["preRepairPlan.planNo"].value  = result[1];
	        document.forms[0].elements["preRepairPlan.name"].value  = result[2];
	        document.forms[0].elements["department.id"].value  = result[3];
	        document.forms[0].elements["preRepairPlan.beginDate"].value  = result[4];
	        document.forms[0].elements["planCreator.name"].value  = result[5];
	        document.forms[0].elements["planAuditor.name"].value  = result[6];
	        document.forms[0].elements["preRepairPlan.planCreatedTime"].value  = result[7];
	      }
	      */
	 </script>
	 <#if preRepairPlan.id?exists>
	 <#assign iframeTitle =''/>
	 <#if planProcFlag?exists>
	   <#if planProcFlag=='PLAN'>
	     <#assign iframeTitle ="${action.getText('preRepairPlanDetail')}"/>
	   <#else>
	     <#assign iframeTitle ="${action.getText('preRepairProcDetail')}"/>
	   </#if>
	 </#if>
	  <ul id="beautytab">
	    <li><a id="preRepairPlanDetail" onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanDetails.html?preRepairPlan.id=#{preRepairPlan.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${iframeTitle}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>