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

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<#--<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>-->
	<tr>
		<#--
		<@ww.textfield label="'${action.getText('contractManagement.code')}'" name="'contractManagement.code'" value="'${req.getParameter('contractManagement.code')?if_exists}'" cssClass="'underline'"/>
		-->
		<@ww.textfield label="'${action.getText('billingRecord.info.code')}'" name="'billingRecord.myCode'" value="'${req.getParameter('billingRecord.myCode')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('billingRecord.contractManagement')}'" name="'billingRecord.contractManagement'" value="'${req.getParameter('billingRecord.contractManagement')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('项目名称')}'" name="'contractManagement.project.name'" value="'${req.getParameter('contractManagement.project.name')?if_exists}'" cssClass="'underline'"/>
		<#--
		<@ww.textfield label="'${action.getText('customerInfo.code')}'" name="'customerInfo.code'" value="'${req.getParameter('customerInfo.code')?if_exists}'" cssClass="'underline'"/>
		-->
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('billingRecord.customerInfo')}'" name="'billingRecord.customerInfo'" value="'${req.getParameter('billingRecord.customerInfo')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('billingRecord.code')}'" name="'billingRecord.code'" value="'${req.getParameter('billingRecord.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('billingRecord.payee')}'" name="'billingRecord.payee'" value="'${req.getParameter('billingRecord.payee')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@pp.dateRanger label="'${action.getText('billingRecord.billingTime')}'" 
 			name="'billingRecord.billingTime'" 
		    value="'${req.getParameter('billingRecord.billingTime_start')?if_exists}|${req.getParameter('billingRecord.billingTime_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('billingRecord.billingTime.dateFormate.error')}";
	    if(queryDate("billingRecord.billingTime_start","billingRecord.billingTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('billingRecord.billingTime.dateFormate.error')}";
	    if(queryDate("billingRecord.billingTime_start","billingRecord.billingTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('billingRecord.billingTime_start').value>getObjByName('billingRecord.billingTime_end').value){
				alert('${action.getText('billingRecord.billingTime.earlyError')}');
				getObjByName('billingRecord.billingTime_end').value="";
	       		getObjByName('billingRecord.billingTime_end').focus();
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
	   var url = "${req.contextPath}/customerRelationship/editCustomerInfo.html?customerInfo.id="+id+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag&readOnly=${req.getParameter('readOnly')?if_exists}";
	   openNewWindow(url);
	 }
	 
	 function editProjectInfo_OpenDialog(id){
	   var url= "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&openFlag=openFlag&notNewFlag=notNewFlag";
	   openNewWindow(url);
	 }
</script>