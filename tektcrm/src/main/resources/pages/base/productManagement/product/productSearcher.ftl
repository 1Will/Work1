
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('products.code')}'" name="'products.code'" value="'${req.getParameter('products.code')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('products.name')}'" name="'products.name'" value="'${req.getParameter('products.name')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('products.model')}'" name="'products.model'" value="'${req.getParameter('products.model')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('products.standard')}'" name="'products.standard'" value="'${req.getParameter('products.standard')?if_exists}'" cssClass="'underline'"/>
		<@ww.select 
    		label="'${action.getText('products.pt.id')}'"
			required="false"
			name="'pt.id'" 
			value="'${req.getParameter('pt.id')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allType"
	    	emptyOption="false" 
	    	disabled="false"/>
		<@ww.select 
    		label="'${action.getText('products.productSource')}'"
			required="false"
			name="'product_source_ID.id'" 
			value="'${req.getParameter('product_source_ID.id')?if_exists}'" 
			listKey="id"
			listValue="name"
		    list="allProductSource"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
	<tr>
		<@ww.select 
    		label="'${action.getText('products.supplier')}'"
			required="false"
			name="'supplier.id'" 
			value="${req.getParameter('supplier.id')?if_exists}" 
			listKey="id"
			listValue="name"
		    list="allSupplier"
		    emptyOption="false" 
		    disabled="false"/>
	    	<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
    var selector=document.all("pt.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('pt.id')?exists>
        if(selector.options[i].value=="${req.getParameter('pt.id')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    
    var selecter=document.all("product_source_ID.id");
    var group=selecter.options.length;
    for(i=0;i<group;i++){
      <#if req.getParameter('product_source_ID.id')?exists>
        if(selecter.options[i].value=="${req.getParameter('product_source_ID.id')?if_exists}"){
           selecter.options[i].selected="true";
        }
      </#if>
    }
    
    var selector=document.all("supplier.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('supplier.id')?exists>
        if(selector.options[i].value=="${req.getParameter('supplier.id')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    function checkInvalidParms(){
           if (getObjByName("pt.id").value==0){
              getObjByName("pt.id").value='';
           }
           if (getObjByName("supplier.id").value==-1){
              getObjByName("supplier.id").value='';
           }
           if (getObjByName("product_source_ID.id").value==-1){
              getObjByName("product_source_ID.id").value='';
           }
          return true;
    }
         
</script>