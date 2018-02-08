<#include "../../includes/eam2008.ftl" />
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
	<#else>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${action.getText('RepairManHourEdit.title')}">
 <base target="_self">
	 <@ww.form name="'repairManHour'" action="'saveRepairManHour'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairManHourToken"/>
	 	   <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
		 	  	<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
	        <#else>
	        	<#if preRepairPlanDetail?exists>
	     	 		<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
	          	</#if>
	        </#if>
     	</#if>
     	<#if repairManHour.id?exists> 
          <@ww.hidden name="'repairManHour.id'" value="'${repairManHour.id?if_exists}'"/>
        </#if>
          <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
          <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
          <@ww.hidden name="'oldManHourNum'" value="''"/>
	 	  <@ww.hidden name="'oldProcManHourNum'" value="''"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  	<#--
	 	  		<@ww.textfield label="'${action.getText('repairManHour.workType')}'" name="'repairManHour.workType'" value="'${repairManHour.workType?if_exists}'" cssClass="'underline'" required="true" disabled="${disabled}"/>
	 	  	    <@ww.textfield label="'${action.getText('repairManHour.unitPrice')}'" name="'repairManHour.unitPrice'" value="'${repairManHour.unitPrice?if_exists}'" cssClass="'underline'" onchange="'calAllPrice();'" disabled="${disabled}"/>
	 	  	-->
	 	  	<@eam2008_WorkTypeSelector/>
	 	  	</tr>
	 	  	<tr>
	 	  	    <@ww.textfield label="'${action.getText('repairManHour.manHourNum')}'" name="'repairManHour.manHourNum'" value="'${(repairManHour.manHourNum?string('#,###,##0.##'))?if_exists}'" cssClass="'underline'" onchange="'calAllPrice();'" disabled="${disabled}"/>
	 	  	    <#assign allPrice=0/>
	 	  	    <#if repairManHour?exists && workType?exists>
	 	  	      <#if workType.unitPrice?exists && repairManHour.manHourNum?exists>
	 	  	        <#assign allPrice=repairManHour.manHourNum * workType.unitPrice/>
	 	  	      </#if>
	 	  	    </#if>
	 	  	    <@ww.textfield label="'${action.getText('repairManHour.allPrice')}'" name="'repairManHour.allPrice'" value="'${(allPrice?string('#,###,##0.##'))?if_exists}'" cssClass="'underline'" disabled="true"/>
	 	  	</tr>
	 	  	<#if planProcFlag?exists>
	        <#if (planProcFlag=='PROC')>
	          <tr>
	          	<@ww.textfield label="'${action.getText('repairManHour.procManHourNum')}'" name="'repairManHour.procManHourNum'" value="'${(repairManHour.procManHourNum?string('#,###,##0.##'))?if_exists}'" cssClass="'underline'" onchange="'calProcAllPrice();'"/>
	            <#assign procAllPrice=0/>
	 	  	    <#if repairManHour?exists && workType?exists>
	 	  	      <#if workType.unitPrice?exists && repairManHour.procManHourNum?exists>
	 	  	        <#assign procAllPrice=repairManHour.procManHourNum * workType.unitPrice/>
	 	  	      </#if>
	 	  	    </#if>
	          	<@ww.textfield label="'${action.getText('repairManHour.procAllPrice')}'" name="'repairManHour.procAllPrice'" value="'${(procAllPrice?string('#,###,##0.##'))?if_exists}'" cssClass="'underline'" disabled="true"/>
	          </tr>
	        </#if>
	        </#if>
	 	  	<tr>
	 	  		<@ww.textarea label="'${action.getText('repairManHour.comment')}'" 
					         name="'repairManHour.comment'" 
					         value="'${repairManHour.comment?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="${disabled}"
							 />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	      
          <#if !(action.isReadOnly())>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	      </#if> 
	     </@buttonBar>
		<script language="javascript">
		  window.onload = function(){
		    if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
		      document.forms[0].elements["oldProcManHourNum"].value = document.forms[0].elements["repairManHour.procManHourNum"].value;
		    }
		    document.forms[0].elements["oldManHourNum"].value = document.forms[0].elements["repairManHour.manHourNum"].value;
		  }
		  function customize_validate() {
		    if ('' == document.forms["repairManHour"].elements["workType.code"].value) {
		      alert("${action.getText('repairManHour.workType.requried')}");
		      return false;
		    } 
		    if ('' == document.forms["repairManHour"].elements["repairManHour.manHourNum"].value) {
		     // alert("${action.getText('repairManHour.manHourNum.requried')}");
		      //return false;
		    } else {
		      if (!isDoubleNumber("repairManHour.manHourNum")) {
		        alert("${action.getText('repairManHour.manHourNum.maxLength')}");
		        return false;
		      }
		      if (!isDoubleNumberBetweenBoolean(document.forms["repairManHour"].elements["repairManHour.manHourNum"].value,1000000001,0)) {
		        alert("${action.getText('repairManHour.manHourNum.maxLength')}");
		        return false;
		      }
		    }
		    if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
		      if ('' != document.forms[0].elements["repairManHour.procManHourNum"].value) {
		        if (!isDoubleNumber("repairManHour.procManHourNum")) {
		          alert("${action.getText('repairManHour.procManHourNum.notDouble')}");
		          return false;
		        }
		        if (!isNumberBetweenBoolen(document.forms["repairManHour"].elements["repairManHour.procManHourNum"].value,10000000,0)) {
		          alert("${action.getText('repairManHour.procManHourNum.maxLength')}");
		          return false;
		        }
		      }
		    }
		   if ('' != document.forms["repairManHour"].elements["repairManHour.comment"].value) {
		      if (!isValidLength(document.forms["repairManHour"],"repairManHour.comment",0,250)) {
		        alert("${action.getText('repairManHour.maxLength')}");
		        return false;
		      }
		    }
		    return true;
		  }	
		  /*
		   * 根据工时单价和预计工时数量,计算预计工时总价
		  */
		  function calAllPrice() {   
		    if ('' != document.forms["repairManHour"].elements["workType.unitPrice"].value && isDoubleNumber("workType.unitPrice")) {
		      var unitPrice = parseFloat(document.forms["repairManHour"].elements["workType.unitPrice"].value);
		      if ('' != document.forms["repairManHour"].elements["repairManHour.manHourNum"].value && isDoubleNumber("repairManHour.manHourNum")) {
		        var manHourNum = parseFloat(document.forms["repairManHour"].elements["repairManHour.manHourNum"].value);
		        document.forms["repairManHour"].elements["repairManHour.allPrice"].value = unitPrice*manHourNum;
		      } else {
		        document.forms["repairManHour"].elements["repairManHour.allPrice"].value = '';
		      }
		    }
		  }
		  function calProcAllPrice() {
		    if ('' != document.forms["repairManHour"].elements["workType.unitPrice"].value && isDoubleNumber("workType.unitPrice")) {
		      var unitPrice = parseFloat(document.forms["repairManHour"].elements["workType.unitPrice"].value);
		      if ('' != document.forms[0].elements["repairManHour.procManHourNum"].value && isDoubleNumber("repairManHour.procManHourNum")) {
		        var procManHourNum = parseFloat(document.forms["repairManHour"].elements["repairManHour.procManHourNum"].value); 
		        document.forms["repairManHour"].elements["repairManHour.procAllPrice"].value = unitPrice*procManHourNum;
		      } else {
		        document.forms["repairManHour"].elements["repairManHour.procAllPrice"].value= '';
		      }
		    }
		  }
		</script>
	 </@ww.form>
</@htmlPage>