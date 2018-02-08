<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="技术资料维护">
 <base target="_self">
	<@ww.form name="'deviceDoc'" action="'saveDeviceDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveDeviceDocToken"/>
		<@inputTable>
	            <tr>
	            	<@ww.file label="'上传文件'" size="60" name="'file'" cssClass="'button'" required="true"/>
	            </tr>	
	            <tr>
	            	<@ww.textfield label="'文件名'" size="50" name="'deviceDoc.name'" value="''" cssClass="'underline'" required="true"/>
	            </tr>		
				<tr>
					 <@oneLine>
					 <@ww.textarea label="'文件描述'" 
					         name="'deviceDoc.description'" 
					         value="''" rows="'4'" cols="'80'" 
							 required="false"/>
					</@oneLine>
				</tr>
		</@inputTable>
		<@buttonBar>
			<@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
			<@vbutton name="'close'"class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		<@ww.hidden name="'origFileName'" value=""/>
		<script language="javascript">
			function getFileName() {
			  var filename = document.forms["deviceDoc"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('请选择上传文件')}");
				return false;
			  }
			  document.forms["deviceDoc"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}

		</script>
	</@ww.form>
</@htmlPage>