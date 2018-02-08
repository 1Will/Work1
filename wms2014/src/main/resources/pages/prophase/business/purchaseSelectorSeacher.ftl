<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('purchaseBill.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('purchaseBill.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	  <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
	   <@ww.textfield label="'${action.getText('buying.Person')}'" name="'buyer.name'" value="'${req.getParameter('buyer.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	   <@ww.textfield label="'${action.getText('name')}'" name="'name1'" value="'${req.getParameter('name1')?if_exists}'" cssClass="'underline'" />	 
	<@ww.textfield label="'${action.getText('model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />	
	
	</tr>
	<tr>
	     <@ww.textfield label="'${action.getText('specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'"  cssClass="'underline'" />
		 <@pp.dateRanger label="'${action.getText('purchaseBill.purchaseDate')}'" name="'purchaseDate'" 
		       value="'${req.getParameter('purchaseDate_start')?if_exists}|${req.getParameter('purchaseDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	</tr>
	<tr>
        <@ww.textfield label="'${action.getText('supplier.name')}'" name="'supplier.name'" value="'${req.getParameter('supplier.name')?if_exists}'" cssClass="'underline'" />
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
function checkInvalidParms(){
	  if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	}  
	}    
 </script>