<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('合同状态变更')}">
<@ww.form namespace="'/projectInfo'" name="'projectState'" action="'saveProjectState'" method="'post'">
<#assign returnUrl="${req.contextPath}/applicationDocManager/editApplicationDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&yesUrl=yesUrl"/>
 <@ww.token name="saveContractStateToken"/>
    <@inputTable>
    	<#if projectState.id?exists>
            <@ww.hidden name="'projectState.id'" value="#{projectState.id}"/>
        </#if>
        
        <#if projectInfo?exists>
        <#if projectInfo.id?exists>
        	<@ww.hidden name="'projectInfo.id'" value="#{projectInfo.id}"/>
        	<#assign returnUrl=returnUrl + '&projectInfo.id=#{projectInfo.id}'/>
        </#if>
        </#if>
    	</tr>
	    	<@ww.select label="'${action.getText('变更前状态')}'" 
				id="beforeStateid"
				name="'beforeStateid'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="false" 
				disabled="true">
			</@ww.select>
			<script language="javascript">
				<#if projectState.beforeState?exists>
					getObjByName("beforeStateid").value =${projectState.beforeState.id?if_exists};
				<#else>
					getObjByName("beforeStateid").value =${projectInfo.state.id?if_exists};
				</#if>
			</script>
	    	<@ww.select label="'${action.getText('变更后状态')}'" 
				id="newStateid"
				name="'newStateid'" 
				value="''"
				listKey="id"
				listValue="name"
				list="changeState"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<script language="javascript">
				<#if projectState.newState?exists>
					getObjByName("newStateid").value =${projectState.newState.id?if_exists};
				<#else>
					getObjByName("newStateid").value =${projectInfo.state.id?if_exists};
				</#if>
			</script>
	    	
   	    <tr>
	    	<td align="right" valign="top">
	    		<label class="label">${action.getText('变更说明')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="projectState.explain" rows="4" >${projectState.explain?if_exists}</textarea>
	        	<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("projectState.explain").cols =width;
			</script>
	        </td>
	   </tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
          <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
    </#if>
          <@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="closeThis();"/>
    </@buttonBar>
    <@ww.hidden name="'origFileName'" value=""/>
<script language="javascript">

	function storeValidation(){
		getObjByName('beforeStateid').removeAttribute('disabled');
		return true;
	}
	 	 
</script>
</@ww.form>
</@htmlPage>
