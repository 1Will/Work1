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

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('applyGoods.title')}">
	<@ww.form name="'listFrom'" namespace="'/apply'" action="'searchApplyGoods'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchApplyGoodsToken"/>
		<#include "./applyGoodsSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/apply/editApplyGoods.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <#assign returnName='replaceWord'>
        <#assign returnName_='replaceWord_'>
        <@list title="${action.getText('applyGoods.list.title')}" 
            includeParameters="applyGoods.code|applyGoods.crateDate_start|applyGoods.crateDate_end|applyGoods.applyPerson|applyGoods.applyPerson|status.id|goodsName.id|purpose.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:applyGoods.code|applyGoods.applyPerson,date:applyGoods.crateDate_start|applyGoods.crateDate_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="applyGoodsIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('applyGoods.code')}" property="code" sortable="desc">
                <a href="editApplyGoods.html?applyGoods.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.crateDate')}" property="createdTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.applyPerson')}" property="applyPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.dept')}" property="dept" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.purpose')}" property="purpose.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.goodsName')}" property="goodsName.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.goodsCount')}" property="goodsCount" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('applyGoods.bussinessUnit')}" property="bussinessUnit" sortable="desc">
              <#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.bussinessUnit?if_exists}'"/>
            	<span title="${object.bussinessUnit?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>8){
		            	document.write(s.slice(0,8)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyGoods.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('备注')}" property="remark" sortable="desc">
             <#assign returnName_=returnName +'replaceWord_'>
             <@ww.hidden name="'${returnName_}'" value="'${object.remark?if_exists}'"/>
             <span title="${object.remark?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName_}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>10){
		            	document.write(s.slice(0,10)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('applyGoods.info')}" boxName="applyGoodsIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>