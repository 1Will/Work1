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

<#-- $Id: contactArchivesList.ftl 2009-12-08 13:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('contactArchives.title')}">
	<@ww.form name="'listForm'" action="'searchContactArchives'" method="'post'">
		<@ww.token name="searchContactArchivesToken"/>
		<#include "./contactArchivesSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'customerIsNotNull'" value="'${req.getParameter('customerIsNotNull')?if_exists}'"/>
		<#--用于项目联系人去重-->
		<@ww.hidden name="'projectId'" value="'${req.getParameter('projectId')?if_exists}'"/>
		<@ww.hidden name="'projectInfoCus'" value="'${req.getParameter('projectInfoCus')?if_exists}'"/>
		
		<#if backVistiFlag?exists>
			<@ww.hidden name="'backVisitFlag'" value="'${backVistiFlag?if_exists}'"/>
			<#if customerId?exists>
				<@ww.hidden name="'customer.id'" value="'#{customerId?if_exists}'"/>
			</#if>
		</#if>
		<#if contactArchiveIds?exists>
			<@ww.hidden name="'contactArchiveIds_a'" value="'${contactArchiveIds?if_exists}'"/>
		<#else>
			<@ww.hidden name="'contactArchiveIds_a'" value="'${req.getParameter('contactArchiveIds')?if_exists}'"/>
		</#if>
			
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<#if backVistiFlag?exists>
				<#else>
					<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/customerRelationship/editContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
				</#if>
				
				<#if backVisitCheckBox?exists>
				 <input type="button" value="保存参与者" onclick="return_contactArchives()"/>
				</#if>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('contactArchives.list.title')}" 
            includeParameters="contactArchives.name|customerIsNotNull|customer.id|contactArchives.customerName|projectId|projectInfoCus|con_Project.id|type.id|readOnly|onlyInvalid|onlyValid|customer.id|backVisitFlag" 
        	fieldMap="like:contactArchives.name|contactArchives.customerName" >
        	
        	<#--列表名称： 客户参与者信息列表 -->
        	<#if backVisitCheckBox?exists>
        	<script language="JavaScript" type="text/JavaScript">
				document.getElementsByTagName("td")[18].innerHTML="${action.getText('customerParticipants.list.title')}" 
        	</script>
        	
			</#if>
			
        	<#if backVistiFlag?exists>
        		<#if backVisitCheckBox?exists>
	        		<#if !(action.isReadOnly())>
			        	<@vlh.checkbox property="id" name="contactArchivesNames">
			            	<@vlh.attribute name="width" value="30" />
			            </@vlh.checkbox>
		            </#if>
		            <@ww.hidden name="'#{object.id}'" value="'#{object.id}:${object.name}'"/>
	        </#if>
	            
        		<@vcolumn title="${action.getText('contactArchives.name')}" property="name" sortable="desc">
        		<#if backVisitCheckBox?exists>
	                    ${object.name}
        		<#else>
        		<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.phone?if_exists}','${object.mobilePhone?if_exists}', '${object.email?if_exists}'));">
	                    ${object.name}
                	</a>
        		 </#if>
	                
	            </@vcolumn>
        	<#else>
        		<#if !(action.isReadOnly())>
		        	<@vlh.checkbox property="id" name="contactArchivesIds">
		            	<@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
	            </#if>
	            <@vcolumn title="${action.getText('contactArchives.name')}" property="name" sortable="desc">
	                <a href="editContactArchives.html?contactArchives.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.name}</a>
					<@alignLeft/>
	            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('contactArchives.customerName')}" property="custName" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.customerType')}" property="customerType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<#assign sex=""/>
			<#if object.sex>
				<#assign sex="${action.getText('contactArchives.women')}"/>
			<#else>
				<#assign sex="${action.getText('contactArchives.man')}"/>
			</#if>
			<@vcolumn title="${action.getText('contactArchives.sex')}" property="sex" sortable="desc">
				${sex}
				<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('contactArchives.dept')}" property="dept" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('contactArchives.duty')}" property="duty" sortable="desc">
     			<@alignLeft attributes="width:100;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.mobilePhone')}" property="mobilePhone" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.phone')}" property="phone" sortable="desc">
     			<@alignLeft attributes="width:150;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contactArchives.caType')}" property="type.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if backVistiFlag?exists>
        <#else>
        	<#if !(action.isReadOnly())>
        <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('contactArchives.info')}" boxName="contactArchivesIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
		</#if>
    </@ww.form>
</@htmlPage>
<script>
	var contactArchiveIds = getObjByName('contactArchiveIds_a').value;
	var nameAids =contactArchiveIds.split(',');
	var idss ='';
	for(var i=0;i<nameAids.length;i++){
		if(i==nameAids.length-1){
			idss+=nameAids[i].split(':')[0];
		}else{
			idss+=nameAids[i].split(':')[0]+',';
		}
	}
	var ids = idss.split(',');
	var selector = document.getElementsByName("contactArchivesNames");
	for(var i =0 ;i<ids.length;i++){
		for(var j=0;j<selector.length;j++){
			if(selector[j].value == ids[i]){
				selector[j].checked = true;
			}
		}
	}
</script>
<script language="javascript">
	function contactArchivess(){
		var name_="";
		var selector = document.getElementsByName("contactArchivesNames");
		var length = selector.length;
		if(length){
			for(var i=0;i<length;i++){
				if(selector[i].checked == true){
					var tempName = selector[i].value;
					if(name_==""){
						name_ =tempName;
					}else{
						name_+=","+tempName;
					}
				}
			}
		}
		returnDialog(new Array(name_));
	}
	
	function return_contactArchives(){
		var contactArchiveIds = getObjByName('contactArchiveIds_a').value;
		returnDialog(new Array(contactArchiveIds));
	}
	
	document.onclick = function(){
		var contactArchiveIds = getObjByName('contactArchiveIds_a').value;
		var selector = document.getElementsByName("contactArchivesNames");
		var length = selector.length;
		if(length){
			for(var i=0;i<length;i++){
				if(selector[i].checked == true){
					var temp = selector[i].value;
					var tempName = getObjByName(temp).value;
					if(contactArchiveIds.indexOf(tempName)<0){
						contactArchiveIds += tempName +",";
					}
				}else{
					var temp = selector[i].value;
					var tempName = getObjByName(temp).value;
					contactArchiveIds = contactArchiveIds.replace(tempName+',','');
				}
			}
		}
		getObjByName('contactArchiveIds_a').value = contactArchiveIds;
		
	}
	
</script>