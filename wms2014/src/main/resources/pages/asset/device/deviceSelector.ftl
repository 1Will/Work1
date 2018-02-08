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
<#-- $Id: deviceSelector.ftl 11004 2008-02-18 08:55:30Z zbzhang $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('device.title')}">
<base target="_self">
<@ww.form name="'listForm'" action="'deviceSelector'" method="'post'">

	<@ww.token name="deviceSelectorToken"/>
	<@ww.hidden name="'multi'" value="'${req.getParameter('multi')?if_exists}'"/>
	<@ww.hidden name="'flag'" value="'${flag?if_exists}'"/>
    <#include "./deviceSearcher.ftl" />
    <@ww.hidden name="'oldDeviceIds'" value="'${filterDeviceIds?if_exists}'"/>
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <@list title="${action.getText('device.list')}" 
        	includeParameters="id|flag|deviceNo|filiale.code|oldDeviceIds|name|multi|cardCreatedTime_start|cardCreatedTime_end|cardCreatedTime|invalid|department.id|category.id|deivceStatus.code|onlyValid|onlyInvalid|device.standard|useCategory.code|device.emphasis"
        	fieldMap="like:id|deviceNo|name,date:cardCreatedTime_start|cardCreatedTime_end" >
        	<#if (req.getParameter("multi")?has_content)>
        	  <@vlh.checkbox property="id" name="deviceIds">
            	<@vlh.attribute name="width" value="30" />
              </@vlh.checkbox>
            </#if>
            <#assign dept=''/>
            <#assign deptId=''/>
            <#assign assetNo=''/>
            <#assign model=''/>
            <#assign specification=''/>
            <#assign category=''/>
            <#assign supplierName=''/>
            <#if object.department?exists>
            	<#assign dept="${object.department.name}"/>
            	<#assign deptId="${object.department.id}"/>
            </#if>
            <#if object.assetNo?exists>
            	<#assign assetNo="${object.assetNo}"/>
            </#if>
            <#if object.model?exists>
            	<#assign model="${object.model}"/>
            </#if>
            <#if object.specification?exists>
            	<#assign specification="${object.specification}"/>
            </#if>
            <#if object.deviceType?exists>
            	<#assign category="${object.deviceType.name}"/>
            </#if>
            <#if object.filialeString?exists>
                <#assign filialeString="${object.filialeString}"/>
            </#if>
            <#if object.manager?exists>
              <#if object.manager.name?exists>
                <#assign managerName="${object.manager.name}"/>
              </#if>
            </#if>
            <#if object.supplier?exists>
            	<#assign supplierName="${object.supplier.name}"/>
            </#if>
            <#if (req.getParameter("multi")?has_content)>
	            <@vcolumn title="${action.getText('device.no')}" property="deviceNo" sortable="desc">
	            	${object.deviceNo}
	        	</@vcolumn>
	        <#else>
	        	<@vcolumn title="${action.getText('device.no')}" property="deviceNo" sortable="desc">
	            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.deviceNo}', '${dept}','${assetNo}','${model}','${specification}','${category}','${supplierName}','${deptId}'));">${object.deviceNo}</a>
	        	</@vcolumn>	
        	</#if>
            <@vcolumn title="${action.getText('device.assetno')}" property="assetNo" sortable="desc"/>
            <@vcolumn title="${action.getText('device.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('device.model')}" property="model" />
            <@vcolumn title="${action.getText('device.specification')}" property="specification" />
            <@vcolumn title="${action.getText('device.filiale')}" property="filialeString" />
            <@vcolumn title="${action.getText('device.category')}"   property="deviceType.name" /> 
            <@vcolumn title="${action.getText('department')}"  property="department.name" />
            <@vcolumn title="${action.getText('managerPep')}"  property="managerName" />
            <@vcolumn title="${action.getText('device.installPlace')}" property="installPlace" />
            <@vcolumn title="${action.getText('device.cardCreatedTime')}" property="cardCreatedTime" format="yyyy-MM-dd" />
            <#assign status=''/>
            <#if object.deviceStatus?exists>
              <#assign status="${object.deviceStatus.value}"/>
            </#if>
            <@vcolumn title="${action.getText('device.status')}">${status}<@alignLeft /></@vcolumn>
            <#--
      		<@vcolumn title="${action.getText('state')}" property="job.docState.value"/>
      		-->
      </@list>
      <#if (req.getParameter("multi")?has_content)&& !first >
        <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"deviceIds\");'">
	      		<#--<@ww.param name="'onclick'" value="'return confirmSelects(\"deviceIds\");'"/>-->
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
      </#if>
      <script language="javascript">
      	window.onload = function() {
		filialeSelectDeptDWR();
      	<#--
       		<#if first>
			    <#if loginUser.department?exists>
			      getObjByNameRe("department.id").value = #{loginUser.department.id};
			    </#if>
			  </#if>
		-->
       }
      <#--
      	document.forms[0].elements["docState.code"].readOnly=true;
      	document.forms[0].elements["docState.code"].disabled=true;
     	document.forms[0].elements["docState.code"].value = 'DOC_APPROVED';
     	-->
     	<#if (action.isOnlyInvalid())>
          getObjByNameRe("onlyDisabled").checked=true;
          changValidOrInvalidStatus();
        </#if>
     	getObjByNameRe("invalid").value="true";
     	
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	        alert("${action.getText('tooling.noSelect')}");
	      	return false;
	      }
	      chooseDevices(boxname);
	      return true;
	    }
	    	      	
	   function chooseDevices(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var deviceIds = "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   deviceIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(deviceIds,false);
	   }
      	
      </script>
</@ww.form>
</@htmlPage>
