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
<@htmlPage title="${action.getText('techAlterDetailSelector.title')}">
<base target="_self">
  <@ww.form name="'listTechAlterDetail'" action="'techAlterDetailSelector'" method="'post'">
    <@ww.token name="techAlterDetailSelectorToken"/>   
	<@inputTable>
	  <tr>
	    <@ww.textfield label="'${action.getText('detail.yearPlanNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('detail.yearPlanName')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'"/>
	  </tr>
      <tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		        value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                    list="departments" emptyOption="false" disabled="false">
        </@ww.select>
        <@pp.datePicker label="'${action.getText('toolingMakeDetail.yearPlan.year')}'" name="'year'"
 					    value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
 					    dateFormat="'%Y'"/>
	  </tr>
	  <tr>
	    <@ww.select label="'${action.getText('category')}'" required="false" name="'toolingType.id'" 
    			    value="'${req.getParameter('toolingType.id')?if_exists}'" listKey="id" listValue="value"
                    list="toolingTypes" emptyOption="false" disabled="false">
        </@ww.select>
        <@pp.dateRanger label="'${action.getText('requestDate')}'" name="'planFinishedDate'" 
                        value="'${req.getParameter('planFinishedDate_start')?if_exists}|${req.getParameter('planFinishedDate_end')?if_exists}'"
                        cssClass="'underline'" dateFormat="date"/>
	  </tr>
	</@inputTable>
	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>	
    <#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="planNo|planName|name|department.id|toolingType.id|year|planFinishedDate_start|planFinishedDate_end"  
     	   fieldMap="like:planNo|planName|name,date:planFinishedDate_start|planFinishedDate_end" >
 	  <@vlh.checkbox property="id" name="techAlterDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('serialNo')}">
        #{itemNo}
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
       <@vcolumn title="${action.getText('detail.yearPlanNo')}" property="yearPlan.planNo">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('detail.yearPlanName')}" property="yearPlan.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="yearPlan.department.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('toolingMakeDetail.yearPlan.year')}" property="yearPlan.year" format="yyyy">
        <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('tooling.no')}" property="graphNo">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('category')}" property="categoryName">
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
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('requestDate')}" property="requestDate" format="yyyy-MM-dd">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('requestReason')}" property="requestReason">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
      <#--
      <#assign createQuarterFlagValue=''/>
      <#if object.createQuarterFlag>
        <#assign createQuarterFlagValue="${action.getText('yes')}"/>
      <#else>
        <#assign createQuarterFlagValue="${action.getText('no')}"/>
      </#if>
      <@vcolumn title="${action.getText('toolingMakeDetail.createQuarterFlag')}">
        ${createQuarterFlagValue}
        <@alignLeft />
      </@vcolumn>
      -->
    </@list>
    <#if !first>
      <@buttonBar>
        <@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"techAlterDetailIds\");'">
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
      </@buttonBar>
    </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
    function confirmSelects(boxname) {
      if (!hasChecked(boxname)) {
        alert("${action.getText('techAlterDetail.noSelect')}");
  	    return false;
      }
      chooseTechAlterDetails(boxname);
      return true;
    }
    function chooseTechAlterDetails(boxname) {
      var selector = document.getElementsByName(boxname);
	  if (!selector) {
	    return false;
	  }
      var techAlterDetailIds = "";
	  if (selector.length) {
	    for (i = 0; i < selector.length; i++) {
	      if (selector[i].checked) {
		    techAlterDetailIds += selector[i].value + ",";
		  }
	  }
	}
	returnDialog(techAlterDetailIds,false);
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
  var selector = document.all("toolingType.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('toolingType.id')?exists>
    if (selector.options[i].value == "${req.getParameter('toolingType.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  function checkInvalidParms() {
    if (document.getElementById("department.id").value == -1) {
  		document.getElementById("department.id").value = '';
	}
	if (document.getElementById("toolingType.id").value == -1) {
  		document.getElementById("toolingType.id").value = '';
	}
	strStartMsg="${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('planFinishedDate.order.error')}";
	if(!queryDate("planFinishedDate_start","planFinishedDate_end",
	    strStartMsg,strEndMsg)){
	    	return false;
	}
	return true;
  }
  </script>
</@htmlPage>
  