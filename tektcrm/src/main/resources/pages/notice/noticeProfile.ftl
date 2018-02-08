<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../includes/hco2011.ftl" />
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
            <td style="VERTICAL-ALIGN:top;text-align:left" colspan="4">
            	<label class="label">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp收件人:</label>
				<select name="availableUser.id" multiple size="6" style="width:450px"></select>
				 <#if sendNotice.users?exists>
				 <script>
					var obj = getObjByName("availableUser.id");
					var users = eval("${usersJson?if_exists}");
		            for (var i=0; i<users.length; i++) {
		            	var opt = new Option(users[i].name, users[i].id);
				    	eval("obj.options[obj.options.length]=opt");
		            }
				 </script>
				 </#if>
			</td>
		  </tr>
     	<tr>
     		<td style="text-align:left" colspan="4">
      			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
      			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
      			<@htmlButton name="submit"  style="width:90px;" value="从用户选择" onclick="user_OpenDialog();"/>
           		<@htmlButton name="submit"  style="width:90px;" value="从部门选择" onclick="return selectChooseuser();"/>
           		<@htmlButton name="submit"  style="width:90px;" value="从组选择" onclick="return selectchooseGroup();"/>
           		<@htmlButton name="submit"  style="width:80px;" value="删除收件人" onclick="return Delete();"/>
           		<@htmlButton name="submit"  style="width:50px;" value="重置" onclick="return Reset();"/>
			</td>
		</tr>
			<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#C7EBBD" noshade></td></tr>
        <tr>
		 <tr>
		   <@ww.textfield label="'${action.getText('noticeTitle')}'" name="'sendNotice.title'" value="'${sendNotice.title?if_exists}'" size="'50'" maxlength="'50'" cssClass="'underline'"  required="true"/>
		</tr>
		<tr>
	       <td align="right" valign="top">
	         <label class="label"><font color="red">*</font>${action.getText('conent')}:</label>
	       </td>
           <td colspan="3">
             <textarea name="sendNotice.content"
                      
                       rows="10"
                       wrap="hard"
                       id="saveNotice_sendNotice.content">${sendNotice.content?if_exists}</textarea>
           </td>
           <script language="javascript">
			 var width=document.body.clientWidth/9;
						getObjByName("sendNotice.content").cols =width;
		   </script>
		</tr>
		<tr>
	      <td align="right">上传:</td>
            <td colspan="3">
               <input type="file" name="file" size="50"  required="true"  onchange="return getName();"/>
            </td>
	    </tr>
		<tr>
	      <@ww.textfield label="'${action.getText('toolingChangeDoc.name')}'" size="50" name="'sendNotice.name'" value="'${sendNotice.name?if_exists}'" cssClass="'underline'" required="false"/>
	    </tr>
	     
	     <tr>
	     <td>
	     </td>
		<td style="VERTICAL-ALIGN:top;text-align:left" colspan="3">
			<#if (sendNotice.users.size()>0)>
				<select name="availableUser.id" multiple size="10" style="display:none">
					<#list sendNotice.users as sendUser>
						<option value="#{sendUser.id}">${sendUser.name}</option>
					</#list>   
				</select>
			<#else>
				<select name="availableUser.id" multiple size="5" style="display:none"></select>
			</#if>
		</td>
	      </tr>
	      <tr>
	      <@select 
        		label="${action.getText('sendNotice.noticeType')}" 
				name="noticeType.id" 
				value="${req.getParameter('noticeType.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allNoticeTypes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@select>
		  <@pp.datePicker label="'${action.getText('durationDate')}'" name="'sendNotice.validityDate'"
	     				  value="'${(sendNotice.validityDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
	     	
        </tr>
        </@inputTable>
        <@ww.hidden name="'origFileName'" value=""/>
        <@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick= "'return selectAvailableUser();'"/>
            <@vsubmit name="'send'" value="'${action.getText('send')}'" onclick= "'return selectAvailableUser();'"/>
            <#if sendNotice.id?exists>
              <@vsubmit name="'cancel'" value="'${action.getText('取消发送')}'"/>
            </#if>
        </#if>
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/notice/listNotice.html"/>
        </@buttonBar>
    </@ww.form>
