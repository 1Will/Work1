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
 		<@ww.textfield label="'${action.getText('contactArchives.name')}'" name="'contactArchives.name'" value="'${req.getParameter('contactArchives.name')?if_exists}'" cssClass="'underline'" />
		<#--
		<@ww.select label="'${action.getText('contactArchives.sex')}'" 
			name="'contactArchives.sex'" 
			value="'${req.getParameter('contactArchives.sex')?if_exists}'"
			headerKey="id"
			headerValue="name"
			list="{
				'${action.getText('select.option.all')}',
				'${action.getText('contactArchives.man')}',
				'${action.getText('contactArchives.women')}'
			}"
			emptyOption="false" 
			disabled="false" >
		</@ww.select>
		-->
		<@ww.textfield label="'${action.getText('contactArchives.customerName')}'" name="'contactArchives.customerName'" value="'${req.getParameter('contactArchives.customerName')?if_exists}'" cssClass="'underline'" />
		
	</tr>
	<tr>
	<@ww.textfield label="'${action.getText('contactArchives.abbreviations')}'" name="'contactArchives.abbreviations'" value="'${req.getParameter('contactArchives.abbreviations')?if_exists}'" cssClass="'underline'" required="false" />
	<@ww.select label="'${action.getText('contactArchives.customerType')}'" 
			name="'type.id'" 
			value="'${req.getParameter('type.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allTypes"
			emptyOption="false" 
			disabled="false">
		</@ww.select>	
	</tr>
	<tr>
	<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
		//var sexSele=document.all("contactArchives.sex");
		var typeSele=document.all("type.id");
	    //var sexGroups=sexSele.options.length;
	    var typeGroups=typeSele.options.length;
	    <#--
	    for(i=0;i<sexGroups;i++){
			<#if req.getParameter('contactArchives.sex')?exists>
	        if(sexSele.options[i].value=="${req.getParameter('contactArchives.sex')?if_exists}"){
	           sexSele.options[i].selected="true";
	        }
			</#if>
	    }
	    -->
	    for(i=0;i<typeGroups;i++){
			<#if req.getParameter('type.id')?exists>
	        if(typeSele.options[i].value=="${req.getParameter('type.id')?if_exists}"){
	           typeSele.options[i].selected="true";
	        }
			</#if>
	    }
	function checkInvalidParms(){
		
		<#--if (getObjByName('contactArchives.sex').value=="${action.getText('select.option.all')}"){
			getObjByName('sex').value='';
		}elseif(getObjByName('contactArchives.sex').value=="${action.getText('contactArchives.man')}"){
			getObjByName('sex').value=false;
		}else if(getObjByName('contactArchives.sex').value=="${action.getText('contactArchives.women')}"){
			getObjByName('sex').value=true;
		}
		-->
		getObjByName('customerIsNotNull').value=true;
		if (getObjByName('type.id').value==-1){
			getObjByName('type.id').value='';
		}
		return true;
    }
</script>