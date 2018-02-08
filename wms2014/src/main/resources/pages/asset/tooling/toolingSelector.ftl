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
<#-- $Id: deviceSelector.ftl 7932 2007-10-22 04:11:59Z qsun $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingSearcher.title')}">
<base target="_self">

<@ww.form name="'listTooling'" action="'toolingSelector'" method="'post'">
	<@ww.token name="toolingSelectorToken"/>
    <#include "toolingAccountSearcher.ftl"/>
    <@ww.hidden name="'multi'" value="'${req.getParameter('multi')?if_exists}'"/>
    <@ww.hidden name="'flag'" value="'${flag?if_exists}'"/>
    <@ww.hidden name="'hiddenCheckBox'" value="'${req.getParameter('hiddenCheckBox')?if_exists}'"/> 
    <@ww.hidden name="'onlyValid'" value="true"/>
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <@list title="${action.getText('tooling.list')}" 
            includeParameters="id|onlyValid|includeDisabled|hiddenCheckBox|multi|includeValid|manager|deviceNo|name|graphNo|toolingType.id|toolingTypeDetail.id|department.id|toolingStatus.code|product.id" 
        	fieldMap="like:id|deviceNo|name|manager|graphNo" >
        	<#if (req.getParameter("multi")?has_content)>
        	  <@vlh.checkbox property="id" name="toolingIds">
            	<@vlh.attribute name="width" value="30" />
              </@vlh.checkbox>
            </#if>
            <#assign graphNo=""/>
        	<#assign department=""/>
        	<#assign deptId=""/>
        	<#assign totalOutput=""/>
        	<#assign usedQuota=""/>
        	<#assign toolingType=""/>
        	<#assign usedDate=""/>
        	<#assign toolingModel=''/>
        	<#assign toolingSpecification=''/>
        	<#assign toolingstatus=''/>
        	<#if object.toolingStatus?exists>
        	  <#assign toolingstatus="${object.toolingStatus.value?if_exists}"/>
        	</#if>
        	<#if object.graphNo?exists>
        	  <#assign graphNo="${object.graphNo?if_exists}"/>
        	</#if>
        	<#if object.department?exists>
        	  <#assign department="${object.department.name?if_exists}"/>
        	  <#assign deptId="${object.department.id?if_exists}"/>
        	</#if>
        	<#if object.manager?exists>
        	   <#assign managerName="${object.manager.name?if_exists}"/>
        	</#if>
        	<#if object.totalOutput?exists>
        	  <#assign totalOutput="${object.totalOutput?if_exists}"/>
        	</#if>
        	<#if object.usedQuota?exists>
        	  <#assign usedQuota="${object.usedQuota?if_exists}"/>
        	</#if>
        	<#if object.toolingType?exists>
        	  <#assign toolingType="${object.toolingType.value?if_exists}"/>
        	</#if>
        	<#if object.usedStartedTime?exists>
        	  <#assign usedDate="${(object.usedStartedTime?string('yyyy-MM-dd'))?if_exists}"/>
        	</#if>
        	<#if object.model?exists>
        	  <#assign toolingModel='${object.model?if_exists}'/>
        	</#if>
        	<#if object.specification?exists>
        	  <#assign toolingSpecification='${object.specification?if_exists}'/>
        	</#if>
        	<#if (req.getParameter("multi")?has_content)>
	            <@vcolumn title="${action.getText('toolingNo')}" property="deviceNo" sortable="desc">
	            	${object.deviceNo}
	        	</@vcolumn>
	        <#else>
	        	<@vcolumn title="${action.getText('toolingNo')}" property="deviceNo" sortable="desc">
	            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.deviceNo}', '${graphNo}', '${department}', '${totalOutput}', '${usedQuota}', '${toolingType}','${usedDate}','${toolingModel}','${toolingSpecification}','${deptId}'));">${object.deviceNo}</a>
	        	</@vcolumn>	
        	</#if>
            <@vcolumn title="${action.getText('toolingDrawNo')}" property="graphNo" sortable="desc"/>
            <@vcolumn title="${action.getText('toolingName')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('toolingCategory')}" property="toolingType.value" />
            <@vcolumn title="${action.getText('category')}" property="toolingTypeDetail.name" />
            <@vcolumn title="${action.getText('productName')}"   property="product.name" /> 
            <@vcolumn title="${action.getText('department')}"  property="department.name" />
            <@vcolumn title="${action.getText('managerPep')}"  property="manager.name" />
            
            <@vcolumn title="${action.getText('toolingState')}" property="toolingStatus.value" />
      </@list>
      <#if (req.getParameter("multi")?has_content)&& !first >
        <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"toolingIds\");'">
	      		<#--<@ww.param name="'onclick'" value="'return confirmSelects(\"deviceIds\");'"/>-->
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
      </#if>
      <script language="JavaScript" type="text/JavaScript"> 
      <#if first>
      	<#if loginUser.department?exists>
          document.forms[0].elements["department.id"].value=#{loginUser.department.id};
        </#if>
      </#if>
	    window.onload = function () {
	  	  toSortDetailTypeByToolType();	
	 	  if (-1 == getObjByNameRe("toolingType.id").value) {
	        getObjByNameRe("toolingTypeDetail.id").length=1;
	      } else {
	       toolTypeValueChange();
	       <#if req.getParameter('toolingTypeDetail.id')?exists>
	         getObjByNameRe("toolingTypeDetail.id").value = "${req.getParameter('toolingTypeDetail.id')?if_exists}";
	       </#if>
	     }
	      if ('${includeDisabled?string}' == 'true') {
	        getObjByNameRe("includeDisabled").checked=true;
	      }
	      if ('${req.getParameter('multi')?if_exists}' == '1') {
	        getObjByNameRe("multi").value="1";
	      }
	      //document.forms[0].elements["toolingStatus.code"].readOnly=true;
      	  //document.forms[0].elements["toolingStatus.code"].disabled=true;
     	  //document.forms[0].elements["toolingStatus.code"].value = '0151';
     	  //document.forms[0].elements["invalid"].value="true";
	    }
	    function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	        alert("${action.getText('tooling.noSelect')}");
	      	return false;
	      }
	      chooseToolings(boxname);
	      return true;
	    }
	    	      	
	   function chooseToolings(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var toolingIds = "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   toolingIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(toolingIds,false);
	   }
	  </script>
</@ww.form>
</@htmlPage>
