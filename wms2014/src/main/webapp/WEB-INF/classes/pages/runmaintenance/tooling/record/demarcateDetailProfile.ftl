<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="工装标定明细维护">
    <@ww.form namespace="'/base/area'" name="'demarcate'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
			 <@ww.select label="'工装类别'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'模具', 
	                    			'夹具', 
	                    			'工位器具',
	                    			'检具',
	                    			'辅具'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  	
		</tr>
            <@eam2008_ToolingSelector/>
             <tr>
            	<@ww.textfield label="'所在位置'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">负责人:</label></td>
            	<td>
		 			<input type="text" name="manager.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
            </tr>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<@ww.textfield label="'制造单位'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            	<@ww.textfield label="'使用单位'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            </tr>
            <tr>
            	 <@ww.textfield label="'标定周期'" name="'period'" value="" cssClass="'underline'"  required="false"/>
				 <@ww.select label="'标定结果'"
	                    	name="result"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'合格', 
	                    			'不合格'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  
				 				 
            </tr>
            <tr>
            	 <@pp.datePicker label="'本次标定日期'" name="'demarcate.time1'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     		 <@pp.datePicker label="'下次标定日期'" name="'demarcate.time2'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     	</tr>   
	     	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
           	<tr>
	    		<@eam2008_RecordLog creator="sa" createdTime="2007-01-01" 
		 			   	  lastOperator="sa" lastModifiedTime="2007-01-01"/>
	 	  	</tr> 	 	  	
        </@inputTable>     
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listChangeLogs.html"/>
        	<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
        </@buttonBar>
    </@ww.form>
	
</@htmlPage>