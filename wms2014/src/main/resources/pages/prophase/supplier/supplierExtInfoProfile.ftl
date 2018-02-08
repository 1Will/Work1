<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('supplier.title')}">
     <@ww.form  name="'supplierExtInfo'" action="'saveSupplierExtInfo'" method="'post'" validate="true">
      <@ww.hidden name="'supplier.id'" value="'${req.getParameter('supplier.id')?if_exists}'"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <@ww.token name="saveSupplierExtInfoToken"/>
<@inputTable>
	  <tr>
	 	   <#--<@ww.select label="'${action.getText('supplierExtInfo.peopleScale')}'" 
	                   required="false" 
	                   name="'supplierExtInfo.peopleScale'" 
    			       listKey="value" 
    			       listValue="label"
                       list="peopleScale" 
                       value="'${supplierExtInfo.peopleScale?if_exists}'"
                       emptyOption="true" 
                       disabled="false"
                       onchange="'peopleScale_change();'">
                 </@ww.select>-->
           <@ww.textfield label="'${action.getText('supplierExtInfo.peopleScale')}'" name="'supplierExtInfo.peopleScale'" value="'${supplierExtInfo.peopleScale?if_exists}'" cssClass="'underline'">	
              </@ww.textfield>
		   <@ww.textfield label="'${action.getText('supplierExtInfo.contact0')}'" name="'supplierExtInfo.contact0'" value="'${supplierExtInfo.contact0?if_exists}'" cssClass="'underline'" />	
	  </tr>
	  <tr> 
	 	   <@ww.textfield label="'${action.getText('supplierExtInfo.tel')}'" name="'supplierExtInfo.tel'" value="'${supplierExtInfo.tel?if_exists}'" cssClass="'underline'" />	
		   <@ww.textfield label="'${action.getText('supplierExtInfo.fax')}'" name="'supplierExtInfo.fax'" value="'${supplierExtInfo.fax?if_exists}'" cssClass="'underline'" />	 
	  </tr>
	  <tr>
	 	   <@ww.textfield label="'${action.getText('supplierExtInfo.site')}'" name="'supplierExtInfo.site'" value="'${supplierExtInfo.site?if_exists}'" cssClass="'underline'" />	
	 	   <@ww.textfield label="'${action.getText('supplierExtInfo.email')}'" name="'supplierExtInfo.email'" value="'${supplierExtInfo.email?if_exists}'" cssClass="'underline'"/>	
	  </tr>
	  <tr> 
	    <@ww.textarea label="'${action.getText('supplierExtInfo.commercialInstrument')}'"
					         name="'supplierExtInfo.commercialInstrument'" 
					         value="'${supplierExtInfo.commercialInstrument?if_exists}'" rows="'3'" cols="'60'" 
							 />       				
        <@ww.textarea label="'${action.getText('supplierExtInfo.businessVariety')}'" 
					         name="'supplierExtInfo.businessVariety'" 
					         value="'${supplierExtInfo.businessVariety?if_exists}'" rows="'3'" cols="'60'" 
							 />       
      </tr>
	  <tr> 
	    <@ww.textarea label="'${action.getText('supplierExtInfo.saleSupport')}'"
					         name="'supplierExtInfo.saleSupport'" 
					         value="'${supplierExtInfo.saleSupport?if_exists}'" rows="'3'" cols="'60'" 
							 />       				
        <@ww.textarea label="'${action.getText('supplierExtInfo.supportQos')}'" 
					         name="'supplierExtInfo.supportQos'" 
					         value="'${supplierExtInfo.supportQos?if_exists}'" rows="'3'" cols="'60'" 
							 />       
      </tr>
 </@inputTable>
    <#if !(action.isReadOnly())>
         <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>          
         </@buttonBar>
    </#if>
</@ww.form>
  <script language="javascript">
     function validate(){
       var number = document.forms[0].elements["supplierExtInfo.peopleScale"].value;
       if( document.forms[0].elements["supplierExtInfo.peopleScale"].value!=''){
       
          if(!isDoubleNumber("supplierExtInfo.peopleScale")){
            alert("${action.getText('peformat.error')}");
            return false;
          }
          else{
              if(!isNumberBetweenBoolen(number, 10000000001, 0)){
               alert("${action.getText('supplierExtInfo.peopleScale.maxLength')}");
                return false;
              }
          }
         }
         
     if(getObjByNameRe("supplierExtInfo.contact0").value!='')	{	  	
       if(!isValidLength(document.forms[0], "supplierExtInfo.contact0", null, 50)){
			alert("${action.getText('supplierExtInfo.contact0.length')}");
			return  false;
			   }	
	}  
	 if(getObjByNameRe("supplierExtInfo.commercialInstrument").value!=''){
			if(!isValidLength(document.forms[0], "supplierExtInfo.commercialInstrument", null, 250)){
				alert("${action.getText('supplierExtInfo.commercialInstrument.length')}");
				return false;
			   }
			} 
			
	  	if(getObjByNameRe("supplierExtInfo.businessVariety").value!=''){
			if(!isValidLength(document.forms[0], "supplierExtInfo.businessVariety", null, 250)){
				alert("${action.getText('supplierExtInfo.businessVariety1.length')}");
				return false;
			   }
			}
		if(getObjByNameRe("supplierExtInfo.saleSupport").value!=''){
			if(!isValidLength(document.forms[0], "supplierExtInfo.saleSupport", null, 250)){
				alert("${action.getText('supplierExtInfo.saleSupport.length')}");
				return false;
			   }
			}
		if(getObjByNameRe("supplierExtInfo.supportQos").value!=''){
			if(!isValidLength(document.forms[0], "supplierExtInfo.supportQos", null, 250)){
				alert("${action.getText('supplierExtInfo.supportQos.length')}");
			return false;
			   }	
		 }	
       return true;
  }
         
 </script>
</@framePage>
 