<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.util.string.encode.Encode"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-申请报错</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">


var temp1=false,temp2=false;
function feedback(){
	if(!temp1){
		checkValue(document.getElementById("content").value, 'content');
	}
	if(!temp2){
		checkValue(document.getElementById("contact").value, 'contact');
	}
	if(temp1 && temp2){
		document.feedbackForm.action = '/v.bo?method=addreportWrong';
		document.feedbackForm.submit();
	}
}
function checkValue(value, id){
	if(id == 'content'){
		if(value == '' || value == "请您指正当前微课或文档所对应的章节目录和知识点，或所属分类有何不妥，我们会根据实际情况做出相应修改。您的意见对微课慕课网非常重要，是微课慕课网前进的动力！"){
			document.getElementById(id).value = "请您指正当前微课或文档所对应的章节目录和知识点，或所属分类有何不妥，我们会根据实际情况做出相应修改。您的意见对微课慕课网非常重要，是微课慕课网前进的动力！";
			document.getElementById(id + "_div").className = "two";
			document.getElementById(id + "_tip").style.display = "block";
			document.getElementById(id).style.border = "#f0c1c7 1px solid";
			document.getElementById(id).style.color = "#aaa";
			temp1 = false;
		}else{
			document.getElementById(id + "_div").className = "one";
			document.getElementById(id + "_tip").style.display = "none";
			document.getElementById(id).style.border = "#cccccc 1px solid";
			temp1 = true;
		}
	}
	if(id == 'contact'){
		if(value == '' || value == "请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！"){
			document.getElementById(id).value = "请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！";
			document.getElementById(id + "_div").className = "two";
			document.getElementById(id + "_tip").style.display = "block";
			document.getElementById(id).style.border = "#f0c1c7 1px solid";
			document.getElementById(id).style.color = "#aaa";
			temp2 = false;
		}else{
			document.getElementById(id + "_div").className = "one";
			document.getElementById(id + "_tip").style.display = "none";
			document.getElementById(id).style.border = "#cccccc 1px solid";
			temp2 = true;
		}
	}
}
function isNull(value, id){
	if(value == '请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！' || value == '请您指正当前微课或文档所对应的章节目录和知识点，或所属分类有何不妥，我们会根据实际情况做出相应修改。您的意见对微课慕课网非常重要，是微课慕课网前进的动力！'){
		document.getElementById(id).value = "";
		document.getElementById(id).style.border = "#3eabff 1px solid";
		document.getElementById(id).style.color = "#000";
	}
}
</script>
</head>
<body style="background:#f7f7f7;">
<%@ include file="top.jsp"%>
<div class="view_main"  style="width: 1000px;margin:0 100 auto;" >
	<div class="view_right">
		<form name="feedbackForm" method="post">
		<input type="hidden" name="fileid" value="${fileid}"/>
        <input type="hidden" name="userid" value="${userid }"/>		
        <input type="hidden" name="type" value="${type }"/>
        
        
        
		<div class="view_right_01">
		    <h3>报错项目名称(<c:if test="${type==1 }">文档</c:if> <c:if test="${type==2 }">微课</c:if>)</h3>
		    <div class="view_right_one">
		     <input type="text" class="line_02" readonly="readonly"  value="${title }"/>
		    </div>
		    
		    
		    
		    <c:if test="${type==1 }">
		    <%
         DocFileInfo model=(DocFileInfo)request.getAttribute("model");
             %>
		    <logic:notEqual value="2010" name="unittype">
              <div class="view_right_one" style="padding-top: 10px;padding-bottom: 10px;">
                                      <span>文档分类：</span>
                   <input type="text" style="width:480px;color:#888;height:30px;line-height:30px;" readonly="readonly" value="${filmtype }" />
            </div>
          </logic:notEqual>
          <logic:equal value="2010" name="unittype">
                <div class="view_right_one" style="padding-top: 10px;padding-bottom: 10px;">
                       <span>教材目录：
                                学科：<input type="text" style="width:100px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="subjectname" id="subjectname" value="${subjectInfo.subjectname }" onclick="selectSubject()"/>
                                年级：<input type="text" style="width:100px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="gradename" id="gradename" value="${gradeInfo.gradename }" onclick="selectGrade()"/>
                                版本：<input type="text" style="width:200px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="versionname" id="versionname" value="${versionInfo.versionname }" onclick="selectVersion()"/>
            <br/><br/>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                 章节：<input type="text" style="width:480px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="columnname" id="columnname" value="${columnInfo.columnname }" onclick="selectColumn()"/>
            </span>
             <br/><br/>
              &nbsp;&nbsp;
              <span>知识点：
              <c:if test="${knopointnames=='点击选择知识点'}">
                <input type="text" style="width:315px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="knopointname" id="knopointname" value="无" onclick="selectKnopoint()"/>
              </c:if>
               <c:if test="${knopointnames!='点击选择知识点'}">
              <input type="text" style="width:315px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="knopointname" id="knopointname" value="${knopointnames }" onclick="selectKnopoint()"/>
                </c:if>	
            </span>
            </div>
          </logic:equal>
		    </c:if>
		    
		    <c:if test="${type==2 }">
		    <logic:notEqual value="2010" name="unittype">
				     <%
            VodFilmInfo model=(VodFilmInfo)request.getAttribute("model");
                      %>
            <div class="view_right_one" style="padding-top: 10px;padding-bottom: 10px;">
               <span>微课分类：</span>
               <input type="text" style="width:480px;color:#888;height:30px;line-height:30px;" readonly="readonly" value="${filmtype }" />
            </div>
          </logic:notEqual>
          <logic:equal value="2010" name="unittype">
          <%
            VodFilmInfo model=(VodFilmInfo)request.getAttribute("model");
          %>
           <div class="view_right_one" style="padding-top: 10px;padding-bottom: 10px;">
               <span>教材目录：</span>
            <span>
                                学科：<input type="text" style="width:100px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="subjectname" id="subjectname" value="${subjectInfo.subjectname }" />
                                年级：<input type="text" style="width:100px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="gradename" id="gradename" value="${gradeInfo.gradename }" />
                                版本：<input type="text" style="width:200px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="versionname" id="versionname" value="${versionInfo.versionname }"/>
            <br/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    章节：<input type="text"  style="width:480px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="columnname" id="columnname" value="${columnInfo.columnname }"/>
             <br/><br/>                    
             &nbsp;&nbsp;                 
                                    知识点：
               <c:if test="${knopointnames=='点击选择知识点' }">  
