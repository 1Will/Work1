<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->


<#include "../../includes/hco2011.ftl" />

<@framePage title="">
<@ww.form action="'saveAdditionalInfo'" namespace="'/personnelFile'" method="'post'">
	<@ww.token name="saveAdditionalInfoToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
		<@ww.hidden name="'additionalInfo.id'" value="'${additionalInfo.id?if_exists}'" />
		<@ww.hidden name="'personnelFiles.id'" value="'${additionalInfo.pf.id?if_exists}'" />
	<tr>
		<@select label="${action.getText('additionalInfo.religion')}" 
			name="additionalInfo.religion"
			listKey="id"
			listValue="name"
			list="allReligion"
			emptyOption="true"
		/>
		<@select label="${action.getText('additionalInfo.health')}"
			name="additionalInfo.health"
			listKey="id"
			listValue="name"
			list="allHealth"
			emptyOption="true"
		/>
	</tr>
	<tr>
		<@textfield anothername="height" name="additionalInfo.height" type="D" label="${action.getText('additionalInfo.height')}"  value="${additionalInfo.height?if_exists}" cssClass="underline"/>
		<@textfield anothername="weight" name="additionalInfo.weight"  type="D" label="${action.getText('additionalInfo.weight')}"  value="${additionalInfo.weight?if_exists}" cssClass="underline"/>
		
	</tr> 
	<tr>
		<@textfield  anothername="sight" name="additionalInfo.sight"  type="D" label="${action.getText('additionalInfo.sight')}"  value="${additionalInfo.sight?if_exists}" cssClass="underline"/>
		<@textfield name="additionalInfo.blood"  label="${action.getText('additionalInfo.blood')}" value="${additionalInfo.blood?if_exists}" cssClass="underline"/>
	</tr>
	<tr>
		<@textfield name="additionalInfo.residenceAddrese" anothername="residenceAddrese" label="${action.getText('additionalInfo.residenceaddrese')}" value="${additionalInfo.residenceAddrese?if_exists}"  maxlength="20"/>
	<td align="right" valign="top"><label  class="label">${action.getText('additionalInfo.homeaddres')}:</label></td>
                <td colspan="3"><input type="text" size="50" class="underline" 
                                   name="additionalInfo.homeAddress"  value="${additionalInfo.homeAddress?if_exists}"
          />
	</td>
	</tr>
	<tr>
		<@textfield name="additionalInfo.postcode" anothername="postcode" type="P" label="${action.getText('additionalInfo.postcode')}" value="${additionalInfo.postcode?if_exists}" cssClass="underline"/>
	<td align="right" valign="top"><label  class="label">${action.getText('additionalInfo.commaddres')}:</label></td>
                <td colspan="3"><input type="text" size="50" class="underline" 
                                   name="additionalInfo.commAddress"  value="${additionalInfo.commAddress?if_exists}"
          />
	</td>
	</tr>
	<tr>
		<td align="right" valign="top">
        		<label class="label">
        			${action.getText('additionalInfo.interests')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="additionalInfo.interests"   rows="4"  >${additionalInfo.interests?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("additionalInfo.interests").cols =width;
			</script>
	</tr>
	</@inputTable>
		    <#if !(action.isReadOnly())>
		         <@buttonBar>
		            <@vsubmit value="'${action.getText('save')}'" onclick="'return bbb();'"/>
		         </@buttonBar>
	        </#if>
	</@ww.form >
</@framePage >
<script  language="JavaScript" type="text/JavaScript">
	function bbb(){
	
		if(!textfieldCheck_wageAccount()){
			return false;
		}
		if(!textfieldCheck_securityAccount()){
			return false;
		}
		if(!textfieldCheck_providentAccount()){
			return false;
		}
		if(!textfieldCheck_height()){
			return false;
		}
		if(!textfieldCheck_weight()){
			return false;
		}
		if(!textfieldCheck_sight()){
			return false;
		}
		if(!textfieldCheck_postcode()){
			return false;
		}
		if(!textfieldCheck_residenceAddrese()){
			return false;
		}
		if(getObjByName('additionalInfo.homeAddress').value.length>50){
			alert('${action.getText('additionalInfo.homeAddress.alert')}')
			return false;
		}
		if(getObjByName('additionalInfo.commAddress').value.length>50){
			alert('${action.getText('additionalInfo.commAddress.alert')}')
			return false;
		}
		if(getObjByName('additionalInfo.interests').value.length>500){
			alert('${action.getText('additionalInfo.interests.alert')}')
			return false;
		}
		return true;
	}
    window.onload=function(){
	      <#if additionalInfo.religion?exists>
			    getObjByName('additionalInfo.religion').value=${additionalInfo.religion.id};
			</#if>
	      <#if additionalInfo.health?exists>
	      		getObjByName('additionalInfo.health').value=${additionalInfo.health.id};
	      </#if>
  		}
</script>