<#include "../../includes/eam2008.ftl" />
 <#assign toolingDevName=""/>
 <#assign toolingDevListName=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign toolingDevName="${action.getText('inventoryBilldeviceSearcher.title')}"/>
   <#assign toolingDevListName="${action.getText('faultBillDevice.list')}"/>
   <#else>
    <#assign toolingDevName="${action.getText('inventoryBillTooingSearcher.title')}"/>
    <#assign toolingDevListName="${action.getText('faultBillTooing.list')}"/>
   </#if>
</#if>
<@htmlPage title="${toolingDevName}">
  <@ww.form namespace="'/runmaintenance/inventory'" name="'toolingFaultBill'" action="'searchInventorys'" method="'post'">
  <@ww.token name="searchInventorysToken"/>
  	     <#include "inventoryDeviceSearcher.ftl"/>
  	       <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@buttonBar>     
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>   
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
        	
         <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/inventory/editInventory.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/> 
         </#if>
         </@buttonBar>   
         <@list title="${toolingDevListName}" 
            includeParameters="billNo|name|readOnly|inventoryDate|inventoryPeople|department.id|inventoryOccurDateTm_start|inventoryOccurDateTm_end|onlyValid|onlyInvalid|toolingDevFlag" 
        	fieldMap="like:billNo|name|inventoryPeople|inventoryDate|,date:inventoryOccurDateTm_start|inventoryOccurDateTm_end" >
	          <#if (object.disabled)>
	          <@vlh.checkbox property="id" name="inventoryIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('inventoryBillNo')}" property="billNo" sortable="desc">
               ${object.billNo}
                 <@alignLeft/>
            </@vcolumn>
             <#else>
             <@vlh.checkbox property="id" name="inventoryIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
             <@vcolumn title="${action.getText('inventoryBillNo')}" property="billNo" sortable="desc">
                 <a href="editInventory.html?inventoryBill.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
                 <@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('inventoryBillName')}" property="name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('inventoryBillDepartment')}" property="department.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('inventory.people')}" property="inventoryPeople.name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('inventoryBillDate')}" property="inventoryDate" format="yyyy-MM-dd" sortable="desc">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment" >
                 <@alignLeft/>
            </@vcolumn>
        </@list>  
	    <#if !first>
        <@buttonBar>
              <#if !(action.isReadOnly())>
	            <@eam2008_disabledOrEnabled_button message="${action.getText('inventoryBill')}" boxName="inventoryIds" jsFunctionName="checkInvalidParms()"/>
	    	</#if>
	    		<#--
	    		<@vbutton value="打印" class="button" />
	    		-->
	    </@buttonBar> 
        </#if>
  </@ww.form>
  <script language="JavaScript" type="text/JavaScript"> 
      window.onload = function () {
       <#if first>
		    <#if loginUser.department?exists>
		      getObjByNameRe("department.id").value = #{loginUser.department.id};
		    </#if>
		  </#if> 
	    if ('${onlyInvalid?string}' == 'true') {
	      getObjByNameRe("onlyDisabled").checked=true;
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