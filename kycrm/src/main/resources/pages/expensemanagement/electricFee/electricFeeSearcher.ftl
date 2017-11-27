<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('电费编码')}'" name="'electricFee.code'" value="'${req.getParameter('electricFee.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('大楼名称')}'" name="'building.name'" value="'${req.getParameter('building.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 				name="'electricFee.starTime'" 
			    value="'${req.getParameter('electricFee.starTime_start')?if_exists}|${req.getParameter('electricFee.starTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
			    
		<@pp.dateRanger label="'${action.getText('结束时间')}'" 
 				name="'electricFee.endTime'" 
			    value="'${req.getParameter('electricFee.endTime_start')?if_exists}|${req.getParameter('electricFee.endTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('开始日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("electricFee.starTime_start","electricFee.starTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    
	    var endDateMsg="${action.getText('结束日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("electricFee.endTime_start","electricFee.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    
	    if(getObjByName('electricFee.starTime_start').value>getObjByName('electricFee.starTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('electricFee.starTime_end').value="";
	       		getObjByName('electricFee.starTime_end').focus();
				return false;
		}
		
	    if(getObjByName('electricFee.endTime_start').value>getObjByName('electricFee.endTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('electricFee.endTime_end').value="";
	       		getObjByName('electricFee.endTime_end').focus();
				return false;
		}
	
		return true;
    }
</script>