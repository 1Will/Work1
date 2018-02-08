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

<#-- $Id: deviceWasterStatisticsSearcher.ftl 2009-09-23 14:38:35Z wliu $ -->



<#include "../../../includes/eam2008.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('deviceWasterStatistics.deviceCard.deviceNo')}'" name="'deviceCard.deviceNo'" value="'${req.getParameter('deviceCard.deviceNo')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('deviceWasterStatistics.deviceCard.name')}'" name="'deviceCard.name'" value="'${req.getParameter('deviceCard.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
    <tr>
 		<@ww.select label="'${action.getText('deviceWasterStatistics.deviceType')}'"
 			name="'deviceType.id'" 
    		listKey="id" 
    		listValue="name"
    		list="allDeviceTypes" 
    		emptyOption="false" 
    		disabled="false">
        </@ww.select>
        <@ww.select label="'${action.getText('deviceWasterStatistics.filiale')}'" required="false" name="'filiale.id'" 
    			  value="'${req.getParameter('filiale.id')?if_exists}'" listKey="id" listValue="name"
                  list="allFiliales" emptyOption="false" disabled="false" onchange="'filialeSelectDeptDWR()'">
        </@ww.select>
        <#--	
        <@ww.select label="'${action.getText('deviceWasterStatistics.filiale')}'"
 			name="'filiale.id'" 
    		listKey="id" 
    		listValue="name"
    		list="allFiliales" 
    		emptyOption="false" 
    		disabled="false">
        </@ww.select>	
        -->
    </tr>
    <tr>
        <@ww.select label="'${action.getText('deviceWasterStatistics.department')}'"  name="'department.id'"  listKey="id" listValue="name"
    			 value="${req.getParameter('department.id')?if_exists}"
                list="allDepartments" emptyOption="false" disabled="false">
        </@ww.select>
        <#--
    	<@ww.select label="'${action.getText('deviceWasterStatistics.department')}'"
 			name="'department.id'" 
    		listKey="id" 
    		listValue="name"
    		list="allDepartments" 
    		emptyOption="false" 
    		disabled="false">
        </@ww.select>
        -->
    	<@pp.datePicker label="'${action.getText('deviceWasterStatistics.month')}'" 
        				name="'month'"
 						value="'${req.getParameter('month')?if_exists}'" 
 						cssClass="'underline'" 
 						size="15" 
 						dateFormat="'%Y-%m'"/>
    </tr>
    
</@inputTable>
<script language="javascript">
	var selector=$("deviceType.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
		<#if req.getParameter('deviceType.id')?exists>
        if(selector.options[i].value=="${req.getParameter('deviceType.id')?if_exists}"){
           selector.options[i].selected="true";
        }
		</#if>
    }
    var selector=$("filiale.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
		<#if req.getParameter('filiale.id')?exists>
        if(selector.options[i].value=="${req.getParameter('filiale.id')?if_exists}"){
           selector.options[i].selected="true";
        }
		</#if>
    }
    var selector=$("department.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
		<#if req.getParameter('department.id')?exists>
        if(selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
           selector.options[i].selected="true";
        }
		</#if>
    }
function checkInvalidParms()
{
	if ($("deviceType.id").value==-1){
			$("deviceType.id").value='';
	}
	if ($("filiale.id").value==-1){
			$("filiale.id").value='';
	}
	if ($("department.id").value==-1){
			$("department.id").value='';
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