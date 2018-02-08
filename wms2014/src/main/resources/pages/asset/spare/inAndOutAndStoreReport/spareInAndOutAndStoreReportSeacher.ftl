<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('spareNo')}'" name="'spareNo'" value="'${req.getParameter('spareNo')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('spareName')}'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'"/> 
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'"/>  
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
		<@ww.select label="'${action.getText('department')}'"
	                    	name="'department.id'"
	                   	 	listKey="id" 
	                    	listValue="name"
	                    	value="'${req.getParameter('department.id')?if_exists}'"
	                    	list="departments" 
	                    	emptyOption="false" 
	                    	disabled="false"
	    />	
	    <@ww.select label="'${action.getText('toolingDevFlag')}'" 
           required="false" name="'toolDevFlag'" 
           value="'${toolingDevFlag?if_exists}'"
           listKey="value" listValue="label"
           list="toolDevFlag" 
           emptyOption="false" 
           disabled="false">
        </@ww.select>		
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('custos')}'" 
					   name="'custos'" value="'${req.getParameter('custos')?if_exists}'" 
					   cssClass="'underline'"/> 	
		<@pp.dateRanger label="'${action.getText('print.Date')}'" 
						name="'createTime'" 
		       			value="'${req.getParameter('createTime_start')?if_exists}|${req.getParameter('createTime_end')?if_exists}'"
						cssClass="'underline'" 
						dateFormat="date"/>
	</tr>
	<tr>
		<@ww.checkbox label="'${action.getText('onlyDisplay')}'"
					  name="'onlyCheck'" 
					  value="'false'" 
					  fieldValue="'value'" 
					  onblur="'changValidOrInvalidStatus()'"/>
					  <@ww.hidden name="'flag'" value="'no'"/>
	</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
    //仅显示发生额
	function changValidOrInvalidStatus(){
		if(document.forms[0].elements["onlyCheck"].checked){
			document.forms[0].elements["onlyCheck"].checked=true;
			document.forms[0].elements["flag"].value="yes";
			return true;
		}
  	}
	<#if req.getParameter('onlyCheck')?exists>
		document.forms[0].elements["onlyCheck"].checked=true;
		document.forms[0].elements["flag"].value="yes";
	</#if>
 	var toolingDevFlagSelector = document.all("toolDevFlag");
  	toolingDevFlagGroups = toolingDevFlagSelector.options.length;
  	for (i=0; i<toolingDevFlagGroups; i++) {
    <#if req.getParameter('toolDevFlag')?exists>
    	if (toolingDevFlagSelector.options[i].value == "${req.getParameter('toolDevFlag')?if_exists}") {
      	    toolingDevFlagSelector.options[i].selected="true";
    	}
    </#if>
    }  
 	var selector = document.all("department.id");
	var groups=selector.options.length;  
	<#if req.getParameter('department.id')?exists>
		for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
	</#if>
	function checkInvalidParms()
	{
		 <#-- 若仓库选择所有,在控制层修改为可查询当前登录用户的权限仓库-->
    	if(getObjByNameRe("warehouse").value == -1) {
	  		getObjByNameRe("warehouse").value = '';
		}
		
    	if(getObjByNameRe("regional").value == -1) {
	  		getObjByNameRe("regional").value = '';
		}
		
		if(document.forms[0].elements["createTime_start"].value!="")
     	{
			if(!validateTime(document.forms[0].elements["createTime_start"].value))
			{
               alert("${action.getText('spareInAndOutAndStoreReport.start_EndTimeMistake')}");
               return false;
			}
		}
		if(document.forms[0].elements["createTime_end"].value!="")
		{
			if(!validateTime(document.forms[0].elements["createTime_end"].value))
			{
               alert("${action.getText('month.error')}");
               return false;
			}
		}
		if (getObjByNameRe("department.id").value==-1){
              getObjByNameRe("department.id").value='';
           }
		if (getObjByNameRe("toolDevFlag").value == -1) {
	  		getObjByNameRe("toolDevFlag").value = '';
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

 