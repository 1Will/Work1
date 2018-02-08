<#include "../../includes/macros.ftl" />
<@inputTable>
<tr>
<@ww.textfield label="'${action.getText('证书名称')}'" name="'qualification.name'" value="'${req.getParameter('qualification.name')?if_exists}'" cssClass="'underline'"/>
</tr>
<tr>
	<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 			name="'qualification.starTime'" 
		    value="'${req.getParameter('qualification.starTime_start')?if_exists}|${req.getParameter('qualification.starTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
	<@pp.dateRanger label="'${action.getText('结束时间')}'"
 			name="'qualification.endTime'" 
		    value="'${req.getParameter('qualification.endTime_start')?if_exists}|${req.getParameter('qualification.endTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
</tr>
</@inputTable>
<script>

	function checkInvalidParms() {
  
	    var beginDateMsg="${action.getText('请输入正确的开始时间')}";
	    if(queryDate("qualification.starTime_start","qualification.starTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	
	    
	    if(getObjByName('qualification.starTime_start').value>getObjByName('qualification.starTime_end').value){
				alert('${action.getText('开始时间区域有误')}');
				getObjByName('qualification.starTime_end').value="";
				getObjByName('qualification.starTime_end').focus();
				return false;
		}
	    
	    var endDateMsg="${action.getText('请输入正确的结束时间')}";
	    if(queryDate("qualification.endTime_start","qualification.endTime_end",
	       endDateMsg,null)==false){
	   	   return false;
	    }	
	    
	    if(getObjByName('qualification.endTime_start').value>getObjByName('qualification.endTime_end').value){
				alert('${action.getText('结束时间区域有误')}');
				getObjByName('qualification.endTime_start').value="";
	       		getObjByName('qualification.endTime_start').focus();
				return false;
		}
    	return true;
	}
</script>