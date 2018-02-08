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
<#include "includes/macros.ftl"/>

<html>
<head>
    <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css"/>
</head>

<body>
<@messageBar title="${action.getText('exception')}"/>
<table class="wwFormTable" style="table-layout: fixed; word-wrap: break-word">
    <tr>
    <#--
        <td class="title">${exception.message?if_exists}</td>
        --->
        <td class="title">抱歉，您遇到了一个系统错误！</td>
    </tr>
    <tr>
      <td class="title">请联系永君科技的技术支持，并描述您所作的操作，联系电话：0551-5392638</td>
    </tr>
    <tr>
        <td class="input">
<div style="width: 100%; height: 300px; overflow: auto; display: none; " id="detailException">
<#--
<div align="right"><a href="#" onclick="detailException.style.display='none'; faceException.style.display='block'; return false;">${action.getText('error.summary')}</a></div>
<pre>
<#macro printException displayException>
    ${displayException}
    <#assign stackTraces = displayException.stackTrace/>
    <#list stackTraces as stackTrace>
        ${stackTrace}
    </#list>
    <#if displayException.cause?exists>
        <br/>
        Cause By:
        <@printException displayException=displayException.cause/>
    </#if>
</#macro>
    <@printException displayException=exception/>
</pre>
-->
</div><div style="width: 100%; height: 300px; overflow: auto" id="faceException">
<div align="right">
    <#--
	<a href="#" onclick="detailException.style.display='block'; faceException.style.display='none'; return false;">${action.getText('error.detail')}</a>
    -->
</div>
<div align="center"><img src="${req.contextPath}/images/error.gif"></div>
</div></td>
    </tr>
</table>
</body>