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
<#include "../includes/macros.ftl" />

<@htmlPage >
 <@ww.form namespace="'/security'" name="'group'" action="'saveGroup'" method="'post'" validate="true">
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
        <tr>
            <@ww.textfield label="'${action.getText('group.code')}'"  name="'group.code'" value="'${group.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
            <@ww.textfield label="'${action.getText('group.name')}'"  name="'group.name'" value="'${group.name?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
    </@inputTable>
     <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('group')}[${group.name?if_exists}]?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${(!group.id?exists)?string}"/>
            </@vsubmit>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/security/listGroups.html"/>
      </@buttonBar>

     <#if !group.new>
     <#--
        <@titleBar title="${action.getText('user.joined')}"/>
      
        <tr class="input">
          <table>
          <tr>
          <td class="title">
            ${action.getText('user.joined')}
          </td>
          <td class="title">
            ${action.getText('role.granted')}
          </td>
          </tr>
          </table>
        </tr>
          -->
       <tr class="input">
         <td>
           <table class="defaultLook">
             <tr>
               <td class="title" width="50%">
                <#--
                  ${action.getText('user.joined')}
                  -->
                  角色授予与剥夺
                </td>
                <td class="title" width="50%">
                <#--
                  ${action.getText('role.granted')}
                  -->
                  用户的加入与离开
                </td>
              </tr>
           </table>
         </td>
       </tr>
        <tr class="input">
          <td>
            <table class="defaultLook">
              <tr width="100%">
                <td style="text-align:center" width="20%"><b>可用角色<b></td>
                <td width="10%"></td>
                <td style="text-align:center" width="20%"><b>已授予角色</b></td>
                <td style="text-align:center" width="20%"><b>已授予的用户</b></td>
                <#--
                <td style="text-align:center" width="20%"><b>已授予的用户</b></td>
                -->
              </tr>
              <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="availableRole.code" multiple size="20">
                    <#list grantableRoles as grantableRole>
                      <option value="${grantableRole.code}">${grantableRole.name}</option>
                    </#list>
                  </select>
                </td>
                <td width="10%">
                  <br><input name="grant_role" type="submit" value="${action.getText('grant')}&gt;" size="15" onclick="return selectAvailableRole();"></br>
                  <br><input name="revoke_role" type="submit" value="&lt;${action.getText('revoke')}" size="15" onclick="return selectGrantedRole();"></br>
                <#--
                <br><input name="assign" type="submit" value="Join&gt;&gt;" size="15"></br>
                <br><input name="assign" type="submit" value="&lt;&lt;Leave" size="15"></br>
                -->
                </td>
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="grantedRole.code" multiple size="20">
                    <#list groupSortRole as grantedRole>
                      <option value="${grantedRole.code}">${grantedRole.name}</option>
                    </#list>
                  </select>
                </td>
                <#--
                <td style="VERTICAL-ALIGN:top;text-align:center" width="20%">
                  <select name="availableUser.id" multiple size="20">
                  </select>
                  <a href="javascript:user_OpenDialog();">
                    <img title="选择用户" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left : 5px;" />
                  </a>
                </td>
                <td width="10%">
                  <br><input name="join" type="submit" value="加入&gt;" size="15" onclick="return selectAvailableUser();"></br>
                  <br><input name="leave" type="submit" value="&lt;离开" size="15" onclick="return selectGrantedUser();"></br>
                </td>
                -->
                <td style="VERTICAL-ALIGN:top;text-align:center;position:relative;top:0px;right:0px" width="50%">
                  
                  <select name="grantedUser.id" multiple size="20">
                    <#list group.users as user>
                      <option value="#{user.id}">${user.name}</option>
                    </#list>
                  </select>
                  <a href="javascript:user_OpenDialog();" style="position:absolute;top:130px;right:110px">
                    <img title="选择用户" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left:5px;" />
                  </a>
                  <#--
                  <input style="position:relative;top:330px;right:160px" name="leave" type="submit" value="&lt;离开" size="15" onclick="return selectGrantedUser();">
                  -->
                  <input  style="position:absolute;top:155px;right:90px" name="leave" type="submit" value="离开&gt;" size="15" onclick="return selectGrantedUser();">
                </td>
              </tr>  
            </table>
          </td>
        </tr>
        <#list group.users as user>
          <input type="hidden" name="filterUserIds" value="#{user.id}"/>
        </#list>
        <#--
        <@listTable>
            <tr>
                <th></th>
                <th>${action.getText('user.loginName')}</th>
                <th>${action.getText('user.name')}</th>
            </tr>
            <#list group.users as user>
                <tr>
                    <td>
                        <input type="checkbox" name="userIds" value="#{user.id}" width="30" />
                    </td>
                    <td>${user.loginName}</td>
                    <td>${user.name}</td>
                </tr>
            </#list>
        </@listTable>
        <@buttonBar>
            <select name="user.id">
                <#list joinableUsers as joinableUser>
                    <option value="#{joinableUser.id}">${joinableUser.name}</option>
                </#list>
            </select>
            <@vsubmit name="'join'" value="'${action.getText('join')}'">
                <@ww.param name="'disabled'" value="${joinableUsers.isEmpty()?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('user')}?" />
            <@vsubmit name="'leave'" value="'${action.getText('leave')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"userIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${group.users.isEmpty()?string}"/>
            </@vsubmit>
        </@buttonBar>
        -->
        <#--
        <table>
          <tr>
            <td style="text-align:right"><b>选择用户</b></td>
            <td></td>
            <td style="text-align:left"><b>已授予的用户</b></td>
          </tr>
          <tr>
              <td style="VERTICAL-ALIGN:top;text-align:right" width="40%">
                <select name="availableUser.id" multiple size="10">
                </select>
                <a href="javascript:user_OpenDialog();">
                  <img title="选择用户" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left : 5px;" />
                </a>
              </td>
              <td width="20%">
                <br><input name="join" type="submit" value="加入&gt;" size="15" onclick="return selectAvailableUser();"></br>
                <br><input name="leave" type="submit" value="&lt;离开" size="15" onclick="return selectGrantedUser();"></br>
              </td>
              <td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
                <select name="grantedUser.id" multiple size="10">
                  <#list group.users as user>
                    <option value="#{user.id}">${user.name}</option>
                  </#list>
                </select>
              </td>
          </tr>
        </table>
        <@titleBar title="${action.getText('role.granted')}"/>
        <table width="100%">
        -->
        <#--
            <tr>
                <th></th>
                <th>${action.getText('role.code')}</th>
                <th>${action.getText('role.name')}</th>
            </tr>
            <#list group.roles as role>
                <tr>
                    <td>
                        <input type="checkbox" name="roleIds" value="#{role.id}" width="30" />
                    </td>
                    <td>${role.code}</td>
                    <td>${role.name}</td>
                </tr>
            </#list>
            -->
            <#--
            <tr>
              <td style="text-align:right" width="40%"><b>可用角色<b></td>
              <td width="20%"></td>
              <td style="text-align:left" width="40%"><b>已授予角色</b></td>
            </tr>
            <tr>
              <td style="VERTICAL-ALIGN:top;text-align:right" width="40%">
                <select name="availableRole.code" multiple size="10">
                  <#list grantableRoles as grantableRole>
                    <option value="${grantableRole.code}">${grantableRole.name}</option>
                  </#list>
                </select>
              </td>
              <td width="20%">
                <br><input name="grant_role" type="submit" value="${action.getText('grant')}&gt;" size="15" onclick="return selectAvailableRole();"></br>
                <br><input name="revoke_role" type="submit" value="&lt;${action.getText('revoke')}" size="15" onclick="return selectGrantedRole();"></br>
                -->
                <#--
                <br><input name="assign" type="submit" value="Join&gt;&gt;" size="15"></br>
                <br><input name="assign" type="submit" value="&lt;&lt;Leave" size="15"></br>
                -->
                <#--
              </td>
              <td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
                <select name="grantedRole.code" multiple size="10">
                  <#list group.roles as grantedRole>
                    <option value="${grantedRole.code}">${grantedRole.name}</option>
                  </#list>
                </select>
              </td>
            </tr>
        </table>
        -->
        <#--
        <@buttonBar>
            <select name="role.id" multiple size="#{grantableRoles.size()}">
                <#list grantableRoles as grantableRole>
                    <option value="#{grantableRole.id}">${grantableRole.name}</option>
                </#list>
            </select>
            <@vsubmit name="'grant_role'" value="'${action.getText('grant')}'">
                <@ww.param name="'disabled'" value="${grantableRoles.isEmpty()?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('role')}?" />
            <@vsubmit name="'revoke_role'" value="'${action.getText('revoke')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"roleIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${group.roles.isEmpty()?string}"/>
            </@vsubmit>
        </@buttonBar>
        -->
    </#if>
 </@ww.form>
     <script type="text/javascript">
		function validate(){
			if (!isValidLengthValue(getObjByName('group.name').value,0,50)) {
			      alert("${'名称最大长度为50'}");
			      return false;
		    }
		}

        document.forms["group"].elements["group.name"].focus();
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
               ary.push(selector.options[i].value);
            }
          }
          document.forms["group"].elements["grantedRoleCodes"].value = ary;
          if ('' != document.forms["group"].elements["grantedRoleCodes"].value) {
            return true;   
          }else{
          	alert('请选择需要剥夺的权限');
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
          }else{
          	alert('请选择需要离开的人员');
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
            var obj = getObjByNameRe("availableUser.id");
            for (var i=0; i<userArray.length; i=i+2) {
              var opt = new Option(userArray[i+1], userArray[i]);
		      eval("obj.options[obj.options.length]=opt");
            }
            -->
	      }
        }
    </script>
</@htmlPage>

