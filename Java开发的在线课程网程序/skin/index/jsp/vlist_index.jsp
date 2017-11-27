<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.bzt.gpw.bo.GpwSearchHots"%>
<%@page import="java.util.Map"%>
<%@page import="com.bzt.sys.util.HtmlUtil"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.edu.bo.EduKnopointInfo"%>
<%@page import="com.bzt.edu.bo.EduSubjectInfo"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${keywords }_微课_微课慕课网-分享资源，传递价值</title>
<link rel="stylesheet" href="/skin/index/css/class.css" type="text/css"  />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function searchOrderby(orderby){
	document.getElementById('orderby').value = orderby;
	document.searchForm.action = "/search.action";
	document.searchForm.submit();
}
function changeSelectValue(value, type){
	if(type == '1'){
		if(value == '41'){
			document.getElementById('xueduan').value = '4';
			document.getElementById('cxueduan').value = '41';
		}else if(value == '42'){
			document.getElementById('xueduan').value = '4';
			document.getElementById('cxueduan').value = '42';
		}else{
			document.getElementById('xueduan').value = value;
			document.getElementById('cxueduan').value = '0';
		}
		document.getElementById('subject').value = '';
		document.getElementById('grade').value = '';
		document.getElementById('knopoint').value = '';
	}
	if(type == '2'){
		document.getElementById('subject').value = value;
		document.getElementById('grade').value = '';
		document.getElementById('knopoint').value = '';
	}
	if(type == '3'){
		document.getElementById('grade').value = value;
		document.getElementById('knopoint').value = '';
	}
	if(type == '4'){
		document.getElementById('knopoint').value = value;
		document.getElementById('grade').value = '';
	}
	
	document.getElementById('startcount').value = 0;
	document.searchForm.action = "/search.action";
	document.searchForm.submit();
}
function searchKeywords(keywords){
	document.getElementById('keywords').value = keywords;
	search('1');
}
</script>

</head>

