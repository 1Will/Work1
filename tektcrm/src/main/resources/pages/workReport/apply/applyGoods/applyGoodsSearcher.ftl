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

<#include "../../../includes/hco2011.ftl" />

<@inputTable>
	<#--<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>-->
	<tr>
 		<@ww.textfield label="'${action.getText('applyGoods.code')}'" name="'applyGoods.code'" value="'${req.getParameter('applyGoods.code')?if_exists}'" cssClass="'underline'" />
 		<@pp.dateRanger label="'${action.getText('applyGoods.crateDate')}'" 
 			name="'applyGoods.crateDate'" 
		    value="'${req.getParameter('applyGoods.crateDate_start')?if_exists}|${req.getParameter('applyGoods.crateDate_end')?if_exists}'"
			cssClass="'underline'" 
			maxlength="10"/> 
	</tr>
	<#--
	<tr>
		<@ww.textfield label="'${action.getText('applyGoods.applyPerson')}'" name="'applyGoods.applyPerson'" value="'${req.getParameter('applyGoods.applyPerson')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('applyGoods.dept')}'" 
			name="'dept.id'" 
			value="'${req.getParameter('dept.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allDepts"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	-->
	<tr>
		<@ww.select label="'${action.getText('applyGoods.goodsName')}'" 
			name="'goodsName.id'" 
			value="'${req.getParameter('goodsName.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allGoodsName"
			emptyOption="true" 
			disabled="false">
		</@ww.select>
		<@ww.select label="'${action.getText('applyGoods.purpose')}'" 
			name="'purpose.id'"
			value="'${req.getParameter('purpose.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allPurposes"
			emptyOption="true" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
		 <@ww.select label="'${action.getText('applyGoods.status')}'" 
			name="'status.id'" 
			value="'${req.getParameter('status.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allStatus"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
window.onload=function(){
<#--
		<#if req.getParameter('dept.id')?exists>
			getObjByName('dept.id').value='${req.getParameter('dept.id')?if_exists}';
		</#if>
-->		
		<#if req.getParameter('goodsName.id')?exists>
			getObjByName('goodsName.id').value='${req.getParameter('goodsName.id')?if_exists}';
		</#if>
		<#if req.getParameter('status.id')?exists>
			getObjByName('status.id').value='${req.getParameter('status.id')?if_exists}';
		</#if>
		<#if req.getParameter('purpose.id')?exists>
			getObjByName('purpose.id').value='${req.getParameter('purpose.id')?if_exists}';
		</#if>
		}
	function checkInvalidParms(){
	<#--
		if (getObjByName('dept.id').value==-1){
			getObjByName('dept.id').value='';
		}
		-->
		if (getObjByName('status.id').value==-1){
			getObjByName('status.id').value='';
		}
		
		var beginDateMsg="${action.getText('applyGoods.createDate.dateFormate.error')}";
	    if(queryDate("applyGoods.crateDate_start","applyGoods.crateDate_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('applyGoods.createDate.dateFormate.error')}";
	    if(queryDate("applyGoods.crateDate_start","applyGoods.crateDate_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
			
		return true;
    }
</script>