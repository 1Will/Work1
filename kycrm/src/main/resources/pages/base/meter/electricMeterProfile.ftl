<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('电表详细')}">
	<@ww.form namespace="'/fee'" name="'listForm'" action="'saveElectricMeter'" method="'post'">
		<@ww.token name="saveElectricMeterToken"/>
		<@inputTable>
			<#if electricMeter.id?exists>
                <@ww.hidden  id="electricMeter.id"  name="'electricMeter.id'" value="#{electricMeter.id?if_exists}"/>
            <#else>
                <@ww.hidden id="electricMeter.id"  name="'electricMeter.id'" value=""/>
            </#if>
			<tr>
	           	<@ww.textfield label="'${action.getText('电表编码')}'" name="'electricMeter.code'" value="'${electricMeter.code?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('电表名称')}'" name="'electricMeter.name'" value="'${electricMeter.name?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('电表倍率')}'" name="'electricMeter.rate'" value="'${electricMeter.rate?if_exists}'" cssClass="'underline'" required="true"/>
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"></@vsubmit>
        </#if>
        <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/fee/listElectricMeter.html?readOnly=${req.getParameter('readOnly')?if_exists}" /> 
	</@buttonBar>
	</@ww.form>
	<script language="javascript">
	function savee(){
		if(getObjByName('electricMeter.code').value==''){
			alert('请输入电表编码');
			getObjByName('electricMeter.code').focus();
			return false;
		}
		
		if(getObjByName('electricMeter.name').value==''){
			alert('请输入电表名称');
			getObjByName('electricMeter.name').focus();
			return false;
		}
		return true;
	}
	</script>
</@htmlPage>
