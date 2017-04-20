<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--项目编号-->	
	<@ww.textfield label="'${action.getText('code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
	<!--客户名称-->	
	<@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
	<!--客户名称-->
	<@ww.textfield label="'${action.getText('projectInfo.customerInfoName')}'" name="'customer.name'" value="'${req.getParameter('customer.name')?if_exists}'" cssClass="'underline'"/>
	<!--联系人-->
	<@ww.textfield label="'${action.getText('projectInfo.contact')}'" name="'contact.name'" value="'${req.getParameter('contact.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
	
	<tr>
	<!--项目负责人-->
	<@ww.textfield label="'${action.getText('projectInfo.controller')}'" name="'controller.name'" value="'${req.getParameter('controller.name')?if_exists}'" cssClass="'underline'"/>
		    
		    
	<!--项目状态-->
	<@ww.select label="'${action.getText('项目状态')}'" 
			name="'state.id'" 
			value="'${req.getParameter('state.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allStates"
			emptyOption="false" 
			disabled="false">
	</@ww.select>
		<script language="javascript">
			<#if req.getParameter('state.id')?exists>
				getObjByName('state.id').value = '${req.getParameter('state.id')?if_exists}';
			</#if>
		</script>
	</tr>
	
	<tr>
	<!--仅查询失效-->
	<@crm_onlySearchInvalid_checkBox />
	<!---->
	</tr>

</@inputTable>
<script language="javascript">
</script>