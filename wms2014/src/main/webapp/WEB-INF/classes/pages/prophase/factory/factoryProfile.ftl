<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('factory.Profile')}">
<base target="_self">
     <@ww.form  name="'factory'" action="'saveFactory'" method="'post'" validate="true">
          <@ww.token name="saveFactoryToken"/>     
          <@inputTable>
          <#if factory.id?exists>
                <@ww.hidden name="'factory.id'" value="#{factory.id}"/>
          </#if>
             <@ww.hidden name="'factory.version'" value="#{factory.version?if_exists}"/>
             <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
             <@ww.hidden name="'requestSourceType'" value="'${requestSourceType?if_exists}'"/>
             <tr>
                 <@ww.textfield label="'${action.getText('factory.supplierNo')}'" name="'supplierNo'" value="'${factory.supplierNo?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
                 <@ww.textfield label="'${action.getText('factory.name')}'" name="'factory.name'" value="'${factory.name?if_exists}'" cssClass="'underline'" required="true" />	
             </tr>
             <tr>
	        	 <@ww.select label="'${action.getText('factory.company_property')}'" 
	                   required="false" 
	                   name="'companyType.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="companyType" 
                       emptyOption="true" 
                       disabled="false">
                       <#if factory.companyType?exists>
                          <@ww.param name="'value'"  value="'${factory.companyType.id?if_exists}'" />
                       </#if>
                 </@ww.select>
              	 <@ww.select label="'${action.getText('factory.supplierType')}'" 
	                   required="false" 
	                   name="'supplierType.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="factoryType" 
                       emptyOption="true" 
                       disabled="false">
                       <#if factory.supplierType?exists>
                       <@ww.param name="'value'"  value="'${factory.supplierType.id?if_exists}'" />
                        </#if>
                 </@ww.select>
             </tr>
             <tr>
                 <@ww.select label="'${action.getText('factory.trade')}'" 
	                   required="false" 
	                   name="'tradeType.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="factoryTrade" 
                       emptyOption="true" 
                       disabled="false">
                       <#if factory.tradeType?exists>
                       <@ww.param name="'value'"  value="'${factory.tradeType.id?if_exists}'" />
                       </#if>
                 </@ww.select>
                 <@ww.select label="'${action.getText('factory.country')}'" 
	                  required="false" 
	                  name="'country.id'" 
    			      listKey="id" 
    			      listValue="name"
                      list="factoryCountry" 
                      emptyOption="true" 
                      disabled="false">
                      <#if factory.country?exists>
                      <@ww.param name="'value'"  value="'${factory.country.id?if_exists}'" />
                      </#if>
                </@ww.select>
                
             </tr>
            <tr>
            	<@ww.textfield label="'${action.getText('factory.zone')}'" name="'factory.zone'" value="'${factory.zone?if_exists}'" cssClass="'underline'" />
                <@ww.textfield label="'${action.getText('factory.register_funds')}'" name="'factory.registeredFunds'" value="'${factory.registeredFunds?if_exists}'" cssClass="'underline'" required="false"/>
            </tr>
            <tr>
            	<@ww.textfield label="'${action.getText('factory.legal_person')}'" name="'factory.legalPerson'" value="'${factory.legalPerson?if_exists}'" cssClass="'underline'" />
            </tr>
            <tr>
            	 <@ww.textarea label="'${action.getText('factory.comment')}'" 
					         name="'factory.comment'" 
					         value="'${factory.comment?if_exists}'" rows="'3'" cols="'60'" 
				 />          
            </tr>           
         </@inputTable> 
         <@buttonBar>
           <#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateLevel();'"/>
		   </#if>
		    <#if '${requestSourceType?if_exists}' == 'PopupWin'>
		     <#if factory.id?exists>
		       <@vbutton name="'select'" class="button" value="${action.getText('select')}" onclick="javascript: returnDialog(new Array(#{factory.id}, '${factory.name}','${factory.supplierNo}'));"/>
		     </#if>
		       <@hrefButton value="${action.getText('back')}" url="${req.contextPath}/popup/factorySelector.html"/>
		    <#else>
		      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/prophase/factory/listFactory.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>	        
		    </#if>
        	
         </@buttonBar>
     </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
     
     window.onload = function() {
       <#if factory.id?exists>
         document.all.frame.src='${req.contextPath}/prophase/supplier/editSupplierExtInfo.html?factory.id=#{factory.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	 	 document.getElementById("supplierExtInfo").className = "selectedtab";
	   </#if>
       <#if factory.companyType?exists>
         document.getElementById("companyType.id").value = #{factory.companyType.id};
       </#if>
       <#if factory.supplierType?exists>
         document.getElementById("supplierType.id").value = #{factory.supplierType.id};
       </#if>
        <#if factory.country?exists>
         document.getElementById("country.id").value = #{factory.country.id};
       </#if>
        <#if factory.tradeType?exists>
         document.getElementById("tradeType.id").value = #{factory.tradeType.id};
       </#if>
         
       }
       function levelChange_OpenDialog(supplierLevelHistoryId) {
	  	  var url = "${req.contextPath}/prophase/supplier/editSupplierLevelHistory.html?supplier.id="+supplierLevelHistoryId;
	  	  popupModalDialog(url, 800, 600,levelChanageHandler);
	  	}
	  	
   	   function levelChanageHandler(result) {
   	      document.forms["supplier"].elements["level.id"].value  = result[0]; 
   	   }
   	   
   	  
   	   function validateLevel(){
   	      
   	     //if(document.getElementById("supplier.name").value ==''){//供应商名称不能为空值
   	       if ('' == $F('factory.name')) {
   	       alert('${action.getText('factoryName.not.null')}');
   	       return false;
   	     }else{
   	       if (!isValidLength(document.forms[0],"factory.name",null,50)) {
	       alert("${action.getText('factory.name.maxLength')}");
		   return false;
	     }
   	     }	
   	     /*
   	    if(document.getElementById("supplier.registeredFunds").value!=''){   //注册资金为数子类型,否则提示错误信息
   	     if(!isNumber("supplier.registeredFunds")){
   	        alert('${action.getText('isRegisterFundsData')}');
   	        return false;
   	         }
   	     }
   	     
   	     */
   	     if(document.getElementById("factory.zone").value!='')	{
	       if(!isValidLength(document.forms[0], "supplier.zone", null, 50)){
			alert("${action.getText('factory.zone.length')}");
			return  false;
			   }
	         }
	     if(document.getElementById("factory.legalPerson").value!='')	{
	       if(!isValidLength(document.forms[0], "supplier.legalPerson", null, 50)){
			alert("${action.getText('factory.legalPerson.length')}");
			return  false;
			   }
	         }    
   	    if ('' != document.forms[0].elements["factory.registeredFunds"].value) {  //验证注册资金长度
	     if (!isValidLength(document.forms[0],"factory.registeredFunds",0,50)) {
	       alert("${action.getText("factory.registeredFunds.maxLength")}");
		   return false;
	     }
	    }
   	      if(document.getElementById("factory.comment").value!='')	{
	       if(!isValidLength(document.forms[0], "factory.comment", null, 250)){
			alert("${action.getText('factory.comment.length')}");
			return  false;
			   }
	         }	
   	       return true;
   	   }
	</script>
	
    <#if factory.id?exists>
     <ul id="beautytab">
	    	 <li><a id="supplierExtInfo" onclick="activeTab(this);" href="../../prophase/supplier/editSupplierExtInfo.html?supplier.id=#{factory.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('factory.factoryExInfo.information')}</a></li>
	    	 <li><a id="repairItem" onclick="activeTab(this);" href="../../prophase/supplier/listSupplierProducts.html?supplier.id=#{factory.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('productInfo')}</a></li>
			 <li><a id="Certification" onclick="activeTab(this);" href="../../prophase/supplier/listSupplierCertifications.html?supplier.id=#{factory.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('Certification')}</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
