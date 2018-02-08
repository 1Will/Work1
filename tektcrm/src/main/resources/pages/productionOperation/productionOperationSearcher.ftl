<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--经营计划编码-->	
	<@ww.textfield label="'${pageTitle?if_exists}${action.getText('编码')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
	<!--经营计划名称-->	
	<@ww.textfield label="'${pageTitle?if_exists}${action.getText('名称')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	<!--负责人-->
	<@ww.textfield label="'${action.getText('编制人')}'" name="'makeUpPerson.name'" value="'${req.getParameter('makeUpPerson.name')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
	<!--编制日期-->
	<@pp.dateRanger label="'${action.getText('编制日期')}'" name="'productionOperation.makeUpDate'" 
            value="'${req.getParameter('productionOperation.makeUpDate_start')?if_exists}|${req.getParameter('productionOperation.makeUpDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("productionOperation.makeUpDate_start").value==""){
		        	var date = new Date();
					getObjByName("productionOperation.makeUpDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	<!--审核人-->
	<@ww.textfield label="'${action.getText('审核人')}'" name="'auditingPerson.name'" value="'${req.getParameter('auditingPerson.name')?if_exists}'" cssClass="'underline'"/>
	
	<!--审核日期-->
	<@pp.dateRanger label="'${action.getText('审核日期')}'" name="'productionOperation.auditingDate'" 
            value="'${req.getParameter('productionOperation.auditingDate_start')?if_exists}|${req.getParameter('productionOperation.auditingDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("productionOperation.auditingDate_start").value==""){
		        	var date = new Date();
					getObjByName("productionOperation.auditingDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	</tr>
	<tr>
	<!--仅查询失效-->
	<@crm_onlySearchInvalid_checkBox />
	<!---->
	</tr>
</@inputTable>
