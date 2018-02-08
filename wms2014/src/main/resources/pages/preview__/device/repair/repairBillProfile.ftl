<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="${action.getText('repair.title')}">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('repair.applicant.sn')}'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('repair.application.creater')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('repair.application.create.time')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@pp.datePicker label="'${action.getText('repair.estimate.time0')}'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
                <@pp.datePicker label="'${action.getText('repair.estimate.time1')}'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
            </tr>
            <tr>
                <@pp.datePicker label="'${action.getText('repair.actual.time0')}'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"/>
                <@pp.datePicker label="'${action.getText('repair.actual.time1')}'" name="'repair.actual.time1'"
	     			value="" cssClass="'underline'" size="15"/>
           </tr>
           <tr colspan="2">
           		<@ww.textarea label="'${action.getText('repair.reason')}'" name="'repair.reason'"
                value="" rows="3" cols="40" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/device/repair/listRepairBills.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/device/repair/listRepairBills.html"/>	
        </@buttonBar>
    </@ww.form>
</@htmlPage>