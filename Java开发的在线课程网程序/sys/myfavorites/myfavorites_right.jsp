<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript" language=javascript>
  function click1(){
   var frame=document.getElementById("frame");
   var tab1=document.getElementById("a1");
   var tab2=document.getElementById("a2");
   tab1.setAttribute("class", "lik_1");
   tab2.setAttribute("class", "");
   frame.src="/sysMyFavoritesAction.do?method=list&fid=${fid}&filetype=2";
   
  }
  function click2(){
  var frame=document.getElementById("frame");
   var tab1=document.getElementById("a1");
   var tab2=document.getElementById("a2");
   tab1.setAttribute("class", "");
   tab2.setAttribute("class", "lik_1");
      frame.src="/sysMyFavoritesAction.do?method=list&fid=${fid}&filetype=1";
  }
  
 window.onload = function play(){
     var temp="<%=session.getAttribute("filetype")%>";
     if(temp==2){
      var frame=document.getElementById("frame");
      var tab1=document.getElementById("a1");
      var tab2=document.getElementById("a2");
      tab1.setAttribute("class", "lik_1");
      tab2.setAttribute("class", "");
      frame.src="/sysMyFavoritesAction.do?method=list&fid=${fid}&filetype=2";
   }else if(temp==1){
        var frame=document.getElementById("frame");
        var tab1=document.getElementById("a1");
        var tab2=document.getElementById("a2");
        tab2.setAttribute("class", "lik_1");
        tab1.setAttribute("class", "");
        frame.src="/sysMyFavoritesAction.do?method=list&fid=${fid}&filetype=1";
   }else if(temp=="null"||temp==""){
     var tab1=document.getElementById("a1");
     tab1.setAttribute("class", "lik_1");
   }
  }

</script>
<html>

<body scroll="no">
<div class="detailed clearfix back_1">
	<ul class="ul_1">
		<li id="tab1" onclick="click1();"><a id="a1">我的微课</a>
		</li>
		<li id="tab2" onclick="click2();" ><a id="a2" >我的文库</a>
		</li>
	</ul>
</div>
<div style="width: 100%;height: 100%;">
<iframe id="frame" style="width: 100%;height: 100%;'" frameborder=”no” border=”0″ src="/sysMyFavoritesAction.do?method=list&filetype=${filetype }&fid=${fid}"></iframe>
</div>
</body>
</html>