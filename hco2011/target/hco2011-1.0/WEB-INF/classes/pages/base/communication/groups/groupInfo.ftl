<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
   shall not disclose such Confidential Information and shall use it only in
   accordance with the terms of the license agreement you entered into with
   YongJun.
   
   YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
   SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
   NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
   LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
   DERIVATIVES.
 -->
<#--$Id: groupInfo.ftl 6148 2007-07-31 01:41:34Z qsun $ -->
<#include "../../../includes/macros.ftl" />

<@htmlPage >
 <@ww.form namespace="'/communication/groups'" name="'group'" action="'saveGroup'" method="'post'" validate="true">
 <@ww.token name="saveGroupToken"/>
    <@inputTable>
        <#if group.id?exists>
            <@ww.hidden name="'group.id'" value="#{group.id}"/>
            <@ww.hidden name="'group.version'" value="#{group.version}"/>
        </#if>
        <@ww.hidden name="'availableRoleCodes'" value=""/>
        <@ww.hidden name="'grantedRoleCodes'" value=""/>
        <@ww.hidden name="'availableUserIds'" value=""/>
        <@ww.hidden name="'grantedUserIds'" value=""/>
        <@ww.hidden name="'join'" value=""/>
        <@ww.hidden name="'moveUserName'" value=""/>
        <tr>
            <@ww.textfield label="'${action.getText('group.code')}'"  name="'group.code'" value="'${group.code?if_exists}'" <#--onblur="'checkCode(this.value)'"-->
            	cssClass="'underline'" required="true" />
            <@ww.textfield label="'${action.getText('group.name')}'"  name="'group.name'" value="'${group.name?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
        <tr>
            <@ww.select label="'${action.getText('parent.group')}'" required="false" name="'parent.group'"  listKey="id" listValue="name"
                  list="parentGroups" emptyOption="true" disabled="false" required="true">                  
      		</@ww.select>
      		  <script language="javascript">
      			<#if group.parentGroup?exists>
      				document.forms[0].elements["parent.group"].value = #{group.parentGroup.id};
      		    <#elseif !group.new>
      		        document.forms[0].elements["parent.group"].value = '0';
      			</#if>
      		  </script>
        </tr>
    </@inputTable>
     <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('group')}[${group.name?if_exists}]?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${(!group.id?exists)?string}"/>	
            </@vsubmit>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/communication/groups/listGroups.html"/>
      </@buttonBar>

     <#if !group.new>
       <tr class="input">
         <td>
           <table style="text-align: center;width: 100%;">
             <tr>
                <td class="title" width="100%">
                    用户的加入与离开
                </td>
              </tr>
           </table>
         </td>
       </tr>
        <tr class="input">
          <td>
            <table class="defaultLook" width="100%">
              <tr width="100%">
                <td style="text-align:center" width="100%"><b>已授予的用户</b></td>
              </tr>
              <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center;position:relative;top:0px;right:0px" width="100%">
                  
                  <select name="grantedUser.id" multiple size="20" style="width:100px">
                     <#list disUsers as user>
                      <option value="#{user.id}">${user.name}</option>
                    </#list>
                  </select>
                  <a href="javascript:user_OpenDialog();" style="position:absolute;top:130px;right:280px">
                    <img title="选择用户" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left:5px;" />
                  </a>
                  <@vsubmit name="'leave'" value="'${action.getText('离开->')}'" onclick="'return selectGrantedUser();'"/>
                 <#--
                 <@htmlButton name="submit0"  style="position:absolute;top:180px;right:242px" value="开始排序" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return keyDown();"/>
                  <@htmlButton name="submit01"  style="position:absolute;top:180px;right:242px; display=none" value="结束排序" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return finish();"/>
                 <@htmlButton name="submit1"  style="position:absolute;top:205px;right:250px;display=none" value="上移↑" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return Moveup();"/>
                  <@htmlButton name="submit2"  style="position:absolute;top:230px;right:250px;display=none" value="下移↓" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return Movedown();"/>
                -->
                </td>
              </tr>  
            </table>
          </td>
        </tr>
        <#list group.users as user>
          <input type="hidden" name="filterUserIds" value="#{user.id}"/>
        </#list>
    </#if>
 </@ww.form>
     <script type="text/javascript">
      var xmlhttpreq = false;
      window.onload = function() {
        <#if group.privilegeGroup?exists>
        <#if group.privilegeGroup==true>
          document.forms["group"].elements["privilegeGroup"].checked=true;
          </#if>
          </#if>
     }
        //document.forms["group"].elements["group.code"].focus();
        //点击"授予"按钮，触发。
        function selectAvailableRole() {
           var ary = new Array();
           var selector = document.all("availableRole.code");
           groups = selector.options.length;
           for (var i=0; i<groups; i++) {
             if (selector.options[i].selected) {
               ary.push(selector.options[i].value);
             }
           }
           document.forms["group"].elements["availableRoleCodes"].value = ary;
           if ('' != document.forms["group"].elements["availableRoleCodes"].value) {
              return true;   
            }
            return false;
        }
        //点击"剥夺"按钮，触发
        function selectGrantedRole() {
          var ary = new Array();
          var selector = document.all("grantedRole.code");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected) {
               ary.push(selector.options[i].value);
            }
          }
          document.forms["group"].elements["grantedRoleCodes"].value = ary;
          if ('' != document.forms["group"].elements["grantedRoleCodes"].value) {
            return true;   
          }
          return false;
        }
        //点击"加入"按钮，触发
        function selectAvailableUser() {
          var ary = new Array();
          var selector = document.all("availableUser.id");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected) {
              ary.push(selector.options[i].value);
            }
          }
          document.forms["group"].elements["availableUserIds"].value = ary;
          if ('' != document.forms["group"].elements["availableUserIds"].value) {
            return true;   
          }
          return false;
        }
        //点击"离开"按钮，触发
        function selectGrantedUser() {
          var ary = new Array();
          var selector = document.all("grantedUser.id");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected) {
               ary.push(selector.options[i].value);
            }
          }
          document.forms["group"].elements["grantedUserIds"].value = ary;
          if ('' != document.forms["group"].elements["grantedUserIds"].value) {
            return true;   
          }
          return false;
        }
        //用户的选择
        function user_OpenDialog() {
          var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T";
          var filterUserIds = document.getElementsByName("filterUserIds");
	      var ary = new Array();
	      for (var i=0; i<filterUserIds.length; i++) {
	        ary.push(filterUserIds[i].value);
	      }
	      if (null != ary) {
	        url = url + '&filterUserIds=' + ary;
	      }
	      popupModalDialog(url, 800, 600, userSelectorHandler);
        }
        function userSelectorHandler(result) {
          if (null != result) {
            result = result.substring(0, result.lastIndexOf(","));
            user = result.split(",");
            var ary = new Array();
            for (var i=0; i<user.length; i=i+2) {
              ary.push(user[i]);
            }
            document.forms["group"].elements["availableUserIds"].value = ary;
            document.forms[0].elements["join"].value = "join";
            document.forms[0].submit();
            <#--
            var userArray = user.split(",");
            var obj = document.getElementById("availableUser.id");
            for (var i=0; i<userArray.length; i=i+2) {
              var opt = new Option(userArray[i+1], userArray[i]);
		      eval("obj.options[obj.options.length]=opt");
            }
            -->
	      }
        }
        function validate() {
		  if ('' != document.forms["group"].elements["group.code"].value) { 
		    if (!isValidLengthValue(document.forms["group"].elements["group.code"].value,0,50)) {
		      alert("${action.getText('group.code.maxLength')}");
		      getObjByName('group.code').value="";
		      getObjByName('group.code').focus();
		      return false;
		    }
		  }else {
		  	alert("${action.getText('group.code.requiredstring')}");
		  	getObjByName('group.code').focus();
		  	return false;
		  }
		  
		  if ('' != document.forms["group"].elements["group.name"].value) {     
		    if (!isValidLengthValue(document.forms["group"].elements["group.name"].value,0,50)) {
		      alert("${action.getText('group.name.maxLength')}");
		       getObjByName('group.name').value="";
		       getObjByName('group.name').focus();
		      return false;
		    }
		  }else {
		  	alert("${action.getText('group.name.requiredstring')}");
		  	getObjByName('group.name').focus();
		  	return false;
		  }
		  if ('' == document.forms["group"].elements["parent.group"].value) {
		    alert("${action.getText('parent.group.requiredstring')}");
		    getObjByName('parent.group').focus();
		  	return false;
		  }
		  return true;
		}
		 function checkCode(code){
		 	if(code != ''){
		 		createxmlhttprequest();
		 		var url = "${req.contextPath}/security/checkGroupCode.html?code="+code;
		 		xmlhttpreq.open("get",url,true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
		 	}
		 }
		 function createxmlhttprequest(){
		 	if (window.XMLHttpRequest) {
		 		xmlhttpreq = new XMLHttpRequest();
		 	}else if (window.ActiveXObject) {
		 		try {
		 			xmlhttpreq = new ActiveXObject("Msxml2.XMLHTTP");
		 		}catch (e) {
		 			try {
		 				xmlhttpreq = new ActiveXObject("microsoft.XMLHTTP");
		 			}catch (e) {}
		 		}
		 	}
		 }
		 function processresponse () {
		 	if (xmlhttpreq.readystate == 4) {
		 		if (xmlhttpreq.status == 200){
		 			var res = xmlhttpreq.responseXML.getElementsByTagName("res")[0].firstChild.data;
		 			if (res == 'repeat'){
		 				document.forms["group"].elements["group.code"].focus();
		 				alert("${action.getText('group.code.repeat')}");
		 			}
		 		}else {
		 			window.alert("页面请求有异常")
		 		}
		 	}
		 }
		 <#--
     //上移触发的js
     function Moveup(){
         var ary = new Array();
         var listObj = document.all("grantedUser.id");
           //遍历列表项
           for(var i=0;i<listObj.options.length; i++){
            if(listObj.options[i].selected && listObj.options[i] != ""&& listObj.options[i] == listObj.options[0]){
                  alert('已移至顶部');
                }
              if(listObj.options[i].selected && listObj.options[i] != ""&& listObj.options[i] != listObj.options[0]){ 
                 var tmpval1 = listObj.options[i].value;                    //获取当前项的值   
                 var tmpval12 = listObj.options[i].text;                    //获取当前项的文本   
                 listObj.options[i].value = listObj.options[i-1].value;   //获取上一项的值   
                 listObj.options[i].text = listObj.options[i-1].text      //获取上一项的文本   
                 listObj.options[i-1].value = tmpval1;                      //实现上下值的互换   
                 listObj.options[i-1].text = tmpval12;                      //实现上下文本的互换   
                 listObj.options[i].selected=false;   
                 listObj.options[i-1].selected=true; 
                 var groupid=document.forms["group"].elements["group.id"].value;
                 upMoveAjax(listObj.options[i].value,listObj.options[i-1].value,groupid);
                 break;  
              }
           }
        }	
     //下移触发的js    
     function Movedown(){
         var listObj = document.all("grantedUser.id");
        //遍历列表项
        for(var i = 0; i < listObj.options.length; i++) {   
       if (listObj.options[i].selected && listObj.options[i] != "" && listObj.options[i+1] == listObj.options[listObj.options.length]){
          alert('已移至尾部');
        }
         if (listObj.options[i].selected && listObj.options[i] != "" && listObj.options[i+1] != listObj.options[listObj.options.length]) {   
            var tmpval1 = listObj.options[i].value;                 //获取当前项的值   
            var tmpval12 = listObj.options[i].text;                 //获取当前项的文本   
            listObj.options[i].value = listObj.options[i+1].value; //获取下一项的值   
            listObj.options[i].text = listObj.options[i+1].text     //获取下一项的文本   
            listObj.options[i+1].value = tmpval1;               //实现上下值的互换   
            listObj.options[i+1].text = tmpval12;               //实现上下文本的互换   
            listObj.options[i].selected=false;   
            listObj.options[i+1].selected=true; 
            var groupid=document.forms["group"].elements["group.id"].value;
            downMoveAjax(listObj.options[i].value,listObj.options[i+1].value,groupid);  
            break;   
          }   
       }   
    }
    //上移触发的Ajax函数
    function upMoveAjax(id,id1,groupid){
		 		createxmlhttprequest();
		 		var url = '${req.contextPath}/communication/groups/upMoveUser.html?id='+id+'&id1='+id1+'&groupid='+groupid;
		 		xmlhttpreq.open("get",encodeURI(url),true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
		 }
	//下移触发的Ajax	 
    function downMoveAjax(id,id1,groupid){
               createxmlhttprequest();
		 		var url = '${req.contextPath}/communication/groups/downMoveUser.html?id='+id+'&id1='+id1+'&groupid='+groupid;
		 		xmlhttpreq.open("get",encodeURI(url),true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
    }		 
	 function createxmlhttprequest(){
		 	if (window.XMLHttpRequest) {
		 		xmlhttpreq = new XMLHttpRequest();
		 	}else if (window.ActiveXObject) {
		 		try {
		 			xmlhttpreq = new ActiveXObject("Msxml2.XMLHTTP");
		 		}catch (e) {
		 			try {
		 				xmlhttpreq = new ActiveXObject("microsoft.XMLHTTP");
		 			}catch (e) {}
		 		}
		 	}
		 }
	 function processresponse () {
		 	if (xmlhttpreq.readystate == 4) {
		 		if (xmlhttpreq.status == 200){
		 			
		 		}else {
		 			window.alert("页面请求有异常")
		 		}
		 	}
		 } 
function keyDown(){
       //点击排序时显示“上移” “下移” 按钮
        document.getElementById("submit0").style.display='none';
        document.getElementById("submit01").style.display='inline';
        document.getElementById("submit1").style.display='inline';
	    document.getElementById("submit2").style.display='inline';
	    //点击排序时 禁止select多选
	    var obj=document.all("grantedUser.id");
	    obj.multiple=false;
	   }
   function finish(){
   
        document.getElementById("submit0").style.display='inline';
        document.getElementById("submit01").style.display='none';
        document.getElementById("submit1").style.display='none';
	    document.getElementById("submit2").style.display='none';
	    var obj=document.all("grantedUser.id");
	    obj.multiple=true;
    }	   
    -->
    </script>
</@htmlPage>

