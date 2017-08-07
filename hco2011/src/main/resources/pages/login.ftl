<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3c.org/tr/1999/REC-html401-19991224/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<#include "includes/macros.ftl" />

<head id="head1">
	<title id="Title_CPLogin">客户关系管理系统登录</title>
	<meta http-equiv="Content-Type" content="text/html;">
	<SCRIPT LANGUAGE="JavaScript">
            // this page should never load inside of another frame
            if (top.location != self.location){
                top.location = self.location;
            }
     </SCRIPT>
     
<style type="text/css">
body,td,th {
	font-family: 宋体;
	font-size: 12px;
	color: #333333;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background: url(${req.contextPath}/stylesheets/images/1-1.jpg) no-repeat;
}
.f1 {
	font-family: "宋体";
	font-size: 14px;
	color: #666666;
	text-decoration: none;
	border: 1px solid #508FD6;
}
.f2 {
	font-family: "宋体";
	font-size: 14px;
	font-weight: bold;
	color: #508FD6;
	text-decoration: none;
}
.f3 {
	font-family: "宋体";
	font-weight: bold;
	color: #FF9900;
	text-decoration: none;
}
</style>

</head>
<body>
<center>
<form name="'login'" action="security_check" method="'post'">
<table width="100%" height="544" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="544" align="right" >
      <table width="694" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td width="70">
            
            </td>
          <td width="356" align="right"  style="background:url(${req.contextPath}/stylesheets/images/1-6.jpg) no-repeat">
           <table width="100%" height="70" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td>　</td>
            </tr>
          </table>
            <table width="100%" height="228" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center" valign="top">
                <table width="100%" height="62" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="62">　</td>
                  </tr>
                </table>
                
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="26%" height="39" align="right" valign="middle">用户名：</td>
                        <td width="59%" align="left" valign="middle"><label>
                         <input id="txtUserName" name="j_username" value="${Session.j_username?if_exists}" type="text" class="f1" size="30" />
                        </label></td>
                        <td width="15%" align="left" valign="middle">　</td>
                      </tr>
                      <tr>
                        <td height="35" align="right" valign="middle">密码：</td>
                        <td align="left" valign="middle"><input id="txtPassword" type="password" name="j_password" class="f1" size="30" /></td>
                        <td align="left" valign="middle">　</td>
                      </tr>
                    </table>
                    <table width="74%" height="20" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="24%" align="right" valign="middle"><label>
                          <input type="checkbox" name="checkbox" value="checkbox" />
                        </label></td>
                        <td width="76%" align="left" valign="middle">记住用户名</td>
                      </tr>
                    </table>
                    <table width="64%" height="30" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="30" align="center" valign="middle"><label>
                          <input name="imgbtn" type="image" src="${req.contextPath}/stylesheets/images/2-1.jpg" border="0" onClick="javascript:this.form.submit()">
                        </label></td>
                        <td align="center" valign="middle" class="f3">忘记密码</td>
                      </tr>
                    </table></td>
            
              </tr>
            </table></td>
            <td width="224">
            
            </td>
        </tr>
      </table>
      </td>
  </tr>
</table>

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

</script>
</form>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="33" align="center" ><font color="#000000" size="1"><strong>Copyright 2009-2017 Yongjun Technology All Rights Reserved 合肥天鹅制冷科技有限公司版权所有</strong></font></td>
        </tr>
      </table>
</center>

</body>
</html>
