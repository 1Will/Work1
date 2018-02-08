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


<#-- $Id: userLogin.ftl 7641 2007-09-28 09:43:11Z qsun $ -->

<#include "includes/macros.ftl" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
	    <title>${action.getText('name.application')}</title>
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css" />
	</head>
	<body>
        <@ww.form name="'login'" action="'security_check'" method="'post'">
        <table width="100%" height="100%" border="0"><tr><td align="center">
            <table width="100%" height="100%" border="0">
                <tr>
                    <td align="center">
                        <table border="0" cellpadding="0" cellspacing="0">
                            
                            <tr>
                                <td>
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" background="${req.contextPath}/images/login/login_m1_bg.gif">
                                        <tr>
                                            <td><img src="${req.contextPath}/images/login/login_m1_left.gif"></td>
                                            <td><img src="${req.contextPath}/images/Logo.JPG"></td>
                                            <td align="right"><img src="${req.contextPath}/images/login/login_m1_right.gif"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><object classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000
                                    codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0"
                                    height=60 width=655>
                                        <param name="movie" value="${req.contextPath}/images/login/login.swf">
                                        <param name="quality" value="high">
                                        <embed src="/swf/login.swf" quality=high
                                            pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"
                                            type="application/x-shockwave-flash" width="650" height="60">
                                        </embed>
                                    </object></td>
                            </tr>
                            <tr>
                                <td width="650" height="160" background="${req.contextPath}/images/login_m3.jpg" align="center">
                                    <table border="0" cellspacing="10">
                                    <tr>
                                        <td colspan="2"><label class="errorLabel">${message?if_exists}</label></td>
                                    </tr>
                                    <tr>
                                        <@ww.textfield label="'${action.getText('username')}'" name="'j_username'" cssClass="'boxing'"/>
                                    </tr>
                                    <tr>
                                        <@ww.password label="'${action.getText('password')}'"  name="'j_password'" cssClass="'boxing'"/>
                                    </tr>
                                    <tr>
                                        <td/>
                                        <SCRIPT LANGUAGE="JavaScript">
                                            function resetPassword() {
                                                var passControl = document.all("j_password");
                                                passControl.value="";
                                                return false;
                                            }
                                        </SCRIPT>
                                        <td>
                                            <input type="image" src="${req.contextPath}/images/login/login_button.gif" width="80" height="20" />
                                            <input type="image" name="resetPassword" src="${req.contextPath}/images/login/reset_button.gif" width="80" height="20" onclick="return resetPassword();" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                           <td align="right"><img src="${req.contextPath}/images/logo3.jpg" height="18"></td>
                           <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        </@ww.form>
	</body>
</html>
