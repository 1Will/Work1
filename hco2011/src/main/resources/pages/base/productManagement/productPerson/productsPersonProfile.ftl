<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('开发团队')}">
<@ww.form namespace="'/productsManager'" name="'productsPerson'" action="'saveProductsPerson'" method="'post'">
	<@ww.token name="saveProductsPersonToken"/>
    <@inputTable>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
    	<#if productsPerson.id?exists>
    		<@ww.hidden name="'productsPerson.id'" value="#{productsPerson.id}"/>
    		<@ww.hidden name="'products.id'" value="'#{productsPerson.products.id?if_exists}'"/>
    	<#else>
    		<@ww.hidden name="'products.id'" value="'#{products.id?if_exists}'"/>
	 	</#if>
	 	
    	<#if productsPerson.developer?exists>
    		<@ww.hidden name="'developer.id'" value="#{productsPerson.developer.id}"/>
    	<#else>
    		<@ww.hidden name="'developer.id'" value=""/>
	 	</#if>
	
    <tr>
    <!--开发成员-->
		<td align="right" valign="top">
       		<span class="required">*</span>
       		<label class="label">${action.getText('开发成员')}:</label>
     	</td>
     	<td>
	     	<#if productsPerson.developer?exists>
	     		<input type="text" id ="developer.name" name="developer.name" class="underline"  value="${productsPerson.developer.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     		<input type="text" id ="developer.name" name="developer.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
			<a onClick="salesman_OpenDialog();">
				<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
			</a>
		</td>
		<@ww.select label="'${action.getText('角色')}'" 
			id="role.id" 
			name="'role.id'" 
			value="'${req.getParameter('role.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allRole"
			required="true"
			emptyOption="false" 
			>
		</@ww.select>
		<script language="javascript">
			<#if productsPerson.role?exists>
				getObjByName('role.id').value = '#{productsPerson.role.id?if_exists}';
			<#else>
				getObjByName('role.id').value = '-1';
			</#if>
		</script>
    </tr>
    
    <tr>
		<!--备注-->
		<td align="right" valign="top">
    		<label class="label">
    			${action.getText('角色说明')}:
    		</label>
    	</td>
        <td colspan="10">
        <#if productsPerson.explain?exists>
        	<textarea name="productsPerson.explain" rows="4" cols="150" >${productsPerson.explain?if_exists}</textarea>
        <#else>
        	<textarea name="productsPerson.explain" rows="4" cols="150" ></textarea>
       </#if>
        </td>
	</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
		<#-- 继续新建按钮    
			<#if projectInfoPersonnels.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPer.html?projectInfo.id=${projectInfoId?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProPer.html?projectInfo.id=${projectInfoId?if_exists}"/>
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
		var newopt=document.createElement("option");
		newopt.text="";
		newopt.value="-1";
		getObjByName('role.id').options.add(newopt,0);
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["developer.id"].value = result[0];
	   		document.forms[0].elements["developer.name"].value = result[2];		 	
		}
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证项目组成员*/
		if('' == getObjByName('developer.name').value){
			alert("${action.getText('请选择开发成员')}");
     		return false;
		}
		/* 业务属性*/
		if('-1' == getObjByName('role.id').value){
			alert("${action.getText('请选择开发者角色')}");
     		return false;
		}
		return true;
	}
	
</script>
</@htmlPage>
