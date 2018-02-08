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

<#--$Id: codeValueProfile.ftl 8737 2007-11-28 11:15:51Z qsun $ -->

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('financeTypeSearcher.title')}">
     <@ww.form  name="'code'" action="'saveFinanceType'" method="'post'" validate="true">
       <@ww.token name="saveFinanceTypeToken"/>
       <#if financeType?exists>
         <@ww.hidden name="'financeType.id'" value="${financeType.id?if_exists}"/>
       </#if>
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('financeType.code')}'" name="'financeType.code'" value="'${financeType.code?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${action.getText('financeType.name')}'" name="'financeType.name'" value="'${financeType.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr> 
             <tr>
                 <@ww.textfield label="'${action.getText('financeType.deprecitionYearLimit')}'" name="'financeType.deprecitionYearLimit'" value="'${financeType.deprecitionYearLimit?if_exists}'" required="false" cssClass="'underline'"/>
                 <@ww.textfield label="'${action.getText('financeType.yearDeprecitionScale')}'" name="'financeType.yearDeprecitionScale'" value="'${financeType.yearDeprecitionScale?if_exists}'" required="false" cssClass="'underline'"/>
             </tr> 
             <tr>
                 <@ww.textfield label="'${action.getText('financeType.netSalvageScale')}'" name="'financeType.netSalvageScale'" value="'${financeType.netSalvageScale?if_exists}'" cssClass="'underline'" required="false"/>
                 <@ww.textarea  label="'${action.getText('financeType.comment')}'" 
	        	                name="'financeType.comment'" required="false"
	        	                value="'${financeType.comment?if_exists}'"  
	        	                rows="3" cols="50" cssClass="'underline'" disabled="false"/>
             </tr>             
         </@inputTable>
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>	          
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/financeType/listFinanceTypes.html"/>
         </@buttonBar>
     </@ww.form>
     <script language="javascript" type="text/JavaScript">
       function customize_validate() {
       <#if financeType.new>
       	 if ('' != document.forms[0].elements["financeType.code"].value){
       	 	var typeCode = document.forms[0].elements["financeType.code"].value;
       	 	<#list allCode as codes>
       	 		if('${codes}' == typeCode){
       	 			alert("${action.getText('financeType.code.repeat')}");
             		return false;
       	 		}
       	 	</#list>
       	 }
       	</#if> 
       	 
       	 if(isNotEmpty(code,"financeType.code")) {
			if (!isValidLength(document.forms[0],"financeType.code",0,50)){
				alert("${action.getText('financeType.code.maxLength')}");
				return false;
		    }
		 } else {
			alert("${action.getText('financeType.code.requiredstring')}");
			return false;
		 }
		if(isNotEmpty(code,"financeType.name")) {
			if (!isValidLength(document.forms[0],"financeType.name",0,50)){
				alert("${action.getText('financeType.name.maxLength')}");
				return false;
		    }
		 } else {
			alert("${action.getText('financeType.name.requiredstring')}");
			return false;
		 }
		if(isNotEmpty(code,"financeType.deprecitionYearLimit")) {
		      if (!isNumber("financeType.deprecitionYearLimit")) {
				alert("${action.getText('financeType.deprecitionYearLimit.isNotNumber')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["financeType.deprecitionYearLimit"].value, 1000001, 0)){	//最大范围要加1，例：价格最大为1000000，在此要设为1000001。以保证取得最大值。
				alert("${action.getText('financeType.deprecitionYearLimit.maxLength')}");
				return false;
		      }
		}
        if(isNotEmpty(code,"financeType.yearDeprecitionScale")) {
				if (!isDoubleNumber("financeType.yearDeprecitionScale")){
					alert("${action.getText('financeType.yearDeprecitionScale.isNotNumber')}");
					return false;
				} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["financeType.yearDeprecitionScale"].value,1.001, 0.000)){  //验证范围
					alert("${action.getText('financeType.yearDeprecitionScale.maxLength')}");
					return false;
				}
			}
		 if(isNotEmpty(code,"financeType.netSalvageScale")) {
				if (!isDoubleNumber("financeType.netSalvageScale")){
					alert("${action.getText('financeType.netSalvageScale.isNotNumber')}");
					return false;
				} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["financeType.netSalvageScale"].value,1.001, 0.000)){  //验证范围
					alert("${action.getText('financeType.netSalvageScale.maxLength')}");
					return false;
				}
			}	
         if ('' != document.forms[0].elements["financeType.netSalvageScale"].value) {
           if (!isDoubleNumber("financeType.netSalvageScale")) {
             alert("${action.getText('financeType.netSalvageScale.formate.error')}");
             return false;
           }
         }
         if(isNotEmpty(code,"financeType.comment")) {
			if (!isValidLength(document.forms[0],"financeType.comment",0,250)){
				alert("${action.getText('financeType.comment.maxlength')}");
				return false;
		    }
		} 
         return true;
         
       }
        function ltrim(str) {
		        var pattern = new RegExp("^[\\s]+","gi");
		        return str.replace(pattern,"");
		}
		
		function rtrim(str) {
		        var pattern = new RegExp("[\\s]+$","gi");
		        return str.replace(pattern,"");
		}
		function trim(str) {
		        return rtrim(ltrim(str));
		} 


       
     </script>
</@htmlPage>