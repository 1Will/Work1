<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('regional.edit')}">
     <@ww.form  name="'listFrom'" action="'saveRegional'" namespace="'/regionalInfo'" method="'post'" >
     	 <@ww.token name="saveRegionalToken"/>
     	  <@inputTable>
     	  <#if regional.id?exists>
     	  	<@ww.hidden name="'regional.id'" value="#{regional.id}"/>
     	  </#if>
		     <tr>
		     <#if regional.id?exists>
				<@ww.textfield label="'${action.getText('regional.code')}'" name="'regional.code'" value="'${regional.code?if_exists}'" cssClass="'underline'" required="true" disabled="true"/><#--disabled="true"-->
			<#else>	
				<@ww.textfield label="'${action.getText('regional.code')}'" name="'regional.code'" value="'${regional.code?if_exists}'" cssClass="'underline'" required="true" />
			</#if>	
			    <@ww.textfield label="'${action.getText('regional.name')}'" name="'regional.name'" value="'${regional.name?if_exists}'" cssClass="'underline'" required="true"/>
				
		  	</tr>
		  	<tr>
		     <#-- 库存级别 -->
  		 <@ww.select label="'${action.getText('regional.storageGrade')}'" 
	                   required="true" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="false"
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${loginUser.id?if_exists},\"${action.getText('')}\");removeSupper();'">
                       <#if regional.storageGrade?exists>
                          <@ww.param name="'value'"  value="'${regional.storageGrade.id?if_exists}'" />
                       </#if>
         </@ww.select>
         
         	<@ww.select 
					label="'${action.getText('regional.warehouse')}'" 
					required="true" 
					name="'warehouse.id'" 
					value="'${req.getParameter('warehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allWarehouse" 
					emptyOption="true" 
					disabled="false" >
				
		</@ww.select>      
         
		  	
		  	</tr>
		  	<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('regional.comment')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="regional.comment" rows="4" cols="95" >${regional.comment?if_exists}</textarea>
		        </td>
			</tr>
     	  </@inputTable>
     	  <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         <@redirectButton value="${action.getText('back')}" url="listRegional.html"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>

<script language="javascript">
	window.onload= function(){
		
		    
		<#if regional.storageGrade?exists>
          $('storageGrade.id').value = #{regional.storageGrade.id?if_exists};
           DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	      wareHouseCascadeDWR("storageGrade.id","warehouse.id",${loginUser.id?if_exists},"${action.getText('')}")
	    		//重新设置为异步方式
	      DWREngine.setAsync(true);
       
        </#if> 
		    var inStatusSelector = document.all("warehouse.id");
		  	inStatusGroups = inStatusSelector.options.length;
		    <#if regional.warehouse?exists>
			  	for (i=0; i<inStatusGroups; i++) {
			    	if (inStatusSelector.options[i].value == "${regional.warehouse.id?if_exists}") {
			      	    inStatusSelector.options[i].selected="true";
			    	}
			    }
		    </#if>
		    
    }
	function validate(){
		//验证库区编码
		if($("regional.code").value==""){
alert("ssssss");
			alert('${action.getText('regional.code.requiredstring')}');
			$("regional.code").focus();
			return false;
		}
		if($("regional.code").value.length>50){
			alert('${action.getText('regional.code.stringlength')}');
			$("regional.code").focus();
			return false;
		}
		//验证库区名称
		if($("regional.name").value==""){
			alert('${action.getText('regional.name.requiredstring')}');
			$("regional.name").focus();
			return false;
		}
		if($("regional.name").value.length>50){
			alert('${action.getText('regional.name.stringlength')}');
			$("regional.name").focus();
			return false;
		}
		//验证仓库级别
	    if($("storageGrade.id").value== "" || $("storageGrade.id").value== -1) {
	  		alert('${action.getText('regional.storageGrade.requiredstring')}');
	  		$("storageGrade.id").focus();
			return false;
		}
	    //验证仓库
	    if($("warehouse.id").value== "" || $("warehouse.id").value== -1) {
	  		alert('${action.getText('regional.warehouse.requiredstring')}');
	  		$("warehouse.id").focus();
			return false;
		}
		//验证备注
		if($("regional.comment").value.length>500){
			alert('${action.getText('regional.comment.stringlength')}');
			$("regional.comment").focus();
			return false;
		}
		
     }
</script>