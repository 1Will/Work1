<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="采购单附件">

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
			<tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>	
            <tr>
            	<@ww.textfield label="'附件名称'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            	<@ww.textfield label="'附件描述'" size="30" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'备注'" size="30" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr> 
        </@inputTable>
        <@inputTable>
            <tr>
            	<td align="right" valign="top"><label class="label">${action.getText('techDoc.select')}:</label></td>
	        	<td>
            		<@ww.file name="uploadFile" size="50"/>
            	</td>
            </tr>
        </@inputTable>
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>