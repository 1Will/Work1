<#include "../../includes/eam2008.ftl" />
<#assign detailEditTitle=''/>
<#assign disabled = ''/>
<#assign require = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
		<#assign require = 'true'/>
		<#assign detailEditTitle="${action.getText('preRepairPlanDetailEdit.title')}"/>
	<#else>
		<#assign disabled = 'true'/>
		<#assign require = 'false'/>
		<#assign detailEditTitle="${action.getText('preRepairProcDetailEdit.title')}"/>
	</#if>
</#if>
<@htmlPage title="${detailEditTitle}">
 <base target="_self">
	 <@ww.form name="'preRepairPlanDetail'" action="'savePreRepairPlanDetail'" method="'post'" validate="true">
	 	  <@ww.token name="savePreRepairPlanDetailToken"/>
	 	  <#if preRepairPlan.id?exists>
         	<@ww.hidden name="'preRepairPlan.id'" value="#{preRepairPlan.id}"/>
          </#if>
          <#if preRepairPlanDetail.id?exists>
         	<@ww.hidden name="'preRepairPlanDetail.id'" value="#{preRepairPlanDetail.id}"/>
          </#if>
          <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
          <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <@inputTable>
	 	   <#if toolingDevFlag?exists>
           <#if toolingDevFlag=='DEVICE'>
           	 <#if (planProcFlag=='PLAN')>
             	<@eam2008_DeviceSelector flag="DevicePreRepair" required="false"/>
             <#else>
             	<@eam2008_DeviceSelector flag="DevicePreRepair" required="false" display="false"/>
             </#if>
           <#else>
             <#if planProcFlag?exists>
	         <#if (planProcFlag=='PLAN')>
               <@eam2008_ToolingSelector toolingStatus="false" required="false" flag="ToolingPreRepair"/>
             <#else>
               <@eam2008_ToolingSelector toolingStatus="true"  required="false" flag="ToolingPreRepair"/>
             </#if>
             </#if>
           </#if>
           </#if>
            <tr>
            <@ww.textfield label="'${action.getText('toolingName')}'" name="'preRepairPlanDetail.toolingName'"   value="'${preRepairPlanDetail.toolingName?if_exists}'" cssClass="'underline'"/>	
               <td align="right" valign="top">	
		    	<#if planProcFlag?exists>
	 	  	      <#if planProcFlag=='PLAN'>
	 	  	        <span class="required">*</span>
	 	  	      </#if>
	 	  	    </#if>
	 	  	    <label class="label">${action.getText('PreRepairPlanDetail.department')}:</label></td>
		    	<td>
			    	<select name="department.id" valign="center">
						    <@ww.iterator value="departments" id="department">
						      <option value="<@ww.property value="id"/>"><@ww.property value="name"/></option>
						    </@ww.iterator>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
			    	<input type="checkbox" id="externalHelpFlag" name="externalHelpFlag" value="true"/><label for="externalHelpFlag" class="label">${action.getText('isExternalHelp')}</label>
		    	</td>
		    	
	        	<#--<#assign dutyPeopleName = ''/>
				<#if preRepairPlanDetail.dutyPeople?exists>
				 <#assign dutyPeopleName = "${preRepairPlanDetail.dutyPeople.name}" />
				</#if>
				<td align="right" valign="top">
				<#if planProcFlag?exists>
	 	  	      <#if planProcFlag=='PLAN'>
	 	  	        <span class="required">*</span>
	 	  	      </#if>
	 	  	    </#if>
				<label class="label">${action.getText('PreRepairPlanDetail.dutyPeople')}:</label></td>
	        	<td>	
	        		<input type="text" name="dutyPeople.name" 
	        			class="underline"  value="${dutyPeopleName}"  maxlength="150" size="20" disabled="${disabled}"/>
	        		<label id=""></label>
	        	    <#if planProcFlag?exists>
	 	  	        <#if planProcFlag=='PLAN'>
			    		<a onClick="dutyPeople_OpenDialog();">
			        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
			        </#if>
			        </#if>
		        </td>
		        <#assign dutyPeopleId = ''/>
				<#if preRepairPlanDetail.dutyPeople?exists>
				 	<#assign dutyPeopleId = "${preRepairPlanDetail.dutyPeople.id}" />
				</#if>
				<input type="hidden" name="dutyPeople.id" value="${dutyPeopleId}" /> -->
            </tr>
            <tr>
            <@ww.textfield label="'${action.getText('PreRepairPlanDetail.dutyPeople')}'" name="'preRepairPlanDetail.dutypeople'"   value="'${preRepairPlanDetail.dutypeople?if_exists}'" cssClass="'underline'" disabled="${disabled}" required="${require}"/>	
            	<@ww.select label="'${action.getText('PreRepairPlanDetail.repairLevel')}'" 
	 	  	                name="'repairLevel.id'" 
		    			    listKey="id" listValue="value"
		                    list="repairLevels" emptyOption="true" 
		                    required="false" disabled="${disabled}">
		    	</@ww.select>
            	<#--<#assign execPeopleName = ''/>
				<#if preRepairPlanDetail.execPeople?exists>
				 <#assign execPeopleName = "${preRepairPlanDetail.execPeople.name}" />
				</#if>
				<td align="right" valign="top"><label class="label">${action.getText('PreRepairPlanDetail.execPeople')}:</label></td>
	        	<td>
	        		<input type="text" name="execPeople.name" 
	        			class="underline"  value="${execPeopleName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
	        	    <#if planProcFlag?exists>
	 	  	        <#if planProcFlag=='PLAN'>
		    		<a onClick="execPeople_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	</#if>
		        	</#if>
		        </td>
		        <#assign execPeopleId = ''/>
				<#if preRepairPlanDetail.execPeople?exists>
				 	<#assign execPeopleId = "${preRepairPlanDetail.execPeople.id}" />
				</#if>
				<input type="hidden" name="execPeople.id" value="${execPeopleId}" /> -->
            </tr>
            <tr>
            <@ww.textfield label="'${action.getText('PreRepairPlanDetail.execPeople')}'" name="'preRepairPlanDetail.planExecPeople'"   value="'${preRepairPlanDetail.planExecPeople?if_exists}'" disabled="${disabled}" cssClass="'underline'"/>	
                <@pp.datePicker label="'${action.getText('PreRepairPlanDetail.planEstimateFinishDate')}'" name="'preRepairPlanDetail.planEstimateFinishDate'"
	                          value="'${(preRepairPlanDetail.planEstimateFinishDate?string('yyyy-MM-dd'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" disabled="${disabled}" maxlength="10"/>
               
            </tr>
            <#--
            <tr>
	 	  	    <@ww.select label="'${action.getText('preRepairPlanDetail.sourceType')}'"  name="'preRepairPlanDetail.sourceType'" 
		    			     listKey="value" listValue="label" value="'${preRepairPlanDetail.sourceType?if_exists}'"
		                     list="sourceTypes" onchange="'changeBudgeNo()'" 
		                     emptyOption="true" disabled="${disabled}"  required="${require}">
		    	</@ww.select>
		 	  	<td id="budgetNoTitle" align="right" valign="top" style="display:none">
		 	  	<#if planProcFlag?exists>
	 	  	      <#if planProcFlag=='PLAN'>
	 	  	        <span class="required">*</span>
	 	  	      </#if>
	 	  	    </#if>
		 	  	<label class="label">${action.getText('preRepairPlanDetail.budgetNo')}:</label></td>
		        <td id="budgetNoContent" style="display:none">
		        	<input type="text" name="preRepairPlanDetail.budgetNo" 
		        			class="underline"  value="${preRepairPlanDetail.budgetNo?if_exists}"  maxlength="150" size="20"/>
			    </td>
			   
	 	  	</tr>-->
	 	  	<tr>
	 	  	 <@ww.textfield label="'${action.getText('PreRepairPlanDetail.allFee')}'" name="'preRepairPlanDetail.allFee'" value="'${preRepairPlanDetail.planAllFee?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true" required="false" />
	 	  	<@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}" planProcFlag="${planProcFlag?if_exists}" disabled="${disabled}" require="${require}"/>
			</tr>
			<tr>	
				<@ww.textarea label="'${action.getText('PreRepairPlanDetail.content')}'" 
					          name="'preRepairPlanDetail.content'" 
					          value="'${preRepairPlanDetail.content?if_exists}'" rows="'3'" cols="'50'"
					          disabled="${disabled}" 
							 />
	 	  		<@ww.textarea label="'${action.getText('PreRepairPlanDetail.targetDemand')}'" 
					          name="'preRepairPlanDetail.targetDemand'" 
					          value="'${preRepairPlanDetail.targetDemand?if_exists}'" rows="'3'" cols="'50'"
					          disabled="${disabled}" 
							 />
	 	  	</tr>
	 	    <#if planProcFlag?exists>
	 	  	  <#if planProcFlag=='PROC'>
	 	  	  <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	 	  	  <tr>
	 	  	      <#assign procExecPeopleName = ''/>
				  <#if preRepairPlanDetail.procExecPeople?exists>
				  	<#assign procExecPeopleName = "${preRepairPlanDetail.procExecPeople.name}" />
				  </#if>
				<#--  <td align="right" valign="top"><label class="label"></label>${action.getText('PreRepairProcDetail.execPeople')}:</td>-->
	        	 
	        	 <#-- <td>${action.getText('PreRepairProcDetail.execPeople')}:
	        		  <input type="text" name="procExecPeople.name" 
	        			  class="underline"  value="${procExecPeopleName}"  maxlength="150" size="10" disabled="true"/>
	        		  <label id=""></label>
		    		    <a onClick="procExecPeople_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		          </td>
		          <#assign procExecPeopleId = ''/>
				  <#if preRepairPlanDetail.procExecPeople?exists>
				    <#assign procExecPeopleId = "${preRepairPlanDetail.procExecPeople.id}" />
				  </#if>
				  <input type="hidden" name="procExecPeople.id" value="${procExecPeopleId}" /> -->
				  <@ww.textfield label="'${action.getText('PreRepairProcDetail.execPeople')}'" name="'practiceExecPeople'"   value="'${preRepairPlanDetail.practiceExecPeople?if_exists}'" cssClass="'underline'"/>	
	 	  	      <@pp.datePicker label="'${action.getText('PreRepairProcDetail.estimateExecDate')}'" name="'preRepairPlanDetail.procEstimateFinishDate'"
	                              value="'${(preRepairPlanDetail.procEstimateFinishDate?string('yyyy-MM-dd'))?if_exists}'" 
	                              cssClass="'underline'" size="15" required="fasle" maxlength="10"/>
	 	  	  </tr>
	 	  	  <tr>
	              <@ww.select label="'${action.getText('PreRepairProcDetail.result')}'" 
		 	  	               name="'procResult'" value="'${preRepairPlanDetail.procResult?if_exists}'"
			    			   listKey="value" listValue="label"
			                   list="procResults" emptyOption="true" 
			                   required="false">
			      </@ww.select>
	 	  	      <@ww.textfield label="'${action.getText('PreRepairProcDetail.allFee')}'" name="'PreRepairProcDetail.allFee'" value="'${preRepairPlanDetail.procAllFee?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true" required="false" />
	 	  	  </tr>
	 	  	  <tr>
	 	  	   	  <@ww.textarea label="'${action.getText('PreRepairProcDetail.execSituation')}'" 
					            name="'preRepairPlanDetail.execSituation'" 
					            value="'${preRepairPlanDetail.execSituation?if_exists}'" rows="'3'" cols="'50'"
							 />
				  <#assign procCheckPeopleName = ''/>
				  <#if preRepairPlanDetail.procCheckPeople?exists>
				  	<#assign procCheckPeopleName = "${preRepairPlanDetail.procCheckPeople.name}" />
				  </#if>
				  <td align="right" valign="top"><label class="label">${action.getText('PreRepairProcDetail.checkPeople')}:</label></td>
	        	  <td>
	        		  <input type="text" name="procCheckPeople.name" 
	        			  class="underline"  value="${procCheckPeopleName}"  maxlength="150" size="10" disabled="true"/>
	        		  <label id=""></label>
		    		    <a onClick="procCheckPeople_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		          </td>
		          <#assign procCheckPeopleId = ''/>
				  <#if preRepairPlanDetail.procCheckPeople?exists>
				    <#assign procCheckPeopleId = "${preRepairPlanDetail.procCheckPeople.id}" />
				  </#if>
				  <input type="hidden" name="procCheckPeople.id" value="${procCheckPeopleId}" /> 
	 	  	  </tr>
	 	  	  </#if>
	 	  	</#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	        <#if !(action.isReadOnly())>
	        	 <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	        	</#if>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
		<script language="javascript">
		  window.onload = function() {
		    <#if preRepairPlanDetail.id?exists>
	          document.all.frame.src = '${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanItems.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=' + #{preRepairPlanDetail.id} + '&planProcFlag=' + '${planProcFlag?if_exists}&preYearFlag=PRE';
		      getObjByNameRe("repairItem").className = "selectedtab";
	        </#if>
	        <#if preRepairPlanDetail.department?exists>
	          document.forms["preRepairPlanDetail"].elements["department.id"].value=#{preRepairPlanDetail.department.id?if_exists};
	     	</#if>
	        <#if preRepairPlanDetail.repairLevel?exists>
	         document.forms["preRepairPlanDetail"].elements["repairLevel.id"].value=#{preRepairPlanDetail.repairLevel.id?if_exists};
	     	</#if>
	     	<#if preRepairPlanDetail.externalHelpFlag?string == 'true'>
	     	  document.forms["preRepairPlanDetail"].elements["externalHelpFlag"].checked=true;
	     	</#if>
	     	<#--
	     	/*
	     	 * 如果费用来源是计划内,则显示预算编号字段
	     	*/
	     	if(document.forms[0].elements["preRepairPlanDetail.sourceType"].value=='IN_BUDGET'){
	     	  getObjByNameRe("budgetNoTitle").style.display='inline';
	     	  getObjByNameRe("budgetNoContent").style.display='inline';
			}
			-->
		    <#if planProcFlag?exists>
	 	  	  <#if planProcFlag=='PROC'>
	 	  	    document.forms["preRepairPlanDetail"].elements["department.id"].disabled=true;
	 	  	    document.forms["preRepairPlanDetail"].elements["externalHelpFlag"].disabled=true;
	 	  	    //document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.budgetNo"].disabled=true;
	 	  	    disableCalendars(document.forms[0],new Array("preRepairPlanDetail.planEstimateFinishDate"),true);
	 	  	  </#if>
	 	  	</#if>
		  }
		 /*
		  *责任人选择
		 */
		 <#-- function dutyPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, dutyPeopleSelectorHandler);
		  }
		  function dutyPeopleSelectorHandler(result) {
		    if(null != result) {
		      document.forms["preRepairPlanDetail"].elements["dutyPeople.id"].value = result[0];
		      document.forms["preRepairPlanDetail"].elements["dutyPeople.name"].value = result[1];
		    } 
		  }
		  /*
		   * 计划执行人选择
		  */
		  function execPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, execPeopleSelectorHandler);
		  }
		  function execPeopleSelectorHandler(result) {
		    if(null != result) {
		      document.forms["preRepairPlanDetail"].elements["execPeople.id"].value = result[0];
		      document.forms["preRepairPlanDetail"].elements["execPeople.name"].value = result[1];
		    }
		  }-->
		  /*
		   * 实际执行人选择
		  */
		 <#-- function procExecPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, procExecPeopleSelectorHandler);
		  }
		  function procExecPeopleSelectorHandler(result) {
		    if(null != result) {
		      document.forms["preRepairPlanDetail"].elements["procExecPeople.id"].value = result[0];
		      document.forms["preRepairPlanDetail"].elements["procExecPeople.name"].value = result[1];
		    }
		  }-->
		  /*
		   * 验收人选择
		  */
		  function procCheckPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, procCheckPeopleSelectorHandler);
		  }
		  function procCheckPeopleSelectorHandler(result) {
		    if (null != result) {
		      document.forms["preRepairPlanDetail"].elements["procCheckPeople.id"].value = result[0];
		      document.forms["preRepairPlanDetail"].elements["procCheckPeople.name"].value = result[1];
		    }
		  }
		  function customize_validate() {
		  <#--  if (document.forms[0].elements["toolingDevFlag"].value == 'DEVICE') {
		      if(!eam2008_device_validate("${action.getText('select.device.no')}")){
				return false;
			  }
		    } else {
		      if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		        return false;
		      }
		    }-->
			
			if(-1 == document.forms["preRepairPlanDetail"].elements["department.id"].value) {
			  alert("${action.getText("select.dutyDepartment")}");
			  return false;
			}
		 if(getObjByNameRe("preRepairPlanDetail.toolingName").value!=''){
		   if(!isValidLength(document.forms["preRepairPlanDetail"], "preRepairPlanDetail.toolingName", null, 50)){
				alert("${action.getText('preRepairPlanDetail.toolingName.length')}");
				return  false;
			   }
			}
			if ('' == getObjByNameRe("preRepairPlanDetail.dutypeople").value) {
			  alert("${action.getText("select.dutyPeople")}");
			  return false;
			}else{
	        if(!isValidLength(document.forms["preRepairPlanDetail"], "preRepairPlanDetail.dutypeople", null, 50)){
				alert("${action.getText('dutypeople.length')}");
				return  false;
			   }
	       }
	       if(getObjByNameRe("preRepairPlanDetail.planExecPeople").value!=''){
		   if(!isValidLength(document.forms["preRepairPlanDetail"], "preRepairPlanDetail.planExecPeople", null, 250)){
				alert("${action.getText('preRepairPlanDetail.planExecPeople.length')}");
				return  false;
			   }
			}
			//验证费用来源
			if (!eam2008_budgetDetail_sourceType_validate("${action.getText('select.sourceType')}")) {
			  return false;
			}
			<#--
			//验证预算编号
			if (!eam2008_budgetDetail_validate("${action.getText('select.budgetNo')}")) {
			  return false;
			}
			-->
			<#--
			if (document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.sourceType"].value == 'IN_BUDGET') {
			  if ('' == document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.budgetNo"].value) {
			    alert("${action.getText("select.budgetNo")}");
			    return false;
			  } if (!isValidLength(document.forms["preRepairPlanDetail"],"preRepairPlanDetail.budgetNo",0,50)) {
			    alert("${action.getText("select.budgetNo.maxlength")}");
			    return false;
			  }
			}
			-->
			if ('' != document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.planEstimateFinishDate"].value) {
			  if(!isDate("preRepairPlanDetail.planEstimateFinishDate")){
			    alert("${action.getText('select.right.preRepairPlanDetail.planEstimateFinishDate')}");
			    return false;
			  }
			}
			/*
			 * 验证工作内容的长度
			*/
			if ('' != document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.content"].value) {
			  if (!isValidLength(document.forms["preRepairPlanDetail"],"preRepairPlanDetail.content",0,250)) {
			    alert("${action.getText("preRepairPlanDetail.content.maxLength")}");
			    return false;
			  }
			}
			/*
			 * 验证目标要求的长度
			*/
			if ('' != document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.targetDemand"].value) {
			  if (!isValidLength(document.forms["preRepairPlanDetail"],"preRepairPlanDetail.targetDemand",0,250)) {
			    alert("${action.getText("preRepairPlanDetail.targetDemand.maxLength")}");
			    return false;
			  }
			}
			if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
			  if ('' != document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.procEstimateFinishDate"].value) {
			  	if(!isDate("preRepairPlanDetail.procEstimateFinishDate")){
					alert("${action.getText('select.right.preRepairPlanDetail.procEstimateFinishDate')}");
					return false;
				}
			  }
			  if ('' != document.forms["preRepairPlanDetail"].elements["preRepairPlanDetail.execSituation"].value) {
			    if (!isValidLength(document.forms["preRepairPlanDetail"],"preRepairPlanDetail.execSituation",0,250)) {
			      alert("${action.getText("preRepairPlanDetail.execSituation.maxLength")}");
			      return false;
			    }
			  }
			}
			return true;
          }
          <#--
          function changeBudgeNo() {
	        if(document.forms[0].elements["preRepairPlanDetail.sourceType"].value=='IN_BUDGET'){
	     	  getObjByNameRe("budgetNoTitle").style.display='inline';
	     	  getObjByNameRe("budgetNoContent").style.display='inline';
		    }
		    if(document.forms[0].elements["preRepairPlanDetail.sourceType"].value=='OUT_BUDGET'){
	     	  getObjByNameRe("budgetNoTitle").style.display='none';
	     	  getObjByNameRe("budgetNoContent").style.display='none';
	     	  document.forms[0].elements["preRepairPlanDetail.budgetNo"].value='';
		    }
		    return true;
	      }
	      -->
		</script>
	 </@ww.form>
     <#if preRepairPlanDetail.id?exists>
     	<ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanItems.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=#{preRepairPlanDetail.id}&planProcFlag=${planProcFlag?if_exists}&preYearFlag=PRE" target="frame">${action.getText('repairItem')}</a></li>
	  		<li><a id="spares" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanSpares.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=#{preRepairPlanDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=PRE" target="frame">${action.getText('spares')}</a></li>
	        <li><a id="repairManHour" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanManHours.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=#{preRepairPlanDetail.id}&planProcFlag=${planProcFlag?if_exists}&preYearFlag=PRE" target="frame">${action.getText('repairManHour')}</a></li>
	        <li><a id="repairTool" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanTools.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=#{preRepairPlanDetail.id}&planProcFlag=${planProcFlag?if_exists}&preYearFlag=PRE" target="frame">${action.getText('repairTool')}</a></li>
     		<li><a id="repairFees" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanFees.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlan.id=#{preRepairPlan.id}&preRepairPlanDetail.id=#{preRepairPlanDetail.id}&planProcFlag=${planProcFlag?if_exists}&preYearFlag=PRE" target="frame">${action.getText('repairFees')}</a></li>
     		<li><a id="deviceDoc"   onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanDocs.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=#{preRepairPlanDetail.id}&planProcFlag=${planProcFlag?if_exists}&preYearFlag=PRE" target="frame" >${action.getText('repairDoc')}</a></li>
     	</ul>
     	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>  
     </#if>
</@htmlPage>