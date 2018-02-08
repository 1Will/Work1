<#include "../../includes/eam2008.ftl" />
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
	<#else>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${action.getText('preRepairPlanDetailSpare.title')}">
 <base target="_self">
	 <@ww.form name="'repairSpare'" action="'saveRepairSpare'" method="'post'" validate="true">
	 	  <@ww.token name="savePreRepairPlanDetailSpareToken"/>
	 	  <@ww.hidden name="'preRepairPlanDetail.id'" value="'${req.getParameter('preRepairPlanDetail.id')?if_exists}'"/>
	 	  <@ww.hidden name="'repairSpare.id'" value="'${req.getParameter('repairSpare.id')?if_exists}'"/>
	 	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 	  <@ww.hidden name="'oldPlanUsedNum'" value="''"/>
	 	  <@ww.hidden name="'oldProcUsedNum'" value="''"/>
	 	  <@inputTable>
	 	  	<tr>
				<#assign spareNo = ''/>
					<#if repairSpare.spare?exists>
					 <#assign spareNo = "${repairSpare.spare.spareNo}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('repairSpare.spare')}:</label></td>
	        	<td>
	        		<input type="text" name="spare.spareNo" 
	        			class="underline"  value="${spareNo}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
	        		<#if planProcFlag?exists>
	                <#if planProcFlag=='PLAN'>
		    		<a onClick="spare_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	</#if>
		        	</#if>
		        </td>
		        <#assign spareId = ''/>
				<#if repairSpare.spare?exists>
				 <#assign managerId = "${repairSpare.spare.id}" />
				</#if>
				<input type="hidden" name="spare.id" value="${spareId}" />
				<#assign spareName = ''/>
				<#if repairSpare.spare?exists>
				 <#assign spareName = "${repairSpare.spare.name}" />
				</#if>
				<@ww.textfield label="'${action.getText('spare.name')}'" name="'spare.name'"   value="'${spareName}'" cssClass="'underline'"  disabled="true" readonly="true"/>
			</tr>
			
        	<tr>
                <#assign spareModelSpecs = ''/>
				<#if repairSpare.spare?exists>
				 	<#assign spareModelSpecs = "${repairSpare.spare.modelSpecs}" />
				</#if>
				<#assign spareCategory = ''/>
				<#if repairSpare.spare?exists>
					<#if repairSpare.spare.category?exists>
					 	<#assign spareCategory = "${repairSpare.spare.category.value}" />
					</#if>
				</#if>
				<@ww.textfield label="'${action.getText('spare.modelSpecs')}'" name="'spare.modelSpecs'"   value="'${spareModelSpecs}'" cssClass="'underline'"  disabled="true" readonly="true"/>
            	<@ww.textfield label="'${action.getText('spare.category')}'" name="'spare.category'"   value="'${spareCategory}'" cssClass="'underline'"  disabled="true" readonly="true"/>
            </tr>    
            <tr>
            	<#assign spareUnitPrice = ''/>
				<#if repairSpare.spare?exists>
				 	<#assign spareUnitPrice = "${repairSpare.spare.unitPrice}" />
				</#if> 
				<@ww.textfield label="'${action.getText('spare.unitPrice')}'" name="'spare.unitPrice'"   value="'${spareUnitPrice}'" cssClass="'underline'"  disabled="true" readonly="true"/>    
      			<@ww.textfield label="'${action.getText('repairSpare.planUsedNum')}'" name="'repairSpare.planUsedNum'"   value="'${repairSpare.planUsedNum?if_exists}'" cssClass="'underline'" disabled="${disabled}"/>    
            </tr> 
            <#if planProcFlag?exists>
	        <#if (planProcFlag=='PROC')>
	        	<tr>
	        		<@ww.textfield label="'${action.getText('repairSpare.procUsedNum')}'" name="'repairSpare.procUsedNum'"   value="'${repairSpare.procUsedNum?if_exists}'" cssClass="'underline'"/>    
	        	</tr>
	        </#if>
	        </#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
		<script language="JavaScript" type="text/JavaScript"> 
		        window.onload = function() {
		          if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
		            document.forms[0].elements["oldProcUsedNum"].value = document.forms[0].elements["repairSpare.procUsedNum"].value;
		          }
		          document.forms[0].elements["oldPlanUsedNum"].value = document.forms[0].elements["repairSpare.planUsedNum"].value;
		        }
				function spare_OpenDialog() {
					popupModalDialog('${req.contextPath}/popup/spareSelector.html?repairSpare=true&&toolingDevFlag=${req.getParameter('toolingDevFlag')?if_exists}',800,600,desigerSelectorHandler);
				}
				function desigerSelectorHandler(result) {
				    document.forms["repairSpare"].elements["spare.id"].value = result[0];
				    document.forms["repairSpare"].elements["spare.name"].value = result[1];
				    document.forms["repairSpare"].elements["spare.spareNo"].value = result[2];
				    document.forms["repairSpare"].elements["spare.modelSpecs"].value = result[3];
				    document.forms["repairSpare"].elements["spare.category"].value = result[4];
				    document.forms["repairSpare"].elements["spare.unitPrice"].value = result[5];
		  		}
		  		function validate(){
		  			if (document.forms["repairSpare"].elements["spare.name"].value == '') {
				    	alert("${action.getText('select.spare.name')}");
				    	return false;
				    }
				    if(!(document.forms["repairSpare"].elements["repairSpare.planUsedNum"].value == '')) {
				    	var data = document.forms["repairSpare"].elements["repairSpare.planUsedNum"].value;
				    	if(!isNumber("repairSpare.planUsedNum")){
				    		alert("${action.getText('right.repairSpare.planUsedNum')}");
				    		return false;
				    	}
				    	if(!isValidLength(document.forms[0],"repairSpare.planUsedNum",0,9)){
				    		alert("${action.getText('repairSpare.planUsedNum.scope')}");
				    		return false;
				    	}
				    }
				    if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
				      if ('' != document.forms[0].elements["repairSpare.procUsedNum"].value) {
				        if(!isNumber("repairSpare.procUsedNum")){
				    		alert("${action.getText('right.repairSpare.procUsedNum')}");
				    		return false;
				    	}
				    	if(!isValidLength(document.forms[0],"repairSpare.procUsedNum",0,9)){
				    		alert("${action.getText('repairSpare.procUsedNum.scope')}");
				    		return false;
				    	}
				      }
				    }
		  		}
		</script>
	 </@ww.form>
</@htmlPage>