<#include "../includes/macros2.ftl" />

<@htmlPage title="检查实施明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
		 	  <tr>
        		<@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
        		<@ww.textfield label="'检查计划编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
        	</tr>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr> 
            <tr>
            	<@ww.textfield label="'检查负责人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	<@ww.textfield label="'保养状态'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>     
            <tr>
            	<@ww.textfield label="'检查结论'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'处理建议'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@pp.datePicker label="'检查开始日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     		<@pp.datePicker label="'检查结束日期'" name="'repair.actual.time1'"
	     			value="" cssClass="'underline'" size="15"/>
            </tr>
            <tr>
            	<@ww.textfield label="'备注'" name="'area.code'" value="" size="50" cssClass="'underline'" readonly="true"/>
            </tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
</@htmlPage>