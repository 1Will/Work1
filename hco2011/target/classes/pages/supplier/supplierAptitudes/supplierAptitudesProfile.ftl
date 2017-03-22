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

<#--$Id: supplierAptitudeProfile.ftl 11326 2008-03-15 06:48:54Z wzou $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('supplierAptitudes.title')}">
     <@ww.form  name="'employeeProfile'" action="'saveSupplierAptitudes'" namespace="'/supplierAptitudes'" method="'post'" >
       <@ww.token name="saveSupplierAptitudesToken"/>
       		<#if supplierAptitudes.id?exists>
                <@ww.hidden name="'supplierAptitudes.id'" value="#{supplierAptitudes.id}"/>
            </#if>
            <#if supplier?exists>
            	<@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        	</#if>
         <@inputTable>
         	<tr>
             		<@ww.textfield label="'${action.getText('supplierAptitudes.name')}'" name="'supplierAptitudes.name'" value="'${supplierAptitudes.name?if_exists}'" cssClass="'underline'" required="true" />
            </tr>
         </@inputTable>
         <@buttonBar>
         	<#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        </#if>	          
	         <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
         </@buttonBar>	
     </@ww.form>
    
     <script language="javascript">
     	function validate(){
     		/*
     		*验证资质名称非空及长度溢出
     		*/
     		if('' == getObjByName('supplierAptitudes.name').value){
     			alert("${action.getText('supplierAptitudes.name.required')}");
     			return false;
     		}else{
     			if(!isValidLengthValue(getObjByName('supplierAptitudes.name').value,0,20)){
     				alert("${action.getText('supplierAptitudes.name.maxLength')}");
     				return false;
     			}
     		}
     	}
     </script>
</@htmlPage>
