<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('applicationDoc.profile')}">
<@ww.form namespace="'/applicationDocManager'" name="'applicationDocInfo'" action="'saveApplicationDoc'" enctype="'multipart/form-data'" method="'post'">
<#assign returnUrl='${req.contextPath}/applicationDocManager/editApplicationDoc.html?yesUrl=yesUrl'/>
 <@ww.token name="saveApplicationDocToken"/>
    <@inputTable>
    	<#if applicationDoc.id?exists>
            <@ww.hidden name="'applicationDoc.id'" value="#{applicationDoc.id}"/>
        </#if>
        <#if advisory?exists>
        <#if advisory.id?exists>
        	<@ww.hidden name="'advisory.id'" value="#{advisory.id}"/>
        	<#assign returnUrl=returnUrl + '&advisory.id=#{advisory.id}'/>
        </#if>
        </#if>
        <#if backVisit?exists>
        <#if backVisit.id?exists>
        	<@ww.hidden name="'backVisit.id'" value="#{backVisit.id}"/>
        	<#assign returnUrl=returnUrl + '&backVisit.id=#{backVisit.id}'/>
        </#if>
        </#if>
         <#if projectInfo?exists>
        <#if projectInfo.id?exists>
        	<@ww.hidden name="'projectInfo.id'" value="#{projectInfo.id}"/>
        	<#assign returnUrl=returnUrl + '&projectInfo.id=#{projectInfo.id}'/>
        </#if>
        </#if>
        <#if supplier?exists>
        <#if supplier.id?exists>
        	<@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        	<#assign returnUrl=returnUrl + '&supplier.id=#{supplier.id}'/>
        </#if>
        </#if>
         <#if contractAdministrator?exists>
        <#if contractAdministrator.id?exists>
        	<@ww.hidden name="'contractAdministrator.id'" value="#{contractAdministrator.id}"/>
        	<#assign returnUrl=returnUrl + '&contractAdministrator.id=#{contractAdministrator.id}'/>
        </#if>
        </#if>
		<tr>
		    <td align="right" valign="top">
	    		<label class="label"><font color=red>*</font>${action.getText('applicationDoc.resourseAdress')}:</label>
	    	</td>
			<td colspan="8">
				<input type="file" name="file" size="97" onchange="getName();"/>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('applicationDoc.name')}'" name="'applicationDoc.name'" value="'${applicationDoc.name?if_exists}'" required="true" cssClass="'underline'"/>
			<@ww.textfield label="'${action.getText('applicationDoc.creator')}'" name="'applicationDoc.creatorName'" value="'${applicationDoc.creatorName?if_exists}'" cssClass="'underline'" readonly="true"/>
			<@pp.datePicker 
				label="'${action.getText('applicationDoc.uploadDate')}'" 
				name="'applicationDoc.uploadDate'" 
	   			value="'${(applicationDoc.uploadDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'" readonly="true"
				maxlength="10"/>
			<script language="javascript">
				<#if applicationDoc.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("applicationDoc.uploadDate").value==''){
						getObjByName("applicationDoc.uploadDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
			</script>
    	</tr>
   	    <tr>
	    	<td align="right" valign="top">
	    		<label class="label">${action.getText('applicationDoc.description')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="applicationDoc.description" rows="3" cols="114" >${applicationDoc.description?if_exists}</textarea>
	        </td>
	   </tr>
    </@inputTable>
    <@buttonBar>
          <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
          
          <#-- 继续新建按钮   -->
			<#if applicationDoc.id?exists>
			<@redirectButton name="newUpload" value="${action.getText('newUpload')}" 
				url="${returnUrl}"/>
			<#else>
			<@redirectButton name="newUpload" value="${action.getText('newUpload')}" 
				url="${req.contextPath}/applicationDocManager/editApplicationDoc.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newUpload").disabled="true";
			</script>
			</#if>
          
          <@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="closeThis();"/>
    </@buttonBar>
    <@ww.hidden name="'origFileName'" value=""/>
<script language="javascript">
	function storeValidation(){
	 	  var filename = document.forms["applicationDocInfo"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if applicationDoc.new>
		    if (filename == '' || ary.length<=0) { 
		      alert("${action.getText('please.choose.file')}");
		      document.forms["applicationDocInfo"].elements["file"].focus();
			  return false;
		    }
		  <#else>
		  //如果不是新建，如果上传文件选择了，则提示是否覆盖原来的文件
		    if (filename != '' && ary.length>0) { 
		      if (!confirm("${action.getText('confirm.overwrite.old.file')}")) {
		         return false;
		      }
		    }
		  </#if>
		 //验证手册名称
		  if (!validateManualDocName()) {
		  getObjByName('applicationDoc.name').focus();
		    return false;
		  }
		  //验证手册描述
		  if (!validateDescription()) {
		    return false;
		  }
			document.forms["applicationDocInfo"].elements["origFileName"].value=ary[ary.length-1];
			return true;
   		}
   		function getName() {
		  getFileNameByFile(document.forms["applicationDocInfo"],"applicationDoc.name");
		}
   		//验证手册名称是否为空,以及长度
	    function validateManualDocName() {
		  if ('' == document.forms["applicationDocInfo"].elements["applicationDoc.name"].value) {
		     alert("${action.getText('applicationDoc.name.required')}");
		     return false;
		  } else if (!isValidLength(document.forms[0], "applicationDoc.name", null, 150)) {
		    alert("${action.getText('applicationDoc.name.length')}");
		    return false;
		  }
		  return true;
		}
		//验证手册描述的长度
		function validateDescription() {
		  if ('' != document.forms["applicationDocInfo"].elements["applicationDoc.description"].value) {
		    if (!isValidLength(document.forms[0], "applicationDoc.description", null, 250)) {
		      alert("${action.getText('applicationDoc.description.length')}");
		      return false;
		    }
		  }
		  <#-- applicationDoc.uploadDate 上传时间-->
		  if('' != document.forms["applicationDocInfo"].elements["applicationDoc.uploadDate"].value){
		 	 if(!isDate('applicationDoc.uploadDate')){
				 alert("${action.getText('applicationDoc.uploadDate.dateFormate.error')}");
				 return false;
			 } 
			 if(isDateLessThenCurrent(getObjByName('applicationDoc.uploadDate').value)){
				 alert('${action.getText('applicationDoc.uploadDate.earlyError')}');
				 return false;
			 }
		 }
		 return true;
	}
</script>
</@ww.form>
</@htmlPage>
