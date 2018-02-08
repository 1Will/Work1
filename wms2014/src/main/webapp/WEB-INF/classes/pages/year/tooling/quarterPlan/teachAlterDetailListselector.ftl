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
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('teachAlterSelector.title')}">
<base target="_self">
  <@ww.form name="'listQuarterPlan'" action="'searchPurchaseTeachAlterDetailSelector'" method="'post'">
    <@ww.token name="repairMaintenanceDetailSelectorToken"/> 
    <@ww.hidden name="'detailType'" value="'${req.getParameter('detailType')?if_exists}'"/>
    <@ww.hidden name="'subscribeId'" value="'${req.getParameter('subscribeId')?if_exists}'"/>
	<@inputTable>
      <tr>
	    <@ww.textfield label="'${action.getText('detail.QuarterPlanNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('detail.QuarterPlanName')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'"/>
	  </tr>
	  <tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		        value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                    list="departments" emptyOption="false" disabled="false">
        </@ww.select>
        <@pp.datePicker label="'${action.getText('toolingMakeDetail.QuarterPlan.year')}'" name="'quarter.year'"
 					    value="'${req.getParameter('quarter.year')?if_exists}'" cssClass="'underline'" size="15" 
 					    dateFormat="'%Y'"/>
	  </tr>
	  <tr>
	  	<@ww.select label="'${action.getText('quarterPlan.qarterType')}'" required="false" name="'qarterType'" 
    		          value="'${req.getParameter('qarterType')?if_exists}'" listKey="value" listValue="label"
                      list="qarterTypes" emptyOption="false" disabled="false">
        </@ww.select>
	    <@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />
	  </tr>
      <tr>
      	<@ww.select label="'${action.getText('toolingMakeDetail.category')}'" required="false" 
	                 name="'category.id'" value="'${req.getParameter('category.id')?if_exists}'"
		             listKey="id" listValue="value" list="categorys" 
		             emptyOption="false" disabled="false" required="false">
		 </@ww.select>
        <@pp.dateRanger label="'${action.getText('requestDate')}'" name="'planFinishedDate'" 
                        value="'${req.getParameter('planFinishedDate_start')?if_exists}|${req.getParameter('planFinishedDate_end')?if_exists}'"
                        cssClass="'underline'" dateFormat="date"/>
     </tr>
   </@inputTable> 
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <#assign itemNo = 1/>
 	<@list title="${action.getText('sparePurchaseDetailSelector.title')}" 
           includeParameters="id|planNo|planName|name|department.id|subscribeId|detailType|qarterType|category.id|planFinishedDate_start|planFinishedDate_end" 
           fieldMap="like:id|planNo|planName|name|,date:planFinishedDate_start|planFinishedDate_end" >
	  <@vlh.checkbox property="id" name="toolingMakeDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('serialNo')}">
        #{itemNo}
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('detail.QuarterPlanNo')}" property="quarterPlan.planNo">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('detail.QuarterPlanName')}" property="quarterPlan.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="quarterPlan.department.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('toolingMakeDetail.QuarterPlan.year')}" property="quarterPlan.year" format="yyyy">
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('toolingMakeDetail.category')}" property="categoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('detailCategory')}" property="detailCategoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('model')}" property="model">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('specification')}" property="specification">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('number')}" property="number">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('allPrice')}" property="allPrice">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('requestDate')}" property="requestDate" format="yyyy-MM-dd">
        <@alignCenter />
      </@vcolumn>
      <@vcolumn title="${action.getText('requestReason')}" property="requestReason">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
        <@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"toolingMakeDetailIds\");'">
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
      </@buttonBar>
    </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
    function confirmSelects(boxname) {
      if (!hasChecked(boxname)) {
        alert("${action.getText('toolingMakeDetail.noSelect')}");
  	    return false;
      }
      chooseToolingMakeDetails(boxname);
      return true;
    }
    function chooseToolingMakeDetails(boxname) {
      var selector = document.getElementsByName(boxname);
	  if (!selector) {
	    return false;
	  }
      var toolingMakeDetailIds = "";
	  if (selector.length) {
	    for (i = 0; i < selector.length; i++) {
	      if (selector[i].checked) {
		    toolingMakeDetailIds += selector[i].value + ",";
		  }
	  }
	}
	returnDialog(toolingMakeDetailIds,false);
  }
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  
  var selector = document.all("category.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('category.id')?exists>
    if (selector.options[i].value == "${req.getParameter('category.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  function checkInvalidParms() {
  	if (document.getElementById("department.id").value == -1) {
  		document.getElementById("department.id").value = '';
	}
	if (document.getElementById("category.id").value == -1) {
  		document.getElementById("category.id").value = '';
	}
	if (document.getElementById("qarterType").value == -1) {
  		document.getElementById("qarterType").value = '';
	}
	if(document.forms[0].elements["quarter.year"].value!=""){
          if(!validateTime(document.forms[0].elements["quarter.year"].value)){
               alert("${action.getText('quarter.year.start_EndTimeMistake')}");
               return false;
          }
    }
	if(document.forms[0].elements["planFinishedDate_start"].value!=""){
          if(!isYear(document.forms[0].elements["planFinishedDate_start"].value)){
               alert("${action.getText('planFinishedDate.start_EndTimeMistake')}");
               return false;
          }
    }
    if(document.forms[0].elements["planFinishedDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["planFinishedDate_end"].value)){
               alert("${action.getText('planFinishedDate.start_EndTimeMistake')}");
               return false;
          }
    }
      return true;
  }
</script>
</@htmlPage>