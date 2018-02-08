<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3c.org/tr/1999/REC-html401-19991224/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<#include "includes/macros.ftl" />

<head id="head1">
	<title id="Title_CPLogin">WMS2014登录</title>
	<meta http-equiv="Content-Type" content="text/html;">
	<link href="${req.contextPath}/stylesheets/loading.css" type="text/css" rel="stylesheet"/>
	<link href="${req.contextPath}/stylesheets/layout.css" type="text/css" rel="stylesheet"/>
	<link href="${req.contextPath}/stylesheets/login.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>
	<SCRIPT LANGUAGE="JavaScript">
            // this page should never load inside of another frame
            if (top.location != self.location){
                top.location = self.location;
            }
     </SCRIPT>
     
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
     
     
</head>

	
<body>

<center>

<form name="'login'" action="security_check" method="'post'">

<div id="position">
	<div id="inposition">
		<div class="login-left">
			<div class="login-left-logo">
				<img height="78" src="${req.contextPath}/stylesheets/images/login_logo.gif" width="297"/>
			</div>
			<div class="login-left-content">
				<ul>
					<li>安全稳定的J2EE操作平台</li>
					<li>全面规范化仓库管理体系</li>
				</ul>
			</div>
			<div class="login-left-sever">

				

			<#--
			<a href="" onclick="popupModalDialog('${req.contextPath}/popup/helpSelector.html', 800, 600);return false;">
				<a href="#" onclick="popupModalDialog('${req.contextPath}/popup/helpSelector.html', 800, 600);return flase;" target="_blank">

					<img height="16" width="16" src="${req.contextPath}/stylesheets/images/icon-demo.gif" align="absMiddle"/>帮助 
				</a>
			-->
			    <a href="#" onclick="popupModalDialog('${req.contextPath}/popup/helpSelector.html', 800, 600);return false;">
					<img height="16" width="16" src="${req.contextPath}/stylesheets/images/icon-demo.gif" align="absMiddle"/>帮助 
				</a>
			<#--
				<a href="javascript:void(0);">
					<img height="16" width="16" src="${req.contextPath}/stylesheets/images/icon-login-seaver.gif" align="absMiddle"/> 永君技术支持
				</a>
			 -->
			 	<#--<a  onclick="javascript:alert('${action.getText('yj.technogy.support')}' + '\n' + '${action.getText('yj.technogy.tel')}' + '\n' + '${action.getText('yj.technogy.email')}');return false;" href="">
					<img height="16" width="16" src="${req.contextPath}/stylesheets/images/icon-login-seaver.gif" align="absMiddle"/> 永君技术支持
				</a>-->
				<a href="#" onclick="popupModalDialog('${req.contextPath}/popup/technogySupport.html', 800, 600);return false;">
					<img height="16" width="16" src="${req.contextPath}/stylesheets/images/icon-demo.gif" align="absMiddle"/>永君技术支持 
				</a>
			</div>
		</div>
		<div class="login-right">
			<div id="UpdatePanel1">
				<div class="login-right-title">欢迎登录乘用车制造公司备件库管理信息系统 </div>
				<div class="login-right-input">
					<table cellSpacing="0" cellPadding="0" width="200" border="0">
					<#--
					    <#assign hasErrors = actionErrors?exists && actionErrors.size() gt 0 />
					    <#assign messageNumber = 0 />
					    <#if hasErrors>
                          <tbody>
                            <#assign messageNumber = messageNumber + actionErrors.size()/>
                              <tr>
	                            <td style="height: 30px" align="right">
	                            </td>
                                <td>
                                  <font id="errorMessage" color="red">${actionErrors.get(0)}</font>
                                </td>
                              </tr>
                          </tbody>
                        </#if>
                        -->
						<tbody>
						   <tr>
                                <td colspan="3" align="center"><font id="errorMessage" color="red">${message?if_exists}</font></td>
                           </tr>
							<tr>
								<td id="tdLeft" noWrap align="right">&nbsp;&nbsp;&nbsp;&nbsp;用户名&nbsp;:&nbsp; </td>
								<td>
									<input class="input" id="txtUserName" name="j_username" value="${Session.j_username?if_exists}">
								</td>
							</tr>
							<tr>
								<td style="height: 30px" align="right">密&nbsp;&nbsp;&nbsp;码&nbsp;:&nbsp; </td>
								<td style="height: 30px">
									<input class="input" id="txtPassword" type="password" name="j_password">
								</td>
							</tr>
							<tr>
								<td style="height: 30px" align="right" />
								<td id="forgotPasswd" style="height: 30px" noWrap>
									<input language="javascript" class="button" id="btnLogin" type="submit" value="登 录" name="btnLogin"/>
									<input language="javascript" class="button" id="btnLogin" onclick="return resetPassword();" tabIndex="5" type="submit" value="清 除" name="btnTest"/>
									<a  onclick="javascript:alert('${action.getText('please.contact.administrator')}');return false;" href="">
										<img height="16" width="12" src="${req.contextPath}/stylesheets/images/login-key.gif" align="absMiddle"/> 忘记密码 
									</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>			
		</div>
	</div>