<body>
<form name="searchForm" method="get">
<input type="hidden" name="id" value="vod"/>
<input type="hidden" name="xueduan" id="xueduan" value="${xueduan }"/>
<input type="hidden" name="cxueduan" id="cxueduan" value="${cxueduan }"/>
<input type="hidden" name="subject" id="subject" value="${subject }"/>
<input type="hidden" name="grade" id="grade" value="${grade }"/>
<input type="hidden" name="knopoint" id="knopoint" value="${knopoint }"/>
<input type="hidden" name="startcount" id="startcount" value="0"/>
<input type="hidden" name="searchButton" id="searchButton" value="${searchButton }"/>
<input type="hidden" name="orderby" id="orderby" value="${orderby }"/>
<input type="hidden" name="searchtools" id="searchtools" value="${searchtools }"/>
<%@ include file="top.jsp"%>
<div class="nav">
	<div class="nav_size">
		<span class="nav_size_font">微课</span>
    	<a href="/search.action?id=doc&xueduan=${xueduan }&cxueduan=${cxueduan }&subject=${subject }&grade=${grade }&knopoint=${knopoint }&startcount=0&searchButton=0&searchtools=1&orderby=1&keywords=${keywords }"><span>文档</span></a>
   		<a href="/search.action?id=user&area1=&area2=&area3=&startcount=0&searchButton=0&searchtools=1&keywords=${keywords }"><span>教师</span></a>
    	<a href="/search.action?id=school&type=&area1=&area2=&area3=&startcount=0&searchButton=0&searchtools=1&keywords=${keywords }"><span>学校</span></a>
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
		<a href="javascript:searchOrderby('1')"><span class="subnav_span1<logic:equal value="1" name="orderby"> hover</logic:equal>">最新</span></a>
   		<a href="javascript:searchOrderby('2')"><span class="subnav_span1<logic:equal value="2" name="orderby"> hover</logic:equal>">评分</span></a>
   		<a href="javascript:searchOrderby('3')"><span class="subnav_span2<logic:equal value="3" name="orderby"> hover</logic:equal>">浏览量</span></a>
   		<a href="javascript:searchOrderby('4')"><span class="subnav_span2<logic:equal value="4" name="orderby"> hover</logic:equal>">收藏量</span></a>
   		<a href="javascript:searchOrderby('9')"><span class="subnav_span1<logic:equal value="9" name="orderby"> hover</logic:equal>">精品</span></a>
   		<font style="line-height:42px;font-size:10px;font-family:宋体;">(找到相关结果约<bean:write name="pagelist" property="totalCount" />个)</font>
   	</div>
    
    <div class="head_nums_cont_inner"  style="display:none;"  id="neirong2">
		<div class="search_tool_conter">
			  <div class='diy_select'>
			  <select onchange="changeSelectValue(this.value, '1')">
				<option value="">学段不限</option>
				<option value="1" <logic:equal value="1" name="xueduan">selected="selected"</logic:equal>>小学教育</option>
				<option value="2" <logic:equal value="2" name="xueduan">selected="selected"</logic:equal>>初中教育</option>
				<option value="3" <logic:equal value="3" name="xueduan">selected="selected"</logic:equal>>高中教育</option>
				<option value="41" <logic:equal value="41" name="cxueduan">selected="selected"</logic:equal>>中职教育</option>
				<option value="42" <logic:equal value="42" name="cxueduan">selected="selected"</logic:equal>>高职教育</option>
				<option value="5" <logic:equal value="5" name="xueduan">selected="selected"</logic:equal>>高等教育</option>
				<option value="6" <logic:equal value="6" name="xueduan">selected="selected"</logic:equal>>继续教育</option>
			  </select>
			  </div>
			  <%
			  List typeList1 = (List)request.getAttribute("typeList1");
			  if(typeList1 !=null && typeList1.size() > 0){
			  %>
			  <div class='diy_select'>
			  <select onchange="changeSelectValue(this.value, '2')">
				<option value="">分类不限</option>
				<%
				String subject = (String)request.getAttribute("subject");
				VodFilmType vft = null;
				for(int i=0, size=typeList1.size(); i<size; i++){
					vft = (VodFilmType)typeList1.get(i);
				%>
				<option value="<%=vft.getTypeid() %>" <%if(vft.getTypeid().toString().equals(subject)){ %>selected="selected"<%} %>><%=vft.getTypename() %></option>
				<%} %>
			  </select>
			  </div>
			  <%} %>
			  <%
			  List typeList2 = (List)request.getAttribute("typeList2");
			  if(typeList2 !=null && typeList2.size() > 0){
			  %>
			  <div class='diy_select'>
			  <select onchange="changeSelectValue(this.value, '3')">
				<option value="">专业不限</option>
				<%
				String grade = (String)request.getAttribute("grade");
				VodFilmType vft = null;
				for(int i=0, size=typeList2.size(); i<size; i++){
					vft = (VodFilmType)typeList2.get(i);
				%>
				<option value="<%=vft.getTypeid() %>" <%if(vft.getTypeid().toString().equals(grade)){ %>selected="selected"<%} %>><%=vft.getTypename() %></option>
				<%} %>
			  </select>
			  </div>
			  <%} %>
			  <%
			  List subjectList = (List)request.getAttribute("subjectList");
			  if(subjectList !=null && subjectList.size() > 0){
			  %>
			  <div class='diy_select'>
			  <select onchange="changeSelectValue(this.value, '2')">
				<option value="">学科不限</option>
				<%
				String subject = (String)request.getAttribute("subject");
				EduSubjectInfo esi = null;
				for(int i=0, size=subjectList.size(); i<size; i++){
					esi = (EduSubjectInfo)subjectList.get(i);
				%>
				<option value="<%=esi.getSubjectid() %>" <%if(esi.getSubjectid().toString().equals(subject)){ %>selected="selected"<%} %>><%=esi.getSubjectname() %></option>
				<%} %>
			  </select>
			  </div>
			  <%} %>
			  <%
			  List knopointList = (List)request.getAttribute("knopointList");
			  if(knopointList !=null && knopointList.size() > 0){
			  %>
			  <div class='diy_select'>
			  <select onchange="changeSelectValue(this.value, '4')">
				<option value="">知识点不限</option>
				<%
				String knopoint = (String)request.getAttribute("knopoint");
				EduKnopointInfo eki = null;
				for(int i=0, size=knopointList.size(); i<size; i++){
					eki = (EduKnopointInfo)knopointList.get(i);
				%>
				<option value="<%=eki.getKnopointid() %>" <%if(eki.getKnopointid().toString().equals(knopoint)){ %>selected="selected"<%} %>><%for(int j=0;j<(eki.getKnopointno().length()-8)/4;j++){%> ├<%}%><%=eki.getTitle() %></option>
				<%} %>
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
    	Object[] object = null;
    	String[] objKeywords = null;
    	String keys = null;
    	String title = null;
    	String author = null;
    	String unitname = null;
    	String descript = null;
    	for(int i=0, size=datalist.size(); i<size; i++){
    		object = (Object[])datalist.get(i);
    		title = object[1].toString();
    		author = object[2].toString();
    		unitname = object[5].toString();
    		descript = HtmlUtil.getHtmlText(object[13].toString());
    		for(int m=0, n=keywordList.size(); m<n; m++){
    			keys = keywordList.get(m);
    			if(title.indexOf(keys) != -1){
    				title = title.replaceAll(keys, keywordMap.get(keys));
    			}
    			if(author.indexOf(keys) != -1){
    				author = author.replaceAll(keys, keywordMap.get(keys));
    			}
    			if(unitname.indexOf(keys) != -1){
    				unitname = unitname.replaceAll(keys, keywordMap.get(keys));
    			}
    			if(descript.indexOf(keys) != -1){
    				if(descript.indexOf(keys) > 30){
    					descript = descript.substring(descript.indexOf(keys));
    				}
    				descript = descript.replaceAll(keys, keywordMap.get(keys));
    			}
    		}
    	%>
    	<div class="content_left_module"  onMouseOut="out();" onMouseMove="move();">
        	<div class="content_left_module_title">
            	<a href="/v-play-<%=object[4] %>-<%=object[0] %>.htm" target="_blank"><p><%=title %></p></a>
            </div>
            <div class="content_left_module_main">
            	<a href="/v-play-<%=object[4] %>-<%=object[0] %>.htm" target="_blank"><%if(object[3].toString().startsWith("http://")){ %><img src="<%=object[3] %>" width="150" height="90" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=object[3] %>" width="150" height="90" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %></a>
                <a href="/v-play-<%=object[4] %>-<%=object[0] %>.htm" target="_blank"><img src="/skin/index/images/icon.png" class="content_left_module_main_icon" /></a>
                <div class="content_left_module_main_font">
                	<a href="/v.bo?method=actors&filmid=<%=object[0] %>&actors=<%=URLEncoder.encode(object[2].toString().trim(),"utf-8") %>" target="_blank"><span class="content_left_module_main_font_span"><%=author %></span></a>
                    <a href="/html/<%=object[4] %>.htm" target="_blank"><span><%=unitname %></span></a>
                    <br />
                    <span>浏览次数:<%=object[10] %></span>&nbsp;
                    <span>收藏次数：<%=object[9] %></span> &nbsp;
                    <span>下载次数：<%=object[11] %></span>  &nbsp;
                    <span>评分:<%=object[7] %>（<%=object[8] %>人评）</span>&nbsp;
                    <div class="content_left_module_main_font_style" title="<%=HtmlUtil.getHtmlText(object[13].toString()) %>">简介：<%=descript %></div>
                    <%
                    if(object[12] != null && object[12].toString().length() > 0){
                    	objKeywords = object[12].toString().split(" ");
                    	if(objKeywords != null && objKeywords.length > 0){
                    %>
                    <span class="content_left_module_main_font_p1">关键词：</span>
                    <%
                    for(int m=0, n=objKeywords.length; m<n; m++){
                    	for(int x=0, y=keywordList.size(); x<y; x++){
    						keys = keywordList.get(x);
    						if(objKeywords[m].indexOf(keys) != -1){
    							objKeywords[m] = objKeywords[m].replaceAll(keys, keywordMap.get(keys));
    		    			}
                    	}
                    %>
                    <a href="javascript:searchKeywords('<%=objKeywords[m] %>')"><span name="shuxue" class="content_left_module_main_font_p"><%=objKeywords[m] %></span></a>
                    <%} %>
                    <%}} %>
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
            	<p>相关文档</p>
           	</div>
            <div class="content_right_module_main">
            	<%
            	List aboutDocList = (List)request.getAttribute("aboutDocList");
            	DocFileInfo dfi = null;
            	for(int i=0, size=aboutDocList.size(); i<size; i++){
            		dfi = (DocFileInfo)aboutDocList.get(i);
            	%>
            	<div class="content_right_module2_main_<%=i+1 %>">
                	<a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank"><img src="<%=dfi.getSketch() %>" width="67" height="82"/></a>
                	<a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank" title="<%=dfi.getTitle() %>"><p><%=SubStringDirectiveModel.subString(dfi.getTitle(), 8, "...") %></p></a>
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
        
         <div class="content_right_module">
        	<div class="content_right_module_title">
            	<p>相关学校</p>
           	</div>
            <div class="content_right_module_main">
            	<%
            	List aboutSchoolList = (List)request.getAttribute("aboutSchoolList");
            	Object[] unitObj = null;
            	for(int i=0, size=aboutSchoolList.size(); i<size; i++){
            		unitObj = (Object[])aboutSchoolList.get(i);
            	%>
            	<div class="content_right_module_main_<%=i+1 %>">
                	<a href="/html/<%=unitObj[0] %>.htm" target="_blank"><img src="<%=unitObj[2] %>" width="118" height="70"/></a>
                	<a href="/html/<%=unitObj[0] %>.htm" target="_blank" title="<%=unitObj[1] %>"><p><%=SubStringDirectiveModel.subString(unitObj[1].toString(), 16, "...") %></p></a>
                </div>
                <%} %>
            </div>
        </div>
        
        <div class="content_right_module2">
        	<div class="content_right_module_title">
            <p>热门搜索</p>
           	</div>
           	  <%
	          List hotSearchList = (List)request.getAttribute("hotSearchList");
	          GpwSearchHots gsh = null;
	          for(int i=0, size=hotSearchList.size(); i<size; i++){
	        	  gsh = (GpwSearchHots)hotSearchList.get(i);
	          %>
	          <a href="javascript:searchKeywords('<%=gsh.getKeywords() %>')" class="content_right_module2_a"><%=gsh.getKeywords() %></a>
	          <%} %>
        </div>
    </div>
    
</div>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>