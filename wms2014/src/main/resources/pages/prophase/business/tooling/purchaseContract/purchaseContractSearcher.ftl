<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('purchaseContract.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('purchaseContract.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	
	<tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
		<@ww.textfield label="'${action.getText('buying.Person')}'" name="'buyer.name'" value="'${req.getParameter('buyer.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		 <@pp.dateRanger label="'${action.getText('purchaseBill.purchaseDate')}'" name="'purchaseDate'" 
		       value="'${req.getParameter('purchaseDate_start')?if_exists}|${req.getParameter('purchaseDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	<@ww.textfield label="'${action.getText('supplier.name')}'" name="'supplier.name'" value="'${req.getParameter('supplier.name')?if_exists}'" cssClass="'underline'" />
	
	</tr>
	<tr>

      <@ww.select label="'${action.getText('purchase.state')}'" 
           required="false" name="'status'" 
           value="'${status?if_exists}'"
           listKey="value" listValue="label"
           list="purchaseStatus" 
           emptyOption="false" 
           disabled="false">
          </@ww.select>	
          <@ww.hidden name="'typeStatus'" value="''"/>
	</tr>
	<tr>
		<@eam2008_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>	
<script language="javascript" type="text/JavaScript">
	var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
   <#if first>
    <#if loginUser.department?exists>
      getObjByNameRe("department.id").value = #{loginUser.department.id};
    </#if>
  </#if> 
  } 
   
  
   var selector = document.all("status");
   groups = selector.options.length;
   for (i=0; i<groups; i++) {
    <#if req.getParameter('status')?exists>
    if (selector.options[i].value == "${req.getParameter('status')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
function checkInvalidParms(){
	     if (getObjByNameRe("department.id").value == -1) {
	     getObjByNameRe("department.id").value = '';
	   }  
	    if (getObjByNameRe("typeStatus").value == -1) {
          getObjByNameRe("typeStatus").value = '';
         }
       	if (getObjByNameRe("status").value == -1) {
          getObjByNameRe("status").value = '';
         }
         
      if(document.forms[0].elements["purchaseDate_start"].value!=""){
          if(!validateTime(document.forms[0].elements["purchaseDate_start"].value)){
               alert("${action.getText('purchaseDate.start_EndTimeMistake')}");
               return false;
          }
      }
      if(document.forms[0].elements["purchaseDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["purchaseDate_end"].value)){
               alert("${action.getText('purchaseDate.start_EndTimeMistake')}");
               return false;
          }
      }
      return true;   
	}    
 </script>