<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('房间名称')}'" name="'house.name'" value="'${req.getParameter('house.name')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('房间编码')}'" name="'house.code'" value="'${req.getParameter('house.code')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 				name="'airFee.starTime'" 
			    value="'${req.getParameter('airFee.starTime_start')?if_exists}|${req.getParameter('airFee.starTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
			    
		<@pp.dateRanger label="'${action.getText('结束时间')}'" 
 				name="'airFee.endTime'" 
			    value="'${req.getParameter('airFee.endTime_start')?if_exists}|${req.getParameter('airFee.endTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('开始日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("airFee.starTime_start","airFee.starTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    
	    var endDateMsg="${action.getText('结束日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("airFee.endTime_start","airFee.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    
	    if(getObjByName('airFee.starTime_start').value>getObjByName('airFee.starTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('airFee.starTime_end').value="";
	       		getObjByName('airFee.starTime_end').focus();
				return false;
		}
		
	    if(getObjByName('airFee.endTime_start').value>getObjByName('airFee.endTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('airFee.endTime_end').value="";
	       		getObjByName('airFee.endTime_end').focus();
				return false;
		}
	
		return true;
    }
</script>