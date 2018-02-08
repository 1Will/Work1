<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('资格证书')}">
     <@ww.form action="'saveQualification'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="saveQualificationToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       
         <#if personnelFiles?exists>
	         <#if personnelFiles.id?exists>
                <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id?if_exists}"/>
         	</#if>
         <#else>
			<@ww.hidden name="'personnelFiles.id'" value=""/>
         </#if>
         
         <#if qualification.id?exists>
                <@ww.hidden name="'qualification.id'" value="#{qualification.id?if_exists}"/>
         </#if>
         
         <#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag' >
    		<@ww.hidden  name="popWindowFlag"  value="${popWindowFlag}"/>
    	</#if>
    	
        <@inputTable>
    	<tr>
    		<@textfield label="${action.getText('证书名称')}" name="qualification.name" value="${qualification.name?if_exists}"  required="true" maxlength="20"/>
    	
        	<@select 
        		label="${action.getText('证书类型')}" 
				name="type.id" 
				id="type.id" 
				value="${req.getParameter('type.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allType"
				required="true"
				emptyOption="false" 
				disabled="false">
			</@select>
			
		</tr>
    	<tr>
			<!--单位-->
        	<@select 
        		label="${action.getText('所属单位')}" 
				name="institution.id"
				value="${req.getParameter('institution.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allInsts"
				required="false"
				emptyOption="false" 
				disabled="false" >
			</@select>
			
			<td align="right" valign="top">
				<label class="label">${action.getText('所属人')}:</label>
			</td>
			<td>
				<input type="text" name="personnelFiles.name" class="underline"  value="<#if personnelFiles?exists>${personnelFiles.name?if_exists}<#else></#if>" maxlength="140" size="20" disabled="true"/>
				<a id="personnelFilesButton" onClick="personnelFile_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>

		<tr>
		<!--开始日期-->
        	<@datePickerRanger
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
     	<!--部门-->
        	<@select 
        		label="${action.getText('管理部门')}" 
				name="dept.id" 
				id="dept.id" 
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				required="true"
				emptyOption="false" 
				disabled="false">
			</@select>
     	</tr>
     	<tr>
     	<@textarea id="qualification.explain" name="qualification.explain" label="${action.getText('说明')}" value="${qualification.explain?if_exists}" rows="4" />
     	<script language="JavaScript" type="text/JavaScript">
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
	         
	         <#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
	         	<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
	         <#else>
	         	<@redirectButton value="${action.getText('back')}" url="listQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	         </#if>
	         
         </@buttonBar>	
     </@ww.form>

<script>
<#if qualification?exists>
<#if qualification.dept?exists>
	getObjByName('dept.id').value = '#{qualification.dept.id?if_exists}';
</#if>

<#if qualification.institution?exists>
	getObjByName('institution.id').value = '#{qualification.institution.id?if_exists}';
</#if>

<#if qualification.type?exists>
	getObjByName('type.id').value = '#{qualification.type.id?if_exists}';
</#if>

<#if req.getParameter('personnelFiles.id')?exists>
	getObjByName('type.id').disabled = true;
</#if>
</#if>
	var isper = true;
	document.onclick=function(){
		var  obj=document.getElementById("type.id");
		 for (i=0;i<obj.length;i++) {//下拉框的长度就是它的选项数.
			if (obj[i].selected== true ) {
				var text=obj[i].text;//获取当前选择项的值
				var bool = text.indexOf("个人");
				if(bool>=0){
					//个人证书
					getObjByName('personnelFilesButton').removeAttribute('style');
					getObjByName('institution.id').value = '';
					getObjByName('institution.id').disabled = true;
					isper = true;
				}else{
					//单位证书
					getObjByName('institution.id').disabled = '';
					getObjByName('personnelFiles.id').value = '';
					getObjByName('personnelFiles.name').value = '';
					getObjByName('personnelFilesButton').style.display = "none";
					isper = false;
				}
			}
		}
	}

	function storeValidation(){
		if(getObjByName('qualification.name').value==''){
			alert('请输入证书名称');
			getObjByName('qualification.name').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert('请输入管理部门');
			getObjByName('dept.id').focus();
			return false;
		}
		
		if(isper){
			if(getObjByName('personnelFiles.id').value==''){
				alert('请选择所属人！');
				getObjByName('personnelFiles.name').focus();
				return false;
			}
		}else{
			if(getObjByName('institution.id').value==''){
				alert('请选择所属单位！');
				getObjByName('institution.id').focus();
				return false;
			}
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
		<#if req.getParameter('personnelFiles.id')?exists>
			getObjByName('type.id').removeAttribute('disabled');
		</#if>
		return true;
	}
	
	//弹出业务员查询模态窗体
	function personnelFile_OpenDialog(){
		var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
		popupModalDialog(url, 800, 600, creatorSelectorHandler);
	 }
	 
	//获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			getObjByName('personnelFiles.id').value = result[0];
			getObjByName('personnelFiles.name').value = result[2];		 	
		}
	}
</script>
</@htmlPage>