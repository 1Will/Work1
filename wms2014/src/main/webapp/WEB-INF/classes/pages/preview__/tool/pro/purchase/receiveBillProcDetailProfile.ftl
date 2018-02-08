<#include "../../../includes/macros2.ftl" />
<@htmlPage title="验收详细">

	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
             <tr>
            	<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
                <@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
            </tr>
            <tr>
            	<@ww.textfield label="'工装图号'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
                <@ww.textfield label="'使用设备'" name="'area.code'" value="" cssClass="'underline'" readonly="false" required="false"/>
            </tr>   
            <tr>
            	<@ww.textfield label="'验收内容'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
                <@ww.textfield label="'验收结果'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
            </tr>                                            
           	<tr>
	        	 <td align="right" vlign="middle" rowspan="3">
                	<label  class="label">备注:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="deviceExtInfo.comment" rows="3" cols="40"></textarea>
            	</td> 
           	</tr>
        </@inputTable>
       
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>