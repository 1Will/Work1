<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('房租详细维护页面')}">
<@ww.form name="'listForm'" action="'saveRent'" method="'post'">
	<@ww.token name="saveRentActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'rent.id'" value="'${rent.id?if_exists}'"/>	
	<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('编码')}'"  id="rent.code"  name="'rent.code'" cssClass="'underline'"  required="true" />
			
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'rent.startTime'" 
				value="'${(rent.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<@pp.datePicker 
				label="'${action.getText('结束时间')}'" 
				name="'rent.endTime'" 
				value="'${(rent.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
		</tr>
		
		<tr>
			<@ww.textfield label="'${action.getText('房间数')}'" maxlength="5" id="rent.sumHouse"  name="'rent.sumHouse'" cssClass="'underline'"   />
			<@ww.textfield label="'${action.getText('总面积')}'" maxlength="5" id="rent.sumSquare"  name="'rent.sumSquare'" cssClass="'underline'"   />
			<@ww.textfield label="'${action.getText('企业数')}'" maxlength="5" id="rent.sumCustomer"  name="'rent.sumCustomer'" cssClass="'underline'" />
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('已交金额')}'" maxlength="5" id="rent.hasSum"  name="'rent.hasSum'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('总金额')}'" maxlength="5" id="rent.sum"  name="'rent.sum'" cssClass="'underline'" />
		</tr>
			
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/fee/listRent.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script  type="text/JavaScript"> 
	<#-- 提交验证-->
	function storeValidation(){
		if(getObjByName("rent.startTime").value==""){
			alert("${action.getText('请选择开始时间!')}");
			getObjByName("rent.startTime").focus();
			return false;
		}
		if(getObjByName("rent.endTime").value==""){
			alert("${action.getText('请选择结束时间!')}");
			getObjByName("rent.endTime").focus();
			return false;
		}
		return true;
	}
	
</script> 
</@htmlPage>
<#if rent.id?exists>
<ul id="beautytab">
	<li>
		<a id="returnPlan" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/contractManagement/listReturnPlanByCustomerAction.html?rent.id=#{rent.id}&sortColumn=batch.name&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('房租详细')}</a>
	</li>
	<#--
	<li>
		<a id="rentTab" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/fee/listRentTab.html?parentRent.id=#{rent.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('详细')}</a>
	</li>
	-->
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/contractManagement/listReturnPlanByCustomerAction.html?rent.id=#{rent.id}&sortColumn=batch.name&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
