<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('supplierlevle.title')}">
	<base target="_self">
     <@ww.form  name="'supplierLevelHistory'" action="'saveSupplierLevelHistory'" method="'post'">
          <@ww.token name="saveSupplierLevelHistoryToken"/>     
          <@inputTable>
            <#if supplier.id?exists>
                 <@ww.hidden name="'supplier.id'" value="'#{supplier.id?if_exists}'"/> 
            </#if>
            <#if supplierLevelHistory.id?exists>
                 <@ww.hidden name="'supplierLevelHistory.id'" value="'#{supplierLevelHistory.id?if_exists}'"/> 
            <#else>
                 <@ww.hidden name="'supplierLevelHistory.id'" value=""/> 
            </#if>
            <tr> 
            <#assign supplierLevel = ''/>
            <#if supplierLevelHistory.origLevel?exists>
                <#assign supplierLevel="${supplierLevelHistory.origLevel}"/>
            <#elseif supplier.level?exists>
                <#assign supplierLevel="${supplier.level.value}"/>
            </#if>
            <@ww.textfield label="'${action.getText('supplier.oldLevel')}'" name="'supplierLevelHistory.origLevel'" value="'${supplierLevel}'" cssClass="'underline'" readonly="true" disabled="true"/> 
            <#if supplierLevelHistory.origLevel?exists>
              <@ww.hidden name="'supplierLevelHistory.origLevelId'" value="'#{supplier.level.id?if_exists}'"/>
            <#else>
              <@ww.hidden name="'supplierLevelHistory.origLevelId'" value="''"/>
            </#if> 
              <#--<#if supplier.level?exists>
                 <@ww.hidden name="'supplierLevelHistory.origLevelId'" value="'#{supplier.level.id?if_exists}'"/>
                 </#if>--> 
                 <@ww.select label="'${action.getText('supplier.newLevel')}'" 
	                   required="false" 
	                   name="'supplierLevelHistory.newLevel'" 
    			       listKey="id" 
    			       listValue="value"
                       list="supplierLevel" 
                       emptyOption="false" 
                       disabled="false">
                       <#--
                       <#if supplierLevelHistory.newLevel?exists>
                            <@ww.param name="'value'"  value="'${supplierLevelHistory.newLevel?if_exists}'" />
                       </#if>
                       -->
                 </@ww.select>	
            </tr>
            <tr>
            <@pp.datePicker label="'${action.getText('requestDate')}'" name="'supplierLevelHistory.changDate'"
	     				    value="'${(supplierLevelHistory.changDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				    required="true" maxlength="10"/>
            <@ww.textarea label="'${action.getText('supplierLevelHistory.demo')}'" 
					         name="'supplierLevelHistory.changeReason'" 
					         value="'${supplierLevelHistory.changeReason?if_exists}'" rows="'3'" cols="'60'" 
							 />             
            </tr>
         </@inputTable>
         <#assign newLevelId = 0/>
         <#if supplier.level?exists>
           <#assign newLevelId = '#{supplier.level.id}'/>
         </#if> 
          <@buttonBar>
              <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateLevel();'"/> 
              <@vbutton class="button" value="${action.getText('close')}" onclick="javascript: returnDialog(new Array('${newLevelId?if_exists}'))"/>
          </@buttonBar>         
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
    window.onload = function () {
         a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["supplierLevelHistory.changDate"].value=time;
		 <#--
          	var selector = document.all("supplierLevelHistory.newLevel");
          	var groups=selector.options.length;  
         	for (i=0; i<groups; i++){
         	    <#if req.getParameter('supplierLevelHistory.newLevel')?exists>
            		if (selector.options[i].value=="${req.getParameter('supplierLevelHistory.newLevel')?if_exists}"){
               			selector.options[i].selected="true";
            		}
            	</#if>
         	}
         	-->
         	<#if newLevel?exists>
         	  document.forms[0].elements["supplierLevelHistory.newLevel"].value = #{newLevel.id?if_exists};
         	</#if>
         
          }
    
    function validateLevel(){
   
         if(document.getElementById("supplierLevelHistory.changDate").value==''){
	        alert('${action.getText('supplierLevelHistory.changDate')}');
	        return false;
	      }
	    var date=document.getElementById("supplierLevelHistory.changDate").value;
		if(!isDate("supplierLevelHistory.changDate")){
		    alert("${action.getText('select.right.supplierLevelHistory.changDate')}");
			return false;
		  }
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.supplierLevelHistory.changDate')}");
		    return false;
		  }
		
		if(document.getElementById("supplierLevelHistory.changeReason").value!=''){
			if(!isValidLength(document.forms[0], "supplierLevelHistory.changeReason", null, 250)){
				alert("${action.getText('supplierLevelHistory.changeReason.length')}");
				return  false;
			   }
			}
	if(document.getElementById("supplierLevelHistory.origLevelId").value==document.getElementById("supplierLevelHistory.newLevel").value) {
             alert('${action.getText('supplierLevelHistory.changeLevel.tag')}');
             return false;
         }  		
        return true;
    }
    </script>
</@htmlPage>   