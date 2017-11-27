<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('customerNews.profile')}">
<@ww.form namespace="'/customerRelationship'" name="'customerNews'" action="'saveCustomerNews'" enctype="'multipart/form-data'" method="'post'">
<#assign returnUrl='${req.contextPath}/customerRelationship/editCustomerNews.html'/>
 <@ww.token name="saveCustomerNewsToken"/>
    <@inputTable>
       <#if customerInfoId ?exists>
	    		<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
	    		<#assign returnUrl=returnUrl + '?&customerInfo.id=#{customerInfoId}'/>
	    	<#else>
	    		<@ww.hidden name="'customerInfo.id'" value="''"/>
    	</#if>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<@ww.hidden name="'savePicName'" value="''"/>
    	<#if customerNews.id?exists>
            <@ww.hidden name="'customerNews.id'" value="#{customerNews.id}"/>
        </#if>
        
		
		<tr>
			<@ww.textfield label="'${action.getText('customerNews.title')}'" name="'customerNews.title'" value="'${customerNews.title?if_exists}'" required="true" cssClass="'underline'"/>
    	</tr>
    	<tr>
		    <td align="right" valign="top">
	    		<label class="label"><font color=red>*</font>${action.getText('customerNews.resourseAdress')}:</label>
	    	</td>
			<td colspan="8">
				<input type="file" name="file" size="60" onchange="getName();"/>
			</td>
		</tr>
		<tr>
		    <@ww.textfield label="'${action.getText('customerNews.pictureName')}'" name="'customerNews.pictureName'" value="'${customerNews.pictureName?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
   	    <tr>
	    	<td align="right" valign="top">
	    	    <span class="required">*</span>
	    		<label class="label">${action.getText('customerNews.content')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="customerNews.content" rows="4" >${customerNews.content?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("customerNews.content").cols =width;
			</script>
	   </tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
          <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
          <#-- 继续新建按钮   -->
			<#if customerNews.id?exists>
			<@redirectButton name="newUpload" value="${action.getText('newUpload')}" 
				url="${returnUrl}"/>
			<#else>
			<@redirectButton name="newUpload" value="${action.getText('newUpload')}" 
				url="${req.contextPath}/customerRelationship/editCustomerNews.html"/>
			<script language="JavaScript" type="text/JavaScript">
			getObjByName("newUpload").disabled="true";
			</script>
			</#if>
    </#if>
          
          <@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="closeThis();"/>
    </@buttonBar>
    <@ww.hidden name="'origFileName'" value=""/>
<script language="javascript">
	function storeValidation(){
	 	  var filename = document.forms["customerNews"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if customerNews.new>
		    if (filename == '' || ary.length<=0) { 
		      alert("${action.getText('please.choose.file')}");
		      document.forms["customerNews"].elements["file"].focus();
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
		   //验证图片名称
		  if (!validateManualDocName()) {
		    getObjByName('customerNews.pictureName').focus();
		    return false;
		  }
		 //验证标题
		 if(getObjByName('customerNews.title').value==''){
		   alert("${action.getText('please.input.title')}");
		   return false;
		  }
		  //验证内容
		  if(getObjByName('customerNews.content').value==''){
		   alert("${action.getText('please.input.content')}");
		   return false;
		  }
		  //验证手册描述
		  if (!validateDescription()) {
		    return false;
		  }
			document.forms["customerNews"].elements["origFileName"].value=ary[ary.length-1];
			return true;
   		}
   		function getName() {
		  getFileNameByFile(document.forms["customerNews"],"customerNews.pictureName");
		  var filename = document.forms["customerNews"].elements["file"].value;
		  if(filename!=''){
		    var filetype=filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
		    getObjByName('savePicName').value=filetype;
		    filetype=filetype+'|'
		    var strFilter=".jpeg|.gif|.jpg|.png|.bmp|.pic|.jps|"
	 	    if(strFilter.indexOf(filetype)>-1)
            {
                return true;
            }else{
             alert("${action.getText('isNotApicture')}");
             document.forms["customerNews"].elements["file"].value='';
             return false;
            }
	 	  }
		}
   		//验证手册名称是否为空,以及长度
	    function validateManualDocName() {
		  if ('' == document.forms["customerNews"].elements["customerNews.pictureName"].value) {
		     alert("${action.getText('customerNews.pictureName.required')}");
		     return false;
		  } else if (!isValidLength(document.forms[0], "customerNews.pictureName", null, 50)) {
		    alert("${action.getText('customerNews.pictureName.length')}");
		    return false;
		  }
		  return true;
	}
	
</script>
</@ww.form>
</@htmlPage>
