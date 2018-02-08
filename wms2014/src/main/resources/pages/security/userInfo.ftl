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
<#include "../includes/macros.ftl" />
<@htmlPage>
<@ww.form namespace="'/security'" name="'user'" action="'saveUser'" method="'post'"   validate="true">
    <@ww.token name="saveUserToken"/>
    <@inputTable >
        <#if user.id?exists>
            <@ww.hidden name="'user.id'" value="#{user.id}"/>
            <@ww.hidden name="'user.version'" value="#{user.version?if_exists}"/>
        </#if>
        <@ww.hidden name="'availableGroupIds'" value=""/>
        <@ww.hidden name="'grantedGroupIds'" value=""/>
        <@ww.hidden name="'availableRoleCodes'" value=""/>
        <@ww.hidden name="'grantedRoleCodes'" value=""/>
        <@ww.hidden name="'filialeId'" value="${req.getParameter('filiale.id')?if_exists}" />
        <tr>
            <@ww.select label="'${action.getText('user.type')}'" required="true" 
                        name="'user.type'"  listKey="value" listValue="label"
                        list="userType" emptyOption="ture" disabled="false" 
                        value="'${(user.userType)?if_exists}'" onchange="'changeValue()'">                  
      		</@ww.select>
        </tr>
        <tr>
            <@ww.textfield label="'${action.getText('user.loginName')}'"
                name="'user.loginName'" cssClass="'underline'" required="true" readonly="${(!user.new)?string}"/>
            <@ww.textfield label="'${action.getText('user.name')}'"
                name="'user.name'" cssClass="'underline'" required="true"/>

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

          
        </tr>
        
        <tr>
            <#if !locales.isEmpty()>
                <@ww.select label="'${action.getText('user.locale')}'" name="'user.locale'"
                    value="'${user.locale?if_exists}'" listKey="toString()"
                    listValue="getDisplayName(locale)" list="locales" required="true" />
            </#if>
    <#--       
            <@ww.select label="'${action.getText('filiale')}'" 
            			required="false" name="'filiale.id'"  
            			listKey="id" listValue="name"
                  		list="filiales" emptyOption="true"  
                  		onchange="'filialeSelectDept()'" disabled="false" required="true">                  
      		</@ww.select>   
              <@ww.select label="'${action.getText('user.department')}'" required="false" 
              		name="'department.id'"  listKey="id" listValue="name"
                    list="departments" emptyOption="true" disabled="false" required="true">                  
      		</@ww.select>
      --> 		
      		<#--	-->	
            <@ww.select label="'${action.getText('filiale')}'" 
                           name="'filiale.id'" 
                           value="'${req.getParameter('filiale.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="filiales" emptyOption="false" 
                           disabled="false" required="true" 
                           onchange="'filialeSelectDeptEditDWR()'">
               </@ww.select>
      			
               <@ww.select label="'${action.getText('department')}'" 
                           name="'department.id'" 
                           value="'${req.getParameter('department.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="departments" emptyOption="false" 
                           disabled="false" required="true">
               </@ww.select>
      	
      	
      		
       <tr>         
            <td align="right" valign="top"><label  for="user_viewall" class="label">${action.getText('user.viewall')}:</label></td>
             <td>
             	<select name="viewall.option" id="user_viewall">
             		<option value="Y">${action.getText('user.canViewAll')}</option>
             		<option value="N">${action.getText('user.canNotViewAll')}</option>
             	</select>
             	<script language="javascript">
             		<#if (user.viewAll)>
             			document.forms[0].elements["viewall.option"].value = 'Y';
             		<#else>
             			document.forms[0].elements["viewall.option"].value = 'N';
             		</#if>
             	</script>
            <@ww.textfield label="'${action.getText('user.email')}'"
                name="'user.email'" size="30" cssClass="'underline'" required="false"/>
             	
        </tr>      
    </@inputTable>

    <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
            <#--
            <#assign confirmMessage = "${action.getText('confirm.disable')}[${user.name?if_exists}]?" />
            <@vsubmit name="'disable'" value="'${action.getText('disable')}'">
                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${(!user.id?exists)?string}"/>
            </@vsubmit>
            -->
            <#if (user.enabled)>
	            <#assign confirmMessage = "${action.getText('confirm.disable')}[${user.name?if_exists}]?" />
	            <@vsubmit name="'disable'" value="'${action.getText('disable')}'">
	                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${(!user.id?exists || user.id==loginUser.id)?string}"/>
	            </@vsubmit>
	        <#else>
	        	<#assign confirmMessage = "${action.getText('comfirm.enable')}[${user.name?if_exists}]?" />
	        	<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'" >
	        		<@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${(!user.id?exists || user.id==loginUser.id)?string}"/>
	        	</@vsubmit>
            </#if>
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/security/listUsers.html"/>
    </@buttonBar>

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
 	<#---组织区域--->  
        <@titleBar title="${action.getText('已加入的组织区域')}"/>
        <@listTable>
            <tr>
                <th></th>
                <th>${action.getText('分公司名称')}</th>
                <th>${action.getText('部门代码')}</th>
                <th>${action.getText('部门名称')}</th>
            </tr>
            <#list user.departments as dept>
                <tr>
                    <td>
                        <input type="checkbox" name="deptIds" value="#{dept.id}" width="30" />
                    </td>
                    <td>${dept.filiale.name}</td>
                    <td>${dept.code}</td>
                    <td>${dept.name}</td>
                </tr>
            </#list>
        </@listTable>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('部门')}?" />
            <@vsubmit name="'revoke_dept'" value="'${action.getText('退出')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"deptIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${user.departments.isEmpty()?string}"/>
            </@vsubmit>
            <@vbutton name="'add'"  class="button" value="${action.getText('选择组织区域')}" onclick="selectFilialeAndDept()"/>
        </@buttonBar>
       <#---权限仓库--->  
        <@titleBar title="${action.getText('已加入的权限仓库')}"/>
        <@listTable>
            <tr>
                <th></th>
                <th>${action.getText('仓库编码')}</th>
                <th>${action.getText('仓库名称')}</th>
            </tr>
            <#list user.warehouses as wh>
                <tr>
                    <td>
                        <input type="checkbox" name="whIds" value="#{wh.id}" width="30" />
                    </td>
                    <td>${wh.code}</td>
                    <td>${wh.name}</td>
                </tr>
            </#list>
        </@listTable>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('仓库')}?" />
            <@vsubmit name="'revoke_wh'" value="'${action.getText('退出')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"whIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${user.warehouses.isEmpty()?string}"/>
            </@vsubmit>
            <@vbutton name="'add'"  class="button" value="${action.getText('选择权限仓库')}" onclick="chooseRoleWareHouse();"/>
        </@buttonBar>
