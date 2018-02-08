<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="工装日常检查维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	    
		 	  <tr>
		 	  	 <@toolingSelector1/>
		 	  </tr>
		 	  <tr>
		 	    <td align="right" valign="top"><label class="label"><font color="red">*</font>检查人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@pp.datePicker label="'检查时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
		     </tr>
		 	  <tr>
		 	  	
	      		<@ww.textarea  label="'检查结果'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	      		<@ww.textarea  label="'处理结果'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		 	  </tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<@redirectButton value="返回" url="${req.contextPath}/preview/tooling/dailyCheck/listToolingDailyCheck.html"/>
	     </@buttonBar>
	</@ww.form>
</@htmlPage>