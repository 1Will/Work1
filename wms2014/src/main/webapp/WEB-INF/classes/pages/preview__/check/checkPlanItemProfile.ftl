<#include "../includes/macros2.ftl" />
<@htmlPage title="检查计划明细维护">


<script language="JavaScript" type="text/JavaScript"> 

	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',500,300);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="" />
                 <@ww.textfield label="'检查名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'检查部位'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            	<@ww.textfield label="'检查标准'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'检查负责人'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>
        </@inputTable>
		
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<input type="button" value="关闭" class="button" onclick="window.close();"
        </@buttonBar>
    </@ww.form>
    
 </@htmlPage>