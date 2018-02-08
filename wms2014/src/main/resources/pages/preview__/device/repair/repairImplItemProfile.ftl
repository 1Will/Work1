<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="维修实施明细维护">

<script language="JavaScript" type="text/JavaScript"> 

	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',750,400);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'" readonly="true" disabled="true"/>
        		 <@ww.textfield label="'项目名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>
			<tr>
				<@ww.textfield label="'实施维修工时'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
            	<@ww.textfield label="'实施单位'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
            </tr>  
 
             <tr>
            	
            	<@ww.textarea label="'实施维修内容摘要'" 
              				   name="'organization.remark'" 
                               value="" rows="3" cols="'50'" 
				               required="false"/>
            </tr>  
            <tr>
            	<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" readonly=""  size="40"/>			
            </tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>		
        	<input type="button" value="关闭" class="button" onclick="window.close();"/>
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	