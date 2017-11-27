<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('水电费详细维护页面')}">
<@ww.form name="'listForm'" action="'saveExpense'" method="'post'">
	<@ww.token name="saveExpenseActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'expense.id'" value="'${expense.id?if_exists}'"/>	
	<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('编码')}'"  id="expense.code"  name="'expense.code'" cssClass="'underline'"  required="true" />
			
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'expense.startTime'" 
				value="'${(expense.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<@pp.datePicker 
				label="'${action.getText('结束时间')}'" 
				name="'expense.endTime'" 
				value="'${(expense.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
				
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('房间数')}'" maxlength="5" id="expense.sumHouse"  name="'expense.sumHouse'" cssClass="'underline'"  required="true" />
			<@ww.textfield label="'${action.getText('总面积')}'" maxlength="5" id="expense.sumSquare"  name="'expense.sumSquare'" cssClass="'underline'"   required="true" />
			<@ww.textfield label="'${action.getText('企业数')}'" maxlength="5" id="expense.sumCustomer"  name="'expense.sumCustomer'" cssClass="'underline'"  required="true" />
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('水费')}'" maxlength="5" id="expense.waterMoney"  name="'expense.waterMoney'" cssClass="'underline'"   required="true" />
			<@ww.textfield label="'${action.getText('电费')}'" maxlength="5" id="expense.electricMoney"  name="'expense.electricMoney'" cssClass="'underline'"  required="true" />
			<@ww.textfield label="'${action.getText('空调费')}'" maxlength="5" id="expense.airMoney"  name="'expense.airMoney'" cssClass="'underline'"   required="true" />
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('网络费')}'" maxlength="5" id="expense.netMoney"  name="'expense.netMoney'" cssClass="'underline'"   required="true" />
			<@ww.textfield label="'${action.getText('物业费')}'" maxlength="5" id="expense.propertyMoney"  name="'expense.propertyMoney'" cssClass="'underline'"   required="true" />
			<@ww.textfield label="'${action.getText('总金额')}'" maxlength="5" id="expense.sum"  name="'expense.sum'" cssClass="'underline'"  required="true" />
		</tr>
			
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/fee/listExpense.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script  type="text/JavaScript"> 
	<#-- 提交验证-->
	function storeValidation(){
		if(getObjByName("expense.startTime").value==""){
			alert("${action.getText('请选择开始时间!')}");
			getObjByName("expense.startTime").focus();
			return false;
		}
		if(getObjByName("expense.endTime").value==""){
			alert("${action.getText('请选择结束时间!')}");
			getObjByName("expense.endTime").focus();
			return false;
		}
		return true;
	}
	
</script> 
</@htmlPage>
<#if expense.id?exists>
<ul id="beautytab">
	<li>
		<a id="expenseTab" onclick="activeTab(this);"  href='${req.contextPath}/fee/listExpenseTab.html?parentEP.id=#{expense.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('账单详细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/fee/listExpenseTab.html?parentEP.id=#{expense.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
