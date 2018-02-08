<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="保养计划项目维护">
<script language="JavaScript" type="text/JavaScript">
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html');
	}

</script>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		 <tr>
	          <@ww.textfield label="'项目号'" name="'area.code'" value="" required="true" cssClass="'underline'"  required="true" /> 
	         	<@ww.textfield label="'保养名称'" name="'area.code'" value="" cssClass="'underline'"  required="true" />
            </tr>  
             <tr>
	          <@ww.textfield label="'保养部位'" name="'area.code'" value="" required="true" cssClass="'underline'"  required="true" /> 
	         	<@ww.textfield label="'保养标准'" name="'area.code'" value="" cssClass="'underline'"  required="true" />
            </tr> 
            <tr>
            <td align="right" valign="top"><label class="label"><font color="red">*</font>保养负责人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" readonly maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="people_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		       <@pp.datePicker label="'保养时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true"/>
            </tr> 
        </@inputTable>
        	<@buttonBar>
	    		<@redirectButton  value="保存" url="#" />
	    	<@vbutton  value="关闭" class="button" onclick="window.close();" />
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>