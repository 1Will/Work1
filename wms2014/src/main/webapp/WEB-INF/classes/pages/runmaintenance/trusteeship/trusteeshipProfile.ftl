<#include "../../includes/eam2008.ftl" />
<#assign trusteeshipEditTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign trusteeshipEditTitle = "${action.getText('deviceTrusteeship.edit')}"/>
  <#else>
  <#assign trusteeshipEditTitle = "${action.getText('toolingTrusteeship.edit')}"/>
  </#if>
</#if>
<@htmlPage title="${trusteeshipEditTitle}">
  <@ww.form namespace="'/runmaintenance/trusteeship'" name="'trusteeship'" action="'saveTrusteeship'" method="'post'">
    <@ww.token name="saveTrusteeshipToken"/>
      <@inputTable>
        <#if trusteeship.id?exists>
          <@ww.hidden name="'trusteeship.id'" value="${trusteeship.id?if_exists}"/>
        </#if>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
            <tr>
              <@ww.textfield label="'${action.getText('trusteeship.billNO')}'" name="'trusteeship.billNo'"   value="'${trusteeship.billNo?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true"/>

              <@ww.textfield label="'${action.getText('trusteeship.billname')}'" name="'trusteeship.billName'"   value="'${trusteeship.billName?if_exists}'" cssClass="'underline'"  required="true"/>
            </tr>
            <tr>
               <tr> 
                  <@ww.select label="'${action.getText('original.department')}'" required="false" name="'trusteeshipDepartment.id'" 
		    		 value="'${req.getParameter('trusteeshipDepartment.id')?if_exists}'" listKey="id" listValue="name"
		           list="trusteeshipDepartments" emptyOption="true" disabled="false">
		          </@ww.select>
		          <@ww.select label="'${action.getText('now.trusteeshipfactory')}'" required="true" name="'supplier.id'" 
		    		 value="'${req.getParameter('supplier.id')?if_exists}'" listKey="id" listValue="name"
		           list="externalHelps" emptyOption="true" disabled="false">
		          </@ww.select>  
            </tr>
            <tr>
                 <@ww.select label="'${action.getText('trusteeship.requestdepartment')}'" required="false" name="'trusteeshipRequestDepartment.id'" 
		    		 value="'${req.getParameter('trusteeshipRequestDepartment.id')?if_exists}'" listKey="id" listValue="name"
		           list="trusteeshipRequestDepartments" emptyOption="true"  required="true" disabled="false">
		          </@ww.select> 
		  <#if trusteeship.trusteeshipUser?exists>
		     <@ww.textfield label="'${action.getText('trusteeshiprequestUserName')}'" name="'trusteeship.trusteeshipUser'"   value="'${trusteeship.trusteeshipUser?if_exists}'" cssClass="'underline'"  required="true"/>
		   <#else>
		    <#assign acceptPeoplePersonName ="${loginUser.name}"/>
		  <@ww.textfield label="'${action.getText('trusteeshiprequestUserName')}'" name="'trusteeship.trusteeshipUser'" value="'${acceptPeoplePersonName}'"  cssClass="'underline'"  required="true"/>
		 </#if>
            <tr>
		  <@pp.datePicker label="'${action.getText('trusteeshiprequestDate')}'" name="'trusteeship.trusteeshipRequestDate'"
	     				  value="'${(trusteeship.trusteeshipRequestDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
        </tr>
        <tr>
          <@ww.textarea  label="'${action.getText('trusteeship.reason')}'" 
	        	         name="'trusteeship.reason'" 
	        	         value="'${trusteeship.reason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"/>
        </tr>
      </@inputTable>
      <@buttonBar>
      <#if !(action.isReadOnly())>
	    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick= "'return migrate_Vlidate();'"/>
	   </#if> 
	    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/trusteeship/listTrusteeship.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	   
	  </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript">
      window.onload = function() {
         a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["trusteeship.trusteeshipRequestDate"].value=time;
        <#if trusteeship.trusteeshipDepartment?exists>
          
         document.getElementById("trusteeshipDepartment.id").value = #{trusteeship.trusteeshipDepartment.id};
        </#if>
        <#if trusteeship.supplier?exists>
         document.getElementById("supplier.id").value = #{trusteeship.supplier.id};
        </#if>
        <#if loginUser.department?exists>
         document.getElementById("trusteeshipRequestDepartment.id").value = #{loginUser.department.id};
        </#if>
        <#if trusteeship.trusteeshipRequestDepartment?exists>
         document.getElementById("trusteeshipRequestDepartment.id").value = #{trusteeship.trusteeshipRequestDepartment.id};
        </#if>
        <#if trusteeship.id?exists>
		   var url = 'listTrusteeshipDetail.html?trusteeship.id=${trusteeship.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      document.getElementById("repairItem").className = "selectedtab";
	    </#if>
	   
    }
     function validateDepartment() {
       var dept = document.forms[0].elements["trusteeshipRequestDepartment.id"].value;
       if (dept =='' || dept == '-1') {
         alert("${action.getText('department.id.requried')}");
         return false;
       }
       return true;
     }
     function migrate_Vlidate(){
       if(document.getElementById("trusteeship.billName").value==''){
	        alert('${action.getText('trusteeship.billName.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "trusteeship.billName", null, 50)){
				alert("${action.getText('trusteeship.billName.length')}");
				return  false;
			   }
	     }
	   
          if(!validateDepartment()){//部门为必须输入项的验证
             return false;
          }
       //验证现托管外协厂   
       if(document.getElementById("supplier.id").value==''){
	       alert('${action.getText('trusteeshipfactory.not.null')}');
	        return false;
	     }
      if(document.getElementById("trusteeship.trusteeshipUser").value==''){
	        alert('${action.getText('trusteeship.trusteeshipUser.not.null')}');
	        return false;
	      }else{
	            if(!isValidLength(document.forms[0], "trusteeship.trusteeshipUser", null, 50)){
				alert("${action.getText('trusteeship.trusteeshipUser.length')}");
				return  false;
			   }
	      }
	   if(document.getElementById("trusteeship.trusteeshipRequestDate").value==''){
	        alert('${action.getText('trusteeship.trusteeshipRequestDate.not.null')}');
	        return false;
	      }   
	    var date=document.getElementById("trusteeship.trusteeshipRequestDate").value;
		if(!isDate("trusteeship.trusteeshipRequestDate")){
		    alert("${action.getText('trusteeship.trusteeshipRequestDate')}");
			return false;
		  }
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.trusteeship.trusteeshipRequestDate')}");
		    return false;
		  }
		  
	        if(document.getElementById("trusteeship.reason").value!=''){
			if(!isValidLength(document.forms[0], "trusteeship.reason", null, 250)){
				alert("${action.getText('trusteeship.reason.length')}");
				return  false;
			   }
			}
			
			return true;
      }
    </script>
    <#if trusteeship.id?exists>
    <#if toolingDevFlag?exists>
      <#if toolingDevFlag == 'DEVICE'>
  	  <ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/trusteeship/listTrusteeshipDetail.html?trusteeship.id=${trusteeship.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}
	    	" target="frame">${action.getText('repairItem')}</a></li>
	    	</ul>
	    <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
     <#else>
         <ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/trusteeship/listTrusteeshipDetail.html?trusteeship.id=${trusteeship.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}
	    	" target="frame">${action.getText('toolingrepairItem')}</a></li>
	    	</ul>
	    <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
      </#if>
    </#if>
    </#if>
</@htmlPage>