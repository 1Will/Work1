<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: contactArchivesSearcher.ftl 2009-12-08 11:00:35Z wliu $ -->

<#include "../includes/hco2011.ftl" />

<@inputTable>
	<#--<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>-->
	<tr>
 		<@ww.textfield label="'${action.getText('financialManagement.code')}'" name="'financialManagement.code'" value="'${req.getParameter('financialManagement.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('financialManagement.contractManagement')}'" name="'financialManagement.contractManagement'" value="'${req.getParameter('financialManagement.contractManagement')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('financialManagement.contractManagement.projectInfo.name')}'" name="'projectInfo.name'" value="'${req.getParameter('projectInfo.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('financialManagement.customerInfo')}'" name="'financialManagement.customerInfo'" value="'${req.getParameter('financialManagement.customerInfo')?if_exists}'" cssClass="'underline'"/>
		<#--
		<@ww.textfield label="'${action.getText('financialManagement.saleman')}'" name="'financialManagement.saleman'" value="'${req.getParameter('financialManagement.saleman')?if_exists}'" cssClass="'underline'"/>
		-->
		
		<@pp.dateRanger label="'${action.getText('financialManagement.collectionDate')}'" 
 			name="'financialManagement.collectionDate'" 
		    value="'${req.getParameter('financialManagement.collectionDate_start')?if_exists}|${req.getParameter('financialManagement.collectionDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
		<@ww.textfield label="'${action.getText('financialManagement.payee')}'" name="'financialManagement.payee'" value="'${req.getParameter('financialManagement.payee')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('financialManagement.collectionDate.dateFormate.error')}";
	    if(queryDate("financialManagement.collectionDate_start","financialManagement.collectionDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('financialManagement.collectionDate.dateFormate.error')}";
	    if(queryDate("financialManagement.collectionDate_start","financialManagement.collectionDate_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
		return true;
    }
    
    function contractManagement_OpenDialog(id){
	   var url = "${req.contextPath}/contractManagement/editContractManagementAction.html?contractManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	   openNewWindow(url);
	 }
	 
	  //弹出客户档案查询模态窗体
	function customer_OpenDialog(id){
	   var url = "${req.contextPath}/customerRelationship/editCustomerInfo.html?customerInfo.id="+id+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   openNewWindow(url);
	 }
	 
	 function editProjectInfo_OpenDialog(id){
	   var url= "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&openFlag=openFlag&notNewFlag=notNewFlag";
	   openNewWindow(url);
	 }
</script>