<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="维修工具维护">

<script language="JavaScript" type="text/JavaScript"> 

	function tool_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/toolSelector.html',600,400);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		 <@ww.textfield label="'项目号'" name="'area.code'" value="1263" cssClass="'underline'" />
        	</tr>
        	<tr>
        		<@ww.textfield label="'工具编号'" name="'area.code'" value="1263" cssClass="'underline'" />
        		<@ww.textfield label="'工具名称'" name="'area.code'" value="1263" cssClass="'underline'" />
        	</tr>
        	<tr>
        		<@ww.textfield label="'工具规格'" name="'area.code'" value="1263" cssClass="'underline'" />
        		<@ww.textfield label="'工具型号'" name="'area.code'" value="1263" cssClass="'underline'" />
        	</tr>
			<tr>
				<@ww.textfield label="'数量'" name="'area.code'" value="1263" cssClass="'underline'" />
            	<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" readonly=""  size="40"/>			
            </tr> 

        </@inputTable>

       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<input type="button" value="关闭" class="button" onclick="window.close();"/>
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	