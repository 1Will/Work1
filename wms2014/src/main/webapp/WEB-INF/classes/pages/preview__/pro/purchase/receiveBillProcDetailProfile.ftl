<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="验收详细">

	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'验收步骤'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'验收结果'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>                                            
            <tr>
            	 <@ww.textfield label="'验收人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	 <@ww.textfield label="'验收时间'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
           	</tr>
           	<tr>
           		<@ww.textfield label="'备注'" size="50" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
           	</tr>
        </@inputTable>
       
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>