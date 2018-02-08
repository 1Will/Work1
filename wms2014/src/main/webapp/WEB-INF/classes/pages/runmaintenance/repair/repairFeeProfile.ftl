<#include "../../includes/eam2008.ftl" />
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign disabled = 'false'/>
	<#else>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${action.getText('preRepairPlanDetailFee.title')}">
 <base target="_self">
	 <@ww.form name="'detailFee'" action="'saveRepairFee'" method="'post'" validate="true">
	 	  <@ww.token name="savePreRepairPlanDetailFeeToken"/>
          <@ww.hidden name="'preRepairPlan.id'" value="'${req.getParameter('preRepairPlan.id')?if_exists}'"/>
          <@ww.hidden name="'repairPlanOrProc.id'" value="'${req.getParameter('repairPlanOrProc.id')?if_exists}'"/>
	 	  <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	</#if>
	 	  <@ww.hidden name="'repairFee.id'" value="'${req.getParameter('repairFee.id')?if_exists}'"/>
	 	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <@ww.hidden name="'oldPlanFee'" value="''"/>
	 	  <@ww.hidden name="'oldProcFee'" value="''"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('repairFee.feeItem')}'" name="'repairFee.feeItem'" value="'${repairFee.feeItem?if_exists}'" cssClass="'underline'" required="true" disabled="${disabled}"/>
	 	  		<@ww.textfield label="'${action.getText('repairFee.planFee')}'" name="'repairFee.planFee'" value="'${repairFee.planFee?if_exists}'" cssClass="'underline'" disabled="${disabled}"/>
		    </tr>
	 	  	<tr>	
	 	  		<@ww.textarea label="'${action.getText('repairFee.comment')}'" 
					         name="'repairFee.comment'" 
					         value="'${repairFee.comment?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="${disabled}"
							 />
	 	  	</tr> 
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	      </@buttonBar>
		<script language="javascript">
			window.onload = function(){
				if (document.forms[0].elements["planProcFlag"].value == 'PROC') {
		            document.forms[0].elements["oldProcFee"].value = formatDigital(document.forms[0].elements["repairFee.procFee"].value);
		          }
		        document.forms[0].elements["oldPlanFee"].value = formatDigital(document.forms[0].elements["repairFee.planFee"].value);
			}
			function validate(){
				if(!repairFee_feeItem()) {
					return false;
				}
				if(!repairFee_planFee()) {
					return false;
				}
				if(!repairFee_comment()) {
					return false;
				}
			}
	     	function repairFee_feeItem() {
	     		if(document.forms["detailFee"].elements["repairFee.feeItem"].value=='') {
	     			alert("${action.getText('input.repairFee.feeItem')}");
		      		return false;
	     		}
	     		var name = document.forms["detailFee"].elements["repairFee.feeItem"].value;
	     		if(!(name=='')){
				  	if(!isValidLength(document.forms[0], "repairFee.feeItem", null, 50)){
				  		alert("${action.getText('repairFee.feeItem.length')}");
				  		return  false;
				  	}
				}
	     		return true;
	     	}
	     	function repairFee_planFee() {
	     		if(!(document.forms["detailFee"].elements["repairFee.planFee"].value=='')) {
	     		  var data = document.forms["detailFee"].elements["repairFee.planFee"].value;
			      if(!isDoubleNumberBetweenBoolean(data,10000000001,0)){
			        alert("${action.getText('repairFee.planFee.scope')}");
			        return false;
			      }
			    }
			    return true;
	     	}
	     	function repairFee_comment(){
	     		var name = document.forms["detailFee"].elements["repairFee.comment"].value;
			    if(!(name=='')){
				  	if(!isValidLength(document.forms[0], "repairFee.comment", null, 250)){
				  		alert("${action.getText('repairFee.comment.length')}");
				  		return  false;
				  	}
				}
			return true;
	     	}
		</script>
	 </@ww.form>
</@htmlPage>