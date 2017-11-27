<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>评论管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/public/js/dialog/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/public/js/comm.js"></script>
<script type="text/javascript" src="/public/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function searchRecord() {
		var type = document.getElementById("type").value;
		var begintime = document.getElementById("begintime").value;
		var endtime = document.getElementById("endtime").value;
		if (type == "") {
			top.Dialog.alert("请选择查询类型！");
			return false;
		}
		if(begintime==""){
		   top.Dialog.alert("请选择开始时间！");
		   return false;
		}
		if(endtime==""){
		  top.Dialog.alert("请选择结束时间！");
		  return false;
		}
		if(type=="1"){
		  if(begintime>endtime){
		  top.Dialog.alert("开始时间不能大于结束时间，请重新选择！");
		   return false;
		  }
		  if((endtime.substr(0,4)!=begintime.substr(0,4))||(endtime.substr(5,2)-begintime.substr(5,2)>1)
		           ||(endtime.substr(5,2)-begintime.substr(5,2)==1
		           &&endtime.substr(8,2)>begintime.substr(8,2))){
		        top.Dialog.alert("按日查询开始时间与结束时间最大只能相差1个月！");
		        return false;
		  }
		}
	    if(type==2){
	      if(begintime>endtime){
	       top.Dialog.alert("开始时间不能大于结束时间，请重新选择！");
	        return false;
	      }
	      if(endtime.substr(0, 4) - begintime.substr(0, 4) > 1
					|| (endtime.substr(0, 4) - begintime.substr(0, 4) ==1 && endtime
							.substr(5, 2) > begintime.substr(5, 2))){
						top.Dialog.alert("按周查询开始时间与结束时间只能相差1年！");
						return false;
			 }
	    }
		if (type == "3") {
			if (begintime > endtime) {
				top.Dialog.alert("开始时间不能大于结束时间，请重新选择时间！");
				return false;
			}
			if (endtime.substr(0, 4) - begintime.substr(0, 4) > 2
					|| (endtime.substr(0, 4) - begintime.substr(0, 4) ==2 && endtime
							.substr(5, 2) > begintime.substr(5, 2))) {
				top.Dialog.alert("按月查询开始时间与结束时间最大只能相差2年！");
				return false;
			}
		}
		document.pageForm.action = "/sysUnitInfoStatisticAction.do?method=main";
		document.pageForm.submit();
	}
 
 function a1(){
   var begtime=document.getElementById("begintime");
   var endtime=document.getElementById("endtime");
   begtime.value="";
   endtime.value="";
   var tab1=document.getElementById("a1");
   var tab2=document.getElementById("a2");
   var tab3=document.getElementById("a3");
   var type=document.getElementById("type");
   type.value="3";
   tab1.setAttribute("class", "lik_1");
   tab2.setAttribute("class", "");
   tab3.setAttribute("class", "");
   document.pageForm.action = "/sysUnitInfoStatisticAction.do?method=main";
   document.pageForm.submit();
};
function a2(){
   var begtime=document.getElementById("begintime");
   var endtime=document.getElementById("endtime");
   begtime.value="";
   endtime.value="";
   var tab1=document.getElementById("a1");
   var tab2=document.getElementById("a2");
   var tab3=document.getElementById("a3");
   var type=document.getElementById("type");
   type.value="2";
   tab1.setAttribute("class", "");
   tab2.setAttribute("class", "lik_1");
   tab3.setAttribute("class", "");
   document.pageForm.action = "/sysUnitInfoStatisticAction.do?method=main";
   document.pageForm.submit();
  };
function a3(){
   var begtime=document.getElementById("begintime");
   var endtime=document.getElementById("endtime");
   begtime.value="";
   endtime.value="";
   var tab1=document.getElementById("a1");
   var tab2=document.getElementById("a2");
   var tab3=document.getElementById("a3");
   var type=document.getElementById("type");
   type.value="1";
   tab1.setAttribute("class", "");
   tab2.setAttribute("class", "");
   tab3.setAttribute("class", "lik_1");
   document.pageForm.action = "/sysUnitInfoStatisticAction.do?method=main";
   document.pageForm.submit();
  };
  function play(){
   var temp=<%=request.getAttribute("type")%>;
   var tab1=document.getElementById("a1");
   var tab2=document.getElementById("a2");
   var tab3=document.getElementById("a3");
   var type=document.getElementById("type");
   if(temp==3){
   type.value="3";
   tab1.setAttribute("class", "lik_1");
   tab2.setAttribute("class", "");
   tab3.setAttribute("class", "");
   }else if(temp==2){
    type.value="2";
   tab1.setAttribute("class", "");
   tab2.setAttribute("class", "lik_1");
   tab3.setAttribute("class", "");
   }else if(temp==1){
    type.value="1";
   tab1.setAttribute("class", "");
   tab2.setAttribute("class", "");
   tab3.setAttribute("class", "lik_1");
   }
  }
  
</script>
</head>

<body onload="play();">
	<FORM name="pageForm" method=post>
		<TABLE class="table_search_title" width="100%" cellSpacing=1
			cellPadding=1>
			<tr>
				<td></td>
			</tr>
		</table>
		<TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1>
			<tr>
				<td class="bg_basecolor" align="left">
			 <input type="hidden" name="type" id="type"  value="3">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td width="80" height="25" align="right">开始时间</td>
							<td>
							<c:if test="${datetype=='1' }">
							<input type="text" id="begintime" size="12"
								name="createdate" value="${begintime}" readonly="readonly"
								onFocus="WdatePicker({dateFmt:'yyyy-MM', alwaysUseStartDate:true})" />
							</c:if>
							<c:if test="${datetype!='1' }">
							<input type="text" id="begintime" size="12"
								name="createdate" value="${begintime}" readonly="readonly"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd', alwaysUseStartDate:true})" />
							</c:if>
							</td>
							<td width="80" height="25" align="right">结束时间</td>
							<td>
						   <c:if test="${datetype=='1' }">
							<input type="text" id="endtime" size="12" name="enddate"
								value="${endtime }" readonly="readonly"
								onFocus="WdatePicker({dateFmt:'yyyy-MM',alwaysUseStartDate:true})" />
							</c:if>
							<c:if test="${datetype!='1' }">
							<input type="text" id="endtime" size="12" name="enddate"
								value="${endtime }" readonly="readonly"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
							</c:if>
							</td>
						</tr>
					</table></td>
				<td width="88" class="bg_basecolor"><input type="button"
					value="统计" onclick="searchRecord()" class="btn_search" />
				</td>
				<td width="88" class="bg_basecolor" >
				<a href="/sysUnitInfoStatisticAction.do?method=dowload&jpeg=${chart }"><span>导出</span></a>
				</td>
			</tr>
			<tr>
       	<div class="detailed clearfix back_1">
         <ul class="ul_1">
           <li id="tab1" onclick="a3();"><a  id="a3" class="lik_1">按日查询</a></li>
           <li id="tab2" onclick="a2();"><a  id="a2" href="#">按周查询</a></li>
           <li id="tab3" onclick="a1();"><a  id="a1" href="#">按月查询</a></li>
           </ul>
           </div>
 		 </tr>
		</table>
		<FORM name="pageForm" method=post>
			<img alt="图表" src="${chartUrl }" mce_src="${chartUrl }"> <br>
</body>
</html>
