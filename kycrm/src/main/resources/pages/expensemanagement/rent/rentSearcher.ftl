<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('房租编码')}'" name="'rent.code'" value="'${req.getParameter('rent.code')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 				name="'rent.startTime'" 
			    value="'${req.getParameter('rent.startTime_start')?if_exists}|${req.getParameter('rent.startTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
			    
		<@pp.dateRanger label="'${action.getText('结束时间')}'" 
 				name="'rent.endTime'" 
			    value="'${req.getParameter('rent.endTime_start')?if_exists}|${req.getParameter('rent.endTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('开始日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("rent.startTime_start","rent.startTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    
	    var endDateMsg="${action.getText('结束日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("rent.endTime_start","rent.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    
	    if(getObjByName('rent.startTime_start').value>getObjByName('rent.startTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('rent.startTime_end').value="";
	       		getObjByName('rent.startTime_end').focus();
				return false;
		}
		
	    if(getObjByName('rent.endTime_start').value>getObjByName('rent.endTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('rent.endTime_end').value="";
	       		getObjByName('rent.endTime_end').focus();
				return false;
		}
		return true;
    }
</script>