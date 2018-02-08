<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6__________
	__________________________________________________________________________-->
	<tr>
	<!--产品编码-->	
	<@ww.textfield label="'${action.getText('异常编码')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
		<!--产品名称-->	
	<@ww.textfield label="'${action.getText('异常名称')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	<!--异常节点-->
	<@ww.select 
	    		label="'${action.getText('异常节点')}'"
				required="false"
				name="'productionPoint.id'" 
				value="${req.getParameter('productionPoint.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allProductionPoints"
			    emptyOption="false" 
		    	disabled="false"/>
		    	<script language="javascript">
			<#if req.getParameter('productionPoint.id')?exists>
				getObjByName('productionPoint.id').value = '${req.getParameter('productionPoint.id')?if_exists}';
			</#if>
		</script>
	</tr>
	<tr>
	<!--产品编码-->	
	<@ww.textfield label="'${action.getText('产品编码')}'" name="'product.code'" value="'${req.getParameter('product.code')?if_exists}'" cssClass="'underline'"/>
		<!--产品名称-->	
	<@ww.textfield label="'${action.getText('产品名称')}'" name="'product.name'" value="'${req.getParameter('product.name')?if_exists}'" cssClass="'underline'"/>
	<!--产品型号-->	
	<@ww.textfield label="'${action.getText('产品型号')}'" name="'product.model'" value="'${req.getParameter('product.model')?if_exists}'" cssClass="'underline'"/>
	
	</tr>
	<tr>
	<@ww.textfield label="'${action.getText('节点负责人')}'" name="'responsibility.name'" value="'${req.getParameter('responsibility.name')?if_exists}'" cssClass="'underline'"/>
	<@ww.textfield label="'${action.getText('提出人')}'" name="'findPersion.name'" value="'${req.getParameter('findPersion.name')?if_exists}'" cssClass="'underline'"/>
	<!--编制日期-->
	<@pp.dateRanger label="'${action.getText('提出日期')}'" name="'productionOperationException.findDate'" 
            value="'${req.getParameter('productionOperationException.findDate_start')?if_exists}|${req.getParameter('productionOperationException.findDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("productionOperationException.findDate_start").value==""){
		        	var date = new Date();
					getObjByName("productionOperationException.findDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	</tr>
	<tr>
	<!--解决人-->	
	<@ww.textfield label="'${action.getText('解决人')}'" name="'solvePersion.name'" value="'${req.getParameter('solvePersion.name')?if_exists}'" cssClass="'underline'"/>
	
	<!--解决日期-->
	<@pp.dateRanger label="'${action.getText('解决日期')}'" name="'productionOperationException.solveDate'" 
            value="'${req.getParameter('productionOperationException.solveDate_start')?if_exists}|${req.getParameter('productionOperationException.solveDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("productionOperationException.solveDate_start").value==""){
		        	var date = new Date();
					getObjByName("productionOperationException.solveDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	
		<!--异常状态-->
	<@ww.select 
	    		label="'${action.getText('异常状态')}'"
				required="false"
				name="'statu.id'" 
				value="${req.getParameter('statu.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allStatu"
			    emptyOption="false" 
		    	disabled="false"/>
		    	<script language="javascript">
			<#if req.getParameter('statu.id')?exists>
				getObjByName('statu.id').value = '${req.getParameter('statu.id')?if_exists}';
			</#if>
		</script>
	</tr>
	<tr>
	<!---->
	</tr>
</@inputTable>
