<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="工装润滑标准维护">
	<@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
		<@inputTable>
			<tr>
				<@ww.textfield readonly="true" label="'${action.getText('device.name')}'" name="'device.name'" value="" cssClass="'underline'" required="true"/>
				<@ww.textfield readonly="true" label="'${action.getText('device.code')}'" name="'device.id'" value="" cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('smooth.part')}'" name="'smooth.part'" value="" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('smooth.time')}'" name="'smooth.time'" value="" cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('smooth.oil')}'" name="'smooth.oil'" value="" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('smooth.kind')}'" name="'smooth.kind'" value="" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('smooth.measure')}'" name="'smooth.measure'" value="" cssClass="'underline'" required="true"/>
			</tr>	
			<tr>
				<@ww.textfield label="'${action.getText('smooth.people')}'" name="'smooth.people'" value="" cssClass="'underline'" required="true"/>
			</tr>
		</@inputTable>
		<@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/device/smoothInfo/listSmoothInfo.html"/>	
	        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/device/smoothInfo/listSmoothInfo.html"/>	
	    </@buttonBar>
	     <#include "smoothInfoSearcher.ftl" />
	</@ww.form>
</@htmlPage>