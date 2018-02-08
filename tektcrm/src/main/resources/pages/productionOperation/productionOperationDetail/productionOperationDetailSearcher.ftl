<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--经营计划编码-->	
	<@ww.textfield label="'${action.getText('经营计划编码')}'" name="'productionOperation.code'" value="'${req.getParameter('productionOperation.code')?if_exists}'" cssClass="'underline'"/>
	<!--经营计划名称-->	
	<@ww.textfield label="'${action.getText('经营计划名称')}'" name="'productionOperation.name'" value="'${req.getParameter('productionOperation.name')?if_exists}'" cssClass="'underline'"/>
	<!--产品编码-->	
	<@ww.textfield label="'${action.getText('产品编码')}'" name="'product.code'" value="'${req.getParameter('product.code')?if_exists}'" cssClass="'underline'"/>
	
	</tr>
	<tr>
		<!--产品名称-->	
	<@ww.textfield label="'${action.getText('产品名称')}'" name="'product.name'" value="'${req.getParameter('product.name')?if_exists}'" cssClass="'underline'"/>
	<!--产品型号-->	
	<@ww.textfield label="'${action.getText('产品型号')}'" name="'product.model'" value="'${req.getParameter('product.model')?if_exists}'" cssClass="'underline'"/>
	<!--执行状态-->
	<@ww.select 
	    		label="'${action.getText('执行状态')}'"
				required="false"
				name="'proStatu.id'" 
				value="${req.getParameter('proStatu.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allProStatu"
			    emptyOption="false" 
		    	disabled="false"/>
		    	<script language="javascript">
			<#if req.getParameter('proStatu.id')?exists>
				getObjByName('proStatu.id').value = '${req.getParameter('proStatu.id')?if_exists}';
			</#if>
		</script>
	</tr>
	<tr>
	<!--仅查询失效-->
	<@crm_onlySearchInvalid_checkBox />
	<!---->
	</tr>
</@inputTable>
