<#--$Id: lubricationPlanList.ftl 10243 2008-01-08 03:18:30Z wzou $ -->
<#include "../../../includes/eam2008.ftl" />
<#assign lubricationTitle = ''/>
<#assign require = ''/>
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign lubricationTitle = "${action.getText('lubricationPlan.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign lubricationTitle = "${action.getText('lubricationProc.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${lubricationTitle}">
  <@ww.form name="'lubrication'" action="'saveLubricationPlan'" method="'post'" validate="true">
    <@ww.token name="saveLubricationPlanToken"/>
    <@inputTable>
      <#if lubrication.id?exists>
        <@ww.hidden name="'lubrication.id'" value="#{lubrication.id}"/>
      </#if>
      <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	  <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	  <tr>
		  <@ww.textfield label="'${action.getText('lubricationPlan.planNo')}'" name="'lubrication.planNo'" value="'${lubrication.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
 	  	  <@ww.textfield label="'${action.getText('lubricationPlan.name')}'" name="'lubrication.name'" value="'${lubrication.name?if_exists}'" cssClass="'underline'" required="${require}" disabled="${disabled}" maxlength="50"/>
	  </tr>
	  <tr>
		  <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			      listKey="id" listValue="name" 
                      list="departments" emptyOption="true" disabled="${disabled}" required="${require}">
    	  </@ww.select>
    	  <@ww.textfield label="'${action.getText('month')}'" name="'lubrication.month'" 
	    				value="'${(lubrication.month)?if_exists}'" cssClass="'underline'" size="15"
	    				disabled="true"/>
    	  <#--<#if planProcFlag?exists>
		    <#if planProcFlag == 'PLAN'>
    	      <@pp.datePicker label="'${action.getText('planBeginTime')}'" name="'lubrication.planBeginTime'"
	     				      value="'${(lubrication.planBeginTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				      required="true" maxlength="10"/>
	        <#else>
	          <@ww.textfield label="'${action.getText('planBeginTime')}'" name="'lubrication.planBeginTime'" value="'${(lubrication.planBeginTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
	        </#if>
	      </#if>-->
	      
      </tr>
      <tr>
         <#if planProcFlag?exists>
		   <#if planProcFlag == 'PLAN'>
             <@pp.datePicker label="'${action.getText('planCreatedTime')}'" name="'lubrication.planCreatedTime'"
	     				     value="'${(lubrication.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				     required="true" maxlength="10"/>
           <#else>
             <@ww.textfield label="'${action.getText('planCreatedTime')}'" name="'lubrication.planCreatedTime'" value="'${(lubrication.planCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
           </#if>
         </#if>
    	  <#assign planCreatorName = ''/>
		  <#if lubrication.planCreator?exists>
		    <#assign planCreatorName = "${lubrication.planCreator.name}" />
		  <#elseif loginUser?exists>
		    <#assign planCreatorName = "${loginUser.name}" />
		  </#if>
    	  <td align="right" valign="top">
    	    <#if planProcFlag?exists>
			  <#if (planProcFlag=='PLAN')>
    	        <span class="required">*</span>
    	      </#if>
    	    </#if>
    	     
    	     <label class="label">${action.getText('planCreator')}:</label>
    	  </td>
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
		 <#if lubrication.planCreator?exists>
		   <#assign planCreatorId = "${lubrication.planCreator.id}" />
		 <#elseif loginUser?exists>
		   <#assign planCreatorId = "${loginUser.id}" />
		 </#if>
		 <input type="hidden" name="planCreator.id" value="${planCreatorId}" />
      </tr>
      <#if planProcFlag?exists>
	    <#if (planProcFlag=='PROC')>
		  <tr>
	        <#assign reportorName = ''/>
		    <#if lubrication.reportor?exists>
			  <#assign reportorName = "${lubrication.reportor.name}" />
			<#elseif loginUser?exists>
		      <#assign reportorName = "${loginUser.name}" />
			</#if>
		    <td align="right" valign="top">
	           <span class="required">*</span>
	           <label class="label">${action.getText('reporter')}:</label>
	        </td>
	        <td>
	          <input type="text" name="reportor.name" 
	        	     class="underline"  value="${reportorName}"  maxlength="140" size="20" disabled="true"/>
	          <a name="reportorSeector" onClick="reportor_OpenDialog();">
    		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
    	      </a>
		    </td>
            <#assign reportorId = ''/>
		    <#if lubrication.reportor?exists>
		      <#assign reportorId = "${lubrication.reportor.id}" />
		    <#elseif loginUser?exists>
		      <#assign reportorId = "${loginUser.id}" />
		    <#else>
		    </#if>
		    <input type="hidden" name="reportor.id" value="${reportorId}" />
		    <@pp.datePicker label="'${action.getText('reportCreatedTime')}'" name="'lubrication.reportDate'"
	     					value="'${(lubrication.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				    required="true" maxlength="10"/>
		  </tr>
		</#if>
	  </#if>
    </@inputTable>
	    <@buttonBar>
	     <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	     </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/lubrication/listLubrications.html?planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if planProcFlag?exists>
			<#if (planProcFlag=='PLAN')>
				<@vsubmit name="'search'" value="'${action.getText('lubricationPlanSearch')}'" onclick="'return lubricationDetailSearch();'"/>
			<#else>
				<@vsubmit name="'search'" value="'${action.getText('lubricationPrcoSearch')}'" onclick="'return lubricationDetailSearch();'"/>
			</#if>
		  </#if>
	      <#if lubrication.id?exists>
	      	   <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_listlubricationPlanDetailPdf('#{lubrication.id}')"/>
	      	   <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_listlubricationPlanDetailXls('#{lubrication.id}')"/>
	      </#if>
	    </@buttonBar>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript">
    window.onload = function() {
      <#if lubrication.id?exists>
	    document.all.frame.src = '${req.contextPath}/runmaintenance/lubrication/listLubricationPlanDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&lubrication.id=#{lubrication.id}' + '&planProcFlag=' + '${planProcFlag?if_exists}';
		getObjByNameRe("lubricationDetail").className = "selectedtab";
	  </#if>
	  <#if lubrication.department?exists>
	    document.forms["lubrication"].elements["department.id"].value=#{lubrication.department.id?if_exists};
	  <#elseif loginUser.department?exists>
	    document.forms["lubrication"].elements["department.id"].value=#{loginUser.department.id};
	  </#if>
      <#if lubrication.id?exists>
        <#if planProcFlag?exists>
	      <#if (planProcFlag=='PROC')>
	        a = new Date();
			var time=a.format("yyyy-MM-dd");
			document.forms["lubrication"].elements["lubrication.reportDate"].value=time;
	      </#if>
	    </#if>
      <#else>
        a = new Date();
	    var time=a.format("yyyy-MM-dd");
	    document.forms["lubrication"].elements["lubrication.planCreatedTime"].value=time;
	    getDateForNextMonthFirstDay(document.forms[0], "lubrication.planBeginTime");    //置计划开始日期初始值为下月1号
      </#if>
    }
    function lubricationDetailSearch(){
    	var url = '${req.contextPath}/runmaintenance/lubrication/listLubricationDetailSearch.html?planProcFlag=${planProcFlag}&lubrication.id=#{lubrication.id}';
    	popupModalDialog(url, 900,700);
    	//加载明细
    	document.all.frame.src = '${req.contextPath}/runmaintenance/lubrication/listLubricationPlanDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&lubrication.id=#{lubrication.id}' + '&planProcFlag=' + '${planProcFlag?if_exists}';
    	getObjByNameRe("lubricationDetail").className = "selectedtab";
    	return false;
    }
    /*
	 * 选择编制人
	*/
     function planCreator_OpenDialog () {
   	   var url = "${req.contextPath}/popup/userSelector.html";
	   popupModalDialog(url, 800, 600, desigerSelectorHandler_Creator);
     }
     function desigerSelectorHandler_Creator(result) {
	  document.forms["lubrication"].elements["planCreator.id"].value = result[0];
	  document.forms["lubrication"].elements["planCreator.name"].value = result[1];
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
	     document.forms["lubrication"].elements["reportor.id"].value = result[0];
	     document.forms["lubrication"].elements["reportor.name"].value = result[1];
	   }
	 }
     function validate() {
       if ('' == document.forms["lubrication"].elements["lubrication.name"].value) {
         alert("${action.getText('select.lubrication.name')}");
         return false;
       } else if (!isValidLength(document.forms[0],"lubrication.name",0,50)){
	      alert("${action.getText('lubrication.name.maxLength')}");
	      return false;
	   }
	   /*
	    * 验证部门
	   */
	   if (document.forms["lubrication"].elements["department.id"].value == '') {
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
	  	* 验证编制人
	   */
        if (document.forms["lubrication"].elements["planCreator.id"].value == '') {
          alert("${action.getText('select.planCreator.name')}");
          return false;
        }
        /*
	  	 * 验证编制日期
	  	*/
	  	if (!validateTime_planCreatedTime()){
	  	  return false;
	  	}
	    <#if planProcFlag?exists>
	      <#if planProcFlag=='PROC'>
		   /*
		  	* 验证报告人
		   */
		    if (document.forms["lubrication"].elements["reportor.id"].value == '') {
		      alert("${action.getText('select.reportor.name')}");
		      return false;
		    }
		    /*
		     * 验证报告日期
		    */
		    if (!validateTime_reportDate()) {
		      return false;
		    }
		  </#if>
		</#if>
		return true;
     }
	 function validateTime_planCreatedTime(){
	   if(document.forms["lubrication"].elements["lubrication.planCreatedTime"].value ==""){
	     alert("${action.getText('select.lubrication.planCreatedTime')}");
		 return false;
	   }
	   var date=getObjByNameRe("lubrication.planCreatedTime").value;
	   if(!isDate("lubrication.planCreatedTime")){
	     alert("${action.getText('select.right.lubrication.planCreatedTime')}");
		 return false;
	   }
	   if(isDateLessThenCurrent(date)){
	     alert("${action.getText('afresh.lubrication.planCreatedTime')}");
		 return false;
	   }
	   return true;
      }	
      function validateTime_planbeginDate(){
		if(document.forms["lubrication"].elements["lubrication.planBeginTime"].value ==""){
		  alert("${action.getText('select.lubrication.planBeginTime')}");
		  return false;
		}
		if(!isDate("lubrication.planBeginTime")){
		  alert("${action.getText('select.right.lubrication.planBeginTime')}");
		  return false;
		}
		return true;
	  }
	  function validateTime_reportDate(){
		if(document.forms["lubrication"].elements["lubrication.reportDate"].value ==""){
		  alert("${action.getText('select.lubrication.reportDate')}");
		  return false;
		}
		if(!isDate("lubrication.reportDate")){
		  alert("${action.getText('select.right.lubrication.reportDate')}");
		  return false;
		}
		return true;
	  }
	  
	  function open_listlubricationPlanDetailXls(id) {
				var lubricationId=id;
				<#if (planProcFlag=='PLAN')>
			          var url='${req.contextPath}/reports/lubrication/listlubricationPlanDetail.xls?lubricationId='+lubricationId;
		        <#else>
			          var url='${req.contextPath}/reports/lubrication/listlubricationProcDetail.xls?lubricationId='+lubricationId;
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}      	
	  function open_listlubricationPlanDetailPdf(id) {
				var lubricationId=id;
			    <#if (planProcFlag=='PLAN')>
			          var url='${req.contextPath}/reports/lubrication/listlubricationPlanDetail.pdf?lubricationId='+lubricationId;
		        <#else>
			          var url='${req.contextPath}/reports/lubrication/listlubricationProcDetail.pdf?lubricationId='+lubricationId;
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	} 
	      	   	
  </script>
  <#if lubrication.id?exists>
  	 <#assign iframeTitle =''/>
	 <#if planProcFlag?exists>
	   <#if planProcFlag=='PLAN'>
	     <#assign iframeTitle ="${action.getText('lubricationPlanDetails')}"/>
	   <#else>
	     <#assign iframeTitle ="${action.getText('lubricationProcDetails')}"/>
	   </#if>
	 </#if>
    <ul id="beautytab">
  	  <li>
  	    <a id="lubricationDetail" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/lubrication/listLubricationPlanDetails.html?lubrication.id=#{lubrication.id}&planProcFlag=${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${iframeTitle}</a>
  	  </li>	  
    </ul>
    <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
  </#if>
</@htmlPage>