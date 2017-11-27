<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.gpw.bo.GpwPhotoAlbum"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/lanrentuku.css" rel="stylesheet" type="text/css" />
<script src="/skin/desktop/js/image.js" type=text/javascript></script>
<script>
function changeType(ftag){
	document.getElementById('ftag').value = ftag;
	document.pageForm.action = '/pcenter.do?method=vod';
	document.pageForm.submit();
}
</script>
</head>
<body style="background:none;">
<form name="pageForm" method="post">
<div id="B7">

    <div class="ybbox">
        <ul>
            <li id="yb1" onclick="changeType('2')" <logic:equal value="2" name="ftag">class="hover"</logic:equal> style="cursor:pointer;">推荐图片</li>
            <li id="yb2" onclick="changeType('1')" <logic:equal value="1" name="ftag">class="hover"</logic:equal> style="cursor:pointer;">推荐视频</li>
        </ul>
    </div>
    <div class="conbox">
    	<logic:equal value="2" name="ftag">
        <div id="con_yb_1">
            <div class="rollBox">
                <div><a class="LeftBotton" onmousedown="GoUp('JS_Cont','List1')" onmouseup="StopUp('JS_Cont',106)" href="javascript:void(0);"></a></div>
                <div class="Cont" id="JS_Cont">
                <div class="ScrCont">
                    <div id="List1">
                      <!-- 图片列表 begin（请上传4的整数倍图片） -->
                      <%
                      	List list = (List)request.getAttribute("list");
                      	GpwPhotoAlbum model = null;
                      	String title = null;
                      	for(int i=0; i<list.size(); i++){
                      		model = (GpwPhotoAlbum)list.get(i);
                      		title = model.getAlbumname();
                      		if(title.length() > 7) title = title.substring(0, 7);
                      %>
                      <%if(i == 0){ %>
                      <div class="pic first" style="_margin-right:17px;"><a href="/photo.do?method=info&objid=<%=model.getAlbumid() %>" target="_blank" title="<%=model.getAlbumname() %>"><img src="<%=model.getPhotosl() %>" width="86" height="120" border="0"/></a><span><a href="/photo.do?method=info&objid=<%=model.getAlbumid() %>" target="_blank"><%=title %></a></span></div>
                      <%} %>
                      <%if(i > 0 && i < (list.size()-1)){ %>
                      <div class="pic"><a href="/photo.do?method=info&objid=<%=model.getAlbumid() %>" target="_blank" title="<%=model.getAlbumname() %>"><img src="<%=model.getPhotosl() %>" width="86" height="120" border="0"/></a><span><a href="/photo.do?method=info&objid=<%=model.getAlbumid() %>" target="_blank"><%=title %></a></span></div>
                      <%} %>
                      <%if(i == list.size()-1){ %>
                      <div class="pic last" style="_margin-right:8px;"><a href="/photo.do?method=info&objid=<%=model.getAlbumid() %>" target="_blank" title="<%=model.getAlbumname() %>"><img src="<%=model.getPhotosl() %>" width="86" height="120" border="0"/></a><span><a href="/photo.do?method=info&objid=<%=model.getAlbumid() %>" target="_blank"><%=title %></a></span></div>
					  <%}} %>
                      <!-- 图片列表 end -->
                    </div>
                    <div id="List2"></div>
                  </div>
                </div>
                <div><a class="RightBotton" onmousedown="GoDown('JS_Cont','List1')" onmouseup="StopDown('JS_Cont',106)" href="javascript:void(0);"></a></div>
               <div class="high01"></div>
          </div>
          <script language="javascript" type="text/javascript">
                <!--//--><![CDATA[//><!--
                //图片滚动列表 mengjia 070816
                scrollImg('JS_Cont','List1','List2');
                //--><!]]>
            </script>
        </div>
        </logic:equal>
        <logic:equal value="1" name="ftag">
        <div id="con_yb_2">
           
		   <div id="tptj">
		   <ul>
		   <logic:iterate id="model" name="list">
		   <%-- <li>·<a href="/vod-vi-<bean:write name="model" property="unitid"/>-<bean:write name="model" property="filmid"/>.htm" target="_blank"><bean:write name="model" property="title"/></a></li>--%>
		   <li>·<a href="/v001-play-<bean:write name="model" property="unitid"/>-<bean:write name="model" property="filmid"/>.htm" target="_blank"><bean:write name="model" property="title"/></a></li>
		   </logic:iterate>
		   </ul>
		   </div>
		   
        </div>
        </logic:equal>
    </div>

</div>
<input type="hidden" name="ftag" id="ftag" value=""/>
</form>
</body>
</html>