<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->

<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('intactnessBillTitle')}">

    <@ww.form namespace="'/runmaintenance/intactness'" name="'intactness'" action="'saveIntactness'" method="'post'">
        <@ww.token name="saveIntactnessToken"/>
        <@inputTable>
          <#if intactnessBill.id?exists>
          	<@ww.hidden name="'intactnessBill.id'" value="#{intactnessBill.id}"/>
          </#if>
           <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
          <tr>
				<@ww.textfield label="'${action.getText('intactness.billNo')}'" name="'intactnessBill.code'" value="'${intactnessBill.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('intactness.name')}'" name="'intactnessBill.name'" value="'${intactnessBill.name?if_exists}'" cssClass="'underline'" required="true"/>
		  </tr>
	 	  <tr> 
	           <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
			    		   listKey="id" listValue="name"
			               list="departments" emptyOption="true" disabled="false"   required="true">
			    </@ww.select> 
			    <#assign intactnessPeopleName = ''/>
				<#if intactnessBill.examiner?exists>
				  <#assign intactnessPeopleName = "${intactnessBill.examiner}" />
				<#elseif loginUser?exists>
				  <#assign intactnessPeopleName = "${loginUser.name}" /> 
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('intactness.examiner')}:</label></td>
	        	<td>
	        		<input type="text" name="intactnessPeople.name" 
	        			class="underline"  value="${intactnessPeopleName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		
		    		    <a onClick="intactnessUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
				<input type="hidden" name="examiner" value="${intactnessPeopleName}" />
		    </tr>
		    <tr>
    	     	<@pp.datePicker label="'${action.getText('intactness.intactnessTime')}'" name="'intactnessBill.examDate'"
	     						value="'${(intactnessBill.examDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true" maxlength="10"/>
		        <@ww.textarea  label="'${action.getText('comment')}'" 
	        	          	   name="'intactnessBill.comment'" 
	        	         	   value="'${intactnessBill.comment? if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" required="false"/>
			</tr>

        </@inputTable>
        <@buttonBar>
        
         <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'" />
           </#if>
           
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/intactness/listIntactnesses.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
        </@buttonBar>
    </@ww.form>
  <script language="JavaScript" type="text/JavaScript">
     window.onload = function() {
       <#if intactnessBill.department?exists>
	     getObjByNameRe("department.id").value = #{intactnessBill.department.id};
	   <#elseif loginUser.department?exists>
	     getObjByNameRe("department.id").value = #{loginUser.department.id};
	   </#if>
	   <#if intactnessBill.id?exists>
	     var url = '${req.contextPath}/runmaintenance/intactness/listIntactnessDetails.html?intactnessBill.id=#{intactnessBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
		 document.all.frame.src= url;
		 getObjByNameRe("intactnessDetail").className = "selectedtab";
	   <#else>
	     a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["intactnessBill.examDate"].value=time;
	   </#if>
     }
     /*
      * 鉴定人选择
     */
     function intactnessUser_OpenDialog() {
	   var url = "${req.contextPath}/popup/userSelector.html";
	   popupModalDialog(url, 800, 600,intactnessUserSelectorHandler);
	 }
	 function intactnessUserSelectorHandler(result) {
	   if (null != result) {
	     document.forms[0].elements["intactnessPeople.name"].value=result[1];
		 document.forms[0].elements["examiner"].value=result[1];
	   }             	
     }
	 function validate() {
	   if(getObjByNameRe("intactnessBill.name").value=='') {
	     alert('${action.getText('select.intactnessBill.name')}');
	     return false;
	   } else if (!isValidLength(document.forms[0],"intactnessBill.name",0,50)){
	      alert("${action.getText('intactnessBill.name.maxLength')}");
	      return false;
	    }
	   if(getObjByNameRe("department.id").value=='') {
	     alert('${action.getText('select.department')}');
	     return false;
	   }
	   if(getObjByNameRe("intactnessPeople.name").value=='') {
	      alert('${action.getText('select.intactnessPeople.name')}');
	      return false;
	   }
	   if(getObjByNameRe("intactnessBill.examDate").value=='') {
	     alert('${action.getText('select.intactnessBill.examDate')}');
	     return false;
	   } else {
		 if(!isDate("intactnessBill.examDate")) {
		   alert("${action.getText('select.right.intactnessBill.examDate')}");
		   return false;
		 }
       }
      if(getObjByNameRe("intactnessBill.comment").value!=''){
        if(!isValidLength(document.forms[0], "intactnessBill.comment", null, 250)){
          alert('${action.getText('intactnessBill.comment.maxLength')}');
          return false;
        }
      }
	   return true;
	 }
   </script>
   <#if intactnessBill.id?exists>
  	  <ul id="beautytab">
	    	<li><a id="intactnessDetail" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/intactness/listIntactnessDetails.html?intactnessBill.id=${intactnessBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('intactnessBill.detail')}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
    </#if>
</@htmlPage>