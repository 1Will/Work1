<#include "../../includes/eam2008.ftl" />
<#assign supplierEditTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign supplierEditTitle = "${action.getText('deviceSupplier.edit')}"/>
  <#else>
  <#assign supplierEditTitle = "${action.getText('toolingSupplier.edit')}"/>
  </#if>
</#if>
<@htmlPage title="${supplierEditTitle}">
     <@ww.form  name="'supplier'" action="'saveSupplier'" method="'post'" validate="true">
          <@ww.token name="saveSupplierToken"/>     
          <@inputTable>
          <#if supplier.id?exists>
             <@ww.hidden name="'supplier.id'" value="#{supplier.id?if_exists}"/>  
              </#if>
             <@ww.hidden name="'supplier.version'" value="#{supplier.version?if_exists}"/>
            
             <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
             <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
             <tr>
                 <@ww.textfield label="'${action.getText('supplier.supplierNo')}'" name="'supplierNo'" value="'${supplier.supplierNo?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
                 <@ww.textfield label="'${action.getText('supplier.chineseName')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'" cssClass="'underline'" required="true" />	
             </tr>
             <tr>
	        	 <@ww.textfield label="'${action.getText('supplier.ename')}'" name="'supplier.enName'" value="'${supplier.enName?if_exists}'" cssClass="'underline'" />
	        	 <@ww.select label="'${action.getText('supplier.companyProperty')}'" 
	                   required="false" 
	                   name="'companyType.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="supplierComapanyProperty" 
                       emptyOption="true" 
                       disabled="false">
                       <#if supplier.companyType?exists>
                          <@ww.param name="'value'"  value="'${supplier.companyType.id?if_exists}'" />
                       </#if>
                 </@ww.select>
             </tr>
             <tr>
              	 <@ww.select label="'${action.getText('supplier.cartory')}'" 
	                   required="false" 
	                   name="'supplierType.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="supplierCatory" 
                       emptyOption="true" 
                       disabled="false">
                       <#if supplier.supplierType?exists>
                       <@ww.param name="'value'"  value="'${supplier.supplierType.id?if_exists}'" />
                        </#if>
                 </@ww.select>
                 <@ww.select label="'${action.getText('supplier.trade')}'" 
	                   required="false" 
	                   name="'tradeType.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="supplierTrade" 
                       emptyOption="true" 
                       disabled="false">
                       <#if supplier.tradeType?exists>
                       <@ww.param name="'value'"  value="'${supplier.tradeType.id?if_exists}'" />
                       </#if>
                 </@ww.select>        	
             </tr> 
             <tr>
                 <@ww.select label="'${action.getText('supplier.country')}'" 
	                  required="false" 
	                  name="'country.id'" 
    			      listKey="id" 
    			      listValue="name"
                      list="supplierCountry" 
                      emptyOption="true" 
                      disabled="false">
                      <#if supplier.country?exists>
                      <@ww.param name="'value'"  value="'${supplier.country.id?if_exists}'" />
                      </#if>
                </@ww.select>
                <@ww.textfield label="'${action.getText('supplier.zone')}'" name="'supplier.zone'" value="'${supplier.zone?if_exists}'" cssClass="'underline'" />	  
            </tr>
            <tr>
                <@ww.textfield label="'${action.getText('supplier.register_funds')}'" name="'supplier.registeredFunds'" value="'${supplier.registeredFunds?if_exists}'" cssClass="'underline'" required="false"/>  
                <@ww.textfield label="'${action.getText('supplier.legal_person')}'" name="'supplier.legalPerson'" value="'${supplier.legalPerson?if_exists}'" cssClass="'underline'" />  
            </tr>     
            <tr>
              <@ww.select label="'${action.getText('supplier.level')}'" 
	                  required="false" 
	                  name="'level.id'" 
    			      listKey="id" 
    			      listValue="value"
                      list="supplierLevel" 
                      emptyOption="true" 
                      required="true"
                      disabled="false">
                </@ww.select>
            </tr>
            <tr>
                  <@ww.textarea label="'${action.getText('supplier.demo')}'" 
					         name="'supplier.comment'" 
					         value="'${supplier.comment?if_exists}'" rows="'3'" cols="'60'" 
				 />          
            </tr>           
         </@inputTable> 
         <@buttonBar>
           <#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateLevel();'"/>  
	         <#if supplier.id?exists>
			 	 <@vbutton class="button" name="'change'" value="${action.getText('supplier.changeLevel')}" onclick="levelChange_OpenDialog('#{supplier.id}');"/>
		 	 </#if>
		   </#if>
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/prophase/supplier/listSuppliers.html?&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>	        
         </@buttonBar>
     </@ww.form>
    <script language="JavaScript" type="text/JavaScript"> 
     
     window.onload = function() {
       <#if supplier.id?exists>
         document.all.frame.src='${req.contextPath}/prophase/supplier/editSupplierExtInfo.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	 	 getObjByNameRe("supplierExtInfo").className = "selectedtab";
	   </#if>
       <#if supplier.companyType?exists>
         getObjByNameRe("companyType.id").value = #{supplier.companyType.id};
       </#if>
       <#if supplier.supplierType?exists>
         getObjByNameRe("supplierType.id").value = #{supplier.supplierType.id};
       </#if>
        <#if supplier.country?exists>
         getObjByNameRe("country.id").value = #{supplier.country.id};
       </#if>
        <#if supplier.tradeType?exists>
         getObjByNameRe("tradeType.id").value = #{supplier.tradeType.id};
       </#if>
        <#if supplier.level?exists>
         getObjByNameRe("level.id").value = #{supplier.level.id};
       </#if>
       <#if supplier.id?exists&&level?exists>
       disableElements(document.forms[0], new Array("level.id"),true);//如果供应商存在，供应商等级不可改变
       </#if> 
         
       }
       function levelChange_OpenDialog(supplierLevelHistoryId) {
	  	  var url = "${req.contextPath}/prophase/supplier/editSupplierLevelHistory.html?supplier.id="+supplierLevelHistoryId;
	  	  popupModalDialog(url, 1024, 900,levelChanageHandler);
	  	}
	  	
   	   function levelChanageHandler(result) {
   	      document.forms["supplier"].elements["level.id"].value  = result[0]; 
   	   }
   	   
   	  
   	   function validateLevel(){
   	      
   	     //if(getObjByNameRe("supplier.name").value ==''){//供应商名称不能为空值
   	       if ('' == $F('supplier.name')) {
   	       alert('${action.getText('supplierName.not.null')}');
   	       return false;
   	     }else{
   	       if (!isValidLength(document.forms[0],"supplier.name",null,50)) {
	       alert("${action.getText('supplier.name.maxLength')}");
		   return false;
	     }
   	     }
   	     if(getObjByNameRe("supplier.enName").value!='')	{
	       if(!isValidLength(document.forms[0], "supplier.enName", null, 50)){
			alert("${action.getText('supplier.enName.length')}");
			return  false;
			   }
	         }	
   	    <#--
   	    if(getObjByNameRe("supplier.registeredFunds").value!=''){   //注册资金为数子类型,否则提示错误信息
   	     if(!isNumber("supplier.registeredFunds")){
   	        alert('${action.getText('isRegisterFundsData')}');
   	        return false;
   	         }
   	     }
   	     -->
   	     
   	     <#--
   	     if(getObjByNameRe("factory.zone").value!='')	{
	       if(!isValidLength(document.forms[0], "factory.zone", null, 50)){
			alert("${action.getText('supplier.zone.length')}");
			return  false;
		   }
	     }
	     -->
	     if(getObjByNameRe("supplier.legalPerson").value!='')	{
	       if(!isValidLength(document.forms[0], "supplier.legalPerson", null, 50)){
			alert("${action.getText('supplier.legalPerson.length')}");
			return  false;
			   }
	         }    
   	    if ('' != document.forms[0].elements["supplier.registeredFunds"].value) {  //验证注册资金长度
	     if (!isValidLength(document.forms[0],"supplier.registeredFunds",0,50)) {
	       alert("${action.getText("supplier.registeredFunds.maxLength")}");
		   return false;
	     }
	    }
   	     if(getObjByNameRe("level.id").value ==''){//供应商等级不能为空值
   	       alert('${action.getText('supplierLevel.not.null')}');
   	       return false;
   	       }
   	      if(getObjByNameRe("supplier.comment").value!='')	{
	       if(!isValidLength(document.forms[0], "supplier.comment", null, 250)){
			alert("${action.getText('supplier.comment.length')}");
			return  false;
			   }
	         }	
   	       return true;
   	   }
	</script>
	
    <#if supplier.id?exists>
     <ul id="beautytab">
	    	 <li><a id="supplierExtInfo" onclick="activeTab(this);" href="../../prophase/supplier/editSupplierExtInfo.html?supplier.id=#{supplier.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('supplier.supplierExInfo.information')}</a></li>
	    	 <li><a id="repairItem" onclick="activeTab(this);" href="listSupplierProducts.html?supplier.id=#{supplier.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('productInfo')}</a></li>
	    	 <li><a id="Certification" onclick="activeTab(this);" href="listSupplierCertifications.html?supplier.id=#{supplier.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('Certification')}</a></li>
	    	<#-- <li><a id="supplierBusinessHistory" onclick="activeTab(this);" href="listSupplierBusinessHistorys.html?supplier.id=#{supplier.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('supplierBusinessHistory')}</a></li>-->
	    	 <li><a id="supplierLevelHistory" onclick="activeTab(this);" href="listSupplierLevelHistory.html?supplier.id=#{supplier.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('supplierLevelHistory')}</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
