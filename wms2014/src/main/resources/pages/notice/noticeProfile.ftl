<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../includes/eam2008.ftl" />
<#--
<#include "../includes/macros.ftl" />
-->
<@htmlPage title="${action.getText('noticeProfile.title')}">
    <@ww.form namespace="'/notice'" name="'notice'" action="'saveNotice'" enctype="'multipart/form-data'" method="'post'">
        <@ww.token name="saveUnusedToken"/>
         <@ww.hidden name="'availableUserIds'" value=""/>
         <@ww.hidden name="'loginUserIds'" value=""/>
        <@inputTable>
        <#if loginUser?exists>
          <#if loginUser.id?exists>
             <input type="hidden" name="loginUser.id" value="#{loginUser.id}" />
          </#if>
        </#if>
        <#if sendNotice.id?exists>
            <@ww.hidden name="'sendNotice.id'" value="#{sendNotice.id}"/>
        </#if>
         <@ww.hidden name="'sendFlag'" value="'${req.getParameter('sendFlag')?if_exists}'"/>
        <tr>
		<#--
		    <@ww.textfield label="'${action.getText('notice.sender')}'" size="50" name="'receviceUser'" value="''" required="false" onchange="'changeValue();'"/>
	-->
		    <#--<td align="right" valign="top"><span class="required">*</span><label class="label">接收人:</label></td>
	 		<td>
	 			<input type="text" name="receviceUser" class="underline"  value="" size="50" onchange="changeValue();"/>
				<a href="javascript:user_OpenDialog();">
		          <img title="选择用户" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left : 5px;" />
		        </a>
	 		</td>
		</tr>	-->
		 <tr>
		   <@ww.textfield label="'${action.getText('noticeTitle')}'" name="'sendNotice.title'" value="'${sendNotice.title?if_exists}'" size="'100'" maxlength="'50'" cssClass="'underline'"  required="true"/>
		</tr>
		<tr>
		<#--
		    <@ww.textarea  label="'${action.getText('conent')}'" 
	        	         name="'sendNotice.content'" 
	        	         value="'${sendNotice.content?if_exists}'"  
	        	         rows="7" cols="100" cssClass="'underline'" wrap="hard"/>
	    	-->
	       <td align="right" valign="top">
	         <label class="label">${action.getText('conent')}:</label>
	       </td>
           <td>
             <textarea name="sendNotice.content"
                       cols="100"
                       rows="10"
                       wrap="hard"
                       id="saveNotice_sendNotice.content">${sendNotice.content?if_exists}</textarea>
           </td>
		</tr>
		<tr>
	      <@ww.file label="'${action.getText('toolingChangeDoc.upload')}'" size="100" name="'file'" cssClass="'button'" onchange="'return getName();'"required="false"/>
	    </tr>
		<tr>
	      <@ww.textfield label="'${action.getText('toolingChangeDoc.name')}'" size="100" name="'sendNotice.name'" value="'${sendNotice.name?if_exists}'" cssClass="'underline'" required="false"/>
	    </tr>
	     
	     <tr>
	     <td>
	     </td>
	     <td style="VERTICAL-ALIGN:top;text-align:left">
	     <#if (sendNotice.users.size()>0)>
	     <select name="availableUser.id" multiple size="10" style="display:none">
	      <#list sendNotice.users as sendUser>
                    <option value="#{sendUser.id}">${sendUser.name}</option>
          </#list>   
         </select>
         <#else>
            <select name="availableUser.id" multiple size="5" style="display:none"> 
           </select>
	     </#if>
	      <tr>
		  <@pp.datePicker label="'${action.getText('durationDate')}'" name="'sendNotice.validityDate'"
	     				  value="'${(sendNotice.validityDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
        </tr>
        </@inputTable>
        <@ww.hidden name="'origFileName'" value=""/>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick= "'return selectAvailableUser();'"/>
            <@vsubmit name="'send'" value="'${action.getText('send')}'" onclick= "'return selectAvailableUser();'"/>
            <#if sendNotice.id?exists>
              <@vsubmit name="'cancel'" value="'${action.getText('取消发送')}'"/>
            </#if>
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/notice/listNotice.html"/>
            <#-- <@htmlButton name="submit"  style="width:90px;" value="取消发送" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return cancelSend();"/>-->
            <#-- <@htmlButton name="cancel"  style="width:90px;" value="取消发送" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'" onclick="return cancelSend();"/>-->
        </@buttonBar>
    </@ww.form>
