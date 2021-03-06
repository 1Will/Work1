<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.HtmlUtil"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="java.util.Map"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${keywords }_学校_微课慕课网-分享资源，传递价值</title>
<link rel="stylesheet" href="/skin/index/css/school.css" type="text/css"  />
<script type="text/javascript">
function changeSelectValue(value, type){
	if(type == '1'){
		document.getElementById('area1').value = value;
		document.getElementById('area2').value = '';
		document.getElementById('area3').value = '';
	}
	if(type == '2'){
		document.getElementById('area2').value = value;
		document.getElementById('area3').value = '';
	}
	if(type == '3'){
		document.getElementById('area3').value = value;
	}
	
	document.getElementById('startcount').value = 0;
	document.searchForm.action = "/search.action";
	document.searchForm.submit();
}
function changeSelectType(type){
	document.getElementById('type').value = type;
	document.getElementById('startcount').value = 0;
	document.searchForm.action = "/search.action";
	document.searchForm.submit();
}
</script>
</head>

<body>
<form name="searchForm" method="get">
<input type="hidden" name="id" value="school"/>
<input type="hidden" name="type" id="type" value="${type }"/>
<input type="hidden" name="area1" id="area1" value="${area1 }"/>
<input type="hidden" name="area2" id="area2" value="${area2 }"/>
<input type="hidden" name="area3" id="area3" value="${area3 }"/>
<input type="hidden" name="startcount" id="startcount" value="0"/>
<input type="hidden" name="searchButton" id="searchButton" value="${searchButton }"/>
<input type="hidden" name="searchtools" id="searchtools" value="${searchtools }"/>
<%@ include file="top.jsp"%>
<div class="nav">
	<div class="nav_size">
		<a href="/search.action?id=vod&xueduan=&cxueduan=&subject=&grade=&knopoint=&startcount=0&searchButton=0&searchtools=1&orderby=1&keywords=${keywords }"><span>微课</span></a>
    	<a href="/search.action?id=doc&xueduan=&cxueduan=&subject=&grade=&knopoint=&startcount=0&searchButton=0&searchtools=1&orderby=1&keywords=${keywords }"><span>文档</span></a>
   		<a href="/search.action?id=user&area1=&area2=&area3=&startcount=0&searchButton=0&searchtools=1&keywords=${keywords }"><span>教师</span></a>
    	<span class="nav_size_font">学校</span>
    	<a href="/v-clist-12-0.htm" target="_blank"><span>大赛</span></a>
    </div>