</div>
<SCRIPT LANGUAGE="JavaScript">
	function resetPassword() {
		var passControl = document.all("j_password");
		passControl.value="";
		return false;
	}
	
</SCRIPT>

<script language="javascript">
function helpOpendialog(){
    var url = "${req.contextPath}/popup/helpSelector.html";
    alert(url);
    popupModalDialog(url, 800, 600);
     alert(url);
 }
function enableTooltips(id){

  var links,i,h;
  if(!document.getElementById || !document.getElementsByTagName) return;
  AddCss();
  h=document.createElement("span");
  h.id="btc";
  h.setAttribute("id","btc");
  h.style.position="absolute";
  document.getElementsByTagName("body")[0].appendChild(h);
  if(id==null) {
    links=document.getElementsByTagName("a");
  } else {
    links=document.getElementById(id).getElementsByTagName("a");
  }
  for(i=0;i<links.length;i++){
	Prepare(links[i]);
  }
}

function Prepare(el){
  var tooltip,t,b,s,l;
  t=el.getAttribute("title");
  if(t==null || t.length==0) {
    t="link:";
  }
  el.removeAttribute("title");
  tooltip=CreateEl("span","tooltip");
  s=CreateEl("span","top");
  s.appendChild(document.createTextNode(t));
  tooltip.appendChild(s);
  b=CreateEl("b","bottom");
  l=el.getAttribute("href");
  if(l.length>30) {
    l=l.substr(0,27)+"...";
  }
  b.appendChild(document.createTextNode(l));
  tooltip.appendChild(b);
  setOpacity(tooltip);
  el.tooltip=tooltip;
  el.onmouseover=showTooltip;
  el.onmouseout=hideTooltip;
  el.onmousemove=Locate;
}

function showTooltip(e){
  document.getElementById("btc").appendChild(this.tooltip);
  Locate(e);
}

function hideTooltip(e){
  var d=document.getElementById("btc");
  if(d.childNodes.length>0) {
    d.removeChild(d.firstChild);
  }
}

function setOpacity(el){
  el.style.filter="alpha(opacity:95)";
  el.style.KHTMLOpacity="0.95";
  el.style.MozOpacity="0.95";
  el.style.opacity="0.95";
}

function CreateEl(t,c){
  var x=document.createElement(t);
  x.className=c;
  x.style.display="block";
  return(x);
}

function AddCss(){
  var l=CreateEl("link");
  l.setAttribute("type","text/css");
  l.setAttribute("rel","stylesheet");
  l.setAttribute("href","?.css");
  l.setAttribute("media","screen");
  document.getElementsByTagName("head")[0].appendChild(l);
}

function Locate(e){
  var posx=0,posy=0;
  if(e==null) e=window.event;
  if(e.pageX || e.pageY){
    posx=e.pageX; posy=e.pageY;
  }
  else if(e.clientX || e.clientY){
    if(document.documentElement.scrollTop){
      posx=e.clientX+document.documentElement.scrollLeft;
      posy=e.clientY+document.documentElement.scrollTop;
    } else{
      posx=e.clientX+document.body.scrollLeft;
      posy=e.clientY+document.body.scrollTop;
    }
  }
  document.getElementById("btc").style.top=(posy+10)+"px";
  document.getElementById("btc").style.left=(posx-20)+"px";
}

//window.onload=function () {enableTooltips("forgotPasswd"); }
</script>



</form>

</center>

<div id="header">
	<div class="lan">
		&nbsp;</div>
</div>


<div id="content"><img src="${req.contextPath}/stylesheets/images/login-wel.gif"> </div>
<div id="buttom">
	<div class="copy2"><nobr>Copyright© 2014 YongJun Technology. All Rights Reserved.</nobr></div>
	<div class="copy"><img src="${req.contextPath}/stylesheets/images/login-copy.gif"/></div>
</div>

</body>

</html>
