<#include "../../includes/macros2.ftl" />

<@htmlPage title="仓库维护">
     <@ww.form  name="'listFrom'" action="'saveWeekly_'" namespace="'/warehouseInfo_'" method="'post'" >
     	 <@ww.token name="saveWeekly_Token"/>
     	  <@inputTable>
     	  	<@ww.hidden name="'personId'" value=""/>
		     <tr>
				<@ww.textfield label="'仓库编码'" name="'code'" value="'0001'" cssClass="'underline'" disabled="true"/>
				<@ww.textfield label="'仓库名'" name="'code'" value="'一号仓库'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'仓库负责人'" name="'code'" value="'刘伟'" cssClass="'underline'" required="true"/>
		  	</tr>
		     <tr>
				<@ww.textfield label="'电话'" name="'code'" value="'740740740 '" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'传真'" name="'code'" value="'740740740 '" cssClass="'underline'" />
				<@ww.textfield label="'邮编'" name="'code'" value="'740740'" cssClass="'underline'"/>
		  	</tr>
		  	<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('地址')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="education.comment" rows="4" cols="95" ></textarea>
		        </td>
			</tr>
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
	         <@redirectButton value="${action.getText('back')}" url="listWarehouseInfo_.html"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>