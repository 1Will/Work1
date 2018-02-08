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

<#--$Id: $ -->

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('lubricationOilSearcher.title')}">
     <@ww.form  name="'LubricationOil'" action="'saveLubricationOil'" method="'post'" validate="true">
       <@ww.token name="saveLubricationOilToken"/>
       <#if lubricationOil?exists>
         <@ww.hidden name="'lubricationOil.id'" value="${lubricationOil.id?if_exists}"/>
       </#if>
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('lubricationOil.code')}'" name="'lubricationOil.code'" value="'${lubricationOil.code?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${action.getText('lubricationOil.name')}'" name="'lubricationOil.name'" value="'${lubricationOil.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr> 
             <tr>
             	<@ww.select label="'${action.getText('lubricationOil.category')}'" required="false" name="'category.id'" 
		    		        listKey="id" listValue="value"
		                    list="categorys" emptyOption="true" disabled="false">
		    	</@ww.select>
		        <@ww.select label="'${action.getText('lubricationOil.measureUnit')}'" required="false" name="'measureUnit.id'" 
		    		        listKey="id" listValue="value"
		                    list="measureUnits" emptyOption="true" disabled="false">
		    	</@ww.select>
             </tr>            
         </@inputTable>
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'"/>
	         <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/base/lubricationOil/listLubricationOils.html"/>
         </@buttonBar>
     </@ww.form>
     <script language="javascript" type="text/JavaScript">
     	  /*
	   *当该润滑油失效时，该页面所有控件，除有效、返回按钮，其他都失效
	  */
	　　<#if lubricationOil.disabled?exists>
	　　　　<#if (lubricationOil.disabled)>
		   　　__disableAllElements__(document.forms[0], new Array("enabled", "back"));
		  </#if>
	　　</#if>
       <#if lubricationOil.id?exists>
         window.onload = function() {
         <#if lubricationOil.category?exists>
           document.forms[0].elements["category.id"].value=${lubricationOil.category.id?if_exists};
         </#if>
         <#if lubricationOil.measureUnit?exists>
           document.forms[0].elements["measureUnit.id"].value=${lubricationOil.measureUnit.id?if_exists};
         </#if>
         }
       </#if>
       
       
       
     </script>
</@htmlPage>