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

<#-- $Id: productSearcher.ftl 2009-11-27 10:50:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('products.code')}'" name="'products.code'" value="'${req.getParameter('products.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('products.name')}'" name="'products.name'" value="'${req.getParameter('products.name')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('products.model')}'" name="'products.model'" value="'${req.getParameter('products.model')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('products.standard')}'" name="'products.standard'" value="'${req.getParameter('products.standard')?if_exists}'" cssClass="'underline'"/>
		<@ww.select 
    		label="'${action.getText('products.pt.id')}'"
			required="false"
			name="'pt.id'" 
			value="'${req.getParameter('pt.id')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allType"
	    	emptyOption="false" 
	    	disabled="false"/>
		<@ww.select 
    		label="'${action.getText('products.productSource')}'"
			required="false"
			name="''" 
			value="'${req.getParameter('')?if_exists}'" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'自主研发',
				'代理',
				'外包'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
	<tr>
		<@ww.select 
    		label="'${action.getText('products.supplier')}'"
			required="false"
			name="''" 
			value="'${req.getParameter('')?if_exists}'" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'所有',
				'A供应商',
				'B供应商'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	<@police_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
    var selector=document.all("pt.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('pt.id')?exists>
        if(selector.options[i].value=="${req.getParameter('pt.id')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    function checkInvalidParms(){
           if (document.getElementById("pt.id").value==-1){
              document.getElementById("pt.id").value='';
           }
          return true;
    }
         
</script>