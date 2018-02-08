<#include "../../includes/eam2008.ftl" />
<#assign washTitle = ''/>
<#assign require = ''/>
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign washTitle = "${action.getText('washPlan.edit')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign washTitle = "${action.getText('washProc.edit')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${washTitle}">
    <@ww.form namespace="'/runmaintenance/wash'" name="'wash'" action="'saveWash'" method="'post'" validate="true">
        <@ww.token name="saveWashToken"/>
        <#if wash.id?exists>
          <@ww.hidden name="'wash.id'" value="#{wash.id?if_exists}"/>
        </#if>
        <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('wash.planNo')}'" name="'wash.planNo'" value="'${wash.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('wash.palnName')}'" name="'wash.name'" value="'${wash.name?if_exists}'" cssClass="'underline'" required="${require}" disabled="${disabled}" maxlength="50"/>
        	</tr>
        	<tr>
        		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name" 
		                list="departments" emptyOption="true" disabled="${disabled}" required="${require}">
		    	</@ww.select>
		    	<#if planProcFlag?exists>
		    	  <#if planProcFlag == 'PLAN'>
	        		<@pp.datePicker label="'${action.getText('wash.planBeginDate')}'" name="'wash.planBeginDate'"
		     						value="'${(wash.planBeginDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
		     						required="true" maxlength="10"/>
		     	  <#else>
		     	    <@ww.textfield label="'${action.getText('wash.planBeginDate')}'" name="'wash.planBeginDate'" value="'${(wash.planBeginDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
		     	  </#if>
		     	</#if>

        	</tr>
	        <tr>
	        	 <#assign planCreatorName = ''/>
				 <#if wash.planCreator?exists>
				   <#assign planCreatorName = "${wash.planCreator.name}" />
				 <#elseif loginUser?exists>
				   <#assign planCreatorName = "${loginUser.name}" />
				 </#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required">*</span>
	        	  </#if>
	        	</#if>
	        	<label class="label">${action.getText('wash.planCreator')}:</label></td>
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
				<#if wash.planCreator?exists>
				 <#assign planCreatorId = "${wash.planCreator.id}" />
				<#elseif loginUser?exists>
				 <#assign planCreatorId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="planCreator.id" value="${planCreatorId}" />
				<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
        		    <@pp.datePicker label="'${action.getText('wash.planCreateDate')}'" name="'wash.planCreatedTime'"
	     							value="'${(wash.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							required="true" maxlength="10"/>
	     		  <#else>
	     		    <@ww.textfield label="'${action.getText('wash.planCreateDate')}'" name="'wash.planCreatedTime'" value="'${(wash.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
                  </#if>
                </#if>
            </tr>
            <tr>
            	<#assign planAuditorName = ''/>
					<#if wash.planAuditor?exists>
					 <#assign planAuditorName = "${wash.planAuditor.name}" />
					<#elseif loginUser?exists>
					  <#assign planAuditorName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required">*</span>
	        	  </#if>
	        	</#if>
				<label class="label">${action.getText('wash.planAuditor')}:</label></td>
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
				<#if wash.planAuditor?exists>
				 <#assign planAuditorId = "${wash.planAuditor.id}" />
				<#elseif loginUser?exists>
				 <#assign planAuditorId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="planAuditor.id" value="${planAuditorId}" />
		   </tr>
	       <#if planProcFlag?exists>
		     <#if (planProcFlag=='PROC')>
		        <tr>
	                <#assign reportorName = ''/>
					<#if wash.reportor?exists>
					  <#assign reportorName = "${wash.reportor.name}" />
					<#elseif loginUser?exists>
					  <#assign reportorName = "${loginUser.name}" />
					</#if>
		        	<td align="right" valign="top">
	        	      <span class="required">*</span>
	        	      <label class="label">${action.getText('wash.reportor')}:</label>
	        	    </td>
	        	    <td>
	        		  <input type="text" name="reportor.name" 
	        			     class="underline"  value="${reportorName}"  maxlength="140" size="20" disabled="true"/>
	        	        <a name="reportorSeector" onClick="reportor_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		            </td>
		            <#assign reportorId = ''/>
				    <#if wash.reportor?exists>
				      <#assign reportorId = "${wash.reportor.id}" />
				    <#elseif loginUser?exists>
				      <#assign reportorId = "${loginUser.id}" />
				    <#else>
				    </#if>
				    <input type="hidden" name="reportor.id" value="${reportorId}" />
				    <@pp.datePicker label="'${action.getText('wash.reportDate')}'" name="'wash.reportDate'"
	     							 value="'${(wash.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							 required="true" maxlength="10"/>
		        </tr>
		      
		     </#if>
		   </#if>
        </@inputTable>
        <@buttonBar>
        
          <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	     </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/wash/listWashs.html?readOnly=${req.getParameter('readOnly')?if_exists}&planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>
	    
	      <#if wash.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_washDetailPdf('#{wash.id}')"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_washDetailXls('#{wash.id}')"/>
	     </#if>
	    </@buttonBar>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript">
	  window.onload = function () {
	    <#if wash.id?exists>
	      document.all.frame.src = '${req.contextPath}/runmaintenance/wash/listWashDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&wash.id=' + '#{wash.id}' + '&planProcFlag=' + '${planProcFlag?if_exists}';
		  getObjByNameRe("washDetail").className = "selectedtab";
	    </#if>
	    <#if wash.department?exists>
	      document.forms["wash"].elements["department.id"].value=#{wash.department.id?if_exists};
	    <#elseif loginUser.department?exists>
	      document.forms["wash"].elements["department.id"].value=#{loginUser.department.id};
	    </#if>
 		<#if (wash.id)?exists>
 		  <#if planProcFlag?exists>
	        <#if (planProcFlag=='PROC')>
	          a = new Date();
			  var time=a.format("yyyy-MM-dd");
			  document.forms["wash"].elements["wash.reportDate"].value=time;
	        </#if>
	      </#if>
       	<#else>
       	  <#if planProcFlag?exists>
	        <#if (planProcFlag=='PLAN')>
	          a = new Date();
			  var time=a.format("yyyy-MM-dd");
			  document.forms["wash"].elements["wash.planCreatedTime"].value=time;
			  getDateForNextMonthFirstDay(document.forms[0], "wash.planBeginDate");    //置计划开始日期初始值为下月1号
			</#if>
		  </#if> 
		</#if>
	  }
	  function open_washDetailXls(id){
       var washid=id;
       <#if (planProcFlag=='PLAN')>
	        var url='${req.contextPath}/reports/tooling/washDetailReportList.xls?washid='+washid+'&flag=PLAN';    
	   <#else>
	   		var url='${req.contextPath}/reports/tooling/washDetailProcReportList.xls?washid='+washid+'&flag=PROC';   
	   </#if>  
       url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
       }
    function open_washDetailPdf(id){
       var washid=id;
       <#if (planProcFlag=='PLAN')>
	        var url='${req.contextPath}/reports/tooling/washDetailReportList.pdf?washid='+washid+'&flag=PLAN';    
	   <#else>
	   		var url='${req.contextPath}/reports/tooling/washDetailProcReportList.pdf?washid='+washid+'&flag=PROC';   
	   </#if>     
       url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
    }
	  /*
	   * 选择编制人
	  */
      function planCreator_OpenDialog () {
   		var url = "${req.contextPath}/popup/userSelector.html";
    	popupModalDialog(url, 800, 600, desigerSelectorHandler_Creator);
      }
      function desigerSelectorHandler_Creator(result) {
    	document.forms["wash"].elements["planCreator.id"].value = result[0];
    	document.forms["wash"].elements["planCreator.name"].value = result[1];
      }
	  /*
	   * 选择审核人 
	  */  
	  function planAuditor_OpenDialog () {
       		var url = "${req.contextPath}/popup/userSelector.html?multiple='F'";
	    	popupModalDialog(url, 800, 600, desigerSelectorHandler_Auditor);
       }
       function desigerSelectorHandler_Auditor(result) {
	    	document.forms["wash"].elements["planAuditor.id"].value = result[0];
	    	document.forms["wash"].elements["planAuditor.name"].value = result[1];
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
	      document.forms["wash"].elements["reportor.id"].value = result[0];
	      document.forms["wash"].elements["reportor.name"].value = result[1];
	    }
	  }
	  function validateTime_planbeginDate(){
		if(document.forms["wash"].elements["wash.planBeginDate"].value ==""){
			alert("${action.getText('select.wash.planBeginDate')}");
			return false;
		}
		if(!isDate("wash.planBeginDate")){
			alert("${action.getText('select.right.wash.planBeginDate')}");
			return false;
		}
		return true;
	  }
	  function validateTime_planCreatedTime(){
		if(document.forms["wash"].elements["wash.planCreatedTime"].value ==''){
			//alert("${action.getText('select.wash.planCreateDate')}");
			alert("${action.getText('planCreateDate.not.null')}");
			return false;
		}
		var date=getObjByNameRe("wash.planCreatedTime").value;
		if(!isDate("wash.planCreatedTime")){
			alert("${action.getText('select.right.wash.planCreateDate')}");
			return false;
		}
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.wash.planCreateDate')}");
			return false;
		}
		return true;
      }	
	  function validate(){
	    /*
	     * 验证清洗计划名称
	    */
	    if (document.forms["wash"].elements["wash.name"].value == '') {
	      alert("${action.getText('select.wash.planName')}");
	      return false;
	    } else if (!isValidLength(document.forms[0],"wash.name",0,50)){
	      alert("${action.getText('wash.name.maxLength')}");
	      return false;
	    }
	    /*
	     * 验证部门
	    */
	  	if (document.forms["wash"].elements["department.id"].value == '') {
	      alert("${action.getText('select.department.name')}");
	      return false;
	    }
	    /*
	     * 验证计划开始日期
	    */
	    if (!validateTime_planbeginDate()){
	  	  return false;
	  	}
	  	/*
	  	 * 验证编制日期
	  	*/
	  	if (!validateTime_planCreatedTime()){
	  	  return false;
	  	}
	  	/*
	  	 * 验证编制人
	  	*/
        if (document.forms["wash"].elements["planCreator.name"].value == '') {
          alert("${action.getText('select.planCreator.name')}");
          return false;
        }
        /*
         * 验证审核人
        */
	    if (document.forms["wash"].elements["planAuditor.name"].value == '') {
	      alert("${action.getText('select.planAuditor.name')}");
	      return false;
	    }
	    /*
         * 日期格式验证
        */
        <#if planProcFlag?exists>
		     <#if (planProcFlag=='PROC')>
	        if(!isDate("wash.reportDate")){
		       alert('${action.getText('wash.reportDate')}'+'${action.getText('dateFormate.error')}');
		       return false; 
	        }
	        </#if> 
		</#if>
	    return true;
	  }
	 </script>
	 <#if wash.id?exists>
	 <#assign iframeTitle =''/>
	 <#if planProcFlag?exists>
	   <#if planProcFlag=='PLAN'>
	     <#assign iframeTitle ="${action.getText('washPlan.detail')}"/>
	   <#else>
	     <#assign iframeTitle ="${action.getText('washProc.detail')}"/>
	   </#if>
	 </#if>
	  <ul id="beautytab">
	    <li><a id="washDetail" onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/wash/listWashDetails.html?wash.id=#{wash.id}&planProcFlag=${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${iframeTitle}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>