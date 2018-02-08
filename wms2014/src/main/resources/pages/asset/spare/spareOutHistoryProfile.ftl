<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('spareOutBill.title')}">
   <base target="_self">
	 <@ww.form name="'spare'" action="'saveSpareOutHistory'" method="'post'" validate="true">
	 	<@ww.token name="saveSpareOutHistoryToken"/>
	 	  <@inputTable>
	 	   <#if sparInOutBill?exists>
	 	     <@ww.hidden name="'sparInOutBill.id'" value="#{sparInOutBill.id?if_exists}"/>
	 	   </#if>
	 	   <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('spareOutBill.billNO')}'" name="'sparInOutBill.bill.billNo'" value="'${sparInOutBill.bill.billNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"/>
	 	  		<@ww.textfield label="'${action.getText('spareOutBill.inoutDateTm')}'"  name="'sparInOutBill.bill.inoutDateTm'" value="'${(sparInOutBill.bill.inoutDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"/>
	 	    </tr>
	 	    <tr>
	 	  		<@ww.textfield label="'${action.getText('spareOutBill.inoutPeople')}'"  name="'sparInOutBill.bill.inoutPeople'" value="'${sparInOutBill.bill.inoutPeople?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"/> 
	            <@ww.textfield label="'${action.getText('spareOutBill.category')}'" name="'sparInOutBill.spare.category.value'" value="'${sparInOutBill.spare.category.value?if_exists}'" disabled="true" readonly="true" cssClass="'underline'" />
	        </tr>
	            <@ww.textfield label="'${action.getText('spareOutBill.name')}'" name="'sparInOutBill.spare.name'" value="'${sparInOutBill.spare.name?if_exists}'" cssClass="'underline'" readonly="true"  disabled="true"/>
	           <@ww.textfield label="'${action.getText('spareOutBill.modelSpecs')}'" name="'sparInOutBill.spare.modelSpecs'" value="'${sparInOutBill.spare.modelSpecs?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
	        </tr>
	        <tr>
	           <@ww.textfield label="'${action.getText('spareOutBill.unit')}'" name="'sparInOutBill.spare.unit'" value="'${sparInOutBill.spare.unit?if_exists}'" cssClass="'underline'" readonly="true"  disabled="true" />
	           <@ww.textfield label="'${action.getText('spareOutBill.before')}'" name="'sparInOutBill.beforenumber'" value="'${sparInOutBill.number?if_exists}'" cssClass="'underline'" readonly="true"   disabled="true" />
	       	</tr>
	       	<tr>
	           <@ww.textfield label="'${action.getText('spareOutBill.number')}'" name="'sparInOutBill.number'" value="'${sparInOutBill.number?if_exists}'" cssClass="'underline'" required="true"  />
	           <@ww.textfield label="'${action.getText('spareOutBill.unit_price')}'" name="'sparInOutBill.spare.unitPrice'" value="'${sparInOutBill.spare.unitPrice?if_exists}'" cssClass="'underline'" required="false" disabled="true" />
	       	</tr>
	       	<tr>
	       	   <#assign sumFee=1 />
	       	   <#assign sumFee=sumFee*sparInOutBill.number />
	       	   <#assign sumFee=sumFee*sparInOutBill.spare.unitPrice />
	           <@ww.textfield label="'${action.getText('spareOutBill.sumFee')}'" name="'sparInOutBill.sumFee'" value="${sumFee}" cssClass="'underline'" disabled="true" readonly="true"  />
	           <@ww.textfield label="'${action.getText('spareOutBill.sumStock')}'" name="'sparInOutBill.spare.stocks'" value="'${sparInOutBill.spare.stocks?if_exists}'" cssClass="'underline'" required="false" disabled="true" />
	       	</tr>		       		 	  	 
		 </@inputTable> 
	 	  <@buttonBar>
	 	    <#if !(action.isReadOnly())>	
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return checkNumberStatus();'" />  
	        </#if>
	        	<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
	     </@buttonBar>
	</@ww.form>
		<script language="JavaScript" type="text/JavaScript">
	  function changValue(){
	     var unitPrice=document.forms[0].elements["sparInOutBill.spare.unitPrice"].value;
	     var number=document.forms[0].elements["sparInOutBill.number"].value;
	     var sum=parseInt(unitPrice)*parseInt(number);
	     document.forms[0].elements["sparInOutBill.sumFee"].value=sum;
	  }
	  
	  	  
	  function checkNumberStatus(){
	   if(document.forms[0].elements["sparInOutBill.number"].value==""){
	     alert("${action.getText('sparInOutBill.number.required')}");
	     return false;
	   }
	   return isNumber(document.forms[0].elements["sparInOutBill.number"].value);
	  }
	  
	  
function isNumber(targetId) {
    var num = targetId;
    s = new String(num);
    var regu = "^[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        if(parseInt(s)>=10000000||parseInt(s)<=0){
         alert("${action.getText('sparInOutBill.number.int')}");
        return false;
        }else{
          return true;
        }
    } else {
        alert("${action.getText('sparInOutBill.number.int')}");
        return false;
    }
}
   </script>
</@htmlPage>