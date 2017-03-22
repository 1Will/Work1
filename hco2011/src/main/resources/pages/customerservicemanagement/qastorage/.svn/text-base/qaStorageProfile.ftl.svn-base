<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: customerInfoProfile.ftl 2009-12-14 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('QA库维护页面')}">
<@ww.form name="'listForm'" action="'saveQaStorageAction'" method="'post'">
	<@ww.token name="saveQaStorageActionToken"/>
	
	<#if qaStorage.id?exists>
		<@ww.hidden name="'qaStorage.id'" value="#{qaStorage.id}"/>
	</#if>
	<@inputTable>
		<tr>
		<@textfield id="code" label="${action.getText('编号')}" maxlength="3"  name="qaStorage.code"  value="${qaStorage.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
		<@textfield id="applyProduct" label="${action.getText('适用产品')}" maxlength="3"  name="qaStorage.applyProduct"  value="${qaStorage.applyProduct?if_exists}"  required="true" anothername="checkapplyProduct"/>
		<@textfield id="versionNumber" label="${action.getText('版本号')}" maxlength="3"  name="qaStorage.versionNumber"  value="${qaStorage.versionNumber?if_exists}"  required="false" anothername="checkversionNumber"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('类型')}'" 
				id="type"
				name="'type.id'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allType"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('状态')}'" 
				id="state"
				name="'state.id'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('严重程度')}'" 
				id="severityDegree"
				name="'severityDegree.id'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allSeverityDegree"
				emptyOption="true" 
				disabled="false">
			</@ww.select>

		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('问题')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="qaStorage.question" rows="3" cols="120" >${qaStorage.question?if_exists}</textarea>
	        </td>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('解决方案')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="qaStorage.resolveProject" rows="3" cols="120" >${qaStorage.resolveProject?if_exists}</textarea>
	        </td>
		</tr>
		
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('内部提示')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="qaStorage.innerPrompt" rows="3" cols="120" >${qaStorage.innerPrompt?if_exists}</textarea>
	        </td>
		</tr>
		
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('备注')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="qaStorage.remark" rows="3" cols="120" >${qaStorage.remark?if_exists}</textarea>
	        </td>
		</tr>
		
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/qaStorage/listQaStorageAction.html"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
		<#-- 提交验证-->
		function storeValidation(){
			<#--if(!textfieldCheck_checkcode()){
				jgetObjByName("#code").focus();
				return false;
			}-->
			if(!textfieldCheck_checkapplyProduct()){
				jgetObjByName("#applyProduct").focus();
				return false;
			}
		}
	
	
	jgetObjByName(function(){
		<#if qaStorage.type?exists>
			jgetObjByName("#type").val("${qaStorage.type.id?if_exists}");
		</#if>
		<#if qaStorage.state?exists>
			jgetObjByName("#state").val("${qaStorage.state.id?if_exists}");
		</#if>
		<#if qaStorage.severityDegree?exists>
			jgetObjByName("#severityDegree").val("${qaStorage.severityDegree.id?if_exists}");
		</#if>
	});
</script>

</@htmlPage>
