<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@ page contentType="text/html;charset=utf-8"%>
<div class="school_home">
    <div class="school_banner">
      <a href="/html/${f_unitid }.htm"><img src="${f_unitinfo.banner}" /></a>
    </div>
    <div class="school_right">
      <%SysUnitInfo f_unitinfo = (SysUnitInfo)request.getAttribute("f_unitinfo"); %>
      <dl>
        <dt><%if(f_unitinfo.getHomepage() != null && !"".equals(f_unitinfo.getHomepage())){ %><a href="${f_unitinfo.homepage}" target="_blank"><img src="${f_unitinfo.logo}"/></a><%}else{ %><img src="${f_unitinfo.logo}"/><%} %></dt>
        <dd>教师数量：<font id="s_js">0</font></dd>
        <dd>微课数量：<font id="s_wk">0</font></dd>
        <dd>文档数量：<font id="s_doc">0</font></dd>
        <a href="javascript:praise()" class="zan_btn" id="s_praise">${f_unitinfo.praise}</a>
      </dl>
      <p class="p_dl">
      	<%=SubStringDirectiveModel.subString(f_unitinfo.getKeywords(), 150, "...") %>
      	<a href="/v-sinfo-${f_unitid}-0.htm" target="_blank">［查看更多］</a>
      </p>
    </div>
</div>
<script type="text/javascript">
function praise(){
	$.ajax({
	   url: "/v.bo?method=praise",
	   data: "unitid=${f_unitid}&flag=1",
	   success: function(msg){
	   	 if('-1' == msg){
	   	 	alert("您已点过赞！");
	   	 }else{
	   	    $("#s_praise").text(msg);
	   	 }
	   }
	}); 
	return;
}
function getSchoolStatistics(){
	$.ajax({
        type: "get",
        url: "/v.bo?method=getSchoolStatistics",
        data: "unitid=${f_unitid}&ram=" + Math.random(),
        dataType: "text",
        success: function(data){
        	var statistics = data.split(";");
        	$("#s_js").text(statistics[0]);
        	$("#s_wk").text(statistics[1]);
        	$("#s_doc").text(statistics[2]);
        	$("#s_praise").text(statistics[3]);
         }
    });
}
getSchoolStatistics();
</script>
