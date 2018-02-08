<#include "../../includes/hco2011.ftl" />
<@htmlPage title="客户等级变更">
<@ww.form name="'listForm'" namespace="'/backvisit'" action="'saveBackVisitStep'" method="'post'">
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
			<tr id="StepingTr" name="StepingTr"   >
			<!--变更前客户等级-->
				<@ww.select label="'${action.getText('变更前客户等级')}'" 
				name="'customerSteping.id'" 
				value="'${req.getParameter('customerSteping.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="AllSteps"
				emptyOption="true"
				disabled="true">
			</@ww.select>
			<!--变更后客户等级-->
			<@ww.select label="'${action.getText('变更后客户等级')}'" 
				name="'customerSteped.id'" 
				value="${req.getParameter('customerSteped.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="AllSteps"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr id="changeReasonTr" name="changeReasonTr"   >
			<!--变更理由-->
			<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('变更理由')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="changeReason" rows="4" cols="150" >${backVisit.changReason?if_exists}</textarea>
	        </td>
			<!---->
		</tr>
	</@inputTable>
	<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
        </#if>
        <input type="button" value="关闭" onclick="window.close();"/>
	</@buttonBar>
</@ww.form>
</@htmlPage>
<script language="javascript">
	window.onload = function () {
		//客户等级
		<#if backVisit.id?exists>
	    <#if backVisit.customerSteping?exists>
			getObjByName('customerSteping.id').value='${backVisit.customerSteping.id?if_exists}';
		</#if>
		</#if>
		//隐藏的客户等级
		<#if backVisit.id?exists>
			<#if backVisit.customerSteping?exists>
				getObjByName('customerStepingId').value='${backVisit.customerSteping.id?if_exists}';
			</#if>
		</#if>
		//变更后客户等级
		<#if backVisit.id?exists>
	    <#if backVisit.customerSteped?exists>
			getObjByName('customerSteped.id').value='${backVisit.customerSteped.id?if_exists}';
		</#if>
		</#if>
		}
		
		
		
	function storeValidation(){
		    if('' == getObjByName('customerSteped.id').value){
				alert("${action.getText('backVisit.customerSteped.required')}");
	     		return false;
			}
		     if('' == getObjByName('changeReason').value){
				alert("${action.getText('backVisit.changeReason.required')}");
	     		return false;
			}
	}
	
	
</script>
