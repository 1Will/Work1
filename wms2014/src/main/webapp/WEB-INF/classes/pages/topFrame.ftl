 <#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
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
<#--$Id: topFrame.ftl 10072 2008-01-03 08:53:12Z qsun $ -->


<#include "includes/macros.ftl" />

<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/default.css" />
</head>
	
<body>
	
<@ww.form name="'logout'" action="'logout'" method="'post'">
<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" background="images/title/spot.gif">
  <tr height="40">
    <td>
        <table width="100%">
           <tr>
           	<td>&nbsp;</td>
              <td align="right">
              	<#if user.organization?exists>
              		<strong>${user.organization.name?if_exists}${action.getText("welcome")},</strong>&nbsp;&nbsp;&nbsp;&nbsp;
              	</#if>
                ${action.getText("user")}:<strong>${user.name}</strong>
                &nbsp;&nbsp;
                <a href="${req.contextPath}/security/userProfile.html" target="mainFrame"><image src="${req.contextPath}/images/icon/profile.gif" align="center" title="${action.getText('profile')}" width="13" height="13" border="0"/></a>
                <input type="image" src="${req.contextPath}/images/icon/logout.gif" align="center" title="${action.getText('logout')}" width="13" height="13" name="logout" onclick="return logout_onclick();"/>
                <a href="${req.contextPath}/index.html" target="mainFrame"><image src="${req.contextPath}/images/icon/home.gif" align="center" title="${action.getText('home')}" width="13" height="13" border="0"/></a>
                <@spacer width=30 height=1/>
              </td>
           </tr>
       </table>
    </td>
  </tr>
  <script>
  function logout_onclick(){
  /*
  	if(top.close()==true) {
  		return true;
  	} else {
  		return false;
  	}
  	*/
  	if (confirm("确定退出本系统吗?")) {
  	  return true;
  	} else {
  	  return false;
  	}
  }
  </script>
  <tr>
     <td vAlign="bottom" align="right" background="${req.contextPath}/images/title/separator.png">
       <@spacer width=30 height=4 />
     </td>
  </tr>
</table>
</@ww.form>
<div style="position:absolute; left: 40px; top:3px">
<img src="${req.contextPath}/images/logo_eam.gif"  height="30"/>
</div>

</body>
	
</html>