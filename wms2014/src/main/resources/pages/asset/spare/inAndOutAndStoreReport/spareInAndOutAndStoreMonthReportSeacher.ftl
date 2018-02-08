<#--月报表查询条件 04/24/2009-->
<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('spareNo')}'" 
					   name="'spareNo'" 
					   value="'${req.getParameter('spareNo')?if_exists}'" 
					   cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('spareName')}'" 
	    			   name="'spareName'" 
	    			   value="'${req.getParameter('spareName')?if_exists}'" 
	    			   cssClass="'underline'"/> 
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('modelSpecs')}'" 
					   name="'modelSpecs'" 
					   value="'${req.getParameter('modelSpecs')?if_exists}'" 
					   cssClass="'underline'"/> 
		<#-- 仓库 -->
       	 	<@ww.select 
				label="'${action.getText('warehouse')}'" 
				name="'warehouse'" 
				value="'${req.getParameter('warehouse')?if_exists}'" 
				listKey="id" 
				listValue="name"
				list="allWarehouseName" 
				disabled="false"
				onchange="'WareCascadeRegionalDWR(\"warehouse\",\"regional\",\"${action.getText('select.option.all')}\",\"search\")'">
			</@ww.select>
	</tr>
	<tr>
	    <#-- 库区 -->
			<@ww.select 
				label="'${action.getText('regional')}'" 
				name="'regional'" 
				value="'${req.getParameter('regional')?if_exists}'" 
				listKey="id" 
				listValue="name"
				list="allRegional" 
				disabled="false">
			</@ww.select>
		<@ww.textfield label="'${action.getText('locationCode')}'" name="'locationCode'" value="'${req.getParameter('locationCode')?if_exists}'" cssClass="'underline'"/>
		 
	</tr>					
	<tr>
	    <@ww.textfield label="'${action.getText('custos')}'" 
			   name="'custos'" value="'${req.getParameter('custos')?if_exists}'" 
			   cssClass="'underline'"/>	
	   	<@pp.datePicker label="'${action.getText('month')}'" 
    					name="'month'"
	     				value="'${req.getParameter('month')?if_exists}'" 
	     				cssClass="'underline'" 
	     				size="15" 
	     				dateFormat="'%Y-%m'"/>
	    
       </tr>
       <tr>
       <@ww.select label="'${action.getText('toolingDevFlag')}'" 
           			required="false" 
           			name="'toolDevFlag'" 
           			value="'${toolingDevFlag?if_exists}'"
           			listKey="value" 
           			listValue="label"
           			list="toolDevFlag" 
           			emptyOption="false" 
           			disabled="false"/>
		<@ww.checkbox label="'${action.getText('onlyDisplay')}'"
					  name="'onlyCheck'" 
					  value="'false'" 
					  fieldValue="'false'" 
					  onblur="'changValidOrInvalidStatus()'"/>
	</tr>
</@inputTable>
<script language="JavaScript" type="text/JavaScript">
    //仅显示发生额
	function changValidOrInvalidStatus(){
		if(document.forms[0].elements["onlyCheck"].checked){
			document.forms[0].elements["onlyCheck"].checked=true;
			document.forms[0].elements["onlyCheck"].value=true;
		}
		else{
			document.forms[0].elements["onlyCheck"].checked=false;
			document.forms[0].elements["onlyCheck"].value=false;
		}
		return true;
	}
	<#if req.getParameter('onlyCheck')?exists>
		document.forms[0].elements["onlyCheck"].checked=true;
		document.forms[0].elements["onlyCheck"].value=true;
	<#else>
	    document.forms[0].elements["onlyCheck"].checked=false;
		document.forms[0].elements["onlyCheck"].value=false;
	</#if>  
	//刷新页面后保存类型条件
	var toolingDevFlagSelector = document.all("toolDevFlag");
  	toolingDevFlagGroups = toolingDevFlagSelector.options.length;
  	for (i=0; i<toolingDevFlagGroups; i++) {
    <#if req.getParameter('toolDevFlag')?exists>
    	if (toolingDevFlagSelector.options[i].value == "${req.getParameter('toolDevFlag')?if_exists}") {
      	    toolingDevFlagSelector.options[i].selected="true";
    	}
    </#if>
    }  
	function checkInvalidParms()
	{/***
		if(document.forms[0].elements["month"].value==''){
			alert("${action.getText('month.error')}");
			return false;
		}*/
		
		//当所选仓库为所有时，将下拉列表框的值设置为空，查询所有
		if (getObjByNameRe("warehouse").value == -1) {
	  		getObjByNameRe("warehouse").value = '';
		}
		
		//当所库区为所有时，将下拉列表框的值设置为空，查询所有
		if (getObjByNameRe("regional").value == -1) {
	  		getObjByNameRe("regional").value = '';
		}
		
		//类型为所有，将类型的值置为空
		if (getObjByNameRe("toolDevFlag").value == -1) {
	  		getObjByNameRe("flag").value = '';
		}
		if(getObjByNameRe("toolDevFlag").value=='DEVICE'){
		   document.forms[0].elements["flag"].value='设备';
		}
		if(getObjByNameRe("toolDevFlag").value=='TOOLING'){
		   document.forms[0].elements["flag"].value='工装';
		}
		return true;		
	}
	
	//仓库级联库区
    	<#if req.getParameter('warehouse')?exists>
    		getObjByName('warehouse').value='${req.getParameter('warehouse')?if_exists}';
    		//设置同步
    		DWREngine.setAsync(false); 
    		//获得仓库的值后触发DWR 级联库区 
			WareCascadeRegionalDWR("warehouse","regional","${action.getText('select.option.all')}","search");
    		//重新设置为异步方式
    		DWREngine.setAsync(true); 
    	<#elseif req.getParameter('roleWarehouseId')?exists>
	        getObjByName('warehouse').value='${req.getParameter('roleWarehouseId')?if_exists}';
    		//设置同步
    		DWREngine.setAsync(false); 
    		//获得仓库的值后触发DWR 级联库区 
			WareCascadeRegionalDWR("warehouse","regional","${action.getText('select.option.all')}","search");
    		//重新设置为异步方式
    		DWREngine.setAsync(true); 
    	</#if>
    	
	  	//加载库区
		var inStatusSelector = document.all("regional");
		  	inStatusGroups = inStatusSelector.options.length;
		    <#if req.getParameter('regional')?exists>
			  	for (i=0; i<inStatusGroups; i++) {
			    	if (inStatusSelector.options[i].value == "${req.getParameter('regional')?if_exists}") {
			      	    inStatusSelector.options[i].selected="true";
			    	}
			    }
		    </#if>
 </script>