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
<#-- $Id: -->
<#include "includes/eam2008.ftl" />
<@htmlPage title="${action.getText('help.title')}">
<style type="text/css">
body{font: 76%/1.5 Arial,sans-serif;background: #FFF;color: #333}
div#container{width: 500px;margin:0 auto}
h1{color: #3CA3FF;margin: 1em 0 0;letter-spacing: -2px}
p{margin: 0 0 1.7em}
a{color: #F60;font-weight:bold}
a:hover{color: #F00}
.tooltip{
width: 200px; color:#000;
font:lighter 11px/1.3 Arial,sans-serif;
text-decoration:none;text-align:center}.tooltip span.top{padding: 30px 8px 0;
     background: url(http://www.makewing.com/images/uppic/200708171844190.gif) no-repeat top}.tooltip b.bottom{padding:3px 8px 15px;color: #548912;
     background: url(http://www.makewing.com/images/uppic/200708171844190.gif) no-repeat bottom}
</style>
<div class="login-left">
           <div class="login-left-logo">
				<img height="273" src="${req.contextPath}/stylesheets/images/help11.bmp"  width="780"/>
			</div>
			<div class="login-left-content">
			</div>
			<div class="login-left-sever">
	      <@listTable>
	      <tr>
	      <th>${action.getText('具体控件')}</th>
	       <th>${action.getText('控件功能描述')}</th>
	      </tr>
	              <tr>
                      <td class="alignLeft">用户名输入框:</td>
		               <td class="alignLeft">
		                 ${action.getText('product.username')}
		                </td>
		          </tr>
		          <tr>
		            <td class="alignLeft">密码输入框:</td>
		             <td class="alignLeft">
		                ${action.getText('product.password')}
		            </td>
		           </tr>
		            <tr>
		            <td class="alignLeft">登陆按扭:</td>
		              <td class="alignLeft">
		                ${action.getText('login.button')}
		            </td>
		            </tr>
		            <tr>
		            <td class="alignLeft">清除按扭:</td>
		             <td class="alignLeft">
		               ${action.getText('clean.botton')}
		            </td>
		           </tr>
		           <tr>
		            <td class="alignLeft">忘记密码:
		            </td>
		              <td class="alignLeft">
		             ${action.getText('foreget.passsword')}
		            </td>
		            </tr>
		            <tr>
		            <td class="alignLeft">永君技术支持:
		            </td>
		              <td class="alignLeft">
		             ${action.getText('compay.tech')}
		            </td>
		            </tr>
	      </@listTable> 
	      <#-- <a href="#" onclick="popupModalDialog('${req.contextPath}/popup/helpFristSelector.html', 1200, 1000);return false;">
					<img height="16" width="16" src="${req.contextPath}/stylesheets/images/icon-demo.gif" align="absMiddle"/>点击下一步进入系统首页帮助文档
				</a>-->
</@htmlPage>

