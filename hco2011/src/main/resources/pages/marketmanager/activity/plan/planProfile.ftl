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

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('title')}">
<@ww.form namespace="'/plan'" name="'planProfile'" action="'savePlan'" method="'post'">
	<@ww.token name="savePlanToken"/>
    <@inputTable>
		<@ww.hidden name="'persons.id'" value="'${req.getParameter('persons.id')?if_exists}'"/>
    	<#if plan.id?exists>
    		<@ww.hidden name="'plan.id'" value="#{plan.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@textfield label="${action.getText('plan.code')}" name="plan.code" anothername="code" value="${plan.code?if_exists}" required="false" cssClass="underline" maxlength="20" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('plan.planTime')}'" 
				name="'plan.planTime'" 
	   			value="'${(plan.planTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
			<script language="javascript">
					var date = new Date();
					if(getObjByName("plan.planTime").value==''){
						getObjByName("plan.planTime").value = date.format("yyyy-MM-dd");
					}
			</script>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('plan.persons')}:</label>
	     	</td>
	     	<td>
	     		<#if plan.persons?exists>
		   		<input type="text" name="plan.persons" class="underline"  value="${plan.persons.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="plan.persons" class="underline"  value="${req.getParameter('plan.persons')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td> 	
		</tr>	
		<tr>
		    <@textarea name="plan.theme" label="${action.getText('plan.theme')}" anothername="theme" maxLength="500" required="true" value="${plan.theme?if_exists}"/>		
		</tr>
		<tr>
		    <@textarea name="plan.description" label="${action.getText('plan.description')}" anothername="description" maxLength="500"  value="${plan.description?if_exists}"/>		
  		</tr>
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/plan/listPlan.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	//弹出负责人查询模态窗体
	function persons_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["persons.id"].value = result[0];
	   		document.forms[0].elements["plan.persons"].value = result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		//策划时间
		if(getObjByName('plan.planTime').value ==''){
		 		alert("${action.getText('plan.planTime.not.null')}");
		 		getObjByName('plan.planTime').focus();
		      	return false;   
		}else{
		 	if(!isDate('plan.planTime')){
				alert("${action.getText('plan.planTime.dateFormate.error')}");
				getObjByName('plan.planTime').value="";
	      	    getObjByName('plan.planTime').focus();
				return false;
			} 
		}
		//负责人
	    if(getObjByName('plan.persons').value==""){
	        alert('${action.getText('plan.persons.not.null')}');
	        return false;
	    }
        //主题
        if(!textareaCheck_theme()){
       		getObjByName('plan.theme').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
