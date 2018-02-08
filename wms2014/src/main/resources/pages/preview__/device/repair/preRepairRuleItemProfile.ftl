<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="预防性维修标准明细维护">

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
            	<@ww.textfield label="'维修部位'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
				<@ww.textfield label="'维修内容摘要'" name="'area.code'" value="" cssClass="'underline'" readonly=""  size="40"/>			
            </tr> 
            <tr>
            	<@ww.textfield label="'运行台时阀值'" name="'area.code'" value="" cssClass="'underline'" readonly=""  size="40"/>			
            </tr>            
        </@inputTable>

       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>		
        	<input type="button" value="关闭" class="button" onclick="window.close();"
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>