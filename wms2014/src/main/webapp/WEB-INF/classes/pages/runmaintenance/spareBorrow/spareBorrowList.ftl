<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareBorrowBill.title')}">
  <@ww.form namespace="'/runmaintenance/spareBorrow'" name="'spareBorrow'" action="'searchSpareBorrows'" method="'post'">
  <@ww.token name="spareDeviceToken"/>
  	     <#include "spareBorrowSeacher.ftl"/>
  	       <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
  	       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>    
          <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
             <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/spareBorrow/editSpareBorrow.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
        </#if>
         </@buttonBar>   
           <@list title="${action.getText('spareBorrowBill.list')}"  
            includeParameters="billNo|name|department.id|borrowDate|borrowDate_start|borrowDate_end|borrowName|appName|borrowStatus|toolingDevFlag|onlyValid|onlyInvalid" 
        	fieldMap="like:billNo|name|borrowName|appName,date:borrowDate_start|borrowDate_end">
	          <@vlh.checkbox property="id" name="spareBorrowIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
	          <#if (object.disabled)>
             <@vcolumn title="${action.getText('spareBorrowBillNo')}" property="billNo" sortable="desc">
               ${object.billNo}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vcolumn title="${action.getText('spareBorrowBillNo')}" property="billNo" sortable="desc">
                 <a href="editSpareBorrow.html?spareBorrow.id=#{object.id}&toolingDevFlag=${toolingDevFlag}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('spareBorrowBillName')}" property="name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('borrowDateTime')}" property="borrowDate"  format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('borrow.Person')}" property="borrowUser.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
         
             <@vcolumn title="${action.getText('appor.Person')}" property="approvalUser.name">
                 <@alignLeft/>
            </@vcolumn>
             <#assign spareBorrowStatus = ''/>
            <#if '${object.status}' == 'NEWSTATUS'>
              <#assign spareBorrowStatus = "${action.getText('NEWSTATUS')}"/>
            <#elseif '${object.status}' == 'BORROWING'>
              <#assign spareBorrowStatus = "${action.getText('BORROWING')}"/>
            <#else>
              <#assign spareBorrowStatus = "${action.getText('BORROWED')}"/>
            </#if>
         <@vcolumn title="${action.getText('status')}" attributes="width='50'">
           ${spareBorrowStatus}
                 <@alignLeft/>
            </@vcolumn>
        </@list>  
	     <#if !first>
        <@buttonBar>
         <#if !(action.isReadOnly())>
	            <@eam2008_disabledOrEnabled_button message="${action.getText('spareBorrowlist')}" boxName="spareBorrowIds" jsFunctionName="checkInvalidParms()"/>
	    </#if>
	    </@buttonBar> 
        </#if>
  </@ww.form>
</@htmlPage>
