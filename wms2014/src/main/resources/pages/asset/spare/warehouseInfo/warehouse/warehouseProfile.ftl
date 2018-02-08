<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('warehouse.edit')}">
     <@ww.form  name="'listFrom'" action="'saveWarehouse'" namespace="'/warehouseInfo'" method="'post'" >
     	 <@ww.token name="saveWarehouseToken"/>
     	  <@inputTable>
     	   <#if warehouse.id?exists>
     	  	 <@ww.hidden name="'warehouse.id'" value="#{warehouse.id}"/>
     	  </#if>
		     <tr>
		     <#if warehouse.id?exists>
				<@ww.textfield label="'${action.getText('warehouse.code')}'" name="'warehouse.code'" value="'${warehouse.code?if_exists}'" cssClass="'underline'" required="true" disabled="true"/><#--disabled="true"-->
			<#else>	
				<@ww.textfield label="'${action.getText('warehouse.code')}'" name="'warehouse.code'" value="'${warehouse.code?if_exists}'" cssClass="'underline'" required="true" />
			</#if>
			    <@ww.textfield label="'${action.getText('warehouse.name')}'" name="'warehouse.name'" value="'${warehouse.name?if_exists}'" cssClass="'underline'" required="true"/>
	  
	      		<#-- 库存级别 -->
  		     <@ww.select label="'${action.getText('storageGrade')}'" 
	                   required="true" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="false">
                       <#if warehouse.storageGrade?exists>
                          <@ww.param name="'value'"  value="'${warehouse.storageGrade.id?if_exists}'" />
                       </#if>
         </@ww.select>
		  	</tr>
		     <tr>
				<@pp.remotePicker label="'${action.getText('warehouse.user')}'" 
					    name="'wareh.user'"
	                    selectorName="'userSelectorAjax'" 
	                    cssClass="'underline'" 
	                    value="warehouse.user"
	                    popup="'${req.contextPath}/popup/userSelector.html'" 
	                    size="15" 
	                    codeName="'loginName'" 
	                    required="true"/>     
                    
				<@ww.textfield label="'${action.getText('warehouse.tel')}'" name="'warehouse.tel'" value="'${warehouse.tel?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('warehouse.fax')}'" name="'warehouse.fax'" value="'${warehouse.fax?if_exists}'" cssClass="'underline'" />
		  	</tr>
		  	<tr>
     			<@text2 label="${action.getText('warehouse.address')}" name="warehouse.address" size="121" maxLength="95"  value="${warehouse.address?if_exists}" />																  	
 		 	
				<@ww.textfield label="'${action.getText('warehouse.postcode')}'" name="'warehouse.postcode'" value="'${warehouse.postcode?if_exists}'" cssClass="'underline'"/>				          				
			</tr>
		  	<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('warehouse.comment')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="warehouse.comment" rows="4" cols="95" >${warehouse.comment?if_exists}</textarea>
		        </td>
			</tr>
     	  </@inputTable>
     	  <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         <@redirectButton value="${action.getText('back')}" url="listWarehouse.html"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>

<script language="javascript">

    <#if warehouse.storageGrade?exists>
            getObjByName('storageGrade.id').value= #{warehouse.storageGrade.id};
     </#if>

	function validate(){
		//验证仓库编码
		if(document.forms[0].elements["warehouse.code"].value==""){
			alert('${action.getText('warehouse.code.requiredstring')}');
			return false;
		}
		if(document.forms[0].elements["warehouse.code"].value.length>50){
			alert('${action.getText('warehouse.code.stringlength')}');
			return false;
		}
		//验证仓库名称
		if(document.forms[0].elements["warehouse.name"].value==""){
			alert('${action.getText('warehouse.name.requiredstring')}');
			return false;
		}
		if(document.forms[0].elements["warehouse.name"].value.length>50){
			alert('${action.getText('warehouse.name.stringlength')}');
			return false;
		}
		//验证负责人
		if(document.forms[0].elements["wareh.user.id"].value==""){
			alert('${action.getText('warehouse.user.requiredstring')}');
			return false;
		}
		//验证仓库级别
		if(document.forms[0].elements["storageGrade.id"].value==""){
			alert('${action.getText('storageGrade.requiredstring')}');
			return false;
		}
		
		//验证电话
		if(document.forms[0].elements["warehouse.tel"].value==""){
			alert('${action.getText('warehouse.tel.requiredstring')}');
			return false;
		}
		if(document.forms[0].elements["warehouse.tel"].value.length>50){
			alert('${action.getText('warehouse.tel.stringlength')}');
			return false;
		}
		//验证传真
		if(document.forms[0].elements["warehouse.fax"].value.length>50){
			alert('${action.getText('warehouse.fax.stringlength')}');
			return false;
		}
		//验证邮编
		if(document.forms[0].elements["warehouse.postcode"].value.length>50){
			alert('${action.getText('warehouse.postcode.stringlength')}');
			return false;
		}
		//验证地址
		 
		if(document.forms[0].elements["warehouse.address"].value.length>50){
			alert('${action.getText('warehouse.address.stringlength')}');
			return false;
		}
		//验证注备
		if(document.forms[0].elements["warehouse.comment"].value.length>500){
			alert('${action.getText('warehouse.comment.stringlength')}');
			return false;
		}
	      
     }
</script>