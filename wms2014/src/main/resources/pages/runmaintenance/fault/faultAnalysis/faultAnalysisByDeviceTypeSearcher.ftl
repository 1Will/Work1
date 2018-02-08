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

<#-- $Id: faultAnalysisByDeviceTypeSearcher.ftl 11122 2009-09-11 14:05:35Z wliu $ -->

<@inputTable>
    <tr>
    	<@ww.select 
    		label="'${action.getText('faultAnalysisByDeviceType.deviceType')}'"
			required="false"
			name="'deviceTypeId'" 
			value="'${req.getParameter('deviceTypeId')?if_exists}'" 
			listKey="id" 
			listValue="name"
		    list="allDeviceTypes"
		    emptyOption="false" 
		    disabled="false"/>
        <@pp.datePicker 
        	label="'${action.getText('faultAnalysisByDeviceType.month')}'" 
			name="'month'"
			value="'${req.getParameter('month')?if_exists}'" 
			cssClass="'underline'" 
			size="15" 
			dateFormat="'%Y-%m'"/>
    </tr>
    
</@inputTable>
<script language="javascript">
	var selector=document.all("deviceTypeId");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
		<#if req.getParameter('deviceTypeId')?exists>
        if(selector.options[i].value=="${req.getParameter('deviceTypeId')?if_exists}"){
           selector.options[i].selected="true";
        }
		</#if>
    }
function checkInvalidParms()
{
	if (getObjByNameRe("deviceTypeId").value==-1){
			getObjByNameRe("deviceTypeId").value='';
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
