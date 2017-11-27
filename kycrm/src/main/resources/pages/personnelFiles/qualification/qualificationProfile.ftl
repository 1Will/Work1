<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('资格证书')}">
     <@ww.form action="'saveQualification'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="saveQualification"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       
         <#if personnelFiles?exists>
                <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id?if_exists}"/>
         </#if>
         <#if qualification.id?exists>
                <@ww.hidden name="'qualification.id'" value="#{qualification.id?if_exists}"/>
         </#if>
         
        <@inputTable>
    	<tr>
    	<@textfield label="${action.getText('证书名称')}" name="qualification.name" value="${qualification.name?if_exists}"  required="true" maxlength="20"/>
    	</tr>
    	<tr>
    	
    	<!--开始日期-->
            	<@datePickerRanger
						anothername="birthday"
		        		label="${action.getText('开始时间')}"
			           	name="qualification.starTime"
			     		value="${(qualification.starTime?string('yyyy-MM-dd'))?if_exists}" 
						cssClass="underline" 
						maxlength="10" 
						required="true"
						flag="true">
				</@datePickerRanger>
				
    	<!--开始日期-->
            	<@datePickerRanger
						anothername="birthday"
		        		label="${action.getText('到期时间')}"
			           	name="qualification.endTime"
			     		value="${(qualification.endTime?string('yyyy-MM-dd'))?if_exists}" 
						cssClass="underline" 
						maxlength="10" 
						required="true"
						flag="true">
				</@datePickerRanger>
     	</tr>
     	<tr>
	    	<td align="right" valign="top">
	    		<label class="label">${action.getText('说明')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="qualification.explain" rows="4" >${qualification.explain?if_exists}</textarea>.
	        	
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("qualification.explain").cols =width;
			</script>
	   </tr>
     	
    	<tr>
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	         </#if>
	         <@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
         </@buttonBar>	
     </@ww.form>

<script>
	function storeValidation(){
		if(getObjByName('qualification.name').value==''){
			alert('请输入证书名称');
			getObjByName('qualification.name').focus();
			return false;
		}
		
		if(getObjByName('qualification.starTime').value==''){
			alert('请选择开始时间');
			getObjByName('qualification.starTime').focus();
			return false;
		}
		
		if(getObjByName('qualification.endTime').value==''){
			alert('请选择结束时间');
			getObjByName('qualification.endTime').focus();
			return false;
		}
		
		return true;
	}
</script>
</@htmlPage>