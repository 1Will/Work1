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

<#-- $Id: productTypeSearcher.ftl 2009-11-27 10:20:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('productType.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
		
		<@ww.textfield label="'${action.getText('productType.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		<@ww.select 
    		label="'${action.getText('productType.parentType')}'"
			required="false"
			name="'parentPT.id'" 
			value="'${req.getParameter('parentPT.id')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allParentPT"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
	<tr>
	<@police_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	    var selector=document.all("parentPT.id");
	    var groups=selector.options.length;
	    for(i=0;i<groups;i++){
	      <#if req.getParameter('parentPT.id')?exists>
	        if(selector.options[i].value=="${req.getParameter('parentPT.id')?if_exists}"){
	           selector.options[i].selected="true";
	        }
	      </#if>
	    }
    function checkInvalidParms(){
           if (getObjByName("parentPT.id").value==-1){
              getObjByName("parentPT.id").value='';
           }
           if (getObjByName("parentPT.id").value==-2){
              getObjByName("parentPT.id").value='';
           }
          return true;
    }
</script>
