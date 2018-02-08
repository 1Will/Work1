<@inputTable>
 	<@ww.select 
				label="'${action.getText('仓库')}'"
        		name="'warehouse.id'"
       	 		listKey="id" 
        		listValue="name"
        		value="'${req.getParameter('warehouse.id')?if_exists}'"
        		list="allWarehouse" 
        		emptyOption="false" 
        		disabled="false"
        		onchange=""
        		>
        		</@ww.select>   
</@inputTable>


<script language="javascript" type="text/JavaScript">
	var warehouseSelector = document.all("warehouse.id");
  	warehouseGroups = warehouseSelector.options.length;
  	for (i=0; i<warehouseGroups; i++) {
	    <#if req.getParameter('warehouse.id')?exists>
	    	if (warehouseSelector.options[i].value == "${req.getParameter('warehouse.id')?if_exists}") {
	      	warehouseSelector.options[i].selected="true";
	    	}
	    </#if>
    }
    
       function checkInvalidParms(){
	     	if(document.forms[0].elements["warehouse.id"].value == -1){
	        	document.forms[0].elements["warehouse.id"].value = '';
	     	}
     	
     	}
 
   
</script>
		