</@htmlPage>
<script type="text/javascript">
window.onload = function(){
  <#if sendNotice.id?exists>
    <#if '${(sendNotice.sendStatus?string)?if_exists}' == 'SENDED'>
      document.forms[0].elements["send"].disabled="true";
      document.forms[0].elements["save"].disabled="true";
    <#else>
      document.forms[0].elements["cancel"].disabled="true";
    </#if>
  </#if>
}
<#--
<#if sendFlag?exists>
   <#if '${sendFlag}' == 'send'>
     document.forms[0].elements["send"].disabled="true";
   </#if>
   </#if>
}
-->
<#--
<#if cancelFlag?exists>
     <#if '${cancelFlag}' == 'cancel'>
      alert("${action.getText('notice.cancel.success')}");
       document.forms[0].elements["cancel"].disabled="true";
     </#if>
   </#if>
   -->
   <#--
   <#if saveOrSendFlag?exists>
     <#if '${saveOrSendFlag}' == 'send'>
        alert("${action.getText('notice.send.success')}");
          document.forms[0].elements["send"].disabled="true";
          -->
       	<#--var reloadUrl = '${req.contextPath}/notice/listNotice.html';
	    self.location.href = reloadUrl;-->
	    <#--
     </#if>
   </#if>
   -->
       <#--function cancelSend(){
		  var url = '${req.contextPath}/notice/cancelSendNotice.html?sendNotice.id=#{sendNotice.id}';
		  self.location.href = url;
		  }-->
    
  <#--window.onload = function() {
   /*
    * 获取隐藏select的用户信息
   */
    var selector = document.all("availableUser.id");
    groups = selector.options.length;
    var ary = new Array();
    for (var i=0; i<groups; i++) {
      ary.push(selector.options[i].text);
    }
    //给接收人控件
    var receviceUserObj = document.forms["notice"].elements["receviceUser"];
    receviceUserObj.value = ary;
    receviceUserObj.setAttribute("maxLength",receviceUserObj.value.length);
  }-->
  function getName() {
    var filename = document.forms["notice"].elements["file"].value;
    var ary = filename.split("\\");
    var ary1 = ary[ary.length-1].split("\.");
    document.forms["notice"].elements["sendNotice.name"].value=ary1[0];
	document.forms["notice"].elements["origFileName"].value=ary[ary.length-1];
	return true;
  }
 <#-- function user_OpenDialog() {
    var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T";
	popupModalDialog(url, 800, 600, userSelectorHandler);
  }
  function userSelectorHandler() {
    if (null != result) {
      user = result.substring(0, result.lastIndexOf(","));
      var userArray = user.split(",");
      var obj = getObjByNameRe("availableUser.id");
      for (var i=0; i<userArray.length; i=i+2) {
        var opt = new Option(userArray[i+1], userArray[i]);
	    eval("obj.options[obj.options.length]=opt");
      }
      var userName = new Array();
      var selector = document.all("availableUser.id");
      groups = selector.options.length;
      for (var i=0; i<groups; i++) {
        userName.push(selector.options[i].text);
      }
      var receviceUserObj = document.forms["notice"].elements["receviceUser"];
      receviceUserObj.value = userName;
      receviceUserObj.setAttribute("maxLength",receviceUserObj.value.length);
    }
  } -->
  function selectAvailableUser() {
   <#---   var ary = new Array();
      var selector = document.all("availableUser.id");
      groups = selector.options.length;
      for (var i=0; i<groups; i++) {
          ary.push(selector.options[i].value);
      }
      document.forms["notice"].elements["availableUserIds"].value = ary;
     // document.forms["notice"].elements["loginUserIds"].value = ary;
      if(getObjByNameRe("receviceUser").value==''){
        alert('${action.getText('send.not.null')}');
	    return false;
      }-->
      if(getObjByNameRe("sendNotice.title").value==''){
         alert('${action.getText('sendNotice.title.not.null')}');
	     return false;
      } else if(!isValidLength(document.forms["notice"], "sendNotice.title", null, 50)) {
         alert("${action.getText('sendNotice.title.maxLength')}");
         return false;
      }
      if (getObjByNameRe("sendNotice.content").value!="") {
        if(!isValidLength(document.forms["notice"], "sendNotice.content", null, 500)) {
         alert("${action.getText('sendNotice.content.maxLength')}");
         return false;
        }
      }
       if(getObjByNameRe("sendNotice.validityDate").value==''){
	        alert('${action.getText('sendNotice.validityDate.not.null')}');
	        return false;
	      }
	    var date=getObjByNameRe("sendNotice.validityDate").value;
		if(!isDate("sendNotice.validityDate")){
		    alert("${action.getText('select.right.sendNotice.validityDate')}");
			return false;
		  }
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.validityDate')}");
		    return false;
		  }
      return true;
      
  }
 <#-- function changeValue() {
    var selector = document.all("availableUser.id");
    groups = selector.options.length;
    var receviceUserObj = document.forms["notice"].elements["receviceUser"];
    var receviceUserValue = receviceUserObj.value;
    if ('' != receviceUserValue) {
      receviceUserValue = receviceUserValue.split(",");
    }
   
    for (var i=groups-1; i>=0; i--) {
      if (receviceUserValue.length > i) {
        if (receviceUserValue[i] != selector.options[i].text) {
          selector.options.remove(i);
        }
      }
      if (receviceUserValue.length <= i) {
        selector.options.remove(i);
      }
    }
    receviceUserObj.setAttribute("maxLength",receviceUserObj.value.length);
  }-->
</script>      