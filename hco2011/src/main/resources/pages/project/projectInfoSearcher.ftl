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
     //弹出客户档案查询模态窗体
	function customer_OpenDialog(id){
	   var url = "${req.contextPath}/customerRelationship/editCustomerInfo.html?customerInfo.id="+id+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   //popupModalDialog(url, 800, 600, SelectorHandlerCustomerInfo);
	   openNewWindow(url);
	 }
	 
	       //弹出联系人模态窗体
	function contactArchives_OpenDialog(id){
	   var url = "${req.contextPath}/customerRelationship/editContactArchives.html?contactArchives.id="+id+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   //popupModalDialog(url, 800, 600, SelectorHandlerContactArchives);
	   openNewWindow(url);
	 }
	//弹出回访模态窗体
	function visitBack_OpenDialog(id){
	   var url = "${req.contextPath}/backvisit/listBackVisitTab.html?customerInfo.id="+id;
	   //popupModalDialog(url, 900,700, SelectorHandlerVisitBack);
	   openNewWindow(url);
	 }

</script>