<input type="text" style="width:315px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="knopointname" id="knopointname" value="无" onclick="selectKnopoint()"/>
               </c:if>  
               <c:if test="${knopointnames!='点击选择知识点' }">      
<input type="text" style="width:315px;color:#888;height:30px;line-height:30px;" readonly="readonly" name="knopointname" id="knopointname" value="${knopointnames }" onclick="selectKnopoint()"/>
               </c:if>     
            </span>
            </div>
	  </logic:equal>
		</c:if>    
		    
		    
			<h3>报错内容</h3>
			<div class="view_right_one">
				<logic:notPresent name="addOk">
				<span class="one" id="content_div">
				  <textarea name="content" id="content" class="line_01" onfocus="isNull(this.value, 'content')" onblur="checkValue(this.value, 'content')">请您指正当前微课或文档所对应的章节目录和知识点，或所属分类有何不妥，我们会根据实际情况做出相应修改。您的意见对微课慕课网非常重要，是微课慕课网前进的动力！</textarea>
				</span>
				<span class="three" id="content_tip">报错内容不能为空！</span>
				<span class="one" id="contact_div">
				  <input name="contact" id="contact" type="text" class="line_02" value="请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！" onfocus="isNull(this.value, 'contact')" onblur="checkValue(this.value, 'contact')"/>
				</span>
				
				

				
				
				
				
				
				<span class="three" id="contact_tip">联系方式不能为空！</span>
				<span class="four">
				<a href="javascript:feedback()"><img src="/skin/wkmk/images/f05.gif" border="0" /></a>
				</span>
				</logic:notPresent>
				<logic:present name="addOk">
				<div>
					<p style="font-size:20px;padding-top:130px;padding-left:260px;">提交成功</p>
					<p style="font-size:12px;padding-top:10px;padding-left:180px;padding-bottom:150px;">感谢您的建议，我们会努力完善微课慕课网！</p>
				</div>
				</logic:present>
			</div>
			
			<div class="view_right_two">
			微课幕课交流QQ群<br />
			282673318<br />
			职教微课大赛专属QQ群<br />
			474297139<br /><br />
			技术支持服务电话<br />
			010-83456666<br />
			010-84929209
			</div>
		</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>