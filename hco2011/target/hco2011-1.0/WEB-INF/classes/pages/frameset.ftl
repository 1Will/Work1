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
<#--$Id: frameset.ftl 10072 2008-01-03 08:53:12Z qsun $ -->

<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title id="Title_CPLogin">欢迎进入永君客户关系管理系统</title>
        <SCRIPT LANGUAGE="JavaScript">
            if (top.location != self.location){
                top.location = self.location;
            }
            window.onload=function(){
             <#if user?exists>
                <#if user.fristLoginFlag==true>
                   alert('您是第一次登陆,请修改密码');
                </#if>
             </#if>
            }
        </SCRIPT>
	</head>
    <frameset rows="113,*" borderWidth="0" border="0">
        <frame src="topFrame.html" name="topFrame" scrolling="no">
        <frameset cols="200,*">
            <frameset rows="41,*">
                <frame src="logoFrame.html" name="logoFrame" scrolling="no">
                <frame src="menuFrame.html" name="menuFrame" scrolling="yes">
            </frameset>
            <#--
            <frame src="center.html" name="centerFrame" scrolling="auto">
            -->
      		<#if user?exists>
          	<#if user.fristLoginFlag==true>
             	<frame src="${req.getParameter('url')?default('security/userProfile.html')}" name="mainFrame" scrolling="yes">
          	<#else>
              	<frame src="${req.getParameter('url')?default('index.html')}" name="mainFrame" scrolling="yes">
           	</#if>  
      	 	</#if> 
        </frameset>
    </frameset>
</html>
