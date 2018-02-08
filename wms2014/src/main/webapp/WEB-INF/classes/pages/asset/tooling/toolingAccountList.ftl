<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolAccountSearch.title')}">
  <@ww.form namespace="'/asset/tooling'" name="'tooling'" action="'searchToolingAccounts'" method="'post'">
  <@ww.token name="searchToolingAccountsToken"/>
  	     <#include "toolingAccountSearcher.ftl"/>
  	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
  	     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        	<#if !(action.isReadOnly())>
              <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/asset/tooling/editToolingAccount.html"/> 
            </#if>
         </@buttonBar>   
         <@list title="${action.getText('toolingAccout.list')}" 
            includeParameters="id|readOnly|eamAuthentication|includeDisabled|includeValid|deviceNo|manager|name|graphNo|productName|toolingType.id|toolingTypeDetail.id|department.id|toolingStatus.code|onlyValid|onlyInvalid" 
        	fieldMap="like:id|deviceNo|name|graphNo|productName|manager" >
            <#if (object.enabled)>
        	<@vlh.checkbox property="id" name="toolingIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#else>
             <@vlh.checkbox property="id" name="disabledTooling" attributes="id=disabledToolingIds onclick=\"notSelectedInvalid('disabledTooling','toolingIds');\"">
            	<@vlh.attribute name="width" value="30" />
            	<@vlh.attribute name="disabled" value="true" />
             </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('toolingNo')}" property="deviceNo" sortable="desc">
              <#if !(action.isOnlyInvalid())>
                <a href="editToolingAccount.html?tooling.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}">${object.deviceNo}</a>
              </#if>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingName')}" property="name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingDrawNo')}" property="graphNo" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingCategory')}" property="toolingType.value">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('category')}" property="toolingTypeDetail.name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('managerPep')}" property="manager.name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('productName')}" property="product.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('processNo')}" property="processNo" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('usedDate')}" property="usedStartedTime" format="${action.getText('dateFormat')}" sortable="desc">
            <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.cardCreatedTime')}" property="cardCreatedTime" format="${action.getText('dateFormat')}" sortable="desc">
            <@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('outputTotal')}" property="totalOutput" sortable="desc">
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('number')}" property="number" sortable="desc">
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('suplierName')}" property="supplier.name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingState')}" property="toolingStatus.value">
            <@alignLeft/>
            </@vcolumn>
        </@list>  
	    <#if !first>
	    <#if !(action.isReadOnly())>
        <@buttonBar>
            <@eam2008_disabledOrEnabled_button message="${action.getText('tooling')}" boxName="toolingIds" jsFunctionName="checkInvalidParms()"/>
           <#-- <#if !valueListNoRecords>
            <input type="button" class="button" name="print" value="${action.getText('print')}" onclick="open_toolingListPdf();" />
           </#if>-->     
        </@buttonBar>
        </#if>
        </#if>
	 <script language="JavaScript" type="text/JavaScript"> 
	  window.onload = function () {
	  	 <#if first>
		    <#if loginUser.department?exists>
		      document.getElementById("department.id").value = #{loginUser.department.id};
		    </#if>
		  </#if>
	  	 toSortDetailTypeByToolType();	
	 	 if (-1 == document.getElementById("toolingType.id").value) {
	        document.getElementById("toolingTypeDetail.id").length=1;
	     } else {
	       toolTypeValueChange();
	       <#if req.getParameter('toolingTypeDetail.id')?exists>
	         document.getElementById("toolingTypeDetail.id").value = "${req.getParameter('toolingTypeDetail.id')?if_exists}";
	       </#if>
	     }
	     if ('${includeDisabled?string}' == 'true') {
	       document.getElementById("includeDisabled").checked=true;
	     }
	  }
	  function validateDelete(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       }
       <#--<#if !first && valueListNoRecords>
         document.forms[0].elements["print"].disabled = true;
       </#if>-->
       function open_toolingListPdf() {
         var ary = new Array();
         if ('' == document.forms[0].elements["deviceNo"].value) {
           ary.push("deviceNo");
           ary.push("null");
         } else {
           ary.push("deviceNo");
           ary.push(document.forms[0].elements["deviceNo"].value);
         }
         if ('' == document.forms[0].elements["name"].value) {
           ary.push("name");
           ary.push("null");
         } else {
           ary.push("name");
           ary.push(document.forms[0].elements["name"].value);
         }
         if ('' == document.forms[0].elements["graphNo"].value) {
           ary.push("graphNo");
           ary.push("null");
         } else {
           ary.push("graphNo");
           ary.push(document.forms[0].elements["graphNo"].value);
         }
         if ('-1' == document.forms[0].elements["product.id"].value) {
           ary.push("productId");
           ary.push("null");
         } else {
           ary.push("productId");
           ary.push(document.forms[0].elements["product.id"].value);
         }
         if ('-1' == document.forms[0].elements["toolingType.id"].value) {
           ary.push("toolingTypeId");
           ary.push("null");
         } else {
           ary.push("toolingTypeId");
           ary.push(document.forms[0].elements["toolingType.id"].value);
         }
         if ('-1' == document.forms[0].elements["toolingTypeDetail.id"].value) {
           ary.push("toolingTypeDetailId");
           ary.push("null");
         } else {
           ary.push("toolingTypeDetailId");
           ary.push(document.forms[0].elements["toolingTypeDetail.id"].value);
         }
         if (-1 == document.forms[0].elements["department.id"].value) {
           ary.push("departmentId");
           ary.push("null");
         } else {
           ary.push("departmentId");
           ary.push(document.forms[0].elements["department.id"].value);
         }
         if ('' == document.forms[0].elements["toolingStatus.code"].value) {
           ary.push("toolingStatusCode");
           ary.push("null");
         } else {
           ary.push("toolingStatusCode");
           ary.push(document.forms[0].elements["toolingStatus.code"].value);
         }
         if (!document.forms[0].elements["includeDisabled"].checked) {
           ary.push("includeDisabled");
           ary.push("null");
         } else {
           ary.push("includeDisabled");
           ary.push("true");
         }
                                                           
         var url = '${req.contextPath}/reports/tooling/toolingList.pdf?searchOption=' + ary;
        // alert(url);
         url = encodeURI(url);
         window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
       }
	</script>
  </@ww.form>
</@htmlPage>