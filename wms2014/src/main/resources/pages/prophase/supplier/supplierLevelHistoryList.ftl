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
<@framePage title="">
  <@ww.form namespace="'/prophase/supplier'" name="'evaluate'" action="'searchEvaluateLevelHistory'" method="'post'">
  <@ww.token name="evaluateToken"/>
   <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
    <#if supplierEvaluate?exists>
	      <@ww.hidden name="'supplierEvaluate.id'" value="'#{supplierEvaluate.id?if_exists}'"/>
	     </#if>
	 <#if supplier?exists>
	   <@ww.hidden name="'supplier.id'" value="'#{supplier.id?if_exists}'"/>
	   </#if>
	   <#if supplierLevelHistory?exists>
	   <@ww.hidden name="'supplierLevelHistory.id'" value="'#{supplierLevelHistory.id?if_exists}'"/>
	   </#if>
    <#assign itemNo = 1/>
         <@list title="" excel=false setupTable=false
            includeParameters="supplier.id" 
        	fieldMap="like:supplier.id">
              <@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('supplierLevelHistory.createdTime')}" property="changDate" format="yyyy-MM-dd">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplierLevelHistory.origLevel')}" property="origLevel">
                   <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplierLevelHistory.newLevel')}" property="newLevel">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplierLevelHistory.changeReason')}" property="changeReason">
                 <@alignLeft/>
            </@vcolumn>
        </@list>  
	     <#if !first>
        </#if>
  </@ww.form>
</@framePage>





