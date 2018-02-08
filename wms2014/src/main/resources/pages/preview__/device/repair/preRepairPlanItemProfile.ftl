<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="预防性计划维护">

<script language="JavaScript" type="text/JavaScript"> 

	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html');
	}
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,400);
	}
</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>        	
        	<tr>
            	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" />
            	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>  
			<tr>
            	<@ww.textfield label="'维修部位'" name="'area.code'" value="" cssClass="'underline'" />
            	<@ww.textfield label="'维修内容摘要'" name="'area.code'" value="" cssClass="'underline'"  size="50"/>
            </tr>     
             <tr>
            	<@ww.textfield label="'责任单位'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
            	<@ww.textfield label="'责任人'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
            </tr> 
             <tr>
            	<@ww.textfield label="'执行人'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
            	<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" readonly=""  size="40"/>			
            </tr> 
             <tr>
            	<@ww.textfield label="'执行结果'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
            </tr>          
        </@inputTable>

       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<input type="button" value="关闭" class="button" onclick="window.close();"/>
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	