<tr class="input">
           <td>
       <table class="defaultLook">
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
            <table class="defaultLook" >
              <tr width="100%">
                <td style="text-align:center" width="20%"><b>可用用户组<b></td>
                <td width="10%"></td>
                <td style="text-align:center" width="20%"><b>已加入用户组</b></td>
                <td style="text-align:center" width="20%"><b>可用角色</b></td>
                <td width="10%"></td>
                <td style="text-align:center" width="20%"><b>已授予角色</b></td>
              </tr>
              <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%" style="width:150px">
                  <select name="availableGroup.id" multiple size="20">
                    <#list joinableGroups as joinableGroup>
                      <option value="#{joinableGroup.id}">${joinableGroup.name}</option>
                    </#list>
                  </select>
                </td>
                <td width="10%">
                  <br><input name="join" type="submit" value="${action.getText('join')}&gt;" size="15" onclick="return selectAvailableGroup();"></br>
                  <br><input name="leave" type="submit" value="&lt;${action.getText('leave')}" size="15" onclick="return selectGrantedGroup();"></br>
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
                  <br><input name="grant_role" type="submit" value="${action.getText('grant')}&gt;" size="15" onclick="return selectAvailableRole();"></br>
                  <br><input name="revoke_role" type="submit" value="&lt;${action.getText('revoke')}" size="15" onclick="return selectGrantedRole();"></br>
                </td>
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="grantedRole.code" multiple size="20" style="width:150px">
                    <#list userSortRole as grantedRole>
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

	  			<#if user.filiale?exists>  
		      		getObjByName('filiale.id').value = ${user.filiale.id?if_exists};
	  			<#else>
	  				filialeSelectDeptEditDWR();
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
          //验证用户名
          if ('' == getObjByName('user.loginName').value) {
            alert("${action.getText('user.loginName.requiredstring')}");
            return false;
          }else{
          	var name = getObjByName('user.loginName').value;
            var regu = "^\\w+$" ;
            var re = new RegExp(regu);
            if (name.search(re) == -1) {
           	  alert("${action.getText("user.loginName.Invalid")}");
              return false;
            }
          }
          //验证姓名
          if ('' == getObjByName('user.name').value) {
            alert("${action.getText('user.name.requiredstring')}");
            return false;
          } else {
          	if (!isValidLengthValue(getObjByName('user.name').value,0,20)) {
				      alert("${action.getText('user.name.maxLength')}");
				      return false;
		    		}
          
            var name = getObjByName('user.name').value;
            var regu = "^[\u4e00-\u9fa5]+$";
            var re = new RegExp(regu);
            if (name.search(re) == -1) {
              alert("${action.getText("user.name.Invalid")}");
              return false;
            } 
          }
          <#if user.new>
          	//当用户类型是系统用户
			if ('SYSTEM_USER' == getObjByName('user.type').value) {
		          //验证密码
		          if ('' == getObjByName('newPassword').value) {
		            alert("${action.getText('password.new.required')}");
		            return false;
		          } else {
		          	if (isBlank("newPassword")){
		          	alert("${action.getText('password.not.blank')}");
		          	return false;
		          	}
		          	if (!isValidLengthValue(getObjByName('newPassword').value,0,30)) {
				      alert("${action.getText('password.new.maxLength')}");
				      return false;
		    		}
		          }
		          //if (isBlank("newPassword1")){
		          	//alert("error");
		          //}
		          //验证重复密码
		          if ('' == getObjByName('confirmPassword').value) {
		            alert("${action.getText('password.confirm.required')}");
		            return false;
		          }
	         } else {   //当用户类型是非系统用户，默认密码为sa
	            getObjByName('newPassword1').value = 'sa';
	            getObjByName('confirmPassword1').value = 'sa';
	          }
	      </#if>
          //验证Eamil格式
          if ('' != getObjByName('user.email').value) {
			var value = getObjByName('user.email').value;
            if (!value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert("${action.getText('user.email.email')}");
          		return false;
          	}
          	if (!isValidLengthValue(getObjByName('user.email').value,0,50)) {
				      alert("${action.getText('user.email.maxLength')}");
				      return false;
		    		}
          }
          //验证分公司不能为空
          if(-1 == getObjByName('filiale.id').value){
          	alert("${action.getText('filiale.id.requiredstring')}");
          	return false;
          }
          //验证部门不能为空
          if(-1 == getObjByName('department.id').value){
          	alert("${action.getText('department.id.requiredstring')}");
          	return false;
          }
          return true;
        }
        //点击"加入"按钮，触发
        function selectAvailableGroup() {
          var ary = new Array();
          var selector = document.all("availableGroup.id");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected) {
              ary.push(selector.options[i].value);
            }
          }
          document.forms[0].elements["availableGroupIds"].value = ary;
          if ('' != document.forms[0].elements["availableGroupIds"].value) {
            return true;   
          }else{
          	alert('请选择需要加入的组');
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
          }else{
          	alert('请选择需要退出的组');
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
            }else{
            	alert('请选择需要授予的权限');
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
               if (selector.options[i].value == "ADMIN") {
                 <#if user.id?exists>
                 <#if loginUser.id == user.id>
                 	alert("系统管理角色不能被剥夺！");
                 	return false;
                 </#if>
                 </#if>
               }
               ary.push(selector.options[i].value);
            }
          }
          document.forms[0].elements["grantedRoleCodes"].value = ary;
          if ('' != document.forms[0].elements["grantedRoleCodes"].value) {
            return true;   
          }else{
          	alert('请选择需要剥夺的权限');
          }
          return false;
        }
        //确认验证
        function changeValidate() {
           //验证新密码
	       if ('' == document.forms["user"].elements["newPassword"].value) {
	         alert("${action.getText('password.new.required')}");
	         return false;
	       } else {
		       if (isBlank("newPassword")){
		         alert("${action.getText('password.not.blank')}");
		         return false;
		        }
		       if (!isValidLengthValue(getObjByName('newPassword').value,0,30)) {
			      alert("${action.getText('password.new.maxLength')}");
			      return false;
		    	}
	       }
	       //验证重复密码
	       if ('' == document.forms["user"].elements["confirmPassword"].value) {
	         alert("${action.getText('password.confirm.required')}");
	         return false;
	       }
        }

        
        //根据用户类型显示哪些字段是必须输入项
        function changeValue() {
          //如果用户类型是非系统用户，则隐藏密码行，用户名不是必须输入项
          if ('NON_SYSTEM_USER' == document.forms[0].elements["user.type"].value) {
            <#if user.new>
              getObjByNameRe("passwordTR").style.display="none";
            </#if>
            //getObjByNameRe("user.loginName.span").style.display="none";
          } else {    //如果用户类型是系统用户，则显示密码行，用户名是必须输入项
            <#if user.new>
              getObjByNameRe("passwordTR").style.display="";
            </#if>
            //getObjByNameRe("user.loginName.span").style.display="";
          }
        
        }

        function selectFilialeAndDept(){
        <#if user.id?exists>
        	var url = '${req.contextPath}/filialeSelector/filialeSelector.html?userId='+#{user.id};
        	popupModalDialog(url,700,500);
        	self.location.reload();
        	//newopen = openWin(url,'select',700,500,1);
        	//if(openwin.opener.close()){
        	//	self.location.reload();
	    	//	return false;
	    	//}
        </#if>	
        }
        
        function chooseRoleWareHouse(){
        <#if user.id?exists>
        	var url = '${req.contextPath}/warehouseSelector/chooseWarehouse.html?userId='+#{user.id};
        	popupModalDialog(url,700,500);
        	self.location.reload();
        </#if>	
        }

    </script>
</@ww.form>
</@htmlPage>
