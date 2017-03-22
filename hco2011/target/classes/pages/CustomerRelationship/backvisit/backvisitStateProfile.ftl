<#include "../../includes/hco2011.ftl" />
<@htmlPage title="客户告警状态变更">
<@ww.form name="'listForm'" namespace="'/backvisit'" action="'saveBackVisitState'" method="'post'">
	<@ww.token name="saveBackVisitToken"/>
	<@inputTable>
		<@ww.hidden name="'contactArchive.id'" value="''"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'customerStepingId'" value="'${req.getParameter('customerStepingId')?if_exists}'"/>
		<@ww.hidden name="'customerStatingId'" value="'${req.getParameter('customerStatingId')?if_exists}'"/>
		<#if backVisit.id?exists>
            <@ww.hidden name="'backVisit.id'" value="#{backVisit.id}"/>
        </#if>
		<!--页面重新排版 wclin 11.7.6
		_____________________________________________________________________________________________________-->
		<!--@@业务员录入@@-->
		
		 <tr ><td colspan="8"><hr/></td></tr>
		 <!--@@新增版块，等级变更@@-->
		 <!--客户等级变更-->
		
		<!--客户状态告警-->
		
			<tr id="StatingTr" name="StatingTr"    >
			<!--变更前客户等级-->
				<@ww.select label="'${action.getText('变更前客户告警状态')}'" 
				name="'customerStating.id'" 
				value="'${req.getParameter('customerStating.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="AllStates"
				emptyOption="true"
				disabled="true">
			</@ww.select>
			<!--变更后客户等级-->
			<@ww.select label="'${action.getText('变更后客户告警状态')}'" 
				name="'customerStated.id'" 
				value="${req.getParameter('customerStated.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="AllStates"
				emptyOption="true" 
				>
			</@ww.select>
		</tr>
		<tr id="changStateReasonTr" name="changStateReasonTr"   >
			<!--变更理由-->
			<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('变更理由')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="changStateReason" rows="3" cols="120" >${backVisit.changStateReason?if_exists}</textarea>
	        </td>
			<!---->
		</tr>
		
	</@inputTable>
	<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
        </#if>
        <input type="button" value="关闭" onclick="window.close()"/>
	</@buttonBar>
</@ww.form>
</@htmlPage>
<script language="javascript">
	window.onload = function () {
	
		
		
		
			//客户告警
		<#if backVisit.id?exists>
	    <#if backVisit.customerStating?exists>
			getObjByName('customerStating.id').value='${backVisit.customerStating.id?if_exists}';
		</#if>
			<#if req.getParameter('backVisit.customerStating')?exists>
			getObjByName('customerStating.id').value='${req.getParameter('backVisit.customerStating')}';
		</#if>
		</#if>
		
		//隐藏的客户告警
		
		<#if backVisit.id?exists>
			<#if backVisit.customerStating?exists>
				getObjByName('customerStatingId').value='${backVisit.customerStating.id?if_exists}';
			</#if>
		<#else>
			<#if req.getParameter('customerStatingId')?exists>
				getObjByName('customerStatingId').value='${req.getParameter('customerStatingId')}';
			</#if>
		</#if>
		
		
			//客户变更后的告警
		<#if backVisit.id?exists>
	    <#if backVisit.customerStated?exists>
			getObjByName('customerStated.id').value='${backVisit.customerStated.id?if_exists}';
		</#if>
			<#if req.getParameter('backVisit.customerSteping')?exists>
			getObjByName('customerStated.id').value='${req.getParameter('backVisit.customerStated')}';
		</#if>
		</#if>
		
		}
	

	function storeValidation(){
		
		    if('' == getObjByName('customerStated.id').value){
				alert("${action.getText('backVisit.customerSteped.required')}");
	     		return false;
			}
		     if('' == getObjByName('changStateReason').value){
				alert("${action.getText('backVisit.changeReason.required')}");
	     		return false;
			}
	     
	   
	}
	
</script>