</@htmlPage>
<script type="text/javascript">
	<#if sendNotice.noticeType?exists>
 		getObjByName('noticeType.id').value = ${sendNotice.noticeType.id};
 	</#if>
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

  function getName() {
    var filename = document.forms["notice"].elements["file"].value;
    var ary = filename.split("\\");
    var ary1 = ary[ary.length-1].split("\.");
    document.forms["notice"].elements["sendNotice.name"].value=ary1[0];
	document.forms["notice"].elements["origFileName"].value=ary[ary.length-1];
		
   return true;
  }

  function selectAvailableUser() {
  	  	var ary = new Array();
      	var selector = getObjByName("availableUser.id");
      	groups = selector.options.length;
      	if(groups ==0){
      		alert("请选择要发送的对象！");
      		getObjByName('availableUser.id').focus();
      		return false;
      	}
      	for (var i=0; i<groups; i++) {
          	ary.push(selector.options[i].value);
      	}
     	document.forms["notice"].elements["availableUserIds"].value = ary;
     	
      if(getObjByName("sendNotice.title").value==''){
         alert('${action.getText('sendNotice.title.not.null')}');
         getObjByName('sendNotice.title').focus();
	     return false;
      } else if(!isValidLength(document.forms["notice"], "sendNotice.title", null, 50)) {
         alert("${action.getText('sendNotice.title.maxLength')}");
         getObjByName('sendNotice.title').focus();
         return false;
      }
      if (getObjByName("sendNotice.content").value=="") {
	       alert("请输入通知内容");
	       getObjByName('sendNotice.content').focus();
	       return false;
      }else{
      	 if(!isValidLength(document.forms["notice"], "sendNotice.content", null, 500)) {
	         alert("${action.getText('sendNotice.content.maxLength')}");
	         return false;
        }
      }
      if(getObjByName('noticeType.id').value == ''){
          alert('请输入通知类型');
          getObjByName('noticeType.id').focus();
          return false
      }
       if(getObjByName("sendNotice.validityDate").value==''){
	        alert('${action.getText('sendNotice.validityDate.not.null')}');
	        getObjByName('sendNotice.validityDate').focus();
	        return false;
	      }
      return true;
      
  }
  //从用户选择获得用户名和手机号码
	function selectChooseuser(){
		var url = "${req.contextPath}/userSelector/userBytelphoneSelector.html?readOnly=${req.getParameter('readOnly')?if_exists}";
        popupModalDialog(url, 800, 600, choose_user_information);
        return false;
	}   
    function choose_user_information(reslut){
		if (null != result) {
			//result = result.substring(0, result.lastIndexOf(","));
			var userArray = result.split(",");
			alert(reslut);
       		var obj = getObjByName("availableUser.id");
       		for (var i=0; i<userArray.length; i++) {
         		var temp = userArray[i].split(":");
         		filterRepeatTel(obj,temp[0]);   //过滤重复的部门或用户
         		var opt = new Option(temp[1],temp[0]);
		 		eval("obj.options[obj.options.length]=opt");
       		}
		}
	}
	
	//从用户组选择获得手机号码和用户名
  	function selectchooseGroup(){
		var url = "${req.contextPath}/groupSelector/groupSelector.html?readOnly=${req.getParameter('readOnly')?if_exists}";
        popupModalDialog(url, 800, 600, choose_group_information);
        return false;
  	}
	function choose_group_information(result){
     	if (null != result) {
       		var groupsArray = result.split(",");
       		var obj = getObjByName("availableUser.id");
       		for (var i=0; i<groupsArray.length; i++) {
         		var temp = groupsArray[i].split(":");
         		filterRepeatTel(obj,temp[0]);   //过滤重复的用户组或用户
         		var opt = new Option(temp[1],temp[0]);
		 		eval("obj.options[obj.options.length]=opt");
       		}
		}
	}
	//过滤重复的手机号码
  	function filterRepeatTel(selectObj, tel) {
    	var length = selectObj.options.length;
    	for (var i=0; i<length; i++) {
      		if (selectObj.options[i].value == tel) {
        		selectObj.remove(i);
        		break;
      		}
    	}
	} 
	
	//删除select中指定的某一项     
   	function Delete() {
     	var selector = getObjByName("availableUser.id"); 
     	//定义一个标记，表示是否有选中
     	var flag = false; 
     	if(selector.options.length<=0){
       		alert('没有要删除的收件人');
       		return false;
     	}
     	var length = selector.options.length;
     	for(var i=length-1;i>=0;i--){
          	if (selector.options[i].selected){
          	 	flag = true;
             	selector.remove(i);
           	//  return false;
            //  selector.selectedIndex = selector.lentht-1;
          	}
          	if(i<=0 && flag == false){
               	alert('请选择要删除的收件人');
               	return false;
          	}
     	}
     	return false;
   	}  
   	//删除select中所有值
  	function Reset(){
     	var selector = getObjByName("availableUser.id"); 
     	var length = selector.length;
     	if (length>0 && !confirm("确认删除所有收件人?")) {
       		return false;
     	}
     	for(var i=length-1;i>=0;i--){
			selector.remove(selector);
			selector.selectedIndex = selector.lentht-1;
     	}
     	return false;
  	} 
  	 //用户的选择

        function user_OpenDialog() {
          var url = "${req.contextPath}/popup/userSelector.html?readOnly=${req.getParameter('readOnly')?if_exists}&multipleSelect=T";
	      popupModalDialog(url, 800, 600, userSelectorHandler);
        }
        
        function userSelectorHandler(result) {
          if (null != result) {
            user = result.substring(0, result.lastIndexOf(","));
            var userArray = user.split(",");
            var obj = getObjByName("availableUser.id");
            for (var i=0; i<userArray.length; i=i+2) {
              var opt = new Option(userArray[i+1], userArray[i]);
		      eval("obj.options[obj.options.length]=opt");
            }
	      }
	    }
	    
</script>      