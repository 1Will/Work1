
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('supplier.supplierNo')}'" name="'supplier.supplierNo'" 
					   value="'${req.getParameter('supplier.supplierNo')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('supplier.name')}'" name="'supplier.name'" 
					   value="'${req.getParameter('supplier.name')?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('supplier.supplierType')}'"
			required="false"
			name="'supplier.supplierType'" 
			value="'${req.getParameter('supplier.supplierType')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allSupplierType"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
	<tr>
		<@ww.select 
    		label="'${action.getText('supplier.tradeType')}'"
			required="false"
			name="'supplier.tradeType'" 
			value="'${req.getParameter('supplier.tradeType')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allTradeType"
	    	emptyOption="false" 
	    	disabled="false"/>
		<@ww.select label="'${action.getText('supplier.companyType')}'"
			required="false"
			name="'supplier.companyType'" 
			value="'${req.getParameter('supplier.companyType')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allCompanyType"
	    	emptyOption="false" 
	    	disabled="false"/>	    	
	    	<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
    var selector=document.all("supplier.supplierType");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('supplier.supplierType')?exists>
        if(selector.options[i].value=="${req.getParameter('supplier.supplierType')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    
    var selector=document.all("supplier.tradeType");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('supplier.tradeType')?exists>
        if(selector.options[i].value=="${req.getParameter('supplier.tradeType')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    
    var selector=document.all("supplier.companyType");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('supplier.companyType')?exists>
        if(selector.options[i].value=="${req.getParameter('supplier.companyType')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    function checkInvalidParms(){
           if (getObjByName("supplier.supplierType").value==0){
              getObjByName("supplier.supplierType").value='';
           }
           if (getObjByName("supplier.tradeType").value==0){
              getObjByName("supplier.tradeType").value='';
           }
           if (getObjByName("supplier.companyType").value==0){
              getObjByName("supplier.companyType").value='';
           }
          return true;
    }
         
</script>