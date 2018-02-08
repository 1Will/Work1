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
<#-- $Id: supplierSelector.ftl 11157 2008-02-29 03:39:18Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('spareBorrowSelector.title')}">
<@ww.form name="'listForm'" namespace="'/runmaintenance/spareBorrow'" action="'spareBorrowSelector'" method="'post'">
	<@ww.token name="searchSpareBorrowSelectorToken"/>
	<@ww.hidden name="'spareBillId'" value="'${req.getParameter('spareBillId')?if_exists}'"/>
    <#include "searchSpareBorrowSelector.ftl" />
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
		 <@list title="" excel=false setupTable=false 
		 includeParameters="billNo|name|spareCode|spareName|department.id|borrowDate|borrowDate_start|borrowDate_end|model|specification|borrowName"
		  fieldMap="billNo|name|spareCode|spareNamemodel|specification|borrowName,date:borrowDate_start|borrowDate_end">
			<@vlh.checkbox property="id" name="spareBorrowDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <@vcolumn title="${action.getText('borrowBillNo')}" property="spareBorrow.billNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('borrowBillName')}" property="spareBorrow.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.code')}" property="spareNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.name')}" property="spareName">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="spareBorrow.department.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('borrowDateTime')}" property="spareBorrow.borrowDate" format="yyyy-MM-dd">
            	<@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('spare.specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
              <@vcolumn title="${action.getText('borrow.Person')}" property="spareBorrow.borrowUser.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('spare.unit')}" property="unit">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('unitPrice')}" property="price">
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="${action.getText('borrowNum')}" property="amount">
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice">
            	<@alignRight />
            </@vcolumn>
          <#assign spareBorrowStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'UNBORROW'>
               <#assign spareBorrowStatus = "${action.getText('UNBORROW')}"/>
               </#if>
              <#if '${object.status}' == 'BORROWED'>
              <#assign spareBorrowStatus = "${action.getText('BORROWED')}"/>
            </#if>
            </#if>
               <@vcolumn title="${action.getText('status')}" attributes="width='50'">
           ${spareBorrowStatus}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
		<#if !first>
       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"spareBorrowDtlIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
	</#if>
</@ww.form>
  <script language="javascript">
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	      	return false;
	      }
	      choosePurBillDetail(boxname);
	      return true;
	    }
	    	      	
	   function choosePurBillDetail(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var purBillDetailIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   purBillDetailIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(purBillDetailIds,false);
	   }
        </script>
</@htmlPage>
