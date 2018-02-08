<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="制度文档上传">
<script language="JavaScript" type="text/JavaScript"> 

	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
	            <tr>
	            	<@ww.file label="'上传文件'" size="60" name="'file'" cssClass="'button'" required="true"/>
	            </tr>	
	            <tr>
	            	<@ww.textfield label="'名称'" size="50" name="'deviceDoc.name'" value="''" cssClass="'underline'" required="true"/>

	            </tr>
	            <tr>
	            	  <@ww.select label="'种类'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    		'',	
		                    		'程序文件', 
		                    		'制度文件'
		                    	  }"
		                    value="selectedDevice"
		                    required="false"
		                    	
		        	/> 
	            </tr>
	            	
				<tr>
					 <@ww.textarea label="'描述'" 
					         name="'deviceDoc.description'" 
					         value="''" rows="'4'" cols="'80'" 
							 required="false"/>
				</tr>
				

  		</@inputTable>
         <@buttonBar>
       		<@redirectButton value="上传" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/tool/docarch/listDocArch.html"/>
        </@buttonBar>

    </@ww.form>
</@htmlPage>