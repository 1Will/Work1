<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->

<#include "../../includes/macros.ftl" />
<#assign inventoryEditTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign inventoryEditTitle = "${action.getText('deviceInventory.edit')}"/>
  <#else>
   <#assign inventoryEditTitle = "${action.getText('toolingInventory.edit')}"/>
  </#if>
</#if>
<@htmlPage title="${inventoryEditTitle}">
    <@ww.form namespace="'/runmaintenance/inventory'" name="'inventory'" action="'saveInventory'" method="'post'">
        <@ww.token name="saveInventoryToken"/>
        <@inputTable>
            <#if inventoryBill.id?exists>
                <@ww.hidden name="'inventoryBill.id'" value="#{inventoryBill.id}"/>
            </#if>
             <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
             <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
          <tr>
				<@ww.textfield label="'${action.getText('inventoryBillNo')}'" name="'inventoryBill.billNo'" value="'${inventoryBill.billNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('inventoryBillName')}'" name="'inventoryBill.name'" value="'${inventoryBill.name?if_exists}'" cssClass="'underline'" required="true"/>
		  </tr>
	 	  <tr> 
	           <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
			    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
			            list="departments" emptyOption="true" disabled="false"   required="true">
			    </@ww.select> 
			    <#assign inventoryPeopleName = ''/>
				<#if inventoryBill.inventoryPeople?exists>
				 <#assign inventoryPeopleName = "${inventoryBill.inventoryPeople.name}" />
				 <#elseif loginUser?exists>
				   <#assign inventoryPeopleName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('inventory.people')}:</label></td>
	        	<td>
	        		<input type="text" name="inventoryPeople.name" 
	        			class="underline"  value="${inventoryPeopleName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="receiveUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign inventoryPeopleId = ''/>
				<#if inventoryBill.inventoryPeople?exists>
				 <#assign inventoryPeopleId = "${inventoryBill.inventoryPeople.id}" />
				  <#elseif loginUser?exists>
				  <#assign inventoryPeopleId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="inventoryPeople.id" value="${inventoryPeopleId}" />
		    </tr>
		    <tr>
    	     	<@pp.datePicker label="'${action.getText('inventory.publishDate')}'" name="'inventoryBill.inventoryDate'"
	     						value="'${(inventoryBill.inventoryDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
		        <@ww.textarea  label="'${action.getText('comment')}'" 
	        	          	   name="'inventoryBill.comment'" 
	        	         	   value="'${inventoryBill.comment? if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" required="false"/>
			</tr>
        </@inputTable>
        <@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return inventory_Vlidate();'" />
         </#if>   
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/inventory/listInventorys.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
        </@buttonBar>
    </@ww.form>
  <script language="JavaScript" type="text/JavaScript">
     window.onload = function() {
	   <#if loginUser.department?exists>
	     document.getElementById("department.id").value = #{loginUser.department.id};
	   </#if>
	   
       <#if inventoryBill.department?exists>
	     document.getElementById("department.id").value = #{inventoryBill.department.id};
	   </#if>
	   <#if inventoryBill.id?exists>
	     var url = '${req.contextPath}/runmaintenance/inventory/listinventoryDetail.html?inventoryBill.id=${inventoryBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		 document.all.frame.src= url;
		 document.getElementById("repairItem").className = "selectedtab";
	   <#else>
	     a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["inventoryBill.inventoryDate"].value=time;
	   </#if>
     }
     function receiveUser_OpenDialog() {
	   var url = "${req.contextPath}/popup/userSelector.html";
	   popupModalDialog(url, 800, 600,managerSelectorHandler);
	 }
	 function managerSelectorHandler(result) {
	   if (null != result) {
	     document.forms[0].elements["inventoryPeople.name"].value=result[1];
		 document.forms[0].elements["inventoryPeople.id"].value=result[0];
	   }             	
     }
	 function inventory_Vlidate(){
	   if(document.getElementById("inventoryBill.name").value=='') {
	     alert('${action.getText('inventoryBill_name')}');
	     return false;
	   }else{
	      if (!isValidLength(document.forms[0], "inventoryBill.name", null, 50)) {
             alert("${action.getText('inventoryBill.name.maxLength')}");
            return false;
            }
	   }
	   if(document.getElementById("department.id").value=='') {
	     alert('${action.getText('inventoryBill.department')}');
	     return false;
	   }
	   if(document.getElementById("inventoryPeople.name").value=='') {
	      alert('${action.getText('inventory.people.not.null')}');
	      return false;
	   }
	   if(document.getElementById("inventoryBill.inventoryDate").value=='') {
	     alert('${action.getText('inventoryBill.inventoryDate')}');
	     return false;
	   } else {
	     var date=document.getElementById("inventoryBill.inventoryDate").value;
		 if(!isDate("inventoryBill.inventoryDate")) {
		   alert("${action.getText('select.right.inventoryBill.inventoryDate')}");
		   return false;
		 }
		 <#--
		 if(!isDateLessEqualThenCurrent(date)) {
		   alert("${action.getText('afresh.inventoryBill.inventoryDate')}");
		   return false;
		 }
		 -->
	   }
		if(document.getElementById("inventoryBill.comment").value!=''){
		   if(!isValidLength(document.forms[0], "inventoryBill.comment", null, 250)){
				alert("${action.getText('comment.islength')}");
				return  false;
			}
		  }
	   return true;
	 }
  	  </script>
  	   <#if inventoryBill.id?exists>
  	     <#if toolingDevFlag?exists>
           <#if toolingDevFlag == 'DEVICE'>
  	         <ul id="beautytab">
	    	 <li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/inventory/listinventoryDetail.html?inventoryBill.id=${inventoryBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('deviceInventoryItem')}</a></li>
	    	 </ul>
	         <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
            <#else>
              <ul id="beautytab">
	    	 <li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/inventory/listinventoryDetail.html?inventoryBill.id=${inventoryBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('toolingInventoryItem')}</a></li>
	    	 </ul>
	         <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
          </#if>
         </#if> 
    </#if>
    </@htmlPage>