</div>
<div class="content">
	<div class="head_nums_cont_outer">
    	<div class="subnav_right_div">
    		<span class="c-gap-left-samll search_tool_close" style="display:none;"  id="shouqi">
            	<img src="/skin/index/images/search_icon2.jpg" class="subnav_right_img" />
				<input onClick="tclick(1)" name="name" class="subnav_input"  type="button" value="收起工具">
			</span>
        </div>
		<div class="subnav_right_div"  id="sosuo" >
        		<img src="/skin/index/images/search_icon.png" class="subnav_right_img" />
       			<input onClick="tclick(2)" name="name" class="subnav_input" type="button"  value="搜索工具">
       	</div>
	</div>
    
    <div class="subnav" id="neirong1">
    	<font style="line-height:42px;font-size:12px;font-family:宋体;padding-left:10px;">(找到相关结果约<bean:write name="pagelist" property="totalCount" />个)</font>
   	</div>
    
    <div class="head_nums_cont_inner"  style="display:none;"  id="neirong2">
		<div class="search_tool_conter">
			<div class='diy_select'>
			<select onchange="changeSelectType(this.value)">
		      <option value="">学校性质不限</option>
		      <option value="2010" <logic:equal value="2010" name="type">selected="selected"</logic:equal>>基础教育</option>
		      <option value="2020" <logic:equal value="2020" name="type">selected="selected"</logic:equal>>职业教育</option>
		      <option value="2030" <logic:equal value="2030" name="type">selected="selected"</logic:equal>>高等教育</option>
		      <option value="2040" <logic:equal value="2040" name="type">selected="selected"</logic:equal>>继续教育</option>
		    </select>
		    </div>
			<div class='diy_select'>
			<select onchange="changeSelectValue(this.value, '1')">
		      <option value="">省市地区不限</option>
		      <%
		      String area1 = (String)request.getAttribute("area1");
		      List arealist1 = (List)request.getAttribute("arealist1");
		      List arealist2 = (List)request.getAttribute("arealist2");
		      List arealist3 = (List)request.getAttribute("arealist3");
		      GpwAreaInfo gai = null;
		      for(int i=0, size=arealist1.size(); i<size; i++){
		    	  gai = (GpwAreaInfo)arealist1.get(i);
		      %>
		      <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(area1)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
		      <%} %>
		    </select>
		    </div>
		    <%if(arealist2 != null && arealist2.size() > 0){ %>
		    <div class='diy_select'>
		    <select onchange="changeSelectValue(this.value, '2')">
		      <option value="">请选择</option>
		      <%
		      String area2 = (String)request.getAttribute("area2");
		      if(arealist2 != null && arealist2.size() > 0){
		      for(int i=0, size=arealist2.size(); i<size; i++){
		    	  gai = (GpwAreaInfo)arealist2.get(i);
		      %>
		      <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(area2)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
		      <%}} %>
		    </select>
		    </div>
		    <%} %>
		    <%if(arealist3 != null && arealist3.size() > 0){ %>
		    <div class='diy_select'>
		    <select onchange="changeSelectValue(this.value, '3')">
		      <option value="">请选择</option>
		      <%
		      
		      String area3 = (String)request.getAttribute("area3");
		      if(arealist3 != null && arealist3.size() > 0){
		      for(int i=0, size=arealist3.size(); i<size; i++){
		    	  gai = (GpwAreaInfo)arealist3.get(i);
		      %>
		      <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(area3)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
		      <%}} %>
		    </select>
		    </div>
		    <%} %>
		</div>
    </div>
    <script type="text/javascript">
	function tclick(parem) {
		document.getElementById('searchtools').value = parem;
		if(1==parem){
	    	document.getElementById("shouqi").style.display="none";	
		  	document.getElementById("sosuo").style.display="block"; 
		}else if(2==parem){
			var sosuo=document.getElementById("sosuo");
			sosuo.style.display="none";
			document.getElementById("shouqi").style.display="block";	
		}
		var item;
		for ( var loop = 1; loop <=2; loop++) {
			item = document.getElementById("neirong"+loop);
			if (loop == parem){
				item.style.visibility = "visible";
				item.style.display="";
			}else{
				item.style.visibility = "hidden";
				item.style.display="none";
			}
		}
	}
	tclick(${searchtools});
	</script>

    <div class="content_left">
    	<%
    	List<String> keywordList = (List<String>)request.getAttribute("keywordList");
    	Map<String, String> keywordMap = (Map<String, String>)request.getAttribute("keywordMap");
    	PageList pagelist = (PageList)request.getAttribute("pagelist");
    	List datalist = pagelist.getDatalist();
    	if(datalist != null && datalist.size() > 0){
    	SysUnitInfo unitInfo = null;
    	String keys = null;
    	String unitname = null;
    	String descript = null;
    	String descripttitle = null;
    	for(int i=0, size=datalist.size(); i<size; i++){
    		unitInfo = (SysUnitInfo)datalist.get(i);
    		unitname = unitInfo.getUnitname();
    		descript = HtmlUtil.getHtmlText(unitInfo.getDescript());
    		descripttitle = descript;
    		for(int m=0, n=keywordList.size(); m<n; m++){
    			keys = keywordList.get(m);
    			if(unitname.indexOf(keys) != -1){
    				unitname = unitname.replaceAll(keys, keywordMap.get(keys));
    			}
    			if(descript.indexOf(keys) != -1){
    				if(descript.indexOf(keys) > 150){
    					descript = descript.substring(descript.indexOf(keys));
    				}
    				descript = descript.replaceAll(keys, keywordMap.get(keys));
    			}
    		}
    	%>
    	<div class="content_left_module">
        	<div class="content_left_module_title">
            	<a href="/html/<%=unitInfo.getUnitid() %>.htm" target="_blank"><p><%=unitname %></p></a>
            </div>
            <div class="content_left_module_main">
            	<a href="/html/<%=unitInfo.getUnitid() %>.htm" target="_blank"><img src="<%=unitInfo.getSketch() %>" class="content_left_module_main_img" width="150" height="90"/></a>
                <div class="content_left_module_main_font">
                    <span>简介：</span>
                    <span class="content_left_module_main_font_p" title="<%=descripttitle %>"><%=descript %></span>
                </div>
            </div>
        </div>
        <%}}else{ %>
        <div class="content_left_module" style="background-color:#fff;">抱歉，没有找到您需要的结果，请尝试更换搜索关键字试试！</div>
        <%} %>
      <%@ include file="page.jsp"%>
    </div> 
    
    <div class="content_right">
    	<div class="content_right_module">
        	<div class="content_right_module_title">
            	<p>相关微课</p>
           	</div>
            <div class="content_right_module_main">
            	<%
            	List aboutVodList = (List)request.getAttribute("aboutVodList");
            	Object[] vodObj = null;
            	for(int i=0, size=aboutVodList.size(); i<size; i++){
            		vodObj = (Object[])aboutVodList.get(i);
            	%>
            	<div class="content_right_module_main_<%=i+1 %>">
                	<a href="/v-play-<%=vodObj[3] %>-<%=vodObj[0] %>.htm" target="_blank"><%if(vodObj[2].toString().startsWith("http://")){ %><img src="<%=vodObj[2] %>" width="118" height="70" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=vodObj[2] %>" width="118" height="70" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %></a>
                    <a href="/v-play-<%=vodObj[3] %>-<%=vodObj[0] %>.htm" target="_blank"><img src="/skin/index/images/right_icon1.png" class="content_right_module_main_1_img<%=i+1 %>" /></a>
                	<a href="/v-play-<%=vodObj[3] %>-<%=vodObj[0] %>.htm" target="_blank" title="<%=vodObj[1] %>"><p><%=SubStringDirectiveModel.subString(vodObj[1].toString(), 16, "...") %></p></a>
                </div>
                <%} %>
            </div>
        </div>
        
        <div class="content_right_module">
        	<div class="content_right_module_title">
            	<p>相关文档</p>
           	</div>
            <div class="content_right_module_main">
            	<%
            	List aboutDocList = (List)request.getAttribute("aboutDocList");
            	Object[] docObj = null;
            	for(int i=0, size=aboutDocList.size(); i<size; i++){
            		docObj = (Object[])aboutDocList.get(i);
            	%>
            	<div class="content_right_module2_main_<%=i+1 %>">
                	<a href="/v-doc-<%=docObj[3] %>-<%=docObj[0] %>.htm" target="_blank"><img src="<%=docObj[2] %>" width="67" height="82"/></a>
                	<a href="/v-doc-<%=docObj[3] %>-<%=docObj[0] %>.htm" target="_blank" title="<%=docObj[1] %>"><p><%=SubStringDirectiveModel.subString(docObj[1].toString(), 8, "...") %></p></a>
                </div>
                <%} %>
            </div>
        </div>
        
         <div class="content_right_module size">
        	<div class="content_right_module_title">
            	<p>相关用户</p>
           	</div>
            <div class="content_right_module_main">
            	<%
            	List aboutUserList = (List)request.getAttribute("aboutUserList");
            	Object[] userObj = null;
            	for(int i=0, size=aboutUserList.size(); i<size; i++){
            		userObj = (Object[])aboutUserList.get(i);
            	%>
            	<div class="content_right_module3_main_<%=i+1 %>">
                	<a href="/v-user-<%=userObj[3] %>-<%=userObj[0] %>.htm" target="_blank"><img src="<%=userObj[2] %>" width="75" height="75"/></a>
                	<a href="/v-user-<%=userObj[3] %>-<%=userObj[0] %>.htm" target="_blank"><p><%=SubStringDirectiveModel.subString(userObj[1].toString(), 9, "...") %></p></a>
                </div>
                <%} %>
            </div>
        </div>
        
    </div>
     
</div>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>