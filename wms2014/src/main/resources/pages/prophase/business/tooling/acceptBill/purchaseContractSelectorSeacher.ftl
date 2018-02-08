<@inputTable>
  <tr>
    <@ww.textfield label="'${action.getText('purchaseContractBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('purchaseContractName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
  </tr>
  <tr>
	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		     value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                 list="departments" emptyOption="false" disabled="false">
    </@ww.select>
	<@ww.select label="'${action.getText('categoryName')}'" required="false" 
	                 name="'category.id'" value="'${req.getParameter('category.id')?if_exists}'"
		             listKey="id" listValue="value" list="categorys" 
		             emptyOption="false" disabled="false" required="false">
		 </@ww.select>
  </tr>
  <tr>
    <@ww.textfield label="'${action.getText('graphNo')}'" name="'graphNo'" value="'${req.getParameter('graphNo')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('ProductName')}'" name="'productName'" value="'${req.getParameter('productName')?if_exists}'" cssClass="'underline'"/>
  </tr>
  <tr>
    <@ww.textfield label="'${action.getText('model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'" cssClass="'underline'"/>
  </tr>
 <tr>
     <@pp.dateRanger label="'${action.getText('purchaseDateTime')}'" name="'purchaseDate'" 
		       value="'${req.getParameter('purchaseDate_start')?if_exists}|${req.getParameter('purchaseDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
  </tr>
</@inputTable> 
 <script language="JavaScript" type="text/JavaScript"> 
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  <#if first>
    <#if loginUser.department?exists>
      getObjByNameRe("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  
  var selector = document.all("category.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('category.id')?exists>
    if (selector.options[i].value == "${req.getParameter('category.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
 function checkInvalidParms() {
  	if (getObjByNameRe("department.id").value == -1) {
  		getObjByNameRe("department.id").value = '';
	}
	if (getObjByNameRe("category.id").value == -1) {
  		getObjByNameRe("category.id").value = '';
	}
 }
 </script>