<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--发货单编码-->	
	<@ww.textfield label="'${action.getText('发货单编码')}'" name="'deliveryNum'" value="'${req.getParameter('deliveryNum')?if_exists}'" cssClass="'underline'"/>
	<!--客户名称-->	
	<@ww.textfield label="'${action.getText('客户名称')}'" name="'customerInfo.name'" value="'${req.getParameter('customerInfo.name')?if_exists}'" cssClass="'underline'"/>
	<!--联系人-->
	<@ww.textfield label="'${action.getText('联系人')}'" name="'contacter'" value="'${req.getParameter('contacter')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
	<!--负责人-->
	<@ww.textfield label="'${action.getText('负责人')}'" name="'deliveryPerson.name'" value="'${req.getParameter('deliveryPerson.name')?if_exists}'" cssClass="'underline'"/>
	<!--部门-->
	<@ww.textfield label="'${action.getText('部门')}'" name="'department.name'" value="'${req.getParameter('department.name')?if_exists}'" cssClass="'underline'"/>
	<!--状态-->
	<@ww.select label="'${action.getText('发货状态')}'" 
		id="deliveryStatus.id" 
		name="'deliveryStatus.id'" 
		value="${req.getParameter('deliveryStatus.id')?if_exists}"
		listKey="id"
		listValue="name"
		list="allDeliveryStatus"
		required="false"
		emptyOption="false" 
		disabled="false">
	</@ww.select>
	</tr>
	<tr>
	<!--发货方式-->
	<@ww.select label="'${action.getText('发货方式')}'" 
		id="deliveryWay.id" 
		name="'deliveryWay.id'" 
		value="'${req.getParameter('deliveryWay.id')?if_exists}'"
		listKey="id"
		listValue="name"
		list="allDeliveryWay"
		required="false"
		emptyOption="false" 
		disabled="false">
	</@ww.select>
	<!--发货日期-->
	<@pp.dateRanger label="'${action.getText('发货日期')}'" name="'invoices.receiptDate'" 
            value="'${req.getParameter('invoices.receiptDate_start')?if_exists}|${req.getParameter('invoices.receiptDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("invoices.receiptDate_start").value==""){
		        	var date = new Date();
					getObjByName("invoices.receiptDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
	</tr>
</@inputTable>
<script language="javascript">
<#if req.getParameter('deliveryWay.id')?exists>
	getObjByName("deliveryWay.id").value='${req.getParameter('deliveryWay.id')?if_exists}';
</#if>
<#if req.getParameter('deliveryStatus.id')?exists>
	getObjByName("deliveryStatus.id").value='${req.getParameter('deliveryStatus.id')?if_exists}';
</#if>
</script>