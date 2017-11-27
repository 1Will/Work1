<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('水费编码')}'" name="'waterFee.code'" value="'${req.getParameter('waterFee.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('大楼名称')}'" name="'building.name'" value="'${req.getParameter('building.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 				name="'waterFee.starTime'" 
			    value="'${req.getParameter('waterFee.starTime_start')?if_exists}|${req.getParameter('waterFee.starTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
			    
		<@pp.dateRanger label="'${action.getText('结束时间')}'" 
 				name="'waterFee.endTime'" 
			    value="'${req.getParameter('waterFee.endTime_start')?if_exists}|${req.getParameter('waterFee.endTime_end')?if_exists}'"
			    readonly="false"
			    cssClass="'underline'" 
			    maxlength="10"/>
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('开始日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("waterFee.starTime_start","waterFee.starTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    
	    var endDateMsg="${action.getText('结束日期，时间格式不对，是：年-月-日')}";
	    if(queryDate("waterFee.endTime_start","waterFee.endTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    
	    if(getObjByName('waterFee.starTime_start').value>getObjByName('waterFee.starTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('waterFee.starTime_end').value="";
	       		getObjByName('waterFee.starTime_end').focus();
				return false;
		}
		
	    if(getObjByName('waterFee.endTime_start').value>getObjByName('waterFee.endTime_end').value){
				alert('${action.getText('结束时间不能小于开始时间')}');
				getObjByName('waterFee.endTime_end').value="";
	       		getObjByName('waterFee.endTime_end').focus();
				return false;
		}
	
		return true;
    }
</script>