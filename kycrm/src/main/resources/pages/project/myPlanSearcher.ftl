<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<tr>
	<@ww.textfield label="'${action.getText('计划名称')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	<!--计划状态-->
	<@ww.select label="'${action.getText('计划状态')}'" 
			name="'planState.id'" 
			value="'${req.getParameter('planState.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allPlanState"
			emptyOption="true" 
			disabled="false">
	</@ww.select>
		<script language="javascript">
			<#if req.getParameter('planState.id')?exists>
				getObjByName('planState.id').value = '${req.getParameter('planState.id')?if_exists}';
			</#if>
		</script>
	
	</tr>
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--项目编号-->	
	<@ww.textfield label="'${action.getText('项目名称')}'" name="'projectInfo.name'" value="'${req.getParameter('projectInfo.name')?if_exists}'" cssClass="'underline'"/>
	<!--合同名称-->	
	<@ww.textfield label="'${action.getText('合同名称')}'" name="'contractManagement.contractName'" value="'${req.getParameter('contractManagement.contractName')?if_exists}'" cssClass="'underline'"/>
	</tr>
	
	<!--仅查询失效-->
	<@crm_onlySearchInvalid_checkBox />
	<!---->
	</tr>

</@inputTable>
