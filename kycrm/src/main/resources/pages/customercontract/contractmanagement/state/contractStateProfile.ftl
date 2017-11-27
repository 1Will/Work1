<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('合同状态变更')}">
<@ww.form namespace="'/contractManagement'" name="'contractState'" action="'saveContractState'" enctype="'multipart/form-data'" method="'post'">
<#assign returnUrl="${req.contextPath}/applicationDocManager/editApplicationDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&yesUrl=yesUrl"/>
 <@ww.token name="saveContractStateToken"/>
    <@inputTable>
    	<#if contractState.id?exists>
            <@ww.hidden name="'contractState.id'" value="#{contractState.id}"/>
        </#if>
        
        <#if contractManagement?exists>
        <#if contractManagement.id?exists>
        	<@ww.hidden name="'contractManagement.id'" value="#{contractManagement.id}"/>
        	<#assign returnUrl=returnUrl + '&contractManagement.id=#{contractManagement.id}'/>
        </#if>
        </#if>
        <#--
		<tr>
		    <td align="right" valign="top">
	    		<label class="label"><font color=red>*</font>${action.getText('上传文件')}:</label>
	    	</td>
			<td colspan="8">
				<input type="file" name="file" size="97" onchange="getName();"/>
			</td>
		</tr>
		
		<tr>
			<#if contractState.id?exists>
				<@ww.textfield label="'${action.getText('资料名称')}'" name="'contractState.fileName'" value="'${contractState.fileName?if_exists}'" required="true" cssClass="'underline'"/>
			<#else>
				<@ww.textfield label="'${action.getText('资料名称')}'" name="'contractState.fileName'" value="''" required="true" cssClass="'underline'"/>
			</#if>
			<@ww.textfield label="'${action.getText('上传人')}'" name="'contractState.creatorName'" value="'${contractState.creatorName?if_exists}'" cssClass="'underline'" readonly="true"/>
			
			<@pp.datePicker 
				label="'${action.getText('上传时间')}'" 
				name="'contractState.uploadDate'" 
	   			value="'${(contractState.uploadDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'" readonly="true"
				maxlength="10"/>
			<script language="javascript">
				<#if contractState.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("contractState.uploadDate").value==''){
						getObjByName("contractState.uploadDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
			</script>
    	</tr>
   	    <tr>
   	    -->
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
				<#if contractState.beforeState?exists>
					getObjByName("beforeStateid").value =${contractState.beforeState.id?if_exists};
				<#else>
					getObjByName("beforeStateid").value =${contractManagement.state.id?if_exists};
				</#if>
			</script>
	    	<@ww.select label="'${action.getText('变更后状态')}'" 
				id="newStateid"
				name="'newStateid'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<script language="javascript">
				<#if contractState.newState?exists>
					getObjByName("newStateid").value =${contractState.newState.id?if_exists};
				<#else>
					getObjByName("newStateid").value =${contractManagement.state.id?if_exists};
				</#if>
			</script>
	    	
   	    <tr>
	    	<td align="right" valign="top">
	    		<label class="label">${action.getText('变更说明')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="contractState.explain" rows="4" cols="150" >${contractState.explain?if_exists}</textarea>
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
	 	 
	function storeValidationOld(){
	 	  var filename = document.forms["contractState"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if contractState.new>
		    if (filename == '' || ary.length<=0) { 
		      alert("请选择文件");
		      document.forms["contractState"].elements["file"].focus();
			  return false;
		    }
		  <#else>
		  //如果不是新建，如果上传文件选择了，则提示是否覆盖原来的文件
		    if (filename != '' && ary.length>0) { 
		      if (!confirm("将会覆盖之前文件？")) {
		         return false;
		      }
		    }
		  </#if>
		 //验证手册名称
		  if (!validateManualDocName()) {
		  getObjByName('contractState.fileName').focus();
		    return false;
		  }
		  //验证手册描述
		  if (!validateDescription()) {
		    return false;
		  }
			document.forms["contractState"].elements["contractState.fileName"].value=ary[ary.length-1];
			getObjByName('beforeStateid').removeAttribute('disabled');
			return true;
   		}
   		function getName() {
		  getFileNameByFile(document.forms["contractState"],"contractState.fileName");
		}
   		//验证手册名称是否为空,以及长度
	    function validateManualDocName() {
		  if ('' == document.forms["contractState"].elements["contractState.fileName"].value) {
		     alert("${action.getText('文件名称不能为空')}");
		     return false;
		  } else if (!isValidLength(document.forms[0], "contractState.fileName", null, 150)) {
		    alert("${action.getText('文件名称不能为空')}");
		    return false;
		  }
		  return true;
		}
		//验证手册描述的长度
		function validateDescription() {
		  if ('' != document.forms["contractState"].elements["contractState.explain"].value) {
		    if (!isValidLength(document.forms[0], "contractState.explain", null, 250)) {
		      alert("${action.getText('请填写更改说明')}");
		      return false;
		    }
		  }
		  <#-- applicationDoc.uploadDate 上传时间-->
		  if('' != document.forms["contractState"].elements["contractState.uploadDate"].value){
			 	 if(!isDate('contractState.uploadDate')){
					 alert("${action.getText('applicationDoc.uploadDate.dateFormate.error')}");
					 return false;
				 } 
				 if(isDateLessThenCurrent(getObjByName('contractState.uploadDate').value)){
					 alert('${action.getText('applicationDoc.uploadDate.earlyError')}');
					 return false;
				 }
			}
		 	return true;
		}
</script>
</@ww.form>
</@htmlPage>
