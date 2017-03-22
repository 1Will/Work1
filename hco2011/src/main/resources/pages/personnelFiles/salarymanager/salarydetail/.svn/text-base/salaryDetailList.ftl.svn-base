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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@framePage title="${action.getText('salaryDetail.search')}">
	<@ww.form name="'listForm'" action="'searchSalaryStandardAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		
		<input type="hidden" name="salaryDetail.salaryStandard" value="${req.getParameter('salaryDetail.salaryStandard')?if_exists}"/>
		<@ww.token name="searchSalaryStandardActionToken"/>
        <@list title="${action.getText('salaryDetail.list')}" 
            includeParameters="salaryDetail.salaryStandard|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="salaryDetailIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
         	<@vcolumn title="${action.getText('项目名称')}" property="salaryItems.name" sortable="desc" >
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('类别')}" property="salaryItems.type.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('序号')}" property="salaryItems.orders.name" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('基本金额')}" property="basicMoney" sortable="desc">
             	<input type="hidden" name="id" value="#{object.id?if_exists}"/>
             	<input type="text" name="basicMoney_#{object.id?if_exists}" value="#{object.basicMoney?if_exists}" style="text-align:right;border:0px" onblur="check_basicMoney(this)"/>
     			<@alignRight/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	        <input type="submit" class="button" value="保存"/>
	        <input type="button" class="button" value="新建" onclick="newa()"/>
	          <@crm_disabledOrEnabled_button message="${action.getText('salaryDetail')}" boxName="salaryDetailIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
    <script>
    function checkInvalidParms(){
    return true;
    }
    
    	function check_basicMoney(t){
    		if(isNaN(t.value)){
    			t.focus();
    			alert("不是数字!");
    		}
    	}
    
    	function newa(){
    		var  url = "${req.contextPath}/salaryItems/listSalaryItemsWindow.html";
			popupModalDialog(url, 800, 600, creatorSelector2Handler);
    	}
    	function creatorSelector2Handler(result) {
			if (null != result) {
				if(result != ""){
					self.location="${req.contextPath}/salaryDetail/listSalaryDetailAction.html?salaryDetail.salaryStandard=${req.getParameter('salaryDetail.salaryStandard')?if_exists}&itemIds="+result;

				}
				
			}
		}
    </script>
</@framePage>
