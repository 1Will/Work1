<#include "../../includes/eam2008.ftl" />
<#assign repairTitle = ''/>
<#assign require = ''/>
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign repairTitle = "${action.getText('repairPlanEdit.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign repairTitle = "${action.getText('repairProcEdit.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${repairTitle}">
    <@ww.form namespace="'/year/repair'" name="'repairPlanOrProc'" action="'saveYearRepairPlanOrProc'" method="'post'" validate="true">
        <@ww.token name="saveYearRepairPlanOrProcToken"/>
         <@ww.hidden name="'repairPlanOrProc.id'" value="${repairPlanOrProc.id?if_exists}"/>
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
        		<@ww.textfield label="'${action.getText('repairPlanOrProc.repairPlanNo')}'" name="'repairPlanOrProc.planNo'" value="'${repairPlanOrProc.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('repairPlanOrProc.repairPlanName')}'" name="'repairPlanOrProc.name'" value="'${repairPlanOrProc.name?if_exists}'" cssClass="'underline'" required="${require}" disabled="${disabled}"/>
        	</tr>
        	<tr>
        		<@ww.select label="'${action.getText('repairPlanOrProc.department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name" 
		                list="departments" emptyOption="true" disabled="${disabled}" required="${require}">
		    	</@ww.select>
		    	<#if planProcFlag?exists>
	              <#if (planProcFlag=='PLAN')>
		    	    <@pp.datePicker label="'${action.getText('repairPlanOrProc.year')}'" name="'repairPlanOrProc.year'"
	     							value="'${(repairPlanOrProc.year)?if_exists}'" cssClass="'underline'" size="15" 
	     							dateFormat="'%Y'" required="${require}" maxlength="4"/>
	              <#else>
	                <@ww.textfield label="'${action.getText('repairPlanOrProc.year')}'" name="'repairPlanOrProc.year'" value="'${(repairPlanOrProc.year)?if_exists}'" cssClass="'underline'" disabled="true"/>
	              </#if>
	            </#if>
		    </tr>
		    <tr>
		    	<#if planProcFlag?exists>
	              <#if (planProcFlag=='PLAN')>
        		     <@pp.datePicker label="'${action.getText('repairPlanOrProc.planCreatedTime')}'" name="'repairPlanOrProc.planCreatedTime'"
	     							  value="'${(repairPlanOrProc.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  required="${require}" maxlength="10"/>
	 	  	  	  <#else>
	 	  	  	    <@ww.textfield label="'${action.getText('repairPlanOrProc.planCreatedTime')}'" name="'repairPlanOrProc.planCreatedTime'" value="'${(repairPlanOrProc.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" disabled="true"/>
	 	  	  	  </#if>
	 	  	  	</#if>
	         	<#assign planCreatorName = ''/>
				<#if repairPlanOrProc.planCreator?exists>
				  <#assign planCreatorName = "${repairPlanOrProc.planCreator.name}" />
				<#elseif loginUser?exists>
				  <#assign planCreatorName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required">*</span>
	        	  </#if>
	        	</#if>
	        	<label class="label">${action.getText('repairPlanOrProc.planCreator')}:</label></td>
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
				<#if repairPlanOrProc.planCreator?exists>
				  <#assign planCreatorId = "#{repairPlanOrProc.planCreator.id}" />
				<#elseif loginUser?exists>
				  <#assign planCreatorId = "#{loginUser.id}" /> 
				</#if>
				<input type="hidden" name="planCreator.id" value="${planCreatorId}" />
				
            </tr>
            <tr>
            	<#assign planAuditorName = ''/>
					<#if repairPlanOrProc.planAuditor?exists>
					 <#assign planAuditorName = "${repairPlanOrProc.planAuditor.name}" />
					</#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required"></span>
	        	  </#if>
	        	</#if>
				<label class="label">${action.getText('repairPlanOrProc.planAuditor')}:</label></td>
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
				<#if repairPlanOrProc.planAuditor?exists>
				 <#assign planAuditorId = "${repairPlanOrProc.planAuditor.id}" />
				</#if>
				<input type="hidden" name="planAuditor.id" value="${planAuditorId}" />
		   		  	<@ww.textfield label="'${action.getText('repairPlanOrProc.planAllFee')}'" name="'repairPlanOrProc.planAllFee'" value="'${repairPlanOrProc.planAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
		   </tr>
		   <#if planProcFlag?exists>
		     <#if (planProcFlag=='PROC')>
		        <tr>
		             <@pp.datePicker label="'${action.getText('repairPlanOrProc.reportDate')}'" name="'repairPlanOrProc.reportDate'"
	     							 value="'${(repairPlanOrProc.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							 required="true" maxlength="10"/>
	                <#assign reportorName = ''/>
					<#if repairPlanOrProc.reportor?exists>
					  <#assign reportorName = "${repairPlanOrProc.reportor.name}" />
					<#elseif loginUser?exists>
					  <#assign reportorName = "${loginUser.name}" />
					</#if>
		        	<td align="right" valign="top">
	        	      <span class="required">*</span>
	        	      <label class="label">${action.getText('repairPlanOrProc.reportor')}:</label>
	        	    </td>
	        	    <td>
	        		  <input type="text" name="reportor.name" 
	        			     class="underline"  value="${reportorName}"  maxlength="140" size="20" disabled="true"/>
	        	        <a name="reportorSeector" onClick="reportor_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		            </td>
		            <#assign reportorId = ''/>
				    <#if repairPlanOrProc.reportor?exists>
				      <#assign reportorId = "${repairPlanOrProc.reportor.id}" />
				    <#elseif loginUser?exists>
				      <#assign reportorId = "${loginUser.id}" />
				    <#else>
				    </#if>
				    <input type="hidden" name="reportor.id" value="${reportorId}" />
		        </tr>
		        <tr>
		        	<@ww.textfield label="'${action.getText('repairPlanOrProc.procAllFee')}'" name="'repairPlanOrProc.procAllFee'" value="'${repairPlanOrProc.procAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
		        </tr>
		     </#if>
		   </#if>
        </@inputTable>
        <@buttonBar>
          <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/year/repair/listYearRepairPlanOrProcs.html?planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if repairPlanOrProc.id?exists>
	      <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_repairPlanOrProcListPdf('#{repairPlanOrProc.id}')"/>
		  <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_repairPlanOrProcListXls('#{repairPlanOrProc.id}')"/>
	      </#if>
	    </@buttonBar>
	    
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript">
	  window.onload = function () {
	    <#if repairPlanOrProc.id?exists>
	      document.all.frame.src = '${req.contextPath}/year/repair/listYearRepairPlanOrProcDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProc.id=' + #{repairPlanOrProc.id} +'&planProcFlag=' + '${planProcFlag?if_exists}' + '&toolingDevFlag=' + '${toolingDevFlag?if_exists}';
		  getObjByNameRe("repairPlanOrProcDetail").className = "selectedtab";
	    </#if>
	    <#if repairPlanOrProc.department?exists>
	      document.forms["repairPlanOrProc"].elements["department.id"].value=#{repairPlanOrProc.department.id?if_exists};
	    <#elseif loginUser.department?exists>
	      document.forms["repairPlanOrProc"].elements["department.id"].value= #{loginUser.department.id};
	    </#if>
	    <#if (repairPlanOrProc.id)?exists>
	      <#if planProcFlag?exists>
		    <#if (planProcFlag=='PROC')>
		      <#if !(repairPlanOrProc.reportDate?exists)>
		        a = new Date();
			    var time=a.format("yyyy-MM-dd");
			    document.forms["repairPlanOrProc"].elements["repairPlanOrProc.reportDate"].value=time;    //置报告日期为当前日期
		      </#if>
			</#if>
	      </#if>
		<#else>
		   <#if planProcFlag?exists>
		     <#if (planProcFlag=='PLAN')>
			   a = new Date();
			   var time=a.format("yyyy-MM-dd");
			   document.forms["repairPlanOrProc"].elements["repairPlanOrProc.planCreatedTime"].value=time;    //置编置日期为当前日期
			   getNextYear(document.forms["repairPlanOrProc"], "repairPlanOrProc.year");               //置年度默认值为下年
			 </#if>
		   </#if> 
		</#if>
	  }
	  /*
	   * 编制人选择
	  */
	  function planCreator_OpenDialog () {
	    var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, creatorSelectorHandler);
	  }
	  function creatorSelectorHandler(result) {
	    if (null != result) {
	      document.forms[0].elements["planCreator.id"].value = result[0];
		  document.forms[0].elements["planCreator.name"].value = result[1];
	    }
	  }
	  /*
	   * 审核人选择
	  */
	  function planAuditor_OpenDialog() {
	    var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, auditorSelectorHandler);
	  }
	  function auditorSelectorHandler(result) {
	    if (null != result) {
	      document.forms[0].elements["planAuditor.id"].value = result[0];
		  document.forms[0].elements["planAuditor.name"].value = result[1];
	    }
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
	      document.forms[0].elements["reportor.id"].value = result[0];
	      document.forms[0].elements["reportor.name"].value = result[1];
	    }
	  }
	  /*
	   * 点击保存按钮，触发的验证
	  */
	  function validate() {
	    if ('PROC' == document.forms[0].elements["planProcFlag"].value) {          //如果是预防性维修实施
	      //验证报告日期
		  if ('' == document.forms[0].elements["repairPlanOrProc.reportDate"].value) {
		    alert("${action.getText('select.repairPlanOrProc.reportDate')}");
			return false;
		  } else {
		    if(!isDate("repairPlanOrProc.reportDate")){        //验证日期类型是否为yyyy-MM-dd
			  alert("${action.getText('select.right.repairPlanOrProc.reportDate')}");
			  return false;
			}
			if(isDateLessThenCurrent(document.forms[0].elements["repairPlanOrProc.reportDate"].value)){
			  alert("${action.getText('afresh.repairPlanOrProc.reportDate')}");
			  return false;
			}
		  }
		  //验证报告人
		  if ('' == document.forms[0].elements["reportor.id"].value) {
		    alert("${action.getText('select.reportor.name')}");
			return false;
		  }
		} 
		if (!validateRepairPlanOrProcName()) {          //验证大项修计划名称
		  return false;
		}
		if(!validateDepartment()) {          //验证部门
		  return false;
		}
		if (!validateYear()) {      //验证年度
		  return false;
		}
		if (!validateTime_planCreatedTime()) {    //验证编制日期
		  return false;
		}
		if ('' == document.forms["repairPlanOrProc"].elements["planCreator.id"].value) {    //验证编制人
		  alert("${action.getText('planCreator.id.required')}");
		  return false;
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
  	  //验证编制日期function
	  function validateTime_planCreatedTime(){
	    //验证编制日期是否为空
	    if(document.forms["repairPlanOrProc"].elements["repairPlanOrProc.planCreatedTime"].value ==""){
		  alert("${action.getText('select.repairPlanOrProc.planCreatedTime')}");
		  return false;
		}
		//验证编制日期是否为日期型
		var date=getObjByNameRe("repairPlanOrProc.planCreatedTime").value;
		if(!isDate("repairPlanOrProc.planCreatedTime")){
	      alert("${action.getText('select.right.repairPlanOrProc.planCreatedTime')}");
		  return false;
		}
		<#if repairPlanOrProc.id?exists>
		<#else>
		  if(isDateLessThenCurrent(date)){
		    alert("${action.getText('afresh.repairPlanOrProc.planCreatedTime')}");
		    return false;
		  }
		</#if>
		return true;
	  }
	  //验证年度
	  function validateYear() {
	    var year = document.forms["repairPlanOrProc"].elements["repairPlanOrProc.year"].value;
	    if ('' == year) {              //验证年度是否为空
	      alert("${action.getText('repairPlanOrProc.year.requrieddate')}");
	      return false;
	    }
	    if (isNaN(parseInt(year))) {   //验证年度格式
	      alert("${action.getText('select.right.repairPlanOrProc.year')}");
	      return false;
	    }
	    return true;
	  }
	  //验证大项修计划名称
	  function validateRepairPlanOrProcName() {
	    if ('' == document.forms["repairPlanOrProc"].elements["repairPlanOrProc.name"].value) {
	      alert("${action.getText('repairPlanOrProc.name.requiredstring')}");
	      return false;
	    } else {
	      if(!isValidLength(document.forms[0], "repairPlanOrProc.name", null, 50)){
		    alert("${action.getText('repairPlanOrProc.name.length')}");
		    return  false;
		  }
	    }
	    return true;
	  }
	  function open_repairPlanOrProcListPdf(id) {
	     	var repairPlanOrProcId=id;
	     	<#if (planProcFlag=='PLAN')>
				var url='${req.contextPath}/reports/device/repairPlanDetaillist.pdf?repairPlanOrProcId='+repairPlanOrProcId+'&flag=PLAN';
			<#else>
				var url='${req.contextPath}/reports/device/repairProcDetaillist.pdf?repairPlanOrProcId='+repairPlanOrProcId+'&flag=PROC';
			</#if>
	  		window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	  		}
	  function open_repairPlanOrProcListXls(id) {
		    var repairPlanOrProcId=id;
	     	<#if (planProcFlag=='PLAN')>
				var url='${req.contextPath}/reports/device/repairPlanDetaillist.xls?repairPlanOrProcId='+repairPlanOrProcId+'&flag=PLAN';
			<#else>
				var url='${req.contextPath}/reports/device/repairProcDetaillist.xls?repairPlanOrProcId='+repairPlanOrProcId+'&flag=PROC';
			</#if>
	  		window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		    }
    </script>
    <#if repairPlanOrProc.id?exists>
	 <#assign iframeTitle =''/>
	 <#if planProcFlag?exists>
	   <#if planProcFlag=='PLAN'>
	     <#assign iframeTitle ="${action.getText('yearRepairPlanDetail')}"/>
	   <#else>
	     <#assign iframeTitle ="${action.getText('yearRepairProcDetail')}"/>
	   </#if>
	 </#if>
	  <ul id="beautytab">
	    <li><a id="repairPlanOrProcDetail" onclick="activeTab(this);" href="${req.contextPath}/year/repair/listYearRepairPlanOrProcDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProc.id=#{repairPlanOrProc.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${iframeTitle}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>