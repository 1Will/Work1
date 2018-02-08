<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: deviceOriginalNetStatisticsList.ftl 2009-09-22 15:28:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@inputTable>
    <tr>
    	<@pp.datePicker label="'${action.getText('deviceOriginalNetStatistics.month')}'" 
        				name="'month'"
 						value="'${req.getParameter('month')?if_exists}'" 
 						cssClass="'underline'" 
 						size="15" 
 						dateFormat="'%Y-%m'"/>
 		<@ww.select label="'${action.getText('deviceOriginalNetStatistics.deviceType')}'"  name="'deviceType.id'" 
    				listKey="id" listValue="name"
    			    list="allDeviceType" emptyOption="false" disabled="false">
        </@ww.select>				
    </tr>
    
</@inputTable>
<script language="javascript">
var selector=getObjByName("deviceType.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
		<#if req.getParameter('deviceType.id')?exists>
        if(selector.options[i].value=="${req.getParameter('deviceType.id')?if_exists}"){
           selector.options[i].selected="true";
        }
		</#if>
    }
function checkInvalidParms()
{
	if (getObjByNameRe("deviceType.id").value==-1){
		getObjByNameRe("deviceType.id").value='';
	}
	var monthValue = document.forms[0].elements["month"].value;
	if(monthValue != ''){
		if(!isMonth(monthValue)){
			alert("${action.getText('month.error')}");
			return false;
		}
	}
	return true;		
}
</script>