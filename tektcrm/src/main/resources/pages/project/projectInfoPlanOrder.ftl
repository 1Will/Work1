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

<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('title.order')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProPlanOrder'" method="'post'">
	<@ww.token name="saveProjectInfoPlanToken"/>
	<#assign returnUrl="${req.contextPath}/projectInfo/editProPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}&yesUrl=yesUrl"/>
    <@inputTable>
    	<#if projectInfoId?exists>
    		<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
    	</#if>
    	<#if idsString?exists>
    		<@ww.hidden name="'ids'"  value="'${idsString?if_exists}'"/>
    	</#if>
    	<#if contractManagementId?exists>
    		<@ww.hidden name="'contractManagement.id'" value="'${contractManagementId?if_exists}'"/>
    	</#if>
    	<@ww.hidden name="'editFlag'" value="'${editFlag?if_exists}'"/>
    	<@ww.hidden name="'projectInfoPlan.isSaved'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	 <#assign itemNo=1>
    	 <@list title="" >
    	 <@ww.hidden name="${object.id}" value="'${object.orderNumber}'"/>
    	  <@vcolumn title="${action.getText('projectInfoPlan.name')}"  property="name" >
            <@alignLeft/>
            <@vlh.attribute name="width" value="260" />
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
			 <@vcolumn title="${action.getText('projectInfoPlanOrder.order')}" >
			 <select id="order#{itemNo}" name="order${object.id}">
			   <#list orderNum as num>
                    <option value="${num}">${num}</option>
               </#list>
             </select>
            	<@vlh.attribute name="width" value="160" />
            </@vcolumn>
    	 </@list>
    	
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		</#if>
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
window.onload=function(){
   var items=#{itemNo};
   for(var i=2;i<=items;i++){
     var name="order"+i;
     var id=document.getElementById(name).name.substring(5);
     var nameNow="order"+id;
     getObjByName(nameNow).value= getObjByName(id).value;
   }
}
	function savee(){
	  var items=#{itemNo};
	  var re = /^[0-9]+.?[0-9]*$/;
	 for(var i=2;i<items;i++){
	    for(var j=i+1;j<=items;j++){
	     var name_1="order"+i;
	     var name_2="order"+j;
	     var number_1=document.getElementById(name_1).value;
	     var number_2=document.getElementById(name_2).value;
	     if(number_1==''||number_2==''){
	         alert("${action.getText('orderNumber.isNot.Null')}");
		　　　　return false;
	     }
	     if (!re.test(number_1)||!re.test(number_2)) {
		　　　　alert("${action.getText('orderNumber.mustbe.number')}");
		　　　　return false;
　　		}
	     if(number_1==number_2){
	       alert("${action.getText('orderNumber.isNot')}");
	       return false;
	     }
	    }
	  }
	  return true;
	}
</script>
</@htmlPage>
