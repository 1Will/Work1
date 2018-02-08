<#include "../../includes/macros2.ftl" />
<@htmlPage title="年度预算明细维护">
	<@ww.form name="'newForm'" action="''" method="'post'">
		<@inputTable>
			<tr>
        		<@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" disabled="true" readonly="true"/>
        		<@ww.textfield label="'项目名称'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
        	</tr>
            <tr>
                <@ww.textfield label="'预算编号'" name="'area.code'" value="" cssClass="'underline'" />
                <@ww.textfield label="'预算名称'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>
            <tr>
            	<@ww.textfield label="'预算制定人'" name="'area.code'" value="" cssClass="'underline'" />
                <@ww.textfield label="'预算制定日期'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>
            <tr>
                <@ww.textfield label="'预算实施部门'" name="'area.code'" value="" cssClass="'underline'" />
                <@ww.textfield label="'预算说明'" name="'area.code'" value=""  cssClass="'underline'" />
            </tr>
            <tr>
                <@ww.textfield label="'预算状况'" name="'area.code'" value="" cssClass="'underline'" />
                <@ww.textfield label="'预算审核'" name="'area.code'" value=""  cssClass="'underline'" />
            </tr>
            <tr>
                <@ww.textfield label="'预算费用'" name="'area.code'" value="" cssClass="'underline'" />
                <@ww.textfield label="'备注'" name="'area.code'" value=""  cssClass="'underline'" />
            </tr> 
        </@inputTable>
        <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	    </@buttonBar>
	</@ww.form>
</@htmlPage>