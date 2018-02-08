<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="附加文档">

	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="12" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'文档名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'文档描述'" size="50" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>                                            
            <tr>
            	 <@ww.textfield label="'所属设备'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
           	</tr>
        </@inputTable>
        <@inputTable>
            <tr>
            	<td align="right" valign="top"><label class="label">文档:</label></td>
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