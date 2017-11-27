<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('customerProduct.profile')}">
<@ww.form namespace="'/customerRelationship'" name="'customerProduct'" action="'saveCustomerProduct'" enctype="'multipart/form-data'" method="'post'">
<#assign returnUrl='${req.contextPath}/customerRelationship/editCustomerProduct.html'/>
 <@ww.token name="saveCustomerProductToken"/>
    <@inputTable>
       <#if customerInfoId ?exists>
	    		<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
	    		<#assign returnUrl=returnUrl + '?&customerInfo.id=#{customerInfoId}'/>
	    	<#else>
	    		<@ww.hidden name="'customerInfo.id'" value="''"/>
    	</#if>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<@ww.hidden name="'savePicName'" value="''"/>
    	<#if customerProduct.id?exists>
            <@ww.hidden name="'customerProduct.id'" value="#{customerProduct.id}"/>
        </#if>
        
		<tr>
		    <td align="right" valign="top">
	    		<label class="label"><font color=red>*</font>${action.getText('customerProduct.resourseAdress')}:</label>
	    	</td>
			<td colspan="8">
				<input type="file" name="file" size="97" onchange="getName();"/>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('customerProduct.productName')}'" name="'customerProduct.productName'" value="'${customerProduct.productName?if_exists}'" required="true" cssClass="'underline'"/>
			<@ww.textfield label="'${action.getText('customerProduct.pictureName')}'" name="'customerProduct.pictureName'" value="'${customerProduct.pictureName?if_exists}'" cssClass="'underline'" required="true" />
    	</tr>
   	    <tr>
	    	<td align="right" valign="top">
	    		<label class="label">${action.getText('customerProduct.description')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="customerProduct.productDescribe" rows="4"  >${customerProduct.productDescribe?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("customerProduct.productDescribe").cols =width;
			</script>
	   </tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
          <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
          <#-- 继续新建按钮   -->
			<#if customerProduct.id?exists>
			<@redirectButton name="newUpload" value="${action.getText('newUpload')}" 
				url="${returnUrl}"/>
			<#else>
			<@redirectButton name="newUpload" value="${action.getText('newUpload')}" 
				url="${req.contextPath}/customerRelationship/editCustomerProduct.html"/>
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
	 	  var filename = document.forms["customerProduct"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if customerProduct.new>
		    if (filename == '' || ary.length<=0) { 
		      alert("${action.getText('please.choose.file')}");
		      document.forms["customerProduct"].elements["file"].focus();
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
		 //验证产品名称
		 if(getObjByName('customerProduct.productName').value==''){
		   alert("${action.getText('please.input.productName')}");
		   return false;
		  }
		  //验证图片名称
		  if (!validateManualDocName()) {
		  getObjByName('customerProduct.pictureName').focus();
		    return false;
		  }
		  //验证手册描述
		  if (!validateDescription()) {
		    return false;
		  }
			document.forms["customerProduct"].elements["origFileName"].value=ary[ary.length-1];
			return true;
   		}
   		function getName() {
		  getFileNameByFile(document.forms["customerProduct"],"customerProduct.pictureName");
		  var filename = document.forms["customerProduct"].elements["file"].value;
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
             document.forms["customerProduct"].elements["file"].value='';
             return false;
            }
	 	  }
		}
   		//验证手册名称是否为空,以及长度
	    function validateManualDocName() {
		  if ('' == document.forms["customerProduct"].elements["customerProduct.pictureName"].value) {
		     alert("${action.getText('customerProduct.pictureName.required')}");
		     return false;
		  } else if (!isValidLength(document.forms[0], "customerProduct.pictureName", null, 50)) {
		    alert("${action.getText('customerProduct.pictureName.length')}");
		    return false;
		  }
		  return true;
	}
	
</script>
</@ww.form>
</@htmlPage>
