<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="工装附件维护">
	<script language="JavaScript" type="text/JavaScript"> 
		function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	</script>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'附件名称'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'附件型号'" name="'area.code'" value="" cssClass="'underline'" required="false" />
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'所需个数'" name="'area.code'" value="" cssClass="'underline'" required="false" />
	 	  		<@ww.textfield label="'单价(元)'" name="'area.code'" value="" cssClass="'underline'" required="false" />
	 	  	</tr>
	 	  	<tr>
	 	  		<td align="right" valign="top"><label class="label"><font color="red"></font>供应商名称:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="supplier_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
	 	  	</tr>
	 	  	<tr>
	        	 <td align="right" vlign="middle" rowspan="3">
                	<label  class="label">${action.getText('comment')}:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="deviceExtInfo.comment" rows="3" cols="80"></textarea>
            	</td>            	
			</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
</@htmlPage>