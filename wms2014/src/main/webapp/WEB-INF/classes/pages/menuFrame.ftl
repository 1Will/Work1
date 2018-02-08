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
<#--$Id: menuFrame.ftl 7925 2007-10-22 02:37:55Z qsun $ -->

<#assign menu=JspTaglibs["/WEB-INF/tld/struts-menu.tld"] />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/menuExpandable.css" />
        <script type="text/javascript" src="${req.contextPath}/javascripts/menuExpandable.js">
        </script>
	</head>
	<script>
		function shleft(){
		 if (parent.leftFrame.cols=="0,*"){
		 alert("显示");
		  parent.leftFrame.cols="40,*,"
		 }
		 else{
		 alert("隐藏");
		  parent.leftFrame.cols="0,*"
		  parent.leftFrame.rows="0,*";
		  parent.underFrame.cols="0,*"
		 }
		}
	</script>
	<body>
			<#--<table border="0" cellPadding="0" cellSpacing="0" height="100%">
              <tr>
                <td id="line" align="center" style="width: 8px;HEIGHT: 100%;cursor:hand" bgColor="#588FC7" LANGUAGE="javascript" onclick=""><img id="pic" src="images/jt_left.gif" value=0 WIDTH="6" HEIGHT="8" title="隐藏">
            -->
            <@menu.useMenuDisplayer name="ListMenu" permissions="filterPermissionsAdapter"            
                locale="${localeKey}" bundle="${bundleKey}">
                <#list menuNames as menuName>
                    <@menu.displayMenu name="${menuName}"/>
                </#list>
                <script type="text/javascript">
                <!--
                    initializeMenus();
                //-->
                </script>
            </@menu.useMenuDisplayer>
            <#--</td>
            </tr>
            </table>-->
	</body>
</html>