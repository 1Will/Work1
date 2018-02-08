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

<#-- $Id: contactArchivesProfile.ftl 2009-12-08 14:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('计划模板维护')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProPlanTemplet'" method="'post'">
	<@ww.token name="saveProPlanTempletToken"/>
	<#assign returnUrl="${req.contextPath}/projectInfo/editProPlanTemplet.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    <@inputTable>
         <#if projectInfoPlan?exists>
    	<@ww.hidden name="'projectInfoPlan.id'" value="'${projectInfoPlan.id?if_exists}'"/>
    	</#if>
    	<@ww.hidden name="'proPlanTemplet.id'" value="'${proPlanTemplet.id?if_exists}'"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<@ww.hidden name="'backCheckBox'" value="'${backCheckBox?if_exists}'"/>
	  <tr>
	     <!--计划模板编码-->
	     <@ww.textfield label="'${action.getText('code')}'" name="'proPlanTemplet.code'" value="'${proPlanTemplet.code?if_exists}'" cssClass="'underline'"  disabled="true"/>
	     <!--计划模板名称-->
		   <@ww.textfield label="'${action.getText('name')}'" name="'proPlanTemplet.name'" value="'${proPlanTemplet.name?if_exists}'" cssClass="'underline'" required="true"/>
		
	  </tr>
     <tr>
         <!--计划名称-->
		<td align="right" valign="top">
       		<span class="required">*</span>
       		<label class="label">${action.getText('proPlanTemplet.proplanName')}:</label>
     	</td>
		<td>
			<input type="text" name="proPlanTemplet.proplanName" class="underline"  value="${proPlanTemplet.proplanName?if_exists}" maxlength="100" size="20" />
		</td>
		<td align="right" valign="top">
		   <span class="required">*</span>
			<label class="label">${action.getText('proPlanTemplet.priority')}:</label>
			<td>
			<select id="proPlanTemplet.priority" name ="proPlanTemplet.priority">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			</select><font color="red">10级为最高优先级</font>
		</td>
		</td>
		<script>
			getObjByName("proPlanTemplet.priority").value=${proPlanTemplet.priority?if_exists};
		</script>
	</tr>
		
     <tr>
			<td align="right" valign="top">
			   <span class="required">*</span>
        		<label class="label">
        			${action.getText('proPlanTemplet')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="proPlanTemplet.outline" rows="4" >${proPlanTemplet.outline?if_exists}</textarea>
	        </td>
	        <script language="javascript">
			 var width=document.body.clientWidth/9;
						getObjByName("proPlanTemplet.outline").cols =width;
		   </script>
		</tr>
    </@inputTable>
    <@buttonBar>
	    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
	    <#if backCheckBox?exists && backCheckBox =='backCheckBox'>
	    <input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
	    <#else>
	       <#if proPlanTemplet.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlanTemplet.html"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlanTemplet.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
		    </#if>
	        <input class="button" type="button" value="${action.getText('back')}"  onclick="back_new()">
	    </#if>
    </@buttonBar>
</@ww.form>

<script type="text/javascript">
function back_new(){
  window.location.href="${req.contextPath}/projectInfo/listProPlanTemplet.html?readOnly=false";
}
function savee(){
  if(getObjByName("proPlanTemplet.name").value==''){
    alert("${action.getText('templet.name.notNull')}");
    return false;
  }
  if(getObjByName("proPlanTemplet.proplanName").value==''){
    alert("${action.getText('plan.name.notNull')}");
    return false;
  }
  if(getObjByName("proPlanTemplet.priority").value==''){
    alert("${action.getText('priority.notNull')}");
    return false;
  }
  if(getObjByName("proPlanTemplet.outline").value==''){
    alert("${action.getText('outline.notNull')}");
    return false;
  }
  return true;
}
</script>
</@htmlPage>
