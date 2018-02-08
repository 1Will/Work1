<#include "../../includes/macros2.ftl" />

<@htmlPage title="库区维护">
	<@ww.form  name="'listFrom'" action="'saveWeekly_'" namespace="'/warehouseInfo_'" method="'post'" >
     	 <@ww.token name="saveWeekly_Token"/>
     	  <@inputTable>
     	  	<@ww.hidden name="'personId'" value=""/>
		     <tr>
				<@ww.textfield label="'库区编码'" name="'code'" value="''" cssClass="'underline'" disabled="true"/>
				<@ww.textfield label="'库区名'" name="'code'" value="''" cssClass="'underline'" required="true"/>
		  		<@ww.select label="'仓库'"
	                    	name="area.code"
	                    	headerValue="一号仓库"
	                    	list="{
	                    		'',
	                    		'一号仓库',
	                    		'二号仓库',
	                    		'三号仓库'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>		  	</tr>
		  	<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('备注')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="education.comment" rows="4" cols="95" ></textarea>
		        </td>
			</tr>
     	  </@inputTable>
     	  <@buttonBar><@redirectButton value="${action.getText('save')}" url="#"/>
     	  <#--
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>-->
	         <@redirectButton value="${action.getText('back')}" url="listRegional_.html"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>