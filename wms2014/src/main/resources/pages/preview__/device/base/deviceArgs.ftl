<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@framePage title="设备技术参数">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
        <@inputTable>
            <tr>
                <@ww.textfield label="'额定功率(r/s)'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'运行重量(t)'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'工作最高温度'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'工作最低温度'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>             
            <tr>
            	<@ww.textfield label="'工作最高湿度'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'工作最低温度'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'额定工作电压(kv)'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	<@ww.textfield label="'备注'" name="'area.code'" size="50" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            
        </@inputTable>
        <@buttonBar>
        	<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        </@buttonBar>
     </@ww.form>
</@framePage>