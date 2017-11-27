<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${title?if_exists}">

<@ww.form namespace="'/customerRelationship'" name="'participant'" action="'saveParticipant'" method="'post'">
	<@ww.token name="saveParticipantToken"/>
    <@inputTable>
    	
		<@ww.hidden id="caFlag" name="'caFlag'" value="'${caFlag?if_exists}'"/>
    	<#if participant.backVisit?exists>
    		<@ww.hidden id="backVisit.id" name="'backVisit.id'" value="'#{participant.backVisit.id}'"/>
    	<#else>
    		<@ww.hidden id="backVisit.id" name="'backVisit.id'" value="${req.getParameter('backVisit.id')?if_exists}"/>
    	</#if>
    	
    	<#if participant.projectInfoPlan?exists>
    		<@ww.hidden id="projectInfoPlan.id" name="'projectInfoPlan.id'" value="'#{participant.projectInfoPlan.id}'"/>
    	<#else>
    		<@ww.hidden id="projectInfoPlan.id" name="'projectInfoPlan.id'" value="'${req.getParameter('projectInfoPlan.id')?if_exists}'"/>
    	</#if>
    	
    	<#if participant.personnelFiles?exists>
    		<@ww.hidden id="personnelFiles.id" name="'personnelFiles.id'" value="'#{participant.personnelFiles.id}'"/>
    	<#else>
    		<@ww.hidden id="personnelFiles.id" name="'personnelFiles.id'" value="''"/>
    	</#if>
    	
    	<#if participant.contactArchives?exists>
    		<@ww.hidden id="contactArchives.id" name="'contactArchives.id'" value="'#{participant.contactArchives.id}'"/>
    	<#else>
    		<@ww.hidden id="contactArchives.id" name="'contactArchives.id'" value="''"/>
    	</#if>
    	
    	
    	
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<@ww.hidden id="participant.id" name="'participant.id'" value="'${participant.id?if_exists}'"/>
    	
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${title?if_exists}:</label>
	     	</td>
	     	<#if caFlag?exists && caFlag=='caFlag'>
			<!--客户陪同者-->
		     	<td>
			   		<input type="text" name="contactArchives.name" class="underline"  value="<#if participant.contactArchives?exists>${participant.contactArchives.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
					<a onClick="contactArchives_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
	     	<#else>
	     	<!--回访同行者-->
		     	<td>
			   		<input type="text" name="personnelFiles.name" class="underline"  value="<#if participant.personnelFiles?exists>${participant.personnelFiles.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
					<a onClick="salesman_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
	     	</#if>
	     	
			<@textfield id="participant.duty" label="${action.getText('职责')}" maxlength="10"  name="participant.duty"  value="${participant.duty?if_exists}"  required="false" />
			
		</tr>
		
			<!--说明-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('说明')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="participant.explain" rows="4"  >${participant.explain?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("participant.explain").cols =width;
			</script>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
		<#-- 继续新建按钮   
			<#if projectInfoPlan.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlan.html?projectInfo.id=${projectInfoId?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPlan.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		 -->
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, setSalesman);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function setSalesman(result) {
		if (null != result) {
			getObjByName('personnelFiles.id').value = result[0];
	   		getObjByName('personnelFiles.name').value = result[2];		 	
		}
	}
	
	<#if backVisit?exists>
	<#if backVisit.customerInfo?exists>
	function contactArchives_OpenDialog(){
		var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisitFlag=backVisit&customer.id=#{backVisit.customerInfo.id}";
		popupModalDialog(url, 800, 600, setContactArchives);
	}
	function setContactArchives(result) {
		if (null != result) {
		 	getObjByName('contactArchives.id').value = result[0];
		 	getObjByName('contactArchives.name').value = result[1];
		}
	}
	</#if>
	</#if>
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证责任人*/
		if('' == getObjByName('contractPlan.startTime').value){
			alert("${action.getText('请选择开始时间！')}");
			getObjByName('contractPlan.startTime').focus();
     		return false;
		}
		if('' == getObjByName('contractPlan.endTime').value){
			alert("${action.getText('请选择结束时间！')}");
			getObjByName('contractPlan.endTime').focus();
     		return false;
		}
		
		if('' == getObjByName('contractPlan.executor.name').value){
			alert("${action.getText('请选择负责人！')}");
			getObjByName('contractPlan.executor.name').focus();
     		return false;
		}
		return true;
	}
	
</script>
</@htmlPage>
