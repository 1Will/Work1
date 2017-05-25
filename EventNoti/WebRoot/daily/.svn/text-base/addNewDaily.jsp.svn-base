<%@page import="org.syntax.jedit.InputHandler.document_end"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="souvc.pojo.WeixinUserInfo"%>
<%@page import="souvc.pojo.UserInfo"%>
<%@page import="main.pojo.ProjectInfo"%>
<%@page import="main.pojo.CustomerInfo"%>
<%@page import="main.pojo.ContactArchives"%>
<%
  String userid=request.getAttribute("userid").toString();
  String userName=(String)request.getAttribute("userName");
  String loginName=(String)request.getAttribute("LoginName");//名字简称lf
  String code=(String)request.getAttribute("code");
  String dutyName=(String)request.getAttribute("dutyName");
  String deptName=(String)request.getAttribute("deptName");
  String deptId=request.getAttribute("deptId").toString();
  String dutyId=request.getAttribute("dutyId").toString();
  String instId=request.getAttribute("instId").toString();
  String organization=request.getAttribute("organization").toString();
  String personnelId=request.getAttribute("personnelId").toString();
 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,user-scalable=0">
		<title>新增日报页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
		
		<script type="text/javascript">
	

 function getBackvisitContext(){
    var url="searchBackvisitContextServlet?method=getBackvisitContext";//请求后台的url  
       //  alert("测试！！进入了function方法");

    url+="&t="+(new Date()).valueOf();//url参数  
    
    $.ajax({  
    url:url,  
    type:'POST',//POST方式  
    dataType:'text',//返回text类型    
    data: {currentDate:$("#currentDate").val(),userName:$("#userName").val(),loginName:$("#loginName").val()},
    beforeSend:function(xmlHttpRequest,status){  
      
    },  
   success:function(data,status){  
    var obj=eval(data);//解析  
    //判断日报是否存在
    if(obj[0].list[1]==undefined ){
       alert("当前日期已存在日报,请重新选择!");
    }else{
    
       var backVisitList=obj[0].list[0];
       var contactArchivesNum=obj[0].list[1];
       var customerInfoNum=obj[0].list[2];
          var string1  =  setBackvisitContent1(backVisitList);//获取部分回访内容
          var string2  =   setBackVisitContent(contactArchivesNum,customerInfoNum);//只获取客户数和联系人数
          var  bcString=string1+string2; 
          document.getElementById("backvisitContext").value=bcString;             
           // alert("bcString: "+bcString);
          setBackVisitIds(backVisitList);//获取backVisitIds
          //根据选择日期 填写相应星期
          var weekDate =getDateWeek($("#currentDate").val()); 
          document.getElementById("weekDate").value=weekDate;  
          
       }
   
   },  
    complete:function(xmlHttpRequest,status){  
    
    },  
    error:function(){  
    
     }  
    });  

 } 	
   //获取部分回访内容加数量  如果需要的话得重新到后台传送相应参数 和接收参数
   function  setBackvisitContent1(backVisitList){
                 //    alert("进入setBackvisitContent方法中.");
			         var String1=null;
			         var n=3;//解析list2 其中n+1为一组数据的个数
			 
			         if(backVisitList.length!=0){
		                  for(var i=0;i<backVisitList.length-n;i++){
						        String1=i+1+".客户名称:"+backVisitList[i+1]
								+"; 联系人:"+backVisitList[i+2]
								+"; 项目名称:"+(backVisitList[i+3]==null?"   ":backVisitList[i+3])+"; \n";
								 i=i+n;
							//	+"; 回访内容:"+backVisitList[i].visitContent +"; \n";
						       
					      }
                           return  String1;
     	     	       }else{
                           return  String1="";
     	     	       }
                }
   
   function setBackVisitIds(backVisitList){
       var ids='';
       var n=3;//解析list2 其中n+1为一组数据的个数
       for(var i=0;i<backVisitList.length-n;i++){
            if(ids==''){
               ids+=backVisitList[i];
            }else{
               i=i+n;
               ids+=("-"+backVisitList[i]);
            }
       }
       document.getElementById("backVisitIds").value=ids;
    //   alert("ids: "+ids);
   }
   
   function setBackVisitContent(contactArchivesNum,customerInfoNum){
       var  bcString=null;
       var  String2=1+".新增客户数:"+customerInfoNum+"个;\n";
       var  String3=2+".新增联系人数:"+contactArchivesNum+"个;\n";
            bcString=String2+String3;
       return bcString;
 
    }
   
  
  //根据选择日期获取对应时间
    function getDateWeek(date1)
   {
    var selectedDate=new Date(date1);
    var week = "";
     switch(selectedDate.getDay())
     {  
     
        case 0:
            week="日";
            break;
        case 1:
            week="一";
            break;
        case 2:
            week="二";
            break;
        case 3:
            week="三";
            break;
        case 4:
            week="四";
            break;
        case 5:
            week="五";
            break;
        case 6:
            week="六";
            break;
     }
     return week;
     
}
 
    //获取当前(yyyy-MM-dd)型日期
   function aa(){
  // alert("进入aa方法！");
  var nowDate=formatDate();
  document.getElementById("currentDate").value=nowDate; 
   }
 
 
			function formatDate() {
				var d = new Date(); 
				month = '' + (d.getMonth() + 1);				 
				day = '' + d.getDate();
				year = d.getFullYear();
				if (month.length < 2) {
					month = '0' + month;
				}
				if (day.length < 2) {
					day = '0' + day;
				}
				return [ year, month, day ].join('-');
			}
			
		
			
			
			function submitInfo() {

				if (document.getElementById("currentDate").value.replace(
						/\s*/g, "") == "") {
					alert("请选择填写日期");
					return false;
				}
				
				
				$('#myForm').submit();

				
			}
		</script>

		
	</head>

	<body onload="aa();getBackvisitContext()">
	<form name="myForm" id="myForm" method="post"
		action="/EventNoti/addDailyServlet">
		<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
		<input type="hidden" name="dutyId" id="dutyId" value="<%=dutyId%>" />
		<input type="hidden" name="dutyName" id="dutyName" value="<%=dutyName%>" />
		<input type="hidden" name="deptId" id="deptId" value="<%=deptId%>" />
		<input type="hidden" name="deptName" id="deptName" value="<%=deptName%>" />
		<input type="hidden" name="instId" id="instId" value="<%=instId%>" />
		<input type="hidden" name="organization" id="organization" value="<%=organization%>" />
		<input type="hidden" name="userName" id="userName" value="<%=userName%>" /> 
		<input type="hidden" name="loginName" id="loginName" value="<%=loginName%>" /> 
		<input type="hidden" name="code" id="code" value="<%=code%>" /> 
		<input type="hidden" name="personnelId" id="personnelId" value="<%=personnelId%>" />
		<input type="hidden" name="backVisitIds" id="backVisitIds" value=" " />

		<div class="page__bd">
		
		
			<div class="weui-form-preview">
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label"> 报告人 </label> <em
							class="weui-form-preview__value"> <%=(userName==null||userName.equals(""))?"":userName%></em>
					</div>
				</div>

				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label"> 报告日期 </label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="date"  id="currentDate"
								name="currentDate" onchange="getBackvisitContext()" />
						</div>
					</div>
				</div>

				<div class="weui_cells">
					<div class="weui_cells_title">星期</div>
					<div class="weui_cells">
						<div class="weui_cell weui_cell_select">
							<div class="weui_cell_bd weui_cell_primary">
								<select class="weui_select" name="weekDate" id="weekDate">
									<option value="一">一</option>
									<option value="二">二</option>
									<option value="三">三</option>
									<option value="四">四</option>
									<option value="五">五</option>
									<option value="六">六</option>
									<option value="日">日</option>
								</select>
							</div>
						</div>
					</div>

					<div class="weui_cells_title">工作时间</div>
					<div class="weui_cells">
						<div class="weui_cell weui_cell_select">
							<div class="weui_cell_bd weui_cell_primary">

								<select name="startHour">
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="00">00</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
								</select>: <select name="startMinute">
									<option value="30">30</option>
									<option value="31">31</option>
									<option value="32">32</option>
									<option value="33">33</option>
									<option value="34">34</option>
									<option value="35">35</option>
									<option value="36">36</option>
									<option value="37">37</option>
									<option value="38">38</option>
									<option value="39">39</option>
									<option value="40">40</option>
									<option value="41">41</option>
									<option value="42">42</option>
									<option value="43">43</option>
									<option value="44">44</option>
									<option value="45">45</option>
									<option value="46">46</option>
									<option value="47">47</option>
									<option value="48">48</option>
									<option value="49">49</option>
									<option value="50">50</option>
									<option value="51">51</option>
									<option value="52">52</option>
									<option value="53">53</option>
									<option value="54">54</option>
									<option value="55">55</option>
									<option value="56">56</option>
									<option value="57">57</option>
									<option value="58">58</option>
									<option value="59">59</option>
									<option value="00">00</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
								</select>- <select name="endHour">

									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="00">00</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
								</select>: <select name="endMinute">
									<option value="00">00</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
									<option value="32">32</option>
									<option value="33">33</option>
									<option value="34">34</option>
									<option value="35">35</option>
									<option value="36">36</option>
									<option value="37">37</option>
									<option value="38">38</option>
									<option value="39">39</option>
									<option value="40">40</option>
									<option value="41">41</option>
									<option value="42">42</option>
									<option value="43">43</option>
									<option value="44">44</option>
									<option value="45">45</option>
									<option value="46">46</option>
									<option value="47">47</option>
									<option value="48">48</option>
									<option value="49">49</option>
									<option value="50">50</option>
									<option value="51">51</option>
									<option value="52">52</option>
									<option value="53">53</option>
									<option value="54">54</option>
									<option value="55">55</option>
									<option value="56">56</option>
									<option value="57">57</option>
									<option value="58">58</option>
									<option value="59">59</option>

								</select> <br>
							</div>
						</div>
					</div>
					<div class="weui-form-preview">
						<div class="weui-form-preview__hd">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label"> 所属部门 </label> <em
									class="weui-form-preview__value"> <%=(deptName==null||deptName.equals(""))?"":deptName%></em>
							</div>
						</div>
					</div>
						<div class="weui-form-preview">
							<div class="weui-form-preview__hd">
								<div class="weui-form-preview__item">
									<label class="weui-form-preview__label"> 职位 </label> 
									<em class="weui-form-preview__value"> 
									<%=(dutyName==null||dutyName.equals(""))?"":dutyName%></em>
								</div>
							</div>
                        </div>
							<div class="weui_cells_title  weui_cell_warn">拜访工作内容</div>
							<div class="weui_cells weui_cells_form">
								<div class="weui_cell">
									<div class="weui_cell_bd weui_cell_primary">
										<textarea class="weui_textarea" id="backvisitContext" name="backvisitContext"
											   rows="3" readonly="readonly" > </textarea>
									</div>
								</div>
							</div>
						
							<div class="weui_cells_title  weui_cell_warn">工作内容</div>
							<div class="weui_cells weui_cells_form">
								<div class="weui_cell">
									<div class="weui_cell_bd weui_cell_primary">
										<textarea id="workContext" name="workContext"
											class="weui_textarea"   placeholder="请输入非客户拜访类的工作内容" onfocus="this.placeholder=' '"  
											maxlength="500"   rows="3"></textarea>
									</div>
								</div>
							</div>


							<div class="weui_cells_title">收获/问题/建议</div>
							<div class="weui_cells weui_cells_form">
								<div class="weui_cell">
									<div class="weui_cell_bd weui_cell_primary">
										<textarea id="questions" name="questions"
											class="weui_textarea"  rows="3"
											placeholder="请输入今日收获/问题/建议" onfocus="this.placeholder=' '" 
										    maxlength="500"  ></textarea>
									</div>
								</div>
							</div>
							
				
						<div class="weui_btn_area">
							<a class="weui_btn weui_btn_primary" href="javascript:void(0)"
								id="showTooltips" onclick="submitInfo();">提交</a>
						</div>

					</div>
				</div>
			</div>
	</form>


</body>
</html>
