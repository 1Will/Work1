<#include "../../includes/eam2008.ftl" />
 <#assign toolingDevName=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign toolingDevName="${action.getText('supplierevaluateSearcher.title')}"/>
   <#else>
    <#assign toolingDevName="${action.getText('supplierevaluateTooingSearcher.title')}"/>
   </#if>
</#if>
<@htmlPage title="${toolingDevName}">
  <@ww.form namespace="'/prophase/supplier'" name="'SupplierEvaluate'" action="'searchSupplierEvaluates'" method="'post'">
  <@ww.token name="SupplierEvaluateToken"/>
  	     <#include "supplierEvaluateSearcher.ftl"/>
  	       <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
  	       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	<#if !(action.isReadOnly())>  
              <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/prophase/supplier/editSupplierEvaluate.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
            </#if>
         </@buttonBar> 
           
         <@list title="${action.getText('SupplierEvaluate')}" 
           includeParameters="supplierNo|name|department.id|evaluatedate_start|evaluatedate_end|averageminute_min|averageminute_max|evaluateUser.name|level.id|onlyValid|onlyInvalid|toolingDevFlag|supplierEvaluateYear" 
        	fieldMap="like:supplierNo|evaluateUser.name|name|,date:evaluatedate_start|evaluatedate_end">
	          <#if (object.disabled)>
	          <@vlh.checkbox property="id" name="supplierEvaluateIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
                 <@vcolumn title="${action.getText('supplier1.supplierNo')}" property="supplier.supplierNo" sortable="desc">
               ${object.supplier.supplierNo}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vlh.checkbox property="id" name="supplierEvaluateIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('supplier1.supplierNo')}" property="supplier.supplierNo" sortable="desc">
                   <a href="editSupplierEvaluate.html?toolingDevFlag=${toolingDevFlag}&supplierEvaluate.id=#{object.id}&supplier.id=#{object.supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.supplier.supplierNo}</a>
                 <@alignLeft/>
            </@vcolumn>
         </#if>
            <@vcolumn title="${action.getText('supplier.name')}" property="supplier.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department.name')}" property="department.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('evaluateUser.name')}" property="evaluateUser.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('evaluatedate')}" property="evaluatedate"  format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('evaluate.year')}" property="unitYear"  format="yyyy" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('evaluate.level')}" property="level" sortable="desc">
               <#if object.level?exists>
                 ${object.level.value}
               </#if>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('averageminute')}" property="evaluateMinute"  sortable="desc">
                 <@alignRight/>
            </@vcolumn>
            
        </@list>  
	    <#if !first>
         <#if !(action.isReadOnly())> 
           <@buttonBar>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('evaluateDevice')}" boxName="supplierEvaluateIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
	     </#if> 
        </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
      window.onload = function () {
      
	    if ('${onlyInvalid?string}' == 'true') {
	      document.getElementById("onlyDisabled").checked=true;
	    }
	  }
	  	  function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       }
       
       
    </script>
</@htmlPage>