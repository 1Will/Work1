<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: payDetailProfile.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('sparePart.title')}">
<base target="_self">
	
	<@ww.form namespace="'/tooling/acceptBillSelector'" name="'sparePart'" action="'saveSparePart'" method="'post'" validate="true">
		<@ww.token name="savePayDetailToken"/>
		<#if acceptBill.id?exists>
			<@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
		</#if>
		<#if sparePart.id?exists>
			<@ww.hidden name="'sparePart.id'" value="#{sparePart.id}"/>
		</#if>
		<@inputTable>
			<tr>
			 <@ww.textfield label="'${action.getText('spareName')}'" name="'sparePart.spareName'" value="'${sparePart.spareName?if_exists}'"  cssClass="'underline'" required="true" />
			 <@ww.textfield label="'${action.getText('spareModel')}'"  name="'sparePart.spareModel'" value="'${sparePart.spareModel?if_exists}'" cssClass="'underline'"/>
			</tr>
			<tr>
			 <@ww.textfield label="'${action.getText('spareSpecification')}'" name="'sparePart.spareSpecification'" value="'${sparePart.spareSpecification?if_exists}'"  cssClass="'underline'"/>
			 <@ww.textfield label="'${action.getText('amount')}'"  name="'sparePart.amount'" value="'${sparePart.amount?if_exists}'" cssClass="'underline'" />
			</tr>
			<tr>
			<@ww.textfield label="'${action.getText('installPosition')}'"  name="'sparePart.installPosition'" value="'${sparePart.installPosition?if_exists}'" cssClass="'underline'"/>
			<td align="right" valign="top"><label class="label">${action.getText('sparePart.damage')}:</label></td>	
		  	<td>
		  		<select name="sparePart.damage">
     			<option value="N">${action.getText('no')}</option>
     			<option value="Y">${action.getText('yes')}</option>
	      	</select>
	      	</td>
          	<script language="javascript">
            	<#if (sparePart.damage)>
         	  	document.forms[0].elements["sparePart.damage"].value = 'Y';
         		<#else>
         	  	document.forms[0].elements["sparePart.damage"].value = 'N';
         		</#if>
          	</script>
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateInsepect()'"/>
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		
	</@ww.form>
	<script language="javascript">	
	function validateInsepect(){
	if(getObjByNameRe("sparePart.spareName").value==''){
	    alert('${action.getText('sparePart.spareName')}');
	    return false;
	  }else{
	      if(!isValidLength(document.forms[0], "sparePart.spareName", null, 50)){
				alert("${action.getText('sparePart.spareName.MaxLength')}");
				return  false;
			   }   
	    }
   if(getObjByNameRe("sparePart.spareModel").value!='')	{
	if(!isValidLength(document.forms[0], "sparePart.spareModel", null, 50)){
			alert("${action.getText('sparePart.spareModel.length')}");
			return  false;
			   }
	}	
	if(getObjByNameRe("sparePart.spareSpecification").value!='')	{	   
	 if(!isValidLength(document.forms[0], "sparePart.spareSpecification", null, 50)){
			alert("${action.getText('sparePart.spareSpecification.length')}");
			return  false;
			   }
     }			   
	 //验证数量是否为空,以及格式
       var number = document.forms[0].elements["sparePart.amount"].value;
       if( document.forms[0].elements["sparePart.amount"].value!=''){
          if(!isDoubleNumber("sparePart.amount")){
            alert("${action.getText('format.error')}");
            return false;
          }else{
              if(!isNumberBetweenBoolen(number, 10000000001, 0)){
               alert("${action.getText('sparePart.amount.maxLength')}");
                return false;
              }
          }
       }
     
     if(getObjByNameRe("sparePart.installPosition").value!='')	{	  	
       if(!isValidLength(document.forms[0], "sparePart.installPosition", null, 50)){
			alert("${action.getText('sparePart.installPosition.length')}");
			return  false;
			   }	
	}
	  return true;
	}
	</script>
</@htmlPage>