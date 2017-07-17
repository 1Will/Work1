<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('backvisitProfile')}">
<@ww.form name="'listForm'" namespace="'/backvisit'" action="'saveBackVisit'" method="'post'">
	<@ww.token name="saveBackVisitToken"/>
	<@inputTable>
		<@ww.hidden name="'contactArchive.id'" value="''"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'fromType'" value="'${req.getParameter('fromType')?if_exists}'"/>
		<@ww.hidden name="'contactArchives'" value="'${backVisit.contactArchives?if_exists}'"/>
		<@ww.hidden name="'employees'" value="'${backVisit.employees?if_exists}'"/>
		<@ww.hidden name="'gradeChange'" value="1"/>
		<@ww.hidden name="'isSaved'" value=""/>
		<#if backVisit.employee?exists>
		<@ww.hidden name="'backVisiter.id'" value="'${backVisit.employee.id?if_exists}'"/>
		
		<#else>
		<@ww.hidden name="'backVisiter.id'" value="''"/>
		</#if>
		<#if backVisit.id?exists>
            <@ww.hidden name="'backVisit.id'" value="#{backVisit.id}"/>
            <@ww.hidden name="'customer.id'" value="'#{backVisit.customerInfo.id?if_exists}'"/>
        <#else>
        	<@ww.hidden name="'customer.id'" value="''"/>
        </#if>
        <#if backVisit.projectInfo?exists>
             <@ww.hidden name="'projectInfo.id'" value="'#{backVisit.projectInfo.id?if_exists}'"/>
            <#else>
            <@ww.hidden name="'projectInfo.id'" value="''"/>
            </#if>
		<!--页面重新排版 wclin 11.7.6
		_____________________________________________________________________________________________________-->
		<!--@@业务员录入@@-->
		<tr>
			<!--回访类型-->
			<#--<@ww.textfield label="'${action.getText('backVisit.name')}'" name="'backVisit.name'" value="'${backVisit.name?if_exists}'" cssClass="'underline'" required="true" />-->
			<@ww.select 
	    		label="'${action.getText('backVisit.backVisitType')}'"
				required="true"
				name="'backVisitType.id'" 
				value="${req.getParameter('backVisitType.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allBackVisitType"
			    emptyOption="false" 
		    	disabled="false"/>
		    <script language="javascript">
			     	<#if backVisit.backVisitType?exists>
			     		getObjByName('backVisitType.id').value = ${backVisit.backVisitType.id};
			     	</#if>
			</script>
			<!--客户名称-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('customer')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="customer" class="underline"  value="${backVisit.customerName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<!--所属项目-->
	    <td align="right" valign="top">
	       		<label class="label">${action.getText('projectInfo')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="backVisit.projectName" class="underline"  value="${backVisit.projectName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="projectInfo_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<!--联系人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('contactArchive')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="contactArchive" class="underline"  value="${backVisit.caName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="contactArchive_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<!--联系人陪同者-->
			<td align="right" valign="top">
			<!--
	       		<span class="required">*</span>
	       	-->
	       		<label class="label">${action.getText('contactArchive')}陪同者:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="contactArchives_" class="underline"  value="${backVisit.contactArchives?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="contactArchives_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<!--回访时间-->
			<@pp.datePicker 
				label="'${action.getText('backVisitDate')}'" 
				name="'backVisit.backVisitDate'" 
	   			value="'${(backVisit.backVisitDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if backVisit.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("backVisit.backVisitDate").value==''){
						getObjByName("backVisit.backVisitDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
		</tr>
		<tr>
			<!--耗时（分）-->
			<@ww.textfield label="'${action.getText('costTime')}'" name="'backVisit.costTime'" value="'#{backVisit.costTime?if_exists}'" cssClass="'underline'" required="true" />
			<!--回访方式-->
			<@ww.select 
	    		label="'${action.getText('backVisitWay')}'"
				required="true"
				name="'backVisitWay.id'" 
				value="${req.getParameter('backVisitWay.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allBackVisitWay"
			    emptyOption="false" 
		    	disabled="false"/>
		    <script language="javascript">
			     	<#if backVisit.backVisitWay?exists>
			     		getObjByName('backVisitWay.id').value = ${backVisit.backVisitWay.id};
			     	</#if>
			</script>
		</tr>
		<tr>
			<!--回访人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('employee')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="backVisiter" class="underline"  value="<#if backVisit.employee?exists>${backVisit.employee.name?if_exists}<#else>${userManager.user.name}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<!--回访人同行者-->
			<td align="right" valign="top">
			<!--
	       		<span class="required">*</span>
	       	-->
	       		<label class="label">${action.getText('employee')}同行者:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="employees_" class="underline"  value="<#if backVisit.employees?exists>${backVisit.employees?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="salesmans_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<!--继续回访-->
			<td align="right"><label for="" class="label">${action.getText('continueBackVisit')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="continueBackVisit0" name="backVisit.continueBackVisit" value="0"  checked/>是
	        	<input type="radio" id="continueBackVisit1" name="backVisit.continueBackVisit" value="1" />否
	        	<script language="javascript">
	        		<#if backVisit.continueBackVisit?exists>
	        			if(${backVisit.continueBackVisit}=='0'){
	        				getObjByName('continueBackVisit0').checked = true;
	        			}else if(${backVisit.continueBackVisit}=='1'){	
	        				getObjByName('continueBackVisit1').checked = true;
	        			}
			     	</#if>
	        	</script>
			</td>
		</tr>
		<tr>
			<!--下次回访时间-->
			<@pp.datePicker 
				label="'${action.getText('nextBackVisitDate')}'" 
				name="'backVisit.nextBackVisitDate'" 
	   			value="'${(backVisit.nextBackVisitDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<!--未联系（天）-->
			<@ww.textfield label="'${action.getText('unconnect')}'" name="'backVisit.unconnect'" value="'${backVisit.unconnect?if_exists}'" cssClass="'underline'" disabled="true" />
			<td align="right"><label for="" class="label">${action.getText('publicBackVisit')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="isPublic0" name="backVisit.isPublic" value="0"  checked/>是
	        	<input type="radio" id="isPublic1" name="backVisit.isPublic" value="1" />否
	        	<script language="javascript">
	        		<#if backVisit.isPublic?exists>
	        			if(${backVisit.isPublic}=='0'){
	        				getObjByName('isPublic0').checked = true;
	        			}else if(${backVisit.isPublic}=='1'){	
	        				getObjByName('isPublic1').checked = true;
	        			}
			     	</#if>
	        	</script>
			</td>
		</tr>
		<tr>
			<!--回访内容-->
			<td align="right" valign="top">
        		<label class="label">
        			<font color='red'>*</font>${action.getText('backVisitContent')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="backVisit.backVisitContent" rows="4" cols="150" >${backVisit.backVisitContent?if_exists}</textarea>
	        	<script>
	        	getObjByName('backVisit.backVisitContent').onfocus=function(){
	        	if(getObjByName('backVisit.backVisitContent').value=='请输入拜访客户目的及主要沟通内容，建议用3句话描述清楚'){
	        	getObjByName('backVisit.backVisitContent').value='';
	        	getObjByName('backVisit.backVisitContent').style.color='000000';}
	        	}
	        	getObjByName('backVisit.backVisitContent').onblur=function(){
	        	if(getObjByName('backVisit.backVisitContent').value==''){
	        	getObjByName('backVisit.backVisitContent').value='请输入拜访客户目的及主要沟通内容，建议用3句话描述清楚';
	        	getObjByName('backVisit.backVisitContent').style.color='999999';}
	        	}
	        	</script>
	        </td>
			<!---->
		</tr>
		<tr>
			<!--客户反馈-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('feedback')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="backVisit.feedback" rows="4" cols="150" >${backVisit.feedback?if_exists}</textarea>
	        	<script>
	        	getObjByName('backVisit.feedback').onfocus=function(){
	        	if(getObjByName('backVisit.feedback').value=='请输入沟通过程中客户的反馈，建议用3句话描述'){
	        	getObjByName('backVisit.feedback').value='';
	        	getObjByName('backVisit.feedback').style.color='000000';}
	        	}
	        	getObjByName('backVisit.feedback').onblur=function(){
	        	if(getObjByName('backVisit.feedback').value==''){
	        	getObjByName('backVisit.feedback').value='请输入沟通过程中客户的反馈，建议用3句话描述';
	        	getObjByName('backVisit.feedback').style.color='999999';}
	        	}
	        	</script>
	        </td>
			<!---->
		</tr>
		<tr>
			<!--后期注意-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('attention')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="backVisit.attention" rows="4" cols="150" >${backVisit.attention?if_exists}</textarea>
	        	<script>
	        	getObjByName('backVisit.attention').onfocus=function(){
	        	if(getObjByName('backVisit.attention').value=='请输入项目维护后期要注意的内容'){
	        	getObjByName('backVisit.attention').value='';
	        	getObjByName('backVisit.attention').style.color='000000';}
	        	}
	        	getObjByName('backVisit.attention').onblur=function(){
	        	if(getObjByName('backVisit.attention').value==''){
	        	getObjByName('backVisit.attention').value='请输入项目维护后期要注意的内容';
	        	getObjByName('backVisit.attention').style.color='999999';}
	        	}
	        	</script>
	        </td>
			<!---->
		</tr>
		<tr>
			<!--备注-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('remarks')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="backVisit.remarks" rows="4" cols="150" >${backVisit.remarks?if_exists}</textarea>
	        	<script>
	        	getObjByName('backVisit.remarks').onfocus=function(){
	        	if(getObjByName('backVisit.remarks').value=='请输入补充信息'){
	        	getObjByName('backVisit.remarks').value='';
	        	getObjByName('backVisit.remarks').style.color='000000';}
	        	}
	        	getObjByName('backVisit.remarks').onblur=function(){
	        	if(getObjByName('backVisit.remarks').value==''){
	        	getObjByName('backVisit.remarks').value='请输入补充信息';
	        	getObjByName('backVisit.remarks').style.color='999999';}
	        	}
	        	</script>
	        </td>
			<!---->
		</tr>
		 <tr ><td colspan="8"><hr/></td></tr>
		 <!--@@新增版块，等级变更@@-->
		 <!--客户等级变更
		 <tr>
			
			<td align="right">
			<div id="showLabel">
			<label for="" class="label">${action.getText('客户等级变更')}:</label>
			</div>
			</td>
	        <td align="left">
	        	<div id="showRadio">
	        	<input type="radio" id="gradeChange0" name="gradeChange" value="0" onClick="changeStept();"/>是
	        	<input type="radio" id="gradeChange1" name="gradeChange" value="1" checked onClick="changeStepf();"/>否
				</div>
			</td>
			</tr>
			-->
			
			<tr id="StepingTr" name="StepingTr"  style="display:none" >
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
			
			<#--
			<@ww.select label="'${action.getText('变更后客户等级')}'" 
			name="'step.id'" 
			value="'${req.getParameter('step.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allSteps"
			emptyOption="true" 
			disabled="false">
		</@ww.select>-->
			<!---->
		</tr>
		<tr id="changeReasonTr" name="changeReasonTr"  style="display:none" >
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
		
		<!--客户状态告警-->
		
			<tr id="StatingTr" name="StatingTr"  style="display:none"  >
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
		<tr id="changStateReasonTr" name="changStateReasonTr"  style="display:none" >
			<!--变更理由-->
			<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('变更理由')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="changStateReason" rows="4" cols="150" >${backVisit.changStateReason?if_exists}</textarea>
	        </td>
			<!---->
		</tr>
		
	</@inputTable>
	<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
            <#if backVisit.isSaved?exists &&backVisit.isSaved=='0' >
            <@vsubmit name="'save'" value="'${action.getText('tj')}'" onclick="'return storeValidationTj();'"  >
            </@vsubmit>
            <#else>
            <@vsubmit name="'save'" value="'${action.getText('tj')}'" onclick="'return storeValidationTj();'"  disabled="true">
            </@vsubmit>
             </#if>
        </#if>
        
        <#-- 继续新建按钮   -->
			<#if backVisit.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/backvisit/editBackVisit.html"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/backvisit/editBackVisit.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
        
	    <#if req.getParameter('fromType')?exists && req.getParameter('fromType') == 'h'>
	    <a href='${req.contextPath}/backvisit/listBackVisitTab.html?customerInfo.id=#{backVisit.customerInfo.id?if_exists}' name="alink" id="alink" style="display:none">
			${action.getText('back')}
            </a>
            <input type="button" value="${action.getText('back')}" onclick="javascript:getObjByName('alink').click();"/>
         	
		<#else>
		<#if openFlag?exists>
		<input type="button" value="${action.getText('关闭')}" onclick="closeThis();"/>
       <#else>
        <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/backvisit/listBackVisit.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       </#if>
        </#if>
        <#if backVisit.id?exists>
        <input type="button" value="等级变更" onclick="backvistStep_OpenDialog()"/>
        <input type="button" value="风险预警" onclick="backvistState_OpenDialog()"/>
        <input type="button" value="项目失败" onclick=""/>
        </#if>
	</@buttonBar>
</@ww.form>
</@htmlPage>
<script language="javascript">
	window.onload = function () {
			if(getObjByName('backVisit.backVisitContent').value==''){
	        	getObjByName('backVisit.backVisitContent').value='请输入拜访客户目的及主要沟通内容，建议用3句话描述清楚';
	        	getObjByName('backVisit.backVisitContent').style.color='999999';
	        	}
			if(getObjByName('backVisit.feedback').value==''){
	        	getObjByName('backVisit.feedback').value='请输入沟通过程中客户的反馈，建议用3句话描述';
	        	getObjByName('backVisit.feedback').style.color='999999';
	        	}
			if(getObjByName('backVisit.attention').value==''){
	        	getObjByName('backVisit.attention').value='请输入项目维护后期要注意的内容';
	        	getObjByName('backVisit.attention').style.color='999999';
	        	}
			if(getObjByName('backVisit.remarks').value==''){
	        	getObjByName('backVisit.remarks').value='请输入补充信息';
	        	getObjByName('backVisit.remarks').style.color='999999';
	        	}
			//客户等级
	
		//联系人
		<#if backVisit.id?exists>
		<#if !(backVisit.caName?exists) && backVisit.customerInfo?exists>
		getObjByName('contactArchive').value='${backVisit.customerInfo.keyContacter?if_exists}';
		</#if>
		<#if req.getParameter('backVisit.contactArchive')?exists>
			getObjByName('contactArchive').value='${req.getParameter('backVisit.contactArchive')}';
		</#if>
		</#if>
		}
		function projectInfo_OpenDialog(){
		if(getObjByName('customer.id').value !=''){
			var  url = "${req.contextPath}/projectInfo/listProjectInfo.html?contactArchivesFlag=contactArchivesFlag&customer.id="+getObjByName('customer.id').value;
			popupModalDialog(url, 800, 600, creatorSelectorHandlerProjectInfo);
		}else{
			alert('请先选择客户');
		}
	}
	
	function creatorSelectorHandlerProjectInfo(result) {
		if (null != result) {
		 	document.forms[0].elements["projectInfo.id"].value = result[0];	
		 	document.forms[0].elements["backVisit.projectName"].value = result[1];
		 	document.forms[0].elements["contactArchive.id"].value = result[2];	
		 	document.forms[0].elements["contactArchive"].value = result[3];
		}
	}
		
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandlerCustomer);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandlerCustomer(result) {
		if (null != result) {
		 	getObjByName('customer.id').value = result[0];	
		 	getObjByName('customer').value = result[1];	
		 	//getObjByName('contactArchive').value = result[8];
		 	getObjByName('customerSteping.id').value = result[9];
		 	getObjByName('customerStepingId').value =  result[9];
		}
	}
	function contactArchive_OpenDialog(){
		if(getObjByName('customer.id').value !=''){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?backVisitFlag=backVisit&customer.id="+getObjByName('customer.id').value;
			popupModalDialog(url, 800, 600, creatorSelectorHandlerContactArchives);
		}else{
			alert('请先选择客户');
		}
	}
	function contactArchives_OpenDialog(){
		if(getObjByName('customer.id').value !=''){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?backVisitCheckBox=backVisitCheckBox&backVisitFlag=backVisit&customer.id="+getObjByName('customer.id').value;
			popupModalDialog(url, 800, 600, creatorSelectorHandlerContactArchivess);
		}else{
			alert('请先选择客户');
		}
	}
	function creatorSelectorHandlerContactArchives(result) {
		if (null != result) {
		 	document.forms[0].elements["contactArchive.id"].value = result[0];	
		 	document.forms[0].elements["contactArchive"].value = result[1];
		}
	}
	function creatorSelectorHandlerContactArchivess(result) {
		if (null != result) {
			var contactArchives_Temp=document.forms[0].elements["contactArchives_"].value;
			if(contactArchives_Temp==""){
				contactArchives_Temp=result[0];
			}else{
				contactArchives_Temp+=","+result[0];
			}
			var names =contactArchives_Temp.split(',');
			var newNames =unique(names);
			var newContactArchives_Temp='';
			for(var i=0;i<newNames.length;i++){
				if(i==newNames.length-1){
					newContactArchives_Temp+=newNames[i];
				}else{
					newContactArchives_Temp+=newNames[i]+',';
				}
			}
	 		document.forms[0].elements["contactArchives"].value = newContactArchives_Temp;
	 		document.forms[0].elements["contactArchives_"].value = newContactArchives_Temp;
		}
	}
	
	function unique(arr){
		// 遍历arr，把元素分别放入tmp数组(不存在才放)
		var tmp = new Array();
		for(var i=0;i<arr.length;i++){
			//该元素在tmp内部不存在才允许追加
			if(tmp.indexOf(arr[i])==-1){
				tmp.push(arr[i]);
			}
		}
		return tmp;
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //弹出业务员同行者查询模态窗体
	 function salesmans_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?backVisitCheckBox=backVisitCheckBox";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler_);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["backVisiter.id"].value = result[0];
	   		document.forms[0].elements["backVisiter"].value = result[2];		 	
		}
	}
	 //获得模态窗体返回值
	function creatorSelectorHandler_(result) {
		if (null != result) {
			var tempEmployees_ =document.forms[0].elements["employees_"].value;
			if(tempEmployees_==""){
				tempEmployees_=result[0];
			}else{
				tempEmployees_+=","+result[0];
			}
			var names =tempEmployees_.split(',');
			var newNames =unique(names);
			var newTempEmployees='';
			for(var i=0;i<newNames.length;i++){
				if(i==newNames.length-1){
					newTempEmployees+=newNames[i];
				}else{
					newTempEmployees+=newNames[i]+',';
				}
			}
			document.forms[0].elements["employees_"].value = newTempEmployees;
		   	document.forms[0].elements["employees"].value = newTempEmployees;		 	
		}
	}
	
	function check(){
		/* 验证回访主题 */
     	if('-1'==getObjByName('backVisitType.id').value){
			alert("${action.getText('backVisitType.id.required')}");
			getObjByName('backVisitType.id').focus();
     		return false;
		}
     	/* 验证客户名称 */
     	if('' == getObjByName('customer').value){
			alert("${action.getText('backVisit.customer.required')}");
     		return false;
		}
		/* 验证联系人 */
		if('' == getObjByName('contactArchive').value){
			alert("${action.getText('backVisit.contactArchive.required')}");
     		return false;
		}
		/* 验证回访日期 */
		if('' == getObjByName('backVisit.backVisitDate').value){
    		alert("${action.getText('backVisit.backVisitDate.not.null')}");
    		getObjByName('backVisit.backVisitDate').focus();
          	return false;
    	}else{
		 	if(!isDate('backVisit.backVisitDate')){
				alert("${action.getText('backVisitDate.error')}");
				getObjByName('backVisit.backVisitDate').value="";
				getObjByName('backVisit.backVisitDate').focus();
				return false;
			}
		}
		/* 验证耗时 */
		if('' == getObjByName('backVisit.costTime').value){
			alert("${action.getText('backVisit.costTime.required')}");
			getObjByName('backVisit.costTime').focus();
     		return false;
		}else{
     		if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('backVisit.costTime').value), 1000000001, -1)){
     			alert("${action.getText('backVisit.costTime.maxLength')}");
     			getObjByName('backVisit.costTime').value="";
     			getObjByName('backVisit.costTime').focus();
     			return false;
     		}
     	}
     	/* 验证回访方式 */
     	if('-1'==getObjByName('backVisitWay.id').value){
			alert("${action.getText('backVisitWay.id.required')}");
			getObjByName('backVisitWay.id').focus();
     		return false;
		}
		/* 验证回访人 */
		if('' == getObjByName('backVisiter').value){
			alert("${action.getText('backVisit.backVisiter.required')}");
     		return false;
		}
		/* 选择继续回访的话，必须填下次回访日期 */
		if(getObjByName('continueBackVisit0').checked == true && '' == getObjByName('backVisit.nextBackVisitDate').value){
				alert("继续回访必须输入下次回访时间");
				getObjByName('backVisit.nextBackVisitDate').focus();
				return false;
		}
		
		/* 验证下次回访日期 */
		if('' != getObjByName('backVisit.nextBackVisitDate').value){
			if(!isDate('backVisit.nextBackVisitDate')){
				alert("${action.getText('nextBackVisitDate.error')}");
				getObjByName('backVisit.nextBackVisitDate').value="";
				getObjByName('backVisit.nextBackVisitDate').focus();
				return false;
			}
		}
		/* 验证回访内容 */
		if('' == getObjByName('backVisit.backVisitContent').value){
			alert("${action.getText('backVisit.backVisitContent.required')}");
			getObjByName('backVisit.backVisitContent').focus();
     		return false;
		}else{
     		if(!isValidLengthValue(getObjByName('backVisit.backVisitContent').value,0,500)){
     			alert("${action.getText('backVisit.backVisitContent.maxLength')}");
     			getObjByName('backVisit.backVisitContent').value="";
     			getObjByName('backVisit.backVisitContent').focus();
     			return false;
     		}
     	}
     	/* 验证客户反馈 */
     	if('' != getObjByName('backVisit.feedback').value){
	     	if(!isValidLengthValue(getObjByName('backVisit.feedback').value,0,500)){
	     		alert("${action.getText('backVisit.feedback.maxLength')}");
	     		getObjByName('backVisit.feedback').value="";
     			getObjByName('backVisit.feedback').focus();
	     		return false;
	     	}
	     }
	     /* 验证后期注意 */
     	if('' != getObjByName('backVisit.attention').value){
	     	if(!isValidLengthValue(getObjByName('backVisit.attention').value,0,500)){
	     		alert("${action.getText('backVisit.attention.maxLength')}");
	     		getObjByName('backVisit.attention').value="";
     			getObjByName('backVisit.attention').focus();
	     		return false;
	     	}
	     }
	     /* 验证备注 */
     	if('' != getObjByName('backVisit.remarks').value){
	     	if(!isValidLengthValue(getObjByName('backVisit.remarks').value,0,500)){
	     		alert("${action.getText('backVisit.remarks.maxLength')}");
	     		getObjByName('backVisit.remarks').value="";
     			getObjByName('backVisit.remarks').focus();
	     		return false;
	     	}
	     }
	}
	
	function setValue(){
		if(getObjByName('backVisit.backVisitContent').value=='请输入拜访客户目的及主要沟通内容，建议用3句话描述清楚'){
        	getObjByName('backVisit.backVisitContent').value='';
        	getObjByName('backVisit.backVisitContent').style.color='000000';
        }
		if(getObjByName('backVisit.feedback').value=='请输入沟通过程中客户的反馈，建议用3句话描述'){
        	getObjByName('backVisit.feedback').value='';
        	getObjByName('backVisit.feedback').style.color='000000';
        	}
		if(getObjByName('backVisit.attention').value=='请输入项目维护后期要注意的内容'){
        	getObjByName('backVisit.attention').value='';
        	getObjByName('backVisit.attention').style.color='000000';
        	}
		if(getObjByName('backVisit.remarks').value=='请输入补充信息'){
        	getObjByName('backVisit.remarks').value='';
        	getObjByName('backVisit.remarks').style.color='000000';
        	}
	}
	
	
	function storeValidation(){
	     getObjByName('isSaved').value="0";
     	 setValue();
     	 return check();
	     
	}
	
	function storeValidationTj(){
		getObjByName('isSaved').value="1";
		setValue();
		return check();
	}
	
	
	//弹出客户等级变更窗体
	function backvistStep_OpenDialog(){
	<#if backVisit.id?exists>
			 var url = "${req.contextPath}/backvisit/editBackVisitStep.html?backVisit.id=#{backVisit.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
	  		 popupModalDialog(url, 1050, 300);
	  		 if(isIE()){self.location.reload();};
			</#if>
	  
	 }
	 //弹出客户告警状态变更窗体
	function backvistState_OpenDialog(){
	<#if backVisit.id?exists>
			 var url = "${req.contextPath}/backvisit/editBackVisitState.html?backVisit.id=#{backVisit.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
	  		 popupModalDialog(url, 1050, 300);
	  		 if(isIE()){self.location.reload();};
			</#if>
	  
	 }
	
</script>
<#if backVisit.id?exists>
<ul id="beautytab">
	<li>
        <a id="additionalInformation" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?backVisit.id=#{backVisit.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >附件资料</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/backvisit/listChangeStepToHistory.html?customerId.id=#{backVisit.customerInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >等级变更历史</a>
	</li>
	<li>
		<a id="replyBackVisit" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/workReport/listReplyDailyTab.html?backVisit.id=#{backVisit.id?if_exists}' target="frame" >消息回复</a>
	</li>
</ul>
	<iframe name="frame" frameborder="0.5" src="${req.contextPath}/applicationDocManager/listApplicationDoc.html?backVisit.id=#{backVisit.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>