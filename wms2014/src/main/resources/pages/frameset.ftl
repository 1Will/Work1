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
	<title id="Title_CPLogin">欢迎进入乘用车制造公司备件库管理信息系统</title>
        <SCRIPT LANGUAGE="JavaScript">
            // this page should never load inside of another frame
            if (top.location != self.location){
                top.location = self.location;
            }
            <#--
            window.onload = function () {
            	window.parent.frames["topFrame"].location.reload();
            	window.parent.frames["menuFrame"].location.reload();
            }
            -->
        </SCRIPT>
	</head>
    <frameset rows="45,*" borderWidth="0" border="0">
        <frame src="topFrame.html" name="topFrame" scrolling="no">
        <frameset cols="200,*" name="underFrame">
            <frameset rows="40, *" name="leftFrame">
                <frame src="logoFrame.html" name="logoFrame" scrolling="no">
                <frame src="menuFrame.html" name="menuFrame" scrolling="yes">
            </frameset>
            <frame src="${req.getParameter('url')?default('index.html')}" name="mainFrame" scrolling="yes">
        </frameset>
    </frameset>
</html>