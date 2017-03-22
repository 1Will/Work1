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

<#-- $Id: institutionSearcher.ftl 2009-11-02 11:44:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />
<@inputTable>
	<@ww.hidden name="'noParent'" value="${req.getParameter('noParent')?if_exists}"/>
	<tr>
		<@ww.textfield label="'${action.getText('institution.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('institution.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
     	<#assign showNone=""/>
        <#assign showNone="${req.getParameter('noParent')?if_exists}"/>
		<@ww.select label="'${action.getText('institution.parentInst')}'" 
			name="'parentInst.id'" 
			value="'${req.getParameter('parentInst.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allParentInsts"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
    <tr>
    	<@ww.textfield label="'${action.getText('institution.lader')}'" name="'lader'" value="'${req.getParameter('lader')?if_exists}'" cssClass="'underline'"/>
		<@police_onlySearchInvalid_checkBox />
    </tr>
</@inputTable>
<script language="javascript">
<#-- 
   window.onload = function () {
		alert(${req.getParameter('noParent')?if_exists});
   }	
-->

	<#if showNone != 'true'>
		var selector=document.all("parentInst.id");
	    var groups=selector.options.length;
	    for(i=0;i<groups;i++){
			<#if req.getParameter('parentInst.id')?exists>
	        if(selector.options[i].value=="${req.getParameter('parentInst.id')?if_exists}"){
	           selector.options[i].selected="true";
	        }
			</#if>
	    }
	<#else>
		getObjByName('parentInst.id').value = -2 ;
		var selector=document.all("parentInst.id");
	    var groups=selector.options.length;
	    for(i=0;i<groups;i++){
			<#if req.getParameter('parentInst.id')?exists>
	        if(selector.options[i].value=="${req.getParameter('parentInst.id')?if_exists}"){
	           selector.options[i].selected="true";
	        }
			</#if>
	    }	
	</#if>
	function checkInvalidParms(){
		getObjByName('noParent').value = '';
		if (getObjByName('parentInst.id').value==-1){
			getObjByName('parentInst.id').value='';
		}else if(getObjByName('parentInst.id').value==-2){
			getObjByName('noParent').value='true'
			getObjByName('parentInst.id').value='false';
		}
		return true;
    }
  
</script>