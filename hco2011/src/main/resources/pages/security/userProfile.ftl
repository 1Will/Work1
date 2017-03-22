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
<#-- $$Id: userProfile.ftl 6134 2007-07-30 10:05:53Z qsun $$ -->

<#include "../includes/hco2011.ftl" />

<@htmlPage>
    <@ww.form name="'user'" action="'saveUserProfile'" method="'post'" validate="true">
        <@ww.token name="saveUserProfileToken"/>
        <@titleBar title="${action.getText('title')}"/>
        <@inputTable>
        <@ww.hidden name="'flag'" value="'${(firstChangePasswordFlag?string)?if_exists}'"/>
        <@ww.hidden name="'flag1'" value="'eeee'"/>
            <tr>
                <@ww.textfield label="'${action.getText('user.loginName')}'"
                    value="'${user.loginName?if_exists}'" readonly="true" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('user.name')}'"  name="'user.name'"
                    value="'${user.name?if_exists}'" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
              	<@ww.textfield label="'${action.getText('telNumber')}'"
                name="'user.telphoneNumber'" size="20" cssClass="'underline'" required="false"/>
               <@pp.datePicker label="'${action.getText('bith')}'" name="'user.brith'"
	     				  value="'${(user.brith?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				   maxlength="10"/>
            </tr>
            <tr>
                <@ww.textfield label="'${action.getText('user.email')}'" name="'user.email'"
                    value="'${user.email?if_exists}'" size="30" cssClass="'underline'"/>                    
            </tr>
        </@inputTable>
        <@buttonBar>
                <@htmlSubmit name="save" value="${action.getText('save')}"/>
        </@buttonBar>
    </@ww.form>
    <hr/>
    <@ww.form name="'password'" action="'changePassword'" method="'post'" validate="true">
        <@ww.token name="changePasswordToken"/>
        <@titleBar title="${action.getText('password.change')}"/>
        <@inputTable>
            <tr>
                <@ww.password label="'${action.getText('password.current')}'"  name="'currentPassword'"  cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@ww.password label="'${action.getText('password.new')}'"  name="'newPassword'"  cssClass="'underline'" required="true"/>
                <@ww.password label="'${action.getText('password.confirm')}'" name="'confirmPassword'"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
                <@htmlSubmit name="save" value="${action.getText('ok')}"/>
        </@buttonBar>
    </@ww.form>

    <script type="text/javascript">
    <#if (action.isFirstChangePasswordFlag())>
    	alert('您已成功修改密码:系统将重新登陆');
        location = '${req.contextPath}/login.html';
    </#if>
      document.forms["password"].elements["currentPassword"].focus();
    </script>
</@htmlPage>
