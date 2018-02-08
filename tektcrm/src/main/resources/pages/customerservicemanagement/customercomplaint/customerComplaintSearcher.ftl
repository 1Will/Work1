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

<#-- $Id: customerInfoSearcher.ftl 2009-12-10 15:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
	<@ww.textfield label="'${action.getText('编号')}'" name="'customerComplaint.code'" value="'${req.getParameter('customerComplaint.code')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('投诉主题')}'" name="'customerComplaint.complaintTitle'" value="'${req.getParameter('customerComplaint.complaintTitle')?if_exists}'" cssClass="'underline'" />
 	<@pp.dateRanger label="'${action.getText('开始时间')}'" 
 						name="'customerComplaint.startTime'" 
			            value="'${req.getParameter('customerComplaint.startTime_start')?if_exists}|${req.getParameter('customerComplaint.startTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            maxlength="10"/>
	</tr>
	<tr>
	<@ww.select label="'${action.getText('投诉类型')}'" 
				id="complaintType"
				name="'customerComplaint.complaintType.id'" 
				value="'${req.getParameter('customerComplaint.complaintType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allComplaintType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
	<@ww.select label="'${action.getText('紧急程度')}'" 
				id="urgencyDegree"
				name="'customerComplaint.urgencyDegree.id'" 
				value="'${req.getParameter('customerComplaint.urgencyDegree.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allUrgencyDegree"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
	
	<#--<@ww.textfield label="'${action.getText('投诉类型')}'" name="'customerComplaint.complaintType'" value="'${req.getParameter('customerComplaint.complaintType')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('紧急程度')}'" name="'customerComplaint.urgencyDegree'" value="'${req.getParameter('customerComplaint.urgencyDegree')?if_exists}'" cssClass="'underline'" />
		--><@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
		
	function checkInvalidParms(){
	var beginDateMsg="${action.getText('时间格式不对，是：年-月-日')}";
	    if(queryDate("customerComplaint.startTime_start","customerComplaint.startTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('时间格式不对，是：年-月-日')}";
	    if(queryDate("customerComplaint.startTime_start","customerComplaint.startTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('customerComplaint.startTime_start').value>getObjByName('customerComplaint.startTime_end').value){
				alert('${action.getText('开始时间要在结束时间之前!')}');
				getObjByName('customerComplaint.startTime_end').value="";
	       		getObjByName('customerComplaint.startTime_end').focus();
				return false;
		}
		return true;
    }
    getObjByName(function(){
    	<#if req.getParameter('customerComplaint.complaintType.id')?exists>
    		getObjByName("complaintType").value ="${req.getParameter('customerComplaint.complaintType.id')?if_exists}";
    	</#if>
    	<#if req.getParameter('customerComplaint.urgencyDegree.id')?exists>
    		getObjByName("urgencyDegree").value ="${req.getParameter('customerComplaint.urgencyDegree.id')?if_exists}";
    	</#if>
    	
    });
</script>