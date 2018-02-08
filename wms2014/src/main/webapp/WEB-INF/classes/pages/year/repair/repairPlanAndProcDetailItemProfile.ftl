<#include "../../includes/eam2008.ftl" />
<#assign detailEditTitle=''/>
<#assign disabled = ''/>
<#assign require = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
		<#assign require = 'true'/>
		<#assign detailEditTitle="${action.getText('repairPlanDetailEdit.title')}"/>
	<#else>
		<#assign disabled = 'true'/>
		<#assign require = 'false'/>
		<#assign detailEditTitle="${action.getText('repairProcDetailEdit.title')}"/>
	</#if>
</#if>
<@htmlPage title="${detailEditTitle}">
 <base target="_self">
	 <@ww.form name="'repairPlanOrProcDetail'" action="'saveRepairPlanOrProcDetail'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairPlanOrProcDetailToken"/>
	 	  <#if repairPlanOrProc.id?exists>
         	<@ww.hidden name="'repairPlanOrProc.id'" value="#{repairPlanOrProc.id}"/>
          </#if>
           <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
          <#if repairPlanOrProcDetail.id?exists>
         	<@ww.hidden name="'repairPlanOrProcDetail.id'" value="#{repairPlanOrProcDetail.id}"/>
          </#if>
          <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
          <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 	  <@inputTable>
	 	  <tr>
	 	  	<@ww.textfield label="'${action.getText('repairPlanOrProcDetail.workNo')}'" name="'repairPlanOrProcDetail.workNo'" value="'${repairPlanOrProcDetail.workNo?if_exists}'" cssClass="'underline'" disabled="${disabled}"/>
	 	   	<@ww.select label="'${action.getText('repairPlanOrProcDetail.category')}'" 
	 	  	                name="'category.id'" 
		    			    listKey="id" listValue="value"
		                    list="categorys" emptyOption="false" 
		                    required="false" disabled="${disabled}" required="${require}">
		    </@ww.select>
		   </tr>
	 	   <#if toolingDevFlag?exists>
           <#if toolingDevFlag=='DEVICE'>
             <#if (planProcFlag=='PLAN')>
               <@eam2008_DeviceSelector flag="DevicePreRepair"/>
             <#else>
               <@eam2008_DeviceSelector display="false" flag="DevicePreRepair"/>
             </#if>
           <#else>
             <#if planProcFlag?exists>
	         <#if (planProcFlag=='PLAN')>
               <@eam2008_ToolingSelector toolingStatus="false" flag="ToolingPreRepair"/>
             <#else>
               <@eam2008_ToolingSelector toolingStatus="true"/>
             </#if>
             </#if>
           </#if>
           </#if>
            <tr>
				<#assign assetModel = ''/>
				<#if repairPlanOrProcDetail.asset?exists>
				 	<#assign assetModel = "${repairPlanOrProcDetail.asset.model?if_exists}" />
				</#if>
		 		<@ww.textfield label="'${action.getText('asset.model')}'" name="'device.model'" value="'${assetModel?if_exists}'" cssClass="'underline'" disabled="true"/>
            	<#assign assetSupplier = ''/>
            	<#if repairPlanOrProcDetail.asset?exists>
            	   <#if repairPlanOrProcDetail.asset.supplier?exists>
            	     <#assign assetSupplier = "${repairPlanOrProcDetail.asset.supplier.name?if_exists}" />
            	   </#if>
				</#if>
				<@ww.textfield label="'${action.getText('supplier')}'" name="'device.supplierName'" value="'${assetSupplier?if_exists}'" cssClass="'underline'"  disabled="true"/>
            </tr>
            <tr>
            	<td align="right" valign="top">	
		    	<#if planProcFlag?exists>
	 	  	      <#if planProcFlag=='PLAN'>
	 	  	        <span class="required">*</span>
	 	  	      </#if>
	 	  	    </#if>
	 	  	    <label class="label">${action.getText('repairPlanOrProcDetail.department')}:</label></td>
		    	<td>
			    	<select name="department.id" valign="center">
					    <@ww.iterator value="departments" id="department">
					      <option value="<@ww.property value="id"/>"><@ww.property value="name"/></option>
					    </@ww.iterator>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
			    	<input type="checkbox" id="externalHelpFlag" name="externalHelpFlag" value="true"/><label for="externalHelpFlag" class="label">${action.getText('repairPlanOrProcDetail.isExternalHelp')}</label>
		    	</td>
		    	<td align="right" valign="top">	
		    	  <label class="label">${action.getText('repairPlanOrProcDetail.technicalLevel')}:</label>
		        </td>
		    	<td>
		    	  <input type="checkbox" id="machineFlag" name="machineFlag" value="true"/>
		    	  <label for="machineFlag" class="label">${action.getText('repairPlanOrProcDetail.machine')}</label>
		    	  <input type="checkbox" id="electricalFlag" name="electricalFlag" value="true"/>
		    	  <label for="electricalFlag" class="label">${action.getText('repairPlanOrProcDetail.electrical')}</label>
		    	</td>
		    	<#--
            	<@ww.select label="'${action.getText('repairPlanOrProcDetail.technicalLevel')}'" 
	 	  	                name="'technicalLevel.id'" 
		    			    listKey="id" listValue="value"
		                    list="technicalLevels" emptyOption="false" 
		                    required="false" disabled="${disabled}">
		    	</@ww.select>
		    	-->
            </tr>
            <tr>
                <@pp.datePicker label="'${action.getText('repairPlanOrProcDetail.planRepairDate')}'" name="'planRepairDate'"
	                          value="'${(repairPlanOrProcDetail.planRepairDate?string('yyyy-MM'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" disabled="${disabled}" dateFormat="'%Y-%m'" maxlength="7"/>
	            <@pp.datePicker label="'${action.getText('repairPlanOrProcDetail.planBeginDate')}'" name="'repairPlanOrProcDetail.planBeginDate'"
	                          value="'${(repairPlanOrProcDetail.planBeginDate?string('yyyy-MM-dd'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" disabled="${disabled}" maxlength="10"/>
            </tr>
            	<@pp.datePicker label="'${action.getText('repairPlanOrProcDetail.planEndDate')}'" name="'repairPlanOrProcDetail.planEndDate'"
	                          value="'${(repairPlanOrProcDetail.planEndDate?string('yyyy-MM-dd'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" disabled="${disabled}" maxlength="10"/>
	 	  	    <@ww.textfield label="'${action.getText('repairPlanOrProcDetail.planDetailAllFee')}'" name="'repairPlanOrProcDetail.planDetailAllFee'" value="'${repairPlanOrProcDetail.planDetailAllFee?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true" required="false" />
	 	  	</tr>
	 	  	<tr>
				<@ww.textarea label="'${action.getText('repairPlanOrProcDetail.content')}'" 
					          name="'repairPlanOrProcDetail.content'" 
					          value="'${repairPlanOrProcDetail.content?if_exists}'" rows="'3'" cols="'50'"
					          disabled="${disabled}" 
							 />
	 	  		<@ww.textarea label="'${action.getText('repairPlanOrProcDetail.targetDemand')}'" 
					          name="'repairPlanOrProcDetail.targetDemand'" 
					          value="'${repairPlanOrProcDetail.targetDemand?if_exists}'" rows="'3'" cols="'50'"
					          disabled="${disabled}" 
							 />
	 	  	</tr>
	 	    <#if planProcFlag?exists>
	 	  	  <#if planProcFlag=='PROC'>
	 	  	  <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	 	  	  <tr>
                <@pp.datePicker label="'${action.getText('repairPlanOrProcDetail.procRepairDate')}'" name="'procRepairDate'"
	                          value="'${(repairPlanOrProcDetail.procRepairDate?string('yyyy-MM'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" dateFormat="'%Y-%m'" maxlength="7"/>
	            <@pp.datePicker label="'${action.getText('repairPlanOrProcDetail.procBeginDate')}'" name="'repairPlanOrProcDetail.procBeginDate'"
	                          value="'${(repairPlanOrProcDetail.procBeginDate?string('yyyy-MM-dd'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" maxlength="10"/>
              </tr>
	 	  	  <tr>
	 	  	  	  <@pp.datePicker label="'${action.getText('repairPlanOrProcDetail.procEndDate')}'" name="'repairPlanOrProcDetail.procEndDate'"
	                          value="'${(repairPlanOrProcDetail.procEndDate?string('yyyy-MM-dd'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" maxlength="10"/>
	               <@ww.select label="'${action.getText('repairPlanOrProcDetail.result')}'" 
		 	  	               name="'procResult'" value="'${repairPlanOrProcDetail.procResult?if_exists}'"
			    			   listKey="value" listValue="label"
			                   list="procResults" emptyOption="true" 
			                   required="false">
			      </@ww.select>            
	 	  	  </tr>
	 	  	  <tr>
	 	  	      <@ww.textfield label="'${action.getText('repairPlanOrProcDetail.procDetailAllFee')}'" name="'repairPlanOrProcDetail.procDetailAllFee'" value="'${repairPlanOrProcDetail.procDetailAllFee?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true" required="false" />
	 	  	   	  <@ww.textarea label="'${action.getText('repairPlanOrProcDetail.execSituation')}'" 
					            name="'repairPlanOrProcDetail.execSituation'" 
					            value="'${repairPlanOrProcDetail.execSituation?if_exists}'" rows="'3'" cols="'50'"
							 />
			  </tr>
	 	  	  </#if>
	 	  	</#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	  
             <#if !(action.isReadOnly())>
	        	 <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	        </#if>	
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="javascript:window.close();">
	     </@buttonBar>
		<script language="javascript">
		  window.onload = function() {
		    <#if repairPlanOrProcDetail.id?exists>
		      document.all.frame.src = '${req.contextPath}/year/repair/listPreRepairPlanItems.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&preYearFlag=YEAR';
		      document.getElementById("repairItem").className = "selectedtab";
	        </#if>
	        <#if repairPlanOrProcDetail.category?exists>
	          document.forms["repairPlanOrProcDetail"].elements["category.id"].value=#{repairPlanOrProcDetail.category.id?if_exists};
	     	</#if>
	        <#if repairPlanOrProcDetail.department?exists>
	          document.forms["repairPlanOrProcDetail"].elements["department.id"].value=#{repairPlanOrProcDetail.department.id?if_exists};
	     	</#if>
	     	<#if repairPlanOrProcDetail.externalHelpFlag?string == 'true'>
	     	  document.forms["repairPlanOrProcDetail"].elements["externalHelpFlag"].checked=true;
	     	</#if>
	     	<#if repairPlanOrProcDetail.machineFlag>
	     	  document.forms["repairPlanOrProcDetail"].elements["machineFlag"].checked=true;
	     	</#if>
	        <#if repairPlanOrProcDetail.electricalFlag>
	     	  document.forms["repairPlanOrProcDetail"].elements["electricalFlag"].checked=true;
	     	</#if>
		    <#if planProcFlag?exists>
	 	  	  <#if planProcFlag=='PROC'>
	 	  	    //disabled计划明细的字段
	 	  	    document.forms["repairPlanOrProcDetail"].elements["department.id"].disabled=true;
	 	  	    document.forms["repairPlanOrProcDetail"].elements["externalHelpFlag"].disabled=true;
	 	  	    document.forms["repairPlanOrProcDetail"].elements["machineFlag"].disabled=true;
	 	  	    document.forms["repairPlanOrProcDetail"].elements["electricalFlag"].disabled=true;
	 	  	    disableCalendars(document.forms[0],new Array("planRepairDate","repairPlanOrProcDetail.planBeginDate","repairPlanOrProcDetail.planEndDate"),true);
	 	  	  </#if>
	 	  	</#if>
		  }
		 
		  function customize_validate() {
		    //如果是实施明细则执行验证
		  	if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
		  	  //验证实际维修日期格式
			  if ('' != document.forms["repairPlanOrProcDetail"].elements["procRepairDate"].value) {
			    if(!isDateOfNotDay("procRepairDate")){
			      alert("${action.getText('select.right.repairPlanOrProcDetail.procRepairDate')}");
			      return false;
			    }
			  }
			  //验证实际开工日期是否为日期型
			  if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.procBeginDate"].value) {
			  	if(!isDate("repairPlanOrProcDetail.procBeginDate")){
					alert("${action.getText('select.right.repairPlanOrProcDetail.procBeginDate')}");
					return false;
				}
			  }
			  //验证实际完工日期是否为日期型
			  if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.procEndDate"].value) {
			  	if(!isDate("repairPlanOrProcDetail.procEndDate")){
					alert("${action.getText('select.right.repairPlanOrProcDetail.procEndDate')}");
					return false;
				}
			  }
			  //验证实际开工日期是否在实际完工日期之后
			  if (!validateBeginDateAndEndDateLogic("repairPlanOrProcDetail.procBeginDate","repairPlanOrProcDetail.procEndDate")) {
			    alert("${action.getText('repairPlanOrProcDetail.procEndDate.early.repairPlanOrProcDetail.procBeginDate.error')}");
			    return false;
			  }
			  //验证执行情况长度是否超过250字符
			  if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.execSituation"].value) {
			    if (!isValidLength(document.forms["repairPlanOrProcDetail"],"repairPlanOrProcDetail.execSituation",0,250)) {
			      alert("${action.getText("repairPlanOrProcDetail.execSituation.maxLength")}");
			      return false;
			    }
			  }
			  return true;
			}
			//验证工作令号长度是否超过50字符
		    if ('' != document.forms[0].elements["repairPlanOrProcDetail.workNo"].value) {
		      if (!isValidLength(document.forms["repairPlanOrProcDetail"],"repairPlanOrProcDetail.workNo",0,50)) {
		        alert("${action.getText('repairPlanOrProcDetail.workNo.maxLength')}");
		        return false;
		      }
		    }
		    //验证种类是否输入
		    if (-1 == document.forms[0].elements["category.id"].value) {
		      alert("${action.getText('select.category.id')}");
		      return false;
		    }
		    if (document.forms[0].elements["toolingDevFlag"].value == 'DEVICE') {
		      //验证设备编号是否选择
		      if(!eam2008_device_validate("${action.getText('select.device.no')}")){
				return false;
			  }
		    } else {
		      //验证工装编号是否选择
		      if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		        return false;
		      }
		    }
		    //验证部门是否选择
			if(-1 == document.forms["repairPlanOrProcDetail"].elements["department.id"].value) {
			  alert("${action.getText("select.dutyDepartment")}");
			  return false;
			}
			//验证计划维修日期格式
			if ('' != document.forms["repairPlanOrProcDetail"].elements["planRepairDate"].value) {
			  if(!isDateOfNotDay("planRepairDate")){
			    alert("${action.getText('select.right.repairPlanOrProcDetail.planRepairDate')}");
			    return false;
			  }
			}
			//验证计划开工日期是否为日期型
			if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.planBeginDate"].value) {
			  if(!isDate("repairPlanOrProcDetail.planBeginDate")){
			    alert("${action.getText('select.right.repairPlanOrProcDetail.planBeginDate')}");
			    return false;
			  }
			}
			//验证计划完工日期是否为日期型
			if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.planEndDate"].value) {
			  if(!isDate("repairPlanOrProcDetail.planEndDate")){
			    alert("${action.getText('select.right.repairPlanOrProcDetail.planEndDate')}");
			    return false;
			  }
			}
			//验证计划开工日期是否在计划完工日期之后
			if (!validateBeginDateAndEndDateLogic("repairPlanOrProcDetail.planBeginDate","repairPlanOrProcDetail.planEndDate")) {
			  alert("${action.getText('repairPlanOrProcDetail.planEndDate.early.repairPlanOrProcDetail.planBeginDate.erro')}");
			  return false;
			}
			/*
			 * 验证工作内容的长度是否超过250字符
			*/
			if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.content"].value) {
			  if (!isValidLength(document.forms["repairPlanOrProcDetail"],"repairPlanOrProcDetail.content",0,250)) {
			    alert("${action.getText("repairPlanOrProcDetail.content.maxLength")}");
			    return false;
			  }
			}
			/*
			 * 验证目标要求的长度是否超过250字符
			*/
			if ('' != document.forms["repairPlanOrProcDetail"].elements["repairPlanOrProcDetail.targetDemand"].value) {
			  if (!isValidLength(document.forms["repairPlanOrProcDetail"],"repairPlanOrProcDetail.targetDemand",0,250)) {
			    alert("${action.getText("repairPlanOrProcDetail.targetDemand.maxLength")}");
			    return false;
			  }
			}
			return true;
          }
          //验证完工日期是否在开工日期之后
          function validateBeginDateAndEndDateLogic(element1, element2) {
            var beginDate = document.forms[0].elements[element1].value;
            var endDate = document.forms[0].elements[element2].value;
            if (!isDateLessThenOldDate(endDate,beginDate)) {
              return false;
            }
            return true;
          }
		</script>
	 </@ww.form>
     <#if repairPlanOrProcDetail.id?exists>
     	<ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/year/repair/listPreRepairPlanItems.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=YEAR" target="frame">${action.getText('repairItem')}</a></li>
	  		<li><a id="spares" onclick="activeTab(this);"  href="${req.contextPath}/year/repair/listPreRepairPlanSpares.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=YEAR" target="frame">${action.getText('spares')}</a></li>
	        <li><a id="repairManHour" onclick="activeTab(this);"  href="${req.contextPath}/year/repair/listPreRepairPlanManHours.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=YEAR" target="frame">${action.getText('repairManHour')}</a></li>
	        <li><a id="repairTool" onclick="activeTab(this);"  href="${req.contextPath}/year/repair/listPreRepairPlanTools.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=YEAR" target="frame">${action.getText('repairTool')}</a></li>
     		<li><a id="repairFees" onclick="activeTab(this);"  href="${req.contextPath}/year/repair/listPreRepairPlanFees.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProc.id=#{repairPlanOrProc.id}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=YEAR" target="frame">${action.getText('repairFees')}</a></li>
     		<li><a id="deviceDoc"   onclick="activeTab(this);" href="${req.contextPath}/year/repair/listPreRepairPlanDocs.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=#{repairPlanOrProcDetail.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=YEAR" target="frame" >${action.getText('repairDoc')}</a></li>
     	</ul>
     	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>  
     </#if>
</@htmlPage>