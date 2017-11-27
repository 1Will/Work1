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
 		<@ww.textfield label="'${action.getText('contractManagement.code')}'" name="'contractManagement.code'" value="'${req.getParameter('contractManagement.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('returnPlan.contractManagement')}'" name="'returnPlan.contractManagement'" value="'${req.getParameter('returnPlan.contractManagement')?if_exists}'" cssClass="'underline'"/>
		
		<@ww.select 
		label="'${action.getText('收款类型')}'"
			required="false"
			name="'mold.id'" 
			value="${req.getParameter('mold.id')?if_exists}" 
			listKey="id"
			listValue="name"
		    list="allMold"
	    	emptyOption="false" 
	    	disabled="false">
    	</@ww.select>
		<script language="javascript">
			<#if req.getParameter('mold.id')?exists>
				getObjByName('mold.id').value = '${req.getParameter('mold.id')?if_exists}';
			</#if>
		</script>
    	<#--
		<@ww.textfield label="'${action.getText('customerInfo.code')}'" name="'customerInfo.code'" value="'${req.getParameter('customerInfo.code')?if_exists}'" cssClass="'underline'"/>
    	-->
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('customerInfo.name')}'" name="'customerInfo.name'" value="'${req.getParameter('customerInfo.name')?if_exists}'" cssClass="'underline'"/>
		
		<@pp.dateRanger label="'${action.getText('returnPlan.planDate')}'" name="'returnPlan.planDate'" 
		    value="'${req.getParameter('returnPlan.planDate_start')?if_exists}|${req.getParameter('returnPlan.planDate_end')?if_exists}'"
			cssClass="'underline'" maxlength="10"/>
			
		<@pp.dateRanger label="'${action.getText('returnPlan.paytime')}'" name="'returnPlan.paytime'" 
		    value="'${req.getParameter('returnPlan.paytime_start')?if_exists}|${req.getParameter('returnPlan.paytime_end')?if_exists}'"
			cssClass="'underline'" maxlength="10"/> 
			
	</tr>
	<tr>
			<td align="right"><label for="" class="label">${action.getText('returnPlan.isOrNot')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="isOrNot0" name="isOrNot" value="0" />是
	        	<input type="radio" id="isOrNot1" name="isOrNot" value="1" />否
			</td>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	function checkInvalidParms(){
		var beginDateMsg="${action.getText('returnPlan.paytime.dateFormate.error')}";
	    if(queryDate("returnPlan.paytime_start","returnPlan.paytime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('returnPlan.paytime.dateFormate.error')}";
	    if(queryDate("returnPlan.paytime_start","returnPlan.paytime_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
	    if(getObjByName('returnPlan.paytime_start').value>getObjByName('returnPlan.paytime_end').value){
				alert('${action.getText('returnPlan.paytime.earlyError')}');
	       		getObjByName('returnPlan.paytime_end').focus();
				return false;
			}
			
		var beginDateMsg="${action.getText('returnPlan.paytime.dateFormate.error')}";
	    if(queryDate("returnPlan.planDate_start","returnPlan.planDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('returnPlan.paytime.dateFormate.error')}";
	    if(queryDate("returnPlan.planDate_start","returnPlan.planDate_end",
	       endDateMsg,null)==false){  
	   	   return false;
	    }
	    if(getObjByName('returnPlan.planDate_start').value>getObjByName('returnPlan.planDate_end').value){
				alert('${action.getText('returnPlan.paytime.earlyError')}');
	       		getObjByName('returnPlan.planDate_end').focus();
				return false;
			}
		return true;
    }
    
	function newFinancialManagement_OpenDialog(id){
	    var url = "${req.contextPath}/financialManagement/editFinancialManagement.html?returnPlan.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	    openNewWindow(url);
    }
    
		function newBillingRecord_OpenDialog(id){
	    var url = "${req.contextPath}/contractManagement/editBillingRecord.html?returnPlan.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	    openNewWindow(url);
    }
    
	function open_detail(code,cmid){
		var url = "${req.contextPath}/productList/listHouseList.html?contractManagement.id="+cmid+"&houseCode="+code+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	    popupModalDialog(url, 1000, 600)
    }
    
	function open_waterDetail(cid,date){
		var url = "${req.contextPath}/fee/listExpenseTab.html?customerInfo.id="+cid+"&expense.endTime_start="+date+"&expense.endTime_end="+date+"&eadOnly=${req.getParameter('readOnly')?if_exists}";
	    popupModalDialog(url, 1000, 600);
	    //openNewWindow(url);
    }
    
</script>