<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('账单编码')}'" name="'expense.code'" value="'${req.getParameter('expense.code')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 				name="'expense.startTime'" 
			    value="'${req.getParameter('expense.startTime_start')?if_exists}|${req.getParameter('expense.startTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
			    
		<@pp.dateRanger label="'${action.getText('结束时间')}'" 
 				name="'expense.endTime'" 
			    value="'${req.getParameter('expense.endTime_start')?if_exists}|${req.getParameter('expense.endTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('开始日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("expense.startTime_start","expense.startTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    
	    var endDateMsg="${action.getText('结束日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("expense.endTime_start","expense.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    
	    if(getObjByName('expense.startTime_start').value>getObjByName('expense.startTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('expense.startTime_end').value="";
	       		getObjByName('expense.startTime_end').focus();
				return false;
		}
		
	    if(getObjByName('expense.endTime_start').value>getObjByName('expense.endTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('expense.endTime_end').value="";
	       		getObjByName('expense.endTime_end').focus();
				return false;
		}
		return true;
    }
</script>