<@inputTable>
        <@ww.hidden name="'fromSpareInBillDetail'" value="'${req.getParameter('fromSpareInBillDetail')?if_exists}'"/>
 		<tr>
 			<@ww.textfield label="'${action.getText('code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" required="false"/>
 			<#-- 仓库 -->
 			<#if req.getParameter('fromSpareInBillDetail')?exists&&req.getParameter('fromSpareInBillDetail')=="true">
        		<@ww.select 
				label="'${action.getText('warehouse')}'" 
				name="'warehouse'" 
				value="'${req.getParameter('warehouse')?if_exists}'" 
				listKey="id" 
				listValue="name"
				list="allWarehouseName" 
				disabled="false"
				onchange="'WareCascadeRegionalDWR(\"warehouse\",\"regional\",\"${action.getText('all')}\",\"search\")'"
				onmousemove="'this.setCapture();'" onmouseout="'this.releaseCapture();'" onfocus="'this.blur();'">
			    </@ww.select>

	 		<#else>
	 		 	<@ww.select 
					label="'${action.getText('warehouse')}'" 
					name="'warehouse'" 
					value="'${req.getParameter('warehouse')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allWarehouseName" 
					disabled="false"
					onchange="'WareCascadeRegionalDWR(\"warehouse\",\"regional\",\"${action.getText('all')}\",\"search\")'">
				</@ww.select>
	   		
	 		</#if>
       	 	
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
 			<#--
 			
 			<@ww.select
					label="'${action.getText('warehouse')}'"
					name="'warehouse'"
					list="allWarehouseName"
					listKey="id"
					listValue="name"
	        />
	        <@ww.select label="'${action.getText('regional')}'"
					name="'regional'"
					list="allRegional"
					listKey="id"
					listValue="name"
			/>-->
		</tr>
 		<tr>
 			<@ww.select label="'${action.getText('locationType')}'"
	                name="'locationType'"
					list="allLocationType"
					listKey="id"
					listValue="value"
	        />
 			<@ww.select label="'${action.getText('bearload')}'"
	                name="'bearload'"
					list="allBearload"
					listKey="id"
					listValue="value"
	        />
	        <td align="right" valign="top"><label  class="label">${action.getText('status')}:</label></td>
	        <td>
	        <select name="status" id="status">
	        	<option value="">${action.getText('all')}</option>
	        	<option value="USED">${action.getText('USED')}</option>
	        	<option value="NON_USE">${action.getText('NON_USE')}</option>
	        </select>
	        </td>
		</tr>
		<tr>
			<@eam2008_onlySearchInvalid_checkBox/>
		</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">

  	//仓库级联库区

    	<#if req.getParameter('warehouse')?exists>
    		$('warehouse').value='${req.getParameter('warehouse')?if_exists}';
    		//设置同步
    		DWREngine.setAsync(false); 
    		//获得仓库的值后触发DWR 级联库区 
			WareCascadeRegionalDWR("warehouse","regional","${action.getText('all')}","search");
    		//重新设置为异步方式
    		DWREngine.setAsync(true);
    		<#--
    	<#elseif req.getParameter('roleWarehouseId')?exists>
	        $('warehouse').value='${req.getParameter('roleWarehouseId')?if_exists}';
    		//设置同步
    		DWREngine.setAsync(false); 
    		//获得仓库的值后触发DWR 级联库区 
			WareCascadeRegionalDWR("warehouse","regional","${action.getText('all')}","search");
    		//重新设置为异步方式
    		DWREngine.setAsync(true); -->
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
		    
	//加载库位类型
			var inStatusSelector = document.all("locationType");
		  	inStatusGroups = inStatusSelector.options.length;
		    <#if req.getParameter('locationType')?exists>
			  	for (i=0; i<inStatusGroups; i++) {
			    	if (inStatusSelector.options[i].value == "${req.getParameter('locationType')?if_exists}") {
			      	    inStatusSelector.options[i].selected="true";
			    	}
			    }
		    </#if>
	//加载承载类型
			var inStatusSelector = document.all("bearload");
		  	inStatusGroups = inStatusSelector.options.length;
		    <#if req.getParameter('bearload')?exists>
			  	for (i=0; i<inStatusGroups; i++) {
			    	if (inStatusSelector.options[i].value == "${req.getParameter('bearload')?if_exists}") {
			      	    inStatusSelector.options[i].selected="true";
			    	}
			    }
		    </#if>
 var inStatusSelector = document.all("status");
  	inStatusGroups = inStatusSelector.options.length;
  	for (i=0; i<inStatusGroups; i++) {
    <#if req.getParameter('status')?exists>
    	if (inStatusSelector.options[i].value == "${req.getParameter('status')?if_exists}") {
      	    inStatusSelector.options[i].selected="true";
    	}
    </#if>

    }
  function checkInvalidParms(){
    <#-- 若仓库选择所有,在控制层修改为可查询当前登录用户的权限仓库
    if(document.getElementById("warehouse").value == -1) {
	  document.getElementById("warehouse").value = '';
	}
	-->
    if(document.getElementById("regional").value == -1) {
	  document.getElementById("regional").value = '';
	}
    if(document.getElementById("locationType").value == -1) {
	  document.getElementById("locationType").value = '';
	}
    if(document.getElementById("bearload").value == -1) {
	  document.getElementById("bearload").value = '';
	}
	if(document.getElementById("warehouse").value == -1) {
	  document.getElementById("warehouse").value = '';
	}
	if(document.getElementById("warehouse").value == -1) {
	  document.getElementById("warehouse").value = '';
	}
      return true;
  }   
</script>