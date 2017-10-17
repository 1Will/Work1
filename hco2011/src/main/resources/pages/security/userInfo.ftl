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
<#--$Id: userInfo.ftl 6134 2007-07-30 10:05:53Z qsun $ -->
<#include "../includes/hco2011.ftl" />
<@htmlPage>
<@ww.form namespace="'/security'" name="'user'" action="'saveUser'" method="'post'"   validate="true">
    <@ww.token name="saveUserToken"/>
    <@inputTable >
        <#if user.id?exists>
            <@ww.hidden name="'user.id'" value="#{user.id}"/>
            <@ww.hidden name="'user.version'" value="#{user.version?if_exists}"/>
        </#if>
         <@ww.hidden name="'department1.id'" value=""/>
        <@ww.hidden name="'availableGroupIds'" value=""/>
        <@ww.hidden name="'grantedGroupIds'" value=""/>
        <@ww.hidden name="'availableRoleCodes'" value=""/>
        <@ww.hidden name="'grantedRoleCodes'" value=""/>
        <@ww.hidden name="'personCode'" value="'${user.code?if_exists}'"/>
         <@ww.hidden name="'personId'" value=""/>
        <tr>
            <td align="right" valign="top">
              <label  for="saveUser_user.loginName" class="label"><span id="user.loginName.span" class="required">*</span>${action.getText('user.loginName')}:</label>
            </td>
		    <td>
		      <input type="text" name="user.loginName" value="${user.loginName?if_exists}" 
		             id="saveUser_user.loginName" class="underline"/>
		    </td>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('user.name')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="user.name" 
			      class="underline"  value="${user.name?if_exists}"  maxlength="140" size="20" readonly="true"/>
		       <a onClick="user_OpenDialog();">
		       	
	    	     <img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand" />
	    	   
	    	   </a>
            </td>
		</tr>
        <#if user.new>
        <tr id="passwordTR">
             <@ww.password label="'${action.getText('password.new')}'" id="newPassword1"
                    name="'newPassword'"  cssClass="'underline'" required="true"/>  
              <@ww.password label="'${action.getText('password.confirm')}'" id="confirmPassword1"
               name="'confirmPassword'"  cssClass="'underline'" required="true"/>
         </tr>
        </#if>
		<tr>
	        <@ww.select label="'${action.getText('institution')}'" 
	                   name="'institution.id'" 
	                   value="'${req.getParameter('institution.id')?if_exists}'"
	                   listKey="id" listValue="name"
	                   list="institutions" emptyOption="false" 
	                   required="true"
		               onchange="'InstitutionSelectDeptDWR(\"institution.id\",\"department.id\",\"${action.getText('')}\",\"false\")'">
	        </@ww.select>
	        <#if user.institustion?exists>
      			<script language="javascript">
      				document.forms[0].elements["institution.id"].value = #{user.institustion.id};
      			</script>
      		</#if>	
            <@ww.select label="'${action.getText('user.department')}'" 
			            required="true" name="'department.id'"  
			            listKey="id" listValue="name"
                  		list="departments" emptyOption="false">                  
      		</@ww.select>
      		<#---->
      		<#if user.department?exists>
      			<script language="javascript">
      				document.forms[0].elements["department.id"].value = #{user.department.id};
      			</script>
      		</#if>
         </tr>
         <tr>
         <@ww.textfield label="'${action.getText('工号')}'"
                name="'user.code'" size="20" cssClass="'underline'" readonly="true"/>
                
      	 <@ww.textfield label="'${action.getText('telNumber')}'"
                name="'user.telphoneNumber'" size="20" cssClass="'underline'" />
         </tr>
         <tr>
         	  <@pp.datePicker label="'${action.getText('bith')}'" name="'user.brith'"
	     				  value="'${(user.brith?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="20" 
	     				   maxlength="10"/>
	     				   
         <#if !locales.isEmpty()>
                <@ww.select label="'${action.getText('user.locale')}'" name="'user.locale'"
                    value="'${user.locale?if_exists}'" listKey="toString()"
                    listValue="getDisplayName(locale)" list="locales" required="true" />
            </#if>
                 <@ww.textfield label="'${action.getText('user.email')}'"
			name="'user.email'" size="20" cssClass="'underline'" required="false"/>
         </tr>
		 <tr>
	 		<td valign="middle" align="right">
	 		   <label  for="privilegeUser" class="checkboxLabel">${action.getText('privilege.user')}:</label>
	 		</td>
	 		<td valign="middle" align="left">
	 		   <input type="checkbox" name="privilegeUser" value="privilege"
                      id="privilegeUser"/>
            </td>
			<script language="javascript">
        		<#if user.privilegeUser==true>
        			getObjByName('privilegeUser').checked = true;
        		<#else>
        		    getObjByName('privilegeUser').checked = false;
		     	</#if>
        	</script> 
		 </tr>  
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
        <#if (user.enabled)>
            <#assign confirmMessage = "${action.getText('confirm.disable')}[${user.name?if_exists}]?" />
            <@vsubmit name="'disable'" value="'${action.getText('disable')}'" onclick="'return ss();'">
            	<@ww.param name="'disabled'" value="${(!user.id?exists)?string}"/>
            </@vsubmit>
	     <#else>
        	<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'" onclick="'return aa();'"/>
         </#if>
    </#if>
         
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/security/listUsers.html"/>
    </@buttonBar>
    <script type="text/javascript">
    	function ss(){
    		var s = confirm("确定要失效该信息吗？");
    		if(s){
    			return true;
    		}else{
    		return false;
    		}
    	}
    	function aa(){
    		var s = confirm("确定要有效该信息吗？");
    		if(s){
    			return true;
    		}else{
    		return false;
    		}
    	}
    </script>

    <#if !user.new>
      <#if user.userType=='SYSTEM_USER'>
        <@ww.token name="changePasswordToken"/>
        <@titleBar title="${action.getText('password.change')}"/>
        <@inputTable>
            <tr>
                <@ww.password label="'${action.getText('password.new')}'"  name="'newPassword'"  cssClass="'underline'" required="true"/>
                <@ww.password label="'${action.getText('password.confirm')}'" name="'confirmPassword'"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
                <@vsubmit name="'change_password'" value="'${action.getText('ok')}'" onclick="'return changeValidate();'"/>
        </@buttonBar>
      </#if>
      
      <!-- -->
      <@titleBar title="${action.getText('已加入的组织区域')}"/>
        <@listTable>
            <tr>
                <th></th>
                <th>${action.getText('单位名称')}</th>
                <th>${action.getText('部门代码')}</th>
                <th>${action.getText('部门名称')}</th>
            </tr>
            <#list user.departments as dept>
                <tr>
                    <td>
                        <input type="checkbox" name="deptIds" value="#{dept.id}" width="30" />
                    </td>
                    <td>${dept.inst.name}</td>
                    <td>${dept.code}</td>
                    <td>${dept.name}</td>
                </tr>
            </#list>
        </@listTable>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('部门')}?" />
            <@vsubmit name="'revokeDept'" value="'${action.getText('删除')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"deptIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${user.departments.isEmpty()?string}"/>
            </@vsubmit>
            <@vbutton name="'add'"  class="button" value="${action.getText('选择组织区域')}" onclick="selectFilialeAndDept()"/>
        </@buttonBar>
      <!-- -->
      
      <tr class="input">
        <td>
	       	<table style="text-align: center;width: 100%;">
	          <tr>
	            <td class="title" width="50%">
	              用户组加入与退出
	            </td>
	            <td class="title" width="50%">
	              角色授予与剥夺
	            </td>
	          </tr>
	        </table>
        </td>
      </tr>
      <tr class="input">
        <td>
            <table class="defaultLook" style="text-align: center;width: 100%;">
              <tr width="100%">
                <td style="text-align:center" width="20%"><b>可用用户组<b></td>
                <td width="10%"></td>
                <td style="text-align:center" width="20%"><b>已加入用户组</b></td>
                <td style="text-align:center" width="20%"><b>可用角色</b></td>
                <td width="10%"></td>
                <td style="text-align:center" width="20%"><b>已授予角色</b></td>
              </tr>
              <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="availableGroup.id" multiple size="20" style="width:150px">
                    <#list joinableAuthorityGroups as joinableGroup>
                      <option value="#{joinableGroup.id}">${joinableGroup.name}</option>
                    </#list>
                  </select>
                </td>
                <td width="10%">
                  <br><@vsubmit name="'join'" value="'${action.getText('join')}->'" onclick="'return selectAvailableGroup();'"/></br>
                  <br><@vsubmit name="'leave'" value="'<-${action.getText('leave')}'" onclick="'return selectGrantedGroup();'"/></br>
                </td>
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="grantedGroup.id" multiple size="20" style="width:150px">
                    <#list user.groups as group>
                      <option value="${group.id}">${group.name}</option>
                    </#list>
                  </select>
                </td>
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="availableRole.code" multiple size="20" style="width:150px">
	                <#list grantableRoles as grantableRole>
	                    <option value="${grantableRole.code}">${grantableRole.name}</option>
	                </#list>
                  </select>
                </td>
                <td width="10%">
                  <br><@vsubmit name="'grant_role'" value="'${action.getText('grant')}->'" onclick="'return selectAvailableRole();'"/></br>
                  <br><@vsubmit name="'revoke_role'" value="'<-${action.getText('revoke')}'" onclick="'return selectGrantedRole();'"/></br>
                </td>
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="grantedRole.code" multiple size="20" style="width:150px">
                    <#list user.roles as grantedRole>
                      <option value="${grantedRole.code}">${grantedRole.name}</option>
                    </#list>
                  </select>
                </td>
              </tr>  
            </table>
          </td>
        </tr>
     </#if>

    <script type="text/javascript">
     window.onload = function() {
//     	 alert("0000 " + getObjByName('institution.id').value );
     }
     <#---->
     	<#if user.institustion?exists >
     		getObjByName('institution.id').value = ${user.institustion.id}
			//设置同步
		    DWREngine.setAsync(false); 
		    //回调单位的值后触发DWR 级联部门  
			InstitutionSelectDeptDWR("institution.id","department.id","${action.getText('')}","false");
		    //重新设置为异步方式
		    DWREngine.setAsync(true);  
		    getObjByName('department.id').value = "#{user.department.id}"; 
     	<#else>
			InstitutionSelectDeptDWR("institution.id","department.id","${action.getText('')}","false");
     	</#if>
     	
        <#if (user.enabled)?exists>
          <#if !(user.enabled)>
            __disableAllElements__(document.forms[0], new Array("enabled", "back"));
            document.forms[0].elements["newPassword"].disabled = true;
            document.forms[0].elements["confirmPassword"].disabled = true;
          <#else>
            document.forms["user"].elements["user.loginName"].focus();
          </#if>
        </#if>
        function validate() {
        
        //验证登陆名
           if ('' == document.forms["user"].elements["user.loginName"].value) {
	            alert("${action.getText('user.loginName.requiredstring')}");
	            getObjByName('user.loginName').focus();
	            return false;
           }else{
		        if(!isValidLength(document.forms["user"], "user.loginName", null, 50)){
					alert("${action.getText('user.loginName.length')}");
					getObjByName('user.loginName').value="";
					getObjByName('user.loginName').focus();
					return  false;
				 }
		   }
		    //验证姓名
           if ('' == document.forms["user"].elements["user.name"].value) {
	            alert("${action.getText('user.name.requiredstring')}");
	            getObjByName('user.name').focus();
	            return false;
           }else{
		        if(!isValidLength(document.forms["user"], "user.name", null, 50)){
					alert("${action.getText('user.name.length')}");
					getObjByName('user.name').value="";
					getObjByName('user.name').focus();
					return  false;
				 }
				 <#-- 
				var name = document.forms["user"].elements["user.name"].value;
	            var regu = "^[\u4e00-\u9fa5]+$";
	            var re = new RegExp(regu);
	            if (name.search(re) == -1) {
	              alert("${action.getText("user.name.Invalid")}");
	              return false;
	            } 
	           -->
			}  
		<#if user.new>	
        //验证密码
          if ('' == document.forms["user"].elements["newPassword1"].value) {
               alert("${action.getText('password.new.required')}");
               getObjByName('newPassword1').focus();
               return false;
          }else if (isBlank("newPassword1")){
	           alert("${action.getText('password.not.blank')}");
	           getObjByName('newPassword1').focus();
	           return false;
	      }

        //验证重复密码
          if ('' == document.forms["user"].elements["confirmPassword1"].value) {
              alert("${action.getText('password.confirm.required')}");
              getObjByName('confirmPassword1').focus();
              return false;
           }
           
           if(document.forms["user"].elements["newPassword1"].value!=document.forms["user"].elements["confirmPassword1"].value){
 				alert("${action.getText('password.is.not.equls.confirmPassword1')}");
 				getObjByName('confirmPassword1').value="";
 				getObjByName('confirmPassword1').focus();
              	return false;          	
           }
         </#if>  
			           	      
	       //验证单位是否为空
           if(-1==document.forms["user"].elements["institution.id"].value){
                alert("${action.getText('user.institustion.required')}");
                getObjByName('institution.id').focus();
                return false;
            }
	       //验证部门
           if(-1 ==document.forms["user"].elements["department.id"].value){
                alert("${action.getText('user.department.id')}");
                getObjByName('department.id').focus();
                 return false;
            }
                        
          //验证手机号码
           if(''!=document.forms["user"].elements["user.telphoneNumber"].value){
              if(!isValidLength(document.forms["user"], "user.telphoneNumber", null, 50)){
				alert("${action.getText('user.telphoneNumber.length')}");
				getObjByName('user.telphoneNumber').value="";
 				getObjByName('user.telphoneNumber').focus();
				return  false;
			   }
           }
          
	        // 验证生日
	        var date=document.getElementById("user.brith").value;
	        if(document.forms["user"].elements["user.brith"].value!=''){
			   if(!isDate("user.brith")){
			        alert("${action.getText('select.right.user.brith')}");
			        getObjByName('user.brith').value="";
 					getObjByName('user.brith').focus();
				  return false;
			    }
	        }  
			//验证Eamil格式
			if ('' != document.forms["user"].elements["user.email"].value) {
				var value = document.forms["user"].elements["user.email"].value;
				if (!value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
					alert("${action.getText('user.email.email')}");
					getObjByName('user.email').value="";
 					getObjByName('user.email').focus();
					return false;
				}
			}
          return true;
        }
        
        //点击"加入"按钮，触发
        function selectAvailableGroup() {
          var ary = new Array();
          var selector = document.all("availableGroup.id");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected && (selector.options[i].value!='-1'&&selector.options[i].value!='-2')) {
              ary.push(selector.options[i].value);
            }
          }
          document.forms[0].elements["availableGroupIds"].value = ary;
          if ('' != document.forms[0].elements["availableGroupIds"].value) {
            return true;   
          }
          return false;
        }
        //点击"退出"按钮，触发
        function selectGrantedGroup() {
          var ary = new Array();
          var selector = document.all("grantedGroup.id");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected) {
              ary.push(selector.options[i].value);
            }
          }
          document.forms[0].elements["grantedGroupIds"].value = ary;
          if ('' != document.forms[0].elements["grantedGroupIds"].value) {
            return true;   
          }
          return false;
        }
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
           document.forms[0].elements["availableRoleCodes"].value = ary;
           if ('' != document.forms[0].elements["availableRoleCodes"].value) {
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
          document.forms[0].elements["grantedRoleCodes"].value = ary;
          if ('' != document.forms[0].elements["grantedRoleCodes"].value) {
            return true;   
          }
          return false;
        }
        //确认验证
        function changeValidate() {
           //验证新密码
	       if ('' == document.forms["user"].elements["newPassword"].value) {
	         alert("${action.getText('password.new.required')}");
	         return false;
	       } else if (isBlank("newPassword")){
	         alert("${action.getText('password.not.blank')}");
	         return false;
	       }
	       //验证重复密码
	       if ('' == document.forms["user"].elements["confirmPassword"].value) {
	         alert("${action.getText('password.confirm.required')}");
	         return false;
	       }
        }
        //根据用户类型显示哪些字段是必须输入项
        function changeValue() {
          //如果用户类型是非登陆用户，则隐藏密码行，用户名不是必须输入项
          if ('NON_SYSTEM_USER' == document.forms[0].elements["user.type"].value) {
            <#if user.new>
              document.getElementById("passwordTR").style.display="none";
            </#if>
            document.getElementById("user.loginName.span").style.display="none";
          } else {    //如果用户类型是登陆用户，则显示密码行，用户名是必须输入项
            <#if user.new>
              document.getElementById("passwordTR").style.display="";
            </#if>
            document.getElementById("user.loginName.span").style.display="";
          }
        
        }

   		//弹出档案
		function user_OpenDialog(){
		   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
		   //alert(url);
		   popupModalDialog(url, 800, 600, creatorSelectorHandler);
		   // window.open(url, 800, 600);
		 }
		 //获得模态窗体返回值
		function creatorSelectorHandler(result) {
			if (null != result) {
				document.forms[0].elements["personId"].value = result[0];
				document.forms[0].elements["personCode"].value = result[1];
		   		document.forms[0].elements["user.name"].value = result[2];
		   		//document.forms[0].elements["user.loginName"].value = result[3];
				document.forms[0].elements["institution.id"].value = result[3];
				getObjByName('user.code').value=result[1];
				
				//设置同步
			    DWREngine.setAsync(false); 
			    //回调单位的值后触发DWR 级联部门  
				InstitutionSelectDeptDWR("institution.id","department.id","${action.getText('')}","false");
			    //重新设置为异步方式
			    DWREngine.setAsync(true);   
			    
			 	document.forms[0].elements["department.id"].value = result[4];
			 	document.forms[0].elements["user.telphoneNumber"].value = result[5];
			 	document.forms[0].elements["user.brith"].value = result[6];
		   		document.forms[0].elements["user.email"].value = result[7];
				
			}
		}

		//弹出组织区域
   		function selectFilialeAndDept(){
        <#if user.id?exists>
        	var url = '${req.contextPath}/organizationRegional/orSelector.html?userId='+#{user.id};
        	popupModalDialog(url,700,500);
        	if(isIE()){self.location.reload();};
        </#if>	
        } 	     

    </script>
</@ww.form>
</@htmlPage>
