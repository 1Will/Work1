<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('preRepairPlanDetailFee.title')}">
 <base target="_self">
	 <@ww.form name="'detailFee'" action="'saveRepairFee'" method="'post'" validate="true">
	 	  <@ww.token name="saveRepairFeeToken"/>
	 	  <#if faultRepair.id?exists>
     	    <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     	  </#if>
     	  <#if repairFee.id?exists>
	 	    <@ww.hidden name="'repairFee.id'" value="'#{repairFee.id?if_exists}'"/>
	 	  </#if>
	 	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <@ww.hidden name="'oldPlanFee'" value="''"/>
	 	  <@ww.hidden name="'oldProcFee'" value="''"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('repairFee.feeItem')}'" name="'repairFee.feeItem'" value="'${repairFee.feeItem?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	 	  		<@ww.textfield label="'${action.getText('repairFee.procFee')}'" name="'repairFee.procFee'" value="'${repairFee.procFee?if_exists}'" cssClass="'underline'" disabled="false"/>
		    </tr>
	 	  	<tr>	
	 	  		<@ww.textarea label="'${action.getText('repairFee.comment')}'" 
					         name="'repairFee.comment'" 
					         value="'${repairFee.comment?if_exists}'" rows="'3'" cols="'50'" 
					         disabled="false"
							 />
	 	  	</tr> 
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	      </@buttonBar>
		<script language="javascript">
			window.onload = function(){
		        document.forms[0].elements["oldPlanFee"].value = formatDigital(document.forms[0].elements["repairFee.procFee"].value);
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
	     		if(!(document.forms["detailFee"].elements["repairFee.procFee"].value=='')) {
	     		var data = document.forms["detailFee"].elements["repairFee.procFee"].value;
		      		if(!isDoubleNumber("repairFee.procFee")){
			    		alert("${action.getText('right.repairFee.procFee')}");
			    		return false;
				    } else if(!isDoubleNumberBetweenBoolean(document.forms[0].elements["repairFee.procFee"].value, 1000000001, 0)){
			    		alert("${action.getText('right.repairFee.procFee')}");
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