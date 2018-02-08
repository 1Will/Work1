<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('expenseForm.code')}'" name="'expenseForm.code'" value="'${req.getParameter('expenseForm.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('expenseForm.projectInfo.name')}'" name="'expenseForm.projectInfo.name'" value="'${req.getParameter('expenseForm.projectInfo.name')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('expenseForm.applyPeople.name')}'" name="'expenseForm.applyPeople.name'" value="'${req.getParameter('expenseForm.applyPeople.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('expenseForm.applyDate')}'" 
 				name="'expenseForm.applyDate'" 
			    value="'${req.getParameter('expenseForm.applyDate_start')?if_exists}|${req.getParameter('expenseForm.applyDate_end')?if_exists}'"
			    readonly="true"
			    cssClass="'underline'" 
			    maxlength="10"/>
	
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('签订日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("expenseForm.applyDate_start","expenseForm.applyDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('签订日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("expenseForm.applyDate_start","expenseForm.applyDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('expenseForm.applyDate_start').value>getObjByName('expenseForm.applyDate_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('expenseForm.applyDate_end').value="";
	       		getObjByName('expenseForm.applyDate_end').focus();
				return false;
		}
	
		return true;
    }
</script>