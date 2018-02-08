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
<#-- $Id: -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('repair.doc.title1')}">
 <base target="_self">
	<@ww.form name="'repairDoc'" action="'saveModifyRepairDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveModifyRepairDocToken"/>
		<@inputTable>
	      <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	  </#if>
     	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	         <@ww.hidden name="'repairDoc.id'" value="#{repairDoc.id?if_exists}"/>
	          	<tr>
	            	<@ww.textfield label="'${action.getText('repair.doc.name')}'" size="50" name="'repairDoc.name'" value="'${repairDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>		
				<tr>
					 <@oneLine>
					 <@ww.textarea label="'${action.getText('repair.doc.description')}'" 
					         name="'repairDoc.description'" 
					         value="'${repairDoc.description?if_exists}'" rows="'4'" cols="'80'" 
							 required="false"/>
					</@oneLine>
				</tr>
		</@inputTable>
		<@buttonBar>
			<@vsubmit name="'new'" value="'${action.getText('save')}'" onclick="''"/>
			<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		<@ww.hidden name="'origFileName'" value=""/>
    </@ww.form>
</@htmlPage>